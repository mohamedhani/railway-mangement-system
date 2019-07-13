package com.example.mohamed.railwaymangementsystem.controller.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.mohamed.railwaymangementsystem.Fragments.AdminComplaintsFragment;
import com.example.mohamed.railwaymangementsystem.Fragments.TrainLocationFragement;
import com.example.mohamed.railwaymangementsystem.Fragments.TrainSchudlingFragment;
import com.example.mohamed.railwaymangementsystem.Fragments.UserComplaintsFragment;

/**
 * Created by mohamed on 4/10/2019.
 */

public class FragementsAdapters extends FragmentStatePagerAdapter {
    Fragment complmentsFragment ;
   public FragementsAdapters(FragmentManager fragmentManager , String  email)
   {
       super(fragmentManager);
       if(email.contains("admin@admin.com"))
       {
           complmentsFragment = new AdminComplaintsFragment();
       }
       else
       {
           complmentsFragment = new UserComplaintsFragment();

       }



   }
    private final int FRAGMENTS_COUNT = 3;
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new TrainSchudlingFragment();

            case 1:
                return new TrainLocationFragement();
            case 2:
                return  complmentsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENTS_COUNT;
    }
}
