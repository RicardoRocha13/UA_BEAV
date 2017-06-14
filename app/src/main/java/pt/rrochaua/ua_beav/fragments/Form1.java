package pt.rrochaua.ua_beav.fragments;


import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


public class Form1 extends Fragment {

    MainActivity parentActivity;
    private OnForm1Listener mListener;

    private static final String TAG = "Debug";
    private Boolean flag = false;
    EditText etCoord = null;
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


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_form1, container, false);

        etCoord = (EditText) v.findViewById(R.id.editTextCoor);

        Button btnSegS = (Button) v.findViewById(R.id.ButtonSegS);
        btnSegS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondIntSemFragment();
            }
        });


        Button btnSegC = (Button) v.findViewById(R.id.ButtonSegC);
        btnSegC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCircExt1Fragment();
            }
        });

        Button btnChangeDate = (Button) v.findViewById(R.id.btnDate);
        final EditText eTextDia = (EditText) v.findViewById(R.id.eTDia);
        final EditText eTextMes = (EditText) v.findViewById(R.id.eTMes);
        final EditText eTextAno = (EditText) v.findViewById(R.id.eTAno);
        final EditText eTHora = (EditText) v.findViewById(R.id.eTHora);
        final EditText eTMin = (EditText) v.findViewById(R.id.eTMin);


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

        Button btnTime = (Button) v.findViewById(R.id.btnTime);
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

        Button btnNumCond = (Button) v.findViewById(R.id.btnokNCon);
        final EditText etNumCond = (EditText) v.findViewById(R.id.eTNumCond);

        btnNumCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setNumbCond(Integer.parseInt(String.valueOf(etNumCond.getText())));
                LinearLayout ll = (LinearLayout) parentActivity.findViewById(R.id.llRadio);
                ll.removeAllViews();
                for (int x = 1; x <= parentActivity.getNumbCond(); x++) {
                    RadioGroup rg = new RadioGroup(parentActivity);
                    rg.setOrientation(LinearLayout.HORIZONTAL);
                    rg.setId(x);

                    RadioButton rdbtn0 = new RadioButton(parentActivity);
                    rdbtn0.setId(Integer.parseInt(x + "0"));
                    rdbtn0.setText("Masculino");
                    rg.addView(rdbtn0);

                    RadioButton rdbtn1 = new RadioButton(parentActivity);
                    rdbtn1.setId(Integer.parseInt(x + "1"));
                    rdbtn1.setText("Feminino");
                    rg.addView(rdbtn1);

                    ll.addView(rg);

                }
            }
        });




//adiciona o valor das coordenadas no EditTextCoor
        Button buttonCoor = (Button) v.findViewById(R.id.buttonCoor);
        buttonCoor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    locationManager = (LocationManager) parentActivity.getSystemService(Context.LOCATION_SERVICE);
                    LocationListener locationListener = new MyLocationListener();
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, locationListener);
                }

        });
        return v;
    }

    //Listener do GPS
    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            System.out.println("###################################");
            System.out.println("LAT: " + location.getLatitude());
            System.out.println("LOMG: " + location.getLongitude());
            System.out.println("###################################");
            etCoord.setText("LAT: " + location.getLatitude() + " LONG: " + location.getLongitude());
            //deslida updates GPS
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
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
