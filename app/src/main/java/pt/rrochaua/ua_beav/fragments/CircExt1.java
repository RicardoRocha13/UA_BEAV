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
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.CircExternas1;


public class CircExt1 extends Fragment {

    MainActivity parentActivity;
    ArrayList<CircExternas1> circexternas1;
    private OnCircExt1Listener mListener;


    public CircExt1() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CircExt1 newInstance() {
        CircExt1 frag = new CircExt1();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();
        super.onCreate(savedInstanceState);
        circexternas1 = parentActivity.getcExt1();
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_circ_ext1, container, false);

        final RadioGroup rGroupTipVia = (RadioGroup) v.findViewById(R.id.radioGroupTVia);
        final EditText eTSemSnVias  = (EditText) v.findViewById(R.id.editTextSemSnVias);
        final RadioGroup rGroupViaT = (RadioGroup) v.findViewById(R.id.radioGroupViaT);
        final RadioGroup rGroupPlant = (RadioGroup) v.findViewById(R.id.radioGroupPlant);
        final RadioGroup rGroupPerf = (RadioGroup) v.findViewById(R.id.radioGroupPerf);
        final RadioGroup rGroupBerm = (RadioGroup) v.findViewById(R.id.radioGroupBerm);
        final RadioGroup rGroupSDA = (RadioGroup) v.findViewById(R.id.radioGroupSDA);
        final RadioGroup rGroupIDV = (RadioGroup) v.findViewById(R.id.radioGroupIDV);
        final RadioGroup rGroupAEODA = (RadioGroup) v.findViewById(R.id.radioGroupAEODA);
        final RadioGroup rGroupFDRC = (RadioGroup) v.findViewById(R.id.radioGroupFDRC);
        final EditText eTLimG  = (EditText) v.findViewById(R.id.editTextLimG);
        final EditText eTLimL  = (EditText) v.findViewById(R.id.editTextLimL);


        if(circexternas1.size()>= 1){


        }

        Button btnSegCircExt1 = (Button) v.findViewById(R.id.ButtonSegCircExt1);
        btnSegCircExt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rGroupTipVia.getCheckedRadioButtonId() == -1 || eTSemSnVias.getText().toString().equals("")
                        || rGroupViaT.getCheckedRadioButtonId() == -1 || rGroupPlant.getCheckedRadioButtonId() == -1
                        || rGroupPerf.getCheckedRadioButtonId() == -1 || rGroupBerm.getCheckedRadioButtonId() == -1
                        || rGroupSDA.getCheckedRadioButtonId() == -1 || rGroupIDV.getCheckedRadioButtonId() == -1
                        || rGroupAEODA.getCheckedRadioButtonId() == -1 || rGroupFDRC.getCheckedRadioButtonId() == -1
                        || eTLimG.getText().toString().equals("") || eTLimL.getText().toString().equals("") ) {
                    Toast.makeText(parentActivity, "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
                } else {
                    int iTipVia = rGroupTipVia.indexOfChild(rGroupTipVia.findViewById(rGroupTipVia.getCheckedRadioButtonId()));
                    int iGroupViaT = rGroupViaT.indexOfChild(rGroupViaT.findViewById(rGroupViaT.getCheckedRadioButtonId()));
                    int iGroupPlant = rGroupPlant.indexOfChild(rGroupPlant.findViewById(rGroupPlant.getCheckedRadioButtonId()));
                    int iGroupPerf = rGroupPerf.indexOfChild(rGroupPerf.findViewById(rGroupPerf.getCheckedRadioButtonId()));
                    int iGroupBerm = rGroupBerm.indexOfChild(rGroupBerm.findViewById(rGroupBerm.getCheckedRadioButtonId()));
                    int iGroupSDA = rGroupSDA.indexOfChild(rGroupSDA.findViewById(rGroupSDA.getCheckedRadioButtonId()));
                    int iGroupIDV = rGroupIDV.indexOfChild(rGroupIDV.findViewById(rGroupIDV.getCheckedRadioButtonId()));
                    int iGroupAEODA = rGroupAEODA.indexOfChild(rGroupAEODA.findViewById(rGroupAEODA.getCheckedRadioButtonId()));
                    int iGroupFDRC = rGroupFDRC.indexOfChild(rGroupFDRC.findViewById(rGroupFDRC.getCheckedRadioButtonId()));







                    //CircExternas1 CE1 = new CircExternas1();

                    parentActivity.goToCircExt2Fragment();
                }




            }
        });


        Button btnAntCircExt1 = (Button) v.findViewById(R.id.ButtonAntCircExt1);
        btnAntCircExt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToForm1Fragment();
            }
        });


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCircExt1Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCircExt1Listener) {
            mListener = (OnCircExt1Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCircExt1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCircExt1Listener {
        // TODO: Update argument type and name
        void goToCircExt1Fragment();
    }

}
