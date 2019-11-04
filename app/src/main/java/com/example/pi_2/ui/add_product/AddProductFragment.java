package com.example.pi_2.ui.add_product;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pi_2.R;
import com.example.pi_2.ui.add_product.AddProductModel;

public class AddProductFragment extends Fragment {

    private AddProductModel addProductModel;

    ImageButton camera;
    Spinner spinner;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addProductModel =
                ViewModelProviders.of(this).get(AddProductModel.class);
        View root = inflater.inflate(R.layout.fragment_add_product, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        camera =root.findViewById(R.id.imagen_producto);

        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchPictureTakenAction();
            }
        });
        spinner = (Spinner)root.findViewById(R.id.categories_spinner);


        String [] Opciones = {"Todo", "Tecnologia", "Hogar", "Ropa", "Libros"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.Yellow), PorterDuff.Mode.SRC_ATOP);


        return root;
    }

    private void dispatchPictureTakenAction(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePic, 0   );
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode, resultcode, data);
        if(requestcode == 0){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            camera.setImageBitmap(bitmap);
        }

    }

}