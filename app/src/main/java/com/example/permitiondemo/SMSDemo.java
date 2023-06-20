package com.example.permitiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SMSDemo extends AppCompatActivity {

    EditText sms_message;
    EditText sms_call;

    private final int REQUEST_SMS_CODE = 2;
    private final  int REQUEST_CALL_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsdemo);

        sms_message = (EditText) findViewById(R.id.sms_message);
        sms_call = (EditText) findViewById(R.id.sms_call);
    }

    //sms code
    void smsCode()
    {
        //getText on field of form

        String msg = sms_message.getText().toString();
        String phone = sms_call.getText().toString();

        //smsManager ka object get karvane ke liye hum getDefault() method use karte hai
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null, msg,null,null);
        Toast.makeText(this, "sms send", Toast.LENGTH_SHORT).show();
    }


    public void sendSms(View view)
    {

        if(ContextCompat.checkSelfPermission(SMSDemo.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
        {
            smsCode();
        }
        else
        {
            ActivityCompat.requestPermissions(SMSDemo.this,new String[]{Manifest.permission.SEND_SMS},REQUEST_SMS_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && REQUEST_SMS_CODE == 2)
        {
            smsCode();
        }
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && REQUEST_CALL_CODE == 1)
        {
            //call();
        }
    }
}