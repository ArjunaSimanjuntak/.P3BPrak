package com.example.m0617057;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.m0617057.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    ActivityMain2Binding main2Binding;
    private ActivityResultLauncher<Intent> intentLauncher;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2Binding =  ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(main2Binding.getRoot());
        this.intentLauncher = this.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                    }
                }
        );
        main2Binding.btnKamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){
            this.intentLauncher.launch(takePictureIntent);
        }
    }

    protected void onCustomActivityResult(Intent reply){
        Bundle extras = reply.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        this.ivPhoto.setImageBitmap(imageBitmap);
    }
}