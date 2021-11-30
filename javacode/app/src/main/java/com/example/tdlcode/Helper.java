package com.example.tdlcode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import androidx.appcompat.app.AlertDialog;

public class Helper {

    public static void PutData(Context context, String key, String value)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("ssshhhh!!!", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(key, value);
            editor.commit();



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String GetData(Context context, String key)
    {
        try
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("ssshhhh!!!", Context.MODE_PRIVATE);
            return sharedpreferences.getString(key, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
