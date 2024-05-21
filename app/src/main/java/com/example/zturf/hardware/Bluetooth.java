package com.example.zturf.hardware;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zturf.R;

import java.util.Set;

public class Bluetooth extends AppCompatActivity {
    ListView listView;
    Button button;
    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        listView = findViewById(R.id.bluetoothlist);
        button = findViewById(R.id.showBluetooth);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        button.setOnClickListener(v -> {
                showBondedDevices();
        });
    }
    private void showBondedDevices() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
        String[] devices = new String[bt.size()];
        int index = 0;
        if (bt.size() > 0) {
            for (BluetoothDevice device : bt) {
                String deviceName = device.getName();
                Log.d("Bluetooth", "Device Name: " + deviceName);
                devices[index] = deviceName;
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, devices);
            listView.setAdapter(arrayAdapter);
        }
    }

}
