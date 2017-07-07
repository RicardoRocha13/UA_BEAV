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
import java.lang.reflect.Array;
import java.text.ParseException;
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
import pt.rrochaua.ua_beav.fragments.NatAci;
import pt.rrochaua.ua_beav.fragments.VeicInt1;
import pt.rrochaua.ua_beav.helpers.CondInterveniente1;
import pt.rrochaua.ua_beav.helpers.CondInterveniente2;
import pt.rrochaua.ua_beav.helpers.ConseqPassageiros;
import pt.rrochaua.ua_beav.helpers.ConseqPeoes;
import pt.rrochaua.ua_beav.helpers.FotosEsquemas;
import pt.rrochaua.ua_beav.helpers.SemVitim;

import pt.rrochaua.ua_beav.helpers.SemVitimCondutor;
import pt.rrochaua.ua_beav.helpers.Util;
import pt.rrochaua.ua_beav.helpers.VeicInterveniente;

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
    //Form1
    Date dataHora;
    int localização, tipoAcidente, nPeoesVitimas,  naturezaAcidente, nVeiculos;
    String local;
    float coordLat, coordLon;

    // S/ vitimas
    ArrayList<SemVitim> sViti = new ArrayList<>();

    // circuntancias externas 1
    int tipoVia, nVias, viaTransito, tracadoViaPlanta,tracadoViaPerfil;
    int tracadoViaBerma, situacaoAcidente, intersecVias, acidenteObrasArte;
    int faixaRodagem, limiteVelocGeral, limiteVelocLocal;

    // circunstancias externas 2
    int tipoPiso, estadoConservacao, obstaculosObras, condicoesAderencia;
    int marcasPavimentos, sinalizacaoLuminosa, sinais;
    int luminosidade, fatoresAtmosfericos;

    // Natureza Acidente
    int despiste, colisao, atropelamento;

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

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public int getLocalização() {
        return localização;
    }

    public void setLocalização(int localização) {
        this.localização = localização;
    }

    public int getTipoAcidente() {
        return tipoAcidente;
    }

    public void setTipoAcidente(int tipoAcidente) {
        this.tipoAcidente = tipoAcidente;
    }

    public int getnPeoesVitimas() {
        return nPeoesVitimas;
    }

    public void setnPeoesVitimas(int nPeoesVitimas) {
        this.nPeoesVitimas = nPeoesVitimas;
    }

    public int getNaturezaAcidente() {
        return naturezaAcidente;
    }

    public void setNaturezaAcidente(int naturezaAcidente) {
        this.naturezaAcidente = naturezaAcidente;
    }

    public int getnVeiculos() {
        return nVeiculos;
    }

    public void setnVeiculos(int nVeiculos) {
        this.nVeiculos = nVeiculos;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public float getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(float coordLat) {
        this.coordLat = coordLat;
    }

    public float getCoordLon() {
        return coordLon;
    }

    public void setCoordLon(float coordLon) {
        this.coordLon = coordLon;
    }

    public ArrayList<SemVitim> getsViti() {
        return sViti;
    }

    public void setsViti(ArrayList<SemVitim> sViti) {
        this.sViti = sViti;
    }

    public int getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(int tipoVia) {
        this.tipoVia = tipoVia;
    }

    public int getnVias() {
        return nVias;
    }

    public void setnVias(int nVias) {
        this.nVias = nVias;
    }

    public int getViaTransito() {
        return viaTransito;
    }

    public void setViaTransito(int viaTransito) {
        this.viaTransito = viaTransito;
    }

    public int getTracadoViaPlanta() {
        return tracadoViaPlanta;
    }

    public void setTracadoViaPlanta(int tracadoViaPlanta) {
        this.tracadoViaPlanta = tracadoViaPlanta;
    }

    public int getTracadoViaPerfil() {
        return tracadoViaPerfil;
    }

    public void setTracadoViaPerfil(int tracadoViaPerfil) {
        this.tracadoViaPerfil = tracadoViaPerfil;
    }

    public int getTracadoViaBerma() {
        return tracadoViaBerma;
    }

    public void setTracadoViaBerma(int tracadoViaBerma) {
        this.tracadoViaBerma = tracadoViaBerma;
    }

    public int getSituacaoAcidente() {
        return situacaoAcidente;
    }

    public void setSituacaoAcidente(int situacaoAcidente) {
        this.situacaoAcidente = situacaoAcidente;
    }

    public int getIntersecVias() {
        return intersecVias;
    }

    public void setIntersecVias(int intersecVias) {
        this.intersecVias = intersecVias;
    }

    public int getAcidenteObrasArte() {
        return acidenteObrasArte;
    }

    public void setAcidenteObrasArte(int acidenteObrasArte) {
        this.acidenteObrasArte = acidenteObrasArte;
    }

    public int getFaixaRodagem() {
        return faixaRodagem;
    }

    public void setFaixaRodagem(int faixaRodagem) {
        this.faixaRodagem = faixaRodagem;
    }

    public int getLimiteVelocGeral() {
        return limiteVelocGeral;
    }

    public void setLimiteVelocGeral(int limiteVelocGeral) {
        this.limiteVelocGeral = limiteVelocGeral;
    }

    public int getLimiteVelocLocal() {
        return limiteVelocLocal;
    }

    public void setLimiteVelocLocal(int limiteVelocLocal) {
        this.limiteVelocLocal = limiteVelocLocal;
    }

    public int getTipoPiso() {
        return tipoPiso;
    }

    public void setTipoPiso(int tipoPiso) {
        this.tipoPiso = tipoPiso;
    }

    public int getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(int estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public int getObstaculosObras() {
        return obstaculosObras;
    }

    public void setObstaculosObras(int obstaculosObras) {
        this.obstaculosObras = obstaculosObras;
    }

    public int getCondicoesAderencia() {
        return condicoesAderencia;
    }

    public void setCondicoesAderencia(int condicoesAderencia) {
        this.condicoesAderencia = condicoesAderencia;
    }

    public int getMarcasPavimentos() {
        return marcasPavimentos;
    }

    public void setMarcasPavimentos(int marcasPavimentos) {
        this.marcasPavimentos = marcasPavimentos;
    }

    public int getSinalizacaoLuminosa() {
        return sinalizacaoLuminosa;
    }

    public void setSinalizacaoLuminosa(int sinalizacaoLuminosa) {
        this.sinalizacaoLuminosa = sinalizacaoLuminosa;
    }

    public int getSinais() {
        return sinais;
    }

    public void setSinais(int sinais) {
        this.sinais = sinais;
    }

    public int getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(int luminosidade) {
        this.luminosidade = luminosidade;
    }

    public int getFatoresAtmosfericos() {
        return fatoresAtmosfericos;
    }

    public void setFatoresAtmosfericos(int fatoresAtmosfericos) {
        this.fatoresAtmosfericos = fatoresAtmosfericos;
    }

    public int getDespiste() {
        return despiste;
    }

    public void setDespiste(int despiste) {
        this.despiste = despiste;
    }

    public int getColisao() {
        return colisao;
    }

    public void setColisao(int colisao) {
        this.colisao = colisao;
    }

    public int getAtropelamento() {
        return atropelamento;
    }

    public void setAtropelamento(int atropelamento) {
        this.atropelamento = atropelamento;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse("04/07/2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SemVitim semviti1 = new SemVitim(1,0);
        SemVitimCondutor semviticond = new SemVitimCondutor(2,1,2,data);
        SemVitimCondutor semviticond2 = new SemVitimCondutor(3,1,3,data);
        sViti.add(semviti1);
        sViti.add(semviticond);
        sViti.add(semviticond2);
        System.out.println("#############################################");
        for(int i = 0; i < sViti.size(); i++){
            System.out.println("Veiculo " + i);
            System.out.println(sViti.get(i).veiculo + " " + sViti.get(i).condutorPresente + " ");
            if(sViti.get(i).condutorPresente==1){
                SemVitimCondutor c = ((SemVitimCondutor) sViti.get(i));
                System.out.println(c.genero + " " + sdf.format(c.idade));
            }
        }
        System.out.println("#############################################");
        goToMenuFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                System.out.println("###################################");
                System.out.println("################ ERROR ############");
                System.out.println("###################################");
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
                        System.out.println("URI PHOTO " + p + ": " + mArrayUri.get(p));
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


}


