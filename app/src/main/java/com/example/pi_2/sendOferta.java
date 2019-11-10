package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class sendOferta extends AppCompatActivity{
    RecyclerView mRecyclerView;
    Button boton;
    RecyclerView.Adapter mAdapter;
    JSONArray data_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendoferta);
        getSupportActionBar().hide();
        boton = findViewById(R.id.btn1);
        mRecyclerView = findViewById(R.id.main_recycler_view_products);
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
        String url = "http://10.0.2.2:8080/myproducts/"+ getIntent().getExtras().get("userId").toString();
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
                            final String userID = getIntent().getExtras().get("userId").toString();
                            final String ownerID = getIntent().getExtras().get("ownerId").toString();
                            final String productID = getIntent().getExtras().get("product_id").toString();
                            mAdapter = new sendOfertaAdapter(data_global,getActivity(), userID, ownerID, productID, boton, getActivity());
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
