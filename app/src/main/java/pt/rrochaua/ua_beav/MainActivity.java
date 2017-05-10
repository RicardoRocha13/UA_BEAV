package pt.rrochaua.ua_beav;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import pt.rrochaua.ua_beav.fragments.Form1;
import pt.rrochaua.ua_beav.fragments.Form2;
import pt.rrochaua.ua_beav.fragments.FotoEsquema;
import pt.rrochaua.ua_beav.fragments.Mapa;
import pt.rrochaua.ua_beav.fragments.Menu;
import pt.rrochaua.ua_beav.helpers.Util;

public class MainActivity extends AppCompatActivity
        implements Menu.OnMenuListener,
        Mapa.OnMapaListener,
        Form1.OnForm1Listener,
        Form2.OnForm2Listener,
        FotoEsquema.OnFotoEsquemaListener{
        int numbCond;

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";



    public int getNumbCond() {
        return numbCond;
    }

    public void setNumbCond(int numbCond) {
        this.numbCond = numbCond;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMenuFragment();





    }



    public static void saveToPreferences(Context context, String key, Boolean allowed) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putBoolean(key, allowed);
        prefsEditor.commit();
    }

    public static Boolean getFromPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }

    public void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                });
        alertDialog.show();
    }

    public void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the Camera.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //finish();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startInstalledAppDetailsActivity(MainActivity.this);
                    }
                });

        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, permission);

                        if (showRationale) {
                            showAlert();
                        } else if (!showRationale) {
                            // user denied flagging NEVER ASK AGAIN
                            // you can either enable some fall back,
                            // disable features of your app
                            // or open another dialog explaining
                            // again the permission and directing to
                            // the app setting
                            saveToPreferences(MainActivity.this, ALLOW_KEY, true);
                        }
                    }
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void startInstalledAppDetailsActivity(final Activity context) {
        if (context == null) {
            return;
        }

        final Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + context.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(i);
    }

    public void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE_SECURE");
        startActivity(intent);
    }






    public void dispatchTakePictureIntent() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)

                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            }
        } else {
           openCamera();
        }
    }






    public static class MyLocation {
        Timer timer1;
        LocationManager lm;
        LocationResult locationResult;
        boolean gps_enabled=false;
        boolean network_enabled=false;

        public boolean getLocation(String context, LocationResult result)
        {
            //I use LocationResult callback class to pass location value from MyLocation to user code.
            locationResult=result;
            /*if(lm==null) {
                lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            }*/

            //exceptions will be thrown if provider is not permitted.
            try{gps_enabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);}catch(Exception ex){}
            try{network_enabled=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);}catch(Exception ex){}

            //don't start listeners if no provider is enabled
            if(!gps_enabled && !network_enabled) {
                return false;
            }

            if(gps_enabled) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
            }
            if(network_enabled) {
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
            }
            timer1=new Timer();
            timer1.schedule(new GetLastLocation(), 20000);
            return true;
        }

        LocationListener locationListenerGps = new LocationListener() {
            public void onLocationChanged(Location location) {
                timer1.cancel();
                locationResult.gotLocation(location);
                lm.removeUpdates(this);
                lm.removeUpdates(locationListenerNetwork);
            }
            public void onProviderDisabled(String provider) {}
            public void onProviderEnabled(String provider) {}
            public void onStatusChanged(String provider, int status, Bundle extras) {}
        };

        LocationListener locationListenerNetwork = new LocationListener() {
            public void onLocationChanged(Location location) {
                timer1.cancel();
                locationResult.gotLocation(location);
                lm.removeUpdates(this);
                lm.removeUpdates(locationListenerGps);
            }
            public void onProviderDisabled(String provider) {}
            public void onProviderEnabled(String provider) {}
            public void onStatusChanged(String provider, int status, Bundle extras) {}
        };



        class GetLastLocation extends TimerTask {
            @Override
            public void run() {
                lm.removeUpdates(locationListenerGps);
                lm.removeUpdates(locationListenerNetwork);

                Location net_loc=null, gps_loc=null;
                if(gps_enabled)
                    gps_loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(network_enabled)
                    net_loc=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                //if there are both values use the latest one
                if(gps_loc!=null && net_loc!=null){
                    if(gps_loc.getTime()>net_loc.getTime())
                        locationResult.gotLocation(gps_loc);
                    else
                        locationResult.gotLocation(net_loc);
                    return;
                }

                if(gps_loc!=null){
                    locationResult.gotLocation(gps_loc);
                    return;
                }
                if(net_loc!=null){
                    locationResult.gotLocation(net_loc);
                    return;
                }
                locationResult.gotLocation(null);
            }
        }

        public abstract class LocationResult{
            public abstract void gotLocation(Location location);
        }
    }











    @Override
    public void goToMenuFragment() {
        Util.switchFragment(getFragmentManager(), "MENU", Menu.newInstance());
    }


    @Override
    public void goToMapaFragment() {
        Util.switchFragment(getFragmentManager(), "Mapa", Mapa.newInstance());
    }

    @Override
    public void goToForm1Fragment() {
        Util.switchFragment(getFragmentManager(), "Form1", Form1.newInstance());
    }


    @Override
    public void goToForm2Fragment() {
        Util.switchFragment(getFragmentManager(), "Form2", Form2.newInstance());
    }

    @Override
    public void goToFotoEsquemaFragment() {
        Util.switchFragment(getFragmentManager(), "FotoEsquema", FotoEsquema.newInstance());
    }
}


