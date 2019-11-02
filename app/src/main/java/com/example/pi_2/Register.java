package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public Activity getActivity(){
        return this;
    }



    public void Registrarse(View view) {

        // 1. Getting username and password inputs from view
        EditText txtUsername = (EditText) findViewById(R.id.r_usuario);
        EditText txtPassword = (EditText) findViewById(R.id.r_Password);
        EditText txtEmail = (EditText) findViewById(R.id.r_mail);
        EditText txtName = (EditText) findViewById(R.id.r_Name);
        EditText txtLastname = (EditText) findViewById(R.id.r_Apellido);

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        String email = txtEmail.getText().toString();
        String name = txtName.getText().toString();
        String lastname = txtLastname.getText().toString();

        // 2. Creating a message from user input data
        Map<String, String> message = new HashMap<>();
        message.put("username", username);
        message.put("password", password);
        message.put("email", email);
        message.put("name", name);
        message.put("lastname", lastname);

        // 3. Converting the message object to JSON string (jsonify)
        JSONObject jsonMessage = new JSONObject(message);

        // 4. Sending json message to Server
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2:8080/register",
                jsonMessage,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO
                        try {
                            Intent intent = new Intent(getActivity(), Login.class);
                            startActivity(intent);
                            showMessage("Registro con éxito");

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            showMessage(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if( error instanceof AuthFailureError){
                            showMessage("Email ya registrado, intente con otro");
                        }
                        else {
                            showMessage(error.getMessage());
                        }
                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

















}
