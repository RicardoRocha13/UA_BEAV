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
import pt.rrochaua.ua_beav.models.ConseqPassageiros;


public class ConsPass extends Fragment {

    MainActivity parentActivity;
    ArrayList<ConseqPassageiros> conspass;
    private OnConsPassListener mListener;


    public ConsPass() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ConsPass newInstance() {
        ConsPass fragment = new ConsPass();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        conspass = parentActivity.getConsPassageiros();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cons_pass, container, false);

        final RadioGroup rGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSexPa);
        final EditText eTextIdade = (EditText) v.findViewById(R.id.eTIdade);
        final RadioGroup rGroupPNV = (RadioGroup) v.findViewById(R.id.radioGroupPNV);
        final RadioGroup rGroupUDADS = (RadioGroup) v.findViewById(R.id.radioGroupUDADS);
        final RadioGroup rGroupGDGDL = (RadioGroup) v.findViewById(R.id.radioGroupGDGDL);

        if(conspass.size()>= 1){

            ((RadioButton)rGroupSex.getChildAt(conspass.get(0).genero)).setChecked(true);
            eTextIdade.setText(String.valueOf(conspass.get(0).idade));
            ((RadioButton)rGroupPNV.getChildAt(conspass.get(0).posicaoVeiculo)).setChecked(true);
            ((RadioButton)rGroupUDADS.getChildAt(conspass.get(0).usoAcessorioSeguranca)).setChecked(true);
            ((RadioButton)rGroupGDGDL.getChildAt(conspass.get(0).grauGraviadeLesoes)).setChecked(true);

        }


        Button btnSegConsPass = (Button) v.findViewById(R.id.ButtonSegConsPass);
        btnSegConsPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eTextIdade.getText().toString().equals("") ||rGroupSex.getCheckedRadioButtonId() == -1
                        ||rGroupPNV.getCheckedRadioButtonId() == -1 || rGroupUDADS.getCheckedRadioButtonId() == -1
                        ||rGroupGDGDL.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));
                    int indexPNV = rGroupPNV.indexOfChild(rGroupPNV.findViewById(rGroupPNV.getCheckedRadioButtonId()));
                    int indexUDAS = rGroupUDADS.indexOfChild(rGroupUDADS.findViewById(rGroupUDADS.getCheckedRadioButtonId()));
                    int indexGDGDL = rGroupGDGDL.indexOfChild(rGroupGDGDL.findViewById(rGroupGDGDL.getCheckedRadioButtonId()));

                    //Para modificar no futuro
                    int indexIDV = 0;

                    ConseqPassageiros cp = new ConseqPassageiros(indexIDV, indexSex, Integer.parseInt(eTextIdade.getText().toString()),
                            indexPNV, indexUDAS, indexGDGDL);

                    conspass.add(0, cp);
                    parentActivity.setConsPassageiros(conspass);
                    parentActivity.goToConsPeoFragment();

                }

            }
        });


        Button btnAntConsPass = (Button) v.findViewById(R.id.ButtonAntConsPass);
        btnAntConsPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (eTextIdade.getText().toString().equals("") ||rGroupSex.getCheckedRadioButtonId() == -1
                        ||rGroupPNV.getCheckedRadioButtonId() == -1 || rGroupUDADS.getCheckedRadioButtonId() == -1
                        ||rGroupGDGDL.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));
                    int indexPNV = rGroupPNV.indexOfChild(rGroupPNV.findViewById(rGroupPNV.getCheckedRadioButtonId()));
                    int indexUDAS = rGroupUDADS.indexOfChild(rGroupUDADS.findViewById(rGroupUDADS.getCheckedRadioButtonId()));
                    int indexGDGDL = rGroupGDGDL.indexOfChild(rGroupGDGDL.findViewById(rGroupGDGDL.getCheckedRadioButtonId()));

                    //Para modificar no futuro
                    int indexIDV = 0;

                    ConseqPassageiros cp = new ConseqPassageiros(indexIDV, indexSex, Integer.parseInt(eTextIdade.getText().toString()),
                            indexPNV, indexUDAS, indexGDGDL);

                    conspass.add(0, cp);
                    parentActivity.setConsPassageiros(conspass);
                    parentActivity.goToCondInt2Fragment();

                }
            }
        });



        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToConsPeoFragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt2Fragment();
            }

        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToConsPassFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConsPassListener) {
            mListener = (OnConsPassListener) context;
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
    public interface OnConsPassListener {
        // TODO: Update argument type and name
        void goToConsPassFragment();
    }
}
