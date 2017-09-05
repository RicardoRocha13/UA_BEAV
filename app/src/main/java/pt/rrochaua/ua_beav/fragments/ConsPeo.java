package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.ConseqPeoes;


public class ConsPeo extends Fragment {
    MainActivity parentActivity;
    ArrayList<ConseqPeoes> conseqpeoes;
    private OnConsPeoListener mListener;



    public ConsPeo() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ConsPeo newInstance() {
        ConsPeo fragment = new ConsPeo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        conseqpeoes = parentActivity.getConsPeoes();
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cons_peo, container, false);

        final ArrayList<Integer> checkCPF = new ArrayList<Integer>();

        final RadioGroup rGroupSexPe = (RadioGroup) v.findViewById(R.id.radioGroupSexPe);
        final EditText eTextIdade = (EditText) v.findViewById(R.id.eTIdade);
        final RadioGroup rGroupPos = (RadioGroup) v.findViewById(R.id.radioGroupPos);
        //final RadioGroup rGroupCPF = (RadioGroup) v.findViewById(R.id.radioGroupCPF);
        final CheckBox cbCPF1 = (CheckBox) v.findViewById(R.id.radioButtonCPF1);
        final CheckBox cbCPF2 = (CheckBox) v.findViewById(R.id.radioButtonCPF2);
        final CheckBox cbCPF3 = (CheckBox) v.findViewById(R.id.radioButtonCPF3);
        final CheckBox cbCPF4 = (CheckBox) v.findViewById(R.id.radioButtonCPF4);
        final EditText eTextTaxa = (EditText) v.findViewById(R.id.eTTaxa);
        final RadioGroup rGroupAco = (RadioGroup) v.findViewById(R.id.radioGroupAco);
        final RadioGroup rGroupUDMR = (RadioGroup) v.findViewById(R.id.radioGroupUDMR);
        final RadioGroup rGroupGDGDL = (RadioGroup) v.findViewById(R.id.radioGroupGDGDL);


        ArrayList<Integer> checkWriteCPF = new ArrayList<Integer>();

        if(conseqpeoes.size()>=1){

            ((RadioButton)rGroupSexPe.getChildAt(conseqpeoes.get(0).genero)).setChecked(true);
            ((RadioButton)rGroupPos.getChildAt(conseqpeoes.get(0).posicao)).setChecked(true);
            ((RadioButton)rGroupAco.getChildAt(conseqpeoes.get(0).acoes)).setChecked(true);
            ((RadioButton)rGroupUDMR.getChildAt(conseqpeoes.get(0).utilizacaoMaterialRefletor)).setChecked(true);
            ((RadioButton)rGroupGDGDL.getChildAt(conseqpeoes.get(0).grauGravidadeLesoes)).setChecked(true);
            eTextIdade.setText(String.valueOf(conseqpeoes.get(0).idade));

            checkWriteCPF = conseqpeoes.get(0).condiPsicoFisicas;

            for (int k=0; k<checkWriteCPF.size(); k++) {
                if (checkWriteCPF.get(k) == 1) {

                    cbCPF1.setChecked(true);

                    eTextTaxa.setText(String.valueOf(conseqpeoes.get(0).taxaAlcolemia));

                }
                if (checkWriteCPF.get(k) == 2) {

                    cbCPF2.setChecked(true);

                    eTextTaxa.setText(String.valueOf(conseqpeoes.get(0).taxaAlcolemia));

                }
                if (checkWriteCPF.get(k) == 3) {

                    cbCPF3.setChecked(true);

                    eTextTaxa.setText(String.valueOf(conseqpeoes.get(0).taxaAlcolemia));

                }
                if (checkWriteCPF.get(k) == 4) {

                    cbCPF4.setChecked(true);

                    eTextTaxa.setText(String.valueOf(conseqpeoes.get(0).taxaAlcolemia));

                }

            }

        }





        Button btnSegConsPeo = (Button) v.findViewById(R.id.ButtonSegConsPeo);
        btnSegConsPeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eTextIdade .getText().toString().equals("") || rGroupSexPe.getCheckedRadioButtonId() == -1 ||
                        rGroupPos.getCheckedRadioButtonId() == -1 || rGroupAco.getCheckedRadioButtonId() == -1 ||
                        rGroupUDMR.getCheckedRadioButtonId() == -1 || rGroupGDGDL.getCheckedRadioButtonId() == -1 ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    if(cbCPF1.isChecked()){
                        checkCPF.add(1);
                    }
                    if(cbCPF2.isChecked()){
                        checkCPF.add(2);
                    }
                    if(cbCPF3.isChecked()){
                        checkCPF.add(3);
                    }
                    if(cbCPF4.isChecked()){
                        checkCPF.add(4);
                    }

                    int indexSexPe = rGroupSexPe.indexOfChild(rGroupSexPe.findViewById(rGroupSexPe.getCheckedRadioButtonId()));
                    int indexPos = rGroupPos.indexOfChild(rGroupPos.findViewById(rGroupPos.getCheckedRadioButtonId()));
                    int indexAco = rGroupAco.indexOfChild(rGroupAco.findViewById(rGroupAco.getCheckedRadioButtonId()));
                    int indexUDMR = rGroupUDMR.indexOfChild(rGroupUDMR.findViewById(rGroupUDMR.getCheckedRadioButtonId()));
                    int indexGDGDL = rGroupGDGDL.indexOfChild(rGroupGDGDL.findViewById(rGroupGDGDL.getCheckedRadioButtonId()));


                    ConseqPeoes CP = new ConseqPeoes(indexSexPe,Integer.parseInt(eTextIdade.getText().toString()),
                            indexPos, indexAco, indexUDMR, indexGDGDL, checkCPF, Float.parseFloat(eTextTaxa.getText().toString()));


                    conseqpeoes.add(0,CP);
                    parentActivity.setConsPeoes(conseqpeoes);


                    parentActivity.goToFotoEsquemaFragment();
                }


            }
        });


        Button btnAntConsPeo = (Button) v.findViewById(R.id.ButtonAntConsPeo);
        btnAntConsPeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eTextIdade .getText().toString().equals("") || rGroupSexPe.getCheckedRadioButtonId() == -1 ||
                        rGroupPos.getCheckedRadioButtonId() == -1 || rGroupAco.getCheckedRadioButtonId() == -1 ||
                        rGroupUDMR.getCheckedRadioButtonId() == -1 || rGroupGDGDL.getCheckedRadioButtonId() == -1  ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    if(cbCPF1.isChecked()){
                        checkCPF.add(1);
                    }
                    if(cbCPF2.isChecked()){
                        checkCPF.add(2);
                    }
                    if(cbCPF3.isChecked()){
                        checkCPF.add(3);
                    }
                    if(cbCPF4.isChecked()){
                        checkCPF.add(4);
                    }

                    int indexSexPe = rGroupSexPe.indexOfChild(rGroupSexPe.findViewById(rGroupSexPe.getCheckedRadioButtonId()));
                    int indexPos = rGroupPos.indexOfChild(rGroupPos.findViewById(rGroupPos.getCheckedRadioButtonId()));
                    int indexAco = rGroupAco.indexOfChild(rGroupAco.findViewById(rGroupAco.getCheckedRadioButtonId()));
                    int indexUDMR = rGroupUDMR.indexOfChild(rGroupUDMR.findViewById(rGroupUDMR.getCheckedRadioButtonId()));
                    int indexGDGDL = rGroupGDGDL.indexOfChild(rGroupGDGDL.findViewById(rGroupGDGDL.getCheckedRadioButtonId()));


                    ConseqPeoes CP = new ConseqPeoes(indexSexPe,Integer.parseInt(eTextIdade.getText().toString()),
                            indexPos, indexAco, indexUDMR, indexGDGDL, checkCPF, Float.parseFloat(eTextTaxa.getText().toString()));


                    conseqpeoes.add(0,CP);
                    parentActivity.setConsPeoes(conseqpeoes);


                    parentActivity.goToConsPassFragment();
                }

            }
        });



        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToFotoEsquemaFragment();
            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToConsPassFragment();
            }

        });



        return v;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToConsPeoFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConsPeoListener) {
            mListener = (OnConsPeoListener) context;
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
    public interface OnConsPeoListener {
        // TODO: Update argument type and name
        void goToConsPeoFragment();
    }
}
