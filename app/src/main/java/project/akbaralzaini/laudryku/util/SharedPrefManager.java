package project.akbaralzaini.laudryku.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_LAUNDRYKU_APP = "laundryku";

    public static final String SP_NAMA = "spNama";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_ID = "spId";
    public static final String SP_ROLE = "spRole";
    public static final String SP_PASSWORD = "password";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_LAUNDRYKU_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public String getSpId(){
        return sp.getString(SP_ID,"");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public String getRole(){
        return sp.getString(SP_ROLE,"");
    }

    public String getSpPassword(){ return sp.getString(SP_PASSWORD,""); }


}
