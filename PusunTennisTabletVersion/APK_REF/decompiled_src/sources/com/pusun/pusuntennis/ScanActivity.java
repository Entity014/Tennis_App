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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.ScanActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return ScanActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
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

    private void initPermission() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 31) {
            arrayList.add("android.permission.BLUETOOTH_SCAN");
            arrayList.add("android.permission.BLUETOOTH_ADVERTISE");
            arrayList.add("android.permission.BLUETOOTH_CONNECT");
            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        } else {
            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (arrayList.size() > 0) {
            ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPermissions() {
        initPermission();
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (bluetoothManager != null) {
                BluetoothAdapter adapter = bluetoothManager.getAdapter();
                if (adapter != null) {
                    if (adapter.isEnabled() || ActivityCompat.checkSelfPermission(this, "android.permission.BLUETOOTH_CONNECT") != 0) {
                        return;
                    }
                    startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 116);
                    return;
                }
                Log.i("tag", "同意申请");
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        String str = new String[]{"android.permission.ACCESS_FINE_LOCATION"}[0];
        if (ContextCompat.checkSelfPermission(this, str) == 0) {
            onPermissionGranted(str);
        } else {
            arrayList.add(str);
        }
    }

    private void onPermissionGranted(String str) {
        str.hashCode();
        if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
            if (Build.VERSION.SDK_INT >= 23 && !checkGPSIsOpen()) {
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.ScanActivity.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ScanActivity.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.ScanActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ScanActivity.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                    }
                }).setCancelable(false).show();
            } else {
                startScan();
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
