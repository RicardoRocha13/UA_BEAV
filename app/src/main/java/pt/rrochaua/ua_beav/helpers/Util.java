package pt.rrochaua.ua_beav.helpers;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import pt.rrochaua.ua_beav.R;

public class Util {

    public static void switchFragment(FragmentManager fm, String fragTag, Fragment newFrag) {
        //remove old fragment from manager
        if (fm.findFragmentByTag(fragTag) != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(fm.findFragmentByTag(fragTag));
            ft.commit();
        }

        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.frame_container, newFrag, fragTag);
        ft.addToBackStack(fragTag);
        ft.commit();
    }
}





