package com.example.sheetstoroomdatabase;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String url ="https://script.googleusercontent.com/macros/echo?user_content_key=MtuL68RNsfDnyf3G3PjjKYMgrzvR4UeI6RXQUReNw8eurBvp7eAvmoGM47H-UQqSTAJ8jmM2XjY7m8k3Py15kQn9NFWhYbGAOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwU1r0_LBkFX_kqN-akhoE9ntAE58kipWMlsc0i6mm8k36QRMr5G0ZBvPvhD408pptW5y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";

    RecyclerView recyclerView;

    DataViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gatherData();
    }

    private void gatherData() {
        final List<Sheet1> listLiveData = viewModel.getList();

        Toast.makeText(this, ""+listLiveData.size(), Toast.LENGTH_SHORT).show();
        if (listLiveData.size()==0){

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Getting Data...");
            dialog.setMessage("Please wait...");
            dialog.show();
            dialog.setCancelable(true);
            final StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("log",""+response);
                    try {
                        JSONObject first = new JSONObject(response.toString());
                        JSONArray arr = first.getJSONArray("Sheet1");

                        for (int i = 0; i <= arr.length(); i++) {
                            JSONObject object = arr.getJSONObject(i);

// String imgurl = object.getString("userImageURL");
                            String adhaar = object.getString("AADHAR_NO");
                            String name = object.getString("STUDENT_NAME");
                            String image = object.getString("IMAGE");
                            String date = object.getString("DATE_OF_BIRTH");
                            String address = object.getString("ADDRESS");
                            String email = object.getString("EMAIL_ID");

                            Sheet1 obj = new Sheet1();
                            obj.setAADHARNO(adhaar);
                            obj.setSTUDENTNAME(name);
                            obj.setADDRESS(address);
                            obj.setIMAGE(image);
                            obj.setDATEOFBIRTH(date);
                            obj.setEMAILID(email);

                            viewModel.insertData(obj);
                            listLiveData.add(obj);
                        }
                        Toast.makeText(MainActivity.this, "Data Fetched "+listLiveData.size()+" Fields Saved", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("log",""+listLiveData.size());
                    recyclerView.setAdapter(new DataAdapter(MainActivity.this,listLiveData));
                    dialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("log",""+error.getMessage());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
        else {
            viewModel.getListLiveData().observe(this, new Observer<List<Sheet1>>() {
                @Override
                public void onChanged(@Nullable List<Sheet1> sheet1s) {
                    recyclerView.setAdapter(new DataAdapter(MainActivity.this,sheet1s));
                }
            });
        }
    }
}
