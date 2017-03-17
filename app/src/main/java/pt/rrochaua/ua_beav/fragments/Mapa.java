package pt.rrochaua.ua_beav.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

import pt.rrochaua.ua_beav.MainActivity;
import pt.rrochaua.ua_beav.R;


//public class Mapa extends android.app.Fragment implements OnMapReadyCallback {


public class Mapa extends Fragment {

    MainActivity parentActivity;
    private OnMapaListener mListener;
    //private GoogleMap mMap;

    public Mapa() {
        // Required empty public constructor
    }


    public static Mapa newInstance() {
        Mapa frag = new Mapa();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity) this.getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //parentActivity.setContentView();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/

        final View v = inflater.inflate(R.layout.fragment_mapa, container, false);
/*
        Button btnRMenu = (Button) v.findViewById(R.id.btnRMenu);
        btnRMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.goToMenuFragment();
            }
        });


*/

        return v;
    }



/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
*/


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.goToMapaFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMapaListener) {
            mListener = (OnMapaListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMapaListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMapaListener {
        // TODO: Update argument type and name
        void goToMapaFragment();
    }
}
