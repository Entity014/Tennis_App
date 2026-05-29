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
import android.os.LocaleList;
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
import com.pusun.pusuntennis.utils.BasicData33;
import com.pusun.pusuntennis.utils.BasicData5;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.Util;
import com.pusun.pusuntennis.utils.VarispinMsg;
import com.pusun.pusuntennis.utils.VarispinStartMsg;
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
import java.util.Locale;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivity6New extends AppCompatActivity implements View.OnClickListener {
    private static final String FORBID_INFO = "forbid";
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static BleDevice bleDevice;
    private String deviceMac;
    private String deviceName;
    private Button aidrill;
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
    private Button change_course;
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
    private int pause;
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
    private int[] reals;
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
    private int[] veloNums = {30, 32, 35, 37, 40, 42, 45, 47, 50, 52, 55, 57, 60};
    private int[] veloTins = {30, 32, 35, 37, 40, 42, 45, 47, 50, 52, 55, 57, 60};
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

    static /* synthetic */ int access$308(MainActivity6New mainActivity6New) {
        int i = mainActivity6New.valueSelect;
        mainActivity6New.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(MainActivity6New mainActivity6New) {
        int i = mainActivity6New.valueSelect;
        mainActivity6New.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$412(MainActivity6New mainActivity6New, int i) {
        int i2 = mainActivity6New.lr + i;
        mainActivity6New.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$420(MainActivity6New mainActivity6New, int i) {
        int i2 = mainActivity6New.lr - i;
        mainActivity6New.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$512(MainActivity6New mainActivity6New, int i) {
        int i2 = mainActivity6New.ud + i;
        mainActivity6New.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$520(MainActivity6New mainActivity6New, int i) {
        int i2 = mainActivity6New.ud - i;
        mainActivity6New.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$6208(MainActivity6New mainActivity6New) {
        int i = mainActivity6New.connNum;
        mainActivity6New.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$6808(MainActivity6New mainActivity6New) {
        int i = mainActivity6New.isFaultOn;
        mainActivity6New.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main6new);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivity6New$$ExternalSyntheticLambda2
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity6New.lambda$onCreate$0(view, windowInsetsCompat);
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
        Button button = (Button) findViewById(R.id.change_course);
        this.change_course = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6New.this.startActivity(new Intent(MainActivity6New.this, (Class<?>) CourseListActivity.class));
            }
        });
        if (isSystemChinese(this)) {
            this.change_course.setVisibility(0);
        }
        Button button2 = (Button) findViewById(R.id.change_tennis);
        this.change_tennis = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                java.util.List<com.clj.fastble.data.BleDevice> connected = com.clj.fastble.BleManager.getInstance().getAllConnectedDevice();
                final com.clj.fastble.data.BleDevice currentDevice = (connected != null && !connected.isEmpty()) ? connected.get(0) : bleDevice;
                com.clj.fastble.BleManager.getInstance().disconnectAllDevice();
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.changing));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ShowHelper.dismissProgressDialog();
                        Intent intent = new Intent(MainActivity6New.this, (Class<?>) MainActivityPad1.class);
                        intent.putExtra("device", currentDevice);
                        intent.putExtra("device_name", com.pusun.pusuntennis.utils.Util.getDeviceName(currentDevice));
                        MainActivity6New.this.startActivity(intent);
                    }
                }, 1500L);
            }
        });
        Button button3 = (Button) findViewById(R.id.savedefault);
        this.savedefault = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivity6New.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivity6New.this.freq.getProgress());
                    defaultDao.setGrade(MainActivity6New.this.valueSelect);
                    defaultDao.setItem2(MainActivity6New.this.lr);
                    defaultDao.setItem3(MainActivity6New.this.ud);
                    defaultDao.setVelo(MainActivity6New.this.velobar.getProgress());
                    defaultDao.setRote(MainActivity6New.this.rotatebar.getProgress());
                    if (MainActivity6New.this.modeCate == 0) {
                        if (MainActivity6New.this.modeNum != 1) {
                            if (MainActivity6New.this.modeNum != 7) {
                                if (MainActivity6New.this.modeNum != 5) {
                                    if (MainActivity6New.this.modeNum != 4) {
                                        if (MainActivity6New.this.modeNum != 2) {
                                            if (MainActivity6New.this.modeNum != 3) {
                                                if (MainActivity6New.this.modeNum != 6) {
                                                    if (MainActivity6New.this.modeNum != 8) {
                                                        if (MainActivity6New.this.modeNum == 9) {
                                                            defaultDao.setDaoName("moon");
                                                            defaultDao.setNumber(24L);
                                                            MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                                        }
                                                    } else {
                                                        defaultDao.setDaoName("place");
                                                        defaultDao.setNumber(23L);
                                                        MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                                    }
                                                } else {
                                                    defaultDao.setDaoName("cross" + MainActivity6New.this.tagC);
                                                    if (MainActivity6New.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivity6New.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivity6New.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivity6New.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivity6New.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivity6New.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivity6New.this.tagV);
                                                if (MainActivity6New.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivity6New.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivity6New.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivity6New.this.tagH);
                                            if (MainActivity6New.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivity6New.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivity6New.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivity6New.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivity6New.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivity6New.this.tagHT);
                                if (MainActivity6New.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivity6New.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivity6New.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivity6New.this.tagFix);
                            if (MainActivity6New.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivity6New.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivity6New.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivity6New.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.save_default_success));
                    MainActivity6New.this.savedefault.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivity6New.this.saveColor = 0;
                    return;
                }
                MainActivity6New mainActivity6New2 = MainActivity6New.this;
                ShowHelper.toastShort(mainActivity6New2, mainActivity6New2.getResources().getString(R.string.no_change_default));
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
        this.frontde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] > 2190) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] = 2190;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[1] = (short) (sArr[1] + 30);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] > 1830) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] = 1830;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.frontadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] = (short) (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] - 30);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] < 1260) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][0] = 1260;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] = (short) (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] - 30);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] < 1110) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][1] = 1110;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] - 5);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][2] < 20) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][2] = 80;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] + 5);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][2] > 80) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][2] = 80;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][3] < 25) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][3] = 25;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData5.b3[MainActivity6New.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData5.b3[MainActivity6New.this.vari_point_num + 19][3] > 65) {
                    BasicData5.b3[MainActivity6New.this.vari_point_num + 19][3] = 65;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
                int i = MainActivity6New.this.vari_point_num + 19;
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) (MainActivity6New.this.vari_point_num + 20), (byte) (BasicData5.b3[i][0] >> 8), (byte) BasicData5.b3[i][0], (byte) (BasicData5.b3[i][1] >> 8), (byte) BasicData5.b3[i][1], (byte) (BasicData5.b3[i][2] >> 8), (byte) BasicData5.b3[i][2], (byte) BasicData5.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.u_dian.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6New.this.dian_num.getText().toString().trim()).intValue();
                int intValue2 = Integer.valueOf(MainActivity6New.this.l_r.getText().toString().trim()).intValue();
                int intValue3 = Integer.valueOf(MainActivity6New.this.u_d.getText().toString().trim()).intValue();
                int intValue4 = Integer.valueOf(MainActivity6New.this.s_d.getText().toString().trim()).intValue();
                MainActivity6New.this.writeBleData(new byte[]{-86, (byte) intValue, (byte) (intValue2 >> 8), (byte) intValue2, (byte) (intValue3 >> 8), (byte) intValue3, (byte) (intValue4 >> 8), (byte) intValue4, (byte) Integer.valueOf(MainActivity6New.this.interval.getText().toString().trim()).intValue(), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        this.lr = BasicData5.a13[0];
        this.ud = BasicData5.a13[1];
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
        this.pos = new TextView[]{this.num1, this.num2, this.num3, this.num4, this.num5, this.num6, this.num7, this.num8, this.num9, this.num10, this.num11, this.num12, this.num13, this.num14, this.num15, this.num16, this.num17, this.num18, this.num19, this.num20, this.num21, this.num22, this.num23, this.num24, this.num25, this.num26, this.num27, this.num28};
        this.poids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        this.reals = new int[]{1, 21, 2, 3, 4, 22, 5, 6, 23, 7, 8, 9, 24, 10, 11, 25, 12, 13, 14, 26, 15, 16, 27, 17, 18, 19, 28, 20};
        this.self_point = (Button) findViewById(R.id.self_point);
        this.high_point = (Button) findViewById(R.id.high_point);
        this.left_right = (Button) findViewById(R.id.left_right);
        this.down_up = (Button) findViewById(R.id.down_up);
        this.whole = (Button) findViewById(R.id.whole);
        this.aidrill = (Button) findViewById(R.id.aidrill);
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
        this.aidrill.setOnClickListener(this);
        this.hit.setOnClickListener(this);
        this.place.setOnClickListener(this);
        this.moon.setOnClickListener(this);
        this.step.setOnClickListener(this);
        this.clear.setOnClickListener(this);
        this.selection.setOnClickListener(this);
        this.group_cross.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button4;
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.stop_ball);
        this.stop_ball = button5;
        button5.setOnClickListener(this);
        if (com.pusun.pusuntennis.utils.Util.getDeviceVersion(bleDevice) < 230712) {
            this.step.setVisibility(4);
            this.step.setClickable(false);
            this.aidrill.setVisibility(4);
            this.aidrill.setClickable(false);
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
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.13
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6New.this.getUrlTxt();
            }
        }, 1000L);
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity6New.14
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinner_zu = (Spinner) findViewById(R.id.spinner_zu);
        this.spinner_zu.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.point_vari)));
        this.spinner_zu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity6New.15
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    MainActivity6New.this.vari_point_num = 1;
                } else if (i == 1) {
                    MainActivity6New.this.vari_point_num = 2;
                } else if (i == 2) {
                    MainActivity6New.this.vari_point_num = 3;
                } else if (i == 3) {
                    MainActivity6New.this.vari_point_num = 4;
                } else if (i == 4) {
                    MainActivity6New.this.vari_point_num = 5;
                } else if (i == 5) {
                    MainActivity6New.this.vari_point_num = 6;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.showSelectPoint(mainActivity6New.vari_point_num);
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
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6New.16
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("up:1");
                    if (MainActivity6New.this.ud >= 1410) {
                        MainActivity6New.access$512(MainActivity6New.this, 30);
                    }
                    if (MainActivity6New.this.ud < 1410) {
                        MainActivity6New.access$512(MainActivity6New.this, 50);
                    }
                    if (MainActivity6New.this.ud < 910) {
                        MainActivity6New.this.ud = 910;
                    }
                    if (MainActivity6New.this.ud > 1830) {
                        MainActivity6New.this.ud = 1830;
                    }
                    short s = (short) MainActivity6New.this.lr;
                    if (MainActivity6New.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6New.this.ud;
                    if (MainActivity6New.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                    if (MainActivity6New.this.ud >= 1410) {
                        MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                    } else {
                        MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.16.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6New.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivity6New.this.isTouch = false;
                    if (MainActivity6New.this.timeCount1 != null) {
                        MainActivity6New.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.16.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6New.this.timeCount1 != null) {
                                MainActivity6New.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6New.17
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("down:1");
                    if (MainActivity6New.this.ud <= 1410) {
                        MainActivity6New.access$520(MainActivity6New.this, 50);
                    }
                    if (MainActivity6New.this.ud > 1410) {
                        MainActivity6New.access$520(MainActivity6New.this, 30);
                    }
                    if (MainActivity6New.this.ud < 910) {
                        MainActivity6New.this.ud = 910;
                    }
                    if (MainActivity6New.this.ud > 1830) {
                        MainActivity6New.this.ud = 1830;
                    }
                    short s = (short) MainActivity6New.this.lr;
                    if (MainActivity6New.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6New.this.ud;
                    if (MainActivity6New.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                    if (MainActivity6New.this.ud > 1410) {
                        MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                    } else {
                        MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.17.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6New.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6New.this.isTouch = false;
                    if (MainActivity6New.this.timeCount2 != null) {
                        MainActivity6New.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.17.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6New.this.timeCount2 != null) {
                                MainActivity6New.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6New.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("left:1");
                    MainActivity6New.access$412(MainActivity6New.this, 30);
                    if (MainActivity6New.this.lr < 1260) {
                        MainActivity6New.this.lr = 1260;
                    }
                    if (MainActivity6New.this.lr > 2190) {
                        MainActivity6New.this.lr = 2190;
                    }
                    short s = (short) MainActivity6New.this.lr;
                    if (MainActivity6New.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6New.this.ud;
                    if (MainActivity6New.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                    if (MainActivity6New.this.ud > 1410) {
                        MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                    } else {
                        MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.18.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.18.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6New.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6New.this.isTouch = false;
                    if (MainActivity6New.this.timeCount3 != null) {
                        MainActivity6New.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.18.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6New.this.timeCount3 != null) {
                                MainActivity6New.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity6New.19
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("right:1");
                    MainActivity6New.access$420(MainActivity6New.this, 30);
                    if (MainActivity6New.this.lr < 1260) {
                        MainActivity6New.this.lr = 1260;
                    }
                    if (MainActivity6New.this.lr > 2190) {
                        MainActivity6New.this.lr = 2190;
                    }
                    short s = (short) MainActivity6New.this.lr;
                    if (MainActivity6New.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity6New.this.ud;
                    if (MainActivity6New.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                    if (MainActivity6New.this.ud > 1410) {
                        MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                    } else {
                        MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.19.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.19.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivity6New.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity6New.this.isTouch = false;
                    if (MainActivity6New.this.timeCount4 != null) {
                        MainActivity6New.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.19.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity6New.this.timeCount4 != null) {
                                MainActivity6New.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6New.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6New.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6New.22
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivity6New.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.22.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivity6New.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6New.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivity6New.this.f_tv.setText("" + i);
                    MainActivity6New.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6New.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity6New.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    MainActivity6New.this.f_tv.setText("" + i);
                    MainActivity6New.this.freq.setProgress((float) i);
                    MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[intValue], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[intValue] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.24.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6New.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6New.25
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                int progressVal = indicatorSeekBar3.getProgress();

                if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 8) {

                    indicatorSeekBar3.setProgress(0.0f);

                    progressVal = 0;

                }

                final int progress = progressVal;
                if (progress != 0 && MainActivity6New.this.velobar.getProgress() < 5) {
                    MainActivity6New.this.velobar.setProgress(5.0f);
                    MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[4] ^ 99), -91});
                    MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.25.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.25.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.25.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity6New.this.r_tv.setText("" + progress);
                MainActivity6New.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity6New.this.rotatebar.getProgress();
                if (progress > -6) {
                    int iVal = progress - 1;

                    if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 8) {

                        MainActivity6New.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivity6New.this.velobar.getProgress() < 5) {
                        MainActivity6New.this.velobar.setProgress(5.0f);
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[4] ^ 99), -91});
                        MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6New.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.26.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.26.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity6New.this.r_tv.setText("" + i);
                    MainActivity6New.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity6New.this.rotatebar.getProgress();
                if (progress < 6) {
                    int iVal = progress + 1;

                    if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 8) {

                        MainActivity6New.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivity6New.this.velobar.getProgress() < 5) {
                        MainActivity6New.this.velobar.setProgress(5.0f);
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[4] ^ 99), -91});
                        MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.27.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity6New.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.27.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.27.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity6New.this.r_tv.setText("" + i);
                    MainActivity6New.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6New.28
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                if (MainActivity6New.this.forbid == 1) {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.showAlertDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.alert), MainActivity6New.this.getResources().getString(R.string.forbid_alert));
                    return;
                }
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivity6New.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivity6New.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivity6New.this.rotatebar.getProgress() != 0) {
                    MainActivity6New.this.r_tv.setText("0");
                    MainActivity6New.this.rotatebar.setProgress(0.0f);
                    MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.28.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivity6New.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.forbid != 1) {
                    int progress = MainActivity6New.this.velobar.getProgress();
                    if (progress > 1) {
                        int i = progress - 1;
                        if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 5 && i > 6) {
                            MainActivity6New.this.velobar.setProgress(6.0f);
                            i = 6;
                        }
                        TextView textView12 = MainActivity6New.this.v_tv;
                        StringBuilder sb = new StringBuilder("");
                        int i2 = i - 1;
                        sb.append(MainActivity6New.this.veloTins[i2]);
                        textView12.setText(sb.toString());
                        MainActivity6New.this.velobar.setProgress(i);
                        if (i < 5 && MainActivity6New.this.rotatebar.getProgress() != 0) {
                            MainActivity6New.this.r_tv.setText("0");
                            MainActivity6New.this.rotatebar.setProgress(0.0f);
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[i2] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.29.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivity6New.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showAlertDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.alert), MainActivity6New.this.getResources().getString(R.string.forbid_alert));
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.forbid != 1) {
                    int progress = MainActivity6New.this.velobar.getProgress();
                    if (progress < 13) {
                        int i = progress + 1;
                        if (MainActivity6New.this.modeCate == 0 && MainActivity6New.this.modeNum == 5 && i > 6) {
                            MainActivity6New.this.velobar.setProgress(6.0f);
                            i = 6;
                        }
                        TextView textView12 = MainActivity6New.this.v_tv;
                        StringBuilder sb = new StringBuilder("");
                        int i2 = i - 1;
                        sb.append(MainActivity6New.this.veloTins[i2]);
                        textView12.setText(sb.toString());
                        MainActivity6New.this.velobar.setProgress(i);
                        if (i < 5 && MainActivity6New.this.rotatebar.getProgress() != 0) {
                            MainActivity6New.this.r_tv.setText("0");
                            MainActivity6New.this.rotatebar.setProgress(0.0f);
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[i2] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.30.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivity6New.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showAlertDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.alert), MainActivity6New.this.getResources().getString(R.string.forbid_alert));
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
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6New.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity6New.this.self.setVisibility(8);
                    MainActivity6New.this.group.setVisibility(0);
                    MainActivity6New.this.points.setVisibility(0);
                    MainActivity6New.this.hintpoints.setVisibility(0);
                    MainActivity6New.this.tennipic2.setVisibility(8);
                    MainActivity6New.this.tennipic3.setVisibility(8);
                    MainActivity6New.this.tennipic4.setVisibility(8);
                    MainActivity6New.this.tennipic5.setVisibility(8);
                    MainActivity6New.this.fourbtn.setVisibility(8);
                    MainActivity6New.this.bg_four.setVisibility(8);
                    MainActivity6New.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivity6New.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivity6New.this.density * 36.0f);
                    MainActivity6New.this.start_layout.setLayoutParams(layoutParams);
                    MainActivity6New.this.start_layout.setGravity(17);
                    MainActivity6New.this.modeCate = 1;
                    return;
                }
                MainActivity6New.this.self.setVisibility(0);
                MainActivity6New.this.group.setVisibility(8);
                MainActivity6New.this.points.setVisibility(8);
                MainActivity6New.this.hintpoints.setVisibility(8);
                MainActivity6New.this.tennipic2.setVisibility(0);
                MainActivity6New.this.bg_input.setVisibility(8);
                MainActivity6New.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivity6New.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivity6New.this.density * 90.0f);
                MainActivity6New.this.start_layout.setLayoutParams(layoutParams2);
                MainActivity6New.this.start_layout.setGravity(83);
                MainActivity6New.this.fourbtn.setVisibility(0);
                MainActivity6New.this.bg_four.setVisibility(0);
                if (MainActivity6New.this.modeNum == 2) {
                    MainActivity6New.this.tennipic3.setVisibility(0);
                    MainActivity6New.this.tennipic4.setVisibility(8);
                    MainActivity6New.this.tennipic5.setVisibility(8);
                }
                if (MainActivity6New.this.modeNum == 3) {
                    MainActivity6New.this.tennipic3.setVisibility(8);
                    MainActivity6New.this.tennipic4.setVisibility(0);
                    MainActivity6New.this.tennipic5.setVisibility(8);
                }
                if (MainActivity6New.this.modeNum == 4) {
                    MainActivity6New.this.tennipic3.setVisibility(8);
                    MainActivity6New.this.tennipic4.setVisibility(8);
                    MainActivity6New.this.tennipic5.setVisibility(0);
                }
                MainActivity6New.this.modeCate = 0;
            }
        });
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.blenoty.getText().toString().trim().contains(MainActivity6New.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    if (MainActivity6New.bleDevice != null) {
                        MainActivity6New.this.connect(MainActivity6New.bleDevice);
                    } else {
                        MainActivity6New.this.checkPermissions();
                    }
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.disconnected));
                    MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity6New.33
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivity6New.this.radioNum = 0;
                        MainActivity6New.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivity6New.this.radioNum = 1;
                        MainActivity6New.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivity6New.this.radioNum = 2;
                        MainActivity6New.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity6New.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity6New.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity6New.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity6New.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity6New.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.34.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity6New.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity6New.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity6New.this, MainActivity6New.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_route_name));
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity6New.this.daoSession.loadAll(VariDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity6New.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity6New.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity6New.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity6New.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.35.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity6New.this.daoSession.loadAll(VariDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((VariDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity6New.this.daoSession.delete((VariDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity6New.this, MainActivity6New.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.select_dianwei.getText() != null && !MainActivity6New.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity6New.this.alert_dialog_input();
                } else {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity6New.this.select_dianwei.getText() != null && !MainActivity6New.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity6New.this.alert_dialog_input2();
                } else {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass38());
        this.lastpoints2.setOnClickListener(new AnonymousClass39());
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.valueSelect = Integer.parseInt(mainActivity6New.value_h.getText().toString().trim());
                MainActivity6New.access$308(MainActivity6New.this);
                if (MainActivity6New.this.valueSelect > 13) {
                    MainActivity6New.this.valueSelect = 13;
                    return;
                }
                MainActivity6New mainActivity6New2 = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New2, mainActivity6New2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.40.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.40.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity6New.this.value_h.setText("" + MainActivity6New.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.40.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.40.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity6New.this.checkIfUpdate();
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.valueSelect = Integer.parseInt(mainActivity6New.value_h.getText().toString().trim());
                MainActivity6New.access$310(MainActivity6New.this);
                if (MainActivity6New.this.valueSelect < 1) {
                    MainActivity6New.this.valueSelect = 1;
                    return;
                }
                MainActivity6New mainActivity6New2 = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New2, mainActivity6New2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.41.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.41.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivity6New.this.value_h.setText("" + MainActivity6New.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.41.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.41.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity6New.this.checkIfUpdate();
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

    /* renamed from: com.pusun.pusuntennis.MainActivity6New$38, reason: invalid class name */
    class AnonymousClass38 implements View.OnClickListener {
        AnonymousClass38() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity6New.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6New.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6New.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity6New.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6New.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivity6New.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivity6New.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivity6New.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivity6New.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivity6New.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivity6New.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivity6New.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity6New.this.freq.getProgress() - 1;
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivity6New.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity6New.this.velobar.getProgress() - 1;
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivity6New.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivity6New.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivity6New.this.valueSelect = item2;
                                    MainActivity6New.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.38.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.sendBaseData(1);
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
            MainActivity6New mainActivity6New = MainActivity6New.this;
            ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_route_name));
        }
    }

    /* renamed from: com.pusun.pusuntennis.MainActivity6New$39, reason: invalid class name */
    class AnonymousClass39 implements View.OnClickListener {
        AnonymousClass39() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity6New.this.daoSession.loadAll(VariDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6New.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6New.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity6New.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6New.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new AnonymousClass1());
                optionPicker.show();
                return;
            }
            MainActivity6New mainActivity6New = MainActivity6New.this;
            ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_route_name));
        }

        /* renamed from: com.pusun.pusuntennis.MainActivity6New$39$1, reason: invalid class name */
        class AnonymousClass1 implements OptionPicker.OnOptionPickListener {
            AnonymousClass1() {
            }

            @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
            public void onOptionPicked(String str) {
                new ArrayList();
                List loadAll = MainActivity6New.this.daoSession.loadAll(VariDao.class);
                for (int i = 0; i < loadAll.size(); i++) {
                    if (((VariDao) loadAll.get(i)).getDaoName().equals(str)) {
                        MainActivity6New.this.select_dianwei.setText(((VariDao) loadAll.get(i)).getSeles());
                        String[] split = ((VariDao) loadAll.get(i)).getSeles().split(",");
                        MainActivity6New.this.selectPoints.clear();
                        for (String str2 : split) {
                            MainActivity6New.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                        MainActivity6New.this.showPoints();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.39.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        BasicData5.b3[20][0] = (short) ((VariDao) loadAll.get(i)).getLr1();
                        BasicData5.b3[20][1] = (short) ((VariDao) loadAll.get(i)).getUd1();
                        BasicData5.b3[20][2] = (short) ((VariDao) loadAll.get(i)).getVelo1();
                        BasicData5.b3[20][3] = (short) ((VariDao) loadAll.get(i)).getFreq1();
                        BasicData5.b3[21][0] = (short) ((VariDao) loadAll.get(i)).getLr2();
                        BasicData5.b3[21][1] = (short) ((VariDao) loadAll.get(i)).getUd2();
                        BasicData5.b3[21][2] = (short) ((VariDao) loadAll.get(i)).getVelo2();
                        BasicData5.b3[21][3] = (short) ((VariDao) loadAll.get(i)).getFreq2();
                        BasicData5.b3[22][0] = (short) ((VariDao) loadAll.get(i)).getLr3();
                        BasicData5.b3[22][1] = (short) ((VariDao) loadAll.get(i)).getUd3();
                        BasicData5.b3[22][2] = (short) ((VariDao) loadAll.get(i)).getVelo3();
                        BasicData5.b3[22][3] = (short) ((VariDao) loadAll.get(i)).getFreq3();
                        BasicData5.b3[23][0] = (short) ((VariDao) loadAll.get(i)).getLr4();
                        BasicData5.b3[23][1] = (short) ((VariDao) loadAll.get(i)).getUd4();
                        BasicData5.b3[23][2] = (short) ((VariDao) loadAll.get(i)).getVelo4();
                        BasicData5.b3[23][3] = (short) ((VariDao) loadAll.get(i)).getFreq4();
                        BasicData5.b3[24][0] = (short) ((VariDao) loadAll.get(i)).getLr5();
                        BasicData5.b3[24][1] = (short) ((VariDao) loadAll.get(i)).getUd5();
                        BasicData5.b3[24][2] = (short) ((VariDao) loadAll.get(i)).getVelo5();
                        BasicData5.b3[24][3] = (short) ((VariDao) loadAll.get(i)).getFreq5();
                        BasicData5.b3[25][0] = (short) ((VariDao) loadAll.get(i)).getLr6();
                        BasicData5.b3[25][1] = (short) ((VariDao) loadAll.get(i)).getUd6();
                        BasicData5.b3[25][2] = (short) ((VariDao) loadAll.get(i)).getVelo6();
                        BasicData5.b3[25][3] = (short) ((VariDao) loadAll.get(i)).getFreq6();
                        MainActivity6New.this.showSelectPoint(MainActivity6New.this.vari_point_num);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.39.1.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int length = BasicData5.b3.length - 6;
        while (length < BasicData5.b3.length) {
            final int final_length = length;
                                    int i2 = length + 1;
                                    short s = BasicData5.b3[length][0];
                                    short s2 = BasicData5.b3[length][0];
                                    short s3 = BasicData5.b3[length][1];
                                    short s4 = BasicData5.b3[length][1];
                                    final byte[] bArr = {-86, (byte) i2, (byte) (BasicData5.b3[length][0] >> 8), (byte) BasicData5.b3[length][0], (byte) (BasicData5.b3[length][1] >> 8), (byte) BasicData5.b3[length][1], (byte) (BasicData5.b3[length][2] >> 8), (byte) BasicData5.b3[length][2], (byte) BasicData5.b3[length][3], -91};
                                    AppLog.e("左右：" + ((int) BasicData5.b3[length][0]) + "上下：" + ((int) BasicData5.b3[length][1]) + "byte:" + MainActivity6New.bytesToHexString(bArr));
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.39.1.2.1
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            AppLog.e("第" + final_length + "条指令");
                                            MainActivity6New.this.writeBleData(bArr);
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
        sb.append(74 - (BasicData5.b3[i2][0] / 30));
        textView.setText(sb.toString());
        this.backvalue2.setText("" + (64 - (BasicData5.b3[i2][1] / 30)));
        TextView textView2 = this.front_m_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData5.b3[i2][2]);
        textView2.setText(sb2.toString());
        TextView textView3 = this.back_m_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData5.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUrlTxt() {
        new Thread(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.42
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
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.42.1
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    String[] split = sb.toString().split(";");
                                    int i = 0;
                                    while (true) {
                                        if (i < split.length) {
                                            if (com.pusun.pusuntennis.utils.Util.getDeviceName(MainActivity6New.bleDevice).equals(split[i].toString().trim())) {
                                                ShowHelper.showAlertDialog(MainActivity6New.this, MainActivity6New.this.getResources().getString(R.string.alert), MainActivity6New.this.getResources().getString(R.string.forbid_alert));
                                                SharedPreferences.Editor edit = MainActivity6New.this.getSharedPreferences(MainActivity6New.FORBID_INFO, 0).edit();
                                                edit.putInt(MainActivity6New.FORBID_INFO, 1);
                                                edit.commit();
                                                SharedPreferences sharedPreferences = MainActivity6New.this.getSharedPreferences(MainActivity6New.FORBID_INFO, 0);
                                                AppLog.e("" + sharedPreferences.getInt(MainActivity6New.FORBID_INFO, 0));
                                                MainActivity6New.this.forbid = sharedPreferences.getInt(MainActivity6New.FORBID_INFO, 0);
                                                break;
                                            }
                                            i++;
                                        } else {
                                            SharedPreferences.Editor edit2 = MainActivity6New.this.getSharedPreferences(MainActivity6New.FORBID_INFO, 0).edit();
                                            edit2.putInt(MainActivity6New.FORBID_INFO, 0);
                                            edit2.commit();
                                            SharedPreferences sharedPreferences2 = MainActivity6New.this.getSharedPreferences(MainActivity6New.FORBID_INFO, 0);
                                            AppLog.e("" + sharedPreferences2.getInt(MainActivity6New.FORBID_INFO, 0));
                                            MainActivity6New.this.forbid = sharedPreferences2.getInt(MainActivity6New.FORBID_INFO, 0);
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
        while (i < BasicData5.b2.length) {
            final int final_i = i;
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData5.b2[i][0] >> 8), (byte) BasicData5.b2[i][0], (byte) (BasicData5.b2[i][1] >> 8), (byte) BasicData5.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData5.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData5.b2[i][0])) ^ ((byte) (BasicData5.b2[i][1] >> 8))) ^ ((byte) BasicData5.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData5.b2[i][0]) + "上下：" + ((int) BasicData5.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.43
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity6New.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.44
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i3 = 10;
        while (i3 < BasicData5.b2.length) {
            final int final_i3 = i3;
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData5.b2[i3][0] >> 8), (byte) BasicData5.b2[i3][0], (byte) (BasicData5.b2[i3][1] >> 8), (byte) BasicData5.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData5.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData5.b2[i3][0])) ^ ((byte) (BasicData5.b2[i3][1] >> 8))) ^ ((byte) BasicData5.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData5.b2[i3][0]) + "上下：" + ((int) BasicData5.b2[i3][1]) + "byte:" + MainActivity6New.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.44.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i3 + "条指令");
                            MainActivity6New.this.writeBleData(bArr2);
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
        char c2 = 2;
        char c3 = 1;
        if (i == 1) {
            int i2 = (this.valueSelect - this.baseHt) * 30;
            int i3 = 0;
        while (i3 < BasicData5.b3.length) {
            final int final_i3 = i3;
                int i4 = i3 + 1;
                short s = BasicData5.b3[i3][0];
                short s2 = BasicData5.b3[i3][0];
                short s3 = BasicData5.b3[i3][c3];
                short s4 = BasicData5.b3[i3][c3];
                final byte[] bArr = {-86, (byte) i4, (byte) (BasicData5.b3[i3][0] >> 8), (byte) BasicData5.b3[i3][0], (byte) ((BasicData5.b3[i3][c3] - i2) >> 8), (byte) (BasicData5.b3[i3][c3] - i2), (byte) (BasicData5.b3[i3][c2] >> 8), (byte) BasicData5.b3[i3][c2], (byte) BasicData5.b3[i3][c], -91};
                AppLog.e("左右：" + ((int) BasicData5.b3[i3][0]) + "上下：" + (BasicData5.b3[i3][1] - i2) + "byte:" + bytesToHexString(bArr));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.45
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + final_i3 + "条指令");
                        MainActivity6New.this.writeBleData(bArr);
                    }
                }, (long) (i3 * 10));
                i3 = i4;
                c3 = 1;
                c = 3;
                c2 = 2;
            }
                final int final_i2 = i2;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.46
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int i5 = 10;
                    while (i5 < BasicData5.b3.length - 8) {
                        int i6 = i5 + 1;
                        short s5 = BasicData5.b3[i5][0];
                        short s6 = BasicData5.b3[i5][0];
                        short s7 = BasicData5.b3[i5][1];
                        short s8 = BasicData5.b3[i5][1];
                        final byte[] bArr2 = {-86, (byte) i6, (byte) (BasicData5.b3[i5][0] >> 8), (byte) BasicData5.b3[i5][0], (byte) ((BasicData5.b3[i5][1] - final_i2) >> 8), (byte) (BasicData5.b3[i5][1] - final_i2), (byte) (BasicData5.b3[i5][2] >> 8), (byte) BasicData5.b3[i5][2], (byte) BasicData5.b3[i5][3], -91};
                        AppLog.e("左右：" + ((int) BasicData5.b3[i5][0]) + "上下：" + (BasicData5.b3[i5][1] - final_i2) + "byte:" + MainActivity6New.bytesToHexString(bArr2));
                final int final_i5 = i5;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.46.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_i5 + "条指令");
                                MainActivity6New.this.writeBleData(bArr2);
                            }
                        }, (long) (i5 * 10));
                        i5 = i6;
                    }
                }
            }, 900L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.47
                @Override // java.lang.Runnable
                public synchronized void run() {
                    short s5 = 8;
                    int length = BasicData5.b3.length - 8;
        while (length < BasicData5.b3.length) {
            final int final_length = length;
                        int i5 = length + 1;
                        short s6 = BasicData5.b3[length][0];
                        short s7 = BasicData5.b3[length][0];
                        short s8 = BasicData5.b3[length][1];
                        short s9 = BasicData5.b3[length][1];
                        final byte[] bArr2 = {-86, (byte) i5, (byte) (BasicData5.b3[length][0] >> s5), (byte) BasicData5.b3[length][0], (byte) (BasicData5.b3[length][1] >> s5), (byte) BasicData5.b3[length][1], (byte) (BasicData5.b3[length][2] >> s5), (byte) BasicData5.b3[length][2], (byte) BasicData5.b3[length][3], -91};
                        AppLog.e("左右：" + ((int) BasicData5.b3[length][0]) + "上下：" + ((int) BasicData5.b3[length][1]) + "byte:" + MainActivity6New.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.47.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_length + "条指令");
                                MainActivity6New.this.writeBleData(bArr2);
                            }
                        }, (long) (length * 10));
                        length = i5;
                        s5 = 8;
                    }
                }
            }, 1200L);
            return;
        }
        int i5 = 0;
        while (i5 < BasicData5.b4.length) {
            int i6 = i5 + 1;
            byte b = (byte) i6;
            final byte[] bArr2 = {-86, b, (byte) (BasicData5.b4[i5][0] >> 8), (byte) BasicData5.b4[i5][0], (byte) (BasicData5.b4[i5][1] >> 8), (byte) BasicData5.b4[i5][1], 0, 0, (byte) ((((((byte) (BasicData5.b4[i5][0] >> 8)) ^ b) ^ ((byte) BasicData5.b4[i5][0])) ^ ((byte) (BasicData5.b4[i5][1] >> 8))) ^ ((byte) BasicData5.b4[i5][1])), -91};
            AppLog.e("左右：" + ((int) BasicData5.b4[i5][0]) + "上下：" + ((int) BasicData5.b4[i5][1]) + "byte:" + bytesToHexString(bArr2));
                final int final_i5 = i5;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.48
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i5 + "条指令");
                    MainActivity6New.this.writeBleData(bArr2);
                }
            }, (long) (i5 * 10));
            i5 = i6;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.49
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i7 = 10;
                while (i7 < BasicData5.b4.length) {
                    int i8 = i7 + 1;
                    byte b2 = (byte) i8;
                    final byte[] bArr3 = {-86, b2, (byte) (BasicData5.b4[i7][0] >> 8), (byte) BasicData5.b4[i7][0], (byte) (BasicData5.b4[i7][1] >> 8), (byte) BasicData5.b4[i7][1], 0, 0, (byte) ((((((byte) (BasicData5.b4[i7][0] >> 8)) ^ b2) ^ ((byte) BasicData5.b4[i7][0])) ^ ((byte) (BasicData5.b4[i7][1] >> 8))) ^ ((byte) BasicData5.b4[i7][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData5.b4[i7][0]) + "上下：" + ((int) BasicData5.b4[i7][1]) + "byte:" + MainActivity6New.bytesToHexString(bArr3));
                final int final_i7 = i7;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.49.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i7 + "条指令");
                            MainActivity6New.this.writeBleData(bArr3);
                        }
                    }, (long) (i7 * 10));
                    i7 = i8;
                }
            }
        }, 900L);
    }

    private void sendBaseDataMore() {
        for (int i = 0; i < BasicData5.b5.length; i++) {
            final int final_i = i;
            byte b = (byte) (i + 19);
            final byte[] bArr = {-86, b, (byte) (BasicData5.b5[i][0] >> 8), (byte) BasicData5.b5[i][0], (byte) (BasicData5.b5[i][1] >> 8), (byte) BasicData5.b5[i][1], 0, 0, (byte) ((((((byte) (BasicData5.b5[i][0] >> 8)) ^ b) ^ ((byte) BasicData5.b5[i][0])) ^ ((byte) (BasicData5.b5[i][1] >> 8))) ^ ((byte) BasicData5.b5[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData5.b5[i][0]) + "上下：" + ((int) BasicData5.b5[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.50
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivity6New.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.51
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i2 = 0;
        while (i2 < BasicData5.b2.length) {
            final int final_i2 = i2;
                    int i3 = i2 + 1;
                    byte b2 = (byte) i3;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData5.b2[i2][0] >> 8), (byte) BasicData5.b2[i2][0], (byte) (BasicData5.b2[i2][1] >> 8), (byte) BasicData5.b2[i2][1], 0, 0, (byte) ((((((byte) (BasicData5.b2[i2][0] >> 8)) ^ b2) ^ ((byte) BasicData5.b2[i2][0])) ^ ((byte) (BasicData5.b2[i2][1] >> 8))) ^ ((byte) BasicData5.b2[i2][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData5.b2[i2][0]) + "上下：" + ((int) BasicData5.b2[i2][1]) + "byte:" + MainActivity6New.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.51.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i2 + "条指令");
                            MainActivity6New.this.writeBleData(bArr2);
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
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.52
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PT4") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PT6")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.53
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.54
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastLong(mainActivity6New, mainActivity6New.getResources().getString(R.string.no_device_found));
                    if (MainActivity6New.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.54.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.startScan();
                            }
                        }, 1000L);
                        MainActivity6New.access$6208(MainActivity6New.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PT")) {
                    MainActivity6New.this.connect(list.get(0));
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
                    MainActivity6New mainActivity6New2 = MainActivity6New.this;
                    ShowHelper.toastLong(mainActivity6New2, mainActivity6New2.getResources().getString(R.string.no_device_found));
                    if (MainActivity6New.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.54.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.startScan();
                            }
                        }, 1000L);
                        MainActivity6New.access$6208(MainActivity6New.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivity6New.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity6New.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity6New.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivity6New.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity6New.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.54.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivity6New.this.connect((BleDevice) arrayList.get(i3));
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
                        MainActivity6New mainActivity = MainActivity6New.this;
                        ShowHelper.showProgressDialog(mainActivity, mainActivity.getResources().getString(R.string.connecting_device));
                    }
                    @Override
                    public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                        MainActivity6New mainActivity = MainActivity6New.this;
                        ShowHelper.toastLong(mainActivity, mainActivity.getResources().getString(R.string.connect_failure_check));
                        ShowHelper.dismissProgressDialog();
                        MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.disconnected));
                        MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity6New.this.signal_note.setText(MainActivity6New.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                    }
                    @Override
                    public void onConnectSuccess(BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        ShowHelper.setProgressDialogMessage(MainActivity6New.this.getResources().getString(R.string.initializing));
                        MainActivity6New.this.connNum = 0;
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                                ShowHelper.toastShort(MainActivity6New.this, MainActivity6New.this.getResources().getString(R.string.please_use));
                            }
                        }, com.google.android.exoplayer2.C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        String resolvedName = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                        if (resolvedName == null || resolvedName.isEmpty()) resolvedName = MainActivity6New.this.deviceName != null ? MainActivity6New.this.deviceName : "";
                        MainActivity6New.this.nameStar = resolvedName;
                        MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.connected));
                        MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_selector));
                        MainActivity6New.this.signal_note.setText(MainActivity6New.this.nameStar + MainActivity6New.this.getResources().getString(R.string.connected));
                        MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_green));
                        MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_blue));
                        MainActivity6New.this.isFaultOn = 0;
                        MainActivity6New.this.gatt = bluetoothGatt;
                        MainActivity6New.bleDevice = bleDevice3;
                        if (bleDevice3.getMac() != null) MainActivity6New.this.deviceMac = bleDevice3.getMac();
                        MainActivity6New.this.startNotify();
                    }
                    @Override
                    public void onDisConnected(boolean z, final BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.disconnected));
                        MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivity6New.this.signal_note.setText(MainActivity6New.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                        MainActivity6New.this.isFaultOn = 0;
                        if (z || MainActivity6New.this.connNum >= 3) { return; }
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public void run() { MainActivity6New.this.connect(bleDevice3); }
                        }, 1000L);
                    }
                });
            } else {
                android.util.Log.e("MainActivity6New", "connect: bleDevice and mac are both null, cannot connect");
            }
            return;
        }
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.55
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.showProgressDialog(mainActivity6New, mainActivity6New.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(final BleDevice bleDevice3, BleException bleException) {
                MainActivity6New mainActivity6New = MainActivity6New.this;
                ShowHelper.toastLong(mainActivity6New, mainActivity6New.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.disconnected));
                MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity6New.this.signal_note.setText(MainActivity6New.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                if (MainActivity6New.this.connNum >= 3 || bleDevice3 == null) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity6New.access$6208(MainActivity6New.this);
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivity6New.this.getResources().getString(R.string.initializing));
                MainActivity6New.this.connNum = 0;
                MyApplication.machineNum = 2;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivity6New.this, MainActivity6New.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                String rawNameMainAc = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                if (rawNameMainAc == null || rawNameMainAc.isEmpty()) rawNameMainAc = MainActivity6New.this.deviceName != null ? MainActivity6New.this.deviceName : "";
                MainActivity6New.this.nameStar = rawNameMainAc;
                MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.connected));
                MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivity6New.this.signal_note.setText(MainActivity6New.this.nameStar + MainActivity6New.this.getResources().getString(R.string.connected));
                MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_green));
                MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivity6New.this.isFaultOn = 0;
                MainActivity6New.this.gatt = bluetoothGatt;
                MainActivity6New.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6New.this.velobar.getProgress() - 1;
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6New.this.freq.getProgress() - 1;
                        MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.7
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                if (!MainActivity6New.this.btn_ball.getText().toString().equals(MainActivity6New.this.getResources().getString(R.string.stop))) {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) 6, (byte) 1770, (byte) 6, (byte) 1590, 0, 0, 1, -91});
                }
                MainActivity6New.this.getDefaultPoint1();
                MainActivity6New.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivity6New.this.blenoty.setText(MainActivity6New.this.getResources().getString(R.string.disconnected));
                MainActivity6New.this.blenoty.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity6New.this.signal.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity6New.this.signal_note.setText(MainActivity6New.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity6New.this.signal_note.setTextColor(MainActivity6New.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivity6New.this.isFaultOn = 0;
                if (z || MainActivity6New.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.55.9
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity6New.access$6208(MainActivity6New.this);
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
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.56
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6New.this.freq.getProgress() - 1;
                        MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 30L);
                this.velobar.setProgress((float) this.defaultDaoList.get(i).getVelo());
                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i).getVelo() - 1]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.57
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity6New.this.velobar.getProgress() - 1;
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[progress] ^ 99), -91});
                    }
                }, 80L);
                this.rotatebar.setProgress((float) this.defaultDaoList.get(i).getRote());
                final int rote = this.defaultDaoList.get(i).getRote();
                if (rote < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.58
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i3) * 5), 0, 0, 0, 0, (byte) (((-i3) * 5) ^ 96), -91});
                        }
                    }, 120L);
                }
                if (rote > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.59
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i3 * 5), 0, 0, 0, 0, (byte) ((i3 * 5) ^ 99), -91});
                        }
                    }, 120L);
                }
                if (rote == 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.60
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                    }, 120L);
                }
                this.r_tv.setText("" + rote);
                if (this.rgheight.getVisibility() != 0 || (grade = this.defaultDaoList.get(i).getGrade()) == 0) {
                    return;
                }
                this.valueSelect = grade;
                this.value_h.setText("" + grade);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.61
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.sendBaseData(1);
                    }
                }, 160L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62
            @Override // java.lang.Runnable
            public void run() {
                MainActivity6New.this.defaultDaoList = new ArrayList();
                MainActivity6New.this.savedefault.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivity6New.this.saveColor = 1;
                MainActivity6New mainActivity6New = MainActivity6New.this;
                mainActivity6New.defaultDaoList = mainActivity6New.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivity6New.this.defaultDaoList.size(); i++) {
                    if (MainActivity6New.this.defaultDaoList.get(i).getDaoName() != null && MainActivity6New.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivity6New.this.savedefault.setBackground(MainActivity6New.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivity6New.this.saveColor = 0;
                        MainActivity6New mainActivity6New2 = MainActivity6New.this;
                        mainActivity6New2.lr = mainActivity6New2.defaultDaoList.get(i).getItem2();
                        MainActivity6New mainActivity6New3 = MainActivity6New.this;
                        mainActivity6New3.ud = mainActivity6New3.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivity6New.this.lr;
                        short s2 = (short) MainActivity6New.this.ud;
                        MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                        if (MainActivity6New.this.ud > 1410) {
                            MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                        } else {
                            MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                        }
                        MainActivity6New.this.freq.setProgress(MainActivity6New.this.defaultDaoList.get(i).getFreq());
                        MainActivity6New.this.f_tv.setText("" + MainActivity6New.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity6New.this.freq.getProgress() - 1;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity6New.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivity6New.this.velobar.setProgress((float) MainActivity6New.this.defaultDaoList.get(i).getVelo());
                        MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[MainActivity6New.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity6New.this.velobar.getProgress() - 1;
                                MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivity6New.this.rotatebar.setProgress((float) MainActivity6New.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivity6New.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.62.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivity6New.this.r_tv.setText("" + rote);
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
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.63
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
                        MainActivity6New.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity6New.this.isFaultOn == 0) {
                        MainActivity6New.access$6808(MainActivity6New.this);
                        MainActivity6New.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PT4") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PT6"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity6New.64
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
                        MainActivity6New.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity6New.this.isFaultOn == 0) {
                        MainActivity6New.access$6808(MainActivity6New.this);
                        MainActivity6New.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.65
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6New.this.checkPower();
            }
        }, 3600L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.66
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity6New.this.sendBaseData(1);
            }
        }, 3800L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.67
            @Override // java.lang.Runnable
            public synchronized void run() {
                if (MainActivity6New.this.forbid == 1) {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 99, 10, 0, 0, 0, 0, 0, 105, -91});
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
    private void dealSpinvaryEvent(VarispinMsg varispinMsg) {
        if (varispinMsg.tipMsg.equals("vary")) {
            int i = varispinMsg.num;
            if (varispinMsg.position == varispinMsg.length && this.pause != 0) {
                writeBleData(new byte[]{-86, (byte) (varispinMsg.position + 20), (byte) (BasicData33.b3[i][0] >> 8), (byte) BasicData33.b3[i][0], (byte) (BasicData33.b3[i][1] >> 8), (byte) BasicData33.b3[i][1], (byte) BasicData33.b3[i][4], (byte) BasicData33.b3[i][2], (byte) (BasicData33.b3[i][3] + (this.pause * 20)), -91});
            } else {
                writeBleData(new byte[]{-86, (byte) (varispinMsg.position + 20), (byte) (BasicData33.b3[i][0] >> 8), (byte) BasicData33.b3[i][0], (byte) (BasicData33.b3[i][1] >> 8), (byte) BasicData33.b3[i][1], (byte) BasicData33.b3[i][4], (byte) BasicData33.b3[i][2], (byte) BasicData33.b3[i][3], -91});
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.68
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                }
            }, 50L);
            if (BasicData33.b3[i][4] == 0 || BasicData33.b3[i][4] == 50 || com.pusun.pusuntennis.utils.Util.getDeviceVersion(bleDevice) >= 650101) {
                return;
            }
            int i2 = BasicData33.b3[i][4] - 50;
            if (i2 < 0) {
                final int final_i2 = i2;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.69
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 2, (byte) (-final_i2), 0, 0, 0, 0, 0, -91});
                    }
                }, 100L);
            }
            if (i2 > 0) {
                final int final_i2 = i2;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.70
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, (byte) final_i2, 0, 0, 0, 0, 0, -91});
                    }
                }, 100L);
            }
        }
    }

    @Subcriber
    private void dealSpinvaryStartEvent(VarispinStartMsg varispinStartMsg) {
        int i = 1;
        if (varispinStartMsg.mode == 1) {
            String[] split = varispinStartMsg.str.split(",");
            this.pause = varispinStartMsg.pause;
            int length = split.length;
            ArrayList arrayList = new ArrayList();
            char c = 0;
            for (String str : split) {
                arrayList.add(Integer.valueOf(Integer.parseInt(str)));
            }
            int i2 = 0;
            while (i2 < arrayList.size()) {
                int intValue = ((Integer) arrayList.get(i2)).intValue() - i;
                if (i2 == arrayList.size() - 1 && varispinStartMsg.pause != 0) {
                    if (varispinStartMsg.pause == i) {
                        final byte[] bArr = {-86, (byte) (i2 + 21), (byte) (BasicData33.b3[intValue][c] >> 8), (byte) BasicData33.b3[intValue][c], (byte) (BasicData33.b3[intValue][i] >> 8), (byte) BasicData33.b3[intValue][i], (byte) BasicData33.b3[intValue][4], (byte) BasicData33.b3[intValue][2], (byte) (BasicData33.b3[intValue][3] + 20), -91};
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.71
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivity6New.this.writeBleData(bArr);
                            }
                        }, i2 * 50);
                    }
                    if (varispinStartMsg.pause == 2) {
                        final byte[] bArr2 = {-86, (byte) (i2 + 21), (byte) (BasicData33.b3[intValue][0] >> 8), (byte) BasicData33.b3[intValue][0], (byte) (BasicData33.b3[intValue][1] >> 8), (byte) BasicData33.b3[intValue][1], (byte) BasicData33.b3[intValue][4], (byte) BasicData33.b3[intValue][2], (byte) (BasicData33.b3[intValue][3] + 40), -91};
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.72
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivity6New.this.writeBleData(bArr2);
                            }
                        }, i2 * 50);
                    }
                } else {
                    final byte[] bArr3 = {-86, (byte) (i2 + 21), (byte) (BasicData33.b3[intValue][0] >> 8), (byte) BasicData33.b3[intValue][0], (byte) (BasicData33.b3[intValue][1] >> 8), (byte) BasicData33.b3[intValue][1], (byte) BasicData33.b3[intValue][4], (byte) BasicData33.b3[intValue][2], (byte) BasicData33.b3[intValue][3], -91};
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.73
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity6New.this.writeBleData(bArr3);
                        }
                    }, i2 * 50);
                }
                i2++;
                i = 1;
                c = 0;
            }
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
            if (length >= 1) {
                this.t1 = 21;
            }
            if (length >= 2) {
                this.t2 = 22;
            }
            if (length >= 3) {
                this.t3 = 23;
            }
            if (length >= 4) {
                this.t4 = 24;
            }
            if (length >= 5) {
                this.t5 = 25;
            }
            if (length >= 6) {
                this.t6 = 26;
            }
            if (length >= 7) {
                this.t7 = 27;
            }
            if (length >= 8) {
                this.t8 = 28;
            }
            if (length >= 9) {
                this.t9 = 29;
            }
            if (length >= 10) {
                this.t10 = 30;
            }
            if (length >= 11) {
                this.t11 = 31;
            }
            if (length >= 12) {
                this.t12 = 32;
            }
            if (length >= 13) {
                this.t13 = 33;
            }
            if (length >= 14) {
                this.t14 = 34;
            }
            if (length >= 15) {
                this.t15 = 35;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.74
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity6New.this.t1, (byte) MainActivity6New.this.t2, (byte) MainActivity6New.this.t3, (byte) MainActivity6New.this.t4, (byte) MainActivity6New.this.t5, (byte) (((((MainActivity6New.this.t1 ^ 108) ^ MainActivity6New.this.t2) ^ MainActivity6New.this.t3) ^ MainActivity6New.this.t4) ^ MainActivity6New.this.t5), -91});
                }
            }, 5L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.75
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity6New.this.t6, (byte) MainActivity6New.this.t7, (byte) MainActivity6New.this.t8, (byte) MainActivity6New.this.t9, (byte) MainActivity6New.this.t10, (byte) (((((MainActivity6New.this.t6 ^ 111) ^ MainActivity6New.this.t7) ^ MainActivity6New.this.t8) ^ MainActivity6New.this.t9) ^ MainActivity6New.this.t10), -91});
                }
            }, 50L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.76
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity6New.this.t11, (byte) MainActivity6New.this.t12, (byte) MainActivity6New.this.t13, (byte) MainActivity6New.this.t14, (byte) MainActivity6New.this.t15, (byte) (((((MainActivity6New.this.t11 ^ 110) ^ MainActivity6New.this.t12) ^ MainActivity6New.this.t13) ^ MainActivity6New.this.t14) ^ MainActivity6New.this.t15), -91});
                }
            }, 100L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.77
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity6New.this.t16, (byte) MainActivity6New.this.t17, (byte) MainActivity6New.this.t18, (byte) MainActivity6New.this.t19, (byte) MainActivity6New.this.t20, (byte) (((((MainActivity6New.this.t16 ^ 105) ^ MainActivity6New.this.t17) ^ MainActivity6New.this.t18) ^ MainActivity6New.this.t19) ^ MainActivity6New.this.t20), -91});
                }
            }, 150L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.78
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity6New.this.t21, (byte) MainActivity6New.this.t22, (byte) MainActivity6New.this.t23, (byte) MainActivity6New.this.t24, (byte) MainActivity6New.this.t25, (byte) (((((MainActivity6New.this.t21 ^ 104) ^ MainActivity6New.this.t22) ^ MainActivity6New.this.t23) ^ MainActivity6New.this.t24) ^ MainActivity6New.this.t25), -91});
                }
            }, 200L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.79
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity6New.this.t26, (byte) MainActivity6New.this.t27, (byte) MainActivity6New.this.t28, 0, 0, (byte) (((MainActivity6New.this.t26 ^ 107) ^ MainActivity6New.this.t27) ^ MainActivity6New.this.t28), -91});
                }
            }, 250L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.80
                @Override // java.lang.Runnable
                public void run() {
                    if (com.pusun.pusuntennis.utils.Util.getDeviceVersion(MainActivity6New.bleDevice) < 650101) {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
                    } else {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 8, 0, 0, 0, 0, 0, 111, -91});
                    }
                }
            }, 320L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.81
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                }
            }, 360L);
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.82
            @Override // java.lang.Runnable
            public void run() {
                MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
            }
        }, 260L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.83
            @Override // java.lang.Runnable
            public void run() {
                MainActivity6New.this.checkPower();
            }
        }, 190L);
        writeBleData(new byte[]{-86, 107, 0, 0, 0, 0, 0, 0, 107, -91});
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.85
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity6New.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.84
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity6New.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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
            case R.id.aidrill /* 2131361863 */:
                Intent intent = new Intent(this, (Class<?>) AIDrillActivity.class);
                intent.putExtra("fm", 4);
                startActivity(intent);
                break;
            case R.id.btn_ball /* 2131361912 */:
                if (this.blenoty.getText().toString().equals(getResources().getString(R.string.disconnected))) {
                    ShowHelper.toastShort(this, getResources().getString(R.string.check_connect));
                    break;
                } else if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                    this.isFaultOn = 0;
                    if (this.forbid == 1) {
                        ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.forbid_alert));
                        break;
                    } else {
                        int i = this.modeCate;
                        if (i == 0) {
                            this.savedefault.setVisibility(0);
                            int i2 = this.modeNum;
                            if (i2 == 1 || i2 == 7) {
                                AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.141
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                            }
                            if (this.modeNum == 4) {
                                writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                            }
                            int i3 = this.modeNum;
                            if (i3 == 5 || i3 == 8 || i3 == 9) {
                                AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.142
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 200L);
                            }
                            if (this.modeNum == 6) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.143
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i4;
                                        int unused = MainActivity6New.this.tagC;
                                        int i5 = 15;
                                        int i6 = 11;
                                        int i7 = MainActivity6New.this.tagC == 2 ? 15 : 11;
                                        int i8 = 13;
                                        if (MainActivity6New.this.tagC == 3) {
                                            i7 = 13;
                                            i4 = 1;
                                        } else {
                                            i4 = 3;
                                        }
                                        if (MainActivity6New.this.tagC == 4) {
                                            i4 = 5;
                                        } else {
                                            i8 = i7;
                                        }
                                        if (MainActivity6New.this.tagC == 5) {
                                            i4 = 5;
                                        } else {
                                            i6 = i8;
                                        }
                                        if (MainActivity6New.this.tagC == 6) {
                                            i4 = 1;
                                        } else {
                                            i5 = i6;
                                        }
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 1, (byte) i5, (byte) i4, 0, 0, 0, (byte) ((i5 ^ 108) ^ i4), -91});
                                    }
                                }, 5L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.144
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 50L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.145
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                    }
                                }, 80L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.146
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                }, 110L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.147
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                }, 140L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.148
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                    }
                                }, 170L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.149
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 250L);
                            }
                            if (this.modeNum == 0) {
                                ShowHelper.toastShort(this, getResources().getString(R.string.select_one_mode));
                            }
                        } else if (i == 2) {
                            if (this.selectPoints.size() == 0) {
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
                                for (int i4 = 0; i4 < this.selectPoints.size(); i4++) {
                                    if (i4 == 0) {
                                        this.t1 = this.selectPoints.get(0).intValue() + 20;
                                    }
                                    if (i4 == 1) {
                                        this.t2 = this.selectPoints.get(1).intValue() + 20;
                                    }
                                    if (i4 == 2) {
                                        this.t3 = this.selectPoints.get(2).intValue() + 20;
                                    }
                                    if (i4 == 3) {
                                        this.t4 = this.selectPoints.get(3).intValue() + 20;
                                    }
                                    if (i4 == 4) {
                                        this.t5 = this.selectPoints.get(4).intValue() + 20;
                                    }
                                    if (i4 == 5) {
                                        this.t6 = this.selectPoints.get(5).intValue() + 20;
                                    }
                                    if (i4 == 6) {
                                        this.t7 = this.selectPoints.get(6).intValue() + 20;
                                    }
                                    if (i4 == 7) {
                                        this.t8 = this.selectPoints.get(7).intValue() + 20;
                                    }
                                    if (i4 == 8) {
                                        this.t9 = this.selectPoints.get(8).intValue() + 20;
                                    }
                                    if (i4 == 9) {
                                        this.t10 = this.selectPoints.get(9).intValue() + 20;
                                    }
                                    if (i4 == 10) {
                                        this.t11 = this.selectPoints.get(10).intValue() + 20;
                                    }
                                    if (i4 == 11) {
                                        this.t12 = this.selectPoints.get(11).intValue() + 20;
                                    }
                                    if (i4 == 12) {
                                        this.t13 = this.selectPoints.get(12).intValue() + 20;
                                    }
                                    if (i4 == 13) {
                                        this.t14 = this.selectPoints.get(13).intValue() + 20;
                                    }
                                    if (i4 == 14) {
                                        this.t15 = this.selectPoints.get(14).intValue() + 20;
                                    }
                                    if (i4 == 15) {
                                        this.t16 = this.selectPoints.get(15).intValue() + 20;
                                    }
                                    if (i4 == 16) {
                                        this.t17 = this.selectPoints.get(16).intValue() + 20;
                                    }
                                    if (i4 == 17) {
                                        this.t18 = this.selectPoints.get(17).intValue() + 20;
                                    }
                                    if (i4 == 18) {
                                        this.t19 = this.selectPoints.get(18).intValue() + 20;
                                    }
                                    if (i4 == 19) {
                                        this.t20 = this.selectPoints.get(19).intValue() + 20;
                                    }
                                    if (i4 == 20) {
                                        this.t21 = this.selectPoints.get(20).intValue() + 20;
                                    }
                                    if (i4 == 21) {
                                        this.t22 = this.selectPoints.get(21).intValue() + 20;
                                    }
                                    if (i4 == 22) {
                                        this.t23 = this.selectPoints.get(22).intValue() + 20;
                                    }
                                    if (i4 == 23) {
                                        this.t24 = this.selectPoints.get(23).intValue() + 20;
                                    }
                                    if (i4 == 24) {
                                        this.t25 = this.selectPoints.get(24).intValue() + 20;
                                    }
                                    if (i4 == 25) {
                                        this.t26 = this.selectPoints.get(25).intValue() + 20;
                                    }
                                    if (i4 == 26) {
                                        this.t27 = this.selectPoints.get(26).intValue() + 20;
                                    }
                                    if (i4 == 27) {
                                        this.t28 = this.selectPoints.get(27).intValue() + 20;
                                    }
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.150
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity6New.this.t1, (byte) MainActivity6New.this.t2, (byte) MainActivity6New.this.t3, (byte) MainActivity6New.this.t4, (byte) MainActivity6New.this.t5, (byte) (((((MainActivity6New.this.t1 ^ 108) ^ MainActivity6New.this.t2) ^ MainActivity6New.this.t3) ^ MainActivity6New.this.t4) ^ MainActivity6New.this.t5), -91});
                                    }
                                }, 5L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.151
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity6New.this.t6, (byte) MainActivity6New.this.t7, (byte) MainActivity6New.this.t8, (byte) MainActivity6New.this.t9, (byte) MainActivity6New.this.t10, (byte) (((((MainActivity6New.this.t6 ^ 111) ^ MainActivity6New.this.t7) ^ MainActivity6New.this.t8) ^ MainActivity6New.this.t9) ^ MainActivity6New.this.t10), -91});
                                    }
                                }, 50L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.152
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity6New.this.t11, (byte) MainActivity6New.this.t12, (byte) MainActivity6New.this.t13, (byte) MainActivity6New.this.t14, (byte) MainActivity6New.this.t15, (byte) (((((MainActivity6New.this.t11 ^ 110) ^ MainActivity6New.this.t12) ^ MainActivity6New.this.t13) ^ MainActivity6New.this.t14) ^ MainActivity6New.this.t15), -91});
                                    }
                                }, 100L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.153
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity6New.this.t16, (byte) MainActivity6New.this.t17, (byte) MainActivity6New.this.t18, (byte) MainActivity6New.this.t19, (byte) MainActivity6New.this.t20, (byte) (((((MainActivity6New.this.t16 ^ 105) ^ MainActivity6New.this.t17) ^ MainActivity6New.this.t18) ^ MainActivity6New.this.t19) ^ MainActivity6New.this.t20), -91});
                                    }
                                }, 150L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.154
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity6New.this.t21, (byte) MainActivity6New.this.t22, (byte) MainActivity6New.this.t23, (byte) MainActivity6New.this.t24, (byte) MainActivity6New.this.t25, (byte) (((((MainActivity6New.this.t21 ^ 104) ^ MainActivity6New.this.t22) ^ MainActivity6New.this.t23) ^ MainActivity6New.this.t24) ^ MainActivity6New.this.t25), -91});
                                    }
                                }, 200L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.155
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity6New.this.t26, (byte) MainActivity6New.this.t27, (byte) MainActivity6New.this.t28, 0, 0, (byte) (((MainActivity6New.this.t26 ^ 107) ^ MainActivity6New.this.t27) ^ MainActivity6New.this.t28), -91});
                                    }
                                }, 250L);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.156
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
                                    }
                                }, 320L);
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
                            for (int i5 = 0; i5 < this.selectPoints.size(); i5++) {
                                if (i5 == 0) {
                                    this.t1 = this.reals[this.selectPoints.get(0).intValue() - 1];
                                }
                                if (i5 == 1) {
                                    this.t2 = this.reals[this.selectPoints.get(1).intValue() - 1];
                                }
                                if (i5 == 2) {
                                    this.t3 = this.reals[this.selectPoints.get(2).intValue() - 1];
                                }
                                if (i5 == 3) {
                                    this.t4 = this.reals[this.selectPoints.get(3).intValue() - 1];
                                }
                                if (i5 == 4) {
                                    this.t5 = this.reals[this.selectPoints.get(4).intValue() - 1];
                                }
                                if (i5 == 5) {
                                    this.t6 = this.reals[this.selectPoints.get(5).intValue() - 1];
                                }
                                if (i5 == 6) {
                                    this.t7 = this.reals[this.selectPoints.get(6).intValue() - 1];
                                }
                                if (i5 == 7) {
                                    this.t8 = this.reals[this.selectPoints.get(7).intValue() - 1];
                                }
                                if (i5 == 8) {
                                    this.t9 = this.reals[this.selectPoints.get(8).intValue() - 1];
                                }
                                if (i5 == 9) {
                                    this.t10 = this.reals[this.selectPoints.get(9).intValue() - 1];
                                }
                                if (i5 == 10) {
                                    this.t11 = this.reals[this.selectPoints.get(10).intValue() - 1];
                                }
                                if (i5 == 11) {
                                    this.t12 = this.reals[this.selectPoints.get(11).intValue() - 1];
                                }
                                if (i5 == 12) {
                                    this.t13 = this.reals[this.selectPoints.get(12).intValue() - 1];
                                }
                                if (i5 == 13) {
                                    this.t14 = this.reals[this.selectPoints.get(13).intValue() - 1];
                                }
                                if (i5 == 14) {
                                    this.t15 = this.reals[this.selectPoints.get(14).intValue() - 1];
                                }
                                if (i5 == 15) {
                                    this.t16 = this.reals[this.selectPoints.get(15).intValue() - 1];
                                }
                                if (i5 == 16) {
                                    this.t17 = this.reals[this.selectPoints.get(16).intValue() - 1];
                                }
                                if (i5 == 17) {
                                    this.t18 = this.reals[this.selectPoints.get(17).intValue() - 1];
                                }
                                if (i5 == 18) {
                                    this.t19 = this.reals[this.selectPoints.get(18).intValue() - 1];
                                }
                                if (i5 == 19) {
                                    this.t20 = this.reals[this.selectPoints.get(19).intValue() - 1];
                                }
                                if (i5 == 20) {
                                    this.t21 = this.reals[this.selectPoints.get(20).intValue() - 1];
                                }
                                if (i5 == 21) {
                                    this.t22 = this.reals[this.selectPoints.get(21).intValue() - 1];
                                }
                                if (i5 == 22) {
                                    this.t23 = this.reals[this.selectPoints.get(22).intValue() - 1];
                                }
                                if (i5 == 23) {
                                    this.t24 = this.reals[this.selectPoints.get(23).intValue() - 1];
                                }
                                if (i5 == 24) {
                                    this.t25 = this.reals[this.selectPoints.get(24).intValue() - 1];
                                }
                                if (i5 == 25) {
                                    this.t26 = this.reals[this.selectPoints.get(25).intValue() - 1];
                                }
                                if (i5 == 26) {
                                    this.t27 = this.reals[this.selectPoints.get(26).intValue() - 1];
                                }
                                if (i5 == 27) {
                                    this.t28 = this.reals[this.selectPoints.get(27).intValue() - 1];
                                }
                            }
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.157
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity6New.this.t1, (byte) MainActivity6New.this.t2, (byte) MainActivity6New.this.t3, (byte) MainActivity6New.this.t4, (byte) MainActivity6New.this.t5, (byte) (((((MainActivity6New.this.t1 ^ 108) ^ MainActivity6New.this.t2) ^ MainActivity6New.this.t3) ^ MainActivity6New.this.t4) ^ MainActivity6New.this.t5), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.158
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity6New.this.t6, (byte) MainActivity6New.this.t7, (byte) MainActivity6New.this.t8, (byte) MainActivity6New.this.t9, (byte) MainActivity6New.this.t10, (byte) (((((MainActivity6New.this.t6 ^ 111) ^ MainActivity6New.this.t7) ^ MainActivity6New.this.t8) ^ MainActivity6New.this.t9) ^ MainActivity6New.this.t10), -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.159
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity6New.this.t11, (byte) MainActivity6New.this.t12, (byte) MainActivity6New.this.t13, (byte) MainActivity6New.this.t14, (byte) MainActivity6New.this.t15, (byte) (((((MainActivity6New.this.t11 ^ 110) ^ MainActivity6New.this.t12) ^ MainActivity6New.this.t13) ^ MainActivity6New.this.t14) ^ MainActivity6New.this.t15), -91});
                                }
                            }, 100L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.160
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity6New.this.t16, (byte) MainActivity6New.this.t17, (byte) MainActivity6New.this.t18, (byte) MainActivity6New.this.t19, (byte) MainActivity6New.this.t20, (byte) (((((MainActivity6New.this.t16 ^ 105) ^ MainActivity6New.this.t17) ^ MainActivity6New.this.t18) ^ MainActivity6New.this.t19) ^ MainActivity6New.this.t20), -91});
                                }
                            }, 150L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.161
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity6New.this.t21, (byte) MainActivity6New.this.t22, (byte) MainActivity6New.this.t23, (byte) MainActivity6New.this.t24, (byte) MainActivity6New.this.t25, (byte) (((((MainActivity6New.this.t21 ^ 104) ^ MainActivity6New.this.t22) ^ MainActivity6New.this.t23) ^ MainActivity6New.this.t24) ^ MainActivity6New.this.t25), -91});
                                }
                            }, 200L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.162
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity6New.this.t26, (byte) MainActivity6New.this.t27, (byte) MainActivity6New.this.t28, 0, 0, (byte) (((MainActivity6New.this.t26 ^ 107) ^ MainActivity6New.this.t27) ^ MainActivity6New.this.t28), -91});
                                }
                            }, 250L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.163
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity6New.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 320L);
                        }
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.164
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 270L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.165
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.renewBasicData();
                            }
                        }, 300L);
                        this.btn_ball.setText(getResources().getString(R.string.stop));
                        this.signal_note.setText(getResources().getString(R.string.status_on));
                        this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                        this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                        break;
                    }
                } else {
                    this.stopMode = this.modeNum;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.166
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 260L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.167
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.checkPower();
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData5.a13[0];
                    short s = BasicData5.a13[1];
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
                int i6 = this.modeNum;
                if (i6 != 3) {
                    if (i6 == 5 || i6 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.96
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.97
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.velobar.setProgress(7.0f);
                    this.v_tv.setText("" + this.veloTins[6]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.98
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.99
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.100
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity6New.this.tagV == 1) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                    if (MainActivity6New.this.tagV == 2) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 3, 3, 8, Ascii.CR, 0, 0, 111, -91});
                                    }
                                    if (MainActivity6New.this.tagV == 3) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 3, 3, Ascii.CR, 0, 0, 0, 103, -91});
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
                int i7 = this.tagC + 1;
                this.tagC = i7;
                if (i7 > 2) {
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData5.a13[0];
                    short s4 = BasicData5.a13[1];
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
                int i8 = this.modeNum;
                if (i8 != 6) {
                    if (i8 == 5 || i8 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.106
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.107
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.velobar.setProgress(7.0f);
                    this.v_tv.setText("" + this.veloTins[6]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.108
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.109
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 250L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.110
                        @Override // java.lang.Runnable
                        public void run() {
                            int i9;
                            int unused = MainActivity6New.this.tagC;
                            int i10 = 15;
                            int i11 = 11;
                            int i12 = MainActivity6New.this.tagC == 2 ? 15 : 11;
                            int i13 = 13;
                            if (MainActivity6New.this.tagC == 3) {
                                i12 = 13;
                                i9 = 1;
                            } else {
                                i9 = 3;
                            }
                            if (MainActivity6New.this.tagC == 4) {
                                i9 = 5;
                            } else {
                                i13 = i12;
                            }
                            if (MainActivity6New.this.tagC == 5) {
                                i9 = 5;
                            } else {
                                i11 = i13;
                            }
                            if (MainActivity6New.this.tagC == 6) {
                                i9 = 1;
                            } else {
                                i10 = i11;
                            }
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 1, (byte) i10, (byte) i9, 0, 0, 0, (byte) ((i10 ^ 108) ^ i9), -91});
                        }
                    }, 5L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.111
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.112
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                        }
                    }, 80L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.113
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                        }
                    }, 110L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.114
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                        }
                    }, 140L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.115
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 170L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.116
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                int i9 = this.modeNum;
                if (i9 != 5) {
                    this.isNumDing = 0;
                    if (i9 != 5 && i9 != 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.101
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.102
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
                this.velobar.setProgress(4.0f);
                this.v_tv.setText("" + this.veloTins[3]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.103
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.velobar.getProgress();
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[3], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[3] ^ 99), -91});
                    }
                }, 50L);
                this.daoNameIng = "high";
                setDefaultMode("high");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.104
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 5) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.105
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.hit /* 2131362126 */:
                int i10 = this.tagHT + 1;
                this.tagHT = i10;
                if (i10 > 1) {
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i11 = this.tagHT;
                if (i11 == 1) {
                    this.lr = 1770;
                }
                if (i11 == 2) {
                    this.lr = 1250;
                }
                if (i11 == 3) {
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
                int i12 = this.modeNum;
                if (i12 != 7) {
                    if (i12 == 5 || i12 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.117
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.118
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.isNumDing = 0;
                    int i13 = this.modeNum;
                    if (i13 == 5 || i13 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.119
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.120
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
                this.velobar.setProgress(6.0f);
                this.v_tv.setText("" + this.veloTins[5]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.121
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[5], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[5] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.122
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.123
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                hideLR();
                this.lr = BasicData5.a13[0];
                short s11 = BasicData5.a13[1];
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
                int i14 = this.modeNum;
                if (i14 != 2) {
                    if (i14 == 5 || i14 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.91
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.92
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.velobar.setProgress(7.0f);
                    this.v_tv.setText("" + this.veloTins[6]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.93
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.94
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2 && this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.95
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity6New.this.tagH == 1) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                    if (MainActivity6New.this.tagH == 2) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 2, 6, 10, 0, 0, 0, 114, -91});
                                    }
                                    if (MainActivity6New.this.tagH == 3) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 110, -91});
                                    }
                                    if (MainActivity6New.this.tagH == 4) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 2, 7, 9, 0, 0, 0, 106, -91});
                                    }
                                    if (MainActivity6New.this.tagH == 5) {
                                        MainActivity6New.this.writeBleData(new byte[]{-86, 106, 2, 6, 8, 10, 0, 0, 96, -91});
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
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
                int i15 = this.modeNum;
                if (i15 != 9) {
                    this.isNumDing = 0;
                    if (i15 == 5 || i15 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.130
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.131
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
                this.velobar.setProgress(8.0f);
                this.v_tv.setText("" + this.veloTins[7]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.132
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.velobar.getProgress();
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[7], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[7] ^ 99), -91});
                    }
                }, 50L);
                this.rotatebar.setProgress(6.0f);
                this.r_tv.setText("6");
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.133
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 1, Ascii.RS, 0, 0, 0, 0, 125, -91});
                    }
                }, 75L);
                this.daoNameIng = "moon";
                setDefaultMode("moon");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.134
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 9) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.135
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                int i16 = this.modeNum;
                if (i16 != 8) {
                    this.isNumDing = 0;
                    if (i16 != 5 && i16 != 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.124
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.125
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
                showTvs(this.fx1);
                this.velobar.setProgress(1.0f);
                this.v_tv.setText("" + this.veloTins[0]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.126
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        if (MainActivity6New.this.velobar.getProgress() > 2) {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[0], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[0] ^ 99), -91});
                        }
                    }
                }, 50L);
                this.rotatebar.setProgress(0.0f);
                this.r_tv.setText("0");
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.127
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 99, -91});
                    }
                }, 75L);
                this.daoNameIng = "place";
                setDefaultMode("place");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.128
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 8) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.129
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(0);
                this.selectPoints.clear();
                showPoints();
                this.modeCate = 1;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.136
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
                        MainActivity6New.this.velobar.setProgress(7.0f);
                        MainActivity6New.this.v_tv.setText("" + MainActivity6New.this.veloTins[6]);
                    }
                }, 50L);
                renewBasicData();
                break;
            case R.id.self_point /* 2131362423 */:
                int i17 = this.tagFix + 1;
                this.tagFix = i17;
                if (i17 > 1) {
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i18 = this.tagFix;
                if (i18 == 1) {
                    this.lr = 1770;
                }
                if (i18 == 2) {
                    this.lr = 1320;
                }
                if (i18 == 3) {
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
                int i19 = this.modeNum;
                if (i19 != 1) {
                    this.isNumDing = 0;
                    if (i19 == 5 || i19 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.86
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.87
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
                this.velobar.setProgress(7.0f);
                this.v_tv.setText("" + this.veloTins[6]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.88
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.89
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.90
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 200L);
                    break;
                }
                break;
            case R.id.step /* 2131362542 */:
                startActivity(new Intent(this, (Class<?>) VaryDrillActivityPS.class));
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
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                this.group_cate.setVisibility(8);
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showTvs(this.tvids15);
                if (!this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    this.lr = BasicData5.a13[0];
                    short s20 = BasicData5.a13[1];
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
                int i20 = this.modeNum;
                if (i20 != 4) {
                    if (i20 == 5 || i20 == 8) {
                        ShowHelper.showProgressDialog(this, getResources().getString(R.string.change_point));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.137
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1300L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.138
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                            }
                        }, 1400L);
                    }
                    this.velobar.setProgress(7.0f);
                    this.v_tv.setText("" + this.veloTins[6]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.139
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity6New.this.veloNums[6], 0, 0, 0, 0, 0, (byte) (MainActivity6New.this.veloNums[6] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.140
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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

    /* JADX INFO: Access modifiers changed from: private */
    public void renewBasicData() {
        BasicData5.b3[20][0] = (short) (BasicData5.b3[1][0] + 90);
        BasicData5.b3[20][1] = BasicData5.b3[1][1];
        BasicData5.b3[20][2] = BasicData5.b3[1][2];
        BasicData5.b3[20][3] = BasicData5.b3[1][3];
        BasicData5.b3[21][0] = (short) (BasicData5.b3[3][0] - 90);
        BasicData5.b3[21][1] = BasicData5.b3[3][1];
        BasicData5.b3[21][2] = BasicData5.b3[3][2];
        BasicData5.b3[21][3] = BasicData5.b3[3][3];
        BasicData5.b3[22][0] = (short) (BasicData5.b3[6][0] + 90);
        BasicData5.b3[22][1] = BasicData5.b3[6][1];
        BasicData5.b3[22][2] = BasicData5.b3[6][2];
        BasicData5.b3[22][3] = BasicData5.b3[6][3];
        BasicData5.b3[23][0] = (short) (BasicData5.b3[8][0] - 90);
        BasicData5.b3[23][1] = BasicData5.b3[8][1];
        BasicData5.b3[23][2] = BasicData5.b3[8][2];
        BasicData5.b3[23][3] = BasicData5.b3[8][3];
        BasicData5.b3[24][0] = (short) (BasicData5.b3[11][0] + 90);
        BasicData5.b3[24][1] = BasicData5.b3[11][1];
        BasicData5.b3[24][2] = BasicData5.b3[11][2];
        BasicData5.b3[24][3] = BasicData5.b3[11][3];
        BasicData5.b3[25][0] = (short) (BasicData5.b3[13][0] - 90);
        BasicData5.b3[25][1] = BasicData5.b3[13][1];
        BasicData5.b3[25][2] = BasicData5.b3[13][2];
        BasicData5.b3[25][3] = BasicData5.b3[13][3];
        BasicData5.b3[26][0] = (short) (BasicData5.b3[16][0] + 90);
        BasicData5.b3[26][1] = BasicData5.b3[16][1];
        BasicData5.b3[26][2] = BasicData5.b3[16][2];
        BasicData5.b3[26][3] = BasicData5.b3[16][3];
        BasicData5.b3[27][0] = (short) (BasicData5.b3[18][0] - 90);
        BasicData5.b3[27][1] = BasicData5.b3[18][1];
        BasicData5.b3[27][2] = BasicData5.b3[18][2];
        BasicData5.b3[27][3] = BasicData5.b3[18][3];
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.168
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i = (MainActivity6New.this.valueSelect - MainActivity6New.this.baseHt) * 30;
                short s = 8;
                int length = BasicData5.b3.length - 8;
        while (length < BasicData5.b3.length) {
            final int final_length = length;
                    int i2 = length + 1;
                    short s2 = BasicData5.b3[length][0];
                    short s3 = BasicData5.b3[length][0];
                    short s4 = BasicData5.b3[length][1];
                    short s5 = BasicData5.b3[length][1];
                    final byte[] bArr = {-86, (byte) i2, (byte) (BasicData5.b3[length][0] >> s), (byte) BasicData5.b3[length][0], (byte) ((BasicData5.b3[length][1] - i) >> s), (byte) (BasicData5.b3[length][1] - i), (byte) (BasicData5.b3[length][2] >> s), (byte) BasicData5.b3[length][2], (byte) BasicData5.b3[length][3], -91};
                    AppLog.e("左右：" + ((int) BasicData5.b3[length][0]) + "上下：" + (BasicData5.b3[length][1] - i) + "byte:" + MainActivity6New.bytesToHexString(bArr));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.168.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_length + "条指令");
                            MainActivity6New.this.writeBleData(bArr);
                        }
                    }, (long) (length * 30));
                    length = i2;
                    s = 8;
                }
            }
        }, 80L);
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
            MainActivity6New.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity6New.this.ud > 1400) {
                    MainActivity6New.access$512(MainActivity6New.this, 30);
                }
                if (MainActivity6New.this.ud <= 1400) {
                    MainActivity6New.access$512(MainActivity6New.this, 100);
                }
                if (MainActivity6New.this.ud < 800) {
                    MainActivity6New.this.ud = 800;
                }
                if (MainActivity6New.this.ud > 1860) {
                    MainActivity6New.this.ud = 1860;
                }
                short s = (short) MainActivity6New.this.lr;
                if (MainActivity6New.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6New.this.ud;
                if (MainActivity6New.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                if (MainActivity6New.this.ud > 1410) {
                    MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                } else {
                    MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                }
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6New.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivity6New.this.ud <= 1400) {
                    MainActivity6New.access$520(MainActivity6New.this, 100);
                }
                if (MainActivity6New.this.ud > 1400) {
                    MainActivity6New.access$520(MainActivity6New.this, 30);
                }
                if (MainActivity6New.this.ud < 800) {
                    MainActivity6New.this.ud = 800;
                }
                if (MainActivity6New.this.ud > 1860) {
                    MainActivity6New.this.ud = 1860;
                }
                short s = (short) MainActivity6New.this.lr;
                if (MainActivity6New.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6New.this.ud;
                if (MainActivity6New.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                if (MainActivity6New.this.ud > 1410) {
                    MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                } else {
                    MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                }
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6New.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity6New.access$412(MainActivity6New.this, 30);
                if (MainActivity6New.this.lr < 1140) {
                    MainActivity6New.this.lr = 1140;
                }
                if (MainActivity6New.this.lr > 2040) {
                    MainActivity6New.this.lr = 2040;
                }
                short s = (short) MainActivity6New.this.lr;
                if (MainActivity6New.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6New.this.ud;
                if (MainActivity6New.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                if (MainActivity6New.this.ud > 1410) {
                    MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                } else {
                    MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity6New.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity6New.access$420(MainActivity6New.this, 30);
                if (MainActivity6New.this.lr < 1140) {
                    MainActivity6New.this.lr = 1140;
                }
                if (MainActivity6New.this.lr > 2040) {
                    MainActivity6New.this.lr = 2040;
                }
                short s = (short) MainActivity6New.this.lr;
                if (MainActivity6New.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity6New.this.ud;
                if (MainActivity6New.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity6New.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity6New.this.num_lr.setText("" + (74 - (MainActivity6New.this.lr / 30)));
                if (MainActivity6New.this.ud > 1410) {
                    MainActivity6New.this.num_ud.setText("" + (64 - (MainActivity6New.this.ud / 30)));
                } else {
                    MainActivity6New.this.num_ud.setText("" + (((1410 - MainActivity6New.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity6New.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity6New.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.169
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.170
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity6New.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivity6New.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivity6New.this.freq.getProgress());
                seleDao.setItem1(MainActivity6New.this.radioNum);
                seleDao.setItem2(MainActivity6New.this.valueSelect);
                seleDao.setVelo(MainActivity6New.this.velobar.getProgress());
                seleDao.setRote(MainActivity6New.this.rotatebar.getProgress());
                MainActivity6New.this.daoSession.insertOrReplace(seleDao);
                create.dismiss();
            }
        });
        create.show();
    }

    public void alert_dialog_input2() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.171
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity6New.172
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity6New mainActivity6New = MainActivity6New.this;
                    ShowHelper.toastShort(mainActivity6New, mainActivity6New.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity6New.this.daoSession.loadAll(VariDao.class);
                VariDao variDao = new VariDao();
                variDao.setDaoName("" + editText.getText().toString().trim());
                variDao.setSeles(MainActivity6New.this.select_dianwei.getText().toString().trim());
                variDao.setFreq1(BasicData5.b3[20][3]);
                variDao.setVelo1(BasicData5.b3[20][2]);
                variDao.setLr1(BasicData5.b3[20][0]);
                variDao.setUd1(BasicData5.b3[20][1]);
                variDao.setFreq2(BasicData5.b3[21][3]);
                variDao.setVelo2(BasicData5.b3[21][2]);
                variDao.setLr2(BasicData5.b3[21][0]);
                variDao.setUd2(BasicData5.b3[21][1]);
                variDao.setFreq3(BasicData5.b3[22][3]);
                variDao.setVelo3(BasicData5.b3[22][2]);
                variDao.setLr3(BasicData5.b3[22][0]);
                variDao.setUd3(BasicData5.b3[22][1]);
                variDao.setFreq4(BasicData5.b3[23][3]);
                variDao.setVelo4(BasicData5.b3[23][2]);
                variDao.setLr4(BasicData5.b3[23][0]);
                variDao.setUd4(BasicData5.b3[23][1]);
                variDao.setFreq5(BasicData5.b3[24][3]);
                variDao.setVelo5(BasicData5.b3[24][2]);
                variDao.setLr5(BasicData5.b3[24][0]);
                variDao.setUd5(BasicData5.b3[24][1]);
                variDao.setFreq6(BasicData5.b3[25][3]);
                variDao.setVelo6(BasicData5.b3[25][2]);
                variDao.setLr6(BasicData5.b3[25][0]);
                variDao.setUd6(BasicData5.b3[25][1]);
                MainActivity6New.this.daoSession.insertOrReplace(variDao);
                create.dismiss();
            }
        });
        create.show();
    }

    public static boolean isSystemChinese(Context context) {
        Locale locale;
        LocaleList locales;
        if (Build.VERSION.SDK_INT >= 24) {
            locales = context.getResources().getConfiguration().getLocales();
            locale = locales.get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return isChineseLocale(locale);
    }

    private static boolean isChineseLocale(Locale locale) {
        return locale.getLanguage().equals(new Locale("zh").getLanguage());
    }
}
