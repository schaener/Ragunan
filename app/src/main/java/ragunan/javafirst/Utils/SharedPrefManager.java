package ragunan.javafirst.Utils;

import android.content.Context;

import ragunan.javafirst.ui.Main.Home.Home;

public class SharedPrefManager {
    public static final String SP_App = "spTMII";
    public static final String SP_MONEY = "spMoney";
    static android.content.SharedPreferences sp;
    android.content.SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp  = context.getSharedPreferences(SP_App,Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }


    public void saveSPString(String keySP , String value){
        spEditor.putString(keySP , value);
        spEditor.commit();
    }

    public static String getSpMoney() {
        return SP_MONEY;
    }

}
