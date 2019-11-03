package com.example.pi_2.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pi_2.MainActivity;
import com.example.pi_2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public JSONArray elements;
    private Context context;

    public HomeAdapter(JSONArray elements, Context context){
        this.elements = elements;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView first_line;
        RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            first_line = itemView.findViewById(R.id.element_view2_first_line);
            container = itemView.findViewById(R.id.element_view2_container);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_view,parent, true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        try {
            JSONObject element = elements.getJSONObject(position);
            String name = element.getString("name");
            //final String id = element.getString("id");
            holder.first_line.setText(name);
            holder.container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(context, MainActivity.class);
                    ///intent.putExtra("category_id",1);
                    //context.startActivity(intent);
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