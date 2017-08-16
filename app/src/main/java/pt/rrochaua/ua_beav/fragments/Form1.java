package pt.rrochaua.ua_beav.fragments;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.Form1Model;


public class Form1 extends Fragment {

    MainActivity parentActivity;
    ArrayList<Form1Model> form1;
    private OnForm1Listener mListener;

    private static final String TAG = "Debug";
    private Boolean flag = false;
    LocationManager locationManager = null;


    public Form1() {
        // Required empty public constructor
    }


    public static Form1 newInstance() {
        Form1 frag = new Form1();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();
        form1 = parentActivity.getForm1();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_form1, container, false);

        ImageButton btnChangeDate = (ImageButton) v.findViewById(R.id.btnDate);
        final EditText eTextDia = (EditText) v.findViewById(R.id.eTDia);
        final EditText eTextMes = (EditText) v.findViewById(R.id.eTMes);
        final EditText eTextAno = (EditText) v.findViewById(R.id.eTAno);
        ImageButton btnTime = (ImageButton) v.findViewById(R.id.btnTime);
        final EditText eTHora = (EditText) v.findViewById(R.id.eTHora);
        final EditText eTMin = (EditText) v.findViewById(R.id.eTMin);
        final RadioGroup rGroupLocali = (RadioGroup) v.findViewById(R.id.radioGroupLoc);
        final EditText eTLocalAci = (EditText) v.findViewById(R.id.editTextLoc);
        ImageButton buttonCoor = (ImageButton) v.findViewById(R.id.buttonCoor);
        final EditText eTCoordLAT = (EditText) v.findViewById(R.id.editTextLat);
        final EditText eTCoordLON = (EditText) v.findViewById(R.id.editTextLon);
        final RadioGroup rGTipoAcide = (RadioGroup) v.findViewById(R.id.radioGroupTDA);
        final TextView txtVnpeosviti = (TextView) v.findViewById(R.id.textViewEsqAci);
        final EditText eTnPeoesViti = (EditText) v.findViewById(R.id.editTextNPeoes);
        final RadioGroup rGNaturaAcident = (RadioGroup) v.findViewById(R.id.radioGroupNDA);
        final EditText eTnVeic = (EditText) v.findViewById(R.id.eTNumVeic);
        Button btnSegC = (Button) v.findViewById(R.id.ButtonSegC);

        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm");

        if(form1.size()>= 1){
            Date data = form1.get(0).dataHora;
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            eTextDia.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            int mes = cal.get(Calendar.MONTH)+1;
            eTextMes.setText(String.valueOf(mes));
            eTextAno.setText(String.valueOf(cal.get(Calendar.YEAR)));
            eTHora.setText(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
            eTMin.setText(String.valueOf(cal.get(Calendar.MINUTE)));
            ((RadioButton)rGroupLocali.getChildAt(form1.get(0).localizacao)).setChecked(true);
            eTLocalAci.setText(form1.get(0).local);
            //ver casa decimais. está a arredondar
            eTCoordLAT.setText(String.valueOf(form1.get(0).coordLat));
            System.out.println("############################" + form1.get(0).coordLat);
            eTCoordLON.setText(String.valueOf(form1.get(0).coordLon));
            ((RadioButton)rGTipoAcide.getChildAt(form1.get(0).tipoAcidente)).setChecked(true);
            if(form1.get(0).tipoAcidente==1){
                txtVnpeosviti.setVisibility(View.VISIBLE);
                eTnPeoesViti.setVisibility(View.VISIBLE);
                eTnPeoesViti.setText(String.valueOf(form1.get(0).nPeoesVitimas));
            }
            ((RadioButton)rGNaturaAcident.getChildAt(form1.get(0).naturezaAcidente)).setChecked(true);
            eTnVeic.setText(String.valueOf(form1.get(0).nVeiculos));
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


        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog timeDialog = new Dialog(v.getContext());
                timeDialog.setContentView(R.layout.time_picker);
                final TimePicker timeP = (TimePicker) timeDialog.findViewById(R.id.timePicker);

                timeP.setIs24HourView(true);

                timeDialog.show();

                Button btnTimeCancel = (Button) timeDialog.findViewById(R.id.btnTimeCancel);
                Button btnTimeOk = (Button) timeDialog.findViewById(R.id.btnTimeOk);

                btnTimeCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeDialog.dismiss();
                    }
                });

                btnTimeOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeDialog.dismiss();
                        eTHora.setVisibility(View.VISIBLE);
                        eTMin.setVisibility(View.VISIBLE);
                        eTHora.setText(Integer.toString(timeP.getCurrentHour()));
                        eTMin.setText(Integer.toString(timeP.getCurrentMinute()));

                    }
                });


            }
        });

        //adiciona o valor das coordenadas no EditTextCoor
        buttonCoor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //parentActivity.gPSPermission();

                parentActivity.dispatchGetCoorIntent();
            }

        });

        rGTipoAcide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radioButtonTDA2){
                    txtVnpeosviti.setVisibility(View.VISIBLE);
                    eTnPeoesViti.setVisibility(View.VISIBLE);
                } else{
                    eTnPeoesViti.setText("");
                    txtVnpeosviti.setVisibility(View.GONE);
                    eTnPeoesViti.setVisibility(View.GONE);
                }
            }
        });


        btnSegC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eTextDia.getText().toString().equals("") || eTHora.getText().toString().equals("")
                        || rGroupLocali.getCheckedRadioButtonId() == -1 || eTCoordLAT.getText().toString().equals("")
                        || eTCoordLON.getText().toString().equals("") || rGTipoAcide.getCheckedRadioButtonId() == -1
                        || rGNaturaAcident.getCheckedRadioButtonId() == -1 || eTnVeic.getText().toString().equals("")) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    if (Float.parseFloat(eTCoordLAT.getText().toString()) < -90 || Float.parseFloat(eTCoordLAT.getText().toString()) > 90) {
                        Toast.makeText(parentActivity, "Latitude formato errado.", Toast.LENGTH_LONG).show();
                    } else if (Float.parseFloat(eTCoordLON.getText().toString()) < -180 || Float.parseFloat(eTCoordLON.getText().toString()) > 180) {
                        Toast.makeText(parentActivity, "Longitude formato errado.", Toast.LENGTH_LONG).show();
                    }else if(rGTipoAcide.getCheckedRadioButtonId() == R.id.radioButtonTDA2 && eTnPeoesViti.getText().toString().equals("")){
                        Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();                 }
                    else {

                        Date data = null;
                        try {
                            data = sdf.parse(eTextDia.getText().toString() + "/" + eTextMes.getText().toString() + "/" + eTextAno.getText().toString() +
                                    " " + eTHora.getText().toString() + ":" + eTMin.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int indexLocalizacao = rGroupLocali.indexOfChild(rGroupLocali.findViewById(rGroupLocali.getCheckedRadioButtonId()));
                        int indexTipoAcidente = rGTipoAcide.indexOfChild(rGTipoAcide.findViewById(rGTipoAcide.getCheckedRadioButtonId()));
                        int nPeoesVit = 0;
                        if(rGTipoAcide.getCheckedRadioButtonId() == R.id.radioButtonTDA2){
                            nPeoesVit = Integer.parseInt(eTnPeoesViti.getText().toString());
                        }
                        int indexNaturaAcidente = rGNaturaAcident.indexOfChild((rGNaturaAcident.findViewById(rGNaturaAcident.getCheckedRadioButtonId())));
                        Form1Model f1 = new Form1Model(data, indexLocalizacao, eTLocalAci.getText().toString(), Double.parseDouble(eTCoordLAT.getText().toString()),
                                Double.parseDouble(eTCoordLON.getText().toString()), indexTipoAcidente, nPeoesVit, indexNaturaAcidente, Integer.parseInt(eTnVeic.getText().toString()));

                        form1.add(0, f1);
                        parentActivity.setForm1(form1);
                        parentActivity.testeSAVE();

                        if(rGTipoAcide.getCheckedRadioButtonId() == R.id.radioButtonTDA2){
                            parentActivity.goToCircExt1Fragment();
                        }else{
                            parentActivity.goToCondIntSemFragment();
                        }

                    }


                }

            }
        });


        Button btnSegTest = (Button) v.findViewById(R.id.ButtonSegTeste);
        btnSegTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rGTipoAcide.getCheckedRadioButtonId() == R.id.radioButtonTDA2){
                    parentActivity.goToCircExt1Fragment();
                }else{
                    parentActivity.goToCondIntSemFragment();
                }
            }
        });



        return v;

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToForm1Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnForm1Listener) {
            mListener = (OnForm1Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnForm1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnForm1Listener {
        // TODO: Update argument type and name
        void goToForm1Fragment();
    }


}
