package com.pusun.pusuntennis;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.pusun.pusuntennis.adapter.DeviceRvAdapter;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ScanActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    private DeviceRvAdapter deviceRvAdapter;
    private RecyclerView list_dev;
    public List<BleDevice> namesList = new ArrayList();
    private RelativeLayout no_scan;
    private TextView scan;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan);
        this.scan = (TextView) findViewById(R.id.scan);
        this.list_dev = (RecyclerView) findViewById(R.id.list_dev);
        View rootLayout = findViewById(R.id.root_layout);
        if (rootLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootLayout, new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.ScanActivity$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    return ScanActivity.lambda$onCreate$0(view, windowInsetsCompat);
                }
            });
        }
        this.no_scan = (RelativeLayout) findViewById(R.id.no_scan);
        this.scan.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.ScanActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BleManager.getInstance().disconnectAllDevice();
                ScanActivity.this.checkPermissions();
            }
        });
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance().enableLog(true).setReConnectCount(1, 5000L).setConnectOverTime(OkHttpUtils.DEFAULT_MILLISECONDS).setOperateTimeout(5000);
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().setAutoConnect(false).setScanTimeOut(5000L).build());
        checkPermissions();
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    private void startScan() {
        List<BleDevice> list = this.namesList;
        if (list != null && list.size() > 0) {
            this.namesList.clear();
            this.deviceRvAdapter.notifyDataSetChanged();
        }
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.ScanActivity.2
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                ScanActivity scanActivity = ScanActivity.this;
                ShowHelper.showProgressDialog(scanActivity, scanActivity.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice) {
                ScanActivity scanActivity = ScanActivity.this;
                ShowHelper.showProgressDialog(scanActivity, scanActivity.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list2) {
                if (list2 == null || list2.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    ScanActivity scanActivity = ScanActivity.this;
                    ShowHelper.toastLong(scanActivity, scanActivity.getResources().getString(R.string.no_device_found));
                    ScanActivity.this.no_scan.setVisibility(0);
                    return;
                }
                ShowHelper.dismissProgressDialog();
                ScanActivity.this.namesList = new ArrayList();
                ScanActivity.this.namesList.clear();
                for (int i = 0; i < list2.size(); i++) {
                    if (list2.get(i).getName() != null && (list2.get(i).getName().trim().contains("PT") || list2.get(i).getName().trim().contains("PP") || list2.get(i).getName().trim().contains("PR") || list2.get(i).getName().trim().contains("PM") || list2.get(i).getName().trim().contains("PA"))) {
                        ScanActivity.this.namesList.add(list2.get(i));
                    }
                }
                if (ScanActivity.this.namesList.size() != 0) {
                    ScanActivity.this.no_scan.setVisibility(8);
                    ScanActivity.this.deviceRvAdapter = new DeviceRvAdapter(ScanActivity.this.namesList, ScanActivity.this);
                    ScanActivity.this.list_dev.setNestedScrollingEnabled(false);
                    ScanActivity.this.list_dev.setLayoutManager(new GridLayoutManager(ScanActivity.this, 1));
                    ScanActivity.this.list_dev.setAdapter(ScanActivity.this.deviceRvAdapter);
                    return;
                }
                ScanActivity scanActivity2 = ScanActivity.this;
                ShowHelper.toastLong(scanActivity2, scanActivity2.getResources().getString(R.string.no_device_found));
                ScanActivity.this.no_scan.setVisibility(0);
            }
        });
    }

    private void checkPermissions() {
        java.util.List<String> permissionList = new java.util.ArrayList<>();
        if (Build.VERSION.SDK_INT >= 31) {
            permissionList.add("android.permission.BLUETOOTH_SCAN");
            permissionList.add("android.permission.BLUETOOTH_CONNECT");
        } else {
            permissionList.add("android.permission.ACCESS_FINE_LOCATION");
        }

        java.util.List<String> missingPermissions = new java.util.ArrayList<>();
        for (String permission : permissionList) {
            if (ContextCompat.checkSelfPermission(this, permission) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission);
            }
        }

        if (!missingPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toArray(new String[0]), 2);
            return;
        }

        // All permissions are granted, now check adapter status
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null && !adapter.isEnabled()) {
            try {
                startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 116);
            } catch (SecurityException e) {
                Log.e("ScanActivity", "SecurityException requesting bluetooth enable", e);
            }
            return;
        }

        // Check GPS status (Only needed for SDK < 31 where location is required for scanning)
        if (Build.VERSION.SDK_INT < 31 && Build.VERSION.SDK_INT >= 23 && !checkGPSIsOpen()) {
            new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.notice))
                .setMessage(getResources().getString(R.string.blue_need_setting))
                .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ScanActivity.this.finish();
                    }
                })
                .setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ScanActivity.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                    }
                })
                .setCancelable(false)
                .show();
        } else {
            startScan();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            boolean allGranted = grantResults.length > 0;
            for (int result : grantResults) {
                if (result != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                // To avoid any possible infinite loop, double check that permissions list is actually empty now
                java.util.List<String> permissionList = new java.util.ArrayList<>();
                if (Build.VERSION.SDK_INT >= 31) {
                    permissionList.add("android.permission.BLUETOOTH_SCAN");
                    permissionList.add("android.permission.BLUETOOTH_CONNECT");
                } else {
                    permissionList.add("android.permission.ACCESS_FINE_LOCATION");
                }
                boolean actuallyAllGranted = true;
                for (String permission : permissionList) {
                    if (ContextCompat.checkSelfPermission(this, permission) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                        actuallyAllGranted = false;
                        break;
                    }
                }
                if (actuallyAllGranted) {
                    checkPermissions();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.blue_need_setting), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.blue_need_setting), Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        if (locationManager == null) {
            return false;
        }
        return locationManager.isProviderEnabled("gps");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && checkGPSIsOpen()) {
            startScan();
        }
        if (i == 116 && i2 == -1) {
            Toast.makeText(this, getResources().getString(R.string.blue_is_open), 0).show();
            checkPermissions();
        } else if (i == 116 && i2 == 0) {
            finish();
        }
    }
}
