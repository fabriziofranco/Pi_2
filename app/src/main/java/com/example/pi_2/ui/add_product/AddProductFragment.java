package com.example.pi_2.ui.add_product;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pi_2.R;
import com.example.pi_2.ui.add_product.AddProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddProductFragment extends Fragment {

    private AddProductModel addProductModel;
    ImageButton camera;
    Spinner spinner;
    Button botoncito;
    private final int PICK_IMAGE_REQUEST = 71;
    Uri uri;
    private StorageReference mStorageRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addProductModel =
                ViewModelProviders.of(this).get(AddProductModel.class);
        View root = inflater.inflate(R.layout.fragment_add_product, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        mStorageRef = FirebaseStorage.getInstance().getReference();
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
        botoncito = root.findViewById(R.id.btnID);
        spinner = (Spinner)root.findViewById(R.id.categories_spinner);


        String [] Opciones = {"Tecnologia", "Hogar", "Ropa", "Libros", "Coleccionables"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.fondo_spinner, Opciones);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getBackground().setColorFilter(getResources().getColor(R.color.Yellow), PorterDuff.Mode.SRC_ATOP);
        String owner_id = getActivity().getIntent().getExtras().get("user_id").toString();
        final TextView textView = root.findViewById(R.id.descripcion);
        textView.setText(owner_id);

        botoncito.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                StorageReference filepath = mStorageRef.child("Photo");

                uploadImage();

            }
        });


        return root;
    }

    private void uploadImage() {
        if(uri != null){
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference    ref = mStorageRef.child("Photo/"+ UUID.randomUUID().toString());

            ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Task <Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();

                    while(!downloadUri.isComplete());
                    Uri url = downloadUri.getResult();
                    String urlFinal = url.toString();



                    Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();



                }
            });


        }
    }


    private void dispatchPictureTakenAction(){
        Intent takePic = new Intent();
        takePic.setType("image/*");
        takePic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(takePic.createChooser(takePic, "Select Picture"), 0   );
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode, resultcode, data);
        if(requestcode == 0){
            uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                camera.setImageBitmap(bitmap);
            }catch(IOException e){
                e.printStackTrace();
            }


        }

    }


}