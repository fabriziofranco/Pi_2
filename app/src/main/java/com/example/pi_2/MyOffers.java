package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyOffers extends AppCompatActivity {

    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    JSONArray data_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        getSupportActionBar().hide();
        mRecyclerView = findViewById(R.id.main_recycler_view_offers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyProductsAdapter(data_global, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public Activity getActivity(){
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();
        String url = "http://10.0.2.2:8080/myoffers/"+ getIntent().getExtras().get("user_id").toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            data_global = response.getJSONArray("data");
                            mAdapter = new MyOffersAdapter(data_global,getActivity());
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
