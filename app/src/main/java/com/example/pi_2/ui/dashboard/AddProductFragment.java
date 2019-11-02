package com.example.pi_2.ui.dashboard;

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

import com.example.pi_2.R;

public class AddProductFragment extends Fragment {

    private AddProductModel addProductModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addProductModel =
                ViewModelProviders.of(this).get(AddProductModel.class);
        View root = inflater.inflate(R.layout.fragment_add_product, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        addProductModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}