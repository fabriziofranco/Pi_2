package com.example.pi_2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.pi_2.R;

public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    Context mContext;
    Activity mActivity;
    View root_global;
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = root.findViewById(R.id.main_recycler_view);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getProducts();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity= getActivity();
    }

    public void getProducts(){
        String url = "http://10.0.2.2:8080/products";
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            mAdapter = new HomeAdapter(data, getActivity());
                            mRecyclerView.setAdapter(mAdapter);
                            showMessage("Se logro");
                        }catch (JSONException e) {
                            e.printStackTrace();
                            showMessage("Error de JSON");
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