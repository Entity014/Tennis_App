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
import com.pusun.pusuntennis.utils.BasicData38;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.Util;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.DefaultDao;
import com.pusun.pusuntennis.utils.dao.SeleDao;
import com.pusun.pusuntennis.utils.dao.VariDao;
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
import java.util.Random;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivity6 extends AppCompatActivity implements View.OnClickListener {
    private static final String FORBID_INFO = "forbid";
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
    private TextView l_ad;
    private EditText l_r;
    private TextView lastpoints;
    private TextView lastpoints2;
    private RelativeLayout layout_adjust;
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
    private TextView roadd;
    private TextView rode;
    private IndicatorSeekBar rotatebar;
    private EditText s_d;
    private Button savedefault;
    private TextView savepoints;
    private TextView savepoints2;
    private TextView select_dianwei;
    private Button selection;
    private LinearLayout self;
    private Button self_point;
    private TextView self_program;
    private TextView self_program2;
    private RelativeLayout self_re;
    private RelativeLayout self_re2;
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
    private RelativeLayout tennipic6;
    private RelativeLayout tennipic7;
    private TimeCount1 timeCount1;
    private TimeCount2 timeCount2;
    private TimeCount3 timeCount3;
    private TimeCount4 timeCount4;
    private int[] tvids;
    private int[] tvids15;
    private TextView[] tvs;
    private TextView u_ad;
    private EditText u_d;
    private Button u_dian;
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
    private int[] frequentNums = {88, 78, 68, 58, 48, 38, 33, 28, 23, 18};
    private int[] veloNums = {55, 63, 78, 86, 92, 96, 100, 110, 120, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 140, 150, 160};
    private int[] veloTins = {0, 2, 4, 5, 6, 7, 8, 10, 12, 14, 16, 18, 20};
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
    private int vari_point_num = 1;
    List<DefaultDao> defaultDaoList = new ArrayList();
    private int saveColor = 0;
    private String daoNameIng = "fix1";

    static /* synthetic */ int access$308(MainActivity6 mainActivity6) {
        int i = mainActivity6.valueSelect;
        mainActivity6.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(MainActivity6 mainActivity6) {
        int i = mainActivity6.valueSelect;
        mainActivity6.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$412(MainActivity6 mainActivity6, int i) {
        int i2 = mainActivity6.lr + i;
        mainActivity6.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$420(MainActivity6 mainActivity6, int i) {
        int i2 = mainActivity6.lr - i;
        mainActivity6.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$512(MainActivity6 mainActivity6, int i) {
        int i2 = mainActivity6.ud + i;
        mainActivity6.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$520(MainActivity6 mainActivity6, int i) {
        int i2 = mainActivity6.ud - i;
        mainActivity6.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$6208(MainActivity6 mainActivity6) {
        int i = mainActivity6.connNum;
        mainActivity6.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$6808(MainActivity6 mainActivity6) {
        int i = mainActivity6.isFaultOn;
        mainActivity6.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main6);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivity6$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity6.lambda$onCreate$0(view, windowInsetsCompat);
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
        this.delepoints2 = (TextView) findViewById(R.id.delepoints2);
        this.lastpoints2 = (TextView) findViewById(R.id.lastpoints2);
        this.savepoints2 = (TextView) findViewById(R.id.savepoints2);
        this.vo_tv = (TextView) findViewById(R.id.vo_tv);
        this.power_lay = (RelativeLayout) findViewById(R.id.power_lay);
        this.group_cate = (RelativeLayout) findViewById(R.id.group_cate);
        this.change_velo_layout2 = (RelativeLayout) findViewById(R.id.change_velo_layout2);
        this.control_all = (RelativeLayout) findViewById(R.id.control_all);
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
        this.dian_num = (EditText) findViewById(R.id.dian_num);
        this.l_r = (EditText) findViewById(R.id.l_r);
        this.u_d = (EditText) findViewById(R.id.u_d);
        this.s_d = (EditText) findViewById(R.id.s_d);
        this.interval = (EditText) findViewById(R.id.interval);
        this.u_dian = (Button) findViewById(R.id.u_dian);
        Button button = (Button) findViewById(R.id.change_tennis);
        this.change_tennis = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                java.util.List<com.clj.fastble.data.BleDevice> connected = com.clj.fastble.BleManager.getInstance().getAllConnectedDevice();
                final com.clj.fastble.data.BleDevice currentDevice = (connected != null && !connected.isEmpty()) ? connected.get(0) : bleDevice;
                com.clj.fastble.BleManager.getInstance().disconnectAllDevice();
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity6, mainActivity6.getResources().getString(R.string.changing));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ShowHelper.dismissProgressDialog();
                        Intent intent = new Intent(MainActivity6.this, (Class<?>) MainActivityPad1.class);
                        intent.putExtra("device", currentDevice);
                        intent.putExtra("device_name", com.pusun.pusuntennis.utils.Util.getDeviceName(currentDevice));
                        MainActivity6.this.startActivity(intent);
                    }
                }, 1500L);
            }
        });
        Button button2 = (Button) findViewById(R.id.savedefault);
        this.savedefault = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivity6.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivity6.this.freq.getProgress());
                    defaultDao.setGrade(MainActivity6.this.valueSelect);
                    defaultDao.setItem2(MainActivity6.this.lr);
                    defaultDao.setItem3(MainActivity6.this.ud);
                    defaultDao.setVelo(MainActivity6.this.velobar.getProgress());
                    defaultDao.setRote(MainActivity6.this.rotatebar.getProgress());
                    if (MainActivity6.this.modeCate == 0) {
                        if (MainActivity6.this.modeNum != 1) {
                            if (MainActivity6.this.modeNum != 7) {
                                if (MainActivity6.this.modeNum != 5) {
                                    if (MainActivity6.this.modeNum != 4) {
                                        if (MainActivity6.this.modeNum != 2) {
                                            if (MainActivity6.this.modeNum != 3) {
                                                if (MainActivity6.this.modeNum != 6) {
                                                    if (MainActivity6.this.modeNum != 8) {
                                                        if (MainActivity6.this.modeNum == 9) {
                                                            defaultDao.setDaoName("moon");
                                                            defaultDao.setNumber(24L);
                                                            MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                                        }
                                                    } else {
                                                        defaultDao.setDaoName("place");
                                                        defaultDao.setNumber(23L);
                                                        MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                                    }
                                                } else {
                                                    defaultDao.setDaoName("cross" + MainActivity6.this.tagC);
                                                    if (MainActivity6.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivity6.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivity6.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivity6.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivity6.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivity6.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivity6.this.tagV);
                                                if (MainActivity6.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivity6.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivity6.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivity6.this.tagH);
                                            if (MainActivity6.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivity6.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivity6.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivity6.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivity6.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivity6.this.tagHT);
                                if (MainActivity6.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivity6.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivity6.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivity6.this.tagFix);
                            if (MainActivity6.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivity6.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivity6.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivity6.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.save_default_success));
                    MainActivity6.this.savedefault.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivity6.this.saveColor = 0;
                    return;
                }
                MainActivity6 mainActivity62 = MainActivity6.this;
                ShowHelper.toastShort(mainActivity62, mainActivity62.getResources().getString(R.string.no_change_default));
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
        this.frontde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] > 2190) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] = 2190;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[1] = (short) (sArr[1] + 30);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] > 1830) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] = 1830;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.frontadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] = (short) (BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] - 30);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] < 1260) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][0] = 1260;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] = (short) (BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] - 30);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] < 1110) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][1] = 1110;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] - 1);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][2] < 0) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][2] = 0;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] + 1);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][2] > 20) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][2] = 20;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][3] < 25) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][3] = 25;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData38.b3[MainActivity6.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData38.b3[MainActivity6.this.vari_point_num + 19][3] > 65) {
                    BasicData38.b3[MainActivity6.this.vari_point_num + 19][3] = 65;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
                int i = MainActivity6.this.vari_point_num + 19;
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) (MainActivity6.this.vari_point_num + 20), (byte) (BasicData38.b3[i][0] >> 8), (byte) BasicData38.b3[i][0], (byte) (BasicData38.b3[i][1] >> 8), (byte) BasicData38.b3[i][1], (byte) (BasicData38.b3[i][2] >> 8), (byte) BasicData38.b3[i][2], (byte) BasicData38.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.u_dian.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6.this.dian_num.getText().toString().trim()).intValue();
                int intValue2 = Integer.valueOf(MainActivity6.this.l_r.getText().toString().trim()).intValue();
                int intValue3 = Integer.valueOf(MainActivity6.this.u_d.getText().toString().trim()).intValue();
                int intValue4 = Integer.valueOf(MainActivity6.this.s_d.getText().toString().trim()).intValue();
                MainActivity6.this.writeBleData(new byte[]{-86, (byte) intValue, (byte) (intValue2 >> 8), (byte) intValue2, (byte) (intValue3 >> 8), (byte) intValue3, (byte) (intValue4 >> 8), (byte) intValue4, (byte) Integer.valueOf(MainActivity6.this.interval.getText().toString().trim()).intValue(), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.start_layout = (LinearLayout) findViewById(R.id.start_layout);
        this.bg_input_big = findViewById(R.id.bg_input_big);
        this.note_ud = (TextView) findViewById(R.id.note_ud);
        this.note_lr = (TextView) findViewById(R.id.note_lr);
        this.num_ud = (TextView) findViewById(R.id.num_ud);
        this.num_lr = (TextView) findViewById(R.id.num_lr);
        this.lr = BasicData38.a13[0];
        this.ud = BasicData38.a13[1];
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
        this.tvids15 = new int[]{14, 16, 17, 18, 20};
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
        this.pos = new TextView[]{this.num1, this.num2, this.num3, this.num4, this.num5, this.num6, this.num7, this.num8, this.num9, this.num10, this.num11, this.num12, this.num13, this.num14, this.num15, this.num16, this.num17, this.num18, this.num19, this.num20, this.num21, this.num22, this.num23, this.num24, this.num25, this.num26};
        this.poids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
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
        SharedPreferences sharedPreferences = getSharedPreferences(FORBID_INFO, 0);
        AppLog.e("" + sharedPreferences.getInt(FORBID_INFO, 0));
        this.forbid = sharedPreferences.getInt(FORBID_INFO, 0);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.12
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6.this.getUrlTxt();
            }
        }, 1000L);
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity6.13
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinner_zu = (Spinner) findViewById(R.id.spinner_zu);
        this.spinner_zu.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.point_vari)));
        this.spinner_zu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity6.14
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    MainActivity6.this.vari_point_num = 1;
                } else if (i == 1) {
                    MainActivity6.this.vari_point_num = 2;
                } else if (i == 2) {
                    MainActivity6.this.vari_point_num = 3;
                } else if (i == 3) {
                    MainActivity6.this.vari_point_num = 4;
                } else if (i == 4) {
                    MainActivity6.this.vari_point_num = 5;
                } else if (i == 5) {
                    MainActivity6.this.vari_point_num = 6;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.showSelectPoint(mainActivity6.vari_point_num);
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
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6.15
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("up:1");
                    if (MainActivity6.this.ud >= 1410) {
                        MainActivity6.access$512(MainActivity6.this, 30);
                    }
                    if (MainActivity6.this.ud < 1410) {
                        MainActivity6.access$512(MainActivity6.this, 50);
                    }
                    if (MainActivity6.this.ud < 910) {
                        MainActivity6.this.ud = 910;
                    }
                    if (MainActivity6.this.ud > 1830) {
                        MainActivity6.this.ud = 1830;
                    }
                    short s = (short) MainActivity6.this.lr;
                    if (MainActivity6.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6.this.ud;
                    if (MainActivity6.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                    if (MainActivity6.this.ud >= 1410) {
                        MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                    } else {
                        MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.15.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.15.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivity6.this.isTouch = false;
                    if (MainActivity6.this.timeCount1 != null) {
                        MainActivity6.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.15.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6.this.timeCount1 != null) {
                                MainActivity6.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6.16
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("down:1");
                    if (MainActivity6.this.ud <= 1410) {
                        MainActivity6.access$520(MainActivity6.this, 50);
                    }
                    if (MainActivity6.this.ud > 1410) {
                        MainActivity6.access$520(MainActivity6.this, 30);
                    }
                    if (MainActivity6.this.ud < 910) {
                        MainActivity6.this.ud = 910;
                    }
                    if (MainActivity6.this.ud > 1830) {
                        MainActivity6.this.ud = 1830;
                    }
                    short s = (short) MainActivity6.this.lr;
                    if (MainActivity6.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6.this.ud;
                    if (MainActivity6.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                    if (MainActivity6.this.ud > 1410) {
                        MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                    } else {
                        MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.16.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6.this.isTouch = false;
                    if (MainActivity6.this.timeCount2 != null) {
                        MainActivity6.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.16.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6.this.timeCount2 != null) {
                                MainActivity6.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6.17
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("left:1");
                    MainActivity6.access$412(MainActivity6.this, 30);
                    if (MainActivity6.this.lr < 1260) {
                        MainActivity6.this.lr = 1260;
                    }
                    if (MainActivity6.this.lr > 2190) {
                        MainActivity6.this.lr = 2190;
                    }
                    short s = (short) MainActivity6.this.lr;
                    if (MainActivity6.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6.this.ud;
                    if (MainActivity6.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                    if (MainActivity6.this.ud > 1410) {
                        MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                    } else {
                        MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.17.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6.this.isTouch = false;
                    if (MainActivity6.this.timeCount3 != null) {
                        MainActivity6.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.17.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6.this.timeCount3 != null) {
                                MainActivity6.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("right:1");
                    MainActivity6.access$420(MainActivity6.this, 30);
                    if (MainActivity6.this.lr < 1260) {
                        MainActivity6.this.lr = 1260;
                    }
                    if (MainActivity6.this.lr > 2190) {
                        MainActivity6.this.lr = 2190;
                    }
                    short s = (short) MainActivity6.this.lr;
                    if (MainActivity6.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6.this.ud;
                    if (MainActivity6.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                    if (MainActivity6.this.ud > 1410) {
                        MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                    } else {
                        MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.18.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.18.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6.this.isTouch = false;
                    if (MainActivity6.this.timeCount4 != null) {
                        MainActivity6.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.18.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6.this.timeCount4 != null) {
                                MainActivity6.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6.21
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivity6.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.21.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivity6.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivity6.this.f_tv.setText("" + i);
                    MainActivity6.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.22.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    MainActivity6.this.f_tv.setText("" + i);
                    MainActivity6.this.freq.setProgress((float) i);
                    MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[intValue], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[intValue] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6.24
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                int progressVal = indicatorSeekBar3.getProgress();

                if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 8) {

                    indicatorSeekBar3.setProgress(0.0f);

                    progressVal = 0;

                }

                final int progress = progressVal;
                if (progress != 0 && MainActivity6.this.velobar.getProgress() < 5) {
                    MainActivity6.this.velobar.setProgress(5.0f);
                    MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[4] ^ 99), -91});
                    MainActivity6.this.v_tv.setText("" + MainActivity6.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.24.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.24.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.24.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity6.this.r_tv.setText("" + progress);
                MainActivity6.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity6.this.rotatebar.getProgress();
                if (progress > -6) {
                    int iVal = progress - 1;

                    if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 8) {

                        MainActivity6.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivity6.this.velobar.getProgress() < 5) {
                        MainActivity6.this.velobar.setProgress(5.0f);
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[4] ^ 99), -91});
                        MainActivity6.this.v_tv.setText("" + MainActivity6.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.25.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.25.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.25.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity6.this.r_tv.setText("" + i);
                    MainActivity6.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity6.this.rotatebar.getProgress();
                if (progress < 6) {
                    int iVal = progress + 1;

                    if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 8) {

                        MainActivity6.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivity6.this.velobar.getProgress() < 5) {
                        MainActivity6.this.velobar.setProgress(5.0f);
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[4] ^ 99), -91});
                        MainActivity6.this.v_tv.setText("" + MainActivity6.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.26.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.26.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity6.this.r_tv.setText("" + i);
                    MainActivity6.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6.27
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                if (MainActivity6.this.forbid == 1) {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.showAlertDialog(mainActivity6, mainActivity6.getResources().getString(R.string.alert), MainActivity6.this.getResources().getString(R.string.forbid_alert));
                    return;
                }
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivity6.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivity6.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivity6.this.rotatebar.getProgress() != 0) {
                    MainActivity6.this.r_tv.setText("0");
                    MainActivity6.this.rotatebar.setProgress(0.0f);
                    MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.27.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivity6.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.forbid != 1) {
                    int progress = MainActivity6.this.velobar.getProgress();
                    if (progress > 1) {
                        int i = progress - 1;
                        if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 5 && i > 6) {
                            MainActivity6.this.velobar.setProgress(6.0f);
                            i = 6;
                        }
                        TextView textView12 = MainActivity6.this.v_tv;
                        StringBuilder sb = new StringBuilder("");
                        int i2 = i - 1;
                        sb.append(MainActivity6.this.veloTins[i2]);
                        textView12.setText(sb.toString());
                        MainActivity6.this.velobar.setProgress(i);
                        if (i < 5 && MainActivity6.this.rotatebar.getProgress() != 0) {
                            MainActivity6.this.r_tv.setText("0");
                            MainActivity6.this.rotatebar.setProgress(0.0f);
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[i2] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.28.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivity6.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showAlertDialog(mainActivity6, mainActivity6.getResources().getString(R.string.alert), MainActivity6.this.getResources().getString(R.string.forbid_alert));
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.forbid != 1) {
                    int progress = MainActivity6.this.velobar.getProgress();
                    if (progress < 13) {
                        int i = progress + 1;
                        if (MainActivity6.this.modeCate == 0 && MainActivity6.this.modeNum == 5 && i > 6) {
                            MainActivity6.this.velobar.setProgress(6.0f);
                            i = 6;
                        }
                        TextView textView12 = MainActivity6.this.v_tv;
                        StringBuilder sb = new StringBuilder("");
                        int i2 = i - 1;
                        sb.append(MainActivity6.this.veloTins[i2]);
                        textView12.setText(sb.toString());
                        MainActivity6.this.velobar.setProgress(i);
                        if (i < 5 && MainActivity6.this.rotatebar.getProgress() != 0) {
                            MainActivity6.this.r_tv.setText("0");
                            MainActivity6.this.rotatebar.setProgress(0.0f);
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[i2] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.29.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivity6.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showAlertDialog(mainActivity6, mainActivity6.getResources().getString(R.string.alert), MainActivity6.this.getResources().getString(R.string.forbid_alert));
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
        this.select_dianwei = (TextView) findViewById(R.id.select_dianwei);
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6.30
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity6.this.self.setVisibility(8);
                    MainActivity6.this.group.setVisibility(0);
                    MainActivity6.this.points.setVisibility(0);
                    MainActivity6.this.hintpoints.setVisibility(0);
                    MainActivity6.this.tennipic2.setVisibility(8);
                    MainActivity6.this.tennipic3.setVisibility(8);
                    MainActivity6.this.tennipic4.setVisibility(8);
                    MainActivity6.this.tennipic5.setVisibility(8);
                    MainActivity6.this.fourbtn.setVisibility(8);
                    MainActivity6.this.bg_four.setVisibility(8);
                    MainActivity6.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivity6.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivity6.this.density * 36.0f);
                    MainActivity6.this.start_layout.setLayoutParams(layoutParams);
                    MainActivity6.this.start_layout.setGravity(17);
                    MainActivity6.this.modeCate = 1;
                    return;
                }
                MainActivity6.this.self.setVisibility(0);
                MainActivity6.this.group.setVisibility(8);
                MainActivity6.this.points.setVisibility(8);
                MainActivity6.this.hintpoints.setVisibility(8);
                MainActivity6.this.tennipic2.setVisibility(0);
                MainActivity6.this.bg_input.setVisibility(8);
                MainActivity6.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivity6.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivity6.this.density * 90.0f);
                MainActivity6.this.start_layout.setLayoutParams(layoutParams2);
                MainActivity6.this.start_layout.setGravity(83);
                MainActivity6.this.fourbtn.setVisibility(0);
                MainActivity6.this.bg_four.setVisibility(0);
                if (MainActivity6.this.modeNum == 2) {
                    MainActivity6.this.tennipic3.setVisibility(0);
                    MainActivity6.this.tennipic4.setVisibility(8);
                    MainActivity6.this.tennipic5.setVisibility(8);
                }
                if (MainActivity6.this.modeNum == 3) {
                    MainActivity6.this.tennipic3.setVisibility(8);
                    MainActivity6.this.tennipic4.setVisibility(0);
                    MainActivity6.this.tennipic5.setVisibility(8);
                }
                if (MainActivity6.this.modeNum == 4) {
                    MainActivity6.this.tennipic3.setVisibility(8);
                    MainActivity6.this.tennipic4.setVisibility(8);
                    MainActivity6.this.tennipic5.setVisibility(0);
                }
                MainActivity6.this.modeCate = 0;
            }
        });
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.blenoty.getText().toString().trim().contains(MainActivity6.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    if (MainActivity6.bleDevice != null) {
                        MainActivity6.this.connect(MainActivity6.bleDevice);
                    } else {
                        MainActivity6.this.checkPermissions();
                    }
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.disconnected));
                    MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6.32
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivity6.this.radioNum = 0;
                        MainActivity6.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivity6.this.radioNum = 1;
                        MainActivity6.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivity6.this.radioNum = 2;
                        MainActivity6.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity6.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity6.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity6.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity6.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity6.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6.33.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity6.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity6.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity6.this, MainActivity6.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_route_name));
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity6.this.daoSession.loadAll(VariDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity6.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity6.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity6.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity6.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6.34.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity6.this.daoSession.loadAll(VariDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((VariDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity6.this.daoSession.delete((VariDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity6.this, MainActivity6.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.select_dianwei.getText() != null && !MainActivity6.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity6.this.alert_dialog_input();
                } else {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6.this.select_dianwei.getText() != null && !MainActivity6.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity6.this.alert_dialog_input2();
                } else {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass37());
        this.lastpoints2.setOnClickListener(new AnonymousClass38());
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.valueSelect = Integer.parseInt(mainActivity6.value_h.getText().toString().trim());
                MainActivity6.access$308(MainActivity6.this);
                if (MainActivity6.this.valueSelect > 25) {
                    MainActivity6.this.valueSelect = 25;
                    return;
                }
                MainActivity6 mainActivity62 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity62, mainActivity62.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.39.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.39.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity6.this.value_h.setText("" + MainActivity6.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.39.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.39.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity6.this.checkIfUpdate();
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.valueSelect = Integer.parseInt(mainActivity6.value_h.getText().toString().trim());
                MainActivity6.access$310(MainActivity6.this);
                if (MainActivity6.this.valueSelect < 1) {
                    MainActivity6.this.valueSelect = 1;
                    return;
                }
                MainActivity6 mainActivity62 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity62, mainActivity62.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.40.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.40.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity6.this.value_h.setText("" + MainActivity6.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.40.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.40.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity6.this.checkIfUpdate();
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

    /* renamed from: com.pusun.pusuntennis.MainActivity6$37, reason: invalid class name */
    class AnonymousClass37 implements View.OnClickListener {
        AnonymousClass37() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity6.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity6.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6.37.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivity6.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivity6.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivity6.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivity6.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivity6.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivity6.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivity6.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity6.this.freq.getProgress() - 1;
                                        MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivity6.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivity6.this.v_tv.setText("" + MainActivity6.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity6.this.velobar.getProgress() - 1;
                                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivity6.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivity6.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivity6.this.valueSelect = item2;
                                    MainActivity6.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.37.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6.this.sendBaseData(1);
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
            MainActivity6 mainActivity6 = MainActivity6.this;
            ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_route_name));
        }
    }

    /* renamed from: com.pusun.pusuntennis.MainActivity6$38, reason: invalid class name */
    class AnonymousClass38 implements View.OnClickListener {
        AnonymousClass38() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity6.this.daoSession.loadAll(VariDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity6.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new AnonymousClass1());
                optionPicker.show();
                return;
            }
            MainActivity6 mainActivity6 = MainActivity6.this;
            ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.no_route_name));
        }

        /* renamed from: com.pusun.pusuntennis.MainActivity6$38$1, reason: invalid class name */
        class AnonymousClass1 implements OptionPicker.OnOptionPickListener {
            AnonymousClass1() {
            }

            @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
            public void onOptionPicked(String str) {
                new ArrayList();
                List loadAll = MainActivity6.this.daoSession.loadAll(VariDao.class);
                for (int i = 0; i < loadAll.size(); i++) {
                    if (((VariDao) loadAll.get(i)).getDaoName().equals(str)) {
                        MainActivity6.this.select_dianwei.setText(((VariDao) loadAll.get(i)).getSeles());
                        String[] split = ((VariDao) loadAll.get(i)).getSeles().split(",");
                        MainActivity6.this.selectPoints.clear();
                        for (String str2 : split) {
                            MainActivity6.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                        MainActivity6.this.showPoints();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.38.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        BasicData38.b3[20][0] = (short) ((VariDao) loadAll.get(i)).getLr1();
                        BasicData38.b3[20][1] = (short) ((VariDao) loadAll.get(i)).getUd1();
                        BasicData38.b3[20][2] = (short) ((VariDao) loadAll.get(i)).getVelo1();
                        BasicData38.b3[20][3] = (short) ((VariDao) loadAll.get(i)).getFreq1();
                        BasicData38.b3[21][0] = (short) ((VariDao) loadAll.get(i)).getLr2();
                        BasicData38.b3[21][1] = (short) ((VariDao) loadAll.get(i)).getUd2();
                        BasicData38.b3[21][2] = (short) ((VariDao) loadAll.get(i)).getVelo2();
                        BasicData38.b3[21][3] = (short) ((VariDao) loadAll.get(i)).getFreq2();
                        BasicData38.b3[22][0] = (short) ((VariDao) loadAll.get(i)).getLr3();
                        BasicData38.b3[22][1] = (short) ((VariDao) loadAll.get(i)).getUd3();
                        BasicData38.b3[22][2] = (short) ((VariDao) loadAll.get(i)).getVelo3();
                        BasicData38.b3[22][3] = (short) ((VariDao) loadAll.get(i)).getFreq3();
                        BasicData38.b3[23][0] = (short) ((VariDao) loadAll.get(i)).getLr4();
                        BasicData38.b3[23][1] = (short) ((VariDao) loadAll.get(i)).getUd4();
                        BasicData38.b3[23][2] = (short) ((VariDao) loadAll.get(i)).getVelo4();
                        BasicData38.b3[23][3] = (short) ((VariDao) loadAll.get(i)).getFreq4();
                        BasicData38.b3[24][0] = (short) ((VariDao) loadAll.get(i)).getLr5();
                        BasicData38.b3[24][1] = (short) ((VariDao) loadAll.get(i)).getUd5();
                        BasicData38.b3[24][2] = (short) ((VariDao) loadAll.get(i)).getVelo5();
                        BasicData38.b3[24][3] = (short) ((VariDao) loadAll.get(i)).getFreq5();
                        BasicData38.b3[25][0] = (short) ((VariDao) loadAll.get(i)).getLr6();
                        BasicData38.b3[25][1] = (short) ((VariDao) loadAll.get(i)).getUd6();
                        BasicData38.b3[25][2] = (short) ((VariDao) loadAll.get(i)).getVelo6();
                        BasicData38.b3[25][3] = (short) ((VariDao) loadAll.get(i)).getFreq6();
                        MainActivity6.this.showSelectPoint(MainActivity6.this.vari_point_num);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.38.1.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int length = BasicData38.b3.length - 6;
        while (length < BasicData38.b3.length) {
            final int final_length = length;
                                    int i2 = length + 1;
                                    short s = BasicData38.b3[length][0];
                                    short s2 = BasicData38.b3[length][0];
                                    short s3 = BasicData38.b3[length][1];
                                    short s4 = BasicData38.b3[length][1];
                                    final byte[] bArr = {-86, (byte) i2, (byte) (BasicData38.b3[length][0] >> 8), (byte) BasicData38.b3[length][0], (byte) (BasicData38.b3[length][1] >> 8), (byte) BasicData38.b3[length][1], (byte) (BasicData38.b3[length][2] >> 8), (byte) BasicData38.b3[length][2], (byte) BasicData38.b3[length][3], -91};
                                    AppLog.e("左右：" + ((int) BasicData38.b3[length][0]) + "上下：" + ((int) BasicData38.b3[length][1]) + "byte:" + MainActivity6.bytesToHexString(bArr));
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.38.1.2.1
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            AppLog.e("第" + final_length + "条指令");
                                            MainActivity6.this.writeBleData(bArr);
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
        int i2 = i + 19;
        sb.append(74 - (BasicData38.b3[i2][0] / 30));
        textView.setText(sb.toString());
        this.backvalue2.setText("" + (64 - (BasicData38.b3[i2][1] / 30)));
        TextView textView2 = this.front_m_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData38.b3[i2][2]);
        textView2.setText(sb2.toString());
        TextView textView3 = this.back_m_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData38.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUrlTxt() {
        new Thread(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.41
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
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.41.1
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    String[] split = sb.toString().split(";");
                                    int i = 0;
                                    while (true) {
                                        if (i < split.length) {
                                            if (com.pusun.pusuntennis.utils.Util.getDeviceName(MainActivity6.bleDevice).equals(split[i].toString().trim())) {
                                                ShowHelper.showAlertDialog(MainActivity6.this, MainActivity6.this.getResources().getString(R.string.alert), MainActivity6.this.getResources().getString(R.string.forbid_alert));
                                                SharedPreferences.Editor edit = MainActivity6.this.getSharedPreferences(MainActivity6.FORBID_INFO, 0).edit();
                                                edit.putInt(MainActivity6.FORBID_INFO, 1);
                                                edit.commit();
                                                SharedPreferences sharedPreferences = MainActivity6.this.getSharedPreferences(MainActivity6.FORBID_INFO, 0);
                                                AppLog.e("" + sharedPreferences.getInt(MainActivity6.FORBID_INFO, 0));
                                                MainActivity6.this.forbid = sharedPreferences.getInt(MainActivity6.FORBID_INFO, 0);
                                                break;
                                            }
                                            i++;
                                        } else {
                                            SharedPreferences.Editor edit2 = MainActivity6.this.getSharedPreferences(MainActivity6.FORBID_INFO, 0).edit();
                                            edit2.putInt(MainActivity6.FORBID_INFO, 0);
                                            edit2.commit();
                                            SharedPreferences sharedPreferences2 = MainActivity6.this.getSharedPreferences(MainActivity6.FORBID_INFO, 0);
                                            AppLog.e("" + sharedPreferences2.getInt(MainActivity6.FORBID_INFO, 0));
                                            MainActivity6.this.forbid = sharedPreferences2.getInt(MainActivity6.FORBID_INFO, 0);
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
                    if (this.selectPoints.get(i6).intValue() + 20 == this.poids[i7] + 1) {
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
        while (i < BasicData38.b2.length) {
            final int final_i = i;
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData38.b2[i][0] >> 8), (byte) BasicData38.b2[i][0], (byte) (BasicData38.b2[i][1] >> 8), (byte) BasicData38.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData38.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData38.b2[i][0])) ^ ((byte) (BasicData38.b2[i][1] >> 8))) ^ ((byte) BasicData38.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData38.b2[i][0]) + "上下：" + ((int) BasicData38.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.42
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity6.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.43
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i3 = 10;
        while (i3 < BasicData38.b2.length) {
            final int final_i3 = i3;
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData38.b2[i3][0] >> 8), (byte) BasicData38.b2[i3][0], (byte) (BasicData38.b2[i3][1] >> 8), (byte) BasicData38.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData38.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData38.b2[i3][0])) ^ ((byte) (BasicData38.b2[i3][1] >> 8))) ^ ((byte) BasicData38.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData38.b2[i3][0]) + "上下：" + ((int) BasicData38.b2[i3][1]) + "byte:" + MainActivity6.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.43.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i3 + "条指令");
                            MainActivity6.this.writeBleData(bArr2);
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
        int i2 = 6;
        char c2 = 2;
        char c3 = 1;
        if (i == 1) {
            int i3 = (this.valueSelect - this.baseHt) * 30;
            int i4 = 0;
        while (i4 < BasicData38.b3.length - i2) {
            final int final_i4 = i4;
                int i5 = i4 + 1;
                short s = BasicData38.b3[i4][0];
                short s2 = BasicData38.b3[i4][0];
                short s3 = BasicData38.b3[i4][c3];
                short s4 = BasicData38.b3[i4][c3];
                final byte[] bArr = {-86, (byte) i5, (byte) (BasicData38.b3[i4][0] >> 8), (byte) BasicData38.b3[i4][0], (byte) ((BasicData38.b3[i4][c3] - i3) >> 8), (byte) (BasicData38.b3[i4][c3] - i3), (byte) (BasicData38.b3[i4][c2] >> 8), (byte) BasicData38.b3[i4][c2], (byte) BasicData38.b3[i4][c], -91};
                AppLog.e("左右：" + ((int) BasicData38.b3[i4][0]) + "上下：" + (BasicData38.b3[i4][1] - i3) + "byte:" + bytesToHexString(bArr));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.44
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + final_i4 + "条指令");
                        MainActivity6.this.writeBleData(bArr);
                    }
                }, (long) (i4 * 10));
                i4 = i5;
                c3 = 1;
                c = 3;
                i2 = 6;
                c2 = 2;
            }
                final int final_i3 = i3;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.45
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int i6 = 10;
        while (i6 < BasicData38.b3.length - 6) {
            final int final_i6 = i6;
                        int i7 = i6 + 1;
                        short s5 = BasicData38.b3[i6][0];
                        short s6 = BasicData38.b3[i6][0];
                        short s7 = BasicData38.b3[i6][1];
                        short s8 = BasicData38.b3[i6][1];
                        final byte[] bArr2 = {-86, (byte) i7, (byte) (BasicData38.b3[i6][0] >> 8), (byte) BasicData38.b3[i6][0], (byte) ((BasicData38.b3[i6][1] - final_i3) >> 8), (byte) (BasicData38.b3[i6][1] - final_i3), (byte) (BasicData38.b3[i6][2] >> 8), (byte) BasicData38.b3[i6][2], (byte) BasicData38.b3[i6][3], -91};
                        AppLog.e("左右：" + ((int) BasicData38.b3[i6][0]) + "上下：" + (BasicData38.b3[i6][1] - final_i3) + "byte:" + MainActivity6.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.45.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_i6 + "条指令");
                                MainActivity6.this.writeBleData(bArr2);
                            }
                        }, (long) (i6 * 10));
                        i6 = i7;
                    }
                }
            }, 900L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.46
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int length = BasicData38.b3.length - 6;
        while (length < BasicData38.b3.length) {
            final int final_length = length;
                        int i6 = length + 1;
                        short s5 = BasicData38.b3[length][0];
                        short s6 = BasicData38.b3[length][0];
                        short s7 = BasicData38.b3[length][1];
                        short s8 = BasicData38.b3[length][1];
                        final byte[] bArr2 = {-86, (byte) i6, (byte) (BasicData38.b3[length][0] >> 8), (byte) BasicData38.b3[length][0], (byte) (BasicData38.b3[length][1] >> 8), (byte) BasicData38.b3[length][1], (byte) (BasicData38.b3[length][2] >> 8), (byte) BasicData38.b3[length][2], (byte) BasicData38.b3[length][3], -91};
                        AppLog.e("左右：" + ((int) BasicData38.b3[length][0]) + "上下：" + ((int) BasicData38.b3[length][1]) + "byte:" + MainActivity6.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.46.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_length + "条指令");
                                MainActivity6.this.writeBleData(bArr2);
                            }
                        }, (long) (length * 10));
                        length = i6;
                    }
                }
            }, 1200L);
            return;
        }
        int i6 = 0;
        while (i6 < BasicData38.b4.length) {
            final int final_i6 = i6;
            int i7 = i6 + 1;
            byte b = (byte) i7;
            final byte[] bArr2 = {-86, b, (byte) (BasicData38.b4[i6][0] >> 8), (byte) BasicData38.b4[i6][0], (byte) (BasicData38.b4[i6][1] >> 8), (byte) BasicData38.b4[i6][1], 0, 0, (byte) ((((((byte) (BasicData38.b4[i6][0] >> 8)) ^ b) ^ ((byte) BasicData38.b4[i6][0])) ^ ((byte) (BasicData38.b4[i6][1] >> 8))) ^ ((byte) BasicData38.b4[i6][1])), -91};
            AppLog.e("左右：" + ((int) BasicData38.b4[i6][0]) + "上下：" + ((int) BasicData38.b4[i6][1]) + "byte:" + bytesToHexString(bArr2));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.47
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i6 + "条指令");
                    MainActivity6.this.writeBleData(bArr2);
                }
            }, (long) (i6 * 10));
            i6 = i7;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.48
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i8 = 10;
        while (i8 < BasicData38.b4.length) {
            final int final_i8 = i8;
                    int i9 = i8 + 1;
                    byte b2 = (byte) i9;
                    final byte[] bArr3 = {-86, b2, (byte) (BasicData38.b4[i8][0] >> 8), (byte) BasicData38.b4[i8][0], (byte) (BasicData38.b4[i8][1] >> 8), (byte) BasicData38.b4[i8][1], 0, 0, (byte) ((((((byte) (BasicData38.b4[i8][0] >> 8)) ^ b2) ^ ((byte) BasicData38.b4[i8][0])) ^ ((byte) (BasicData38.b4[i8][1] >> 8))) ^ ((byte) BasicData38.b4[i8][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData38.b4[i8][0]) + "上下：" + ((int) BasicData38.b4[i8][1]) + "byte:" + MainActivity6.bytesToHexString(bArr3));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.48.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i8 + "条指令");
                            MainActivity6.this.writeBleData(bArr3);
                        }
                    }, (long) (i8 * 10));
                    i8 = i9;
                }
            }
        }, 900L);
    }

    private void sendBaseDataMore() {
        for (int i = 0; i < BasicData38.b5.length; i++) {
            final int final_i = i;
            byte b = (byte) (i + 19);
            final byte[] bArr = {-86, b, (byte) (BasicData38.b5[i][0] >> 8), (byte) BasicData38.b5[i][0], (byte) (BasicData38.b5[i][1] >> 8), (byte) BasicData38.b5[i][1], 0, 0, (byte) ((((((byte) (BasicData38.b5[i][0] >> 8)) ^ b) ^ ((byte) BasicData38.b5[i][0])) ^ ((byte) (BasicData38.b5[i][1] >> 8))) ^ ((byte) BasicData38.b5[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData38.b5[i][0]) + "上下：" + ((int) BasicData38.b5[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.49
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity6.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.50
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i2 = 0;
        while (i2 < BasicData38.b2.length) {
            final int final_i2 = i2;
                    int i3 = i2 + 1;
                    byte b2 = (byte) i3;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData38.b2[i2][0] >> 8), (byte) BasicData38.b2[i2][0], (byte) (BasicData38.b2[i2][1] >> 8), (byte) BasicData38.b2[i2][1], 0, 0, (byte) ((((((byte) (BasicData38.b2[i2][0] >> 8)) ^ b2) ^ ((byte) BasicData38.b2[i2][0])) ^ ((byte) (BasicData38.b2[i2][1] >> 8))) ^ ((byte) BasicData38.b2[i2][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData38.b2[i2][0]) + "上下：" + ((int) BasicData38.b2[i2][1]) + "byte:" + MainActivity6.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.50.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i2 + "条指令");
                            MainActivity6.this.writeBleData(bArr2);
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
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity6.51
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PT4") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PR1")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity6.52
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivity6.53
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity6, mainActivity6.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity6, mainActivity6.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastLong(mainActivity6, mainActivity6.getResources().getString(R.string.no_device_found));
                    if (MainActivity6.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.53.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6.this.startScan();
                            }
                        }, 1000L);
                        MainActivity6.access$6208(MainActivity6.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PT")) {
                    MainActivity6.this.connect(list.get(0));
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
                    MainActivity6 mainActivity62 = MainActivity6.this;
                    ShowHelper.toastLong(mainActivity62, mainActivity62.getResources().getString(R.string.no_device_found));
                    if (MainActivity6.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.53.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6.this.startScan();
                            }
                        }, 1000L);
                        MainActivity6.access$6208(MainActivity6.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivity6.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivity6.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6.53.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivity6.this.connect((BleDevice) arrayList.get(i3));
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
                        MainActivity6 mainActivity = MainActivity6.this;
                        ShowHelper.showProgressDialog(mainActivity, mainActivity.getResources().getString(R.string.connecting_device));
                    }
                    @Override
                    public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                        MainActivity6 mainActivity = MainActivity6.this;
                        ShowHelper.toastLong(mainActivity, mainActivity.getResources().getString(R.string.connect_failure_check));
                        ShowHelper.dismissProgressDialog();
                        MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.disconnected));
                        MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity6.this.signal_note.setText(MainActivity6.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                    }
                    @Override
                    public void onConnectSuccess(BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        ShowHelper.setProgressDialogMessage(MainActivity6.this.getResources().getString(R.string.initializing));
                        MainActivity6.this.connNum = 0;
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                                ShowHelper.toastShort(MainActivity6.this, MainActivity6.this.getResources().getString(R.string.please_use));
                            }
                        }, com.google.android.exoplayer2.C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        String resolvedName = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                        if (resolvedName == null || resolvedName.isEmpty()) resolvedName = MainActivity6.this.deviceName != null ? MainActivity6.this.deviceName : "";
                        MainActivity6.this.nameStar = resolvedName;
                        MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.connected));
                        MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_selector));
                        MainActivity6.this.signal_note.setText(MainActivity6.this.nameStar + MainActivity6.this.getResources().getString(R.string.connected));
                        MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_green));
                        MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_blue));
                        MainActivity6.this.isFaultOn = 0;
                        MainActivity6.this.gatt = bluetoothGatt;
                        MainActivity6.bleDevice = bleDevice3;
                        if (bleDevice3.getMac() != null) MainActivity6.this.deviceMac = bleDevice3.getMac();
                        MainActivity6.this.startNotify();
                    }
                    @Override
                    public void onDisConnected(boolean z, final BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.disconnected));
                        MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity6.this.signal_note.setText(MainActivity6.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                        MainActivity6.this.isFaultOn = 0;
                        if (z || MainActivity6.this.connNum >= 3) { return; }
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public void run() { MainActivity6.this.connect(bleDevice3); }
                        }, 1000L);
                    }
                });
            } else {
                android.util.Log.e("MainActivity6", "connect: bleDevice and mac are both null, cannot connect");
            }
            return;
        }
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivity6.54
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.showProgressDialog(mainActivity6, mainActivity6.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(final BleDevice bleDevice3, BleException bleException) {
                MainActivity6 mainActivity6 = MainActivity6.this;
                ShowHelper.toastLong(mainActivity6, mainActivity6.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.disconnected));
                MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity6.this.signal_note.setText(MainActivity6.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                if (MainActivity6.this.connNum >= 3 || bleDevice3 == null) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity6.access$6208(MainActivity6.this);
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivity6.this.getResources().getString(R.string.initializing));
                MainActivity6.this.connNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivity6.this, MainActivity6.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                String rawNameMainAc = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                if (rawNameMainAc == null || rawNameMainAc.isEmpty()) rawNameMainAc = MainActivity6.this.deviceName != null ? MainActivity6.this.deviceName : "";
                MainActivity6.this.nameStar = rawNameMainAc;
                MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.connected));
                MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivity6.this.signal_note.setText(MainActivity6.this.nameStar + MainActivity6.this.getResources().getString(R.string.connected));
                MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_green));
                MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivity6.this.isFaultOn = 0;
                MainActivity6.this.gatt = bluetoothGatt;
                MainActivity6.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6.this.velobar.getProgress() - 1;
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6.this.freq.getProgress() - 1;
                        MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.7
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                if (!MainActivity6.this.btn_ball.getText().toString().equals(MainActivity6.this.getResources().getString(R.string.stop))) {
                    MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) 6, (byte) 1770, (byte) 6, (byte) 1590, 0, 0, 1, -91});
                }
                MainActivity6.this.getDefaultPoint1();
                MainActivity6.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivity6.this.blenoty.setText(MainActivity6.this.getResources().getString(R.string.disconnected));
                MainActivity6.this.blenoty.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity6.this.signal.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity6.this.signal_note.setText(MainActivity6.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity6.this.signal_note.setTextColor(MainActivity6.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivity6.this.isFaultOn = 0;
                if (z || MainActivity6.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.54.9
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity6.access$6208(MainActivity6.this);
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
                this.num_lr.setText("" + (74 - (this.lr / 30)));
                if (this.ud > 1410) {
                    this.num_ud.setText("" + (64 - (this.ud / 30)));
                } else {
                    this.num_ud.setText("" + (((1410 - this.ud) / 50) + 17));
                }
                this.freq.setProgress(this.defaultDaoList.get(i).getFreq());
                this.f_tv.setText("" + this.defaultDaoList.get(i).getFreq());
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.55
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6.this.freq.getProgress() - 1;
                        MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 30L);
                this.velobar.setProgress((float) this.defaultDaoList.get(i).getVelo());
                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i).getVelo() - 1]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.56
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6.this.velobar.getProgress() - 1;
                        MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[progress] ^ 99), -91});
                    }
                }, 80L);
                this.rotatebar.setProgress((float) this.defaultDaoList.get(i).getRote());
                final int rote = this.defaultDaoList.get(i).getRote();
                if (rote < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.57
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i3) * 5), 0, 0, 0, 0, (byte) (((-i3) * 5) ^ 96), -91});
                        }
                    }, 120L);
                }
                if (rote > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.58
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i3 * 5), 0, 0, 0, 0, (byte) ((i3 * 5) ^ 99), -91});
                        }
                    }, 120L);
                }
                if (rote == 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.59
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                    }, 120L);
                }
                this.r_tv.setText("" + rote);
                if (this.rgheight.getVisibility() != 0 || (grade = this.defaultDaoList.get(i).getGrade()) == 0) {
                    return;
                }
                this.valueSelect = grade;
                this.value_h.setText("" + grade);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.60
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.sendBaseData(1);
                    }
                }, 160L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61
            @Override // java.lang.Runnable
            public void run() {
                MainActivity6.this.defaultDaoList = new ArrayList();
                MainActivity6.this.savedefault.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivity6.this.saveColor = 1;
                MainActivity6 mainActivity6 = MainActivity6.this;
                mainActivity6.defaultDaoList = mainActivity6.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivity6.this.defaultDaoList.size(); i++) {
                    if (MainActivity6.this.defaultDaoList.get(i).getDaoName() != null && MainActivity6.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivity6.this.savedefault.setBackground(MainActivity6.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivity6.this.saveColor = 0;
                        MainActivity6 mainActivity62 = MainActivity6.this;
                        mainActivity62.lr = mainActivity62.defaultDaoList.get(i).getItem2();
                        MainActivity6 mainActivity63 = MainActivity6.this;
                        mainActivity63.ud = mainActivity63.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivity6.this.lr;
                        short s2 = (short) MainActivity6.this.ud;
                        MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                        if (MainActivity6.this.ud > 1410) {
                            MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                        } else {
                            MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                        }
                        MainActivity6.this.freq.setProgress(MainActivity6.this.defaultDaoList.get(i).getFreq());
                        MainActivity6.this.f_tv.setText("" + MainActivity6.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity6.this.freq.getProgress() - 1;
                                MainActivity6.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivity6.this.velobar.setProgress((float) MainActivity6.this.defaultDaoList.get(i).getVelo());
                        MainActivity6.this.v_tv.setText("" + MainActivity6.this.veloTins[MainActivity6.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity6.this.velobar.getProgress() - 1;
                                MainActivity6.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivity6.this.rotatebar.setProgress((float) MainActivity6.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivity6.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity6.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity6.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.61.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivity6.this.r_tv.setText("" + rote);
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
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity6.62
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
                        MainActivity6.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity6.this.isFaultOn == 0) {
                        MainActivity6.access$6808(MainActivity6.this);
                        MainActivity6.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PT4") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PR1"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity6.63
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
                        MainActivity6.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity6.this.isFaultOn == 0) {
                        MainActivity6.access$6808(MainActivity6.this);
                        MainActivity6.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.64
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6.this.checkPower();
            }
        }, 3600L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.65
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6.this.sendBaseData(1);
            }
        }, 3800L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.66
            @Override // java.lang.Runnable
            public synchronized void run() {
                if (MainActivity6.this.forbid == 1) {
                    MainActivity6.this.writeBleData(new byte[]{-86, 99, 10, 0, 0, 0, 0, 0, 105, -91});
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
            if (str != null && (str.startsWith("PT1") || this.nameStar.startsWith("PT2"))) {
                this.group_cate.setVisibility(8);
            }
            this.hintpoints.setVisibility(0);
            this.tennipic2.setVisibility(8);
            this.tennipic3.setVisibility(8);
            this.tennipic4.setVisibility(8);
            this.tennipic5.setVisibility(8);
            this.tennipic7.setVisibility(0);
            this.fourbtn.setVisibility(8);
            this.bg_four.setVisibility(8);
            this.bg_input.setVisibility(0);
            this.bg_input_big.setVisibility(0);
            this.control_all.setVisibility(0);
            this.self_program.setVisibility(0);
            this.self_program2.setVisibility(8);
            this.self_re.setVisibility(0);
            this.self_re2.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
            layoutParams.height = (int) (this.density * 44.0f);
            layoutParams.topMargin = (int) (this.density * 12.0f);
            this.start_layout.setLayoutParams(layoutParams);
            this.start_layout.setGravity(17);
            this.change_velo_layout2.setVisibility(8);
            this.rg_hint.setVisibility(0);
            this.tennipic6.setVisibility(8);
            this.rgheight.setVisibility(0);
            this.savepoints.setVisibility(0);
            this.modeCate = 1;
            return;
        }
        if (i == 2) {
            this.savedefault.setVisibility(8);
            this.points.setVisibility(0);
            this.group_cate.setVisibility(8);
            this.tennipic6.setVisibility(0);
            this.tennipic7.setVisibility(8);
            this.control_all.setVisibility(8);
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
        this.change_velo_layout2.setVisibility(8);
        this.control_all.setVisibility(0);
        this.tennipic6.setVisibility(8);
        this.group_cate.setVisibility(8);
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
        if (i2 == 1 || i2 == 2 || i2 == 5 || i2 == 7 || i2 == 8 || i2 == 9) {
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.68
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity6.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.67
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity6.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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

    /* JADX WARN: Code restructure failed: missing block: B:287:0x0dec, code lost:
    
        if (r1.get(r1.size() - 1).intValue() == 3) goto L250;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0e5e, code lost:
    
        if (r1.get(r1.size() - 1).intValue() == 3) goto L265;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x017c, code lost:
    
        if (r1.get(r1.size() - 1).intValue() == 3) goto L56;
     */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r17) {
        /*
            Method dump skipped, instructions count: 10471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusun.pusuntennis.MainActivity6.onClick(android.view.View):void");
    }

    private int getRandomNumberFrom11To15() {
        return new Random().nextInt(5) + 11;
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
            MainActivity6.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity6.this.ud > 1400) {
                    MainActivity6.access$512(MainActivity6.this, 30);
                }
                if (MainActivity6.this.ud <= 1400) {
                    MainActivity6.access$512(MainActivity6.this, 100);
                }
                if (MainActivity6.this.ud < 800) {
                    MainActivity6.this.ud = 800;
                }
                if (MainActivity6.this.ud > 1860) {
                    MainActivity6.this.ud = 1860;
                }
                short s = (short) MainActivity6.this.lr;
                if (MainActivity6.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6.this.ud;
                if (MainActivity6.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                if (MainActivity6.this.ud > 1410) {
                    MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                } else {
                    MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                }
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity6.this.ud <= 1400) {
                    MainActivity6.access$520(MainActivity6.this, 100);
                }
                if (MainActivity6.this.ud > 1400) {
                    MainActivity6.access$520(MainActivity6.this, 30);
                }
                if (MainActivity6.this.ud < 800) {
                    MainActivity6.this.ud = 800;
                }
                if (MainActivity6.this.ud > 1860) {
                    MainActivity6.this.ud = 1860;
                }
                short s = (short) MainActivity6.this.lr;
                if (MainActivity6.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6.this.ud;
                if (MainActivity6.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                if (MainActivity6.this.ud > 1410) {
                    MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                } else {
                    MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                }
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity6.access$412(MainActivity6.this, 30);
                if (MainActivity6.this.lr < 1140) {
                    MainActivity6.this.lr = 1140;
                }
                if (MainActivity6.this.lr > 2040) {
                    MainActivity6.this.lr = 2040;
                }
                short s = (short) MainActivity6.this.lr;
                if (MainActivity6.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6.this.ud;
                if (MainActivity6.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                if (MainActivity6.this.ud > 1410) {
                    MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                } else {
                    MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity6.access$420(MainActivity6.this, 30);
                if (MainActivity6.this.lr < 1140) {
                    MainActivity6.this.lr = 1140;
                }
                if (MainActivity6.this.lr > 2040) {
                    MainActivity6.this.lr = 2040;
                }
                short s = (short) MainActivity6.this.lr;
                if (MainActivity6.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6.this.ud;
                if (MainActivity6.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6.this.num_lr.setText("" + (74 - (MainActivity6.this.lr / 30)));
                if (MainActivity6.this.ud > 1410) {
                    MainActivity6.this.num_ud.setText("" + (64 - (MainActivity6.this.ud / 30)));
                } else {
                    MainActivity6.this.num_ud.setText("" + (((1410 - MainActivity6.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.165
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.166
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity6.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivity6.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivity6.this.freq.getProgress());
                seleDao.setItem1(MainActivity6.this.radioNum);
                seleDao.setItem2(MainActivity6.this.valueSelect);
                seleDao.setVelo(MainActivity6.this.velobar.getProgress());
                seleDao.setRote(MainActivity6.this.rotatebar.getProgress());
                MainActivity6.this.daoSession.insertOrReplace(seleDao);
                create.dismiss();
            }
        });
        create.show();
    }

    public void alert_dialog_input2() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.167
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6.168
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity6 mainActivity6 = MainActivity6.this;
                    ShowHelper.toastShort(mainActivity6, mainActivity6.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity6.this.daoSession.loadAll(VariDao.class);
                VariDao variDao = new VariDao();
                variDao.setDaoName("" + editText.getText().toString().trim());
                variDao.setSeles(MainActivity6.this.select_dianwei.getText().toString().trim());
                variDao.setFreq1(BasicData38.b3[20][3]);
                variDao.setVelo1(BasicData38.b3[20][2]);
                variDao.setLr1(BasicData38.b3[20][0]);
                variDao.setUd1(BasicData38.b3[20][1]);
                variDao.setFreq2(BasicData38.b3[21][3]);
                variDao.setVelo2(BasicData38.b3[21][2]);
                variDao.setLr2(BasicData38.b3[21][0]);
                variDao.setUd2(BasicData38.b3[21][1]);
                variDao.setFreq3(BasicData38.b3[22][3]);
                variDao.setVelo3(BasicData38.b3[22][2]);
                variDao.setLr3(BasicData38.b3[22][0]);
                variDao.setUd3(BasicData38.b3[22][1]);
                variDao.setFreq4(BasicData38.b3[23][3]);
                variDao.setVelo4(BasicData38.b3[23][2]);
                variDao.setLr4(BasicData38.b3[23][0]);
                variDao.setUd4(BasicData38.b3[23][1]);
                variDao.setFreq5(BasicData38.b3[24][3]);
                variDao.setVelo5(BasicData38.b3[24][2]);
                variDao.setLr5(BasicData38.b3[24][0]);
                variDao.setUd5(BasicData38.b3[24][1]);
                variDao.setFreq6(BasicData38.b3[25][3]);
                variDao.setVelo6(BasicData38.b3[25][2]);
                variDao.setLr6(BasicData38.b3[25][0]);
                variDao.setUd6(BasicData38.b3[25][1]);
                MainActivity6.this.daoSession.insertOrReplace(variDao);
                create.dismiss();
            }
        });
        create.show();
    }
}
