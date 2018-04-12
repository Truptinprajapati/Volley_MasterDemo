package com.example.trupti.myvolleyhasmap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String,String>> iteamlist;
    RecyclerView recyclerView;
    String Data_url,atitle,des,image;
    ProgressDialog progressDialog;
    HashMap<String,String> map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.rvdata);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        iteamlist=new ArrayList<HashMap<String, String>>();
        iteamlist=new ArrayList<>();

        loaddata();


    }

    private void loaddata() {

        Data_url="https://api.myjson.com/bins/1h0v0h";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, Data_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try{

                    JSONObject obj=new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),"data"+response,Toast.LENGTH_SHORT).show();
                    JSONObject data=obj.getJSONObject("response");


                    //JSONObject jsonObject=data.getJSONObject("result");

                   // String succs=data.getString("result");

                     //   if (succs.equals("success")){
                            JSONArray jsonArray=data.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject adata=jsonArray.getJSONObject(i);

                                map=new HashMap<>();

                                atitle=adata.getString("title");
                                map.put("atitle",atitle);

                                des=adata.getString(("description"));
                                map.put("des",des);

                                image=adata.getString("image");
                                map.put("image",image);


                                iteamlist.add(map);

                            }

                            MyAdapter adapter = new MyAdapter(iteamlist, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                            progressDialog.dismiss();


                       // }



                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }

        })
        {
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params=new HashMap<String, String>();
                return params;
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        progressDialog=ProgressDialog.show(MainActivity.this,"plz wait ...",null,true,true);
        progressDialog.setMessage("feaching data view");
        progressDialog.setCancelable(false);


    }


}



