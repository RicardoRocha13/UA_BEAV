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
import pt.rrochaua.ua_beav.models.CircExternas2;


public class CircExt2 extends Fragment {

    MainActivity parentActivity;
    ArrayList<CircExternas2> circexternas2;
    private OnCircExt2Listener mListener;


    public CircExt2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CircExt2 newInstance() {
        CircExt2 fragment = new CircExt2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        super.onCreate(savedInstanceState);
        circexternas2 = parentActivity.getcExt2();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_circ_ext2, container, false);

        final RadioGroup rGPis = (RadioGroup) v.findViewById(R.id.radioGroupPis);
        final RadioGroup rGEst = (RadioGroup) v.findViewById(R.id.radioGroupEst);
        final RadioGroup rGObs = (RadioGroup) v.findViewById(R.id.radioGroupObs);
        final RadioGroup rGCondAd = (RadioGroup) v.findViewById(R.id.radioGroupCondAd);
        final RadioGroup rGMarPav = (RadioGroup) v.findViewById(R.id.radioGroupMarPav);
        final RadioGroup rGSinLum = (RadioGroup) v.findViewById(R.id.radioGroupSinLum);
        final RadioGroup rGSin = (RadioGroup) v.findViewById(R.id.radioGroupSin);
        final RadioGroup rGLum = (RadioGroup) v.findViewById(R.id.radioGroupLum);
        final RadioGroup rGFatAt = (RadioGroup) v.findViewById(R.id.radioGroupFatAt);




        if(circexternas2.size()>= 1){
            ((RadioButton)rGPis.getChildAt(circexternas2.get(0).tipoPiso)).setChecked(true);
            ((RadioButton)rGEst.getChildAt(circexternas2.get(0).estadoConservacao)).setChecked(true);
            ((RadioButton)rGObs.getChildAt(circexternas2.get(0).obstaculosObras)).setChecked(true);
            ((RadioButton)rGCondAd.getChildAt(circexternas2.get(0).condicoesAderencia)).setChecked(true);
            ((RadioButton)rGMarPav.getChildAt(circexternas2.get(0).marcasPavimentos)).setChecked(true);
            ((RadioButton)rGSinLum.getChildAt(circexternas2.get(0).sinalizacaoLuminosa)).setChecked(true);
            ((RadioButton)rGSin.getChildAt(circexternas2.get(0).sinais)).setChecked(true);
            ((RadioButton)rGLum.getChildAt(circexternas2.get(0).luminosidade)).setChecked(true);
            ((RadioButton)rGFatAt.getChildAt(circexternas2.get(0).fatoresAtmosfericos)).setChecked(true);

        }


        Button btnSegCircExt2 = (Button) v.findViewById(R.id.ButtonSegCircExt2);
        btnSegCircExt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rGPis.getCheckedRadioButtonId() == -1 || rGEst.getCheckedRadioButtonId() == -1 ||
                        rGObs.getCheckedRadioButtonId() == -1 || rGCondAd.getCheckedRadioButtonId() == -1 ||
                        rGMarPav.getCheckedRadioButtonId() == -1 || rGSinLum.getCheckedRadioButtonId() == -1 ||
                        rGSin.getCheckedRadioButtonId() == -1 || rGLum.getCheckedRadioButtonId() == -1 ||
                        rGFatAt.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                }else{
                    int indexPis = rGPis.indexOfChild(rGPis.findViewById(rGPis.getCheckedRadioButtonId()));
                    int indexEst = rGEst.indexOfChild(rGEst.findViewById(rGEst.getCheckedRadioButtonId()));
                    int indexObs = rGPis.indexOfChild(rGPis.findViewById(rGPis.getCheckedRadioButtonId()));
                    int indexConAd = rGCondAd.indexOfChild(rGCondAd.findViewById(rGCondAd.getCheckedRadioButtonId()));
                    int indexMarPav = rGMarPav.indexOfChild(rGMarPav.findViewById(rGMarPav.getCheckedRadioButtonId()));
                    int indexSinLum = rGSinLum.indexOfChild(rGSinLum.findViewById(rGSinLum.getCheckedRadioButtonId()));
                    int indexSin = rGSin.indexOfChild(rGSin.findViewById(rGSin.getCheckedRadioButtonId()));
                    int indexLum = rGLum.indexOfChild(rGLum.findViewById(rGLum.getCheckedRadioButtonId()));
                    int indexFatAt = rGFatAt.indexOfChild(rGFatAt.findViewById(rGFatAt.getCheckedRadioButtonId()));

                    CircExternas2 CE2 = new CircExternas2(indexPis, indexEst, indexObs, indexConAd,
                            indexMarPav, indexSinLum, indexSin, indexLum, indexFatAt);

                    circexternas2.add(0,CE2);
                    parentActivity.setcExt2(circexternas2);
                    parentActivity.goToNatAciFragment();

                }
            }
        });


        Button btnAntCircExt2 = (Button) v.findViewById(R.id.ButtonAntCircExt2);
        btnAntCircExt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rGPis.getCheckedRadioButtonId() == -1 || rGEst.getCheckedRadioButtonId() == -1 ||
                        rGObs.getCheckedRadioButtonId() == -1 || rGCondAd.getCheckedRadioButtonId() == -1 ||
                        rGMarPav.getCheckedRadioButtonId() == -1 || rGSinLum.getCheckedRadioButtonId() == -1 ||
                        rGSin.getCheckedRadioButtonId() == -1 || rGLum.getCheckedRadioButtonId() == -1 ||
                        rGFatAt.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                }else{
                    int indexPis = rGPis.indexOfChild(rGPis.findViewById(rGPis.getCheckedRadioButtonId()));
                    int indexEst = rGEst.indexOfChild(rGEst.findViewById(rGEst.getCheckedRadioButtonId()));
                    int indexObs = rGPis.indexOfChild(rGPis.findViewById(rGPis.getCheckedRadioButtonId()));
                    int indexConAd = rGCondAd.indexOfChild(rGCondAd.findViewById(rGCondAd.getCheckedRadioButtonId()));
                    int indexMarPav = rGMarPav.indexOfChild(rGMarPav.findViewById(rGMarPav.getCheckedRadioButtonId()));
                    int indexSinLum = rGSinLum.indexOfChild(rGSinLum.findViewById(rGSinLum.getCheckedRadioButtonId()));
                    int indexSin = rGSin.indexOfChild(rGSin.findViewById(rGSin.getCheckedRadioButtonId()));
                    int indexLum = rGLum.indexOfChild(rGLum.findViewById(rGLum.getCheckedRadioButtonId()));
                    int indexFatAt = rGFatAt.indexOfChild(rGFatAt.findViewById(rGFatAt.getCheckedRadioButtonId()));

                    CircExternas2 CE2 = new CircExternas2(indexPis, indexEst, indexObs, indexConAd,
                            indexMarPav, indexSinLum, indexSin, indexLum, indexFatAt);

                    circexternas2.add(0,CE2);
                    parentActivity.setcExt2(circexternas2);
                    parentActivity.goToCircExt1Fragment();

                }
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCircExt2Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCircExt2Listener) {
            mListener = (OnCircExt2Listener) context;
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
    public interface OnCircExt2Listener {
        // TODO: Update argument type and name
        void goToCircExt2Fragment();

    }
}
