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


public class CondInt2 extends Fragment {
    MainActivity parentActivity;

    private OnCondInt2Listener mListener;


    public CondInt2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CondInt2 newInstance() {
        CondInt2 fragment = new CondInt2();
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
        final View v = inflater.inflate(R.layout.fragment_form1, container, false);

        Button btnSeg = (Button) v.findViewById(R.id.ButtonSeg);
        btnSeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToConsPassFragment();
            }
        });


        Button btnAnt = (Button) v.findViewById(R.id.ButtonAnt);
        btnAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToCondInt1Fragment();
            }
        });

        return inflater.inflate(R.layout.fragment_cond_int2, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToCondInt2Fragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCondInt2Listener) {
            mListener = (OnCondInt2Listener) context;
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
    public interface OnCondInt2Listener {
        // TODO: Update argument type and name
        void goToCondInt2Fragment();
    }
}
