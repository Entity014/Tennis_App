package com.pusun.pusuntennis;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import cn.qqtheme.framework.picker.OptionPicker;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;

import com.google.common.base.Ascii;
import com.kyleduo.switchbutton.SwitchButton;
import com.pusun.pusuntennis.utils.AppLog;
import com.pusun.pusuntennis.utils.BLEServiceParameters;
import com.pusun.pusuntennis.utils.BasicData22;
import com.pusun.pusuntennis.utils.BasicData3;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.DefaultDao;
import com.pusun.pusuntennis.utils.dao.SeleDao;
import com.pusun.pusuntennis.utils.dao.VariDao;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivityPadX extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static BleDevice bleDevice;
    private String deviceMac;
    private String deviceName;
    private TextView back_m_add;
    private TextView back_m_de;
    private TextView back_m_value;
    private TextView backadd2;
    private TextView backde2;
    private TextView backvalue2;
    private Button bajada;
    private Button bandeja;
    private View bg_four;
    private View bg_input;
    private View bg_input_big;
    private TextView blenoty;
    private Button btn_ball;
    private Button change_get;
    private Button change_point;
    private Button change_tennis;
    private RelativeLayout change_velo_layout2;
    private Button clear;
    private RelativeLayout control_all;
    private TextView d_ad;
    private DaoSession daoSession;
    private TextView delepoints;
    private TextView delepoints2;
    float density;
    private EditText dian_num;
    private Button down_up;
    private EditText et1;
    private EditText et2;
    private TextView f_ad;
    private TextView f_de;
    private TextView f_tv;
    private RelativeLayout fourbtn;
    private IndicatorSeekBar freq;
    private TextView freq_up;
    private TextView freqadd;
    private TextView freqde;
    private TextView front_m_add;
    private TextView front_m_de;
    private TextView front_m_value;
    private TextView frontadd2;
    private TextView frontde2;
    private TextView frontvalue2;
    BluetoothGatt gatt;
    private LinearLayout group;
    private RelativeLayout group_cate;
    private Button group_cross;
    private TextView h_ad;
    private TextView h_de;
    private Button high_point;
    private TextView hintpoints;
    private Button hit;
    private EditText interval;
    private RelativeLayout knob_freq;
    private RelativeLayout knob_speed;
    private TextView l_ad;
    private EditText l_r;
    private TextView lastpoints;
    private TextView lastpoints2;
    private TextView lastpoints3;
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
    private TextView num29;
    private TextView num3;
    private TextView num30;
    private TextView num31;
    private TextView num32;
    private TextView num33;
    private TextView num34;
    private TextView num35;
    private TextView num36;
    private TextView num37;
    private TextView num38;
    private TextView num39;
    private TextView num4;
    private TextView num40;
    private TextView num41;
    private TextView num5;
    private TextView num6;
    private TextView num7;
    private TextView num8;
    private TextView num9;
    private TextView num_lr;
    private TextView num_ud;
    private RelativeLayout pinlv;
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
    private TextView roadd;
    private TextView rode;
    private IndicatorSeekBar rotatebar;
    private EditText s_d;
    private Button savedefault;
    private TextView savepoints;
    private TextView savepoints2;
    private TextView select_dianwei;
    private TextView select_dianwei2;
    private Button selection;
    private LinearLayout self;
    private Button self_point;
    private TextView self_program;
    private TextView self_program2;
    private RelativeLayout self_re;
    private RelativeLayout self_re2;
    private Button serve_return;
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
    private TextView snum29;
    private TextView snum3;
    private TextView snum30;
    private TextView snum31;
    private TextView snum32;
    private TextView snum33;
    private TextView snum34;
    private TextView snum35;
    private TextView snum4;
    private TextView snum5;
    private TextView snum6;
    private TextView snum7;
    private TextView snum8;
    private TextView snum9;
    private TextView spadd;
    private TextView spde;
    private Spinner spinner;
    private Spinner spinner_zu;
    private LinearLayout start_layout;
    private Button step;
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
    private int t29;
    private int t3;
    private int t30;
    private int t31;
    private int t32;
    private int t33;
    private int t34;
    private int t35;
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
    private RelativeLayout tennipic6;
    private RelativeLayout tennipic7;
    private TimeCount1 timeCount1;
    private TimeCount2 timeCount2;
    private TimeCount3 timeCount3;
    private TimeCount4 timeCount4;
    private int[] tvids;
    private TextView[] tvs;
    private TextView u_ad;
    private EditText u_d;
    private Button u_dian;
    private int ud;
    private RelativeLayout up_velo_layout;
    private TextView v_ad;
    private TextView v_de;
    private TextView v_tv;
    private TextView value_h;
    private TextView velo_up;
    private IndicatorSeekBar velobar;
    private TextView vo_tv;
    private Button whole;
    private List<Integer> selectPoints = new ArrayList();
    private int modeNum = 1;
    private int modeCate = 0;
    private int vari_point_num = 1;
    private int[] frequentNums = {88, 78, 68, 58, 48, 38, 33, 28, 23, 18};
    private int[] veloNums = {66, 76, 80, 83, 87, 91, 95, 100, 105, 115, 125, TsExtractor.TS_STREAM_TYPE_E_AC3, 145};
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
    private final int[] h1 = {14, 15, 16, 17, 18, 19, 20};
    private final int[] h2 = {14, 20};
    private final int[] h3 = {15, 19};
    private final int[] h4 = {16, 18};
    private final int[] h5 = {14, 17, 20};
    private final int[] wh1 = {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
    private final int[] v1 = {3, 10, 17, 24};
    private final int[] v2 = {3, 10, 17, 24};
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
    private int baseHt = 10;
    private int valueSelect = 10;
    List<DefaultDao> defaultDaoList = new ArrayList();
    private int saveColor = 0;
    private String daoNameIng = "fix1";

    static /* synthetic */ int access$308(MainActivityPadX mainActivityPadX) {
        int i = mainActivityPadX.valueSelect;
        mainActivityPadX.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(MainActivityPadX mainActivityPadX) {
        int i = mainActivityPadX.valueSelect;
        mainActivityPadX.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$412(MainActivityPadX mainActivityPadX, int i) {
        int i2 = mainActivityPadX.lr + i;
        mainActivityPadX.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$420(MainActivityPadX mainActivityPadX, int i) {
        int i2 = mainActivityPadX.lr - i;
        mainActivityPadX.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$512(MainActivityPadX mainActivityPadX, int i) {
        int i2 = mainActivityPadX.ud + i;
        mainActivityPadX.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$520(MainActivityPadX mainActivityPadX, int i) {
        int i2 = mainActivityPadX.ud - i;
        mainActivityPadX.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$7508(MainActivityPadX mainActivityPadX) {
        int i = mainActivityPadX.connNum;
        mainActivityPadX.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$8108(MainActivityPadX mainActivityPadX) {
        int i = mainActivityPadX.isFaultOn;
        mainActivityPadX.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_pad_x);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivityPadX$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivityPadX.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.switchbtn = (SwitchButton) findViewById(R.id.switchbtn);
        this.tableName = (TextView) findViewById(R.id.tableName);
        this.self = (LinearLayout) findViewById(R.id.self);
        this.group = (LinearLayout) findViewById(R.id.group);
        this.change_velo_layout2 = (RelativeLayout) findViewById(R.id.change_velo_layout2);
        this.control_all = (RelativeLayout) findViewById(R.id.control_all);
        this.pinlv = (RelativeLayout) findViewById(R.id.pinlv);
        this.up_velo_layout = (RelativeLayout) findViewById(R.id.up_velo_layout);
        this.velo_up = (TextView) findViewById(R.id.velo_up);
        this.freq_up = (TextView) findViewById(R.id.freq_up);
        this.knob_freq = (RelativeLayout) findViewById(R.id.knob_freq);
        this.knob_speed = (RelativeLayout) findViewById(R.id.knob_freq);
        this.bandeja = (Button) findViewById(R.id.bandeja);
        this.bajada = (Button) findViewById(R.id.bajada);
        this.serve_return = (Button) findViewById(R.id.serve_return);
        this.bandeja.setOnClickListener(this);
        this.bajada.setOnClickListener(this);
        this.serve_return.setOnClickListener(this);
        this.freqde = (TextView) findViewById(R.id.freqde);
        this.freqadd = (TextView) findViewById(R.id.freqadd);
        this.spde = (TextView) findViewById(R.id.spde);
        this.spadd = (TextView) findViewById(R.id.spadd);
        this.rode = (TextView) findViewById(R.id.rode);
        this.roadd = (TextView) findViewById(R.id.roadd);
        this.self_program = (TextView) findViewById(R.id.self_program);
        this.self_program2 = (TextView) findViewById(R.id.self_program2);
        this.self_re2 = (RelativeLayout) findViewById(R.id.self_re2);
        this.self_re = (RelativeLayout) findViewById(R.id.self_re);
        this.points = (RelativeLayout) findViewById(R.id.points);
        this.delepoints = (TextView) findViewById(R.id.delepoints);
        this.lastpoints = (TextView) findViewById(R.id.lastpoints);
        this.savepoints = (TextView) findViewById(R.id.savepoints);
        this.delepoints2 = (TextView) findViewById(R.id.delepoints2);
        this.lastpoints2 = (TextView) findViewById(R.id.lastpoints2);
        this.savepoints2 = (TextView) findViewById(R.id.savepoints2);
        this.lastpoints3 = (TextView) findViewById(R.id.lastpoints3);
        this.dian_num = (EditText) findViewById(R.id.dian_num);
        this.l_r = (EditText) findViewById(R.id.l_r);
        this.u_d = (EditText) findViewById(R.id.u_d);
        this.s_d = (EditText) findViewById(R.id.s_d);
        this.interval = (EditText) findViewById(R.id.interval);
        this.u_dian = (Button) findViewById(R.id.u_dian);
        Button button = (Button) findViewById(R.id.change_tennis);
        this.change_tennis = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                java.util.List<com.clj.fastble.data.BleDevice> connected = com.clj.fastble.BleManager.getInstance().getAllConnectedDevice();
                final com.clj.fastble.data.BleDevice currentDevice = (connected != null && !connected.isEmpty()) ? connected.get(0) : bleDevice;
                com.clj.fastble.BleManager.getInstance().disconnectAllDevice();
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.changing));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ShowHelper.dismissProgressDialog();
                        Intent intent = new Intent(MainActivityPadX.this, (Class<?>) MainActivityXV.class);
                        intent.putExtra("device", currentDevice);
                        intent.putExtra("device_name", com.pusun.pusuntennis.utils.Util.getDeviceName(currentDevice));
                        MainActivityPadX.this.startActivity(intent);
                    }
                }, 1500L);
            }
        });
        Button button2 = (Button) findViewById(R.id.savedefault);
        this.savedefault = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivityPadX.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivityPadX.this.freq.getProgress());
                    defaultDao.setGrade(MainActivityPadX.this.valueSelect);
                    defaultDao.setItem2(MainActivityPadX.this.lr);
                    defaultDao.setItem3(MainActivityPadX.this.ud);
                    defaultDao.setVelo(MainActivityPadX.this.velobar.getProgress());
                    defaultDao.setRote(MainActivityPadX.this.rotatebar.getProgress());
                    if (MainActivityPadX.this.modeCate == 0) {
                        if (MainActivityPadX.this.modeNum != 1) {
                            if (MainActivityPadX.this.modeNum != 7) {
                                if (MainActivityPadX.this.modeNum != 5) {
                                    if (MainActivityPadX.this.modeNum != 4) {
                                        if (MainActivityPadX.this.modeNum != 2) {
                                            if (MainActivityPadX.this.modeNum != 3) {
                                                if (MainActivityPadX.this.modeNum != 6) {
                                                    if (MainActivityPadX.this.modeNum != 8) {
                                                        if (MainActivityPadX.this.modeNum != 9) {
                                                            if (MainActivityPadX.this.modeNum != 15) {
                                                                if (MainActivityPadX.this.modeNum != 16) {
                                                                    if (MainActivityPadX.this.modeNum == 17) {
                                                                        defaultDao.setDaoName("sreturn");
                                                                        defaultDao.setNumber(27L);
                                                                        MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                                    }
                                                                } else {
                                                                    defaultDao.setDaoName("bajada");
                                                                    defaultDao.setNumber(26L);
                                                                    MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                                }
                                                            } else {
                                                                defaultDao.setDaoName("bandeja");
                                                                defaultDao.setNumber(25L);
                                                                MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                            }
                                                        } else {
                                                            defaultDao.setDaoName("moon");
                                                            defaultDao.setNumber(24L);
                                                            MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                        }
                                                    } else {
                                                        defaultDao.setDaoName("place");
                                                        defaultDao.setNumber(23L);
                                                        MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                    }
                                                } else {
                                                    defaultDao.setDaoName("cross" + MainActivityPadX.this.tagC);
                                                    if (MainActivityPadX.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivityPadX.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivityPadX.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivityPadX.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivityPadX.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivityPadX.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivityPadX.this.tagV);
                                                if (MainActivityPadX.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivityPadX.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivityPadX.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivityPadX.this.tagH);
                                            if (MainActivityPadX.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivityPadX.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivityPadX.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivityPadX.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivityPadX.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivityPadX.this.tagHT);
                                if (MainActivityPadX.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivityPadX.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivityPadX.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivityPadX.this.tagFix);
                            if (MainActivityPadX.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivityPadX.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivityPadX.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivityPadX.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.save_default_success));
                    MainActivityPadX.this.savedefault.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivityPadX.this.saveColor = 0;
                    return;
                }
                MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                ShowHelper.toastShort(mainActivityPadX2, mainActivityPadX2.getResources().getString(R.string.no_change_default));
            }
        });
        this.frontvalue2 = (TextView) findViewById(R.id.frontvalue2);
        this.backvalue2 = (TextView) findViewById(R.id.backvalue2);
        this.front_m_value = (TextView) findViewById(R.id.front_m_value);
        this.back_m_value = (TextView) findViewById(R.id.back_m_value);
        this.frontde2 = (TextView) findViewById(R.id.frontde2);
        this.backde2 = (TextView) findViewById(R.id.backde2);
        this.frontadd2 = (TextView) findViewById(R.id.frontadd2);
        this.backadd2 = (TextView) findViewById(R.id.backadd2);
        this.front_m_de = (TextView) findViewById(R.id.front_m_de);
        this.front_m_add = (TextView) findViewById(R.id.front_m_add);
        this.back_m_de = (TextView) findViewById(R.id.back_m_de);
        this.back_m_add = (TextView) findViewById(R.id.back_m_add);
        this.frontde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] > 2370) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] = 2370;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[1] = (short) (sArr[1] + 100);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] > 4300) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] = 4300;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.frontadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] = (short) (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] - 30);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] < 90) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][0] = 90;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] = (short) (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] - 100);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] < 600) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][1] = 600;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[2] = (short) (sArr[2] - 5);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][2] < 40) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][2] = 40;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[2] = (short) (sArr[2] + 5);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][2] > 140) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][2] = 140;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][3] < 25) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][3] = 25;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData22.b3[MainActivityPadX.this.vari_point_num + 34];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][3] > 65) {
                    BasicData22.b3[MainActivityPadX.this.vari_point_num + 34][3] = 65;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
                int i = MainActivityPadX.this.vari_point_num + 34;
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) (MainActivityPadX.this.vari_point_num + 35), (byte) (BasicData22.b3[i][0] >> 8), (byte) BasicData22.b3[i][0], (byte) (BasicData22.b3[i][1] >> 8), (byte) BasicData22.b3[i][1], (byte) (BasicData22.b3[i][2] >> 8), (byte) BasicData22.b3[i][2], (byte) BasicData22.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.u_dian.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityPadX.this.dian_num.getText().toString().trim()).intValue();
                int intValue2 = Integer.valueOf(MainActivityPadX.this.l_r.getText().toString().trim()).intValue();
                int intValue3 = Integer.valueOf(MainActivityPadX.this.u_d.getText().toString().trim()).intValue();
                int intValue4 = Integer.valueOf(MainActivityPadX.this.s_d.getText().toString().trim()).intValue();
                MainActivityPadX.this.writeBleData(new byte[]{-86, (byte) intValue, (byte) (intValue2 >> 8), (byte) intValue2, (byte) (intValue3 >> 8), (byte) intValue3, (byte) (intValue4 >> 8), (byte) intValue4, (byte) Integer.valueOf(MainActivityPadX.this.interval.getText().toString().trim()).intValue(), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.lastpoints3.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX.this.showBottomDialog();
            }
        });
        this.vo_tv = (TextView) findViewById(R.id.vo_tv);
        this.power_lay = (RelativeLayout) findViewById(R.id.power_lay);
        this.group_cate = (RelativeLayout) findViewById(R.id.group_cate);
        this.layout_fast = (RelativeLayout) findViewById(R.id.layout_fast);
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
        this.bg_input_big = findViewById(R.id.bg_input_big);
        this.rgheight = (RelativeLayout) findViewById(R.id.rgheight);
        this.rgheight1 = (RadioGroup) findViewById(R.id.rgheight1);
        this.rg_hint = (TextView) findViewById(R.id.rg_hint);
        this.value_h = (TextView) findViewById(R.id.value_h);
        this.h_ad = (TextView) findViewById(R.id.h_ad);
        this.h_de = (TextView) findViewById(R.id.h_de);
        this.start_layout = (LinearLayout) findViewById(R.id.start_layout);
        this.note_ud = (TextView) findViewById(R.id.note_ud);
        this.note_lr = (TextView) findViewById(R.id.note_lr);
        this.num_ud = (TextView) findViewById(R.id.num_ud);
        this.num_lr = (TextView) findViewById(R.id.num_lr);
        this.lr = BasicData22.m18[0];
        this.ud = BasicData22.m18[1];
        this.num_lr.setText("" + ((this.lr / 30) + 2));
        this.num_ud.setText("" + (this.ud / 100));
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
        this.snum28 = (TextView) findViewById(R.id.snum28);
        this.snum29 = (TextView) findViewById(R.id.snum29);
        this.snum30 = (TextView) findViewById(R.id.snum30);
        this.snum31 = (TextView) findViewById(R.id.snum31);
        this.snum32 = (TextView) findViewById(R.id.snum32);
        this.snum33 = (TextView) findViewById(R.id.snum33);
        this.snum34 = (TextView) findViewById(R.id.snum34);
        TextView textView = (TextView) findViewById(R.id.snum35);
        this.snum35 = textView;
        this.tvs = new TextView[]{this.snum1, this.snum2, this.snum3, this.snum4, this.snum5, this.snum6, this.snum7, this.snum8, this.snum9, this.snum10, this.snum11, this.snum12, this.snum13, this.snum14, this.snum15, this.snum16, this.snum17, this.snum18, this.snum19, this.snum20, this.snum21, this.snum22, this.snum23, this.snum24, this.snum25, this.snum26, this.snum27, this.snum28, this.snum29, this.snum30, this.snum31, this.snum32, this.snum33, this.snum34, textView};
        this.tvids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34};
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
        this.num29 = (TextView) findViewById(R.id.num29);
        this.num30 = (TextView) findViewById(R.id.num30);
        this.num31 = (TextView) findViewById(R.id.num31);
        this.num32 = (TextView) findViewById(R.id.num32);
        this.num33 = (TextView) findViewById(R.id.num33);
        this.num34 = (TextView) findViewById(R.id.num34);
        this.num35 = (TextView) findViewById(R.id.num35);
        this.num36 = (TextView) findViewById(R.id.num36);
        this.num37 = (TextView) findViewById(R.id.num37);
        this.num38 = (TextView) findViewById(R.id.num38);
        this.num39 = (TextView) findViewById(R.id.num39);
        this.num40 = (TextView) findViewById(R.id.num40);
        this.num41 = (TextView) findViewById(R.id.num41);
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
        this.num29.setOnClickListener(this);
        this.num30.setOnClickListener(this);
        this.num31.setOnClickListener(this);
        this.num32.setOnClickListener(this);
        this.num33.setOnClickListener(this);
        this.num34.setOnClickListener(this);
        this.num35.setOnClickListener(this);
        this.num36.setOnClickListener(this);
        this.num37.setOnClickListener(this);
        this.num38.setOnClickListener(this);
        this.num39.setOnClickListener(this);
        this.num40.setOnClickListener(this);
        this.num41.setOnClickListener(this);
        this.pos = new TextView[]{this.num1, this.num2, this.num3, this.num4, this.num5, this.num6, this.num7, this.num8, this.num9, this.num10, this.num11, this.num12, this.num13, this.num14, this.num15, this.num16, this.num17, this.num18, this.num19, this.num20, this.num21, this.num22, this.num23, this.num24, this.num25, this.num26, this.num27, this.num28, this.num29, this.num30, this.num31, this.num32, this.num33, this.num34, this.num35, this.num36, this.num37, this.num38, this.num39, this.num40, this.num41};
        this.poids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
        this.self_point = (Button) findViewById(R.id.self_point);
        this.high_point = (Button) findViewById(R.id.high_point);
        this.left_right = (Button) findViewById(R.id.left_right);
        this.down_up = (Button) findViewById(R.id.down_up);
        this.whole = (Button) findViewById(R.id.whole);
        this.hit = (Button) findViewById(R.id.hit);
        this.place = (Button) findViewById(R.id.place);
        this.moon = (Button) findViewById(R.id.moon);
        this.step = (Button) findViewById(R.id.step);
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
        this.step.setOnClickListener(this);
        this.clear.setOnClickListener(this);
        this.selection.setOnClickListener(this);
        this.group_cross.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button3;
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.stop_ball);
        this.stop_ball = button4;
        button4.setOnClickListener(this);
        if (com.pusun.pusuntennis.utils.Util.getDeviceVersion(bleDevice) < 230712) {
            this.step.setVisibility(4);
            this.step.setClickable(false);
        }
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
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.13
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 1) {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    mainActivityPadX.showPos(mainActivityPadX.gr1);
                    return;
                }
                if (i == 2) {
                    MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                    mainActivityPadX2.showPos(mainActivityPadX2.gr2);
                    return;
                }
                if (i == 3) {
                    MainActivityPadX mainActivityPadX3 = MainActivityPadX.this;
                    mainActivityPadX3.showPos(mainActivityPadX3.gr3);
                    return;
                }
                if (i == 4) {
                    MainActivityPadX mainActivityPadX4 = MainActivityPadX.this;
                    mainActivityPadX4.showPos(mainActivityPadX4.gr4);
                    return;
                }
                if (i == 5) {
                    MainActivityPadX mainActivityPadX5 = MainActivityPadX.this;
                    mainActivityPadX5.showPos(mainActivityPadX5.gr5);
                    return;
                }
                if (i == 6) {
                    MainActivityPadX mainActivityPadX6 = MainActivityPadX.this;
                    mainActivityPadX6.showPos(mainActivityPadX6.gr6);
                    return;
                }
                if (i == 7) {
                    MainActivityPadX mainActivityPadX7 = MainActivityPadX.this;
                    mainActivityPadX7.showPos(mainActivityPadX7.gr7);
                    return;
                }
                if (i == 8) {
                    MainActivityPadX mainActivityPadX8 = MainActivityPadX.this;
                    mainActivityPadX8.showPos(mainActivityPadX8.gr8);
                    return;
                }
                if (i == 9) {
                    MainActivityPadX mainActivityPadX9 = MainActivityPadX.this;
                    mainActivityPadX9.showPos(mainActivityPadX9.gr9);
                    return;
                }
                if (i == 10) {
                    MainActivityPadX mainActivityPadX10 = MainActivityPadX.this;
                    mainActivityPadX10.showPos(mainActivityPadX10.gr10);
                } else if (i == 11) {
                    MainActivityPadX mainActivityPadX11 = MainActivityPadX.this;
                    mainActivityPadX11.showPos(mainActivityPadX11.gr11);
                } else if (i == 12) {
                    MainActivityPadX mainActivityPadX12 = MainActivityPadX.this;
                    mainActivityPadX12.showPos(mainActivityPadX12.gr12);
                } else {
                    MainActivityPadX mainActivityPadX13 = MainActivityPadX.this;
                    mainActivityPadX13.showPos(mainActivityPadX13.poids);
                }
            }
        });
        this.spinner_zu = (Spinner) findViewById(R.id.spinner_zu);
        this.spinner_zu.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.point_vari)));
        this.spinner_zu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.14
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    MainActivityPadX.this.vari_point_num = 1;
                } else if (i == 1) {
                    MainActivityPadX.this.vari_point_num = 2;
                } else if (i == 2) {
                    MainActivityPadX.this.vari_point_num = 3;
                } else if (i == 3) {
                    MainActivityPadX.this.vari_point_num = 4;
                } else if (i == 4) {
                    MainActivityPadX.this.vari_point_num = 5;
                } else if (i == 5) {
                    MainActivityPadX.this.vari_point_num = 6;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.showSelectPoint(mainActivityPadX.vari_point_num);
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
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.15
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("up:1");
                    MainActivityPadX.access$512(MainActivityPadX.this, 100);
                    if (MainActivityPadX.this.ud < 300) {
                        MainActivityPadX.this.ud = 300;
                    }
                    if (MainActivityPadX.this.ud > 4600) {
                        MainActivityPadX.this.ud = 4600;
                    }
                    if (MainActivityPadX.this.modeCate == 0 && ((MainActivityPadX.this.modeNum == 1 || MainActivityPadX.this.modeNum == 2) && MainActivityPadX.this.ud > 2000)) {
                        MainActivityPadX.this.ud = 2000;
                    }
                    short s = (short) MainActivityPadX.this.lr;
                    if (MainActivityPadX.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPadX.this.ud;
                    if (MainActivityPadX.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                    MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.15.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.15.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount1 != null) {
                                MainActivityPadX.this.timeCount1.cancel();
                            }
                            MainActivityPadX.this.timeCount1 = MainActivityPadX.this.new TimeCount1(20000L, 200L);
                            MainActivityPadX.this.timeCount1.start();
                        }
                    }, 1L);
                    MainActivityPadX.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivityPadX.this.isTouch = false;
                    if (MainActivityPadX.this.timeCount1 != null) {
                        MainActivityPadX.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.15.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount1 != null) {
                                MainActivityPadX.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.16
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("down:1");
                    MainActivityPadX.access$520(MainActivityPadX.this, 100);
                    if (MainActivityPadX.this.ud < 300) {
                        MainActivityPadX.this.ud = 300;
                    }
                    if (MainActivityPadX.this.ud > 6000) {
                        MainActivityPadX.this.ud = PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
                    }
                    if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 5 && MainActivityPadX.this.ud < 2000) {
                        MainActivityPadX.this.ud = 2000;
                    }
                    short s = (short) MainActivityPadX.this.lr;
                    if (MainActivityPadX.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPadX.this.ud;
                    if (MainActivityPadX.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                    MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.16.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount2 != null) {
                                MainActivityPadX.this.timeCount2.cancel();
                            }
                            MainActivityPadX.this.timeCount2 = MainActivityPadX.this.new TimeCount2(20000L, 200L);
                            MainActivityPadX.this.timeCount2.start();
                        }
                    }, 1L);
                    MainActivityPadX.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPadX.this.isTouch = false;
                    if (MainActivityPadX.this.timeCount2 != null) {
                        MainActivityPadX.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.16.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount2 != null) {
                                MainActivityPadX.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.17
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("left:1");
                    MainActivityPadX.access$412(MainActivityPadX.this, 30);
                    if (MainActivityPadX.this.lr < 90) {
                        MainActivityPadX.this.lr = 90;
                    }
                    if (MainActivityPadX.this.lr > 2370) {
                        MainActivityPadX.this.lr = 2370;
                    }
                    short s = (short) MainActivityPadX.this.lr;
                    if (MainActivityPadX.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPadX.this.ud;
                    if (MainActivityPadX.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                    MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.17.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount3 != null) {
                                MainActivityPadX.this.timeCount3.cancel();
                            }
                            MainActivityPadX.this.timeCount3 = MainActivityPadX.this.new TimeCount3(20000L, 200L);
                            MainActivityPadX.this.timeCount3.start();
                        }
                    }, 1L);
                    MainActivityPadX.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPadX.this.isTouch = false;
                    if (MainActivityPadX.this.timeCount3 != null) {
                        MainActivityPadX.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.17.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount3 != null) {
                                MainActivityPadX.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("right:1");
                    MainActivityPadX.access$420(MainActivityPadX.this, 30);
                    if (MainActivityPadX.this.lr < 90) {
                        MainActivityPadX.this.lr = 90;
                    }
                    if (MainActivityPadX.this.lr > 2370) {
                        MainActivityPadX.this.lr = 2370;
                    }
                    short s = (short) MainActivityPadX.this.lr;
                    if (MainActivityPadX.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPadX.this.ud;
                    if (MainActivityPadX.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                    MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.18.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.18.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount4 != null) {
                                MainActivityPadX.this.timeCount4.cancel();
                            }
                            MainActivityPadX.this.timeCount4 = MainActivityPadX.this.new TimeCount4(20000L, 200L);
                            MainActivityPadX.this.timeCount4.start();
                        }
                    }, 1L);
                    MainActivityPadX.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPadX.this.isTouch = false;
                    if (MainActivityPadX.this.timeCount4 != null) {
                        MainActivityPadX.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.18.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPadX.this.timeCount4 != null) {
                                MainActivityPadX.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.21
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivityPadX.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivityPadX.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityPadX.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivityPadX.this.f_tv.setText("" + i);
                    MainActivityPadX.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.22.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityPadX.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    MainActivityPadX.this.f_tv.setText("" + i);
                    MainActivityPadX.this.freq.setProgress((float) i);
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[intValue], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[intValue] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.24
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                int progressVal = indicatorSeekBar3.getProgress();

                if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 8) {

                    indicatorSeekBar3.setProgress(0.0f);

                    progressVal = 0;

                }

                final int progress = progressVal;
                if (progress != 0 && MainActivityPadX.this.velobar.getProgress() < 5) {
                    MainActivityPadX.this.velobar.setProgress(5.0f);
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[4] ^ 99), -91});
                    MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.24.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.24.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.24.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityPadX.this.r_tv.setText("" + progress);
                MainActivityPadX.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPadX.this.rotatebar.getProgress();
                if (progress > -6) {
                    int iVal = progress - 1;

                    if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 8) {

                        MainActivityPadX.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivityPadX.this.velobar.getProgress() < 5) {
                        MainActivityPadX.this.velobar.setProgress(5.0f);
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[4] ^ 99), -91});
                        MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.25.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPadX.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.25.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.25.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPadX.this.r_tv.setText("" + i);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPadX.this.rotatebar.getProgress();
                if (progress < 6) {
                    int iVal = progress + 1;

                    if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 8) {

                        MainActivityPadX.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivityPadX.this.velobar.getProgress() < 5) {
                        MainActivityPadX.this.velobar.setProgress(5.0f);
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[4] ^ 99), -91});
                        MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPadX.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.26.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.26.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPadX.this.r_tv.setText("" + i);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.27
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivityPadX.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivityPadX.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivityPadX.this.rotatebar.getProgress() != 0) {
                    MainActivityPadX.this.r_tv.setText("0");
                    MainActivityPadX.this.rotatebar.setProgress(0.0f);
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.27.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivityPadX.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPadX.this.velobar.getProgress();
                if (progress > 1) {
                    int i = progress - 1;
                    if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 5 && i > 6) {
                        MainActivityPadX.this.velobar.setProgress(6.0f);
                        i = 6;
                    }
                    TextView textView12 = MainActivityPadX.this.v_tv;
                    StringBuilder sb = new StringBuilder("");
                    int i2 = i - 1;
                    sb.append(MainActivityPadX.this.veloTins[i2]);
                    textView12.setText(sb.toString());
                    MainActivityPadX.this.velobar.setProgress(i);
                    if (i < 5 && MainActivityPadX.this.rotatebar.getProgress() != 0) {
                        MainActivityPadX.this.r_tv.setText("0");
                        MainActivityPadX.this.rotatebar.setProgress(0.0f);
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[i2] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.28.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPadX.this.velobar.getProgress();
                if (progress < 13) {
                    int i = progress + 1;
                    if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 5 && i > 6) {
                        MainActivityPadX.this.velobar.setProgress(6.0f);
                        i = 6;
                    }
                    TextView textView12 = MainActivityPadX.this.v_tv;
                    StringBuilder sb = new StringBuilder("");
                    int i2 = i - 1;
                    sb.append(MainActivityPadX.this.veloTins[i2]);
                    textView12.setText(sb.toString());
                    MainActivityPadX.this.velobar.setProgress(i);
                    if (i < 5 && MainActivityPadX.this.rotatebar.getProgress() != 0) {
                        MainActivityPadX.this.r_tv.setText("0");
                        MainActivityPadX.this.rotatebar.setProgress(0.0f);
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[i2] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.29.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivityPadX.this.checkIfUpdate();
                }
            }
        });
        this.layout_adjust = (RelativeLayout) findViewById(R.id.layout_adjust);
        this.tennipic2 = (RelativeLayout) findViewById(R.id.tennipic2);
        this.tennipic3 = (RelativeLayout) findViewById(R.id.tennipic3);
        this.tennipic4 = (RelativeLayout) findViewById(R.id.tennipic4);
        this.tennipic5 = (RelativeLayout) findViewById(R.id.tennipic5);
        this.tennipic6 = (RelativeLayout) findViewById(R.id.tennipic6);
        this.tennipic7 = (RelativeLayout) findViewById(R.id.tennipic7);
        this.blenoty = (TextView) findViewById(R.id.connect);
        TextView textView12 = (TextView) findViewById(R.id.select_dianwei);
        this.select_dianwei = textView12;
        textView12.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX.this.showBottomDialog();
            }
        });
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivityPadX.this.self.setVisibility(8);
                    MainActivityPadX.this.group.setVisibility(0);
                    MainActivityPadX.this.points.setVisibility(0);
                    MainActivityPadX.this.hintpoints.setVisibility(0);
                    MainActivityPadX.this.group_cate.setVisibility(0);
                    MainActivityPadX.this.tennipic2.setVisibility(8);
                    MainActivityPadX.this.tennipic3.setVisibility(8);
                    MainActivityPadX.this.tennipic4.setVisibility(8);
                    MainActivityPadX.this.tennipic5.setVisibility(8);
                    MainActivityPadX.this.fourbtn.setVisibility(8);
                    MainActivityPadX.this.bg_four.setVisibility(8);
                    MainActivityPadX.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivityPadX.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivityPadX.this.density * 36.0f);
                    MainActivityPadX.this.start_layout.setLayoutParams(layoutParams);
                    MainActivityPadX.this.start_layout.setGravity(17);
                    MainActivityPadX.this.modeCate = 1;
                    return;
                }
                MainActivityPadX.this.self.setVisibility(0);
                MainActivityPadX.this.group.setVisibility(8);
                MainActivityPadX.this.points.setVisibility(8);
                MainActivityPadX.this.hintpoints.setVisibility(8);
                MainActivityPadX.this.tennipic2.setVisibility(0);
                MainActivityPadX.this.bg_input.setVisibility(8);
                MainActivityPadX.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivityPadX.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivityPadX.this.density * 90.0f);
                MainActivityPadX.this.start_layout.setLayoutParams(layoutParams2);
                MainActivityPadX.this.start_layout.setGravity(83);
                MainActivityPadX.this.fourbtn.setVisibility(0);
                MainActivityPadX.this.bg_four.setVisibility(0);
                if (MainActivityPadX.this.modeNum == 2) {
                    MainActivityPadX.this.tennipic3.setVisibility(0);
                    MainActivityPadX.this.tennipic4.setVisibility(8);
                    MainActivityPadX.this.tennipic5.setVisibility(8);
                }
                if (MainActivityPadX.this.modeNum == 3) {
                    MainActivityPadX.this.tennipic3.setVisibility(8);
                    MainActivityPadX.this.tennipic4.setVisibility(0);
                    MainActivityPadX.this.tennipic5.setVisibility(8);
                }
                if (MainActivityPadX.this.modeNum == 4) {
                    MainActivityPadX.this.tennipic3.setVisibility(8);
                    MainActivityPadX.this.tennipic4.setVisibility(8);
                    MainActivityPadX.this.tennipic5.setVisibility(0);
                }
                MainActivityPadX.this.modeCate = 0;
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivityPadX.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivityPadX.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivityPadX.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivityPadX.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivityPadX.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.32.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivityPadX.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivityPadX.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivityPadX.this, MainActivityPadX.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_route_name));
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivityPadX.this.daoSession.loadAll(VariDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivityPadX.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivityPadX.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivityPadX.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivityPadX.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.33.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivityPadX.this.daoSession.loadAll(VariDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((VariDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivityPadX.this.daoSession.delete((VariDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivityPadX.this, MainActivityPadX.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.select_dianwei.getText() != null && !MainActivityPadX.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivityPadX.this.alert_dialog_input();
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.select_dianwei.getText() != null && !MainActivityPadX.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivityPadX.this.alert_dialog_input2();
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass36());
        this.lastpoints2.setOnClickListener(new AnonymousClass37());
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.blenoty.getText().toString().trim().contains(MainActivityPadX.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    if (MainActivityPadX.bleDevice != null) {
                        MainActivityPadX.this.connect(MainActivityPadX.bleDevice);
                    } else {
                        MainActivityPadX.this.checkPermissions();
                    }
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.disconnected));
                    MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.39
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivityPadX.this.radioNum = 0;
                        MainActivityPadX.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivityPadX.this.radioNum = 1;
                        MainActivityPadX.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivityPadX.this.radioNum = 2;
                        MainActivityPadX.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.valueSelect = Integer.parseInt(mainActivityPadX.value_h.getText().toString().trim());
                MainActivityPadX.access$308(MainActivityPadX.this);
                if (MainActivityPadX.this.valueSelect > 16) {
                    MainActivityPadX.this.valueSelect = 16;
                    return;
                }
                MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX2, mainActivityPadX2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.40.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1300L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.40.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1400L);
                MainActivityPadX.this.value_h.setText("" + MainActivityPadX.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.40.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.40.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivityPadX.this.checkIfUpdate();
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.valueSelect = Integer.parseInt(mainActivityPadX.value_h.getText().toString().trim());
                MainActivityPadX.access$310(MainActivityPadX.this);
                if (MainActivityPadX.this.valueSelect < 4) {
                    MainActivityPadX.this.valueSelect = 4;
                    return;
                }
                MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX2, mainActivityPadX2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.41.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1300L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.41.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1400L);
                MainActivityPadX.this.value_h.setText("" + MainActivityPadX.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.41.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.41.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivityPadX.this.checkIfUpdate();
            }
        });
        showSelectPoint(this.vari_point_num);
        connect(bleDevice);
        getWindow().setSoftInputMode(2);
        getDefaultPoint1();
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* renamed from: com.pusun.pusuntennis.MainActivityPadX$36, reason: invalid class name */
    class AnonymousClass36 implements View.OnClickListener {
        AnonymousClass36() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivityPadX.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityPadX.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityPadX.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivityPadX.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityPadX.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivityPadX.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivityPadX.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivityPadX.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivityPadX.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivityPadX.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivityPadX.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivityPadX.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPadX.this.freq.getProgress() - 1;
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivityPadX.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPadX.this.velobar.getProgress() - 1;
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivityPadX.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivityPadX.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivityPadX.this.valueSelect = item2;
                                    MainActivityPadX.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.36.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.sendBaseData(1);
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
            MainActivityPadX mainActivityPadX = MainActivityPadX.this;
            ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_route_name));
        }
    }

    /* renamed from: com.pusun.pusuntennis.MainActivityPadX$37, reason: invalid class name */
    class AnonymousClass37 implements View.OnClickListener {
        AnonymousClass37() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivityPadX.this.daoSession.loadAll(VariDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityPadX.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityPadX.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivityPadX.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityPadX.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new AnonymousClass1());
                optionPicker.show();
                return;
            }
            MainActivityPadX mainActivityPadX = MainActivityPadX.this;
            ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_route_name));
        }

        /* renamed from: com.pusun.pusuntennis.MainActivityPadX$37$1, reason: invalid class name */
        class AnonymousClass1 implements OptionPicker.OnOptionPickListener {
            AnonymousClass1() {
            }

            @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
            public void onOptionPicked(String str) {
                new ArrayList();
                List loadAll = MainActivityPadX.this.daoSession.loadAll(VariDao.class);
                for (int i = 0; i < loadAll.size(); i++) {
                    if (((VariDao) loadAll.get(i)).getDaoName().equals(str)) {
                        MainActivityPadX.this.select_dianwei.setText(((VariDao) loadAll.get(i)).getSeles());
                        String[] split = ((VariDao) loadAll.get(i)).getSeles().split(",");
                        MainActivityPadX.this.selectPoints.clear();
                        for (String str2 : split) {
                            MainActivityPadX.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                        MainActivityPadX.this.showPoints();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.37.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        BasicData22.b3[35][0] = (short) ((VariDao) loadAll.get(i)).getLr1();
                        BasicData22.b3[35][1] = (short) ((VariDao) loadAll.get(i)).getUd1();
                        BasicData22.b3[35][2] = (short) ((VariDao) loadAll.get(i)).getVelo1();
                        BasicData22.b3[35][3] = (short) ((VariDao) loadAll.get(i)).getFreq1();
                        BasicData22.b3[36][0] = (short) ((VariDao) loadAll.get(i)).getLr2();
                        BasicData22.b3[36][1] = (short) ((VariDao) loadAll.get(i)).getUd2();
                        BasicData22.b3[36][2] = (short) ((VariDao) loadAll.get(i)).getVelo2();
                        BasicData22.b3[36][3] = (short) ((VariDao) loadAll.get(i)).getFreq2();
                        BasicData22.b3[37][0] = (short) ((VariDao) loadAll.get(i)).getLr3();
                        BasicData22.b3[37][1] = (short) ((VariDao) loadAll.get(i)).getUd3();
                        BasicData22.b3[37][2] = (short) ((VariDao) loadAll.get(i)).getVelo3();
                        BasicData22.b3[37][3] = (short) ((VariDao) loadAll.get(i)).getFreq3();
                        BasicData22.b3[38][0] = (short) ((VariDao) loadAll.get(i)).getLr4();
                        BasicData22.b3[38][1] = (short) ((VariDao) loadAll.get(i)).getUd4();
                        BasicData22.b3[38][2] = (short) ((VariDao) loadAll.get(i)).getVelo4();
                        BasicData22.b3[38][3] = (short) ((VariDao) loadAll.get(i)).getFreq4();
                        BasicData22.b3[39][0] = (short) ((VariDao) loadAll.get(i)).getLr5();
                        BasicData22.b3[39][1] = (short) ((VariDao) loadAll.get(i)).getUd5();
                        BasicData22.b3[39][2] = (short) ((VariDao) loadAll.get(i)).getVelo5();
                        BasicData22.b3[39][3] = (short) ((VariDao) loadAll.get(i)).getFreq5();
                        BasicData22.b3[40][0] = (short) ((VariDao) loadAll.get(i)).getLr6();
                        BasicData22.b3[40][1] = (short) ((VariDao) loadAll.get(i)).getUd6();
                        BasicData22.b3[40][2] = (short) ((VariDao) loadAll.get(i)).getVelo6();
                        BasicData22.b3[40][3] = (short) ((VariDao) loadAll.get(i)).getFreq6();
                        MainActivityPadX.this.showSelectPoint(MainActivityPadX.this.vari_point_num);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.37.1.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int length = BasicData22.b3.length - 6;
        while (length < BasicData22.b3.length) {
            final int final_length = length;
                                    int i2 = length + 1;
                                    short s = BasicData22.b3[length][0];
                                    short s2 = BasicData22.b3[length][0];
                                    short s3 = BasicData22.b3[length][1];
                                    short s4 = BasicData22.b3[length][1];
                                    final byte[] bArr = {-86, (byte) i2, (byte) (BasicData22.b3[length][0] >> 8), (byte) BasicData22.b3[length][0], (byte) (BasicData22.b3[length][1] >> 8), (byte) BasicData22.b3[length][1], (byte) (BasicData22.b3[length][2] >> 8), (byte) BasicData22.b3[length][2], (byte) BasicData22.b3[length][3], -91};
                                    AppLog.e("左右：" + ((int) BasicData22.b3[length][0]) + "上下：" + ((int) BasicData22.b3[length][1]) + "byte:" + MainActivityPadX.bytesToHexString(bArr));
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.37.1.2.1
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            AppLog.e("第" + final_length + "条指令");
                                            MainActivityPadX.this.writeBleData(bArr);
                                        }
                                    }, (long) (length * 10));
                                    length = i2;
                                }
                            }
                        }, 60L);
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSelectPoint(int i) {
        TextView textView = this.frontvalue2;
        StringBuilder sb = new StringBuilder("");
        int i2 = i + 34;
        sb.append((BasicData22.b3[i2][0] / 30) + 2);
        textView.setText(sb.toString());
        this.backvalue2.setText("" + (BasicData22.b3[i2][1] / 100));
        TextView textView2 = this.front_m_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData22.b3[i2][2] - 30);
        textView2.setText(sb2.toString());
        TextView textView3 = this.back_m_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData22.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
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

    /* JADX INFO: Access modifiers changed from: private */
    public void showPos(int[] iArr) {
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
        int i = 0;
        if (this.modeCate == 1) {
            if (this.selectPoints.size() != 0) {
                for (int i2 = 0; i2 < this.poids.length; i2++) {
                    this.pos[i2].setBackground(getResources().getDrawable(R.drawable.tenniball2));
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i3 = 0; i3 < this.selectPoints.size(); i3++) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= this.poids.length) {
                            break;
                        }
                        if (this.selectPoints.get(i3).intValue() == this.poids[i4] + 1) {
                            this.pos[i4].setBackground(getResources().getDrawable(R.drawable.tenniball));
                            break;
                        }
                        i4++;
                    }
                    if (i3 != this.selectPoints.size() - 1) {
                        stringBuffer.append(this.selectPoints.get(i3) + ",");
                    } else {
                        stringBuffer.append(this.selectPoints.get(i3));
                    }
                }
                this.select_dianwei.setText(stringBuffer);
                return;
            }
            this.select_dianwei.setText("");
            while (i < this.poids.length) {
                this.pos[i].setBackground(getResources().getDrawable(R.drawable.tenniball2));
                i++;
            }
            return;
        }
        if (this.selectPoints.size() != 0) {
            for (int i5 = 0; i5 < this.poids.length; i5++) {
                this.pos[i5].setBackground(getResources().getDrawable(R.drawable.tenniball2));
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i6 = 0; i6 < this.selectPoints.size(); i6++) {
                int i7 = 0;
                while (true) {
                    if (i7 >= this.poids.length) {
                        break;
                    }
                    if (this.selectPoints.get(i6).intValue() + 35 == this.poids[i7] + 1) {
                        this.pos[i7].setBackground(getResources().getDrawable(R.drawable.tenniball));
                        break;
                    }
                    i7++;
                }
                if (i6 != this.selectPoints.size() - 1) {
                    stringBuffer2.append(this.selectPoints.get(i6) + ",");
                } else {
                    stringBuffer2.append(this.selectPoints.get(i6));
                }
            }
            this.select_dianwei.setText(stringBuffer2);
            return;
        }
        this.select_dianwei.setText("");
        while (i < this.poids.length) {
            this.pos[i].setBackground(getResources().getDrawable(R.drawable.tenniball2));
            i++;
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
        while (i < BasicData3.b2.length) {
            final int final_i = i;
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData3.b2[i][0] >> 8), (byte) BasicData3.b2[i][0], (byte) (BasicData3.b2[i][1] >> 8), (byte) BasicData3.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData3.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData3.b2[i][0])) ^ ((byte) (BasicData3.b2[i][1] >> 8))) ^ ((byte) BasicData3.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData3.b2[i][0]) + "上下：" + ((int) BasicData3.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.42
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivityPadX.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.43
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i3 = 20;
        while (i3 < BasicData3.b2.length) {
            final int final_i3 = i3;
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData3.b2[i3][0] >> 8), (byte) BasicData3.b2[i3][0], (byte) (BasicData3.b2[i3][1] >> 8), (byte) BasicData3.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData3.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData3.b2[i3][0])) ^ ((byte) (BasicData3.b2[i3][1] >> 8))) ^ ((byte) BasicData3.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData3.b2[i3][0]) + "上下：" + ((int) BasicData3.b2[i3][1]) + "byte:" + MainActivityPadX.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.43.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i3 + "条指令");
                            MainActivityPadX.this.writeBleData(bArr2);
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
        char c = 3;
        char c2 = 1;
        char c3 = 0;
        if (i == 1) {
            int i2 = (this.baseHt - this.valueSelect) * 100;
            int i3 = 0;
            for (int i4 = 6; i3 < BasicData22.b3.length - i4; i4 = 6) {
                int i5 = i3 + 1;
                short s = BasicData22.b3[i3][c3];
                short s2 = BasicData22.b3[i3][c3];
                short s3 = BasicData22.b3[i3][c2];
                short s4 = BasicData22.b3[i3][c2];
                short s5 = BasicData22.b3[i3][2];
                short s6 = BasicData22.b3[i3][2];
                final byte[] bArr = {-86, (byte) i5, (byte) (BasicData22.b3[i3][c3] >> 8), (byte) BasicData22.b3[i3][c3], (byte) ((BasicData22.b3[i3][c2] - i2) >> 8), (byte) (BasicData22.b3[i3][c2] - i2), (byte) (BasicData22.b3[i3][2] >> 8), (byte) BasicData22.b3[i3][2], (byte) BasicData22.b3[i3][c], -91};
                AppLog.e("左右：" + ((int) BasicData22.b3[i3][0]) + "上下：" + (BasicData22.b3[i3][1] - i2) + "byte:" + bytesToHexString(bArr));
                final int final_i3 = i3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.44
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + final_i3 + "条指令");
                        MainActivityPadX.this.writeBleData(bArr);
                    }
                }, (long) (i3 * 10));
                i3 = i5;
                c2 = 1;
                c3 = 0;
                c = 3;
            }
                final int final_i2 = i2;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.45
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int i6 = 20;
        while (i6 < BasicData22.b3.length - 6) {
            final int final_i6 = i6;
                        int i7 = i6 + 1;
                        short s7 = BasicData22.b3[i6][0];
                        short s8 = BasicData22.b3[i6][0];
                        short s9 = BasicData22.b3[i6][1];
                        short s10 = BasicData22.b3[i6][1];
                        short s11 = BasicData22.b3[i6][2];
                        short s12 = BasicData22.b3[i6][2];
                        final byte[] bArr2 = {-86, (byte) i7, (byte) (BasicData22.b3[i6][0] >> 8), (byte) BasicData22.b3[i6][0], (byte) ((BasicData22.b3[i6][1] - final_i2) >> 8), (byte) (BasicData22.b3[i6][1] - final_i2), (byte) (BasicData22.b3[i6][2] >> 8), (byte) BasicData22.b3[i6][2], (byte) BasicData22.b3[i6][3], -91};
                        AppLog.e("左右：" + ((int) BasicData22.b3[i6][0]) + "上下：" + (BasicData22.b3[i6][1] - final_i2) + "byte:" + MainActivityPadX.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.45.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_i6 + "条指令");
                                MainActivityPadX.this.writeBleData(bArr2);
                            }
                        }, (long) (i6 * 10));
                        i6 = i7;
                    }
                }
            }, 900L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.46
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int length = BasicData22.b3.length - 6;
        while (length < BasicData22.b3.length) {
            final int final_length = length;
                        int i6 = length + 1;
                        short s7 = BasicData22.b3[length][0];
                        short s8 = BasicData22.b3[length][0];
                        short s9 = BasicData22.b3[length][1];
                        short s10 = BasicData22.b3[length][1];
                        short s11 = BasicData22.b3[length][2];
                        short s12 = BasicData22.b3[length][2];
                        final byte[] bArr2 = {-86, (byte) i6, (byte) (BasicData22.b3[length][0] >> 8), (byte) BasicData22.b3[length][0], (byte) (BasicData22.b3[length][1] >> 8), (byte) BasicData22.b3[length][1], (byte) (BasicData22.b3[length][2] >> 8), (byte) BasicData22.b3[length][2], (byte) BasicData22.b3[length][3], -91};
                        AppLog.e("左右：" + ((int) BasicData22.b3[length][0]) + "上下：" + ((int) BasicData22.b3[length][1]) + "byte:" + MainActivityPadX.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.46.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_length + "条指令");
                                MainActivityPadX.this.writeBleData(bArr2);
                            }
                        }, (long) (length * 10));
                        length = i6;
                    }
                }
            }, 1200L);
            return;
        }
        int i6 = 0;
        while (i6 < BasicData3.b4.length) {
            final int final_i6 = i6;
            int i7 = i6 + 1;
            byte b = (byte) i7;
            final byte[] bArr2 = {-86, b, (byte) (BasicData3.b4[i6][0] >> 8), (byte) BasicData3.b4[i6][0], (byte) (BasicData3.b4[i6][1] >> 8), (byte) BasicData3.b4[i6][1], 0, 0, (byte) ((((((byte) (BasicData3.b4[i6][0] >> 8)) ^ b) ^ ((byte) BasicData3.b4[i6][0])) ^ ((byte) (BasicData3.b4[i6][1] >> 8))) ^ ((byte) BasicData3.b4[i6][1])), -91};
            AppLog.e("左右：" + ((int) BasicData3.b4[i6][0]) + "上下：" + ((int) BasicData3.b4[i6][1]) + "byte:" + bytesToHexString(bArr2));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.47
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i6 + "条指令");
                    MainActivityPadX.this.writeBleData(bArr2);
                }
            }, (long) (i6 * 10));
            i6 = i7;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.48
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i8 = 20;
        while (i8 < BasicData3.b4.length) {
            final int final_i8 = i8;
                    int i9 = i8 + 1;
                    byte b2 = (byte) i9;
                    final byte[] bArr3 = {-86, b2, (byte) (BasicData3.b4[i8][0] >> 8), (byte) BasicData3.b4[i8][0], (byte) (BasicData3.b4[i8][1] >> 8), (byte) BasicData3.b4[i8][1], 0, 0, (byte) ((((((byte) (BasicData3.b4[i8][0] >> 8)) ^ b2) ^ ((byte) BasicData3.b4[i8][0])) ^ ((byte) (BasicData3.b4[i8][1] >> 8))) ^ ((byte) BasicData3.b4[i8][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData3.b4[i8][0]) + "上下：" + ((int) BasicData3.b4[i8][1]) + "byte:" + MainActivityPadX.bytesToHexString(bArr3));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.48.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i8 + "条指令");
                            MainActivityPadX.this.writeBleData(bArr3);
                        }
                    }, (long) (i8 * 10));
                    i8 = i9;
                }
            }
        }, 900L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeBleData(byte[] bArr) {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PT0") || this.nameStar.startsWith("PT1"))) {
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.49
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PT7") || this.nameStar.startsWith("PT8") || this.nameStar.startsWith("PM")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.50
                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteFailure(BleException bleException) {
                    }

                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteSuccess(int i, int i2, byte[] bArr2) {
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.51
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.no_device_found));
                    if (MainActivityPadX.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.51.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPadX.this.startScan();
                            }
                        }, 1000L);
                        MainActivityPadX.access$7508(MainActivityPadX.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PT")) {
                    MainActivityPadX.this.connect(list.get(0));
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
                    MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX2, mainActivityPadX2.getResources().getString(R.string.no_device_found));
                    if (MainActivityPadX.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.51.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPadX.this.startScan();
                            }
                        }, 1000L);
                        MainActivityPadX.access$7508(MainActivityPadX.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivityPadX.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityPadX.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityPadX.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivityPadX.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityPadX.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.51.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivityPadX.this.connect((BleDevice) arrayList.get(i3));
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
        // If the BleDevice has a valid BluetoothDevice use it directly;
        // otherwise fall back to MAC-string connect which reconstructs the device from the adapter.
        String mac = (bleDevice2 != null) ? bleDevice2.getMac() : null;
        if (mac == null && deviceMac != null) {
            mac = deviceMac;
        }
        if (bleDevice2 == null || bleDevice2.getDevice() == null) {
            if (mac != null) {
                final String finalMac = mac;
                BleManager.getInstance().connect(finalMac, new BleGattCallback() {
                    @Override
                    public void onStartConnect() {
                        MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                        ShowHelper.showProgressDialog(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.connecting_device));
                    }
                    @Override
                    public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                        MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                        ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.connect_failure_check));
                        ShowHelper.dismissProgressDialog();
                        MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.disconnected));
                        MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                    }
                    @Override
                    public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                        ShowHelper.setProgressDialogMessage(MainActivityPadX.this.getResources().getString(R.string.initializing));
                        MainActivityPadX.this.connNum = 0;
                        new Handler().postDelayed(new Runnable() {
                            @Override public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                                ShowHelper.toastShort(MainActivityPadX.this, MainActivityPadX.this.getResources().getString(R.string.please_use));
                            }
                        }, com.google.android.exoplayer2.C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        String resolvedName = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                        if (resolvedName == null || resolvedName.isEmpty()) resolvedName = MainActivityPadX.this.deviceName != null ? MainActivityPadX.this.deviceName : "";
                        MainActivityPadX.this.nameStar = resolvedName;
                        MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.connected));
                        MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_selector));
                        MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.nameStar + MainActivityPadX.this.getResources().getString(R.string.connected));
                        MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_green));
                        MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_blue));
                        MainActivityPadX.this.isFaultOn = 0;
                        MainActivityPadX.this.gatt = bluetoothGatt;
                        MainActivityPadX.bleDevice = bleDevice3;
                        if (bleDevice3.getMac() != null) MainActivityPadX.this.deviceMac = bleDevice3.getMac();
                        new Handler().postDelayed(new Runnable() { @Override public synchronized void run() { MainActivityPadX.this.sendBaseData(1); } }, 1500L);
                        new Handler().postDelayed(new Runnable() { @Override public synchronized void run() {
                            int progress = MainActivityPadX.this.velobar.getProgress() - 1;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[progress] ^ 99), -91});
                        } }, 3200L);
                        new Handler().postDelayed(new Runnable() { @Override public synchronized void run() {
                            int progress = MainActivityPadX.this.freq.getProgress() - 1;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[progress] ^ 97), -91});
                        } }, 3350L);
                        new Handler().postDelayed(new Runnable() { @Override public synchronized void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                        } }, 3400L);
                        new Handler().postDelayed(new Runnable() { @Override public synchronized void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                        } }, 3450L);
                        new Handler().postDelayed(new Runnable() { @Override public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        } }, 3500L);
                        MainActivityPadX.this.getDefaultPoint1();
                        MainActivityPadX.this.startNotify();
                    }
                    @Override
                    public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                        MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.disconnected));
                        MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                        MainActivityPadX.this.isFaultOn = 0;
                        if (z || MainActivityPadX.this.connNum >= 3) { return; }
                        new Handler().postDelayed(new Runnable() {
                            @Override public void run() { MainActivityPadX.this.connect(bleDevice3); }
                        }, 1000L);
                        MainActivityPadX.access$7508(MainActivityPadX.this);
                    }
                });
            } else {
                android.util.Log.e("MainActivityPadX", "connect: bleDevice and mac are both null, cannot connect");
            }
            return;
        }
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.52
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.showProgressDialog(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.disconnected));
                MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivityPadX.this.getResources().getString(R.string.initializing));
                MainActivityPadX.this.connNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivityPadX.this, MainActivityPadX.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                String rawNameMainAc = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                if (rawNameMainAc == null || rawNameMainAc.isEmpty()) rawNameMainAc = MainActivityPadX.this.deviceName != null ? MainActivityPadX.this.deviceName : "";
                MainActivityPadX.this.nameStar = rawNameMainAc;
                MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.connected));
                MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.nameStar + MainActivityPadX.this.getResources().getString(R.string.connected));
                MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_green));
                MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivityPadX.this.isFaultOn = 0;
                MainActivityPadX.this.gatt = bluetoothGatt;
                MainActivityPadX.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPadX.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPadX.this.velobar.getProgress() - 1;
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPadX.this.freq.getProgress() - 1;
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.7
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                MainActivityPadX.this.getDefaultPoint1();
                MainActivityPadX.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivityPadX.this.blenoty.setText(MainActivityPadX.this.getResources().getString(R.string.disconnected));
                MainActivityPadX.this.blenoty.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityPadX.this.signal.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityPadX.this.signal_note.setText(MainActivityPadX.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityPadX.this.signal_note.setTextColor(MainActivityPadX.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivityPadX.this.isFaultOn = 0;
                if (z || MainActivityPadX.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.52.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivityPadX.access$7508(MainActivityPadX.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIfUpdate() {
        this.defaultDaoList = new ArrayList();
        this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (i >= this.defaultDaoList.size()) {
                break;
            }
            if (this.defaultDaoList.get(i).getDaoName() != null && this.defaultDaoList.get(i).getDaoName().equals(this.daoNameIng) && this.modeCate == 0) {
                if (this.num_lr.getVisibility() == 0 && this.lr != this.defaultDaoList.get(i).getItem2()) {
                    z = true;
                }
                if (this.num_ud.getVisibility() == 0 && this.ud != this.defaultDaoList.get(i).getItem3()) {
                    z = true;
                }
                if (this.freq.getProgress() != this.defaultDaoList.get(i).getFreq()) {
                    z = true;
                }
                if (this.velobar.getProgress() != this.defaultDaoList.get(i).getVelo()) {
                    z = true;
                }
                if (this.rotatebar.getProgress() != this.defaultDaoList.get(i).getRote()) {
                    z = true;
                }
                if (this.rgheight.getVisibility() == 0 && this.valueSelect != this.defaultDaoList.get(i).getGrade()) {
                    z = true;
                }
                if (z) {
                    this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                    this.saveColor = 1;
                    z2 = true;
                    break;
                }
                z2 = true;
            }
            i++;
        }
        if (!z) {
            this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
            this.saveColor = 0;
        }
        if (this.defaultDaoList.size() == 0 || !z2) {
            this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
            this.saveColor = 1;
        }
    }

    private void setDefaultMode(String str) {
        int grade;
        this.daoNameIng = str;
        this.defaultDaoList = new ArrayList();
        this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
        this.saveColor = 1;
        this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
        for (int i = 0; i < this.defaultDaoList.size(); i++) {
            if (this.defaultDaoList.get(i).getDaoName() != null && this.defaultDaoList.get(i).getDaoName().equals(this.daoNameIng)) {
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.saveColor = 0;
                this.lr = this.defaultDaoList.get(i).getItem2();
                int item3 = this.defaultDaoList.get(i).getItem3();
                this.ud = item3;
                int i2 = this.lr;
                writeBleData(new byte[]{-86, 108, (byte) (((short) i2) >> 8), (byte) i2, (byte) (((short) item3) >> 8), (byte) item3, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                this.freq.setProgress((float) this.defaultDaoList.get(i).getFreq());
                this.f_tv.setText("" + this.defaultDaoList.get(i).getFreq());
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.53
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPadX.this.freq.getProgress() - 1;
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 30L);
                this.velobar.setProgress((float) this.defaultDaoList.get(i).getVelo());
                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i).getVelo() - 1]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.54
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPadX.this.velobar.getProgress() - 1;
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[progress] ^ 99), -91});
                    }
                }, 80L);
                this.rotatebar.setProgress((float) this.defaultDaoList.get(i).getRote());
                final int rote = this.defaultDaoList.get(i).getRote();
                if (rote < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.55
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i3) * 5), 0, 0, 0, 0, (byte) (((-i3) * 5) ^ 96), -91});
                        }
                    }, 120L);
                }
                if (rote > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.56
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i3 * 5), 0, 0, 0, 0, (byte) ((i3 * 5) ^ 99), -91});
                        }
                    }, 120L);
                }
                if (rote == 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.57
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                    }, 120L);
                }
                this.r_tv.setText("" + rote);
                if (this.rgheight.getVisibility() != 0 || (grade = this.defaultDaoList.get(i).getGrade()) == 0) {
                    return;
                }
                this.valueSelect = grade;
                this.value_h.setText("" + grade);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.58
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.sendBaseData(1);
                    }
                }, 160L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59
            @Override // java.lang.Runnable
            public void run() {
                MainActivityPadX.this.defaultDaoList = new ArrayList();
                MainActivityPadX.this.savedefault.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivityPadX.this.saveColor = 1;
                MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                mainActivityPadX.defaultDaoList = mainActivityPadX.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivityPadX.this.defaultDaoList.size(); i++) {
                    if (MainActivityPadX.this.defaultDaoList.get(i).getDaoName() != null && MainActivityPadX.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivityPadX.this.savedefault.setBackground(MainActivityPadX.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivityPadX.this.saveColor = 0;
                        MainActivityPadX mainActivityPadX2 = MainActivityPadX.this;
                        mainActivityPadX2.lr = mainActivityPadX2.defaultDaoList.get(i).getItem2();
                        MainActivityPadX mainActivityPadX3 = MainActivityPadX.this;
                        mainActivityPadX3.ud = mainActivityPadX3.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivityPadX.this.lr;
                        short s2 = (short) MainActivityPadX.this.ud;
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                        MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                        MainActivityPadX.this.freq.setProgress((float) MainActivityPadX.this.defaultDaoList.get(i).getFreq());
                        MainActivityPadX.this.f_tv.setText("" + MainActivityPadX.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityPadX.this.freq.getProgress() - 1;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPadX.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivityPadX.this.velobar.setProgress((float) MainActivityPadX.this.defaultDaoList.get(i).getVelo());
                        MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[MainActivityPadX.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityPadX.this.velobar.getProgress() - 1;
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivityPadX.this.rotatebar.setProgress((float) MainActivityPadX.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivityPadX.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.59.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivityPadX.this.r_tv.setText("" + rote);
                        return;
                    }
                }
            }
        }, 3650L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNotify() {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PT0") || this.nameStar.startsWith("PT1"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.60
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
                        MainActivityPadX.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityPadX.this.isFaultOn == 0) {
                        MainActivityPadX.access$8108(MainActivityPadX.this);
                        MainActivityPadX.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PT7") || this.nameStar.startsWith("PT8") || this.nameStar.startsWith("PM"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityPadX.61
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
                        MainActivityPadX.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityPadX.this.isFaultOn == 0) {
                        MainActivityPadX.access$8108(MainActivityPadX.this);
                        MainActivityPadX.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.62
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivityPadX.this.checkPower();
            }
        }, 3600L);
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
        if (showFaultMsg.code == 3) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.no_ball_alert));
            this.btn_ball.setText(getResources().getString(R.string.start));
            this.signal_note.setText(getResources().getString(R.string.status_stop));
            this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
            this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_dark_selector));
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
        int i = this.modeCate;
        if (i == 1) {
            this.savedefault.setVisibility(8);
            this.points.setVisibility(0);
            this.group_cate.setVisibility(8);
            String str = this.nameStar;
            if (str != null && (str.startsWith("PT1") || this.nameStar.startsWith("PT7"))) {
                this.group_cate.setVisibility(0);
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
            this.control_all.setVisibility(0);
            this.freq_up.setVisibility(0);
            this.knob_freq.setVisibility(0);
            this.velo_up.setVisibility(0);
            this.knob_speed.setVisibility(0);
            this.f_tv.setVisibility(0);
            this.v_tv.setVisibility(0);
            this.pinlv.setVisibility(0);
            this.up_velo_layout.setVisibility(0);
            this.self_program.setVisibility(0);
            this.self_program2.setVisibility(8);
            this.self_re.setVisibility(0);
            this.self_re2.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
            layoutParams.height = (int) (this.density * 44.0f);
            layoutParams.topMargin = (int) (this.density * 12.0f);
            this.start_layout.setLayoutParams(layoutParams);
            this.start_layout.setGravity(17);
            this.rg_hint.setVisibility(0);
            this.rgheight.setVisibility(0);
            this.savepoints.setVisibility(0);
            this.change_velo_layout2.setVisibility(8);
            this.tennipic6.setVisibility(8);
            this.tennipic7.setVisibility(0);
            this.modeCate = 1;
            return;
        }
        if (i == 2) {
            this.savedefault.setVisibility(8);
            this.points.setVisibility(0);
            this.group_cate.setVisibility(8);
            this.tennipic6.setVisibility(0);
            this.tennipic7.setVisibility(8);
            this.freq_up.setVisibility(8);
            this.knob_freq.setVisibility(8);
            this.knob_speed.setVisibility(8);
            this.velo_up.setVisibility(8);
            this.f_tv.setVisibility(8);
            this.v_tv.setVisibility(8);
            this.pinlv.setVisibility(8);
            this.up_velo_layout.setVisibility(8);
            String str2 = this.nameStar;
            if (str2 != null && (str2.startsWith("PT1") || this.nameStar.startsWith("PT2"))) {
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
            this.change_velo_layout2.setVisibility(0);
            this.self_program.setVisibility(8);
            this.self_program2.setVisibility(0);
            this.self_re.setVisibility(8);
            this.self_re2.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
            layoutParams2.height = (int) (this.density * 44.0f);
            layoutParams2.topMargin = (int) (this.density * 12.0f);
            this.start_layout.setLayoutParams(layoutParams2);
            this.start_layout.setGravity(17);
            this.rg_hint.setVisibility(8);
            this.rgheight.setVisibility(8);
            this.savepoints.setVisibility(0);
            this.modeCate = 2;
            return;
        }
        this.savedefault.setVisibility(0);
        this.points.setVisibility(8);
        this.hintpoints.setVisibility(8);
        this.tennipic2.setVisibility(0);
        this.bg_input.setVisibility(8);
        this.bg_input_big.setVisibility(8);
        this.group_cate.setVisibility(8);
        this.control_all.setVisibility(0);
        this.freq_up.setVisibility(0);
        this.knob_freq.setVisibility(0);
        this.velo_up.setVisibility(0);
        this.knob_speed.setVisibility(0);
        this.f_tv.setVisibility(0);
        this.v_tv.setVisibility(0);
        this.pinlv.setVisibility(0);
        this.up_velo_layout.setVisibility(0);
        this.change_velo_layout2.setVisibility(8);
        this.tennipic6.setVisibility(8);
        this.tennipic7.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
        layoutParams3.height = (int) (this.density * 90.0f);
        layoutParams3.topMargin = (int) (this.density * 25.0f);
        this.start_layout.setLayoutParams(layoutParams3);
        this.start_layout.setGravity(83);
        this.fourbtn.setVisibility(0);
        this.bg_four.setVisibility(0);
        this.rg_hint.setVisibility(0);
        this.rgheight.setVisibility(0);
        int i2 = this.modeNum;
        if (i2 == 1 || i2 == 2 || i2 == 5 || i2 == 7 || i2 == 8 || i2 == 9 || i2 == 15 || i2 == 16 || i2 == 17) {
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.64
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityPadX.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.63
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityPadX.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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
        int i;
        int i2;
        int id = view.getId();
        switch (id) {
            case R.id.bajada /* 2131361896 */:
                this.tableName.setText(getResources().getString(R.string.bajada));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.bajada.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                this.isNumDing = 1;
                if (this.modeNum != 16) {
                    this.isNumDing = 0;
                    this.lr = 1200;
                    this.ud = 3800;
                    short s = (short) 1200;
                    short s2 = (short) 3800;
                    writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 16;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(4.0f);
                this.v_tv.setText("" + this.veloTins[3]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.100
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPadX.this.velobar.getProgress();
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[3], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[3] ^ 99), -91});
                    }
                }, 50L);
                this.daoNameIng = "bajada";
                setDefaultMode("bajada");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.101
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 16) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.102
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.bandeja /* 2131361897 */:
                this.tableName.setText(getResources().getString(R.string.bandeja));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.bandeja.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                this.isNumDing = 1;
                if (this.modeNum != 15) {
                    this.isNumDing = 0;
                    this.lr = 1200;
                    this.ud = 4200;
                    short s3 = (short) 1200;
                    short s4 = (short) 4200;
                    writeBleData(new byte[]{-86, 108, (byte) (s3 >> 8), (byte) s3, (byte) (s4 >> 8), (byte) s4, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 15;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(3.0f);
                this.v_tv.setText("" + this.veloTins[2]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.97
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPadX.this.velobar.getProgress();
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[2], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[2] ^ 99), -91});
                    }
                }, 50L);
                this.daoNameIng = "bandeja";
                setDefaultMode("bandeja");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.98
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 15) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.99
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            default:
                switch (id) {
                    case R.id.btn_ball /* 2131361912 */:
                        if (this.blenoty.getText().toString().equals(getResources().getString(R.string.disconnected))) {
                            ShowHelper.toastShort(this, getResources().getString(R.string.check_connect));
                            break;
                        } else if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                            this.isFaultOn = 0;
                            int i3 = this.modeCate;
                            if (i3 == 0) {
                                this.savedefault.setVisibility(0);
                                int i4 = this.modeNum;
                                if (i4 == 1 || i4 == 7 || i4 == 15 || i4 == 16 || i4 == 17) {
                                    AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.109
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                        }
                                    }, 200L);
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
                                    if (this.tagV == 1) {
                                        writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                    if (this.tagV == 2) {
                                        writeBleData(new byte[]{-86, 106, 3, 4, Ascii.VT, Ascii.DC2, Ascii.EM, 32, 77, -91});
                                    }
                                    if (this.tagV == 3) {
                                        writeBleData(new byte[]{-86, 106, 3, 4, Ascii.EM, 0, 0, 0, 116, -91});
                                    }
                                }
                                if (this.modeNum == 4) {
                                    writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                                }
                                int i5 = this.modeNum;
                                if (i5 == 5 || i5 == 8 || i5 == 9) {
                                    AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.110
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                        }
                                    }, 200L);
                                }
                                if (this.modeNum == 6) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.111
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i6;
                                            int unused = MainActivityPadX.this.tagC;
                                            int i7 = 28;
                                            int i8 = 22;
                                            int i9 = MainActivityPadX.this.tagC == 2 ? 28 : 22;
                                            int i10 = 25;
                                            if (MainActivityPadX.this.tagC == 3) {
                                                i9 = 25;
                                                i6 = 1;
                                            } else {
                                                i6 = 4;
                                            }
                                            if (MainActivityPadX.this.tagC == 4) {
                                                i6 = 7;
                                            } else {
                                                i10 = i9;
                                            }
                                            if (MainActivityPadX.this.tagC == 5) {
                                                i6 = 7;
                                            } else {
                                                i8 = i10;
                                            }
                                            if (MainActivityPadX.this.tagC == 6) {
                                                i6 = 1;
                                            } else {
                                                i7 = i8;
                                            }
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 1, (byte) i7, (byte) i6, 0, 0, 0, (byte) ((i7 ^ 108) ^ i6), -91});
                                        }
                                    }, 5L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.112
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                        }
                                    }, 50L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.113
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                        }
                                    }, 80L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.114
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                        }
                                    }, 110L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.115
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                        }
                                    }, 140L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.116
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                        }
                                    }, 170L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.117
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                        }
                                    }, 250L);
                                }
                                if (this.modeNum == 0) {
                                    ShowHelper.toastShort(this, getResources().getString(R.string.select_one_mode));
                                }
                            } else if (i3 == 2) {
                                if (this.selectPoints.size() == 0) {
                                    ShowHelper.toastShort(this, getResources().getString(R.string.select_points));
                                    break;
                                } else {
                                    int i6 = 0;
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
                                    this.t29 = 0;
                                    this.t30 = 0;
                                    this.t31 = 0;
                                    this.t32 = 0;
                                    this.t33 = 0;
                                    this.t34 = 0;
                                    this.t35 = 0;
                                    int i7 = 0;
                                    while (i7 < this.selectPoints.size()) {
                                        if (i7 == 0) {
                                            this.t1 = this.selectPoints.get(i6).intValue() + 35;
                                        }
                                        if (i7 == 1) {
                                            this.t2 = this.selectPoints.get(1).intValue() + 35;
                                        }
                                        if (i7 == 2) {
                                            this.t3 = this.selectPoints.get(2).intValue() + 35;
                                        }
                                        if (i7 == 3) {
                                            this.t4 = this.selectPoints.get(3).intValue() + 35;
                                        }
                                        if (i7 == 4) {
                                            this.t5 = this.selectPoints.get(4).intValue() + 35;
                                        }
                                        if (i7 == 5) {
                                            this.t6 = this.selectPoints.get(5).intValue() + 35;
                                        }
                                        if (i7 == 6) {
                                            this.t7 = this.selectPoints.get(6).intValue() + 35;
                                        }
                                        if (i7 == 7) {
                                            this.t8 = this.selectPoints.get(7).intValue() + 35;
                                        }
                                        if (i7 == 8) {
                                            this.t9 = this.selectPoints.get(8).intValue() + 35;
                                        }
                                        if (i7 == 9) {
                                            this.t10 = this.selectPoints.get(9).intValue() + 35;
                                        }
                                        if (i7 == 10) {
                                            this.t11 = this.selectPoints.get(10).intValue() + 35;
                                        }
                                        if (i7 == 11) {
                                            this.t12 = this.selectPoints.get(11).intValue() + 35;
                                        }
                                        if (i7 == 12) {
                                            this.t13 = this.selectPoints.get(12).intValue() + 35;
                                        }
                                        if (i7 == 13) {
                                            this.t14 = this.selectPoints.get(13).intValue() + 35;
                                        }
                                        if (i7 == 14) {
                                            this.t15 = this.selectPoints.get(14).intValue() + 35;
                                        }
                                        if (i7 == 15) {
                                            this.t16 = this.selectPoints.get(15).intValue() + 35;
                                        }
                                        if (i7 == 16) {
                                            this.t17 = this.selectPoints.get(16).intValue() + 35;
                                        }
                                        if (i7 == 17) {
                                            this.t18 = this.selectPoints.get(17).intValue() + 35;
                                        }
                                        if (i7 == 18) {
                                            this.t19 = this.selectPoints.get(18).intValue() + 35;
                                        }
                                        if (i7 == 19) {
                                            this.t20 = this.selectPoints.get(19).intValue() + 35;
                                        }
                                        if (i7 == 20) {
                                            this.t21 = this.selectPoints.get(20).intValue() + 35;
                                        }
                                        if (i7 == 21) {
                                            this.t22 = this.selectPoints.get(21).intValue() + 35;
                                        }
                                        if (i7 == 22) {
                                            this.t23 = this.selectPoints.get(22).intValue() + 35;
                                        }
                                        if (i7 == 23) {
                                            this.t24 = this.selectPoints.get(23).intValue() + 35;
                                        }
                                        if (i7 == 24) {
                                            this.t25 = this.selectPoints.get(24).intValue() + 35;
                                        }
                                        if (i7 == 25) {
                                            this.t26 = this.selectPoints.get(25).intValue() + 35;
                                        }
                                        if (i7 == 26) {
                                            this.t27 = this.selectPoints.get(26).intValue() + 35;
                                        }
                                        if (i7 == 27) {
                                            this.t28 = this.selectPoints.get(27).intValue() + 35;
                                        }
                                        i7++;
                                        i6 = 0;
                                    }
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.118
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivityPadX.this.t1, (byte) MainActivityPadX.this.t2, (byte) MainActivityPadX.this.t3, (byte) MainActivityPadX.this.t4, (byte) MainActivityPadX.this.t5, (byte) (((((MainActivityPadX.this.t1 ^ 108) ^ MainActivityPadX.this.t2) ^ MainActivityPadX.this.t3) ^ MainActivityPadX.this.t4) ^ MainActivityPadX.this.t5), -91});
                                        }
                                    }, 5L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.119
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivityPadX.this.t6, (byte) MainActivityPadX.this.t7, (byte) MainActivityPadX.this.t8, (byte) MainActivityPadX.this.t9, (byte) MainActivityPadX.this.t10, (byte) (((((MainActivityPadX.this.t6 ^ 111) ^ MainActivityPadX.this.t7) ^ MainActivityPadX.this.t8) ^ MainActivityPadX.this.t9) ^ MainActivityPadX.this.t10), -91});
                                        }
                                    }, 50L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.120
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivityPadX.this.t11, (byte) MainActivityPadX.this.t12, (byte) MainActivityPadX.this.t13, (byte) MainActivityPadX.this.t14, (byte) MainActivityPadX.this.t15, (byte) (((((MainActivityPadX.this.t11 ^ 110) ^ MainActivityPadX.this.t12) ^ MainActivityPadX.this.t13) ^ MainActivityPadX.this.t14) ^ MainActivityPadX.this.t15), -91});
                                        }
                                    }, 100L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.121
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivityPadX.this.t16, (byte) MainActivityPadX.this.t17, (byte) MainActivityPadX.this.t18, (byte) MainActivityPadX.this.t19, (byte) MainActivityPadX.this.t20, (byte) (((((MainActivityPadX.this.t16 ^ 105) ^ MainActivityPadX.this.t17) ^ MainActivityPadX.this.t18) ^ MainActivityPadX.this.t19) ^ MainActivityPadX.this.t20), -91});
                                        }
                                    }, 150L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.122
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivityPadX.this.t21, (byte) MainActivityPadX.this.t22, (byte) MainActivityPadX.this.t23, (byte) MainActivityPadX.this.t24, (byte) MainActivityPadX.this.t25, (byte) (((((MainActivityPadX.this.t21 ^ 104) ^ MainActivityPadX.this.t22) ^ MainActivityPadX.this.t23) ^ MainActivityPadX.this.t24) ^ MainActivityPadX.this.t25), -91});
                                        }
                                    }, 200L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.123
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivityPadX.this.t26, (byte) MainActivityPadX.this.t27, (byte) MainActivityPadX.this.t28, 0, 0, (byte) (((MainActivityPadX.this.t26 ^ 107) ^ MainActivityPadX.this.t27) ^ MainActivityPadX.this.t28), -91});
                                        }
                                    }, 250L);
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.124
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
                                        }
                                    }, 320L);
                                }
                            } else if (this.selectPoints.size() == 0) {
                                ShowHelper.toastShort(this, getResources().getString(R.string.select_points));
                                break;
                            } else {
                                int i8 = 0;
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
                                this.t29 = 0;
                                this.t30 = 0;
                                this.t31 = 0;
                                this.t32 = 0;
                                this.t33 = 0;
                                this.t34 = 0;
                                this.t35 = 0;
                                int i9 = 0;
                                while (i9 < this.selectPoints.size()) {
                                    if (i9 == 0) {
                                        this.t1 = this.selectPoints.get(i8).intValue();
                                    }
                                    if (i9 == 1) {
                                        this.t2 = this.selectPoints.get(1).intValue();
                                    }
                                    if (i9 == 2) {
                                        this.t3 = this.selectPoints.get(2).intValue();
                                    }
                                    if (i9 == 3) {
                                        this.t4 = this.selectPoints.get(3).intValue();
                                    }
                                    if (i9 == 4) {
                                        this.t5 = this.selectPoints.get(4).intValue();
                                    }
                                    if (i9 == 5) {
                                        this.t6 = this.selectPoints.get(5).intValue();
                                    }
                                    if (i9 == 6) {
                                        this.t7 = this.selectPoints.get(6).intValue();
                                    }
                                    if (i9 == 7) {
                                        this.t8 = this.selectPoints.get(7).intValue();
                                    }
                                    if (i9 == 8) {
                                        this.t9 = this.selectPoints.get(8).intValue();
                                    }
                                    if (i9 == 9) {
                                        this.t10 = this.selectPoints.get(9).intValue();
                                    }
                                    if (i9 == 10) {
                                        this.t11 = this.selectPoints.get(10).intValue();
                                    }
                                    if (i9 == 11) {
                                        this.t12 = this.selectPoints.get(11).intValue();
                                    }
                                    if (i9 == 12) {
                                        this.t13 = this.selectPoints.get(12).intValue();
                                    }
                                    if (i9 == 13) {
                                        this.t14 = this.selectPoints.get(13).intValue();
                                    }
                                    if (i9 == 14) {
                                        this.t15 = this.selectPoints.get(14).intValue();
                                    }
                                    if (i9 == 15) {
                                        this.t16 = this.selectPoints.get(15).intValue();
                                    }
                                    if (i9 == 16) {
                                        this.t17 = this.selectPoints.get(16).intValue();
                                    }
                                    if (i9 == 17) {
                                        this.t18 = this.selectPoints.get(17).intValue();
                                    }
                                    if (i9 == 18) {
                                        this.t19 = this.selectPoints.get(18).intValue();
                                    }
                                    if (i9 == 19) {
                                        this.t20 = this.selectPoints.get(19).intValue();
                                    }
                                    if (i9 == 20) {
                                        this.t21 = this.selectPoints.get(20).intValue();
                                    }
                                    if (i9 == 21) {
                                        this.t22 = this.selectPoints.get(21).intValue();
                                    }
                                    if (i9 == 22) {
                                        this.t23 = this.selectPoints.get(22).intValue();
                                    }
                                    if (i9 == 23) {
                                        this.t24 = this.selectPoints.get(23).intValue();
                                    }
                                    if (i9 == 24) {
                                        this.t25 = this.selectPoints.get(24).intValue();
                                    }
                                    if (i9 == 25) {
                                        this.t26 = this.selectPoints.get(25).intValue();
                                    }
                                    if (i9 == 26) {
                                        this.t27 = this.selectPoints.get(26).intValue();
                                    }
                                    if (i9 == 27) {
                                        this.t28 = this.selectPoints.get(27).intValue();
                                    }
                                    i9++;
                                    i8 = 0;
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.125
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivityPadX.this.t1, (byte) MainActivityPadX.this.t2, (byte) MainActivityPadX.this.t3, (byte) MainActivityPadX.this.t4, (byte) MainActivityPadX.this.t5, (byte) (((((MainActivityPadX.this.t1 ^ 108) ^ MainActivityPadX.this.t2) ^ MainActivityPadX.this.t3) ^ MainActivityPadX.this.t4) ^ MainActivityPadX.this.t5), -91});
                                    }
                                }, 5L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.126
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivityPadX.this.t6, (byte) MainActivityPadX.this.t7, (byte) MainActivityPadX.this.t8, (byte) MainActivityPadX.this.t9, (byte) MainActivityPadX.this.t10, (byte) (((((MainActivityPadX.this.t6 ^ 111) ^ MainActivityPadX.this.t7) ^ MainActivityPadX.this.t8) ^ MainActivityPadX.this.t9) ^ MainActivityPadX.this.t10), -91});
                                    }
                                }, 50L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.127
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivityPadX.this.t11, (byte) MainActivityPadX.this.t12, (byte) MainActivityPadX.this.t13, (byte) MainActivityPadX.this.t14, (byte) MainActivityPadX.this.t15, (byte) (((((MainActivityPadX.this.t11 ^ 110) ^ MainActivityPadX.this.t12) ^ MainActivityPadX.this.t13) ^ MainActivityPadX.this.t14) ^ MainActivityPadX.this.t15), -91});
                                    }
                                }, 100L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.128
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivityPadX.this.t16, (byte) MainActivityPadX.this.t17, (byte) MainActivityPadX.this.t18, (byte) MainActivityPadX.this.t19, (byte) MainActivityPadX.this.t20, (byte) (((((MainActivityPadX.this.t16 ^ 105) ^ MainActivityPadX.this.t17) ^ MainActivityPadX.this.t18) ^ MainActivityPadX.this.t19) ^ MainActivityPadX.this.t20), -91});
                                    }
                                }, 150L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.129
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivityPadX.this.t21, (byte) MainActivityPadX.this.t22, (byte) MainActivityPadX.this.t23, (byte) MainActivityPadX.this.t24, (byte) MainActivityPadX.this.t25, (byte) (((((MainActivityPadX.this.t21 ^ 104) ^ MainActivityPadX.this.t22) ^ MainActivityPadX.this.t23) ^ MainActivityPadX.this.t24) ^ MainActivityPadX.this.t25), -91});
                                    }
                                }, 200L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.130
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivityPadX.this.t26, (byte) MainActivityPadX.this.t27, (byte) MainActivityPadX.this.t28, 0, 0, (byte) (((MainActivityPadX.this.t26 ^ 107) ^ MainActivityPadX.this.t27) ^ MainActivityPadX.this.t28), -91});
                                    }
                                }, 250L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.131
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 320L);
                            }
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.132
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 270L);
                            this.btn_ball.setText(getResources().getString(R.string.stop));
                            this.signal_note.setText(getResources().getString(R.string.status_on));
                            this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                            this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                            break;
                        } else {
                            this.stopMode = this.modeNum;
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.133
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 260L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.134
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.checkPower();
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
                        int i10 = this.tagV + 1;
                        this.tagV = i10;
                        if (i10 > 3) {
                            this.tagV = 1;
                        }
                        if (this.tagV == 1) {
                            this.tableName.setText(getResources().getString(R.string.vertical_random));
                        }
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
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        short s5 = BasicData22.m11[0];
                        this.lr = s5;
                        this.ud = 2000;
                        short s6 = s5;
                        short s7 = (short) 2000;
                        writeBleData(new byte[]{-86, 108, (byte) (s6 >> 8), (byte) s6, (byte) (s7 >> 8), (byte) s7, 0, 0, 1, -91});
                        this.num_lr.setText("" + ((this.lr / 30) + 2));
                        this.num_ud.setText("" + (this.ud / 100));
                        hideUD();
                        if (this.modeNum != 3) {
                            this.velobar.setProgress(7.0f);
                            this.v_tv.setText("" + this.veloTins[6]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.71
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
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
                        String str = "ud" + this.tagV;
                        this.daoNameIng = str;
                        setDefaultMode(str);
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.72
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.73
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (MainActivityPadX.this.tagV == 1) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                            }
                                            if (MainActivityPadX.this.tagV == 2) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.VT, Ascii.DC2, Ascii.EM, 32, 77, -91});
                                            }
                                            if (MainActivityPadX.this.tagV == 3) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.EM, 0, 0, 0, 116, -91});
                                            }
                                        }
                                    }, 30L);
                                }
                                if (this.modeNum == 4) {
                                    writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                                    break;
                                }
                            }
                        }
                        break;
                    case R.id.group_cross /* 2131362113 */:
                        int i11 = this.tagC + 1;
                        this.tagC = i11;
                        if (i11 > 6) {
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
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.points.setVisibility(8);
                        if (this.modeNum != 6) {
                            this.velobar.setProgress(7.0f);
                            this.v_tv.setText("" + this.veloTins[6]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.77
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
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
                        String str2 = "cross" + this.tagC;
                        this.daoNameIng = str2;
                        setDefaultMode(str2);
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.78
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 250L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.79
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i12;
                                    int unused = MainActivityPadX.this.tagC;
                                    int i13 = 28;
                                    int i14 = 22;
                                    int i15 = MainActivityPadX.this.tagC == 2 ? 28 : 22;
                                    int i16 = 25;
                                    if (MainActivityPadX.this.tagC == 3) {
                                        i15 = 25;
                                        i12 = 1;
                                    } else {
                                        i12 = 4;
                                    }
                                    if (MainActivityPadX.this.tagC == 4) {
                                        i12 = 7;
                                    } else {
                                        i16 = i15;
                                    }
                                    if (MainActivityPadX.this.tagC == 5) {
                                        i12 = 7;
                                    } else {
                                        i14 = i16;
                                    }
                                    if (MainActivityPadX.this.tagC == 6) {
                                        i12 = 1;
                                    } else {
                                        i13 = i14;
                                    }
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 1, (byte) i13, (byte) i12, 0, 0, 0, (byte) ((i13 ^ 108) ^ i12), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.80
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.81
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                }
                            }, 80L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.82
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                }
                            }, 110L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.83
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                }
                            }, 140L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.84
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 170L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.85
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
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
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        this.isNumDing = 1;
                        if (this.modeNum != 5) {
                            this.isNumDing = 0;
                            this.lr = 1200;
                            this.ud = 3600;
                            short s8 = (short) 1200;
                            short s9 = (short) 3600;
                            writeBleData(new byte[]{-86, 108, (byte) (s8 >> 8), (byte) s8, (byte) (s9 >> 8), (byte) s9, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                        }
                        this.modeNum = 5;
                        this.modeCate = 0;
                        changeOperate();
                        showTvs(this.fx1);
                        this.velobar.setProgress(4.0f);
                        this.v_tv.setText("" + this.veloTins[3]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.74
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.velobar.getProgress();
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[3], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[3] ^ 99), -91});
                            }
                        }, 50L);
                        this.daoNameIng = "high";
                        setDefaultMode("high");
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.75
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 100L);
                            if (this.modeCate == 0 && this.modeNum == 5) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.76
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                                break;
                            }
                        }
                        break;
                    case R.id.hit /* 2131362126 */:
                        int i12 = this.tagHT + 1;
                        this.tagHT = i12;
                        if (i12 > 3) {
                            this.tagHT = 1;
                        }
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
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        int i13 = this.tagHT;
                        if (i13 == 1) {
                            this.lr = 1200;
                        }
                        if (i13 == 2) {
                            this.lr = 270;
                        }
                        if (i13 == 3) {
                            this.lr = 2130;
                        }
                        this.ud = 1800;
                        short s10 = (short) this.lr;
                        short s11 = (short) 1800;
                        writeBleData(new byte[]{-86, 108, (byte) (s10 >> 8), (byte) s10, (byte) (s11 >> 8), (byte) s11, 0, 0, 1, -91});
                        this.num_lr.setText("" + ((this.lr / 30) + 2));
                        this.num_ud.setText("" + (this.ud / 100));
                        this.isNumDing = 1;
                        if (this.modeNum != 7) {
                            i = 0;
                            this.isNumDing = 0;
                        } else {
                            i = 0;
                        }
                        this.modeNum = 7;
                        this.modeCate = i;
                        changeOperate();
                        this.velobar.setProgress(6.0f);
                        this.v_tv.setText("" + this.veloTins[5]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.86
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[5], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[5] ^ 99), -91});
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
                        String str3 = "hit" + this.tagHT;
                        this.daoNameIng = str3;
                        setDefaultMode(str3);
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.87
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                                }
                            }, 100L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.88
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                            break;
                        }
                        break;
                    case R.id.left_right /* 2131362169 */:
                        int i14 = this.tagH + 1;
                        this.tagH = i14;
                        if (i14 > 5) {
                            this.tagH = 1;
                        }
                        if (this.tagH == 1) {
                            this.tableName.setText(getResources().getString(R.string.horizontal_random));
                        }
                        if (this.tagH == 2) {
                            this.tableName.setText(getResources().getString(R.string.horizontal_wide_line));
                        }
                        if (this.tagH == 3) {
                            this.tableName.setText(getResources().getString(R.string.horizontal_middle_line));
                        }
                        if (this.tagH == 4) {
                            this.tableName.setText(getResources().getString(R.string.horizontal_narrow_line));
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
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        hideLR();
                        short s12 = BasicData22.m11[0];
                        this.lr = s12;
                        this.ud = 2000;
                        short s13 = s12;
                        short s14 = (short) 2000;
                        writeBleData(new byte[]{-86, 108, (byte) (s13 >> 8), (byte) s13, (byte) (s14 >> 8), (byte) s14, 0, 0, 1, -91});
                        this.num_lr.setText("" + ((this.lr / 30) + 2));
                        this.num_ud.setText("" + (this.ud / 100));
                        showUD();
                        if (this.modeNum != 2) {
                            this.velobar.setProgress(7.0f);
                            this.v_tv.setText("" + this.veloTins[6]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.68
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
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
                        String str4 = "lr" + this.tagH;
                        this.daoNameIng = str4;
                        setDefaultMode(str4);
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.69
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 100L);
                            if (this.modeCate == 0) {
                                if (this.modeNum == 1) {
                                    writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                                if (this.modeNum == 2 && this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.70
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            if (MainActivityPadX.this.tagH == 1) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                            }
                                            if (MainActivityPadX.this.tagH == 2) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.NAK, 0, 0, 0, 114, -91});
                                            }
                                            if (MainActivityPadX.this.tagH == 3) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 2, Ascii.DLE, Ascii.DC4, 0, 0, 0, 110, -91});
                                            }
                                            if (MainActivityPadX.this.tagH == 4) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 2, 17, 19, 0, 0, 0, 106, -91});
                                            }
                                            if (MainActivityPadX.this.tagH == 5) {
                                                MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.DC2, Ascii.NAK, 0, 0, 96, -91});
                                            }
                                        }
                                    }, 30L);
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
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        this.isNumDing = 1;
                        if (this.modeNum != 9) {
                            this.isNumDing = 0;
                            this.lr = 1200;
                            this.ud = PathInterpolatorCompat.MAX_NUM_POINTS;
                            short s15 = (short) 1200;
                            short s16 = (short) PathInterpolatorCompat.MAX_NUM_POINTS;
                            writeBleData(new byte[]{-86, 108, (byte) (s15 >> 8), (byte) s15, (byte) (s16 >> 8), (byte) s16, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                        }
                        this.modeNum = 9;
                        this.modeCate = 0;
                        changeOperate();
                        showTvs(this.fx1);
                        this.velobar.setProgress(5.0f);
                        this.v_tv.setText("" + this.veloTins[4]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.93
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.velobar.getProgress();
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[4] ^ 99), -91});
                            }
                        }, 50L);
                        this.rotatebar.setProgress(6.0f);
                        this.r_tv.setText("6");
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.94
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.rotatebar.setProgress(6.0f);
                                MainActivityPadX.this.r_tv.setText("6");
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 1, Ascii.RS, 0, 0, 0, 0, 122, -91});
                            }
                        }, 75L);
                        this.daoNameIng = "moon";
                        setDefaultMode("moon");
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.95
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 100L);
                            if (this.modeCate == 0 && this.modeNum == 9) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.96
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                                break;
                            }
                        }
                        break;
                    case R.id.num22 /* 2131362267 */:
                        if (this.selectPoints.size() < 35) {
                            this.selectPoints.add(22);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num23 /* 2131362269 */:
                        if (this.selectPoints.size() < 35) {
                            this.selectPoints.add(23);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num24 /* 2131362271 */:
                        if (this.selectPoints.size() < 35) {
                            this.selectPoints.add(24);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num25 /* 2131362273 */:
                        if (this.selectPoints.size() < 35) {
                            this.selectPoints.add(25);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num26 /* 2131362275 */:
                        if (this.selectPoints.size() < 35) {
                            this.selectPoints.add(26);
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
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        this.isNumDing = 1;
                        if (this.modeNum != 8) {
                            this.isNumDing = 0;
                            this.lr = 1200;
                            this.ud = 2300;
                            short s17 = (short) 1200;
                            short s18 = (short) 2300;
                            writeBleData(new byte[]{-86, 108, (byte) (s17 >> 8), (byte) s17, (byte) (s18 >> 8), (byte) s18, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                        }
                        this.modeNum = 8;
                        this.modeCate = 0;
                        changeOperate();
                        showTvs(this.fx1);
                        this.velobar.setProgress(1.0f);
                        this.v_tv.setText("" + this.veloTins[0]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.89
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.velobar.getProgress();
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[0], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[0] ^ 99), -91});
                            }
                        }, 50L);
                        this.rotatebar.setProgress(0.0f);
                        this.r_tv.setText("0");
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.90
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 99, -91});
                            }
                        }, 75L);
                        this.daoNameIng = "place";
                        setDefaultMode("place");
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.91
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 100L);
                            if (this.modeCate == 0 && this.modeNum == 8) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.92
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.points.setVisibility(0);
                        this.group_cate.setVisibility(0);
                        this.selectPoints.clear();
                        showPoints();
                        this.modeCate = 1;
                        changeOperate();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.106
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
                                MainActivityPadX.this.velobar.setProgress(7.0f);
                                MainActivityPadX.this.v_tv.setText("" + MainActivityPadX.this.veloTins[6]);
                            }
                        }, 50L);
                        break;
                    case R.id.self_point /* 2131362423 */:
                        int i15 = this.tagFix + 1;
                        this.tagFix = i15;
                        if (i15 > 3) {
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
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        int i16 = this.tagFix;
                        if (i16 == 1) {
                            this.lr = 1200;
                        }
                        if (i16 == 2) {
                            this.lr = 270;
                        }
                        if (i16 == 3) {
                            this.lr = 2130;
                        }
                        this.ud = 2000;
                        short s19 = (short) this.lr;
                        short s20 = (short) 2000;
                        writeBleData(new byte[]{-86, 108, (byte) (s19 >> 8), (byte) s19, (byte) (s20 >> 8), (byte) s20, 0, 0, 1, -91});
                        this.num_lr.setText("" + ((this.lr / 30) + 2));
                        this.num_ud.setText("" + (this.ud / 100));
                        this.isNumDing = 1;
                        if (this.modeNum != 1) {
                            i2 = 0;
                            this.isNumDing = 0;
                        } else {
                            i2 = 0;
                        }
                        this.modeNum = 1;
                        this.modeCate = i2;
                        changeOperate();
                        this.velobar.setProgress(7.0f);
                        this.v_tv.setText("" + this.veloTins[6]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.65
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
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
                        String str5 = "fix" + this.tagFix;
                        this.daoNameIng = str5;
                        setDefaultMode(str5);
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.66
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                                }
                            }, 100L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.67
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                            break;
                        }
                        break;
                    case R.id.serve_return /* 2131362429 */:
                        this.tableName.setText(getResources().getString(R.string.servereturn));
                        this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showLR();
                        showUD();
                        this.isNumDing = 1;
                        if (this.modeNum != 17) {
                            this.isNumDing = 0;
                            this.lr = 1200;
                            this.ud = 2000;
                            short s21 = (short) 1200;
                            short s22 = (short) 2000;
                            writeBleData(new byte[]{-86, 108, (byte) (s21 >> 8), (byte) s21, (byte) (s22 >> 8), (byte) s22, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                        }
                        this.modeNum = 17;
                        this.modeCate = 0;
                        changeOperate();
                        showTvs(this.fx1);
                        this.velobar.setProgress(2.0f);
                        this.v_tv.setText("" + this.veloTins[1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.103
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivityPadX.this.velobar.getProgress();
                                MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[1], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[1] ^ 99), -91});
                            }
                        }, 50L);
                        this.daoNameIng = "sreturn";
                        setDefaultMode("sreturn");
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.104
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                }
                            }, 100L);
                            if (this.modeCate == 0 && this.modeNum == 17) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.105
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPadX.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                                break;
                            }
                        }
                        break;
                    case R.id.step /* 2131362542 */:
                        this.tableName.setText(getResources().getString(R.string.vari));
                        this.group_cross.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.selection.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.hit.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.self_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.points.setVisibility(0);
                        this.selectPoints.clear();
                        showPoints();
                        this.modeCate = 2;
                        changeOperate();
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
                        this.bandeja.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.bajada.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.serve_return.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                        this.points.setVisibility(8);
                        this.group_cate.setVisibility(8);
                        this.tennipic3.setVisibility(8);
                        this.tennipic4.setVisibility(8);
                        this.tennipic5.setVisibility(0);
                        showTvs(this.wh1);
                        if (this.modeNum != 4) {
                            this.velobar.setProgress(7.0f);
                            this.v_tv.setText("" + this.veloTins[6]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.107
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPadX.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPadX.this.veloNums[6] ^ 99), -91});
                                }
                            }, 50L);
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
                        this.daoNameIng = "whole";
                        setDefaultMode("whole");
                        if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.108
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(1);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num10 /* 2131362252 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(10);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num11 /* 2131362253 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(11);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num12 /* 2131362254 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(12);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num13 /* 2131362255 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(13);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num14 /* 2131362256 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(14);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num15 /* 2131362257 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(15);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num16 /* 2131362258 */:
                                if (this.selectPoints.size() < 35) {
                                    this.selectPoints.add(16);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            default:
                                switch (id) {
                                    case R.id.num17 /* 2131362260 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(17);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num18 /* 2131362261 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(18);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num19 /* 2131362262 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(19);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num2 /* 2131362263 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(2);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num20 /* 2131362264 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(20);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num21 /* 2131362265 */:
                                        if (this.selectPoints.size() < 35) {
                                            this.selectPoints.add(21);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    default:
                                        switch (id) {
                                            case R.id.num27 /* 2131362277 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(27);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num28 /* 2131362278 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(28);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num29 /* 2131362279 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(29);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num3 /* 2131362280 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(3);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num30 /* 2131362281 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(30);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num31 /* 2131362282 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(31);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num32 /* 2131362283 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(32);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num33 /* 2131362284 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(33);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num34 /* 2131362285 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(34);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num35 /* 2131362286 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(35);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num36 /* 2131362287 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(1);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num37 /* 2131362288 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(2);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num38 /* 2131362289 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(3);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num39 /* 2131362290 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(4);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num4 /* 2131362291 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(4);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num40 /* 2131362292 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(5);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num41 /* 2131362293 */:
                                                if (this.selectPoints.size() < 35) {
                                                    this.selectPoints.add(6);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            default:
                                                switch (id) {
                                                    case R.id.num5 /* 2131362299 */:
                                                        if (this.selectPoints.size() < 35) {
                                                            this.selectPoints.add(5);
                                                        } else {
                                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                        }
                                                        showPoints();
                                                        break;
                                                    case R.id.num6 /* 2131362300 */:
                                                        if (this.selectPoints.size() < 35) {
                                                            this.selectPoints.add(6);
                                                        } else {
                                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                        }
                                                        showPoints();
                                                        break;
                                                    case R.id.num7 /* 2131362301 */:
                                                        if (this.selectPoints.size() < 35) {
                                                            this.selectPoints.add(7);
                                                        } else {
                                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                        }
                                                        showPoints();
                                                        break;
                                                    case R.id.num8 /* 2131362302 */:
                                                        if (this.selectPoints.size() < 35) {
                                                            this.selectPoints.add(8);
                                                        } else {
                                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                        }
                                                        showPoints();
                                                        break;
                                                    case R.id.num9 /* 2131362303 */:
                                                        if (this.selectPoints.size() < 35) {
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBottomDialog() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.setContentView(View.inflate(this, R.layout.keyboard_layout, null));
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(-1, -2);
        dialog.show();
        TextView textView = (TextView) dialog.findViewById(R.id.select_dianwei2);
        this.select_dianwei2 = textView;
        textView.setText(this.select_dianwei.getText().toString());
        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.135
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.negative).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.136
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() > 0) {
                    MainActivityPadX.this.selectPoints.remove(MainActivityPadX.this.selectPoints.size() - 1);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp1).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.137
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(1);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp2).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.138
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(2);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp3).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.139
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(3);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp4).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.140
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(4);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp5).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.141
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(5);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp6).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.142
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(6);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp7).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.143
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(7);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp8).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.144
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(8);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp9).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.145
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(9);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp10).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.146
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(10);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp11).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.147
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(11);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp12).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.148
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(12);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp13).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.149
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(13);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp14).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.150
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(14);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp15).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.151
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(15);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp16).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.152
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(16);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp17).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.153
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(17);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp18).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.154
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(18);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp19).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.155
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(19);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp20).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.156
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(20);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp21).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.157
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(21);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp22).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.158
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(22);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp23).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.159
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(23);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp24).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.160
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(24);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp25).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.161
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(25);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp26).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.162
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(26);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp27).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.163
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(27);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp28).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.164
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(28);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp29).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.165
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(29);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp30).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.166
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(30);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp31).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.167
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(31);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp32).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.168
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(32);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp33).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.169
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(33);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp34).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.170
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(34);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp35).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.171
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPadX.this.selectPoints.size() < 35) {
                    MainActivityPadX.this.selectPoints.add(35);
                } else {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastLong(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.up_to));
                }
                MainActivityPadX.this.showPoints();
                MainActivityPadX.this.select_dianwei2.setText(MainActivityPadX.this.select_dianwei.getText().toString());
            }
        });
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
            MainActivityPadX.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPadX.access$512(MainActivityPadX.this, 100);
                if (MainActivityPadX.this.ud < 300) {
                    MainActivityPadX.this.ud = 300;
                }
                if (MainActivityPadX.this.ud > 4300) {
                    MainActivityPadX.this.ud = 4300;
                }
                if (MainActivityPadX.this.modeCate == 0 && ((MainActivityPadX.this.modeNum == 1 || MainActivityPadX.this.modeNum == 2) && MainActivityPadX.this.ud > 2000)) {
                    MainActivityPadX.this.ud = 2000;
                }
                short s = (short) MainActivityPadX.this.lr;
                if (MainActivityPadX.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPadX.this.ud;
                if (MainActivityPadX.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPadX.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPadX.access$520(MainActivityPadX.this, 100);
                if (MainActivityPadX.this.ud < 300) {
                    MainActivityPadX.this.ud = 300;
                }
                if (MainActivityPadX.this.ud > 4300) {
                    MainActivityPadX.this.ud = 4300;
                }
                if (MainActivityPadX.this.modeCate == 0 && MainActivityPadX.this.modeNum == 5 && MainActivityPadX.this.ud < 2000) {
                    MainActivityPadX.this.ud = 2000;
                }
                short s = (short) MainActivityPadX.this.lr;
                if (MainActivityPadX.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPadX.this.ud;
                if (MainActivityPadX.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPadX.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPadX.access$412(MainActivityPadX.this, 30);
                if (MainActivityPadX.this.lr < 90) {
                    MainActivityPadX.this.lr = 90;
                }
                if (MainActivityPadX.this.lr > 2370) {
                    MainActivityPadX.this.lr = 2370;
                }
                short s = (short) MainActivityPadX.this.lr;
                if (MainActivityPadX.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPadX.this.ud;
                if (MainActivityPadX.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                AppLog.e("count3:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPadX.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPadX.access$420(MainActivityPadX.this, 30);
                if (MainActivityPadX.this.lr < 90) {
                    MainActivityPadX.this.lr = 90;
                }
                if (MainActivityPadX.this.lr > 2370) {
                    MainActivityPadX.this.lr = 2370;
                }
                short s = (short) MainActivityPadX.this.lr;
                if (MainActivityPadX.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPadX.this.ud;
                if (MainActivityPadX.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPadX.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPadX.this.num_lr.setText("" + ((MainActivityPadX.this.lr / 30) + 2));
                MainActivityPadX.this.num_ud.setText("" + (MainActivityPadX.this.ud / 100));
                AppLog.e("count4:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPadX.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPadX.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.172
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.173
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList().clear();
                List loadAll = MainActivityPadX.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivityPadX.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivityPadX.this.freq.getProgress());
                seleDao.setItem1(MainActivityPadX.this.radioNum);
                seleDao.setItem2(MainActivityPadX.this.valueSelect);
                seleDao.setVelo(MainActivityPadX.this.velobar.getProgress());
                seleDao.setRote(MainActivityPadX.this.rotatebar.getProgress());
                MainActivityPadX.this.daoSession.insertOrReplace(seleDao);
                StringBuilder sb = new StringBuilder("num:");
                sb.append(loadAll.size() + 1);
                sb.append("seleN:");
                sb.append(seleDao.getNumber());
                AppLog.e(sb.toString());
                create.dismiss();
            }
        });
        create.show();
    }

    public void alert_dialog_input2() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.174
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPadX.175
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivityPadX mainActivityPadX = MainActivityPadX.this;
                    ShowHelper.toastShort(mainActivityPadX, mainActivityPadX.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivityPadX.this.daoSession.loadAll(VariDao.class);
                VariDao variDao = new VariDao();
                variDao.setDaoName("" + editText.getText().toString().trim());
                variDao.setSeles(MainActivityPadX.this.select_dianwei.getText().toString().trim());
                variDao.setFreq1(BasicData22.b3[35][3]);
                variDao.setVelo1(BasicData22.b3[35][2]);
                variDao.setLr1(BasicData22.b3[35][0]);
                variDao.setUd1(BasicData22.b3[35][1]);
                variDao.setFreq2(BasicData22.b3[36][3]);
                variDao.setVelo2(BasicData22.b3[36][2]);
                variDao.setLr2(BasicData22.b3[36][0]);
                variDao.setUd2(BasicData22.b3[36][1]);
                variDao.setFreq3(BasicData22.b3[37][3]);
                variDao.setVelo3(BasicData22.b3[37][2]);
                variDao.setLr3(BasicData22.b3[37][0]);
                variDao.setUd3(BasicData22.b3[37][1]);
                variDao.setFreq4(BasicData22.b3[38][3]);
                variDao.setVelo4(BasicData22.b3[38][2]);
                variDao.setLr4(BasicData22.b3[38][0]);
                variDao.setUd4(BasicData22.b3[38][1]);
                variDao.setFreq5(BasicData22.b3[39][3]);
                variDao.setVelo5(BasicData22.b3[39][2]);
                variDao.setLr5(BasicData22.b3[39][0]);
                variDao.setUd5(BasicData22.b3[39][1]);
                variDao.setFreq6(BasicData22.b3[40][3]);
                variDao.setVelo6(BasicData22.b3[40][2]);
                variDao.setLr6(BasicData22.b3[40][0]);
                variDao.setUd6(BasicData22.b3[40][1]);
                MainActivityPadX.this.daoSession.insertOrReplace(variDao);
                create.dismiss();
            }
        });
        create.show();
    }
}
