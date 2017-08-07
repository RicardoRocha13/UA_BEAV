package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.CircExternas1;


public class CircExt1 extends Fragment {

    MainActivity parentActivity;
    ArrayList<CircExternas1> circexternas1;
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
        circexternas1 = parentActivity.getcExt1();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_circ_ext1, container, false);

        final RadioGroup rGroupTipVia = (RadioGroup) v.findViewById(R.id.radioGroupTVia);
        final EditText eTSemSnVias  = (EditText) v.findViewById(R.id.editTextSemSnVias);
        final RadioGroup rGroupViaT = (RadioGroup) v.findViewById(R.id.radioGroupViaT);
        final RadioGroup rGroupPlant = (RadioGroup) v.findViewById(R.id.radioGroupPlant);
        final RadioGroup rGroupPerf = (RadioGroup) v.findViewById(R.id.radioGroupPerf);
        final RadioGroup rGroupBerm = (RadioGroup) v.findViewById(R.id.radioGroupBerm);
        final RadioGroup rGroupSDA = (RadioGroup) v.findViewById(R.id.radioGroupSDA);
        final RadioGroup rGroupIDV = (RadioGroup) v.findViewById(R.id.radioGroupIDV);
        final RadioGroup rGroupAEODA = (RadioGroup) v.findViewById(R.id.radioGroupAEODA);
        final RadioGroup rGroupFDRC = (RadioGroup) v.findViewById(R.id.radioGroupFDRC);
        final EditText eTLimG  = (EditText) v.findViewById(R.id.editTextLimG);
        final EditText eTLimL  = (EditText) v.findViewById(R.id.editTextLimL);

        final RadioButton rButtonVEsq = (RadioButton) v.findViewById(R.id.radioButtonViaT1);
        final RadioButton rButtonVDir = (RadioButton) v.findViewById(R.id.radioButtonViaT2);


        if(circexternas1.size()>= 1){

            ((RadioButton)rGroupTipVia.getChildAt(circexternas1.get(0).tipoVia)).setChecked(true);
            eTSemSnVias.setText(String.valueOf(circexternas1.get(0).nVias));
            ((RadioButton)rGroupViaT.getChildAt(circexternas1.get(0).viaTransito)).setChecked(true);
            ((RadioButton)rGroupPlant.getChildAt(circexternas1.get(0).tracadoViaPlanta)).setChecked(true);
            ((RadioButton)rGroupPerf.getChildAt(circexternas1.get(0).tracadoViaPerfil)).setChecked(true);
            ((RadioButton)rGroupBerm.getChildAt(circexternas1.get(0).tracadoViaBerma)).setChecked(true);
            ((RadioButton)rGroupSDA.getChildAt(circexternas1.get(0).situacaoAcidente)).setChecked(true);
            ((RadioButton)rGroupIDV.getChildAt(circexternas1.get(0).intersecVias)).setChecked(true);
            ((RadioButton)rGroupAEODA.getChildAt(circexternas1.get(0).acidenteObrasArte)).setChecked(true);
            ((RadioButton)rGroupFDRC.getChildAt(circexternas1.get(0).faixaRodagem)).setChecked(true);
            eTLimG.setText(String.valueOf(circexternas1.get(0).limiteVelocGeral));
            eTLimL.setText(String.valueOf(circexternas1.get(0).limiteVelocLocal));


        }



/*
        eTSemSnVias.addTextChangedListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId >= 2){
                    rButtonVEsq.setVisibility(View.VISIBLE);
                    rButtonVDir.setVisibility(View.VISIBLE);
                } else{
                    rButtonVEsq.setVisibility(View.GONE);
                    rButtonVDir.setVisibility(View.GONE);
                }
            }
        });
*/

        Button btnSegCircExt1 = (Button) v.findViewById(R.id.ButtonSegCircExt1);
        btnSegCircExt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupTipVia.getCheckedRadioButtonId() == -1 || eTSemSnVias.getText().toString().equals("")
                        || rGroupViaT.getCheckedRadioButtonId() == -1 || rGroupPlant.getCheckedRadioButtonId() == -1
                        || rGroupPerf.getCheckedRadioButtonId() == -1 || rGroupBerm.getCheckedRadioButtonId() == -1
                        || rGroupSDA.getCheckedRadioButtonId() == -1 || rGroupIDV.getCheckedRadioButtonId() == -1
                        || rGroupAEODA.getCheckedRadioButtonId() == -1 || rGroupFDRC.getCheckedRadioButtonId() == -1
                        || eTLimG.getText().toString().equals("") || eTLimL.getText().toString().equals("") ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexTipVia = rGroupTipVia.indexOfChild(rGroupTipVia.findViewById(rGroupTipVia.getCheckedRadioButtonId()));
                    int indexGroupViaT = rGroupViaT.indexOfChild(rGroupViaT.findViewById(rGroupViaT.getCheckedRadioButtonId()));
                    int indexGroupPlant = rGroupPlant.indexOfChild(rGroupPlant.findViewById(rGroupPlant.getCheckedRadioButtonId()));
                    int indexGroupPerf = rGroupPerf.indexOfChild(rGroupPerf.findViewById(rGroupPerf.getCheckedRadioButtonId()));
                    int indexGroupBerm = rGroupBerm.indexOfChild(rGroupBerm.findViewById(rGroupBerm.getCheckedRadioButtonId()));
                    int indexGroupSDA = rGroupSDA.indexOfChild(rGroupSDA.findViewById(rGroupSDA.getCheckedRadioButtonId()));
                    int indexGroupIDV = rGroupIDV.indexOfChild(rGroupIDV.findViewById(rGroupIDV.getCheckedRadioButtonId()));
                    int indexGroupAEODA = rGroupAEODA.indexOfChild(rGroupAEODA.findViewById(rGroupAEODA.getCheckedRadioButtonId()));
                    int indexGroupFDRC = rGroupFDRC.indexOfChild(rGroupFDRC.findViewById(rGroupFDRC.getCheckedRadioButtonId()));


                    CircExternas1 CE1 = new CircExternas1(indexTipVia, Integer.parseInt(eTSemSnVias.getText().toString()), indexGroupViaT,
                            indexGroupPlant, indexGroupPerf, indexGroupBerm, indexGroupSDA, indexGroupIDV, indexGroupAEODA,
                            indexGroupFDRC, Integer.parseInt(eTLimG.getText().toString()), Integer.parseInt(eTLimL.getText().toString()));

                    circexternas1.add(0,CE1);
                    parentActivity.setcExt1(circexternas1);
                    parentActivity.goToCircExt2Fragment();
                }




            }
        });


        Button btnAntCircExt1 = (Button) v.findViewById(R.id.ButtonAntCircExt1);
        btnAntCircExt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupTipVia.getCheckedRadioButtonId() == -1 || eTSemSnVias.getText().toString().equals("")
                        || rGroupViaT.getCheckedRadioButtonId() == -1 || rGroupPlant.getCheckedRadioButtonId() == -1
                        || rGroupPerf.getCheckedRadioButtonId() == -1 || rGroupBerm.getCheckedRadioButtonId() == -1
                        || rGroupSDA.getCheckedRadioButtonId() == -1 || rGroupIDV.getCheckedRadioButtonId() == -1
                        || rGroupAEODA.getCheckedRadioButtonId() == -1 || rGroupFDRC.getCheckedRadioButtonId() == -1
                        || eTLimG.getText().toString().equals("") || eTLimL.getText().toString().equals("") ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexTipVia = rGroupTipVia.indexOfChild(rGroupTipVia.findViewById(rGroupTipVia.getCheckedRadioButtonId()));
                    int indexGroupViaT = rGroupViaT.indexOfChild(rGroupViaT.findViewById(rGroupViaT.getCheckedRadioButtonId()));
                    int indexGroupPlant = rGroupPlant.indexOfChild(rGroupPlant.findViewById(rGroupPlant.getCheckedRadioButtonId()));
                    int indexGroupPerf = rGroupPerf.indexOfChild(rGroupPerf.findViewById(rGroupPerf.getCheckedRadioButtonId()));
                    int indexGroupBerm = rGroupBerm.indexOfChild(rGroupBerm.findViewById(rGroupBerm.getCheckedRadioButtonId()));
                    int indexGroupSDA = rGroupSDA.indexOfChild(rGroupSDA.findViewById(rGroupSDA.getCheckedRadioButtonId()));
                    int indexGroupIDV = rGroupIDV.indexOfChild(rGroupIDV.findViewById(rGroupIDV.getCheckedRadioButtonId()));
                    int indexGroupAEODA = rGroupAEODA.indexOfChild(rGroupAEODA.findViewById(rGroupAEODA.getCheckedRadioButtonId()));
                    int indexGroupFDRC = rGroupFDRC.indexOfChild(rGroupFDRC.findViewById(rGroupFDRC.getCheckedRadioButtonId()));


                    CircExternas1 CE1 = new CircExternas1(indexTipVia, Integer.parseInt(eTSemSnVias.getText().toString()), indexGroupViaT,
                            indexGroupPlant, indexGroupPerf, indexGroupBerm, indexGroupSDA, indexGroupIDV, indexGroupAEODA,
                            indexGroupFDRC, Integer.parseInt(eTLimG.getText().toString()), Integer.parseInt(eTLimL.getText().toString()));

                    circexternas1.add(0,CE1);
                    parentActivity.setcExt1(circexternas1);
                    parentActivity.goToForm1Fragment();
                }

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
