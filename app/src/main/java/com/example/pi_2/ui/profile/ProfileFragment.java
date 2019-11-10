package com.example.pi_2.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pi_2.MyOffers;
import com.example.pi_2.MyProducts;
import com.example.pi_2.Producto;
import com.example.pi_2.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView usuario= root.findViewById(R.id.usuario_profile);
        String toset="Qué deseas consultar "+ getActivity().getIntent().getExtras().get("username").toString() +" ?";
        usuario.setText(toset);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Button boton_productos= (Button) getActivity().findViewById(R.id.boton_productos);
        boton_productos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyProducts.class);
                String user_id = getActivity().getIntent().getExtras().get("user_id").toString();
                String username = getActivity().getIntent().getExtras().get("username").toString();
                intent.putExtra("user_id", user_id);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

        Button boton_ofertas= (Button) getActivity().findViewById(R.id.boton_ofertas);
        boton_ofertas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyOffers.class);
                String user_id = getActivity().getIntent().getExtras().get("user_id").toString();
                String username = getActivity().getIntent().getExtras().get("username").toString();
                intent.putExtra("user_id", user_id);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });

    }


}