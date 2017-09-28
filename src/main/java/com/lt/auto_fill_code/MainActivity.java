package com.lt.auto_fill_code;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SMSBroadcastReceiver.OnReceiveSMSListener {

    private SMSBroadcastReceiver mSMSBroadcastReceiver = new SMSBroadcastReceiver();
    private EditText mEt_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_code = (EditText) findViewById(R.id.et_code);
        mSMSBroadcastReceiver.setOnReceiveSMSListener(this);
        // 注册广播
        IntentFilter intentFilter = new IntentFilter(SMSBroadcastReceiver.SMS_RECEIVED_ACTION);
        // 设置优先级
        intentFilter.setPriority(Integer.MAX_VALUE);
        registerReceiver(mSMSBroadcastReceiver,intentFilter);
    }

    @Override
    public void onReceived(String message) {
        mEt_code.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mSMSBroadcastReceiver);
    }
}
