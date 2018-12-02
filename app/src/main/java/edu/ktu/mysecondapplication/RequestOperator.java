package edu.ktu.mysecondapplication;

import android.graphics.ColorSpace;
import android.view.Display;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RequestOperator extends Thread{

    public interface RequestOperatorListener{
        void success (ArrayList<ModelPost> publication);
        void failed (int responseCode);
    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener(RequestOperatorListener listener) {
        this.listener = listener;
    }

    @Override
    public void run(){
        super.run();
        try{
            ArrayList<ModelPost> publication = request();

            if(publication!=null)
                success(publication);
            else
                failed(responseCode);
        }
        catch (IOException e){
            failed(-1);
        }
        catch (JSONException e){
            failed(-2);
        }
    }

    private ArrayList<ModelPost> request() throws IOException, JSONException{
        URL obj = new URL("http://jsonplaceholder.typicode.com/posts");

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");

        responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        InputStreamReader streamReader;

        if (responseCode == 200){
            streamReader = new InputStreamReader(con.getInputStream());
        }
        else{
            streamReader =new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while((inputLine = in.readLine())!= null){
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        if(responseCode == 200)
            return parsingJsonArray(response.toString());
        else
            return null;
    }

    public ArrayList<ModelPost> parsingJsonArray(String response) throws  JSONException{
        JSONArray array = new JSONArray(response);
        ArrayList<ModelPost> post = new ArrayList<>();

        if(array !=null){
            for(int i=0;i<9;i++){
                JSONObject object = array.getJSONObject(i);
                post.add(new ModelPost(Integer.parseInt(object.getString("id")),Integer.parseInt(object.getString("userId")),object.getString("title"),object.getString("body")));
            }
        }
        return post;
    }
    /*public ModelPost parsingJsonObject(String response) throws JSONException{
        JSONObject object = new JSONObject(response);
        ModelPost post = new ModelPost();

        post.setId(object.optInt("id",0));
        post.setUserId(object.optInt("userId",0));

        post.setTitle(object.getString("title"));
        post.setBodyText(object.getString("body"));

        return post;
    }*/

    private void failed(int code){
        if(listener!=null)
            listener.failed(code);
    }

    private void success(ArrayList<ModelPost> publication){
        if(listener!=null){
            listener.success(publication);
            CountView.setString(Integer.toString(publication.size()));
        }
    }
}
