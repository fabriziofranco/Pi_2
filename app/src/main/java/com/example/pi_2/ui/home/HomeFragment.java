package com.example.pi_2.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

    Spinner spinner;
    private View root;
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    JSONArray data_global;

    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
    
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = root.findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeAdapter(data_global, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        //Lista Desplegable
        spinner = (Spinner)root.findViewById(R.id.categories_spinner);


        String [] Opciones = {"Todo", "Tecnologia", "Hogar", "Ropa", "Libros"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.Yellow), PorterDuff.Mode.SRC_ATOP);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        String url = "http://10.0.2.2:8080/products";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            data_global = response.getJSONArray("data");
                            mAdapter = new HomeAdapter(data_global, getActivity());
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