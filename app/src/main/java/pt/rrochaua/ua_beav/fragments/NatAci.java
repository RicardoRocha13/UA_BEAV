package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.NatuAcidente;


public class NatAci extends Fragment {
    MainActivity parentActivity;
    ArrayList<NatuAcidente> nataci;
    private OnNatAciListener mListener;



    public NatAci() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static NatAci newInstance() {
        NatAci fragment = new NatAci();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        super.onCreate(savedInstanceState);
        nataci = parentActivity.getNatuAcidente();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_nat_aci, container, false);

        final RadioGroup rGroupDesp = (RadioGroup) v.findViewById(R.id.radioGroupDesp);
        final RadioGroup rGroupCol = (RadioGroup) v.findViewById(R.id.radioGroupCol);
        final RadioGroup rGroupAtro = (RadioGroup) v.findViewById(R.id.radioGroupAtro);



        if(nataci.size()>= 1){


        }

        Button btnSegNatAci = (Button) v.findViewById(R.id.ButtonSegNatAci);
        btnSegNatAci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToVeicInt1Fragment();
            }
        });


        Button btnAntNatAci = (Button) v.findViewById(R.id.ButtonAntNatAci);
        btnAntNatAci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCircExt2Fragment();
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToNatAciFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNatAciListener) {
            mListener = (OnNatAciListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnNatAciListener {
        // TODO: Update argument type and name
        void goToNatAciFragment();
    }
}
