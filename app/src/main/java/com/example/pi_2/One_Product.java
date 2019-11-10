package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class One_Product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one__product);
        UpdateText();
        getSupportActionBar().hide();
    }

    public Activity getActivity(){
        return this;
    }

    @Override
    public void onResume() {
        super.onResume();
        Button botonEdit= (Button) findViewById(R.id.btnEdit);
        final Button botonEnviar= (Button) findViewById(R.id.btnSend);
        final ImageView imagen= (ImageView) findViewById(R.id.imagen2);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView description = (TextView) findViewById(R.id.descripcion);
        final EditText name_edit=(EditText) findViewById(R.id.editname);
        final EditText descripction_edit=(EditText) findViewById(R.id.editdescripcion);
        botonEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                botonEnviar.setVisibility(View.VISIBLE);
                description.setVisibility(View.GONE);
                name.setVisibility(View.GONE);
                name_edit.setVisibility(View.VISIBLE);
                descripction_edit.setVisibility(View.VISIBLE);
                imagen.setVisibility(View.GONE);
            }
        });


        botonEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                botonEnviar.setVisibility(View.GONE);
                description.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                name_edit.setVisibility(View.GONE);
                descripction_edit.setVisibility(View.GONE);
                imagen.setVisibility(View.VISIBLE);


                EditText txtUsername = (EditText) findViewById(R.id.editname);
                EditText txtPassword = (EditText) findViewById(R.id.editdescripcion);


                String name = txtUsername.getText().toString();
                String description = txtPassword.getText().toString();
                Map<String, String> message = new HashMap<>();
                message.put("name", name);
                message.put("description", description);


                JSONObject jsonMessage = new JSONObject(message);

                // 4. Sending json message to Server
                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.PUT,
                        "http://10.0.2.2:8080/products2/"+ getIntent().getExtras().get("product_id").toString(),
                        jsonMessage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //TODO
                                try {
                                    UpdateText();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                if( error instanceof AuthFailureError){
                                }
                                else {
                                }
                            }
                        }
                );
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(request);
            }
        });






        Button BotonBorrar= (Button) getActivity().findViewById(R.id.btnDelete);
        BotonBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Map<String, String> message = new HashMap<>();
                JSONObject jsonMessage = new JSONObject(message);

                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.DELETE,
                        "http://10.0.2.2:8080/delete_product/" + getIntent().getExtras().get("product_id").toString(),
                        jsonMessage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //TODO
                                try {
                                    Intent intent = new Intent(getActivity(), MyProducts.class);
                                    startActivity(intent);

                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                if( error instanceof AuthFailureError){
                                }
                                else {
                                }
                            }
                        }
                );
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(request);
            }
        });

    }


public void UpdateText(){
    final String id = getIntent().getExtras().get("product_id").toString();
    String url = "http://10.0.2.2:8080/product_by_id/<xid>";
    url = url.replace("<xid>", id);
    RequestQueue queue = Volley.newRequestQueue(this);
    JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        TextView name = (TextView) findViewById(R.id.name);
                        TextView description = (TextView) findViewById(R.id.descripcion);
                        TextView name_edit = (TextView) findViewById(R.id.editname);
                        TextView description_edit = (TextView) findViewById(R.id.editdescripcion);
                        String name1 = response.getString("name");
                        String description1 = response.getString("description");
                        name.setText(name1);
                        description.setText(description1);
                        description_edit.setText(description1);
                        name_edit.setText(name1);
                        ImageView imagen = findViewById(R.id.imagen2);
                        Picasso.get().load(response.getString("url")).into(imagen);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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





