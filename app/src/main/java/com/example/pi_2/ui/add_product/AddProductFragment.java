package com.example.pi_2.ui.add_product;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi_2.Login;
import com.example.pi_2.R;
import com.example.pi_2.ui.add_product.AddProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddProductFragment extends Fragment {

    private AddProductModel addProductModel;
    ImageButton camera;
    Spinner spinner;
    Button botoncito;
    private final int PICK_IMAGE_REQUEST = 71;
    Uri uri;
    String urlFinal,owner_id;

    private StorageReference mStorageRef;


    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }


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
        owner_id = getActivity().getIntent().getExtras().get("user_id").toString();
        final TextView textView = root.findViewById(R.id.descripcion);


        botoncito.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                StorageReference filepath = mStorageRef.child("Photo");
                uploadImage();
                PostProduct();

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
                    final Uri url = downloadUri.getResult();
                    urlFinal = url.toString();
                    Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                    Thread someThread = new Thread() {

                        @Override
                        public void run() {
                            //some actions
                            while ((urlFinal==null)); //wait for condition
                                
                        }

                    };

                }
            });


        }
    }


    void PostProduct(){
        // 1. Getting username and password inputs from view
        EditText descripciontxt = (EditText) getActivity().findViewById(R.id.descripcion);

        EditText nametxt = (EditText) getActivity().findViewById(R.id.name);
        String description = descripciontxt.getText().toString();
        String name = nametxt.getText().toString();

        Spinner sp1 = (Spinner) getActivity().findViewById(R.id.categories_spinner);
        String item = sp1.getSelectedItem().toString();

        String value_of_spinner;

        if(item.equals("Tecnologia"))
            value_of_spinner="1";
        else if(item.equals("Hogar"))
            value_of_spinner="2";
        else if(item.equals("Ropa"))
            value_of_spinner="3";
        else if(item.equals("Libros"))
            value_of_spinner="4";
        else
            value_of_spinner="5";



        // 2. Creating a message from user input data
        Map<String, String> message = new HashMap<>();
        message.put("description", description);
        message.put("name", name);
        message.put("owner_id", owner_id);
        message.put("category_id", value_of_spinner);
        message.put("url", urlFinal);

        // 3. Converting the message object to JSON string (jsonify)
        JSONObject jsonMessage = new JSONObject(message);

        // 4. Sending json message to Server
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2:8080/createProduct",
                jsonMessage,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TODO
                        try {
                            showMessage("Registrado con éxito");

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
                        showMessage(error.getMessage());

                    }
                }
        );
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
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