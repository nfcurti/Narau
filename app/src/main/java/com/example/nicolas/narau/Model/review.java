package com.example.nicolas.narau.Model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Nicolas on 1/4/2017.
 */
public class review implements Serializable {
    public int idreview;
    public int idsender;
    public int idreceiver;
    public String imgurl;
    public String reviewtext;

    public review(JSONObject json){
        try{
            this.idreview = json.getInt("idreview");
            this.idsender = json.getInt("idsender");
            this.imgurl = json.getString("img");
            this.idreceiver = json.getInt("idreceiver");
            this.reviewtext = json.getString("reviewtext");
        }catch(JSONException e){
            Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
        }

    }

    public int getIdreview() {
        return idreview;
    }

    public void setIdreview(int idreview) {
        this.idreview = idreview;
    }

    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int idsender) {
        this.idsender = idsender;
    }

    public int getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(int idreceiver) {
        this.idreceiver = idreceiver;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getReviewtext() {
        return reviewtext;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    public review(int idreview, int idsender, int idreceiver, String imgurl, String reviewtext) {
        this.idreview = idreview;
        this.idsender = idsender;
        this.idreceiver = idreceiver;
        this.imgurl = imgurl;
        this.reviewtext = reviewtext;
    }

}
