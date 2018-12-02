package edu.ktu.mysecondapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener{

    private Button sendRequestButton;
    private TextView title;
    private TextView bodyText;
    private ListView mylist;
    private ModelPostAdapter adapter;
    private Context context = this;
    private String count;


    ArrayList<ModelPost> publication= new ArrayList<>();
    private IndicatingView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivitydesign);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);

        indicator = (IndicatingView) findViewById(R.id.generated_graphic);
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };
    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();
        setIndicatorStatus(IndicatingView.WAITING);
    }

    public void updatePublication(){
        mylist = (ListView) findViewById(R.id.listView);
        adapter = new ModelPostAdapter(this, publication);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publication != null) {
                    mylist.setAdapter(adapter);
                    count = Integer.toString(publication.size());
                }

            }
        });

    }

    @Override
    public void success(ArrayList<ModelPost> publication){
        this.publication= publication;
        updatePublication();
        setIndicatorStatus(IndicatingView.SUCCESS);
        CountView.setString(count);
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
        setIndicatorStatus(IndicatingView.FAILED);
    }

    public void setIndicatorStatus(final int status){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }
    public String GetCount(){
        return count;
    }


}
