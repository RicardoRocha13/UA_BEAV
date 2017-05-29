package pt.rrochaua.ua_beav.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


public class CondIntSem extends Fragment {
    MainActivity parentActivity;

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

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cond_int_sem, container, false);

        Button btnSegCondIntSem = (Button) v.findViewById(R.id.ButtonSegCondIntSem);
        btnSegCondIntSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToFotoEsquemaFragment();
            }
        });


        Button btnAntCondIntSem = (Button) v.findViewById(R.id.ButtonAntCondIntSem);
        btnAntCondIntSem.setOnClickListener(new View.OnClickListener() {
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
