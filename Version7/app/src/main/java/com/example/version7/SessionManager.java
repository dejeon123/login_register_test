package com.example.version7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.HashMap;

public class SessionManager
{
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
//    private static final String PREF_NAME = "AndroidHiveLogin";
//
//    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String PREF_NAME = "LOGIN";
    private static final String KEY_IS_LOGGEDIN = "IS_LOGIN";
    private static final String NAME = "NAME";
    private static final String EMAIL = "EMAIL";


    public SessionManager(Context context)
    {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public void createSession(String username, String email){
        editor.putBoolean(KEY_IS_LOGGEDIN,true);
        editor.putString(NAME, username);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public void checkLogin(){
        if (this.isLoggedIn()){
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){

        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, pref.getString(NAME, null));
        user.put(EMAIL, pref.getString(EMAIL, null));
        return user;
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }

}