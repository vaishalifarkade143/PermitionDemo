package com.example.permitiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/*flow of code
 jaise hi hum call karenge callToNo()
method call hogi or vo check karega ky permition granted hai
to vo call() vale method ko call karva dega curser call method me chala jayega or
 call() method ke andar ka code execute hoga or call karva dega
 or agar permission granted nhi hai to else block execute hoga
 permission ke liye popup box open hoga.
 agar mai allow karti hu then vo  onRequestPermissionsResult()
 method ke andar aa jayega  condition check karega or call karva dega*/
public class MainActivityOne extends AppCompatActivity {

    EditText et_phone;
    private final  int REQUEST_CALL_CODE = 1; //call code banaya variable declared value is any thing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);
        et_phone = (EditText) findViewById(R.id.et_phone);

    }
    public  void callToNo(View view)
    {
       // String phno = et_phone.getText().toString();

        //------no need to give permition

//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:"+phno));
//        startActivity(intent);

        //permition reqired for action call
        //we will check permision pelese koi permition di yta nhi --ContextCompat.checkSelfPermission()
        //konsi permition check karenge di hai ya nhi --> MainActivityOne.this, Manifest.permission.CALL_PHONE
        //agar CALL_PHONE permission granted hai to --> PackageManager.PERMISSION_GRANTED
        if(ContextCompat.checkSelfPermission(MainActivityOne.this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
        {
//            Intent intent = new Intent(Intent.ACTION_DIAL);
//            intent.setData(Uri.parse("tel:"+phno));
//            startActivity(intent);

             call();

        }
        else
        {
            //agar permission Granted nahi hai to hum permission ke liye request karenge

            ActivityCompat.requestPermissions(MainActivityOne.this,new  String[]{Manifest.permission.CALL_PHONE},1);//ye method ek dialog box open kar dega us hisab se hume accept ya fir reject karna padega

        }
    }

    void call()
    {
        String phno = et_phone.getText().toString();

        //------no need to give permition

//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:"+phno));
//        startActivity(intent);

        //------ permition required
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phno));
        startActivity(intent);
    }

    //onRequestPermissionsResult ye method overide karo taki ekbar call button
    //par click karne ke bad permission allow karte hi call
    //lag jaye vapis se call buttion par click na karna pade


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //grantResults.length > 0 that it means permition grant hue hai n ky dial no ki length 0 se jada hai
        // grantResults[] ke andar jo bhi value ayegi vo equal hogi PERMISSION_GRANTED to permition usko mil chuki hai--> grantResults[0] == PackageManager.PERMISSION_GRANTED
        //
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && REQUEST_CALL_CODE == 1)
        {
            call();
        }

    }

}