package com.example.pi_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class sendOfertaAdapter extends RecyclerView.Adapter<sendOfertaAdapter.ViewHolder> {
    public static final int MULTI_SELECTION = 2;
    public static final int SINGLE_SELECTION = 1;
    int index;
    Button botoncito;
    Context context2;
    String userfromID, usertoID, productID;
    public JSONArray elements;
    private Context context;
    String lista_ids[];

    public sendOfertaAdapter( JSONArray elements, Context context, String uno, String dos, String tres, Button boton1, Context contexto){

        userfromID = uno;
        usertoID = dos;
        botoncito = boton1;
        context2 = contexto;
        productID = tres;
        this.elements = elements;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first_line;
        RelativeLayout container;
        ImageView iamgen;
        public ViewHolder(View itemView) {
            super(itemView);
            first_line = itemView.findViewById(R.id.element_view2_first_line);
            container = itemView.findViewById(R.id.element_view2_container);
            iamgen = itemView.findViewById(R.id.element_view2_image);

        }
    }

    @NonNull
    @Override
    public sendOfertaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        index = 0;
        lista_ids = new String [10];
        botoncito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> message = new HashMap<>();
                message.put("user_from_id", userfromID);
                message.put("user_to_id", usertoID);
                message.put("id_requeridos", productID);

                Map<String, String[]> lista2 = new HashMap<>();
                lista2.put("ids_enviados", lista_ids);

                // 3. Converting the message object to JSON string (jsonify)
                JSONObject jsonMessage = new JSONObject(message);
                try {
                    jsonMessage.accumulate("ids_enviados", lista_ids);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // 4. Sending json message to Server
                JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://10.0.2.2:8080/createT",
                        jsonMessage,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //TODO
                                try {
                                    System.out.println("EXITO");

                                }
                                catch (Exception e) {
                                    System.out.println("FRACAZO");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                System.out.println(error.getMessage());
                            }
                        }
                );
                RequestQueue queue = Volley.newRequestQueue(context2);
                queue.add(request);

            }
        });
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_view,parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull final sendOfertaAdapter.ViewHolder holder, int position) {
        try {
            holder.setIsRecyclable(false);
            final JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name");

            holder.first_line.setText(name);
            Picasso.get().load(element.getString("url")).into(holder.iamgen);
            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        holder.container.setBackgroundColor(0xFFFF00);
                    try {
                        final String idProductoPropio = element.getString("id");
                        lista_ids[index] = idProductoPropio;
                        for(int i = 0;i < index+1; i++){
                            System.out.println(lista_ids[i]);
                        }
                        index++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(elements!=null)
            return elements.length();
        else
            return 0;
    }
}