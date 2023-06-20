package com.example.permitiondemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.Manifest;

public class GallaryDemo extends AppCompatActivity {

    ImageView imageview;

    private  final  int REQUEST_GALLARY_CODE = 3;
    private  final  int REQUEST_CAMERA_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_demo);

        imageview = (ImageView) findViewById(R.id.imageview);
    }
    public  void openGallary(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*"); // image/* --> it will take all type of images we cannot make any changes in it
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent , "Select Picture"),REQUEST_GALLARY_CODE);//to select specific image

    }
    public void openCamera(View view)
    {
        //check the camera permmition granted or not
        if(ContextCompat.checkSelfPermission(GallaryDemo.this,Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,REQUEST_CAMERA_CODE);
        }
        else
        {
            ActivityCompat.requestPermissions(GallaryDemo.this,new String[] {Manifest.permission.CAMERA},REQUEST_CAMERA_CODE);
        }

    }
 //to set image on imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if(resultCode == RESULT_OK && requestCode  == REQUEST_GALLARY_CODE)
            {
                Uri uri = data.getData();
                if (uri != null)
                {
                    //Toast.makeText(this, "success......", Toast.LENGTH_SHORT).show();
                    imageview.setImageURI(uri);
                }
            }
            //to show photo on imageview which is click by camera

            if(resultCode == RESULT_OK && requestCode == REQUEST_CAMERA_CODE)
            {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageview.setImageBitmap(bitmap);
            }

            //this type of code not to click double on open button
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
//        {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && REQUEST_SMS_CODE == 2)
//            {
//                smsCode();
//            }
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && REQUEST_CALL_CODE == 1)
//            {
//                //call();
//            }
//        }

    }


}