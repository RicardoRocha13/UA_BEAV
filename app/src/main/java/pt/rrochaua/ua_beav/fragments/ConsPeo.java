package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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


        final RadioGroup rGroupSexPe = (RadioGroup) v.findViewById(R.id.radioGroupSexPe);
        final EditText eTextIdade = (EditText) v.findViewById(R.id.eTIdade);
        final RadioGroup rGroupPos = (RadioGroup) v.findViewById(R.id.radioGroupPos);
        final RadioGroup rGroupCPF = (RadioGroup) v.findViewById(R.id.radioGroupCPF);
        final EditText eTextTaxa = (EditText) v.findViewById(R.id.eTTaxa);
        final RadioGroup rGroupAco = (RadioGroup) v.findViewById(R.id.radioGroupAco);
        final RadioGroup rGroupUDMR = (RadioGroup) v.findViewById(R.id.radioGroupUDMR);
        final RadioGroup rGroupGDGDL = (RadioGroup) v.findViewById(R.id.radioGroupGDGDL);


        if(conseqpeoes.size()>=1){
            /*
            if (conseqpeoes.get(0).condiPsicoFisicas==3){


            ConseqPeoesAlcool cpa = ((ConseqPeoesAlcool) conseqpeoes.get(0));

            eTextTaxa.setText(String.valueOf(conseqpeoes.get(0).taxaAlcolemia));

            }
            */
            ((RadioButton)rGroupSexPe.getChildAt(conseqpeoes.get(0).genero)).setChecked(true);
            ((RadioButton)rGroupPos.getChildAt(conseqpeoes.get(0).posicao)).setChecked(true);
            ((RadioButton)rGroupAco.getChildAt(conseqpeoes.get(0).acoes)).setChecked(true);
            ((RadioButton)rGroupUDMR.getChildAt(conseqpeoes.get(0).utilizacaoMaterialRefletor)).setChecked(true);
            ((RadioButton)rGroupGDGDL.getChildAt(conseqpeoes.get(0).grauGravidadeLesoes)).setChecked(true);
            //((RadioButton)rGroupCPF.getChildAt(conseqpeoes.get(0).condiPsicoFisicas)).setChecked(true);
            eTextIdade.setText(String.valueOf(conseqpeoes.get(0).idade));
        }





        Button btnSegConsPeo = (Button) v.findViewById(R.id.ButtonSegConsPeo);
        btnSegConsPeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eTextIdade .getText().toString().equals("") || rGroupSexPe.getCheckedRadioButtonId() == -1 ||
                        rGroupPos.getCheckedRadioButtonId() == -1 || rGroupCPF.getCheckedRadioButtonId() == -1 ||
                        rGroupAco.getCheckedRadioButtonId() == -1 || rGroupUDMR.getCheckedRadioButtonId() == -1 ||
                        rGroupGDGDL.getCheckedRadioButtonId() == -1  ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    /* Para quando é selecionado a hiipotese de taxa alcoolemia
                    if () {

                    }
                    */

                    int indexSexPe = rGroupSexPe.indexOfChild(rGroupSexPe.findViewById(rGroupSexPe.getCheckedRadioButtonId()));
                    int indexPos = rGroupPos.indexOfChild(rGroupPos.findViewById(rGroupPos.getCheckedRadioButtonId()));
                    int indexCPF = rGroupCPF.indexOfChild(rGroupCPF.findViewById(rGroupCPF.getCheckedRadioButtonId()));
                    int indexAco = rGroupAco.indexOfChild(rGroupAco.findViewById(rGroupAco.getCheckedRadioButtonId()));
                    int indexUDMR = rGroupUDMR.indexOfChild(rGroupUDMR.findViewById(rGroupUDMR.getCheckedRadioButtonId()));
                    int indexGDGDL = rGroupGDGDL.indexOfChild(rGroupGDGDL.findViewById(rGroupGDGDL.getCheckedRadioButtonId()));

/* Erro na idade e mais uma vez com os checkboxes
                    ConseqPeoes CP = new ConseqPeoes(indexSexPe, eTextIdade.getText().toString(),
                            indexPos, indexAco, indexUDMR, indexGDGDL,  indexCPF);
*/

                    parentActivity.goToFotoEsquemaFragment();
                }



            }
        });


        Button btnAntConsPeo = (Button) v.findViewById(R.id.ButtonAntConsPeo);
        btnAntConsPeo.setOnClickListener(new View.OnClickListener() {
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
