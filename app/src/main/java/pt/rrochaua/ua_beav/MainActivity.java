package pt.rrochaua.ua_beav;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public int getNumbCond() {
        return numbCond;
    }

    public void setNumbCond(int numbCond) {
        this.numbCond = numbCond;
    }







    static final int REQUEST_IMAGE_CAPTURE = 1;


    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
*/

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }











    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToMenuFragment();


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


