package com.home.ating.control;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ating on 16/9/15.
 */
public class Fragment1 extends ListFragment {


    private List<Computer> mData = null;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getData();
//        ArrayAdapter<Computer> adapter = new MyAdapter(getActivity(), mData);
//
//        setListAdapter(adapter);

        /*data = new ArrayList<String>();
        for(int i = 5;i < 11; i++){
            data.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data);

        setListAdapter(adapter);
        */


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_pc_list, container, false);
    }

    private void getData(){
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    Socket socket = new Socket(Config.SERVER_IP, Config.SERVER_PORT);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream out = new PrintStream(socket.getOutputStream());
                    Config.input = in;
                    Config.output = out;

                    out.print(Config.KEY);

                    Thread.sleep(100);

                    out.print("getinfo");

                    String json = in.readLine();
                    Type type = new TypeToken<ArrayList<Computer>>(){}.getType();
                    List<Computer> data = new Gson().fromJson(json, type);

                    final ArrayAdapter<Computer> adapter = new MyAdapter(getActivity(), data);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setListAdapter(adapter);
                        }
                    });
                }catch(IOException e){
                    Log.e("getData", e.getMessage());
                }catch(InterruptedException e){
                    Log.e("getData", e.getMessage());
                }
            }
        };

        thread.start();

//        List<Computer> data = new ArrayList<Computer>();
//        for(int i = 0; i < 5; i++){
//            data.add(new Computer(String.valueOf(i), String.valueOf(i)));
//        }
//        return data;

    }
}
