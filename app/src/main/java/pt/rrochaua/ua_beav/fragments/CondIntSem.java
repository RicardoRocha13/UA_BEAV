package pt.rrochaua.ua_beav.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.SemVitim;
import pt.rrochaua.ua_beav.models.SemVitimCondutor;


public class CondIntSem extends Fragment {
    MainActivity parentActivity;
    ArrayList<SemVitim> semvitim;
    private OnCondIntSemListener mListener;



    public CondIntSem() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CondIntSem newInstance() {
        CondIntSem fragment = new CondIntSem();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        semvitim = parentActivity.getsViti();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cond_int_sem, container, false);

        final RadioGroup rGroupVI = (RadioGroup) v.findViewById(R.id.radioGroupVI);
        final RadioGroup rGroupCondP = (RadioGroup) v.findViewById(R.id.radioGroupCondP);
        final RadioGroup rGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSex);

        ImageButton btnChangeDate = (ImageButton) v.findViewById(R.id.btnDate);
        final EditText eTextDia = (EditText) v.findViewById(R.id.eTDia);
        final EditText eTextMes = (EditText) v.findViewById(R.id.eTMes);
        final EditText eTextAno = (EditText) v.findViewById(R.id.eTAno);

        Button btnSegCondIntSem = (Button) v.findViewById(R.id.ButtonSegCondIntSem);
        Button btnAntCondIntSem = (Button) v.findViewById(R.id.ButtonAntCondIntSem);


        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(semvitim.size()>=1){
            if (semvitim.get(0).condutorPresente==0){
                //faltava isto
                SemVitimCondutor svc = ((SemVitimCondutor) semvitim.get(0));

                //mudei isto
                Date data = svc.idade;
                Calendar cal = Calendar.getInstance();
                cal.setTime(data);
                eTextDia.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                int mes = cal.get(Calendar.MONTH)+1;
                eTextMes.setText(String.valueOf(mes));
                eTextAno.setText(String.valueOf(cal.get(Calendar.YEAR)));
                //mudei isto
                ((RadioButton)rGroupSex.getChildAt(svc.genero)).setChecked(true);


            }

            ((RadioButton)rGroupCondP.getChildAt(semvitim.get(0).condutorPresente)).setChecked(true);
            ((RadioButton)rGroupVI.getChildAt(semvitim.get(0).veiculo)).setChecked(true);


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


        btnSegCondIntSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupCondP.getCheckedRadioButtonId() == -1 || rGroupVI.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    Date idade = null;
                    try {
                        idade = sdf.parse(eTextDia.getText().toString() + "/" + eTextMes.getText().toString() + "/" + eTextAno.getText().toString() +
                                " " );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int indexPresente = rGroupCondP.indexOfChild(rGroupCondP.findViewById(rGroupCondP.getCheckedRadioButtonId()));int Presente = rGroupCondP.indexOfChild(rGroupCondP.findViewById(rGroupCondP.getCheckedRadioButtonId()));
                    int indexVeiculo = rGroupVI.indexOfChild(rGroupVI.findViewById(rGroupVI.getCheckedRadioButtonId()));


                    //estava mal
                    if (rGroupCondP.getCheckedRadioButtonId() == R.id.radioButtonCondP1) {

                        if (eTextDia.getText().toString().equals("") || rGroupSex.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                        }
                        int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));

                        SemVitimCondutor SVC = new SemVitimCondutor(indexVeiculo, indexPresente, indexSex, idade);

                        semvitim.add(0, SVC);
                        parentActivity.setsViti(semvitim);
                        parentActivity.goToFotoEsquemaFragment();

                    }else{
                        SemVitim SV = new SemVitim(indexVeiculo, indexPresente);
                        semvitim.add(0, SV);
                        parentActivity.setsViti(semvitim);
                        parentActivity.goToFotoEsquemaFragment();
                    }

                }




            }
        });


        btnAntCondIntSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (rGroupCondP.getCheckedRadioButtonId() == -1 || rGroupVI.getCheckedRadioButtonId() == -1){
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {

                    Date idade = null;
                    try {
                        idade = sdf.parse(eTextDia.getText().toString() + "/" + eTextMes.getText().toString() + "/" + eTextAno.getText().toString() +
                                " " );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    int indexPresente = rGroupCondP.indexOfChild(rGroupCondP.findViewById(rGroupCondP.getCheckedRadioButtonId()));int Presente = rGroupCondP.indexOfChild(rGroupCondP.findViewById(rGroupCondP.getCheckedRadioButtonId()));
                    int indexVeiculo = rGroupVI.indexOfChild(rGroupVI.findViewById(rGroupVI.getCheckedRadioButtonId()));


                    if (rGroupVI.getCheckedRadioButtonId() == 0) {

                        if (eTextDia.getText().toString().equals("") || rGroupSex.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                        }
                        int indexSex = rGroupSex.indexOfChild(rGroupSex.findViewById(rGroupSex.getCheckedRadioButtonId()));

                        SemVitimCondutor SVC = new SemVitimCondutor(indexVeiculo, indexPresente, indexSex, idade);

                        semvitim.add(0, SVC);
                        parentActivity.setsViti(semvitim);

                        parentActivity.goToForm1Fragment();

                    }else{
                        SemVitim SV = new SemVitim(indexVeiculo, indexPresente);
                        semvitim.add(0, SV);
                        parentActivity.setsViti(semvitim);

                        parentActivity.goToForm1Fragment();
                    }

                }

            }
        });





        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCondIntSemFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCondIntSemListener) {
            mListener = (OnCondIntSemListener) context;
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
    public interface OnCondIntSemListener {
        // TODO: Update argument type and name
        void goToCondIntSemFragment();
    }
}
