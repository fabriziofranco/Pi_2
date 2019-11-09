package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static java.security.AccessController.getContext;

public class Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

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
                            String name1 = response.getString("name");
                            String description1 = response.getString("description");
                            name.setText(name1);
                            description.setText(description1);
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
