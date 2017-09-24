package pt.rrochaua.ua_beav.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.CondInterveniente1;
import pt.rrochaua.ua_beav.models.CondInterveniente1CartaTeste;
import pt.rrochaua.ua_beav.models.CondInterveniente1Teste;
import pt.rrochaua.ua_beav.models.CondIntervenientes1Carta;


public class CondInt1 extends Fragment {
    MainActivity parentActivity;
    ArrayList<CondInterveniente1> condint1;
    private OnCondInt1Listener mListener;

    public CondInt1() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static CondInt1 newInstance() {
        CondInt1 fragment = new CondInt1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        condint1 = parentActivity.getcInterv1();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cond_int1, container, false);

        final ArrayList<Integer> checkBOF = new ArrayList<Integer>();

        Button btnSegCondInt1 = (Button) v.findViewById(R.id.ButtonSegCondInt1);
        Button btnAntCondInt1 = (Button) v.findViewById(R.id.ButtonAntCondInt1);


        final RadioGroup rGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSex);
        ImageButton btnChangeDate = (ImageButton) v.findViewById(R.id.btnDate);
        final RadioGroup rGroupLic = (RadioGroup) v.findViewById(R.id.radioGroupLCDC);
        final TextView textViewLig = (TextView) v.findViewById(R.id.textViewLig);
        final EditText eTextLig = (EditText) v.findViewById(R.id.editTextLig);
        final TextView textViewAnoHab = (TextView) v.findViewById(R.id.textViewAnoHab);
        final EditText eTextAnoHab = (EditText) v.findViewById(R.id.editTextAnoHab);
        final RadioGroup rGroupCDNDA = (RadioGroup) v.findViewById(R.id.radioGroupCDNDA);
        final TextView textViewPes = (TextView) v.findViewById(R.id.textViewPes);
        final EditText eTextPes = (EditText) v.findViewById(R.id.editTextPes);
        final RadioGroup rGroupTDCC = (RadioGroup) v.findViewById(R.id.radioGroupTDCC);

        final EditText eTextDia = (EditText) v.findViewById(R.id.eTDia);
        final EditText eTextMes = (EditText) v.findViewById(R.id.eTMes);
        final EditText eTextAno = (EditText) v.findViewById(R.id.eTAno);
        final EditText eTHora = (EditText) v.findViewById(R.id.eTHora);
        final EditText eTMin = (EditText) v.findViewById(R.id.eTMin);


        final CheckBox cbOF1 = (CheckBox) v.findViewById(R.id.radioButtonOF1);
        final CheckBox cbOF2 = (CheckBox) v.findViewById(R.id.radioButtonOF2);
        final CheckBox cbOF3 = (CheckBox) v.findViewById(R.id.radioButtonOF3);
        final CheckBox cbOF4 = (CheckBox) v.findViewById(R.id.radioButtonOF4);
        final CheckBox cbOF5 = (CheckBox) v.findViewById(R.id.radioButtonOF5);
        final CheckBox cbOF6 = (CheckBox) v.findViewById(R.id.radioButtonOF6);

        //ArrayList para obter os dados para radioGroupOF
        ArrayList<Integer> checkWriteOF = new ArrayList<Integer>();


        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        if(condint1.size()>=1){

            ((RadioButton)rGroupSex.getChildAt(condint1.get(0).genero)).setChecked(true);
            Date data = condint1.get(0).idade;
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            eTextDia.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            int mes = cal.get(Calendar.MONTH)+1;
            eTextMes.setText(String.valueOf(mes));
            eTextAno.setText(String.valueOf(cal.get(Calendar.YEAR)));
            ((RadioButton)rGroupLic.getChildAt(condint1.get(0).licencaCarta)).setChecked(true);
            ((RadioButton)rGroupCDNDA.getChildAt(condint1.get(0).testeAlcool)).setChecked(true);
            checkWriteOF = condint1.get(0).outrosFactores;

            for (int k=0; k<checkWriteOF.size(); k++) {
                if (checkWriteOF.get(k) == 1) {

                    cbOF1.setChecked(true);


                }
                if (checkWriteOF.get(k) == 2) {

                    cbOF2.setChecked(true);


                }
                if (checkWriteOF.get(k) == 3) {

                    cbOF3.setChecked(true);

                }
                if (checkWriteOF.get(k) == 4) {

                    cbOF4.setChecked(true);


                }

                if (checkWriteOF.get(k) == 5) {

                    cbOF5.setChecked(true);

                }
                if (checkWriteOF.get(k) == 6) {

                    cbOF6.setChecked(true);


                }
            }

                ((RadioButton)rGroupTDCC.getChildAt(condint1.get(0).tempoCondução)).setChecked(true);


                //FALTAM VERIFICAÇÔES - CORRIGIDO
                if (condint1.get(0).testeAlcool==0 && condint1.get(0).licencaCarta!=0){

                    CondInterveniente1Teste ci1t = ((CondInterveniente1Teste)condint1.get(0));

                    eTextPes.setText(String.valueOf(ci1t.valorAlcool));
                    textViewPes.setVisibility(View.VISIBLE);
                    eTextPes.setVisibility(View.VISIBLE);


                }

                if (condint1.get(0).licencaCarta==0){

                    CondIntervenientes1Carta ci1c = ((CondIntervenientes1Carta)condint1.get(0));

                    eTextLig.setText(String.valueOf(ci1c.paisEmissao));
                    eTextAnoHab.setText(String.valueOf(ci1c.anoHabilitação));

                    textViewLig.setVisibility(View.VISIBLE);
                    eTextLig.setVisibility(View.VISIBLE);
                    textViewAnoHab.setVisibility(View.VISIBLE);
                    eTextAnoHab.setVisibility(View.VISIBLE);


                    if (condint1.get(0).testeAlcool==0){

                        CondInterveniente1CartaTeste ci1ct = ((CondInterveniente1CartaTeste)condint1.get(0));

                        eTextPes.setText(String.valueOf(ci1ct.valorAlcool));
                        textViewPes.setVisibility(View.VISIBLE);
                        eTextPes.setVisibility(View.VISIBLE);

                    }


                }





        }


        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code to open fragment
                final Dialog dateDialog = new Dialog(v.getContext());
                dateDialog.setContentView(R.layout.date_picker);
                dateDialog.show();

                Button btnDateCancel = (Button) dateDialog.findViewById(R.id.btnDateCancel);
                Button btnDateOk = (Button) dateDialog.findViewById(R.id.btnDateOk);
                final DatePicker dP = (DatePicker) dateDialog.findViewById(R.id.datePicker);

                btnDateCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateDialog.dismiss();
                    }
                });

                btnDateOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dateDialog.dismiss();
                        eTextDia.setText(Integer.toString(dP.getDayOfMonth()));
                        eTextMes.setText(Integer.toString(dP.getMonth() + 1));
                        eTextAno.setText(Integer.toString(dP.getYear()));

                    }
                });

            }
        });



        rGroupLic.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonLCDC1){
                    textViewLig.setVisibility(View.VISIBLE);
                    eTextLig.setVisibility(View.VISIBLE);
                    textViewAnoHab.setVisibility(View.VISIBLE);
                    eTextAnoHab.setVisibility(View.VISIBLE);
                } else{
                    textViewLig.setVisibility(View.GONE);
                    eTextLig.setVisibility(View.GONE);
                    textViewAnoHab.setVisibility(View.GONE);
                    eTextAnoHab.setVisibility(View.GONE);
                }
            }
        });





        rGroupCDNDA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonCDNDA1){
                    textViewPes.setVisibility(View.VISIBLE);
                    eTextPes.setVisibility(View.VISIBLE);
                } else{
                    textViewPes.setVisibility(View.GONE);
                    eTextPes.setVisibility(View.GONE);
                }
            }
        });




        btnAntCondInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rGroupSex.getCheckedRadioButtonId() == -1 || rGroupLic.getCheckedRadioButtonId() == -1 ||
                        eTextDia.getText().toString().equals("") || rGroupCDNDA.getCheckedRadioButtonId() == -1 ||
                        rGroupTDCC.getCheckedRadioButtonId() == -1 ){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    int idveiculo = 0;


                    Date idade = null;
                    try {
                        idade = sdf.parse(eTextDia.getText().toString() + "/" + eTextMes.getText().toString() + "/" + eTextAno.getText().toString() +
                                " " );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));
                    int indexLicença = rGroupLic.indexOfChild(rGroupLic.findViewById(rGroupLic.getCheckedRadioButtonId()));
                    int indexCDNDA = rGroupCDNDA.indexOfChild(rGroupCDNDA.findViewById(rGroupCDNDA.getCheckedRadioButtonId()));
                    int indexTDCC = rGroupTDCC.indexOfChild(rGroupTDCC.findViewById(rGroupTDCC.getCheckedRadioButtonId()));


                    if (cbOF1.isChecked()) {
                        checkBOF.add(1);
                    }
                    if (cbOF2.isChecked()) {
                        checkBOF.add(2);
                    }
                    if (cbOF3.isChecked()) {
                        checkBOF.add(3);
                    }
                    if (cbOF4.isChecked()) {
                        checkBOF.add(4);
                    }
                    if (cbOF5.isChecked()) {
                        checkBOF.add(5);
                    }
                    if (cbOF6.isChecked()) {
                        checkBOF.add(6);
                    }


                    if (rGroupLic.getCheckedRadioButtonId() == R.id.radioButtonLCDC1) {

                        if (eTextAnoHab.getText().toString().equals("") || eTextLig.getText().toString().equals("")) {
                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                        }else {
                            int indexAnoHab = Integer.parseInt(eTextAnoHab.getText().toString());
                            //PAÍS - APARECE TECLANO ALFANUMERICO - NÂO CORRIGIDO
                            int indexLig = Integer.parseInt(eTextLig.getText().toString());

                            if (rGroupCDNDA.getCheckedRadioButtonId() == R.id.radioButtonCDNDA1){
                                if (eTextPes.getText().toString().equals("")){
                                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                                } else {
                                    //TIPO ERRADO DE VARIAVEL - CORRIGIDO
                                    float indexPes = Float.parseFloat(eTextPes.getText().toString());
                                    CondInterveniente1CartaTeste ci1ct = new CondInterveniente1CartaTeste (idveiculo, indexSex, idade,
                                            indexLicença, indexCDNDA, checkBOF, indexTDCC, indexLig, indexAnoHab, indexPes);

                                    condint1.add(0,ci1ct);
                                    parentActivity.setcInterv1(condint1);
                                    parentActivity.goToVeicInt1Fragment();
                                }

                            }else{
                                CondIntervenientes1Carta ci1c = new CondIntervenientes1Carta (idveiculo, indexSex, idade,
                                        indexLicença, indexCDNDA, checkBOF, indexTDCC, indexLig, indexAnoHab);

                                condint1.add(0,ci1c);
                                parentActivity.setcInterv1(condint1);
                                parentActivity.goToVeicInt1Fragment();

                            }





                        }

                    } else {
                        if (rGroupCDNDA.getCheckedRadioButtonId() == R.id.radioButtonCDNDA1){
                            if (eTextPes.getText().toString().equals("")){
                                Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                            } else {
                                //TIPO ERRADO DE VARIAVEL - CORRIGIDO
                                float indexPes = Float.parseFloat(eTextPes.getText().toString());
                                CondInterveniente1Teste ci1t = new CondInterveniente1Teste (idveiculo, indexSex, idade,
                                        indexLicença, indexCDNDA, checkBOF, indexTDCC, indexPes);

                                condint1.add(0,ci1t);
                                parentActivity.setcInterv1(condint1);
                                parentActivity.goToVeicInt1Fragment();
                            }

                        }else{
                            CondInterveniente1 ci1 = new CondInterveniente1 (idveiculo, indexSex, idade,
                                    indexLicença, indexCDNDA, checkBOF, indexTDCC);

                            condint1.add(0,ci1);
                            parentActivity.setcInterv1(condint1);
                            parentActivity.goToVeicInt1Fragment();

                        }

                    }

                }


            }
        });

        btnSegCondInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(rGroupSex.getCheckedRadioButtonId() == -1 || rGroupLic.getCheckedRadioButtonId() == -1 ||
                    eTextDia.getText().toString().equals("") || rGroupCDNDA.getCheckedRadioButtonId() == -1 ||
                        rGroupTDCC.getCheckedRadioButtonId() == -1 ){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    int idveiculo = 0;


                    Date idade = null;
                    try {
                        idade = sdf.parse(eTextDia.getText().toString() + "/" + eTextMes.getText().toString() + "/" + eTextAno.getText().toString() +
                                " " );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));
                    int indexLicença = rGroupLic.indexOfChild(rGroupLic.findViewById(rGroupLic.getCheckedRadioButtonId()));
                    int indexCDNDA = rGroupCDNDA.indexOfChild(rGroupCDNDA.findViewById(rGroupCDNDA.getCheckedRadioButtonId()));
                    int indexTDCC = rGroupTDCC.indexOfChild(rGroupTDCC.findViewById(rGroupTDCC.getCheckedRadioButtonId()));


                    if (cbOF1.isChecked()) {
                        checkBOF.add(1);
                    }
                    if (cbOF2.isChecked()) {
                        checkBOF.add(2);
                    }
                    if (cbOF3.isChecked()) {
                        checkBOF.add(3);
                    }
                    if (cbOF4.isChecked()) {
                        checkBOF.add(4);
                    }
                    if (cbOF5.isChecked()) {
                        checkBOF.add(5);
                    }
                    if (cbOF6.isChecked()) {
                        checkBOF.add(6);
                    }


                    if (rGroupLic.getCheckedRadioButtonId() == R.id.radioButtonLCDC1) {

                        if (eTextAnoHab.getText().toString().equals("") || eTextLig.getText().toString().equals("")) {
                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                        }else {
                            int indexAnoHab = Integer.parseInt(eTextAnoHab.getText().toString());
                            //PAÍS - APARECE TECLANO ALFANUMERICO - NÂO CORRIGIDO
                            int indexLig = Integer.parseInt(eTextLig.getText().toString());

                            if (rGroupCDNDA.getCheckedRadioButtonId() == R.id.radioButtonCDNDA1){
                                if (eTextPes.getText().toString().equals("")){
                                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                                } else {
                                    //TIPO ERRADO DE VARIAVEL - CORRIGIDO
                                    float indexPes = Float.parseFloat(eTextPes.getText().toString());
                                    CondInterveniente1CartaTeste ci1ct = new CondInterveniente1CartaTeste (idveiculo, indexSex, idade,
                                            indexLicença, indexCDNDA, checkBOF, indexTDCC, indexLig, indexAnoHab, indexPes);

                                    condint1.add(0,ci1ct);
                                    parentActivity.setcInterv1(condint1);
                                    parentActivity.goToCondInt2Fragment();
                                }

                            }else{
                                CondIntervenientes1Carta ci1c = new CondIntervenientes1Carta (idveiculo, indexSex, idade,
                                        indexLicença, indexCDNDA, checkBOF, indexTDCC, indexLig, indexAnoHab);

                                condint1.add(0,ci1c);
                                parentActivity.setcInterv1(condint1);
                                parentActivity.goToCondInt2Fragment();

                            }





                        }

                    } else {
                        if (rGroupCDNDA.getCheckedRadioButtonId() == R.id.radioButtonCDNDA1){
                            if (eTextPes.getText().toString().equals("")){
                                Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                            } else {
                                //TIPO ERRADO DE VARIAVEL - CORRIGIDO
                                float indexPes = Float.parseFloat(eTextPes.getText().toString());
                                CondInterveniente1Teste ci1t = new CondInterveniente1Teste (idveiculo, indexSex, idade,
                                        indexLicença, indexCDNDA, checkBOF, indexTDCC, indexPes);

                                condint1.add(0,ci1t);
                                parentActivity.setcInterv1(condint1);
                                parentActivity.goToCondInt2Fragment();
                            }

                        }else{
                            CondInterveniente1 ci1 = new CondInterveniente1 (idveiculo, indexSex, idade,
                                    indexLicença, indexCDNDA, checkBOF, indexTDCC);

                            condint1.add(0,ci1);
                            parentActivity.setcInterv1(condint1);
                            parentActivity.goToCondInt2Fragment();

                        }

                    }

                }
            }
        });

        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt2Fragment();

            }
        });
        Button btnAntTest = (Button) v.findViewById(R.id.ButtonAntTeste);
        btnAntTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToVeicInt1Fragment();
            }

        });


        //quando abre para escrever checkbox
        //cbOF1.setChecked(true);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCondInt1Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCondInt1Listener) {
            mListener = (OnCondInt1Listener) context;
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
    public interface OnCondInt1Listener {
        // TODO: Update argument type and name
        void goToCondInt1Fragment();
    }
}
