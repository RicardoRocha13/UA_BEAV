package pt.rrochaua.ua_beav.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Fragment;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;

public class Menu extends Fragment {

    MainActivity parentActivity;
    private OnMenuListener mListener;

    public Menu() {
        // Required empty public constructor
    }


    public static Menu newInstance() {
        Menu frag = new Menu();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu, container, false);

        Button btnForm1 = (Button) v.findViewById(R.id.btnForm1);
        btnForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parentActivity.goToForm1Fragment();

            }
        });

        Button btnMapa = (Button) v.findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMapaFragment();
            }
        });


        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToMenuFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMenuListener) {
            mListener = (OnMenuListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFrag1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMenuListener {
        // TODO: Update argument type and name
        void goToMenuFragment();
    }
}
