package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.Form1Model;
import pt.rrochaua.ua_beav.models.NatuAcidente;


public class NatAci extends Fragment {
    MainActivity parentActivity;
    ArrayList<NatuAcidente> nataci;
    ArrayList<Form1Model> form1;
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
        form1 = parentActivity.getForm1();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_nat_aci, container, false);

        final RadioGroup rGroupDesp = (RadioGroup) v.findViewById(R.id.radioGroupDesp);
        final RadioGroup rGroupCol = (RadioGroup) v.findViewById(R.id.radioGroupCol);
        final RadioGroup rGroupAtro = (RadioGroup) v.findViewById(R.id.radioGroupAtro);

        final int acidente = form1.get(0).naturezaAcidente;

        if(nataci.size()>= 1){
            ((RadioButton)rGroupDesp.getChildAt(nataci.get(0).despiste)).setChecked(true);
            ((RadioButton)rGroupCol.getChildAt(nataci.get(0).colisao)).setChecked(true);
            ((RadioButton)rGroupAtro.getChildAt(nataci.get(0).atropelamento)).setChecked(true);
        }


        if(acidente == 0){
            rGroupDesp.setVisibility(View.VISIBLE);
            rGroupCol.setVisibility(View.GONE);
            rGroupAtro.setVisibility(View.GONE);
        }
        if(acidente == 1){
            rGroupDesp.setVisibility(View.GONE);
            rGroupCol.setVisibility(View.VISIBLE);
            rGroupAtro.setVisibility(View.GONE);
        }
        if(acidente == 2){
            rGroupDesp.setVisibility(View.GONE);
            rGroupCol.setVisibility(View.GONE);
            rGroupAtro.setVisibility(View.VISIBLE);
        }


        Button btnSegNatAci = (Button) v.findViewById(R.id.ButtonSegNatAci);
        btnSegNatAci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupDesp.getCheckedRadioButtonId() == -1 & rGroupCol.getCheckedRadioButtonId() == -1 &
                        rGroupAtro.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexDesp = rGroupDesp.indexOfChild(rGroupDesp.findViewById(rGroupDesp.getCheckedRadioButtonId()));
                    int indexCol = rGroupCol.indexOfChild(rGroupCol.findViewById(rGroupCol.getCheckedRadioButtonId()));
                    int indexAtro = rGroupAtro.indexOfChild(rGroupAtro.findViewById(rGroupAtro.getCheckedRadioButtonId()));

                    NatuAcidente NA = new NatuAcidente(indexDesp, indexCol, indexAtro);

                    nataci.add(0,NA);
                    parentActivity.setNatuAcidente(nataci);
                    parentActivity.goToVeicInt1Fragment();
                }

            }

        });


        Button btnAntNatAci = (Button) v.findViewById(R.id.ButtonAntNatAci);
        btnAntNatAci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupDesp.getCheckedRadioButtonId() == -1 || rGroupCol.getCheckedRadioButtonId() == -1 ||
                        rGroupAtro.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexDesp = rGroupDesp.indexOfChild(rGroupDesp.findViewById(rGroupDesp.getCheckedRadioButtonId()));
                    int indexCol = rGroupCol.indexOfChild(rGroupCol.findViewById(rGroupCol.getCheckedRadioButtonId()));
                    int indexAtro = rGroupAtro.indexOfChild(rGroupAtro.findViewById(rGroupAtro.getCheckedRadioButtonId()));

                    NatuAcidente NA = new NatuAcidente(indexDesp, indexCol, indexAtro);

                    nataci.add(0,NA);
                    parentActivity.setNatuAcidente(nataci);
                    parentActivity.goToCircExt2Fragment();
                }

            }

        });




        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToVeicInt1Fragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
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
