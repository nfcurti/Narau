package com.example.nicolas.narau.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Nicolas on 1/4/2017.
 */
public class prof implements Serializable {
    public int idprof;
    public int id;
    public String rubro;
    public String descr;
    public String info;

    public prof(JSONObject json){
        try{
            this.idprof = json.getInt("idprof");
            this.id = json.getInt("id");
            this.rubro = json.getString("rubro");
            this.descr = json.getString("descr");
            this.info = json.getString("info");
        }catch(JSONException e){
            Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
        }

    }

    public prof(String info, String descr, String rubro, int id, int idprof) {
        this.info = info;
        this.descr = descr;
        this.rubro = rubro;
        this.id = id;
        this.idprof = idprof;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdprof() {
        return idprof;
    }

    public void setIdprof(int idprof) {
        this.idprof = idprof;
    }


}
