package pt.rrochaua.ua_beav.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


public class CondInt1 extends Fragment {
    MainActivity parentActivity;

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
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cond_int1, container, false);

        final ArrayList<Integer> checkBOF = new ArrayList<Integer>();

        Button btnSegCondInt1 = (Button) v.findViewById(R.id.ButtonSegCondInt1);
        Button btnAntCondInt1 = (Button) v.findViewById(R.id.ButtonAntCondInt1);
        ImageButton btnChangeDate = (ImageButton) v.findViewById(R.id.btnDate);
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

        btnAntCondInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToVeicInt1Fragment();
            }
        });

        btnSegCondInt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbOF1.isChecked()){
                    checkBOF.add(1);
                }
                if(cbOF2.isChecked()){
                    checkBOF.add(2);
                }
                if(cbOF3.isChecked()){
                    checkBOF.add(3);
                }
                if(cbOF4.isChecked()){
                    checkBOF.add(4);
                }
                if(cbOF5.isChecked()){
                    checkBOF.add(5);
                }
                if(cbOF6.isChecked()){
                    checkBOF.add(6);
                }

                System.out.println("###################");
                System.out.println(checkBOF);
                System.out.println("###################");

                parentActivity.goToCondInt2Fragment();
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
