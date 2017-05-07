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
    public int idprof;
    public String rubro;
    public String descr;
    public String info;
    public String msisdn;


    public User(JSONObject json){
        try{
            if(json.has("id")) {
                this.id = json.getInt("id");
            }
            if(json.has("name")) {
                this.name = json.getString("name");
            }
            if(json.has("img")) {
                this.img = json.getString("img");
            }
            if(json.has("facebook_id")) {
                this.facebook_id = json.getString("facebook_id");
            }
            if(json.has("msisdn")) {
                this.msisdn = json.getString("msisdn");
            }
            if (json.has("idprof")){
                this.idprof = json.getInt("idprof");
                this.id = json.getInt("id");
                this.rubro = json.getString("rubro");
                this.descr = json.getString("descr");
                this.info = json.getString("info");
            }
        }catch(JSONException e){
            Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
        }

    }

    public User(int id, String name, String img, String msisdn,String facebook_id,String info, String descr, String rubro, int idprof) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.msisdn = msisdn;
        this.facebook_id = facebook_id;
        this.info = info;
        this.descr = descr;
        this.rubro = rubro;
        this.idprof = idprof;
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

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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

    public int getIdprof() {
        return idprof;
    }

    public void setIdprof(int idprof) {
        this.idprof = idprof;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
