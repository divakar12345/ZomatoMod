package com.example.zomatomod.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zomatomod.R;

public class PreferenceConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeEntityId(int status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("entityId", status);
        editor.commit();
    }

    public int readEntityId() {
        return sharedPreferences.getInt("entityId", 7);
    }

    public void writeEntityType(String userAvatar) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userAvatar", userAvatar);
        editor.commit();
    }

    public String readEntityType() {
        return sharedPreferences.getString("entityType", "city");
    }

}
