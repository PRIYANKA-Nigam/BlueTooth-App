package com.example.bluetoothapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2; ImageView img; Button b1,b2,b3,b4; BluetoothAdapter bluetoothAdapter;
private static final  int REQUEST_ENABLE_BT=0; private static final  int REQUEST_DISCOVER_BT=1; @Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.tt); textView2=findViewById(R.id.tt2); img=findViewById(R.id.img);
        b1=findViewById(R.id.bt); b2=findViewById(R.id.bt2); b3=findViewById(R.id.bt3);b4=findViewById(R.id.bt4);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter(); if (bluetoothAdapter==null){ textView1.setText("Bluetooth is not available"); }
        else {  textView1.setText("Bluetooth is  available"); }
    if (bluetoothAdapter.isEnabled()){ img.setImageResource(R.drawable.ic_baseline_bluetooth_connected_24);
    }else { img.setImageResource(R.drawable.ic_baseline_bluetooth_disabled_24); }
    b1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) { if (!bluetoothAdapter.isEnabled()){
               showToast("Turning On BlueTooth..."); Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
               startActivityForResult(intent,REQUEST_ENABLE_BT); }
           else{ showToast("BlueTooth is already On"); } }});
    b3.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) { if(!bluetoothAdapter.isDiscovering()){
               showToast("Making Your Device Discoverable"); Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
               startActivityForResult(intent,REQUEST_DISCOVER_BT); } }});
    b2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) { if (bluetoothAdapter.isEnabled()){ bluetoothAdapter.disable();
                showToast("Turning BlueTooth Off..."); img.setImageResource(R.drawable.ic_baseline_bluetooth_disabled_24); }
                else { showToast("BlueTooth is already Off"); } }});
    b4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { if (bluetoothAdapter.isEnabled()){
                textView1.setText("Paired Devices"); Set<BluetoothDevice> devices=bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device: devices){ textView1.append("\nDevices: " + device.getName()+ ","+device); } }
            else { showToast("Turn on blueTooth to get paired devices"); } }}); } @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { switch (requestCode){ case REQUEST_ENABLE_BT:
                if (resultCode==RESULT_OK){ img.setImageResource(R.drawable.ic_baseline_bluetooth_24); showToast("Bluetooth is On");
                }else { showToast("Couldn't on BlueTooth"); } break; }
        super.onActivityResult(requestCode, resultCode, data); }private void showToast(String msg){ Toast.makeText(this,msg,Toast.LENGTH_SHORT).show(); }}