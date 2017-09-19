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
import pt.rrochaua.ua_beav.models.VeicInterveniente;
import pt.rrochaua.ua_beav.models.VeicIntervenienteTipo;
import pt.rrochaua.ua_beav.models.VeicIntervenienteTipoEspecial;
import pt.rrochaua.ua_beav.models.VeicIntervenienteTipoEspecialMercPerigosa;
import pt.rrochaua.ua_beav.models.VeicIntervenienteTipoMercPerigosa;
import pt.rrochaua.ua_beav.models.VeicIntervinienteMercPerigosa;


public class VeicInt1 extends Fragment {
    MainActivity parentActivity;
    ArrayList<VeicInterveniente> veicinterveniente;
    private OnVeicInt1Listener mListener;


    public VeicInt1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static VeicInt1 newInstance() {
        VeicInt1 fragment = new VeicInt1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        veicinterveniente = parentActivity.getvInterv();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_veic_int1, container, false);


        final RadioGroup rGroupFuga = (RadioGroup) v.findViewById(R.id.radioGroupFuga);
        final RadioGroup rGroupCat = (RadioGroup) v.findViewById(R.id.radioGroupCat);
        final RadioGroup rGroupTVeic = (RadioGroup) v.findViewById(R.id.radioGroupTVeic);
        final EditText eTextVEsp = (EditText) v.findViewById(R.id.editTextVEsp);
        final RadioGroup rGroupTDS = (RadioGroup) v.findViewById(R.id.radioGroupTDS);
        final EditText eTextADM = (EditText) v.findViewById(R.id.editTextADM);
        final RadioGroup rGroupIP = (RadioGroup) v.findViewById(R.id.radioGroupIP);
        final RadioGroup rGroupTMerc = (RadioGroup) v.findViewById(R.id.radioGroupTMerc);
        final RadioGroup rGroupCADR = (RadioGroup) v.findViewById(R.id.radioGroupCADR);
        final EditText eTextCADR = (EditText) v.findViewById(R.id.editTextCADR);
        final RadioGroup rGroupCL = (RadioGroup) v.findViewById(R.id.radioGroupCL);
        final RadioGroup rGroupPneu = (RadioGroup) v.findViewById(R.id.radioGroupPneu);
        final RadioGroup rGroupTaco = (RadioGroup) v.findViewById(R.id.radioGroupTaco);
        final RadioGroup rGroupSeg = (RadioGroup) v.findViewById(R.id.radioGroupSeg);
        final RadioGroup rGroupInPo = (RadioGroup) v.findViewById(R.id.radioGroupInPo);
        final EditText eTextNPass = (EditText) v.findViewById(R.id.editTextNPass);
        final RadioGroup rGroupCondP = (RadioGroup) v.findViewById(R.id.radioGroupCondP);


        if(veicinterveniente.size()>=1){
            ((RadioButton)rGroupFuga.getChildAt(veicinterveniente.get(0).fuga)).setChecked(true);
            ((RadioButton)rGroupCat.getChildAt(veicinterveniente.get(0).categoriaClasse)).setChecked(true);
            ((RadioButton)rGroupTDS.getChildAt(veicinterveniente.get(0).tipoServico)).setChecked(true);
            eTextADM.setText(String.valueOf(veicinterveniente.get(0).anoMatricula));
            ((RadioButton)rGroupTMerc.getChildAt(veicinterveniente.get(0).mercadoriasPerigosas)).setChecked(true);
            ((RadioButton)rGroupCL.getChildAt(veicinterveniente.get(0).cargaLotacao)).setChecked(true);
            ((RadioButton)rGroupPneu.getChildAt(veicinterveniente.get(0).pneus)).setChecked(true);
            ((RadioButton)rGroupTaco.getChildAt(veicinterveniente.get(0).tacografo)).setChecked(true);
            ((RadioButton)rGroupSeg.getChildAt(veicinterveniente.get(0).seguro)).setChecked(true);
            ((RadioButton)rGroupInPo.getChildAt(veicinterveniente.get(0).incendioPosterior)).setChecked(true);
            eTextNPass.setText(String.valueOf(veicinterveniente.get(0).nPassageiros));
            ((RadioButton)rGroupCondP.getChildAt(veicinterveniente.get(0).condutorPresente)).setChecked(true);


            if(veicinterveniente.get(0).categoriaClasse==5 || veicinterveniente.get(0).categoriaClasse==5){
                VeicIntervenienteTipo vit = ((VeicIntervenienteTipo) veicinterveniente.get(0));
                ((RadioButton) rGroupTVeic.getChildAt(vit.tipo)).setChecked(true);

                if(((VeicIntervenienteTipo) veicinterveniente.get(0)).tipo==4){
                    VeicIntervenienteTipoEspecial vite = ((VeicIntervenienteTipoEspecial) veicinterveniente.get(0));
                    eTextVEsp.setText(String.valueOf(vite.especial));



                    if(veicinterveniente.get(0).mercadoriasPerigosas==0){
                        VeicIntervenienteTipoEspecialMercPerigosa vitemp = ((VeicIntervenienteTipoEspecialMercPerigosa) veicinterveniente.get(0));
                        ((RadioButton)rGroupCADR.getChildAt(vitemp.certificadoADR)).setChecked(true);
                        eTextCADR.setText(String.valueOf(vitemp.materiaObjetoPerigoso));

                    }


                }


                if(veicinterveniente.get(0).mercadoriasPerigosas==0){
                    VeicIntervenienteTipoMercPerigosa vitmp = ((VeicIntervenienteTipoMercPerigosa) veicinterveniente.get(0));
                    ((RadioButton)rGroupCADR.getChildAt(vitmp.certificadoADR)).setChecked(true);
                    eTextCADR.setText(String.valueOf(vitmp.materiaObjetoPerigoso));
                }


            }


            if(veicinterveniente.get(0).mercadoriasPerigosas==0){
                VeicIntervinienteMercPerigosa mp = ((VeicIntervinienteMercPerigosa) veicinterveniente.get(0));
                ((RadioButton)rGroupCADR.getChildAt(mp.certificadoADR)).setChecked(true);
                eTextCADR.setText(String.valueOf(mp.materiaObjetoPerigoso));
            }






        }





        Button btnSegVeicInt1 = (Button) v.findViewById(R.id.ButtonSegVeicInt1);
        btnSegVeicInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rGroupFuga.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    if (rGroupFuga.getCheckedRadioButtonId() == R.id.radioButtonFuga1){

                    }


                }






                parentActivity.goToCondInt1Fragment();
            }
        });


        Button btnAntVeicInt1 = (Button) v.findViewById(R.id.ButtonAntVeicInt1);
        btnAntVeicInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToNatAciFragment();
            }
        });




        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt1Fragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToNatAciFragment();
            }

        });



        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToVeicInt1Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnVeicInt1Listener) {
            mListener = (OnVeicInt1Listener) context;
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
    public interface OnVeicInt1Listener {
        // TODO: Update argument type and name
        void goToVeicInt1Fragment();
    }
}
