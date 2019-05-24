package com.example.jolvalre.beworker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageButton;

import com.example.jolvalre.beworker.fragment.AutreFragment;
import com.example.jolvalre.beworker.fragment.CompleterProfilFragment;
import com.example.jolvalre.beworker.fragment.ProfilFragment;
import com.example.jolvalre.beworker.fragment.SecurityFragment;
import com.example.jolvalre.beworker.fragment.VosContactFragment;
import com.example.jolvalre.beworker.fragment.VotreProfilFragment;

/*
 * cette classe  peermet de gerer facilement les echanges entre
 * le viewpager et le tablayout du main_activity
 * */

public class PagerAdapter2 extends FragmentStatePagerAdapter {
    ImageButton suivant,suivant2, precedant2, suivant3, precedant3,precedant4,suivant4,fab,suivant5,precedant5;

    public PagerAdapter2(FragmentManager fm) {
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
            case 0:frag = new VotreProfilFragment();
                break;
            case 1:frag = new CompleterProfilFragment();
                break;
            case 2:frag = new VosContactFragment();
                break;
            case 3: frag = new AutreFragment();
                break;
            case 4: frag = new SecurityFragment();
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
        return 5;
    }
}

