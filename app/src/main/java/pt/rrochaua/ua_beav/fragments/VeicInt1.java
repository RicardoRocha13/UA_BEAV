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
import android.widget.TextView;
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
        final TextView textViewLig = (TextView) v.findViewById(R.id.textViewLig);
        final TextView textViewPes = (TextView) v.findViewById(R.id.textViewPes);
        final TextView textViewVEsp = (TextView) v.findViewById(R.id.textViewVEsp);
        final TextView textViewADR = (TextView) v.findViewById(R.id.textViewADR);
        final TextView textViewMAT = (TextView) v.findViewById(R.id.textViewMAT);






        if(veicinterveniente.size()>=1){
            ((RadioButton)rGroupFuga.getChildAt(veicinterveniente.get(0).fuga)).setChecked(true);
            ((RadioButton)rGroupCat.getChildAt(veicinterveniente.get(0).categoriaClasse)).setChecked(true);
            ((RadioButton)rGroupTDS.getChildAt(veicinterveniente.get(0).tipoServico)).setChecked(true);
            eTextADM.setText(String.valueOf(veicinterveniente.get(0).anoMatricula));
            ((RadioButton)rGroupIP.getChildAt(veicinterveniente.get(0).inspeccaoPeriodica)).setChecked(true);
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





        rGroupCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonTipCat6){
                    textViewLig.setVisibility(View.VISIBLE);
                    rGroupTVeic.setVisibility(View.VISIBLE);
                } else{
                    textViewLig.setVisibility(View.GONE);
                    rGroupTVeic.setVisibility(View.GONE);
                }
            }
        });



        rGroupCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonTipCat7){
                    textViewPes.setVisibility(View.VISIBLE);
                    rGroupTVeic.setVisibility(View.VISIBLE);
                } else{
                    textViewPes.setVisibility(View.GONE);
                    rGroupTVeic.setVisibility(View.GONE);
                }
            }
        });



        rGroupTVeic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonTVeic5){
                    textViewVEsp.setVisibility(View.VISIBLE);
                    eTextVEsp.setVisibility(View.VISIBLE);
                } else{
                    textViewVEsp.setVisibility(View.GONE);
                    eTextVEsp.setVisibility(View.GONE);
                }
            }
        });


        rGroupTMerc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonTipTMerc1){
                    rGroupCADR.setVisibility(View.VISIBLE);
                    textViewADR.setVisibility(View.VISIBLE);
                    textViewMAT.setVisibility(View.VISIBLE);
                    eTextCADR.setVisibility(View.VISIBLE);
                } else{
                    rGroupCADR.setVisibility(View.GONE);
                    textViewADR.setVisibility(View.GONE);
                    textViewMAT.setVisibility(View.GONE);
                    eTextCADR.setVisibility(View.GONE);
                }
            }
        });









        Button btnSegVeicInt1 = (Button) v.findViewById(R.id.ButtonSegVeicInt1);
        btnSegVeicInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rGroupFuga.getCheckedRadioButtonId() == -1 || rGroupCat.getCheckedRadioButtonId() == -1 ||
                        rGroupTDS.getCheckedRadioButtonId() == -1 || rGroupIP.getCheckedRadioButtonId() == -1 ||
                        rGroupTMerc.getCheckedRadioButtonId() == -1 || rGroupCL.getCheckedRadioButtonId() == -1 ||
                        rGroupPneu.getCheckedRadioButtonId() == -1 || rGroupTaco.getCheckedRadioButtonId() == -1 ||
                        rGroupSeg.getCheckedRadioButtonId() == -1 || rGroupInPo.getCheckedRadioButtonId() == -1 ||
                        rGroupInPo.getCheckedRadioButtonId() == -1 || eTextADM.getText().toString().equals("") ||
                        eTextNPass.getText().toString().equals("")){

                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    int indexGroupFuga = rGroupFuga.indexOfChild(rGroupFuga.findViewById(rGroupFuga.getCheckedRadioButtonId()));
                    int indexGroupCat = rGroupCat.indexOfChild(rGroupCat.findViewById(rGroupCat.getCheckedRadioButtonId()));
                    int indexGroupTDS = rGroupTDS.indexOfChild(rGroupTDS.findViewById(rGroupTDS.getCheckedRadioButtonId()));
                    int indexADM = Integer.parseInt(eTextADM.getText().toString());
                    int indexGroupIP = rGroupIP.indexOfChild(rGroupIP.findViewById(rGroupIP.getCheckedRadioButtonId()));
                    int indexGroupTMerc = rGroupTMerc.indexOfChild(rGroupTMerc.findViewById(rGroupTMerc.getCheckedRadioButtonId()));
                    int indexGroupCL = rGroupCL.indexOfChild(rGroupCL.findViewById(rGroupCL.getCheckedRadioButtonId()));
                    int indexGroupPneu = rGroupPneu.indexOfChild(rGroupPneu.findViewById(rGroupPneu.getCheckedRadioButtonId()));
                    int indexGroupTaco = rGroupTaco.indexOfChild(rGroupTaco.findViewById(rGroupTaco.getCheckedRadioButtonId()));
                    int indexGroupSeg = rGroupSeg.indexOfChild(rGroupSeg.findViewById(rGroupSeg.getCheckedRadioButtonId()));
                    int indexGroupInPo = rGroupInPo.indexOfChild(rGroupInPo.findViewById(rGroupInPo.getCheckedRadioButtonId()));
                    int indexNPass = Integer.parseInt(eTextNPass.getText().toString());
                    int indexGroupCondP = rGroupCondP.indexOfChild(rGroupCondP.findViewById(rGroupCondP.getCheckedRadioButtonId()));




                    if(rGroupCat.getCheckedRadioButtonId() == R.id.radioButtonTipCat6 || rGroupCat.getCheckedRadioButtonId() == R.id.radioButtonTipCat7 ){

                        if (rGroupTVeic.getCheckedRadioButtonId() == -1){
                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();

                        } else {

                            int indexGroupTVeic = rGroupTVeic.indexOfChild(rGroupTVeic.findViewById(rGroupTVeic.getCheckedRadioButtonId()));


                            if(rGroupTVeic.getCheckedRadioButtonId() == R.id.radioButtonTVeic5){
                                if (eTextVEsp.getText().toString().equals("")){
                                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();

                                } else {

                                    int indexVEsp = Integer.parseInt(eTextVEsp.getText().toString());

                                    if (rGroupTMerc.getCheckedRadioButtonId() == R.id.radioButtonTipTMerc1) {

                                        if (eTextCADR.getText().toString().equals("") || rGroupCADR.getCheckedRadioButtonId() == -1 ){
                                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();

                                        } else {

                                            int indexGroupCADR = rGroupCADR.indexOfChild(rGroupCADR.findViewById(rGroupCADR.getCheckedRadioButtonId()));
                                            int indexCADR = Integer.parseInt(eTextCADR.getText().toString());

                                        }

                                    } else {


                                    }


                                }

                            }else {


                                if (rGroupTMerc.getCheckedRadioButtonId() == R.id.radioButtonTipTMerc1) {

                                    if (eTextCADR.getText().toString().equals("") || rGroupCADR.getCheckedRadioButtonId() == -1 ){
                                        Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();

                                    } else {
                                        int indexGroupCADR = rGroupCADR.indexOfChild(rGroupCADR.findViewById(rGroupCADR.getCheckedRadioButtonId()));
                                        int indexCADR = Integer.parseInt(eTextCADR.getText().toString());

                                    }

                                } else {




                                }

                            }
                        }




                    } else {


                        if (rGroupTMerc.getCheckedRadioButtonId() == R.id.radioButtonTipTMerc1) {

                            if (eTextCADR.getText().toString().equals("") || rGroupCADR.getCheckedRadioButtonId() == -1 ){
                                Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();

                            } else {
                                int indexGroupCADR = rGroupCADR.indexOfChild(rGroupCADR.findViewById(rGroupCADR.getCheckedRadioButtonId()));
                                int indexCADR = Integer.parseInt(eTextCADR.getText().toString());

                            }

                        } else {

                            VeicInterveniente vi = new VeicInterveniente(indexGroupFuga, indexGroupCat, indexGroupTDS,
                                    indexADM,  indexGroupIP, indexGroupTMerc, indexGroupCL, indexGroupPneu, indexGroupTaco,
                                    indexGroupSeg, indexGroupInPo, indexNPass, indexGroupCondP);

                            veicinterveniente.add(0,vi);
                            parentActivity.setvInterv(veicinterveniente);
                            parentActivity.goToCondInt1Fragment();

                        }

                    }





                }





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
