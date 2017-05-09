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


//tens de por o seguinte frag


public class Form2 extends Fragment {

    MainActivity parentActivity;
    private OnForm2Listener mListener;

    public Form2() {
        // Required empty public constructor
    }


    public static Form2 newInstance() {
        Form2 frag = new Form2();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_form2, container, false);

        Button btnForm3 = (Button) v.findViewById(R.id.btnForm3);
        btnForm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMenuFragment();
            }
        });





        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToForm2Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnForm2Listener) {
            mListener = (OnForm2Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnForm2Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnForm2Listener {
        // TODO: Update argument type and name
        void goToForm2Fragment();
    }


}