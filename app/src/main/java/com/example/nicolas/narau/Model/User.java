package com.example.nicolas.narau.Model;

/**
 * Created by Nicolas on 1/4/2017.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by juancurti on 3/10/17.
 */

public class User implements Serializable {
    public int id;
    public String name;
    public String img;
    public String facebook_id;
    //public String token;

    public User(JSONObject json){
        try{
            this.id = json.getInt("id");
            this.name = json.getString("username");
            this.img = json.getString("img");
            this.facebook_id = json.getString("facebook_id");
        }catch(JSONException e){
            Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
        }

    }

    public User(int id, String name, String img, String facebook_id) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.facebook_id = facebook_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getimg() {
        return img;
    }

    public void setimg(String img) {
        this.img = img;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

}
