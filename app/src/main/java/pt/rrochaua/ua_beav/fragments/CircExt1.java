package pt.rrochaua.ua_beav.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


public class CircExt1 extends Fragment {

    MainActivity parentActivity;
    private OnCircExt1Listener mListener;


    public CircExt1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CircExt1 newInstance() {
        CircExt1 frag = new CircExt1();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_circ_ext1, container, false);

        Button btnSeg = (Button) v.findViewById(R.id.ButtonSegCircExt1);
        btnSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCircExt2Fragment();
                System.out.println("##################### HELLOOOOO carrega butão ########################");
            }
        });


        Button btnAnt = (Button) v.findViewById(R.id.ButtonAntCircExt1);
        btnAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToForm1Fragment();
            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCircExt1Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCircExt1Listener) {
            mListener = (OnCircExt1Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCircExt1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCircExt1Listener {
        // TODO: Update argument type and name
        void goToCircExt1Fragment();
    }

}
