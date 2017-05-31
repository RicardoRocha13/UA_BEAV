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


public class ConsPass extends Fragment {

    MainActivity parentActivity;

    private OnConsPassListener mListener;


    public ConsPass() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ConsPass newInstance() {
        ConsPass fragment = new ConsPass();
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
        final View v = inflater.inflate(R.layout.fragment_cons_pass, container, false);

        Button btnSegConsPass = (Button) v.findViewById(R.id.ButtonSegConsPass);
        btnSegConsPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToConsPeoFragment();
            }
        });


        Button btnAntConsPass = (Button) v.findViewById(R.id.ButtonAntConsPass);
        btnAntConsPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt2Fragment();
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToConsPassFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnConsPassListener) {
            mListener = (OnConsPassListener) context;
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
    public interface OnConsPassListener {
        // TODO: Update argument type and name
        void goToConsPassFragment();
    }
}