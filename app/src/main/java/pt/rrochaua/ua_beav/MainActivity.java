package pt.rrochaua.ua_beav;


import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pt.rrochaua.ua_beav.fragments.CircExt1;
import pt.rrochaua.ua_beav.fragments.CircExt2;
import pt.rrochaua.ua_beav.fragments.CondInt1;
import pt.rrochaua.ua_beav.fragments.CondInt2;
import pt.rrochaua.ua_beav.fragments.CondIntSem;
import pt.rrochaua.ua_beav.fragments.ConsPass;
import pt.rrochaua.ua_beav.fragments.ConsPeo;
import pt.rrochaua.ua_beav.fragments.Form1;
import pt.rrochaua.ua_beav.fragments.Form2;
import pt.rrochaua.ua_beav.fragments.FotoEsquema;
import pt.rrochaua.ua_beav.fragments.Mapa;
import pt.rrochaua.ua_beav.fragments.Menu;
import pt.rrochaua.ua_beav.fragments.NatAci;
import pt.rrochaua.ua_beav.fragments.VeicInt1;
import pt.rrochaua.ua_beav.helpers.Util;

public class MainActivity extends AppCompatActivity
        implements Menu.OnMenuListener,
        Mapa.OnMapaListener,
        Form1.OnForm1Listener,
        Form2.OnForm2Listener,
        FotoEsquema.OnFotoEsquemaListener,
        CircExt1.OnCircExt1Listener,
        CircExt2.OnCircExt2Listener,
        CondInt1.OnCondInt1Listener,
        CondInt2.OnCondInt2Listener,
        CondIntSem.OnCondIntSemListener,
        ConsPass.OnConsPassListener,
        ConsPeo.OnConsPeoListener,
        NatAci.OnNatAciListener,
        VeicInt1.OnVeicInt1Listener {


    int numbCond;

    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 200;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 300;
    public static final String ALLOW_KEY = "ALLOWED";
    public static final String CAMERA_PREF = "camera_pref";
    static final int PICK_IMAGE_MULTIPLE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    String imageEncoded;
    List<String> imagesEncodedList;
    String mCurrentPhotoPath;


// chamar, por exemplo esta função apartir de um fragment a variável numCond, para guardar na mainactivity o valor
    public void setNumbCond(int numbCond) {
        this.numbCond = numbCond;
    }


//chamar esta função para chamar o valor da variável numbCond da main activity para o fragment em questão
    public int getNumbCond() {
        return numbCond;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMenuFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
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


    // Secção de permissões
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
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_STORAGE);
                    }
                });
        alertDialog.show();
    }

    public void showAlertLocation() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App needs to access the GPS.");

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
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
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

    public void gPSPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
                }
            }
        }
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

            case MY_PERMISSIONS_REQUEST_STORAGE: {
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
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                for (int i = 0, len = permissions.length; i < len; i++) {
                    String permission = permissions[i];

                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean
                                showRationale =
                                ActivityCompat.shouldShowRequestPermissionRationale(
                                        this, permission);

                        if (showRationale) {
                            showAlertLocation();
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
        }
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

    // Secção de escolha de fotos
    public void selectPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
    }

    // Secção para tirar fotos
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    //Gera um ficheiro vazio para guardar posteriormente a foto
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void openCamera() {
        Intent takepictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takepictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                //Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                takepictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takepictureIntent, REQUEST_TAKE_PHOTO);
            }

        }
    }

    public void dispatchTakePictureIntent() {

        //fazer os 3 if em separado para a permissao da camara e de armazenamento, assim como para abrir a camara

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED & ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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


        } else  if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (getFromPref(this, ALLOW_KEY)) {
                showSettingsAlert();
            } else if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA)) {
                    showAlert();
                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_STORAGE);
                }
            }

        } else {
            openCamera();
        }
    }



    //Resultados dos Intents
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_MULTIPLE:
                if(resultCode == RESULT_OK && null!= data){
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    imagesEncodedList = new ArrayList<String>();
                    if(data.getData()!= null){
                        Uri mImageUri = data.getData();

                        //Get Cursor
                        Cursor cursor = getContentResolver().query(mImageUri, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imageEncoded = cursor.getString(columnIndex);
                        cursor.close();
                    } else  {
                        if (data.getClipData() != null){
                            ClipData mClipData = data.getClipData();
                            ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                            for(int i=0; i < mClipData.getItemCount(); i++){
                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mArrayUri.add(uri);
                                // Get the cursor
                                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                                // Move to first row
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                imageEncoded  = cursor.getString(columnIndex);
                                imagesEncodedList.add(imageEncoded);
                                cursor.close();
                            }
                            Log.v("LOG_TAG", "select Images" + mArrayUri.size());
                        }
                    }
                } else {
                    Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    galleryAddPic();
                    Toast.makeText(this, "Fotografia guardada com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Erro ao guardar a fotografia", Toast.LENGTH_LONG).show();
                }
                break;
        }
        
    }




    //Secção Fragments
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

    @Override
    public void goToCircExt1Fragment() {
        Util.switchFragment(getFragmentManager(), "CircExt1", CircExt1.newInstance());
    }

    @Override
    public void goToCircExt2Fragment() {
        Util.switchFragment(getFragmentManager(), "CircExt2", CircExt2.newInstance());
    }

    @Override
    public void goToCondInt1Fragment() {
        Util.switchFragment(getFragmentManager(), "CondInt1", CondInt1.newInstance());
    }

    @Override
    public void goToCondInt2Fragment() {
        Util.switchFragment(getFragmentManager(), "CondInt2", CondInt2.newInstance());
    }

    @Override
    public void goToCondIntSemFragment() {
        Util.switchFragment(getFragmentManager(), "CondIntSem", CondIntSem.newInstance());
    }

    @Override
    public void goToConsPassFragment() {
        Util.switchFragment(getFragmentManager(), "ConsPass", ConsPass.newInstance());
    }

    @Override
    public void goToConsPeoFragment() {
        Util.switchFragment(getFragmentManager(), "ConsPeo", ConsPeo.newInstance());
    }

    @Override
    public void goToNatAciFragment() {
        Util.switchFragment(getFragmentManager(), "NatAci", NatAci.newInstance());
    }

    @Override
    public void goToVeicInt1Fragment() {
        Util.switchFragment(getFragmentManager(), "VeicInt1", VeicInt1.newInstance());
    }


}


