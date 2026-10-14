package com.example.radioprobe;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.bluetooth.BluetoothAdapter;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.graphics.Color;
import android.view.Gravity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 32, 24, 24);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("فاحص الإشارة RadioProbe");
        title.setTextSize(25);
        title.setTextColor(Color.BLUE);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setTextSize(18);
        info.setTextColor(Color.BLACK);
        info.setPadding(0, 30, 0, 0);

        WifiManager wifi = (WifiManager)
                getApplicationContext().getSystemService(
                        Context.WIFI_SERVICE);

        String wifiState = "غير معروف";
        if (wifi != null) {
            wifiState = wifi.isWifiEnabled()
                    ? "يعمل" : "متوقف";
        }

        BluetoothAdapter bluetooth =
                BluetoothAdapter.getDefaultAdapter();

        String bluetoothState;
        if (bluetooth == null) {
            bluetoothState = "غير متوفر";
        } else {
            bluetoothState = bluetooth.isEnabled()
                    ? "يعمل" : "متوقف";
        }

        info.setText(
                "معلومات الهاتف\n\n" +
                "الشركة: " +
                android.os.Build.MANUFACTURER + "\n" +
                "الجهاز: " +
                android.os.Build.MODEL + "\n" +
                "إصدار أندرويد: " +
                android.os.Build.VERSION.RELEASE + "\n\n" +
                "حالة Wi-Fi: " + wifiState + "\n" +
                "حالة Bluetooth: " + bluetoothState + "\n\n" +
                "ملاحظة: التطبيق يفحص الحالة الأساسية فقط، " +
                "ولا يقيس جميع الموجات اللاسلكية."
        );

        layout.addView(info);
        setContentView(layout);
    }
}
