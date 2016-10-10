package com.rf.hp.getdeviceinfo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        MyDeviceInfo myDeviceInfo = new MyDeviceInfo(this);
        //myDeviceInfo.getNativePhoneNumber()
        String nativePhoneNumber = myDeviceInfo.getNativePhoneNumber();
        String phoneInfo = myDeviceInfo.getPhoneInfo();
        String providersName = myDeviceInfo.getProvidersName();
        System.out.println("nativePhoneNumber:"+nativePhoneNumber+"/n"+"phoneInfo:"+phoneInfo+"/n"+"providersName:"+providersName);
        //手机型号
        String model = Build.MODEL;
        tvInfo.setText("nativePhoneNumber:"+nativePhoneNumber+",model:"+model);


    }
}
