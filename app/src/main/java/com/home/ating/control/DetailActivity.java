package com.home.ating.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String ip = intent.getExtras().getString("ip");
        String user = intent.getExtras().getString("user");

        TextView ipTv = (TextView)this.findViewById(R.id.ipText);
        TextView userTv = (TextView)this.findViewById(R.id.userText);

        ipTv.setText(ip);
        userTv.setText(user);

        Button sendBtn = (Button) this.findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)((View)v.getParent()).findViewById(R.id.command);
                TextView ipTv = (TextView)((View) v.getParent()).findViewById(R.id.ipText);
                String command = editText.getText().toString();
                String ip = ipTv.getText().toString();

                StringBuilder sb = new StringBuilder();
                sb.append("[\"");
                sb.append(ip);
                sb.append("\",\"");
                sb.append(command);
                sb.append("\"]");

                final String message = sb.toString();
                Log.e("command", message);
                Thread thread = new Thread(){
                    @Override
                    public void run(){
                        Config.output.print(message);
                    }
                };
                thread.start();
            }
        });
    }


}
