package com.example.mohamed.railwaymangementsystem.utiltes;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mohamed on 2/18/2019.
 */

public class GlobalMethods {
    static private Toast toast;
    public static  boolean isEmpty(String input)
    {
        if(input.trim().length()>0)
            return false;
        else
            return true;
    }
    public  static  void createToast ( Context context ,String message)
    {     if(toast!=null)
        {
            toast.cancel();
        }
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }
}
