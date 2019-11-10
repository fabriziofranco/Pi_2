package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class One_Offer extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    JSONArray data_global;
    RecyclerView mRecyclerView2;
    RecyclerView.Adapter mAdapter2;
    JSONArray data_global2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one__offer);
        getSupportActionBar().hide();
        mRecyclerView = findViewById(R.id.main_recycler_solicitado);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OneOfferAdapter(data_global, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView2 = findViewById(R.id.main_recycler_ofrecidos);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mAdapter2 = new OneOfferAdapter(data_global2, this);
        mRecyclerView2.setAdapter(mAdapter2);
    }



    public Activity getActivity(){
        return this;
    }



    @Override
    protected void onResume() {
        super.onResume();
        Fill_views();


        Button aceptar = (Button) findViewById(R.id.Aceptx);
        aceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AceptarOferta();
            }
        });


        Button Rechazar = (Button) findViewById(R.id.Decline);
        Rechazar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RechazarOferta();
            }
        });


    }

    private void Fill_views() {
        String url = "http://10.0.2.2:8080/requeridobytransaction/"+ getIntent().getExtras().get("offer_id").toString();
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
                            mAdapter = new OneOfferAdapter(data_global,getActivity());
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


        String url2= "http://10.0.2.2:8080/ofrecidosbytransaction/"+ getIntent().getExtras().get("offer_id").toString();
        RequestQueue queue2 = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                Request.Method.GET,
                url2,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            data_global2 = response.getJSONArray("data");
                            mAdapter2 = new OneOfferAdapter(data_global2,getActivity());
                            mRecyclerView2.setAdapter(mAdapter2);
                            mAdapter2.notifyDataSetChanged();
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
        queue2.add(jsonObjectRequest2);
    }


    private void AceptarOferta(){

        String url = "http://10.0.2.2:8080/aceptaroferta/"+getIntent().getExtras().get("offer_id").toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            Intent intent = new Intent(getActivity(), MyOffers.class);
                            String user_id = getIntent().getExtras().get("user_id").toString();
                            intent.putExtra("user_id",user_id);
                            startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(request);
    }

    private void RechazarOferta(){

        String url = "http://10.0.2.2:8080/rechazaroferta/"+getIntent().getExtras().get("offer_id").toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            Intent intent = new Intent(getActivity(), MyOffers.class);
                            startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(request);
    }

}
