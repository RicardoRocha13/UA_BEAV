package pt.rrochaua.ua_beav.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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
import pt.rrochaua.ua_beav.models.CondInterveniente2;


public class CondInt2 extends Fragment {
    MainActivity parentActivity;
    ArrayList<CondInterveniente2> condint2;
    private OnCondInt2Listener mListener;


    public CondInt2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CondInt2 newInstance() {
        CondInt2 fragment = new CondInt2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        super.onCreate(savedInstanceState);
        condint2 = parentActivity.getcInterv2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cond_int2, container, false);

        final RadioGroup rGGAMAA = (RadioGroup) v.findViewById(R.id.radioGroupAMAA);
        final RadioGroup rGICAM = (RadioGroup) v.findViewById(R.id.radioGroupICAM);
        final RadioGroup rGAS = (RadioGroup) v.findViewById(R.id.radioGroupAS);
        final RadioGroup rGGDGDL = (RadioGroup) v.findViewById(R.id.radioGroupGDGDL);

        if (condint2.size() >= 1){
            ((RadioButton)rGGAMAA.getChildAt(condint2.get(0).accaoManobras)).setChecked(true);
            ((RadioButton)rGICAM.getChildAt(condint2.get(0).infoComplementar)).setChecked(true);
            ((RadioButton)rGAS.getChildAt(condint2.get(0).acessoriosSeguranca)).setChecked(true);
            ((RadioButton)rGGDGDL.getChildAt(condint2.get(0).gravidadeLesoes)).setChecked(true);
        }


        Button btnSegCondInt2 = (Button) v.findViewById(R.id.ButtonSegCondInt2);
        btnSegCondInt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGGAMAA.getCheckedRadioButtonId() == -1 || rGICAM.getCheckedRadioButtonId() == -1 ||
                        rGAS.getCheckedRadioButtonId() == -1 || rGGDGDL.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    int indexGAMAA = rGGAMAA.indexOfChild(rGGAMAA.findViewById(rGGAMAA.getCheckedRadioButtonId()));
                    int indexICAM = rGICAM.indexOfChild(rGICAM.findViewById(rGICAM.getCheckedRadioButtonId()));
                    int indexAS = rGAS.indexOfChild(rGAS.findViewById(rGAS.getCheckedRadioButtonId()));
                    int indexGDGDL = rGGDGDL.indexOfChild(rGGDGDL.findViewById(rGGDGDL.getCheckedRadioButtonId()));

                    //A modificar posteriormente
                    int indexIDV = 0;

                    CondInterveniente2 ci2 = new CondInterveniente2(indexIDV, indexGAMAA, indexICAM,
                            indexAS, indexGDGDL);


                    condint2.add(0, ci2);
                    parentActivity.setcInterv2(condint2);


                    parentActivity.goToConsPassFragment();

                }

            }
        });


        Button btnAntCondInt2 = (Button) v.findViewById(R.id.ButtonAntCondInt2);
        btnAntCondInt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGGAMAA.getCheckedRadioButtonId() == -1 || rGICAM.getCheckedRadioButtonId() == -1 ||
                        rGAS.getCheckedRadioButtonId() == -1 || rGGDGDL.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    int indexGAMAA = rGGAMAA.indexOfChild(rGGAMAA.findViewById(rGGAMAA.getCheckedRadioButtonId()));
                    int indexICAM = rGICAM.indexOfChild(rGICAM.findViewById(rGICAM.getCheckedRadioButtonId()));
                    int indexAS = rGAS.indexOfChild(rGAS.findViewById(rGAS.getCheckedRadioButtonId()));
                    int indexGDGDL = rGGDGDL.indexOfChild(rGGDGDL.findViewById(rGGDGDL.getCheckedRadioButtonId()));

                    //A modificar posteriormente
                    int indexIDV = 0;

                    CondInterveniente2 ci2 = new CondInterveniente2(indexIDV, indexGAMAA, indexICAM,
                            indexAS, indexGDGDL);


                    condint2.add(0, ci2);

                    parentActivity.goToCondInt1Fragment();
                }
            }
        });


        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToConsPassFragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt1Fragment();
            }

        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCondInt2Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCondInt2Listener) {
            mListener = (OnCondInt2Listener) context;
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
    public interface OnCondInt2Listener {
        // TODO: Update argument type and name
        void goToCondInt2Fragment();
    }
}
