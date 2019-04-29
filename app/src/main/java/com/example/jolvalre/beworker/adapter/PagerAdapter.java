package com.example.jolvalre.beworker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.jolvalre.beworker.fragment.HomeFragment;
import com.example.jolvalre.beworker.fragment.NotificationFragment;
import com.example.jolvalre.beworker.fragment.PostFragment;
import com.example.jolvalre.beworker.fragment.ProfilFragment;

/*
* cette classe  peermet de gerer facilement les echanges entre
* le viewpager et le tablayout du main_activity
* */

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
    * Cette fonction permet de retourner le bon fragment suivant le nombre passe
     * en parametre. notre tab_view_main a 4 item donc il existe 4 fragment distint possible
     **/
    @Override
    public Fragment getItem(int i) {
        Fragment frag=null;
        switch (i){
            case 0:frag = new HomeFragment();
                break;
            case 1:frag = new PostFragment();
                break;
            case 2:frag = new NotificationFragment();
                break;
            case 3:frag = new ProfilFragment();
                break;
        }
        return frag;
    }
    /**
     * Cette fonctionn retourne le nombre de possibilite que le view_pager_main peut gerer
     * soit 4 dans notre cas vue que le tab_llayout_main a 4 items
     * */
    @Override
    public int getCount() {
        return 4;
    }
}
