package pt.rrochaua.ua_beav.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;
import pt.rrochaua.ua_beav.models.CircExternas2;


public class CircExt2 extends Fragment {

    MainActivity parentActivity;

    ArrayList<CircExternas2> circexternas2;
    private OnCircExt2Listener mListener;


    public CircExt2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CircExt2 newInstance() {
        CircExt2 fragment = new CircExt2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        parentActivity = (MainActivity) this.getActivity();

        super.onCreate(savedInstanceState);

        circexternas2 = parentActivity.getcExt2();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_circ_ext2, container, false);

        final RadioGroup rGPis = (RadioGroup) v.findViewById(R.id.radioGroupPis);
        final RadioGroup rGEst = (RadioGroup) v.findViewById(R.id.radioGroupEst);
        final RadioGroup rGObs = (RadioGroup) v.findViewById(R.id.radioGroupObs);
        final RadioGroup rGCondAd = (RadioGroup) v.findViewById(R.id.radioGroupCondAd);
        final RadioGroup rGMarPav = (RadioGroup) v.findViewById(R.id.radioGroupMarPav);
        final RadioGroup rGSinLum = (RadioGroup) v.findViewById(R.id.radioGroupSinLum);
        final RadioGroup rGSin = (RadioGroup) v.findViewById(R.id.radioGroupSin);
        final RadioGroup rGLum = (RadioGroup) v.findViewById(R.id.radioGroupLum);
        final RadioGroup rGFatAt = (RadioGroup) v.findViewById(R.id.radioGroupFatAt);




        if(circexternas2.size()>= 1){


        }


        Button btnSegCircExt2 = (Button) v.findViewById(R.id.ButtonSegCircExt2);
        btnSegCircExt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToNatAciFragment();
            }
        });


        Button btnAntCircExt2 = (Button) v.findViewById(R.id.ButtonAntCircExt2);
        btnAntCircExt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCircExt1Fragment();
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCircExt2Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCircExt2Listener) {
            mListener = (OnCircExt2Listener) context;
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
    public interface OnCircExt2Listener {
        // TODO: Update argument type and name
        void goToCircExt2Fragment();

    }
}
