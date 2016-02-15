package codepath.com.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Patrick on 2/14/2016.
 */
public class Articles implements Serializable{
    String webUrl;
    String headline;
    String thumbNail;

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public String getWebUrl() {
        return webUrl;
    }



    public Articles(JSONObject jsonObject){

        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if(multimedia.length() > 0){
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbNail = "http://nytimes.com/" + multimediaJson.getString("url");
            }else{
                this.thumbNail = "";
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Articles> fromJSONArray(JSONArray array){

        ArrayList<Articles> results = new ArrayList<>();
        for (int i=0; i<array.length(); i++){
            try {
                results.add(new Articles(array.getJSONObject(i)));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return results;
    }
}
