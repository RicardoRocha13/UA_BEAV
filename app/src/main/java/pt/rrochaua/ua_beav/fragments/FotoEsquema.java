package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.FotosEsquemas;


public class FotoEsquema extends Fragment {
    MainActivity parentActivity;
    private OnFotoEsquemaListener mListener;
    ArrayList<FotosEsquemas> fotoesquema;


    public FotoEsquema() {
        // Required empty public constructor
    }


    public static FotoEsquema newInstance() {
        FotoEsquema frag = new FotoEsquema();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();
        fotoesquema = parentActivity.getFotosEsquemas();
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_foto_esquema, container, false);

        final EditText eTextEsquema = (EditText) v.findViewById(R.id.editTextEsq);
        String sFotos;



        if(fotoesquema.size()>=1){

            eTextEsquema.setText(String.valueOf(fotoesquema.get(0).esquemas));
            sFotos = String.valueOf(fotoesquema.get(0).fotos);
        }



        Button btnFormSegFotoEsquema = (Button) v.findViewById(R.id.ButtonSegFotoEsquema);
        btnFormSegFotoEsquema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMenuFragment();
            }
        });
        Button btnAntFotoEsquema = (Button) v.findViewById(R.id.ButtonAntFotoEsquema);
        btnAntFotoEsquema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondIntSemFragment();
            }
        });


        Button buttonFot = (Button) v.findViewById(R.id.buttonFot);
        buttonFot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.dispatchTakePictureIntent();

            }
        });


        Button buttonEFot = (Button) v.findViewById(R.id.buttonEFot);
        buttonEFot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.selectPhoto();
            }
        });


        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMenuFragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondIntSemFragment();
            }

        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToFotoEsquemaFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFotoEsquemaListener) {
            mListener = (OnFotoEsquemaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFotoEsquemaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFotoEsquemaListener {
        // TODO: Update argument type and name
        void goToFotoEsquemaFragment();
    }


}