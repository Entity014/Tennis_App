package com.pusun.pusuntennis;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import cn.qqtheme.framework.picker.OptionPicker;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;

import com.google.common.base.Ascii;
import com.kyleduo.switchbutton.SwitchButton;
import com.pusun.pusuntennis.utils.AppLog;
import com.pusun.pusuntennis.utils.BLEServiceParameters;
import com.pusun.pusuntennis.utils.BasicData2;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.Util;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.SeleDao;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    private static final String FORBID_INFO = "forbid";
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static BleDevice bleDevice;
    private String deviceMac;
    private String deviceName;
    private View bg_four;
    private View bg_input;
    private View bg_input_big;
    private TextView blenoty;
    private Button btn_ball;
    private Button change_get;
    private Button change_point;
    private Button clear;
    private TextView d_ad;
    private DaoSession daoSession;
    private TextView delepoints;
    float density;
    private Button down_up;
    private EditText et1;
    private EditText et2;
    private TextView f_ad;
    private TextView f_de;
    private TextView f_tv;
    private RelativeLayout fourbtn;
    private IndicatorSeekBar freq;
    BluetoothGatt gatt;
    private LinearLayout group;
    private RelativeLayout group_cate;
    private Button group_cross;
    private TextView h_ad;
    private TextView h_de;
    private Button high_point;
    private TextView hintpoints;
    private Button hit;
    private TextView l_ad;
    private TextView lastpoints;
    private RelativeLayout layout_adjust;
    private RelativeLayout layout_fast;
    private Button left_right;
    private int lr;
    private Button moon;
    private String nameStar;
    private TextView note_lr;
    private TextView note_ud;
    private TextView num1;
    private TextView num10;
    private TextView num11;
    private TextView num12;
    private TextView num13;
    private TextView num14;
    private TextView num15;
    private TextView num16;
    private TextView num17;
    private TextView num18;
    private TextView num19;
    private TextView num2;
    private TextView num20;
    private TextView num21;
    private TextView num22;
    private TextView num23;
    private TextView num24;
    private TextView num25;
    private TextView num26;
    private TextView num27;
    private TextView num28;
    private TextView num3;
    private TextView num4;
    private TextView num5;
    private TextView num6;
    private TextView num7;
    private TextView num8;
    private TextView num9;
    private TextView num_lr;
    private TextView num_ud;
    private Button place;
    private int[] poids;
    private RelativeLayout points;
    private TextView[] pos;
    private ImageView power1;
    private ImageView power2;
    private ImageView power3;
    private ImageView power4;
    private RelativeLayout power_lay;
    private ImageView power_no;
    private TextView r_ad;
    private TextView r_de;
    private TextView r_tv;
    private TextView rg_hint;
    private RelativeLayout rgheight;
    private RadioGroup rgheight1;
    private TextView ri_ad;
    private IndicatorSeekBar rotatebar;
    private TextView savepoints;
    private TextView select_dianwei;
    private Button selection;
    private LinearLayout self;
    private Button self_point;
    private TextView signal;
    private TextView signal_note;
    private TextView snum1;
    private TextView snum10;
    private TextView snum11;
    private TextView snum12;
    private TextView snum13;
    private TextView snum14;
    private TextView snum15;
    private TextView snum16;
    private TextView snum17;
    private TextView snum18;
    private TextView snum19;
    private TextView snum2;
    private TextView snum20;
    private TextView snum21;
    private TextView snum22;
    private TextView snum23;
    private TextView snum24;
    private TextView snum25;
    private TextView snum26;
    private TextView snum27;
    private TextView snum28;
    private TextView snum3;
    private TextView snum4;
    private TextView snum5;
    private TextView snum6;
    private TextView snum7;
    private TextView snum8;
    private TextView snum9;
    private Spinner spinner;
    private LinearLayout start_layout;
    private Button stop_ball;
    private SwitchButton switchbtn;
    private int t1;
    private int t10;
    private int t11;
    private int t12;
    private int t13;
    private int t14;
    private int t15;
    private int t16;
    private int t17;
    private int t18;
    private int t19;
    private int t2;
    private int t20;
    private int t21;
    private int t22;
    private int t23;
    private int t24;
    private int t25;
    private int t26;
    private int t27;
    private int t28;
    private int t3;
    private int t4;
    private int t5;
    private int t6;
    private int t7;
    private int t8;
    private int t9;
    private TextView tableName;
    private RelativeLayout tennipic2;
    private RelativeLayout tennipic3;
    private RelativeLayout tennipic4;
    private RelativeLayout tennipic5;
    private TimeCount1 timeCount1;
    private TimeCount2 timeCount2;
    private TimeCount3 timeCount3;
    private TimeCount4 timeCount4;
    private int[] tvids;
    private int[] tvids15;
    private TextView[] tvs;
    private TextView u_ad;
    private int ud;
    private TextView v_ad;
    private TextView v_de;
    private TextView v_tv;
    private TextView value_h;
    private IndicatorSeekBar velobar;
    private TextView vo_tv;
    private Button whole;
    private List<Integer> selectPoints = new ArrayList();
    private int modeNum = 1;
    private int modeCate = 0;
    private int[] frequentNums = {88, 78, 68, 58, 48, 38, 33, 28, 23, 20};
    private int[] veloNums = {80, 83, 86, 89, 92, 96, 100, 110, 120, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 140, 150, 160};
    private int[] veloTins = {20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 140};
    private boolean isTouch = false;
    private int stopMode = 1;
    private int isFaultOn = 0;
    private int isNumDing = 0;
    private int tagH = 0;
    private int tagV = 0;
    private int tagC = 0;
    private int tagHT = 0;
    private int tagFix = 1;
    private int flagPower = 0;
    private int connNum = 0;
    private int radioNum = 1;
    private final int[] c1 = {21, 3};
    private final int[] c2 = {27, 3};
    private final int[] c3 = {24, 0};
    private final int[] c4 = {24, 6};
    private final int[] c5 = {21, 6};
    private final int[] c6 = {27, 0};
    private final int[] h1 = {14, 16, 17, 18, 20};
    private final int[] h2 = {14, 20};
    private final int[] h3 = {16, 18};
    private final int[] h4 = {16, 18};
    private final int[] h5 = {14, 17, 20};
    private final int[] v1 = {3, 17, 24};
    private final int[] v2 = {3, 17, 24};
    private final int[] v3 = {3, 24};
    private final int[] ht1 = {3};
    private final int[] ht2 = {5};
    private final int[] ht3 = {1};
    private final int[] fx1 = {17};
    private final int[] fx2 = {19};
    private final int[] fx3 = {15};
    private final int[] gr1 = {18, 8, 19, 23, 22, 5};
    private final int[] gr2 = {25, 19, 23, 15, 26, 0};
    private final int[] gr3 = {23, 15, 25, 26, 1, 10};
    private final int[] gr4 = {12, 8, 12, 23, 21, 18};
    private final int[] gr5 = {25, 19, 23, 15, 13, 7};
    private final int[] gr6 = {21, 16, 25, 12, 6, 8};
    private final int[] gr7 = {25, 26, 27, 12, 5, 1};
    private final int[] gr8 = {9, 10, 11, 22, 23, 26};
    private final int[] gr9 = {13, 7, 27, 6, 21, 0};
    private final int[] gr10 = {25, 13, 23, 7, 19, 15};
    private final int[] gr11 = {27, 25, 26, 24, 7, 14};
    private final int[] gr12 = {21, 15, 25, 12, 7, 6};
    private int forbid = 0;
    private int baseHt = 10;
    private int valueSelect = 10;

    static /* synthetic */ int access$112(MainActivity4 mainActivity4, int i) {
        int i2 = mainActivity4.ud + i;
        mainActivity4.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$120(MainActivity4 mainActivity4, int i) {
        int i2 = mainActivity4.ud - i;
        mainActivity4.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$212(MainActivity4 mainActivity4, int i) {
        int i2 = mainActivity4.lr + i;
        mainActivity4.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$220(MainActivity4 mainActivity4, int i) {
        int i2 = mainActivity4.lr - i;
        mainActivity4.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$4408(MainActivity4 mainActivity4) {
        int i = mainActivity4.valueSelect;
        mainActivity4.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$4410(MainActivity4 mainActivity4) {
        int i = mainActivity4.valueSelect;
        mainActivity4.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$4708(MainActivity4 mainActivity4) {
        int i = mainActivity4.connNum;
        mainActivity4.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$5308(MainActivity4 mainActivity4) {
        int i = mainActivity4.isFaultOn;
        mainActivity4.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main4);
        EventBus.getDefault().register(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.density = displayMetrics.scaledDensity;
        bleDevice = (BleDevice) getIntent().getParcelableExtra("device");
        if (bleDevice != null && bleDevice.getMac() != null) {
            deviceMac = bleDevice.getMac();
        }
        if (bleDevice != null && bleDevice.getName() != null) {
            deviceName = bleDevice.getName().trim();
        }
        // Fallback: name passed explicitly before disconnect
        String intentDeviceName = getIntent().getStringExtra("device_name");
        if (intentDeviceName != null && !intentDeviceName.isEmpty()) {
            deviceName = intentDeviceName;
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivity4$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity4.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.switchbtn = (SwitchButton) findViewById(R.id.switchbtn);
        this.tableName = (TextView) findViewById(R.id.tableName);
        this.self = (LinearLayout) findViewById(R.id.self);
        this.group = (LinearLayout) findViewById(R.id.group);
        this.points = (RelativeLayout) findViewById(R.id.points);
        this.delepoints = (TextView) findViewById(R.id.delepoints);
        this.lastpoints = (TextView) findViewById(R.id.lastpoints);
        this.savepoints = (TextView) findViewById(R.id.savepoints);
        this.vo_tv = (TextView) findViewById(R.id.vo_tv);
        this.power_lay = (RelativeLayout) findViewById(R.id.power_lay);
        this.group_cate = (RelativeLayout) findViewById(R.id.group_cate);
        this.power1 = (ImageView) findViewById(R.id.power1);
        this.power2 = (ImageView) findViewById(R.id.power2);
        this.power3 = (ImageView) findViewById(R.id.power3);
        this.power4 = (ImageView) findViewById(R.id.power4);
        this.power_no = (ImageView) findViewById(R.id.power_no);
        this.signal = (TextView) findViewById(R.id.signal);
        this.signal_note = (TextView) findViewById(R.id.signal_note);
        this.fourbtn = (RelativeLayout) findViewById(R.id.fourbtn);
        this.bg_four = findViewById(R.id.bg_four);
        this.bg_input = findViewById(R.id.bg_input);
        this.rgheight = (RelativeLayout) findViewById(R.id.rgheight);
        this.rgheight1 = (RadioGroup) findViewById(R.id.rgheight1);
        this.rg_hint = (TextView) findViewById(R.id.rg_hint);
        this.value_h = (TextView) findViewById(R.id.value_h);
        this.h_ad = (TextView) findViewById(R.id.h_ad);
        this.h_de = (TextView) findViewById(R.id.h_de);
        this.start_layout = (LinearLayout) findViewById(R.id.start_layout);
        this.bg_input_big = findViewById(R.id.bg_input_big);
        this.note_ud = (TextView) findViewById(R.id.note_ud);
        this.note_lr = (TextView) findViewById(R.id.note_lr);
        this.num_ud = (TextView) findViewById(R.id.num_ud);
        this.num_lr = (TextView) findViewById(R.id.num_lr);
        this.lr = BasicData2.a13[0];
        this.ud = BasicData2.a13[1];
        this.num_lr.setText("" + (74 - (this.lr / 30)));
        if (this.ud > 1410) {
            this.num_ud.setText("" + (64 - (this.ud / 30)));
        } else {
            this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
        }
        this.f_tv = (TextView) findViewById(R.id.f_tv);
        this.r_tv = (TextView) findViewById(R.id.r_tv);
        this.v_tv = (TextView) findViewById(R.id.v_tv);
        this.snum1 = (TextView) findViewById(R.id.snum1);
        this.snum2 = (TextView) findViewById(R.id.snum2);
        this.snum3 = (TextView) findViewById(R.id.snum3);
        this.snum4 = (TextView) findViewById(R.id.snum4);
        this.snum5 = (TextView) findViewById(R.id.snum5);
        this.snum6 = (TextView) findViewById(R.id.snum6);
        this.snum7 = (TextView) findViewById(R.id.snum7);
        this.snum8 = (TextView) findViewById(R.id.snum8);
        this.snum9 = (TextView) findViewById(R.id.snum9);
        this.snum10 = (TextView) findViewById(R.id.snum10);
        this.snum11 = (TextView) findViewById(R.id.snum11);
        this.snum12 = (TextView) findViewById(R.id.snum12);
        this.snum13 = (TextView) findViewById(R.id.snum13);
        this.snum14 = (TextView) findViewById(R.id.snum14);
        this.snum15 = (TextView) findViewById(R.id.snum15);
        this.snum16 = (TextView) findViewById(R.id.snum16);
        this.snum17 = (TextView) findViewById(R.id.snum17);
        this.snum18 = (TextView) findViewById(R.id.snum18);
        this.snum19 = (TextView) findViewById(R.id.snum19);
        this.snum20 = (TextView) findViewById(R.id.snum20);
        this.snum21 = (TextView) findViewById(R.id.snum21);
        this.snum22 = (TextView) findViewById(R.id.snum22);
        this.snum23 = (TextView) findViewById(R.id.snum23);
        this.snum24 = (TextView) findViewById(R.id.snum24);
        this.snum25 = (TextView) findViewById(R.id.snum25);
        this.snum26 = (TextView) findViewById(R.id.snum26);
        this.snum27 = (TextView) findViewById(R.id.snum27);
        TextView textView = (TextView) findViewById(R.id.snum28);
        this.snum28 = textView;
        this.tvs = new TextView[]{this.snum1, this.snum2, this.snum3, this.snum4, this.snum5, this.snum6, this.snum7, this.snum8, this.snum9, this.snum10, this.snum11, this.snum12, this.snum13, this.snum14, this.snum15, this.snum16, this.snum17, this.snum18, this.snum19, this.snum20, this.snum21, this.snum22, this.snum23, this.snum24, this.snum25, this.snum26, this.snum27, textView};
        this.tvids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        this.tvids15 = new int[]{0, 2, 3, 4, 6, 14, 16, 17, 18, 20, 21, 23, 24, 25, 27};
        this.num1 = (TextView) findViewById(R.id.num1);
        this.num2 = (TextView) findViewById(R.id.num2);
        this.num3 = (TextView) findViewById(R.id.num3);
        this.num4 = (TextView) findViewById(R.id.num4);
        this.num5 = (TextView) findViewById(R.id.num5);
        this.num6 = (TextView) findViewById(R.id.num6);
        this.num7 = (TextView) findViewById(R.id.num7);
        this.num8 = (TextView) findViewById(R.id.num8);
        this.num9 = (TextView) findViewById(R.id.num9);
        this.num10 = (TextView) findViewById(R.id.num10);
        this.num11 = (TextView) findViewById(R.id.num11);
        this.num12 = (TextView) findViewById(R.id.num12);
        this.num13 = (TextView) findViewById(R.id.num13);
        this.num14 = (TextView) findViewById(R.id.num14);
        this.num15 = (TextView) findViewById(R.id.num15);
        this.num16 = (TextView) findViewById(R.id.num16);
        this.num17 = (TextView) findViewById(R.id.num17);
        this.num18 = (TextView) findViewById(R.id.num18);
        this.num19 = (TextView) findViewById(R.id.num19);
        this.num20 = (TextView) findViewById(R.id.num20);
        this.num21 = (TextView) findViewById(R.id.num21);
        this.num22 = (TextView) findViewById(R.id.num22);
        this.num23 = (TextView) findViewById(R.id.num23);
        this.num24 = (TextView) findViewById(R.id.num24);
        this.num25 = (TextView) findViewById(R.id.num25);
        this.num26 = (TextView) findViewById(R.id.num26);
        this.num27 = (TextView) findViewById(R.id.num27);
        this.num28 = (TextView) findViewById(R.id.num28);
        this.num1.setOnClickListener(this);
        this.num2.setOnClickListener(this);
        this.num3.setOnClickListener(this);
        this.num4.setOnClickListener(this);
        this.num5.setOnClickListener(this);
        this.num6.setOnClickListener(this);
        this.num7.setOnClickListener(this);
        this.num8.setOnClickListener(this);
        this.num9.setOnClickListener(this);
        this.num10.setOnClickListener(this);
        this.num11.setOnClickListener(this);
        this.num12.setOnClickListener(this);
        this.num13.setOnClickListener(this);
        this.num14.setOnClickListener(this);
        this.num15.setOnClickListener(this);
        this.num16.setOnClickListener(this);
        this.num17.setOnClickListener(this);
        this.num18.setOnClickListener(this);
        this.num19.setOnClickListener(this);
        this.num20.setOnClickListener(this);
        this.num21.setOnClickListener(this);
        this.num22.setOnClickListener(this);
        this.num23.setOnClickListener(this);
        this.num24.setOnClickListener(this);
        this.num25.setOnClickListener(this);
        this.num26.setOnClickListener(this);
        this.num27.setOnClickListener(this);
        this.num28.setOnClickListener(this);
        this.pos = new TextView[]{this.num1, this.num2, this.num3, this.num4, this.num5, this.num6, this.num7, this.num8, this.num9, this.num10, this.num11, this.num12, this.num13, this.num14, this.num15, this.num16, this.num17, this.num18, this.num19, this.num20};
        this.poids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        this.self_point = (Button) findViewById(R.id.self_point);
        this.high_point = (Button) findViewById(R.id.high_point);
        this.left_right = (Button) findViewById(R.id.left_right);
        this.down_up = (Button) findViewById(R.id.down_up);
        this.whole = (Button) findViewById(R.id.whole);
        this.hit = (Button) findViewById(R.id.hit);
        this.place = (Button) findViewById(R.id.place);
        this.moon = (Button) findViewById(R.id.moon);
        this.clear = (Button) findViewById(R.id.clear);
        this.selection = (Button) findViewById(R.id.selection);
        this.group_cross = (Button) findViewById(R.id.group_cross);
        this.self_point.setOnClickListener(this);
        this.high_point.setOnClickListener(this);
        this.left_right.setOnClickListener(this);
        this.down_up.setOnClickListener(this);
        this.whole.setOnClickListener(this);
        this.hit.setOnClickListener(this);
        this.place.setOnClickListener(this);
        this.moon.setOnClickListener(this);
        this.clear.setOnClickListener(this);
        this.selection.setOnClickListener(this);
        this.group_cross.setOnClickListener(this);
        Button button = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.stop_ball);
        this.stop_ball = button2;
        button2.setOnClickListener(this);
        this.et1 = (EditText) findViewById(R.id.et1);
        this.et2 = (EditText) findViewById(R.id.et2);
        this.daoSession = MyApplication.getmDaoSession();
        this.change_point = (Button) findViewById(R.id.change_point);
        this.change_get = (Button) findViewById(R.id.change_get);
        this.hintpoints = (TextView) findViewById(R.id.hintpoints);
        TimeCount1 timeCount1 = this.timeCount1;
        if (timeCount1 != null) {
            timeCount1.cancel();
        }
        TimeCount2 timeCount2 = this.timeCount2;
        if (timeCount2 != null) {
            timeCount2.cancel();
        }
        TimeCount3 timeCount3 = this.timeCount3;
        if (timeCount3 != null) {
            timeCount3.cancel();
        }
        TimeCount4 timeCount4 = this.timeCount4;
        if (timeCount4 != null) {
            timeCount4.cancel();
        }
        this.spinner = (Spinner) findViewById(R.id.spinner_fast);
        SharedPreferences sharedPreferences = getSharedPreferences(FORBID_INFO, 0);
        AppLog.e("" + sharedPreferences.getInt(FORBID_INFO, 0));
        this.forbid = sharedPreferences.getInt(FORBID_INFO, 0);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.1
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity4.this.getUrlTxt();
            }
        }, 1000L);
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity4.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.ri_ad);
        this.ri_ad = textView2;
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) findViewById(R.id.l_ad);
        this.l_ad = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) findViewById(R.id.u_ad);
        this.u_ad = textView4;
        textView4.setOnClickListener(this);
        TextView textView5 = (TextView) findViewById(R.id.d_ad);
        this.d_ad = textView5;
        textView5.setOnClickListener(this);
        TextView textView6 = (TextView) findViewById(R.id.v_ad);
        this.v_ad = textView6;
        textView6.setOnClickListener(this);
        TextView textView7 = (TextView) findViewById(R.id.v_de);
        this.v_de = textView7;
        textView7.setOnClickListener(this);
        TextView textView8 = (TextView) findViewById(R.id.f_ad);
        this.f_ad = textView8;
        textView8.setOnClickListener(this);
        TextView textView9 = (TextView) findViewById(R.id.f_de);
        this.f_de = textView9;
        textView9.setOnClickListener(this);
        TextView textView10 = (TextView) findViewById(R.id.r_ad);
        this.r_ad = textView10;
        textView10.setOnClickListener(this);
        TextView textView11 = (TextView) findViewById(R.id.r_de);
        this.r_de = textView11;
        textView11.setOnClickListener(this);
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity4.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("up:1");
                    if (MainActivity4.this.ud >= 1410) {
                        MainActivity4.access$112(MainActivity4.this, 30);
                    }
                    if (MainActivity4.this.ud < 1410) {
                        MainActivity4.access$112(MainActivity4.this, 50);
                    }
                    if (MainActivity4.this.ud < 1110) {
                        MainActivity4.this.ud = 1110;
                    }
                    if (MainActivity4.this.ud > 1830) {
                        MainActivity4.this.ud = 1830;
                    }
                    short s = (short) MainActivity4.this.lr;
                    if (MainActivity4.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity4.this.ud;
                    if (MainActivity4.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                    if (MainActivity4.this.ud >= 1410) {
                        MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                    } else {
                        MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.3.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivity4.this.isTouch = false;
                    if (MainActivity4.this.timeCount1 != null) {
                        MainActivity4.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.3.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity4.this.timeCount1 != null) {
                                MainActivity4.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity4.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("down:1");
                    if (MainActivity4.this.ud <= 1410) {
                        MainActivity4.access$120(MainActivity4.this, 50);
                    }
                    if (MainActivity4.this.ud > 1410) {
                        MainActivity4.access$120(MainActivity4.this, 30);
                    }
                    if (MainActivity4.this.ud < 1110) {
                        MainActivity4.this.ud = 1110;
                    }
                    if (MainActivity4.this.ud > 1830) {
                        MainActivity4.this.ud = 1830;
                    }
                    short s = (short) MainActivity4.this.lr;
                    if (MainActivity4.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity4.this.ud;
                    if (MainActivity4.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                    if (MainActivity4.this.ud > 1410) {
                        MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                    } else {
                        MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.4.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                } else if (action == 1) {
                    MainActivity4.this.isTouch = false;
                    if (MainActivity4.this.timeCount2 != null) {
                        MainActivity4.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.4.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity4.this.timeCount2 != null) {
                                MainActivity4.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity4.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("left:1");
                    MainActivity4.access$212(MainActivity4.this, 30);
                    if (MainActivity4.this.lr < 1260) {
                        MainActivity4.this.lr = 1260;
                    }
                    if (MainActivity4.this.lr > 2190) {
                        MainActivity4.this.lr = 2190;
                    }
                    short s = (short) MainActivity4.this.lr;
                    if (MainActivity4.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity4.this.ud;
                    if (MainActivity4.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                    if (MainActivity4.this.ud > 1410) {
                        MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                    } else {
                        MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.5.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                } else if (action == 1) {
                    MainActivity4.this.isTouch = false;
                    if (MainActivity4.this.timeCount3 != null) {
                        MainActivity4.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.5.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity4.this.timeCount3 != null) {
                                MainActivity4.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity4.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("right:1");
                    MainActivity4.access$220(MainActivity4.this, 30);
                    if (MainActivity4.this.lr < 1260) {
                        MainActivity4.this.lr = 1260;
                    }
                    if (MainActivity4.this.lr > 2190) {
                        MainActivity4.this.lr = 2190;
                    }
                    short s = (short) MainActivity4.this.lr;
                    if (MainActivity4.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity4.this.ud;
                    if (MainActivity4.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                    if (MainActivity4.this.ud > 1410) {
                        MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                    } else {
                        MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.6.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                } else if (action == 1) {
                    MainActivity4.this.isTouch = false;
                    if (MainActivity4.this.timeCount4 != null) {
                        MainActivity4.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.6.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity4.this.timeCount4 != null) {
                                MainActivity4.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity4.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity4.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity4.9
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivity4.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivity4.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity4.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity4.10
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                int progressVal = indicatorSeekBar3.getProgress();

                if (MainActivity4.this.modeCate == 0 && MainActivity4.this.modeNum == 8) {

                    indicatorSeekBar3.setProgress(0.0f);

                    progressVal = 0;

                }

                final int progress = progressVal;
                if (progress != 0 && MainActivity4.this.velobar.getProgress() < 5) {
                    MainActivity4.this.velobar.setProgress(5.0f);
                    MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[4] ^ 99), -91});
                    MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.10.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity4.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.10.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity4.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivity4.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity4.this.r_tv.setText("" + progress);
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity4.11
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                if (MainActivity4.this.forbid == 1) {
                    MainActivity4 mainActivity4 = MainActivity4.this;
                    ShowHelper.showAlertDialog(mainActivity4, mainActivity4.getResources().getString(R.string.alert), MainActivity4.this.getResources().getString(R.string.forbid_alert));
                    return;
                }
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivity4.this.modeCate == 0 && MainActivity4.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivity4.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivity4.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivity4.this.rotatebar.getProgress() != 0) {
                    MainActivity4.this.r_tv.setText("0");
                    MainActivity4.this.rotatebar.setProgress(0.0f);
                    MainActivity4.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
            }
        });
        this.layout_adjust = (RelativeLayout) findViewById(R.id.layout_adjust);
        this.tennipic2 = (RelativeLayout) findViewById(R.id.tennipic2);
        this.tennipic3 = (RelativeLayout) findViewById(R.id.tennipic3);
        this.tennipic4 = (RelativeLayout) findViewById(R.id.tennipic4);
        this.tennipic5 = (RelativeLayout) findViewById(R.id.tennipic5);
        this.blenoty = (TextView) findViewById(R.id.connect);
        this.select_dianwei = (TextView) findViewById(R.id.select_dianwei);
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity4.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity4.this.self.setVisibility(8);
                    MainActivity4.this.group.setVisibility(0);
                    MainActivity4.this.points.setVisibility(0);
                    MainActivity4.this.hintpoints.setVisibility(0);
                    MainActivity4.this.tennipic2.setVisibility(8);
                    MainActivity4.this.tennipic3.setVisibility(8);
                    MainActivity4.this.tennipic4.setVisibility(8);
                    MainActivity4.this.tennipic5.setVisibility(8);
                    MainActivity4.this.fourbtn.setVisibility(8);
                    MainActivity4.this.bg_four.setVisibility(8);
                    MainActivity4.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivity4.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivity4.this.density * 36.0f);
                    MainActivity4.this.start_layout.setLayoutParams(layoutParams);
                    MainActivity4.this.start_layout.setGravity(17);
                    MainActivity4.this.modeCate = 1;
                    return;
                }
                MainActivity4.this.self.setVisibility(0);
                MainActivity4.this.group.setVisibility(8);
                MainActivity4.this.points.setVisibility(8);
                MainActivity4.this.hintpoints.setVisibility(8);
                MainActivity4.this.tennipic2.setVisibility(0);
                MainActivity4.this.bg_input.setVisibility(8);
                MainActivity4.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivity4.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivity4.this.density * 90.0f);
                MainActivity4.this.start_layout.setLayoutParams(layoutParams2);
                MainActivity4.this.start_layout.setGravity(83);
                MainActivity4.this.fourbtn.setVisibility(0);
                MainActivity4.this.bg_four.setVisibility(0);
                if (MainActivity4.this.modeNum == 2) {
                    MainActivity4.this.tennipic3.setVisibility(0);
                    MainActivity4.this.tennipic4.setVisibility(8);
                    MainActivity4.this.tennipic5.setVisibility(8);
                }
                if (MainActivity4.this.modeNum == 3) {
                    MainActivity4.this.tennipic3.setVisibility(8);
                    MainActivity4.this.tennipic4.setVisibility(0);
                    MainActivity4.this.tennipic5.setVisibility(8);
                }
                if (MainActivity4.this.modeNum == 4) {
                    MainActivity4.this.tennipic3.setVisibility(8);
                    MainActivity4.this.tennipic4.setVisibility(8);
                    MainActivity4.this.tennipic5.setVisibility(0);
                }
                MainActivity4.this.modeCate = 0;
            }
        });
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity4.this.blenoty.getText().toString().trim().contains(MainActivity4.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    if (MainActivity4.bleDevice != null) {
                        MainActivity4.this.connect(MainActivity4.bleDevice);
                    } else {
                        MainActivity4.this.checkPermissions();
                    }
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.disconnected));
                    MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity4.14
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivity4.this.radioNum = 0;
                        MainActivity4.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivity4.this.radioNum = 1;
                        MainActivity4.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivity4.this.radioNum = 2;
                        MainActivity4.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity4.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity4.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity4.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity4.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity4.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity4.15.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity4.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity4.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity4.this, MainActivity4.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity4 mainActivity4 = MainActivity4.this;
                ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.no_route_name));
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass16());
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity4 mainActivity4 = MainActivity4.this;
                mainActivity4.valueSelect = Integer.parseInt(mainActivity4.value_h.getText().toString().trim());
                MainActivity4.access$4408(MainActivity4.this);
                if (MainActivity4.this.valueSelect > 13) {
                    MainActivity4.this.valueSelect = 13;
                    return;
                }
                MainActivity4 mainActivity42 = MainActivity4.this;
                ShowHelper.showProgressDialog(mainActivity42, mainActivity42.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.17.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.17.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity4.this.value_h.setText("" + MainActivity4.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.17.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.17.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.sendBaseData(1);
                    }
                }, 50L);
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity4 mainActivity4 = MainActivity4.this;
                mainActivity4.valueSelect = Integer.parseInt(mainActivity4.value_h.getText().toString().trim());
                MainActivity4.access$4410(MainActivity4.this);
                if (MainActivity4.this.valueSelect < 1) {
                    MainActivity4.this.valueSelect = 1;
                    return;
                }
                MainActivity4 mainActivity42 = MainActivity4.this;
                ShowHelper.showProgressDialog(mainActivity42, mainActivity42.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.18.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.18.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity4.this.value_h.setText("" + MainActivity4.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.18.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.18.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.sendBaseData(1);
                    }
                }, 50L);
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity4.this.select_dianwei.getText() != null && !MainActivity4.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity4.this.alert_dialog_input();
                } else {
                    MainActivity4 mainActivity4 = MainActivity4.this;
                    ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.no_point_select));
                }
            }
        });
        connect(bleDevice);
        getWindow().setSoftInputMode(2);
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* renamed from: com.pusun.pusuntennis.MainActivity4$16, reason: invalid class name */
    class AnonymousClass16 implements View.OnClickListener {
        AnonymousClass16() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity4.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity4.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity4.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity4.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity4.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity4.16.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivity4.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivity4.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivity4.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivity4.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivity4.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivity4.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivity4.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity4.this.freq.getProgress() - 1;
                                        MainActivity4.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity4.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivity4.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity4.this.velobar.getProgress() - 1;
                                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivity4.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity4.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity4.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivity4.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivity4.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivity4.this.valueSelect = item2;
                                    MainActivity4.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.16.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.sendBaseData(1);
                                    }
                                }, 160L);
                                return;
                            }
                        }
                    }
                });
                optionPicker.show();
                return;
            }
            MainActivity4 mainActivity4 = MainActivity4.this;
            ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.no_route_name));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUrlTxt() {
        new Thread(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.20
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("https://www.pusuntech.com/Download/stop.txt").openStream()));
                    final StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                            AppLog.e("" + readLine);
                        } else {
                            bufferedReader.close();
                            Looper.prepare();
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.20.1
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    String[] split = sb.toString().split(";");
                                    int i = 0;
                                    while (true) {
                                        if (i < split.length) {
                                            if (com.pusun.pusuntennis.utils.Util.getDeviceName(MainActivity4.bleDevice).equals(split[i].toString().trim())) {
                                                ShowHelper.showAlertDialog(MainActivity4.this, MainActivity4.this.getResources().getString(R.string.alert), MainActivity4.this.getResources().getString(R.string.forbid_alert));
                                                SharedPreferences.Editor edit = MainActivity4.this.getSharedPreferences(MainActivity4.FORBID_INFO, 0).edit();
                                                edit.putInt(MainActivity4.FORBID_INFO, 1);
                                                edit.commit();
                                                SharedPreferences sharedPreferences = MainActivity4.this.getSharedPreferences(MainActivity4.FORBID_INFO, 0);
                                                AppLog.e("" + sharedPreferences.getInt(MainActivity4.FORBID_INFO, 0));
                                                MainActivity4.this.forbid = sharedPreferences.getInt(MainActivity4.FORBID_INFO, 0);
                                                break;
                                            }
                                            i++;
                                        } else {
                                            SharedPreferences.Editor edit2 = MainActivity4.this.getSharedPreferences(MainActivity4.FORBID_INFO, 0).edit();
                                            edit2.putInt(MainActivity4.FORBID_INFO, 0);
                                            edit2.commit();
                                            SharedPreferences sharedPreferences2 = MainActivity4.this.getSharedPreferences(MainActivity4.FORBID_INFO, 0);
                                            AppLog.e("" + sharedPreferences2.getInt(MainActivity4.FORBID_INFO, 0));
                                            MainActivity4.this.forbid = sharedPreferences2.getInt(MainActivity4.FORBID_INFO, 0);
                                            break;
                                        }
                                    }
                                }
                            }, 1000L);
                            Looper.loop();
                            return;
                        }
                    }
                } catch (MalformedURLException e) {
                    AppLog.e("strUrl:" + e.toString());
                } catch (IOException e2) {
                    AppLog.e("strIO:" + e2.toString());
                }
            }
        }).start();
    }

    private void showTvs(int[] iArr) {
        int i = 0;
        while (true) {
            int[] iArr2 = this.tvids;
            if (i >= iArr2.length) {
                break;
            }
            this.tvs[iArr2[i]].setVisibility(4);
            i++;
        }
        for (int i2 : iArr) {
            this.tvs[i2].setVisibility(0);
        }
    }

    private void showPos(int[] iArr) {
        int i = 0;
        while (true) {
            int[] iArr2 = this.poids;
            if (i >= iArr2.length) {
                break;
            }
            this.pos[iArr2[i]].setVisibility(4);
            i++;
        }
        if (iArr.length < 7) {
            this.selectPoints.clear();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                this.pos[iArr[i2]].setVisibility(0);
                this.selectPoints.add(Integer.valueOf(iArr[i2] + 1));
            }
            showPoints();
            return;
        }
        this.selectPoints.clear();
        this.select_dianwei.setText("");
        for (int i3 : iArr) {
            this.pos[i3].setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPoints() {
        if (this.selectPoints.size() != 0) {
            for (int i = 0; i < this.poids.length; i++) {
                this.pos[i].setBackground(getResources().getDrawable(R.drawable.tenniball2));
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < this.selectPoints.size(); i2++) {
                int i3 = 0;
                while (true) {
                    if (i3 >= this.poids.length) {
                        break;
                    }
                    if (this.selectPoints.get(i2).intValue() == this.poids[i3] + 1) {
                        this.pos[i3].setBackground(getResources().getDrawable(R.drawable.tenniball));
                        break;
                    }
                    i3++;
                }
                if (i2 != this.selectPoints.size() - 1) {
                    stringBuffer.append(this.selectPoints.get(i2) + ",");
                } else {
                    stringBuffer.append(this.selectPoints.get(i2));
                }
            }
            this.select_dianwei.setText(stringBuffer);
            return;
        }
        this.select_dianwei.setText("");
        for (int i4 = 0; i4 < this.poids.length; i4++) {
            this.pos[i4].setBackground(getResources().getDrawable(R.drawable.tenniball2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & 255).toUpperCase();
            if (upperCase.length() < 2) {
                sb.append(0);
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    private void sendBaseData() {
        int i = 0;
        while (i < BasicData2.b2.length) {
            final int final_i = i;
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData2.b2[i][0] >> 8), (byte) BasicData2.b2[i][0], (byte) (BasicData2.b2[i][1] >> 8), (byte) BasicData2.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData2.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData2.b2[i][0])) ^ ((byte) (BasicData2.b2[i][1] >> 8))) ^ ((byte) BasicData2.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData2.b2[i][0]) + "上下：" + ((int) BasicData2.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.21
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity4.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.22
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i3 = 10;
        while (i3 < BasicData2.b2.length) {
            final int final_i3 = i3;
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData2.b2[i3][0] >> 8), (byte) BasicData2.b2[i3][0], (byte) (BasicData2.b2[i3][1] >> 8), (byte) BasicData2.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData2.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData2.b2[i3][0])) ^ ((byte) (BasicData2.b2[i3][1] >> 8))) ^ ((byte) BasicData2.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData2.b2[i3][0]) + "上下：" + ((int) BasicData2.b2[i3][1]) + "byte:" + MainActivity4.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.22.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i3 + "条指令");
                            MainActivity4.this.writeBleData(bArr2);
                        }
                    }, (long) (i3 * 10));
                    i3 = i4;
                }
            }
        }, 900L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBaseData(int i) {
        if (i == 0) {
            sendBaseData();
            return;
        }
        short s = 8;
        char c = 1;
        if (i == 1) {
            int i2 = (this.valueSelect - this.baseHt) * 30;
            int i3 = 0;
        while (i3 < BasicData2.b3.length) {
            final int final_i3 = i3;
                int i4 = i3 + 1;
                byte b = (byte) i4;
                final byte[] bArr = {-86, b, (byte) (BasicData2.b3[i3][0] >> s), (byte) BasicData2.b3[i3][0], (byte) ((BasicData2.b3[i3][1] - i2) >> 8), (byte) (BasicData2.b3[i3][1] - i2), 0, 0, (byte) ((((((byte) (BasicData2.b3[i3][0] >> 8)) ^ b) ^ ((byte) BasicData2.b3[i3][0])) ^ ((byte) ((BasicData2.b3[i3][1] - i2) >> s))) ^ ((byte) (BasicData2.b3[i3][1] - i2))), -91};
                AppLog.e("左右：" + ((int) BasicData2.b3[i3][0]) + "上下：" + (BasicData2.b3[i3][1] - i2) + "byte:" + bytesToHexString(bArr));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.23
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + final_i3 + "条指令");
                        MainActivity4.this.writeBleData(bArr);
                    }
                }, (long) (i3 * 10));
                i3 = i4;
                s = 8;
            }
                final int final_i2 = i2;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.24
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int i5 = 10;
                    while (i5 < BasicData2.b3.length) {
                        int i6 = i5 + 1;
                        byte b2 = (byte) i6;
                        final byte[] bArr2 = {-86, b2, (byte) (BasicData2.b3[i5][0] >> 8), (byte) BasicData2.b3[i5][0], (byte) ((BasicData2.b3[i5][1] - final_i2) >> 8), (byte) (BasicData2.b3[i5][1] - final_i2), 0, 0, (byte) ((((((byte) (BasicData2.b3[i5][0] >> 8)) ^ b2) ^ ((byte) BasicData2.b3[i5][0])) ^ ((byte) ((BasicData2.b3[i5][1] - final_i2) >> 8))) ^ ((byte) (BasicData2.b3[i5][1] - final_i2))), -91};
                        AppLog.e("左右：" + ((int) BasicData2.b3[i5][0]) + "上下：" + (BasicData2.b3[i5][1] - final_i2) + "byte:" + MainActivity4.bytesToHexString(bArr2));
                final int final_i5 = i5;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.24.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_i5 + "条指令");
                                MainActivity4.this.writeBleData(bArr2);
                            }
                        }, (long) (i5 * 10));
                        i5 = i6;
                    }
                }
            }, 900L);
            return;
        }
        int i5 = 0;
        while (i5 < BasicData2.b4.length) {
            int i6 = i5 + 1;
            byte b2 = (byte) i6;
            byte b3 = (byte) ((((((byte) (BasicData2.b4[i5][0] >> 8)) ^ b2) ^ ((byte) BasicData2.b4[i5][0])) ^ ((byte) (BasicData2.b4[i5][c] >> 8))) ^ ((byte) BasicData2.b4[i5][c]));
            byte b4 = (byte) (BasicData2.b4[i5][0] >> 8);
            byte b5 = (byte) BasicData2.b4[i5][0];
            byte b6 = (byte) (BasicData2.b4[i5][c] >> 8);
            byte b7 = (byte) BasicData2.b4[i5][c];
            final byte[] bArr2 = new byte[10];
            bArr2[0] = -86;
            bArr2[c] = b2;
            bArr2[2] = b4;
            bArr2[3] = b5;
            bArr2[4] = b6;
            bArr2[5] = b7;
            bArr2[6] = 0;
            bArr2[7] = 0;
            bArr2[8] = b3;
            bArr2[9] = -91;
            AppLog.e("左右：" + ((int) BasicData2.b4[i5][0]) + "上下：" + ((int) BasicData2.b4[i5][c]) + "byte:" + bytesToHexString(bArr2));
                final int final_i5 = i5;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.25
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i5 + "条指令");
                    MainActivity4.this.writeBleData(bArr2);
                }
            }, (long) (i5 * 10));
            i5 = i6;
            c = 1;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.26
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i7 = 10;
                while (i7 < BasicData2.b4.length) {
                    int i8 = i7 + 1;
                    byte b8 = (byte) i8;
                    final byte[] bArr3 = {-86, b8, (byte) (BasicData2.b4[i7][0] >> 8), (byte) BasicData2.b4[i7][0], (byte) (BasicData2.b4[i7][1] >> 8), (byte) BasicData2.b4[i7][1], 0, 0, (byte) ((((((byte) (BasicData2.b4[i7][0] >> 8)) ^ b8) ^ ((byte) BasicData2.b4[i7][0])) ^ ((byte) (BasicData2.b4[i7][1] >> 8))) ^ ((byte) BasicData2.b4[i7][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData2.b4[i7][0]) + "上下：" + ((int) BasicData2.b4[i7][1]) + "byte:" + MainActivity4.bytesToHexString(bArr3));
                final int final_i7 = i7;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.26.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i7 + "条指令");
                            MainActivity4.this.writeBleData(bArr3);
                        }
                    }, (long) (i7 * 10));
                    i7 = i8;
                }
            }
        }, 900L);
    }

    private void sendBaseDataMore() {
        for (int i = 0; i < BasicData2.b5.length; i++) {
            final int final_i = i;
            byte b = (byte) (i + 19);
            final byte[] bArr = {-86, b, (byte) (BasicData2.b5[i][0] >> 8), (byte) BasicData2.b5[i][0], (byte) (BasicData2.b5[i][1] >> 8), (byte) BasicData2.b5[i][1], 0, 0, (byte) ((((((byte) (BasicData2.b5[i][0] >> 8)) ^ b) ^ ((byte) BasicData2.b5[i][0])) ^ ((byte) (BasicData2.b5[i][1] >> 8))) ^ ((byte) BasicData2.b5[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData2.b5[i][0]) + "上下：" + ((int) BasicData2.b5[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.27
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity4.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.28
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i2 = 0;
        while (i2 < BasicData2.b2.length) {
            final int final_i2 = i2;
                    int i3 = i2 + 1;
                    byte b2 = (byte) i3;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData2.b2[i2][0] >> 8), (byte) BasicData2.b2[i2][0], (byte) (BasicData2.b2[i2][1] >> 8), (byte) BasicData2.b2[i2][1], 0, 0, (byte) ((((((byte) (BasicData2.b2[i2][0] >> 8)) ^ b2) ^ ((byte) BasicData2.b2[i2][0])) ^ ((byte) (BasicData2.b2[i2][1] >> 8))) ^ ((byte) BasicData2.b2[i2][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData2.b2[i2][0]) + "上下：" + ((int) BasicData2.b2[i2][1]) + "byte:" + MainActivity4.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.28.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i2 + "条指令");
                            MainActivity4.this.writeBleData(bArr2);
                        }
                    }, (long) (i2 * 10));
                    i2 = i3;
                }
            }
        }, 900L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeBleData(byte[] bArr) {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PT0") || this.nameStar.startsWith("PT1"))) {
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity4.29
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivity4 mainActivity4 = MainActivity4.this;
                    ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PT2") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PT6")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity4.30
                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteFailure(BleException bleException) {
                    }

                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                        MainActivity4 mainActivity4 = MainActivity4.this;
                        ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.order_executed));
                    }
                });
            }
        }
    }

    public static List<String> getVideoFilesAllName(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles == null) {
            Log.e("error", "空目录");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < listFiles.length; i++) {
            if (!listFiles[i].isDirectory() && listFiles[i].getAbsolutePath().toString().contains("mp4")) {
                arrayList.add(listFiles[i].getAbsolutePath());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScan() {
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivity4.31
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivity4 mainActivity4 = MainActivity4.this;
                ShowHelper.showProgressDialog(mainActivity4, mainActivity4.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivity4 mainActivity4 = MainActivity4.this;
                ShowHelper.showProgressDialog(mainActivity4, mainActivity4.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivity4 mainActivity4 = MainActivity4.this;
                    ShowHelper.toastLong(mainActivity4, mainActivity4.getResources().getString(R.string.no_device_found));
                    if (MainActivity4.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.31.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.startScan();
                            }
                        }, 1000L);
                        MainActivity4.access$4708(MainActivity4.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PT")) {
                    MainActivity4.this.connect(list.get(0));
                    return;
                }
                final ArrayList arrayList = new ArrayList();
                arrayList.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName() != null && list.get(i).getName().trim().contains("PT")) {
                        arrayList.add(list.get(i));
                    }
                }
                if (arrayList.size() == 0) {
                    MainActivity4 mainActivity42 = MainActivity4.this;
                    ShowHelper.toastLong(mainActivity42, mainActivity42.getResources().getString(R.string.no_device_found));
                    if (MainActivity4.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.31.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.startScan();
                            }
                        }, 1000L);
                        MainActivity4.access$4708(MainActivity4.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivity4.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity4.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity4.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivity4.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity4.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity4.31.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivity4.this.connect((BleDevice) arrayList.get(i3));
                                return;
                            }
                        }
                    }
                });
                optionPicker.show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect(BleDevice bleDevice2) {
        // MAC-based connect fallback: if BleDevice parcelable lost its BluetoothDevice
        // reference after a disconnect, reconstruct the device from the adapter via MAC.
        String mac = (bleDevice2 != null) ? bleDevice2.getMac() : null;
        if (mac == null && deviceMac != null) {
            mac = deviceMac;
        }
        if (bleDevice2 == null || bleDevice2.getDevice() == null) {
            if (mac != null) {
                BleManager.getInstance().connect(mac, new BleGattCallback() {
                    @Override
                    public void onStartConnect() {
                        MainActivity4 mainActivity = MainActivity4.this;
                        ShowHelper.showProgressDialog(mainActivity, mainActivity.getResources().getString(R.string.connecting_device));
                    }
                    @Override
                    public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                        MainActivity4 mainActivity = MainActivity4.this;
                        ShowHelper.toastLong(mainActivity, mainActivity.getResources().getString(R.string.connect_failure_check));
                        ShowHelper.dismissProgressDialog();
                        MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.disconnected));
                        MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity4.this.signal_note.setText(MainActivity4.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                    }
                    @Override
                    public void onConnectSuccess(BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        ShowHelper.setProgressDialogMessage(MainActivity4.this.getResources().getString(R.string.initializing));
                        MainActivity4.this.connNum = 0;
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                                ShowHelper.toastShort(MainActivity4.this, MainActivity4.this.getResources().getString(R.string.please_use));
                            }
                        }, com.google.android.exoplayer2.C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        String resolvedName = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                        if (resolvedName == null || resolvedName.isEmpty()) resolvedName = MainActivity4.this.deviceName != null ? MainActivity4.this.deviceName : "";
                        MainActivity4.this.nameStar = resolvedName;
                        MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.connected));
                        MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_selector));
                        MainActivity4.this.signal_note.setText(MainActivity4.this.nameStar + MainActivity4.this.getResources().getString(R.string.connected));
                        MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_green));
                        MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_blue));
                        MainActivity4.this.isFaultOn = 0;
                        MainActivity4.this.gatt = bluetoothGatt;
                        MainActivity4.bleDevice = bleDevice3;
                        if (bleDevice3.getMac() != null) MainActivity4.this.deviceMac = bleDevice3.getMac();
                        MainActivity4.this.startNotify();
                    }
                    @Override
                    public void onDisConnected(boolean z, final BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.disconnected));
                        MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity4.this.signal_note.setText(MainActivity4.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                        MainActivity4.this.isFaultOn = 0;
                        if (z || MainActivity4.this.connNum >= 3) { return; }
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public void run() { MainActivity4.this.connect(bleDevice3); }
                        }, 1000L);
                    }
                });
            } else {
                android.util.Log.e("MainActivity4", "connect: bleDevice and mac are both null, cannot connect");
            }
            return;
        }
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivity4.32
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivity4 mainActivity4 = MainActivity4.this;
                ShowHelper.showProgressDialog(mainActivity4, mainActivity4.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(final BleDevice bleDevice3, BleException bleException) {
                MainActivity4 mainActivity4 = MainActivity4.this;
                ShowHelper.toastLong(mainActivity4, mainActivity4.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.disconnected));
                MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity4.this.signal_note.setText(MainActivity4.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                if (MainActivity4.this.connNum >= 3 || bleDevice3 == null) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity4.access$4708(MainActivity4.this);
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivity4.this.getResources().getString(R.string.initializing));
                MainActivity4.this.connNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivity4.this, MainActivity4.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                String rawNameMainAc = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                if (rawNameMainAc == null || rawNameMainAc.isEmpty()) rawNameMainAc = MainActivity4.this.deviceName != null ? MainActivity4.this.deviceName : "";
                MainActivity4.this.nameStar = rawNameMainAc;
                MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.connected));
                MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivity4.this.signal_note.setText(MainActivity4.this.nameStar + MainActivity4.this.getResources().getString(R.string.connected));
                MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_green));
                MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivity4.this.isFaultOn = 0;
                MainActivity4.this.gatt = bluetoothGatt;
                MainActivity4.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity4.this.velobar.getProgress() - 1;
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity4.this.freq.getProgress() - 1;
                        MainActivity4.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity4.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.7
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                if (!MainActivity4.this.btn_ball.getText().toString().equals(MainActivity4.this.getResources().getString(R.string.stop))) {
                    MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) 6, (byte) 1770, (byte) 6, (byte) 1590, 0, 0, 1, -91});
                }
                MainActivity4.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivity4.this.blenoty.setText(MainActivity4.this.getResources().getString(R.string.disconnected));
                MainActivity4.this.blenoty.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity4.this.signal.setBackground(MainActivity4.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity4.this.signal_note.setText(MainActivity4.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity4.this.signal_note.setTextColor(MainActivity4.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivity4.this.isFaultOn = 0;
                if (z || MainActivity4.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.32.9
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity4.access$4708(MainActivity4.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNotify() {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PT0") || this.nameStar.startsWith("PT1"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity4.33
                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onNotifyFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onNotifySuccess() {
                }

                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onCharacteristicChanged(byte[] bArr) {
                    AppLog.e("notify:" + bArr.length + "指令：" + (bArr[1] & 255));
                    if (bArr.length > 2 && (bArr[1] & 255) == 3) {
                        MainActivity4.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity4.this.isFaultOn == 0) {
                        MainActivity4.access$5308(MainActivity4.this);
                        MainActivity4.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PT2") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PT6"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity4.34
                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onNotifyFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onNotifySuccess() {
                }

                @Override // com.clj.fastble.callback.BleNotifyCallback
                public void onCharacteristicChanged(byte[] bArr) {
                    AppLog.e("notify:" + bArr.length + "指令：" + (bArr[1] & 255));
                    if (bArr.length > 2 && (bArr[1] & 255) == 3) {
                        AppLog.e("notifypower:" + bArr.length + "指令：" + (bArr[2] & 255));
                        MainActivity4.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity4.this.isFaultOn == 0) {
                        MainActivity4.access$5308(MainActivity4.this);
                        MainActivity4.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.35
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity4.this.checkPower();
            }
        }, 3600L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.36
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity4.this.sendBaseData(1);
            }
        }, 3800L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.37
            @Override // java.lang.Runnable
            public synchronized void run() {
                if (MainActivity4.this.forbid == 1) {
                    MainActivity4.this.writeBleData(new byte[]{-86, 99, 10, 0, 0, 0, 0, 0, 105, -91});
                }
            }
        }, 3900L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void batteryVolumeMsg(int i) {
        BatteryVolumeMsg batteryVolumeMsg = new BatteryVolumeMsg();
        batteryVolumeMsg.volume = i;
        EventBus.getDefault().post(batteryVolumeMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void faultMsg(int i) {
        ShowFaultMsg showFaultMsg = new ShowFaultMsg();
        showFaultMsg.code = i;
        EventBus.getDefault().post(showFaultMsg);
    }

    @Subcriber
    private void dealFaultDataEvent(ShowFaultMsg showFaultMsg) {
        if (showFaultMsg.code == 1) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.wheel_alert));
        }
        if (showFaultMsg.code == 2) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.gate_alert));
        }
    }

    @Subcriber
    private void dealBatteryVolumeEvent(BatteryVolumeMsg batteryVolumeMsg) {
        this.vo_tv.setText("" + batteryVolumeMsg.volume);
        int i = batteryVolumeMsg.volume;
        if (i < 45) {
            this.power1.setVisibility(8);
            this.power2.setVisibility(8);
            this.power3.setVisibility(8);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(0);
            int i2 = this.flagPower + 1;
            this.flagPower = i2;
            if (i2 <= 1) {
                ShowHelper.showAlertDialog(this, getResources().getString(R.string.notice), getResources().getString(R.string.no_power_alert));
            }
        }
        if (i < 55 && i >= 45) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(8);
            this.power3.setVisibility(8);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i < 61 && i >= 55) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(8);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i < 68 && i >= 61) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(0);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i >= 68) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(0);
            this.power4.setVisibility(0);
            this.power_no.setVisibility(8);
        }
    }

    private void changeOperate() {
        if (this.modeCate == 1) {
            this.points.setVisibility(0);
            this.group_cate.setVisibility(8);
            String str = this.nameStar;
            if (str != null && (str.startsWith("PT1") || this.nameStar.startsWith("PT2"))) {
                this.group_cate.setVisibility(8);
            }
            this.hintpoints.setVisibility(0);
            this.tennipic2.setVisibility(8);
            this.tennipic3.setVisibility(8);
            this.tennipic4.setVisibility(8);
            this.tennipic5.setVisibility(8);
            this.fourbtn.setVisibility(8);
            this.bg_four.setVisibility(8);
            this.bg_input.setVisibility(0);
            this.bg_input_big.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
            layoutParams.height = (int) (this.density * 44.0f);
            layoutParams.topMargin = (int) (this.density * 12.0f);
            this.start_layout.setLayoutParams(layoutParams);
            this.start_layout.setGravity(17);
            this.rg_hint.setVisibility(0);
            this.rgheight.setVisibility(0);
            this.savepoints.setVisibility(0);
            this.modeCate = 1;
            return;
        }
        this.points.setVisibility(8);
        this.hintpoints.setVisibility(8);
        this.tennipic2.setVisibility(0);
        this.bg_input.setVisibility(8);
        this.bg_input_big.setVisibility(8);
        this.group_cate.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
        layoutParams2.height = (int) (this.density * 90.0f);
        layoutParams2.topMargin = (int) (this.density * 25.0f);
        this.start_layout.setLayoutParams(layoutParams2);
        this.start_layout.setGravity(83);
        this.fourbtn.setVisibility(0);
        this.bg_four.setVisibility(0);
        this.rg_hint.setVisibility(0);
        this.rgheight.setVisibility(0);
        int i = this.modeNum;
        if (i == 1 || i == 2 || i == 5 || i == 7 || i == 8 || i == 9) {
            this.rg_hint.setVisibility(8);
            this.rgheight.setVisibility(8);
        }
        this.savepoints.setVisibility(8);
        this.modeCate = 0;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        BleManager.getInstance().disconnectAllDevice();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 2 && iArr.length > 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                if (iArr[i2] == 0) {
                    onPermissionGranted(strArr[i2]);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPermissions() {
        if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            BluetoothManager bluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (bluetoothManager != null) {
                BluetoothAdapter adapter = bluetoothManager.getAdapter();
                if (adapter != null) {
                    if (adapter.isEnabled()) {
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
        if (arrayList.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 2);
    }

    private void onPermissionGranted(String str) {
        str.hashCode();
        if (str.equals("android.permission.ACCESS_FINE_LOCATION")) {
            if (Build.VERSION.SDK_INT >= 23 && !checkGPSIsOpen()) {
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.39
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity4.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.38
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity4.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
                    }
                }).setCancelable(false).show();
            } else {
                connect(bleDevice);
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
            connect(bleDevice);
        }
        if (i == 116 && i2 == -1) {
            Toast.makeText(this, getResources().getString(R.string.blue_is_open), 0).show();
            checkPermissions();
        } else if (i == 116 && i2 == 0) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPower() {
        writeBleData(new byte[]{-86, 103, 0, 0, 0, 0, 0, 0, 103, -91});
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.forbid == 1) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.forbid_alert));
        }
        int id = view.getId();
        switch (id) {
            case R.id.btn_ball /* 2131361912 */:
                if (this.blenoty.getText().toString().equals(getResources().getString(R.string.disconnected))) {
                    ShowHelper.toastShort(this, getResources().getString(R.string.check_connect));
                    break;
                } else if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                    if (this.forbid == 1) {
                        ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.forbid_alert));
                        break;
                    } else {
                        if (this.modeCate == 0) {
                            int i = this.modeNum;
                            if (i == 1 || i == 7) {
                                AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.95
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                            }
                            if (this.modeNum == 2) {
                                if (this.tagH == 1) {
                                    writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                }
                                if (this.tagH == 2) {
                                    writeBleData(new byte[]{-86, 106, 2, 6, 10, 0, 0, 0, 100, -91});
                                }
                                if (this.tagH == 3) {
                                    writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 102, -91});
                                }
                                if (this.tagH == 4) {
                                    writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 102, -91});
                                }
                                if (this.tagH == 5) {
                                    writeBleData(new byte[]{-86, 106, 2, 6, 8, 10, 0, 0, 108, -91});
                                }
                                if (this.stopMode != 2) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.96
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                                            MainActivity4.this.velobar.setProgress(7.0f);
                                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                                        }
                                    }, 100L);
                                }
                            }
                            if (this.modeNum == 3) {
                                if (this.tagV == 1) {
                                    writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                }
                                if (this.tagV == 2) {
                                    writeBleData(new byte[]{-86, 106, 3, 3, 8, Ascii.CR, 0, 0, 111, -91});
                                }
                                if (this.tagV == 3) {
                                    writeBleData(new byte[]{-86, 106, 3, 3, Ascii.CR, 0, 0, 0, 103, -91});
                                }
                                if (this.stopMode != 3) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.97
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                                            MainActivity4.this.velobar.setProgress(7.0f);
                                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                                        }
                                    }, 100L);
                                }
                            }
                            if (this.modeNum == 4) {
                                writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                                if (this.stopMode != 4) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.98
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                                            MainActivity4.this.velobar.setProgress(7.0f);
                                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                                        }
                                    }, 100L);
                                }
                            }
                            int i2 = this.modeNum;
                            if (i2 == 5 || i2 == 8 || i2 == 9) {
                                AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.99
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                            }
                            if (this.modeNum == 6) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.100
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i3;
                                        int unused = MainActivity4.this.tagC;
                                        int i4 = 15;
                                        int i5 = 11;
                                        int i6 = MainActivity4.this.tagC == 2 ? 15 : 11;
                                        int i7 = 13;
                                        if (MainActivity4.this.tagC == 3) {
                                            i6 = 13;
                                            i3 = 1;
                                        } else {
                                            i3 = 3;
                                        }
                                        if (MainActivity4.this.tagC == 4) {
                                            i3 = 5;
                                        } else {
                                            i7 = i6;
                                        }
                                        if (MainActivity4.this.tagC == 5) {
                                            i3 = 5;
                                        } else {
                                            i5 = i7;
                                        }
                                        if (MainActivity4.this.tagC == 6) {
                                            i3 = 1;
                                        } else {
                                            i4 = i5;
                                        }
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 1, (byte) i4, (byte) i3, 0, 0, 0, (byte) ((i4 ^ 108) ^ i3), -91});
                                    }
                                }, 5L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.101
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 50L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.102
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                    }
                                }, 80L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.103
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                }, 110L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.104
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                }, 140L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.105
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 170L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.106
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 250L);
                                if (this.stopMode != 6) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.107
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                                            MainActivity4.this.velobar.setProgress(7.0f);
                                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                                        }
                                    }, 300L);
                                }
                            }
                            if (this.modeNum == 0) {
                                ShowHelper.toastShort(this, getResources().getString(R.string.select_one_mode));
                            }
                        } else if (this.selectPoints.size() == 0) {
                            ShowHelper.toastShort(this, getResources().getString(R.string.select_points));
                            break;
                        } else {
                            this.t1 = 0;
                            this.t2 = 0;
                            this.t3 = 0;
                            this.t4 = 0;
                            this.t5 = 0;
                            this.t6 = 0;
                            this.t7 = 0;
                            this.t8 = 0;
                            this.t9 = 0;
                            this.t10 = 0;
                            this.t11 = 0;
                            this.t12 = 0;
                            this.t13 = 0;
                            this.t14 = 0;
                            this.t15 = 0;
                            this.t16 = 0;
                            this.t17 = 0;
                            this.t18 = 0;
                            this.t19 = 0;
                            this.t20 = 0;
                            this.t21 = 0;
                            this.t22 = 0;
                            this.t23 = 0;
                            this.t24 = 0;
                            this.t25 = 0;
                            this.t26 = 0;
                            this.t27 = 0;
                            this.t28 = 0;
                            for (int i3 = 0; i3 < this.selectPoints.size(); i3++) {
                                if (i3 == 0) {
                                    this.t1 = this.selectPoints.get(0).intValue();
                                }
                                if (i3 == 1) {
                                    this.t2 = this.selectPoints.get(1).intValue();
                                }
                                if (i3 == 2) {
                                    this.t3 = this.selectPoints.get(2).intValue();
                                }
                                if (i3 == 3) {
                                    this.t4 = this.selectPoints.get(3).intValue();
                                }
                                if (i3 == 4) {
                                    this.t5 = this.selectPoints.get(4).intValue();
                                }
                                if (i3 == 5) {
                                    this.t6 = this.selectPoints.get(5).intValue();
                                }
                                if (i3 == 6) {
                                    this.t7 = this.selectPoints.get(6).intValue();
                                }
                                if (i3 == 7) {
                                    this.t8 = this.selectPoints.get(7).intValue();
                                }
                                if (i3 == 8) {
                                    this.t9 = this.selectPoints.get(8).intValue();
                                }
                                if (i3 == 9) {
                                    this.t10 = this.selectPoints.get(9).intValue();
                                }
                                if (i3 == 10) {
                                    this.t11 = this.selectPoints.get(10).intValue();
                                }
                                if (i3 == 11) {
                                    this.t12 = this.selectPoints.get(11).intValue();
                                }
                                if (i3 == 12) {
                                    this.t13 = this.selectPoints.get(12).intValue();
                                }
                                if (i3 == 13) {
                                    this.t14 = this.selectPoints.get(13).intValue();
                                }
                                if (i3 == 14) {
                                    this.t15 = this.selectPoints.get(14).intValue();
                                }
                                if (i3 == 15) {
                                    this.t16 = this.selectPoints.get(15).intValue();
                                }
                                if (i3 == 16) {
                                    this.t17 = this.selectPoints.get(16).intValue();
                                }
                                if (i3 == 17) {
                                    this.t18 = this.selectPoints.get(17).intValue();
                                }
                                if (i3 == 18) {
                                    this.t19 = this.selectPoints.get(18).intValue();
                                }
                                if (i3 == 19) {
                                    this.t20 = this.selectPoints.get(19).intValue();
                                }
                                if (i3 == 20) {
                                    this.t21 = this.selectPoints.get(20).intValue();
                                }
                                if (i3 == 21) {
                                    this.t22 = this.selectPoints.get(21).intValue();
                                }
                                if (i3 == 22) {
                                    this.t23 = this.selectPoints.get(22).intValue();
                                }
                                if (i3 == 23) {
                                    this.t24 = this.selectPoints.get(23).intValue();
                                }
                                if (i3 == 24) {
                                    this.t25 = this.selectPoints.get(24).intValue();
                                }
                                if (i3 == 25) {
                                    this.t26 = this.selectPoints.get(25).intValue();
                                }
                                if (i3 == 26) {
                                    this.t27 = this.selectPoints.get(26).intValue();
                                }
                                if (i3 == 27) {
                                    this.t28 = this.selectPoints.get(27).intValue();
                                }
                            }
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.108
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity4.this.t1, (byte) MainActivity4.this.t2, (byte) MainActivity4.this.t3, (byte) MainActivity4.this.t4, (byte) MainActivity4.this.t5, (byte) (((((MainActivity4.this.t1 ^ 108) ^ MainActivity4.this.t2) ^ MainActivity4.this.t3) ^ MainActivity4.this.t4) ^ MainActivity4.this.t5), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.109
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity4.this.t6, (byte) MainActivity4.this.t7, (byte) MainActivity4.this.t8, (byte) MainActivity4.this.t9, (byte) MainActivity4.this.t10, (byte) (((((MainActivity4.this.t6 ^ 111) ^ MainActivity4.this.t7) ^ MainActivity4.this.t8) ^ MainActivity4.this.t9) ^ MainActivity4.this.t10), -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.110
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity4.this.t11, (byte) MainActivity4.this.t12, (byte) MainActivity4.this.t13, (byte) MainActivity4.this.t14, (byte) MainActivity4.this.t15, (byte) (((((MainActivity4.this.t11 ^ 110) ^ MainActivity4.this.t12) ^ MainActivity4.this.t13) ^ MainActivity4.this.t14) ^ MainActivity4.this.t15), -91});
                                }
                            }, 100L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.111
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity4.this.t16, (byte) MainActivity4.this.t17, (byte) MainActivity4.this.t18, (byte) MainActivity4.this.t19, (byte) MainActivity4.this.t20, (byte) (((((MainActivity4.this.t16 ^ 105) ^ MainActivity4.this.t17) ^ MainActivity4.this.t18) ^ MainActivity4.this.t19) ^ MainActivity4.this.t20), -91});
                                }
                            }, 150L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.112
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity4.this.t21, (byte) MainActivity4.this.t22, (byte) MainActivity4.this.t23, (byte) MainActivity4.this.t24, (byte) MainActivity4.this.t25, (byte) (((((MainActivity4.this.t21 ^ 104) ^ MainActivity4.this.t22) ^ MainActivity4.this.t23) ^ MainActivity4.this.t24) ^ MainActivity4.this.t25), -91});
                                }
                            }, 200L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.113
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity4.this.t26, (byte) MainActivity4.this.t27, (byte) MainActivity4.this.t28, 0, 0, (byte) (((MainActivity4.this.t26 ^ 107) ^ MainActivity4.this.t27) ^ MainActivity4.this.t28), -91});
                                }
                            }, 250L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.114
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity4.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 320L);
                        }
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.115
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 270L);
                        this.btn_ball.setText(getResources().getString(R.string.stop));
                        this.signal_note.setText(getResources().getString(R.string.status_on));
                        this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                        this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                        break;
                    }
                } else {
                    this.stopMode = this.modeNum;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.116
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 260L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.117
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.checkPower();
                        }
                    }, 190L);
                    writeBleData(new byte[]{-86, 107, 0, 0, 0, 0, 0, 0, 107, -91});
                    this.btn_ball.setText(getResources().getString(R.string.start));
                    this.signal_note.setText(getResources().getString(R.string.status_stop));
                    this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                    this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_dark_selector));
                    break;
                }
            case R.id.clear /* 2131361946 */:
                if (this.selectPoints.size() > 0) {
                    List<Integer> list = this.selectPoints;
                    list.remove(list.size() - 1);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.down_up /* 2131361990 */:
                this.tagV = 3;
                if (this.tagV == 2) {
                    this.tableName.setText(getResources().getString(R.string.vertical_circulation));
                }
                if (this.tagV == 3) {
                    this.tableName.setText(getResources().getString(R.string.vertical_deep_light));
                }
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData2.a13[0];
                    short s = BasicData2.a13[1];
                    this.ud = s;
                    short s2 = (short) this.lr;
                    short s3 = s;
                    writeBleData(new byte[]{-86, 108, (byte) (s2 >> 8), (byte) s2, (byte) (s3 >> 8), (byte) s3, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                hideUD();
                int i4 = this.modeNum;
                if (i4 != 3) {
                    if (i4 == 5 || i4 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.50
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.51
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.52
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                            MainActivity4.this.velobar.setProgress(7.0f);
                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                        }
                    }, 150L);
                }
                this.modeNum = 3;
                this.modeCate = 0;
                changeOperate();
                if (this.tagV == 1) {
                    showTvs(this.v1);
                }
                if (this.tagV == 2) {
                    showTvs(this.v2);
                }
                if (this.tagV == 3) {
                    showTvs(this.v3);
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.53
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2) {
                            if (this.tagH == 1) {
                                writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                            }
                            if (this.tagH == 2) {
                                writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.NAK, 0, 0, 0, 114, -91});
                            }
                            if (this.tagH == 3) {
                                writeBleData(new byte[]{-86, 106, 2, Ascii.DLE, Ascii.DC4, 0, 0, 0, 110, -91});
                            }
                            if (this.tagH == 4) {
                                writeBleData(new byte[]{-86, 106, 2, 17, 19, 0, 0, 0, 106, -91});
                            }
                            if (this.tagH == 5) {
                                writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.DC2, Ascii.NAK, 0, 0, 96, -91});
                            }
                        }
                        if (this.modeNum == 3) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.54
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity4.this.tagV == 1) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                    if (MainActivity4.this.tagV == 2) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 3, 3, 8, Ascii.CR, 0, 0, 111, -91});
                                    }
                                    if (MainActivity4.this.tagV == 3) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 3, 3, Ascii.CR, 0, 0, 0, 103, -91});
                                    }
                                }
                            }, 200L);
                        }
                        if (this.modeNum == 4) {
                            writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                            break;
                        }
                    }
                }
                break;
            case R.id.group_cross /* 2131362113 */:
                int i5 = this.tagC + 1;
                this.tagC = i5;
                if (i5 > 2) {
                    this.tagC = 1;
                }
                this.tableName.setText(getResources().getString(R.string.cross1));
                if (this.tagC == 1) {
                    this.tableName.setText(getResources().getString(R.string.cross1));
                }
                if (this.tagC == 2) {
                    this.tableName.setText(getResources().getString(R.string.cross2));
                }
                if (this.tagC == 3) {
                    this.tableName.setText(getResources().getString(R.string.cross3));
                }
                if (this.tagC == 4) {
                    this.tableName.setText(getResources().getString(R.string.cross4));
                }
                if (this.tagC == 5) {
                    this.tableName.setText(getResources().getString(R.string.cross5));
                }
                if (this.tagC == 6) {
                    this.tableName.setText(getResources().getString(R.string.cross6));
                }
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData2.a13[0];
                    short s4 = BasicData2.a13[1];
                    this.ud = s4;
                    short s5 = (short) this.lr;
                    short s6 = s4;
                    writeBleData(new byte[]{-86, 108, (byte) (s5 >> 8), (byte) s5, (byte) (s6 >> 8), (byte) s6, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                int i6 = this.modeNum;
                if (i6 != 6) {
                    if (i6 == 5 || i6 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.60
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.61
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.62
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                            MainActivity4.this.velobar.setProgress(7.0f);
                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                        }
                    }, 280L);
                }
                this.modeNum = 6;
                this.modeCate = 0;
                changeOperate();
                this.fourbtn.setVisibility(8);
                this.bg_four.setVisibility(8);
                this.bg_input.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
                layoutParams.height = (int) (this.density * 46.0f);
                layoutParams.topMargin = (int) (this.density * 20.0f);
                this.start_layout.setLayoutParams(layoutParams);
                this.start_layout.setGravity(1);
                this.tennipic5.setVisibility(0);
                if (this.tagC == 1) {
                    showTvs(this.c1);
                }
                if (this.tagC == 2) {
                    showTvs(this.c2);
                }
                if (this.tagC == 3) {
                    showTvs(this.c3);
                }
                if (this.tagC == 4) {
                    showTvs(this.c4);
                }
                if (this.tagC == 5) {
                    showTvs(this.c5);
                }
                if (this.tagC == 6) {
                    showTvs(this.c6);
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.63
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 250L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.64
                        @Override // java.lang.Runnable
                        public void run() {
                            int i7;
                            int unused = MainActivity4.this.tagC;
                            int i8 = 15;
                            int i9 = 11;
                            int i10 = MainActivity4.this.tagC == 2 ? 15 : 11;
                            int i11 = 13;
                            if (MainActivity4.this.tagC == 3) {
                                i10 = 13;
                                i7 = 1;
                            } else {
                                i7 = 3;
                            }
                            if (MainActivity4.this.tagC == 4) {
                                i7 = 5;
                            } else {
                                i11 = i10;
                            }
                            if (MainActivity4.this.tagC == 5) {
                                i7 = 5;
                            } else {
                                i9 = i11;
                            }
                            if (MainActivity4.this.tagC == 6) {
                                i7 = 1;
                            } else {
                                i8 = i9;
                            }
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 1, (byte) i8, (byte) i7, 0, 0, 0, (byte) ((i8 ^ 108) ^ i7), -91});
                        }
                    }, 5L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.65
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.66
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                        }
                    }, 80L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.67
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                        }
                    }, 110L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.68
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                        }
                    }, 140L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.69
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 170L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.70
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                        }
                    }, 210L);
                    break;
                }
                break;
            case R.id.high_point /* 2131362124 */:
                this.tableName.setText(getResources().getString(R.string.smash_p));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                int i7 = this.modeNum;
                if (i7 != 5) {
                    this.isNumDing = 0;
                    if (i7 != 5 && i7 != 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.55
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.56
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.lr = 1770;
                    this.ud = 1210;
                    short s7 = (short) 1770;
                    short s8 = (short) 1210;
                    writeBleData(new byte[]{-86, 108, (byte) (s7 >> 8), (byte) s7, (byte) (s8 >> 8), (byte) s8, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                this.modeNum = 5;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.57
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.velobar.getProgress();
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[3], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[3] ^ 99), -91});
                        MainActivity4.this.velobar.setProgress(4.0f);
                        MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[3]);
                    }
                }, 50L);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.58
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 5) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.59
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.hit /* 2131362126 */:
                int i8 = this.tagHT + 1;
                this.tagHT = i8;
                if (i8 > 1) {
                    this.tagHT = 1;
                }
                this.tableName.setText(getResources().getString(R.string.volley_p1));
                this.tableName.setText(getResources().getString(R.string.volley_p1));
                if (this.tagHT == 1) {
                    this.tableName.setText(getResources().getString(R.string.volley_p1));
                }
                if (this.tagHT == 2) {
                    this.tableName.setText(getResources().getString(R.string.volley_p2));
                }
                if (this.tagHT == 3) {
                    this.tableName.setText(getResources().getString(R.string.volley_p3));
                }
                this.hit.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i9 = this.tagHT;
                if (i9 == 1) {
                    this.lr = 1770;
                }
                if (i9 == 2) {
                    this.lr = 1250;
                }
                if (i9 == 3) {
                    this.lr = 2050;
                }
                this.lr = 1770;
                this.ud = 1590;
                short s9 = (short) 1770;
                short s10 = (short) 1590;
                writeBleData(new byte[]{-86, 108, (byte) (s9 >> 8), (byte) s9, (byte) (s10 >> 8), (byte) s10, 0, 0, 1, -91});
                this.num_lr.setText("" + (74 - (this.lr / 30)));
                if (this.ud > 1410) {
                    this.num_ud.setText("" + (64 - (this.ud / 30)));
                } else {
                    this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                }
                this.isNumDing = 1;
                int i10 = this.modeNum;
                if (i10 != 7) {
                    if (i10 == 5 || i10 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.71
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.72
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.isNumDing = 0;
                    int i11 = this.modeNum;
                    if (i11 == 5 || i11 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.73
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.74
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                }
                this.modeNum = 7;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.75
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[5], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[5] ^ 99), -91});
                        MainActivity4.this.velobar.setProgress(6);
                        MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[5]);
                    }
                }, 50L);
                if (this.tagHT == 1) {
                    showTvs(this.ht1);
                }
                if (this.tagHT == 2) {
                    showTvs(this.ht2);
                }
                if (this.tagHT == 3) {
                    showTvs(this.ht3);
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.76
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.77
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 200L);
                    break;
                }
                break;
            case R.id.left_right /* 2131362169 */:
                this.tagH = 4;
                if (this.tagH == 2) {
                    this.tableName.setText(getResources().getString(R.string.horizontal_wide_line));
                }
                if (this.tagH == 3) {
                    this.tableName.setText(getResources().getString(R.string.horizontal_middle_line));
                }
                if (this.tagH == 4) {
                    this.tableName.setText(getResources().getString(R.string.horizontal_two_line));
                }
                if (this.tagH == 5) {
                    this.tableName.setText(getResources().getString(R.string.horizontal_three_line));
                }
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                hideLR();
                this.lr = BasicData2.a13[0];
                short s11 = BasicData2.a13[1];
                this.ud = s11;
                short s12 = (short) this.lr;
                short s13 = s11;
                writeBleData(new byte[]{-86, 108, (byte) (s12 >> 8), (byte) s12, (byte) (s13 >> 8), (byte) s13, 0, 0, 1, -91});
                this.num_lr.setText("" + (74 - (this.lr / 30)));
                if (this.ud > 1410) {
                    this.num_ud.setText("" + (64 - (this.ud / 30)));
                } else {
                    this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                }
                showUD();
                int i12 = this.modeNum;
                if (i12 != 2) {
                    if (i12 == 5 || i12 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.45
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.46
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.47
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                            MainActivity4.this.velobar.setProgress(7.0f);
                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                        }
                    }, 150L);
                }
                this.modeNum = 2;
                this.modeCate = 0;
                changeOperate();
                if (this.tagH == 1) {
                    showTvs(this.h1);
                }
                if (this.tagH == 2) {
                    showTvs(this.h2);
                }
                if (this.tagH == 3) {
                    showTvs(this.h3);
                }
                if (this.tagH == 4) {
                    showTvs(this.h4);
                }
                if (this.tagH == 5) {
                    showTvs(this.h5);
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.48
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2 && this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.49
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity4.this.tagH == 1) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                    if (MainActivity4.this.tagH == 2) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 2, 6, 10, 0, 0, 0, 114, -91});
                                    }
                                    if (MainActivity4.this.tagH == 3) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 110, -91});
                                    }
                                    if (MainActivity4.this.tagH == 4) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 106, -91});
                                    }
                                    if (MainActivity4.this.tagH == 5) {
                                        MainActivity4.this.writeBleData(new byte[]{-86, 106, 2, 6, 8, 10, 0, 0, 96, -91});
                                    }
                                }
                            }, 200L);
                        }
                        if (this.modeNum == 3) {
                            writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                        }
                        if (this.modeNum == 4) {
                            writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                            break;
                        }
                    }
                }
                break;
            case R.id.moon /* 2131362210 */:
                this.tableName.setText(getResources().getString(R.string.moon));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                int i13 = this.modeNum;
                if (i13 != 9) {
                    this.isNumDing = 0;
                    if (i13 == 5 || i13 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.84
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.85
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.lr = 1770;
                    this.ud = 1470;
                    short s14 = (short) 1770;
                    short s15 = (short) 1470;
                    writeBleData(new byte[]{-86, 108, (byte) (s14 >> 8), (byte) s14, (byte) (s15 >> 8), (byte) s15, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                this.modeNum = 9;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.86
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.velobar.getProgress();
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[7], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[7] ^ 99), -91});
                        MainActivity4.this.velobar.setProgress(8.0f);
                        MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[7]);
                    }
                }, 50L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.87
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.rotatebar.setProgress(6.0f);
                        MainActivity4.this.r_tv.setText("6");
                        MainActivity4.this.writeBleData(new byte[]{-86, 98, 1, Ascii.RS, 0, 0, 0, 0, 125, -91});
                    }
                }, 150L);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.88
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 9) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.89
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.num22 /* 2131362267 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(22);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num23 /* 2131362269 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(23);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num24 /* 2131362271 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(24);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num25 /* 2131362273 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(25);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num26 /* 2131362275 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(26);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num3 /* 2131362280 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(3);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num4 /* 2131362291 */:
                if (this.selectPoints.size() < 28) {
                    this.selectPoints.add(4);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.place /* 2131362332 */:
                this.tableName.setText(getResources().getString(R.string.place));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                int i14 = this.modeNum;
                if (i14 != 8) {
                    this.isNumDing = 0;
                    if (i14 != 5 && i14 != 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.78
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.79
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.lr = 1770;
                    this.ud = 1470;
                    short s16 = (short) 1770;
                    short s17 = (short) 1470;
                    writeBleData(new byte[]{-86, 108, (byte) (s16 >> 8), (byte) s16, (byte) (s17 >> 8), (byte) s17, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                this.modeNum = 8;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.80
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        if (MainActivity4.this.velobar.getProgress() > 2) {
                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[0], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[0] ^ 99), -91});
                            MainActivity4.this.velobar.setProgress(1.0f);
                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[0]);
                        }
                    }
                }, 50L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.81
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.rotatebar.setProgress(0.0f);
                        MainActivity4.this.r_tv.setText("0");
                        MainActivity4.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 99, -91});
                    }
                }, 150L);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.82
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 8) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.83
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.selection /* 2131362414 */:
                this.tableName.setText(getResources().getString(R.string.program_p));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(0);
                this.modeCate = 1;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.90
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                        MainActivity4.this.velobar.setProgress(7.0f);
                        MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                    }
                }, 50L);
                break;
            case R.id.self_point /* 2131362423 */:
                int i15 = this.tagFix + 1;
                this.tagFix = i15;
                if (i15 > 1) {
                    this.tagFix = 1;
                }
                this.tableName.setText(getResources().getString(R.string.fix_p1));
                if (this.tagFix == 1) {
                    this.tableName.setText(getResources().getString(R.string.fix_p1));
                }
                if (this.tagFix == 2) {
                    this.tableName.setText(getResources().getString(R.string.fix_p2));
                }
                if (this.tagFix == 3) {
                    this.tableName.setText(getResources().getString(R.string.fix_p3));
                }
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i16 = this.tagFix;
                if (i16 == 1) {
                    this.lr = 1770;
                }
                if (i16 == 2) {
                    this.lr = 1320;
                }
                if (i16 == 3) {
                    this.lr = 2130;
                }
                this.ud = 1590;
                short s18 = (short) this.lr;
                short s19 = (short) 1590;
                writeBleData(new byte[]{-86, 108, (byte) (s18 >> 8), (byte) s18, (byte) (s19 >> 8), (byte) s19, 0, 0, 1, -91});
                this.num_lr.setText("" + (74 - (this.lr / 30)));
                if (this.ud > 1410) {
                    this.num_ud.setText("" + (64 - (this.ud / 30)));
                } else {
                    this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                }
                this.isNumDing = 1;
                int i17 = this.modeNum;
                if (i17 != 1) {
                    this.isNumDing = 0;
                    if (i17 == 5 || i17 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.40
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.41
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                }
                this.modeNum = 1;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.42
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                        MainActivity4.this.velobar.setProgress(7.0f);
                        MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                    }
                }, 50L);
                if (this.tagFix == 1) {
                    showTvs(this.fx1);
                }
                if (this.tagFix == 2) {
                    showTvs(this.fx2);
                }
                if (this.tagFix == 3) {
                    showTvs(this.fx3);
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.43
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.44
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 200L);
                    break;
                }
                break;
            case R.id.whole /* 2131362658 */:
                this.tableName.setText(getResources().getString(R.string.random_p));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                this.group_cate.setVisibility(8);
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showTvs(this.tvids15);
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData2.a13[0];
                    short s20 = BasicData2.a13[1];
                    this.ud = s20;
                    short s21 = (short) this.lr;
                    short s22 = s20;
                    writeBleData(new byte[]{-86, 108, (byte) (s21 >> 8), (byte) s21, (byte) (s22 >> 8), (byte) s22, 0, 0, 1, -91});
                    this.num_lr.setText("" + (74 - (this.lr / 30)));
                    if (this.ud > 1410) {
                        this.num_ud.setText("" + (64 - (this.ud / 30)));
                    } else {
                        this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                    }
                }
                int i18 = this.modeNum;
                if (i18 != 4) {
                    if (i18 == 5 || i18 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.91
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.92
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.93
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity4.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity4.this.veloNums[6] ^ 99), -91});
                            MainActivity4.this.velobar.setProgress(7.0f);
                            MainActivity4.this.v_tv.setText("" + MainActivity4.this.veloTins[6]);
                        }
                    }, 150L);
                }
                this.modeNum = 4;
                this.modeCate = 0;
                changeOperate();
                this.fourbtn.setVisibility(8);
                this.bg_four.setVisibility(8);
                this.bg_input.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (this.density * 46.0f);
                layoutParams2.topMargin = (int) (this.density * 20.0f);
                this.start_layout.setLayoutParams(layoutParams2);
                this.start_layout.setGravity(1);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.94
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2) {
                            writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                        }
                        if (this.modeNum == 3) {
                            writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                        }
                        if (this.modeNum == 4) {
                            writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                            break;
                        }
                    }
                }
                break;
            default:
                switch (id) {
                    case R.id.num1 /* 2131362251 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(1);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num10 /* 2131362252 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(10);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num11 /* 2131362253 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(11);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num12 /* 2131362254 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(12);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num13 /* 2131362255 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(13);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num14 /* 2131362256 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(14);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num15 /* 2131362257 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(15);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num16 /* 2131362258 */:
                        if (this.selectPoints.size() < 28) {
                            this.selectPoints.add(16);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    default:
                        switch (id) {
                            case R.id.num17 /* 2131362260 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(17);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num18 /* 2131362261 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(18);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num19 /* 2131362262 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(19);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num2 /* 2131362263 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(2);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num20 /* 2131362264 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(20);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num21 /* 2131362265 */:
                                if (this.selectPoints.size() < 28) {
                                    this.selectPoints.add(21);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            default:
                                switch (id) {
                                    case R.id.num27 /* 2131362277 */:
                                        if (this.selectPoints.size() < 28) {
                                            this.selectPoints.add(27);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num28 /* 2131362278 */:
                                        if (this.selectPoints.size() < 28) {
                                            this.selectPoints.add(28);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    default:
                                        switch (id) {
                                            case R.id.num5 /* 2131362299 */:
                                                if (this.selectPoints.size() < 28) {
                                                    this.selectPoints.add(5);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num6 /* 2131362300 */:
                                                if (this.selectPoints.size() < 28) {
                                                    this.selectPoints.add(6);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num7 /* 2131362301 */:
                                                if (this.selectPoints.size() < 28) {
                                                    this.selectPoints.add(7);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num8 /* 2131362302 */:
                                                if (this.selectPoints.size() < 28) {
                                                    this.selectPoints.add(8);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num9 /* 2131362303 */:
                                                if (this.selectPoints.size() < 28) {
                                                    this.selectPoints.add(9);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                        }
                                }
                        }
                }
        }
    }

    private void hideUD() {
        this.note_ud.setVisibility(8);
        this.num_ud.setVisibility(8);
        this.u_ad.setVisibility(8);
        this.d_ad.setVisibility(8);
    }

    private void showUD() {
        this.note_ud.setVisibility(0);
        this.num_ud.setVisibility(0);
        this.u_ad.setVisibility(0);
        this.d_ad.setVisibility(0);
    }

    private void showLR() {
        this.note_lr.setVisibility(0);
        this.num_lr.setVisibility(0);
        this.l_ad.setVisibility(0);
        this.ri_ad.setVisibility(0);
    }

    private void hideLR() {
        this.note_lr.setVisibility(8);
        this.num_lr.setVisibility(8);
        this.l_ad.setVisibility(8);
        this.ri_ad.setVisibility(8);
    }

    class TimeCount1 extends CountDownTimer {
        public TimeCount1(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MainActivity4.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity4.this.ud > 1400) {
                    MainActivity4.access$112(MainActivity4.this, 30);
                }
                if (MainActivity4.this.ud <= 1400) {
                    MainActivity4.access$112(MainActivity4.this, 100);
                }
                if (MainActivity4.this.ud < 800) {
                    MainActivity4.this.ud = 800;
                }
                if (MainActivity4.this.ud > 1860) {
                    MainActivity4.this.ud = 1860;
                }
                short s = (short) MainActivity4.this.lr;
                if (MainActivity4.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity4.this.ud;
                if (MainActivity4.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                if (MainActivity4.this.ud > 1410) {
                    MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                } else {
                    MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                }
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        }
    }

    class TimeCount2 extends CountDownTimer {
        public TimeCount2(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MainActivity4.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity4.this.ud <= 1400) {
                    MainActivity4.access$120(MainActivity4.this, 100);
                }
                if (MainActivity4.this.ud > 1400) {
                    MainActivity4.access$120(MainActivity4.this, 30);
                }
                if (MainActivity4.this.ud < 800) {
                    MainActivity4.this.ud = 800;
                }
                if (MainActivity4.this.ud > 1860) {
                    MainActivity4.this.ud = 1860;
                }
                short s = (short) MainActivity4.this.lr;
                if (MainActivity4.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity4.this.ud;
                if (MainActivity4.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                if (MainActivity4.this.ud > 1410) {
                    MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                } else {
                    MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                }
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        }
    }

    class TimeCount3 extends CountDownTimer {
        public TimeCount3(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MainActivity4.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity4.access$212(MainActivity4.this, 30);
                if (MainActivity4.this.lr < 1140) {
                    MainActivity4.this.lr = 1140;
                }
                if (MainActivity4.this.lr > 2040) {
                    MainActivity4.this.lr = 2040;
                }
                short s = (short) MainActivity4.this.lr;
                if (MainActivity4.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity4.this.ud;
                if (MainActivity4.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                if (MainActivity4.this.ud > 1410) {
                    MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                } else {
                    MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        }
    }

    class TimeCount4 extends CountDownTimer {
        public TimeCount4(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            MainActivity4.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity4.access$220(MainActivity4.this, 30);
                if (MainActivity4.this.lr < 1140) {
                    MainActivity4.this.lr = 1140;
                }
                if (MainActivity4.this.lr > 2040) {
                    MainActivity4.this.lr = 2040;
                }
                short s = (short) MainActivity4.this.lr;
                if (MainActivity4.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity4.this.ud;
                if (MainActivity4.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity4.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity4.this.num_lr.setText("" + (74 - (MainActivity4.this.lr / 30)));
                if (MainActivity4.this.ud > 1410) {
                    MainActivity4.this.num_ud.setText("" + (64 - (MainActivity4.this.ud / 30)));
                } else {
                    MainActivity4.this.num_ud.setText("" + (((1410 - MainActivity4.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity4.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity4.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        }
    }

    private class SpinnerAdapter extends ArrayAdapter<String> {
        Context context;
        String[] items;

        public SpinnerAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            this.items = strArr;
            this.context = context;
        }

        @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.context).inflate(android.R.layout.simple_spinner_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(this.items[i]);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setTextSize(16.0f);
            return view;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.context).inflate(android.R.layout.simple_spinner_item, viewGroup, false);
            }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(this.items[i]);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setTextSize(16.0f);
            return view;
        }
    }

    public void alert_dialog_input() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.118
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity4.119
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity4 mainActivity4 = MainActivity4.this;
                    ShowHelper.toastShort(mainActivity4, mainActivity4.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity4.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivity4.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivity4.this.freq.getProgress());
                seleDao.setItem1(MainActivity4.this.radioNum);
                seleDao.setItem2(MainActivity4.this.valueSelect);
                seleDao.setVelo(MainActivity4.this.velobar.getProgress());
                seleDao.setRote(MainActivity4.this.rotatebar.getProgress());
                MainActivity4.this.daoSession.insertOrReplace(seleDao);
                create.dismiss();
            }
        });
        create.show();
    }
}
