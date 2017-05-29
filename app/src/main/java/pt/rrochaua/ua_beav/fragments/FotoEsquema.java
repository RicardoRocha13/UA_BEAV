package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


public class FotoEsquema extends Fragment {
    MainActivity parentActivity;
    private OnFotoEsquemaListener mListener;

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
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_foto_esquema, container, false);


        Button btnFormSeg = (Button) v.findViewById(R.id.ButtonSeg);
        btnFormSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMenuFragment();
            }
        });
        Button btnAnt = (Button) v.findViewById(R.id.ButtonAnt);
        btnAnt.setOnClickListener(new View.OnClickListener() {
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