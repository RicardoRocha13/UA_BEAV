package pt.rrochaua.ua_beav;


import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import pt.rrochaua.ua_beav.fragments.Menu_fragment;
import pt.rrochaua.ua_beav.fragments.NatAci;
import pt.rrochaua.ua_beav.fragments.VeicInt1;
import pt.rrochaua.ua_beav.models.CircExternas1;
import pt.rrochaua.ua_beav.models.CircExternas2;
import pt.rrochaua.ua_beav.models.CondInterveniente1;
import pt.rrochaua.ua_beav.models.CondInterveniente2;
import pt.rrochaua.ua_beav.models.ConseqPassageiros;
import pt.rrochaua.ua_beav.models.ConseqPeoes;
import pt.rrochaua.ua_beav.models.Form1Model;
import pt.rrochaua.ua_beav.models.FotosEsquemas;
import pt.rrochaua.ua_beav.models.NatuAcidente;
import pt.rrochaua.ua_beav.models.SemVitim;

import pt.rrochaua.ua_beav.helpers.Util;
import pt.rrochaua.ua_beav.models.VeicInterveniente;

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
        VeicInt1.OnVeicInt1Listener,
        Menu_fragment.OnMenu_fragmentListener{

    ArrayList<Uri> mArrayUri;

    public static final int PERMISSIONS_REQUEST_LOCATION = 200;
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 100;
    static final int PICK_IMAGE_MULTIPLE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    String mCurrentPhotoPath;
    ProgressDialog dialog;

    LocationManager locationManager = null;

    //variaveis para guardar
    int numbCond;

    ArrayList<Integer> menuButtons = new ArrayList<>();
    ArrayList<Integer> menuButtons2 = new ArrayList<>();

    public ArrayList<Integer> getMenuButtons() {
        return menuButtons;
    }

    public void setMenuButtons(ArrayList<Integer> menuButtons) {
        this.menuButtons = menuButtons;
    }

    public ArrayList<Integer> getMenuButtons2() {
        return menuButtons2;
    }

    public void setMenuButtons2(ArrayList<Integer> menuButtons2) {
        this.menuButtons2 = menuButtons2;
    }


    //Form1
    ArrayList<Form1Model> form1 = new ArrayList<>();

    // S/ vitimas
    ArrayList<SemVitim> sViti = new ArrayList<>();

    // circuntancias externas 1
    ArrayList<CircExternas1> cExt1 = new ArrayList<>();

    // circunstancias externas 2
    ArrayList<CircExternas2> cExt2 = new ArrayList<>();

    // Natureza Acidente
    ArrayList<NatuAcidente> natuAcidente = new ArrayList<>();

    // Veiculos intervenientes
    ArrayList<VeicInterveniente> vInterv = new ArrayList<>();

    // Condutor Interveniente 1
    ArrayList<CondInterveniente1> cInterv1 = new ArrayList<>();

    // Condutor Interveniente 2
    ArrayList<CondInterveniente2> cInterv2 = new ArrayList<>();

    // Consequencia Passageiros
    ArrayList<ConseqPassageiros> consPassageiros = new ArrayList<>();

    // Consequencia Peoes
    ArrayList<ConseqPeoes> consPeoes = new ArrayList<>();

    // Fotos e Esquemas
    ArrayList<FotosEsquemas> fotosEsquemas = new ArrayList<>();

    // ############################
    // Begin of getters and setters
    // ############################

    // chamar, por exemplo esta função apartir de um fragment a variável numCond, para guardar na mainactivity o valor
    public void setNumbCond(int numbCond) {
        this.numbCond = numbCond;
    }

    //chamar esta função para chamar o valor da variável numbCond da main activity para o fragment em questão
    public int getNumbCond() {
        return numbCond;
    }

    public ArrayList<Form1Model> getForm1() {
        return form1;
    }

    public void setForm1(ArrayList<Form1Model> form1) {
        this.form1 = form1;
    }

    public ArrayList<SemVitim> getsViti() {
        return sViti;
    }

    public void setsViti(ArrayList<SemVitim> sViti) {
        this.sViti = sViti;
    }

    public ArrayList<CircExternas1> getcExt1() {
        return cExt1;
    }

    public void setcExt1(ArrayList<CircExternas1> cExt1) {
        this.cExt1 = cExt1;
    }

    public ArrayList<CircExternas2> getcExt2() {
        return cExt2;
    }

    public void setcExt2(ArrayList<CircExternas2> cExt2) {
        this.cExt2 = cExt2;
    }

    public ArrayList<NatuAcidente> getNatuAcidente() {
        return natuAcidente;
    }

    public void setNatuAcidente(ArrayList<NatuAcidente> natuAcidente) {
        this.natuAcidente = natuAcidente;
    }

    public ArrayList<VeicInterveniente> getvInterv() {
        return vInterv;
    }

    public void setvInterv(ArrayList<VeicInterveniente> vInterv) {
        this.vInterv = vInterv;
    }

    public ArrayList<CondInterveniente1> getcInterv1() {
        return cInterv1;
    }

    public void setcInterv1(ArrayList<CondInterveniente1> cInterv1) {
        this.cInterv1 = cInterv1;
    }

    public ArrayList<CondInterveniente2> getcInterv2() {
        return cInterv2;
    }

    public void setcInterv2(ArrayList<CondInterveniente2> cInterv2) {
        this.cInterv2 = cInterv2;
    }

    public ArrayList<ConseqPassageiros> getConsPassageiros() {
        return consPassageiros;
    }

    public void setConsPassageiros(ArrayList<ConseqPassageiros> consPassageiros) {
        this.consPassageiros = consPassageiros;
    }

    public ArrayList<ConseqPeoes> getConsPeoes() {
        return consPeoes;
    }

    public void setConsPeoes(ArrayList<ConseqPeoes> consPeoes) {
        this.consPeoes = consPeoes;
    }

    public ArrayList<FotosEsquemas> getFotosEsquemas() {
        return fotosEsquemas;
    }

    public void setFotosEsquemas(ArrayList<FotosEsquemas> fotosEsquemas) {
        this.fotosEsquemas = fotosEsquemas;
    }

    // ##########################
    // End of getters and setters
    // ##########################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMenuFragment();
        menuButtons.add(0);
        menuButtons2.add(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }

    // Secção de permissões
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFilePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (cameraPermission && readExternalFilePermission) {
                        openCamera();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_MULTIPLE_REQUEST);
                        }
                    }
                }
                break;

            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0) {
                    boolean gPSPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (gPSPermission) {
                        dialog = ProgressDialog.show(this, "", "A obter coordenadas GPS. Por favor espere...", true);
                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        LocationListener locationListener = new MyLocationListener();
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);

                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
                        }
                    }
                }
                break;


        }
    }

    // Secção de escolha de fotos
    public void selectPhoto() {
        Intent intentMultiple = new Intent();
        intentMultiple.setType("image/*");
        intentMultiple.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intentMultiple.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentMultiple, "Select a Picture"), PICK_IMAGE_MULTIPLE);
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) +
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_MULTIPLE_REQUEST);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_MULTIPLE_REQUEST);
                }
            }
        } else {
            openCamera();
        }

    }

    public void dispatchGetCoorIntent() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
                }
            }

        } else {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Localização desligada");
                dialog.setMessage("Para utilizar esta funcionalidade tem de ter o serviço de localização activo. Carregue no botão definições e active essa funcionalidade. Este texto está horrivel, é favor de o melhorar.");
                dialog.setPositiveButton("Defenições", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        });
                dialog.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();


            }else{
                dialog = ProgressDialog.show(this, "", "A obter coordenadas GPS. Por favor espere...", true);
                LocationListener locationListener = new MyLocationListener();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
            }

        }
    }

    public void testeSAVE(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm");

        System.out.println("##################### FORM1 ####################");
        System.out.println("DATA E HORA: " + sdf.format(form1.get(0).dataHora));
        System.out.println("Localização: " + form1.get(0).localizacao);
        System.out.println("Local: " + form1.get(0).local);
        System.out.println("LAT: " + form1.get(0).coordLat);
        System.out.println("LON: " + form1.get(0).coordLon);
        System.out.println("Tipo Acidente: " + form1.get(0).tipoAcidente);
        System.out.println("N. Peoes: " + form1.get(0).nPeoesVitimas);
        System.out.println("Natureza Acidente: " + form1.get(0).naturezaAcidente);
        System.out.println("N. Veiculos: " + form1.get(0).nVeiculos);

    }



    //Listener do GPS
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            System.out.println("###################################");
            System.out.println("LAT: " + location.getLatitude());
            System.out.println("LONG: " + location.getLongitude());
            System.out.println("###################################");
            dialog.dismiss();

            Fragment fragCoor = getFragmentManager().findFragmentByTag("Form1");
            ((TextView) fragCoor.getView().findViewById(R.id.editTextLat)).setText("" + location.getLatitude());
            ((TextView) fragCoor.getView().findViewById(R.id.editTextLon)).setText("" + location.getLongitude());

            //desliga updates GPS
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    //Resultados dos Intents
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_MULTIPLE:
                if (resultCode == RESULT_OK && data != null) {
                    if (data.getData() != null) {
                        Uri mImageUri = data.getData();

                        mArrayUri = new ArrayList<Uri>();
                        mArrayUri.add(mImageUri);
                    } else {
                        if (data.getClipData() != null) {
                            ClipData mClipData = data.getClipData();
                            mArrayUri = new ArrayList<Uri>();
                            for (int i = 0; i < mClipData.getItemCount(); i++) {
                                ClipData.Item item = mClipData.getItemAt(i);
                                Uri uri = item.getUri();
                                mArrayUri.add(uri);
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "Não selecionou nenhuma fotografia", Toast.LENGTH_LONG).show();
                }









                //só para demonstração, enquanto não está a enviar para base de dados
                if(mArrayUri!=null) {
                    System.out.println("##########################################################");
                    System.out.println("NUMBER OF SELECTED FILES: " + mArrayUri.size());
                    for (int p = 0; p < mArrayUri.size(); p++) {
                        System.out.println("URI PHOTO " + p + ": " + mArrayUri.get(p).toString());
                    }
                    System.out.println("##########################################################");
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

    @Override
    public void goToMenu_fragmentFragment() {
        Util.switchFragment(getFragmentManager(), "Menu", Menu_fragment.newInstance());
    }


}


