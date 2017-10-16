package pt.rrochaua.ua_beav.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;

public class Menu_fragment extends Fragment {

    MainActivity parentActivity;

    private OnMenu_fragmentListener mListener;

    public Menu_fragment() {

    }

    public static Menu_fragment newInstance() {
        Menu_fragment fragment = new Menu_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();
            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_menu_fragment, container, false);

        System.out.println("########## TIPO: " + parentActivity.getMenuButtons());
        System.out.println("########## CORES: " + parentActivity.getMenuButtons2());
        LinearLayout  ll = (LinearLayout) v.findViewById(R.id.llButtons);
        ArrayList<Integer> buttons = parentActivity.getMenuButtons();
        for (int i =0; i< buttons.size(); i++){
            switch (buttons.get(i)){
                case 0:
                    Button btn = new Button(v.getContext());
                    btn.setId(i);
                    btn.setText("MForm1");
                    if(parentActivity.getMenuButtons2().get(i)==1){
                        btn.setTextColor(Color.GREEN);
                    }else{
                        btn.setTextColor(Color.RED);
                    }
                    ll.addView(btn);
                    break;
                case 1:
                    Button btn2 = new Button(v.getContext());
                    btn2.setId(i);
                    btn2.setText("Veiculo" + i);
                    if(parentActivity.getMenuButtons2().get(i)==1){
                        btn2.setTextColor(Color.GREEN);
                    }else{
                        btn2.setTextColor(Color.RED);
                    }
                    ll.addView(btn2);
                    break;
            }
        }

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToMenu_fragmentFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMenu_fragmentListener) {
            mListener = (OnMenu_fragmentListener) context;
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


    public interface OnMenu_fragmentListener {
        // TODO: Update argument type and name
        void goToMenu_fragmentFragment();
    }
}
