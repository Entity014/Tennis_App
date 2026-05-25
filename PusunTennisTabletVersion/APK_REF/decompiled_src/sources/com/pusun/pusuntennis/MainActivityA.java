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
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Ascii;
import com.kyleduo.switchbutton.SwitchButton;
import com.pusun.pusuntennis.utils.AppLog;
import com.pusun.pusuntennis.utils.BLEServiceParameters;
import com.pusun.pusuntennis.utils.BasicData9;
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
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivityA extends AppCompatActivity implements View.OnClickListener {
    private static final String FORBID_INFO = "forbid";
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static BleDevice bleDevice;
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
    private RelativeLayout w_ad;
    private TextView w_add;
    private TextView w_de;
    private TextView w_note;
    private TextView w_num;
    private Button whole;
    private List<Integer> selectPoints = new ArrayList();
    private int modeNum = 1;
    private int modeCate = 0;
    private int[] frequentNums = {88, 78, 68, 58, 48, 38, 33, 28, 23, 20};
    private int[] veloNums = {52, 54, 56, 58, 60, 63, 66, 69, 73, 78, 83, 92, 102};
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
    private int vari_point_num = 1;
    List<DefaultDao> defaultDaoList = new ArrayList();
    private int saveColor = 0;
    private String daoNameIng = "fix1";

    static /* synthetic */ int access$608(MainActivityA mainActivityA) {
        int i = mainActivityA.valueSelect;
        mainActivityA.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$610(MainActivityA mainActivityA) {
        int i = mainActivityA.valueSelect;
        mainActivityA.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$6308(MainActivityA mainActivityA) {
        int i = mainActivityA.connNum;
        mainActivityA.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$6908(MainActivityA mainActivityA) {
        int i = mainActivityA.isFaultOn;
        mainActivityA.isFaultOn = i + 1;
        return i;
    }

    static /* synthetic */ int access$712(MainActivityA mainActivityA, int i) {
        int i2 = mainActivityA.lr + i;
        mainActivityA.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$720(MainActivityA mainActivityA, int i) {
        int i2 = mainActivityA.lr - i;
        mainActivityA.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$812(MainActivityA mainActivityA, int i) {
        int i2 = mainActivityA.ud + i;
        mainActivityA.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$820(MainActivityA mainActivityA, int i) {
        int i2 = mainActivityA.ud - i;
        mainActivityA.ud = i2;
        return i2;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_activitya);
        EventBus.getDefault().register(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.density = displayMetrics.scaledDensity;
        bleDevice = (BleDevice) getIntent().getParcelableExtra("device");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivityA$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivityA.lambda$onCreate$0(view, windowInsetsCompat);
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
        this.w_note = (TextView) findViewById(R.id.w_note);
        this.w_de = (TextView) findViewById(R.id.w_de);
        this.w_add = (TextView) findViewById(R.id.w_add);
        this.w_num = (TextView) findViewById(R.id.w_num);
        this.w_ad = (RelativeLayout) findViewById(R.id.w_ad);
        this.w_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BasicData9.b3[5][0] < 2120) {
                    short[] sArr = BasicData9.b3[5];
                    sArr[0] = (short) (sArr[0] + 30);
                    BasicData9.b3[9][0] = (short) (r5[0] - 50);
                    MainActivityA.this.sendBaseData(1);
                    MainActivityA.this.w_num.setText("" + (Integer.valueOf(MainActivityA.this.w_num.getText().toString().trim()).intValue() + 1));
                    SharedPreferences.Editor edit = MainActivityA.this.getSharedPreferences("wide", 0).edit();
                    edit.putInt("wide", Integer.valueOf(MainActivityA.this.w_num.getText().toString().trim()).intValue());
                    edit.commit();
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                }
            }
        });
        this.w_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BasicData9.b3[5][0] > 1830) {
                    BasicData9.b3[5][0] = (short) (r5[0] - 30);
                    short[] sArr = BasicData9.b3[9];
                    sArr[0] = (short) (sArr[0] + 50);
                    MainActivityA.this.sendBaseData(1);
                    MainActivityA.this.w_num.setText("" + (Integer.valueOf(MainActivityA.this.w_num.getText().toString().trim()).intValue() - 1));
                    SharedPreferences.Editor edit = MainActivityA.this.getSharedPreferences("wide", 0).edit();
                    edit.putInt("wide", Integer.valueOf(MainActivityA.this.w_num.getText().toString().trim()).intValue());
                    edit.commit();
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                }
            }
        });
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
        Button button = (Button) findViewById(R.id.savedefault);
        this.savedefault = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivityA.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivityA.this.freq.getProgress());
                    defaultDao.setGrade(MainActivityA.this.valueSelect);
                    defaultDao.setItem2(MainActivityA.this.lr);
                    defaultDao.setItem3(MainActivityA.this.ud);
                    defaultDao.setVelo(MainActivityA.this.velobar.getProgress());
                    defaultDao.setRote(MainActivityA.this.rotatebar.getProgress());
                    if (MainActivityA.this.modeCate == 0) {
                        if (MainActivityA.this.modeNum != 1) {
                            if (MainActivityA.this.modeNum != 7) {
                                if (MainActivityA.this.modeNum != 5) {
                                    if (MainActivityA.this.modeNum != 4) {
                                        if (MainActivityA.this.modeNum != 2) {
                                            if (MainActivityA.this.modeNum != 3) {
                                                if (MainActivityA.this.modeNum != 6) {
                                                    if (MainActivityA.this.modeNum != 8) {
                                                        if (MainActivityA.this.modeNum == 9) {
                                                            defaultDao.setDaoName("moon");
                                                            defaultDao.setNumber(24L);
                                                            MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                                        }
                                                    } else {
                                                        defaultDao.setDaoName("place");
                                                        defaultDao.setNumber(23L);
                                                        MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                                    }
                                                } else {
                                                    defaultDao.setDaoName("cross" + MainActivityA.this.tagC);
                                                    if (MainActivityA.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivityA.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivityA.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivityA.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivityA.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivityA.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivityA.this.tagV);
                                                if (MainActivityA.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivityA.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivityA.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivityA.this.tagH);
                                            if (MainActivityA.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivityA.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivityA.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivityA.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivityA.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivityA.this.tagHT);
                                if (MainActivityA.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivityA.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivityA.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivityA.this.tagFix);
                            if (MainActivityA.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivityA.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivityA.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivityA.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.save_default_success));
                    MainActivityA.this.savedefault.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivityA.this.saveColor = 0;
                    return;
                }
                MainActivityA mainActivityA2 = MainActivityA.this;
                ShowHelper.toastShort(mainActivityA2, mainActivityA2.getResources().getString(R.string.no_change_default));
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
        this.frontde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][0] > 2190) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][0] = 2190;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[1] = (short) (sArr[1] + 30);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][1] > 1830) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][1] = 1830;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.frontadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData9.b3[MainActivityA.this.vari_point_num + 19][0] = (short) (r15[0] - 30);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][0] < 1260) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][0] = 1260;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData9.b3[MainActivityA.this.vari_point_num + 19][1] = (short) (r15[1] - 30);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][1] < 1110) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][1] = 1110;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] - 5);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][2] < 50) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][2] = 50;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[2] = (short) (sArr[2] + 5);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][2] > 100) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][2] = 100;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][3] < 25) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][3] = 25;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData9.b3[MainActivityA.this.vari_point_num + 19];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData9.b3[MainActivityA.this.vari_point_num + 19][3] > 65) {
                    BasicData9.b3[MainActivityA.this.vari_point_num + 19][3] = 65;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
                int i = MainActivityA.this.vari_point_num + 19;
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) (MainActivityA.this.vari_point_num + 20), (byte) (BasicData9.b3[i][0] >> 8), (byte) BasicData9.b3[i][0], (byte) (BasicData9.b3[i][1] >> 8), (byte) BasicData9.b3[i][1], (byte) (BasicData9.b3[i][2] >> 8), (byte) BasicData9.b3[i][2], (byte) BasicData9.b3[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.u_dian.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityA.this.dian_num.getText().toString().trim()).intValue();
                int intValue2 = Integer.valueOf(MainActivityA.this.l_r.getText().toString().trim()).intValue();
                int intValue3 = Integer.valueOf(MainActivityA.this.u_d.getText().toString().trim()).intValue();
                int intValue4 = Integer.valueOf(MainActivityA.this.s_d.getText().toString().trim()).intValue();
                MainActivityA.this.writeBleData(new byte[]{-86, (byte) intValue, (byte) (intValue2 >> 8), (byte) intValue2, (byte) (intValue3 >> 8), (byte) intValue3, (byte) (intValue4 >> 8), (byte) intValue4, (byte) Integer.valueOf(MainActivityA.this.interval.getText().toString().trim()).intValue(), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        this.lr = BasicData9.m13[0];
        this.ud = BasicData9.m13[1];
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
        Button button2 = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.stop_ball);
        this.stop_ball = button3;
        button3.setOnClickListener(this);
        if (Integer.valueOf(bleDevice.getName().toString().trim().substring(3, 9)).intValue() < 230712) {
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
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.13
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivityA.this.getUrlTxt();
            }
        }, 1000L);
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivityA.14
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinner_zu = (Spinner) findViewById(R.id.spinner_zu);
        this.spinner_zu.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.point_vari)));
        this.spinner_zu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivityA.15
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    MainActivityA.this.vari_point_num = 1;
                } else if (i == 1) {
                    MainActivityA.this.vari_point_num = 2;
                } else if (i == 2) {
                    MainActivityA.this.vari_point_num = 3;
                } else if (i == 3) {
                    MainActivityA.this.vari_point_num = 4;
                } else if (i == 4) {
                    MainActivityA.this.vari_point_num = 5;
                } else if (i == 5) {
                    MainActivityA.this.vari_point_num = 6;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.showSelectPoint(mainActivityA.vari_point_num);
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
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityA.16
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("up:1");
                    if (MainActivityA.this.ud >= 1410) {
                        MainActivityA.access$812(MainActivityA.this, 30);
                    }
                    if (MainActivityA.this.ud < 1410) {
                        MainActivityA.access$812(MainActivityA.this, 50);
                    }
                    if (MainActivityA.this.ud < 910) {
                        MainActivityA.this.ud = 910;
                    }
                    if (MainActivityA.this.ud > 1830) {
                        MainActivityA.this.ud = 1830;
                    }
                    short s = (short) MainActivityA.this.lr;
                    if (MainActivityA.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityA.this.ud;
                    if (MainActivityA.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                    if (MainActivityA.this.ud >= 1410) {
                        MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                    } else {
                        MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.16.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivityA.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivityA.this.isTouch = false;
                    if (MainActivityA.this.timeCount1 != null) {
                        MainActivityA.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.16.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityA.this.timeCount1 != null) {
                                MainActivityA.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityA.17
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("down:1");
                    if (MainActivityA.this.ud <= 1410) {
                        MainActivityA.access$820(MainActivityA.this, 50);
                    }
                    if (MainActivityA.this.ud > 1410) {
                        MainActivityA.access$820(MainActivityA.this, 30);
                    }
                    if (MainActivityA.this.ud < 910) {
                        MainActivityA.this.ud = 910;
                    }
                    if (MainActivityA.this.ud > 1830) {
                        MainActivityA.this.ud = 1830;
                    }
                    short s = (short) MainActivityA.this.lr;
                    if (MainActivityA.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityA.this.ud;
                    if (MainActivityA.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                    if (MainActivityA.this.ud > 1410) {
                        MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                    } else {
                        MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.17.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivityA.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityA.this.isTouch = false;
                    if (MainActivityA.this.timeCount2 != null) {
                        MainActivityA.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.17.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityA.this.timeCount2 != null) {
                                MainActivityA.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityA.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("left:1");
                    MainActivityA.access$712(MainActivityA.this, 30);
                    if (MainActivityA.this.lr < 1260) {
                        MainActivityA.this.lr = 1260;
                    }
                    if (MainActivityA.this.lr > 2190) {
                        MainActivityA.this.lr = 2190;
                    }
                    short s = (short) MainActivityA.this.lr;
                    if (MainActivityA.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityA.this.ud;
                    if (MainActivityA.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                    if (MainActivityA.this.ud > 1410) {
                        MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                    } else {
                        MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.18.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.18.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivityA.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityA.this.isTouch = false;
                    if (MainActivityA.this.timeCount3 != null) {
                        MainActivityA.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.18.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityA.this.timeCount3 != null) {
                                MainActivityA.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityA.19
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    if (Util.isFastClick()) {
                        return false;
                    }
                    AppLog.e("right:1");
                    MainActivityA.access$720(MainActivityA.this, 30);
                    if (MainActivityA.this.lr < 1260) {
                        MainActivityA.this.lr = 1260;
                    }
                    if (MainActivityA.this.lr > 2190) {
                        MainActivityA.this.lr = 2190;
                    }
                    short s = (short) MainActivityA.this.lr;
                    if (MainActivityA.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityA.this.ud;
                    if (MainActivityA.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                    if (MainActivityA.this.ud > 1410) {
                        MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                    } else {
                        MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.19.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.19.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                        }
                    }, 1L);
                    MainActivityA.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityA.this.isTouch = false;
                    if (MainActivityA.this.timeCount4 != null) {
                        MainActivityA.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.19.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityA.this.timeCount4 != null) {
                                MainActivityA.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityA.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityA.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityA.22
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivityA.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.22.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivityA.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityA.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivityA.this.f_tv.setText("" + i);
                    MainActivityA.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.23.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityA.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityA.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    MainActivityA.this.f_tv.setText("" + i);
                    MainActivityA.this.freq.setProgress((float) i);
                    MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[intValue], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[intValue] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.24.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityA.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityA.25
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                final int progress = indicatorSeekBar3.getProgress();
                if (MainActivityA.this.modeCate == 0 && MainActivityA.this.modeNum == 8) {
                    indicatorSeekBar3.setProgress(0.0f);
                    progress = 0;
                }
                if (progress != 0 && MainActivityA.this.velobar.getProgress() < 5) {
                    MainActivityA.this.velobar.setProgress(5.0f);
                    MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[4] ^ 99), -91});
                    MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.25.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.25.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.25.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityA.this.r_tv.setText("" + progress);
                MainActivityA.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityA.this.rotatebar.getProgress();
                if (progress > -6) {
                    final int i = progress - 1;
                    if (MainActivityA.this.modeCate == 0 && MainActivityA.this.modeNum == 8) {
                        MainActivityA.this.rotatebar.setProgress(0.0f);
                        i = 0;
                    }
                    if (i != 0 && MainActivityA.this.velobar.getProgress() < 5) {
                        MainActivityA.this.velobar.setProgress(5.0f);
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[4] ^ 99), -91});
                        MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityA.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.26.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.26.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityA.this.r_tv.setText("" + i);
                    MainActivityA.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityA.this.rotatebar.getProgress();
                if (progress < 6) {
                    final int i = progress + 1;
                    if (MainActivityA.this.modeCate == 0 && MainActivityA.this.modeNum == 8) {
                        MainActivityA.this.rotatebar.setProgress(0.0f);
                        i = 0;
                    }
                    if (i != 0 && MainActivityA.this.velobar.getProgress() < 5) {
                        MainActivityA.this.velobar.setProgress(5.0f);
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[4] ^ 99), -91});
                        MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.27.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityA.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.27.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.27.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityA.this.r_tv.setText("" + i);
                    MainActivityA.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityA.28
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                if (MainActivityA.this.forbid == 1) {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.showAlertDialog(mainActivityA, mainActivityA.getResources().getString(R.string.alert), MainActivityA.this.getResources().getString(R.string.forbid_alert));
                    return;
                }
                int progress = indicatorSeekBar4.getProgress();
                TextView textView12 = MainActivityA.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivityA.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 4 && MainActivityA.this.rotatebar.getProgress() != 0) {
                    MainActivityA.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                    MainActivityA.this.rotatebar.setProgress(0.0f);
                    MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.28.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivityA.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.forbid != 1) {
                    int progress = MainActivityA.this.velobar.getProgress();
                    if (progress > 1) {
                        int i = progress - 1;
                        TextView textView12 = MainActivityA.this.v_tv;
                        StringBuilder sb = new StringBuilder("");
                        int i2 = progress - 2;
                        sb.append(MainActivityA.this.veloTins[i2]);
                        textView12.setText(sb.toString());
                        MainActivityA.this.velobar.setProgress(i);
                        if (i < 4 && MainActivityA.this.rotatebar.getProgress() != 0) {
                            MainActivityA.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                            MainActivityA.this.rotatebar.setProgress(0.0f);
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[i2] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.29.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivityA.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.showAlertDialog(mainActivityA, mainActivityA.getResources().getString(R.string.alert), MainActivityA.this.getResources().getString(R.string.forbid_alert));
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.forbid != 1) {
                    int progress = MainActivityA.this.velobar.getProgress();
                    if (progress < 13) {
                        int i = progress + 1;
                        MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[progress]);
                        MainActivityA.this.velobar.setProgress((float) i);
                        if (i < 4 && MainActivityA.this.rotatebar.getProgress() != 0) {
                            MainActivityA.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                            MainActivityA.this.rotatebar.setProgress(0.0f);
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[progress] ^ 99), -91});
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.30.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 100L);
                        MainActivityA.this.checkIfUpdate();
                        return;
                    }
                    return;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.showAlertDialog(mainActivityA, mainActivityA.getResources().getString(R.string.alert), MainActivityA.this.getResources().getString(R.string.forbid_alert));
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
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityA.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivityA.this.self.setVisibility(8);
                    MainActivityA.this.group.setVisibility(0);
                    MainActivityA.this.points.setVisibility(0);
                    MainActivityA.this.hintpoints.setVisibility(0);
                    MainActivityA.this.tennipic2.setVisibility(8);
                    MainActivityA.this.tennipic3.setVisibility(8);
                    MainActivityA.this.tennipic4.setVisibility(8);
                    MainActivityA.this.tennipic5.setVisibility(8);
                    MainActivityA.this.fourbtn.setVisibility(8);
                    MainActivityA.this.bg_four.setVisibility(8);
                    MainActivityA.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivityA.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivityA.this.density * 36.0f);
                    MainActivityA.this.start_layout.setLayoutParams(layoutParams);
                    MainActivityA.this.start_layout.setGravity(17);
                    MainActivityA.this.modeCate = 1;
                    return;
                }
                MainActivityA.this.self.setVisibility(0);
                MainActivityA.this.group.setVisibility(8);
                MainActivityA.this.points.setVisibility(8);
                MainActivityA.this.hintpoints.setVisibility(8);
                MainActivityA.this.tennipic2.setVisibility(0);
                MainActivityA.this.bg_input.setVisibility(8);
                MainActivityA.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivityA.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivityA.this.density * 90.0f);
                MainActivityA.this.start_layout.setLayoutParams(layoutParams2);
                MainActivityA.this.start_layout.setGravity(83);
                MainActivityA.this.fourbtn.setVisibility(0);
                MainActivityA.this.bg_four.setVisibility(0);
                if (MainActivityA.this.modeNum == 2) {
                    MainActivityA.this.tennipic3.setVisibility(0);
                    MainActivityA.this.tennipic4.setVisibility(8);
                    MainActivityA.this.tennipic5.setVisibility(8);
                }
                if (MainActivityA.this.modeNum == 3) {
                    MainActivityA.this.tennipic3.setVisibility(8);
                    MainActivityA.this.tennipic4.setVisibility(0);
                    MainActivityA.this.tennipic5.setVisibility(8);
                }
                if (MainActivityA.this.modeNum == 4) {
                    MainActivityA.this.tennipic3.setVisibility(8);
                    MainActivityA.this.tennipic4.setVisibility(8);
                    MainActivityA.this.tennipic5.setVisibility(0);
                }
                MainActivityA.this.modeCate = 0;
            }
        });
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.blenoty.getText().toString().trim().contains(MainActivityA.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivityA.this.checkPermissions();
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivityA.this.blenoty.setText(MainActivityA.this.getResources().getString(R.string.disconnected));
                    MainActivityA.this.blenoty.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityA.33
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivityA.this.radioNum = 0;
                        MainActivityA.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivityA.this.radioNum = 1;
                        MainActivityA.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivityA.this.radioNum = 2;
                        MainActivityA.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivityA.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivityA.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivityA.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivityA.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivityA.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityA.34.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivityA.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivityA.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivityA.this, MainActivityA.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_route_name));
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivityA.this.daoSession.loadAll(VariDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivityA.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivityA.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivityA.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivityA.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityA.35.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivityA.this.daoSession.loadAll(VariDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((VariDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivityA.this.daoSession.delete((VariDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivityA.this, MainActivityA.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.select_dianwei.getText() != null && !MainActivityA.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivityA.this.alert_dialog_input();
                } else {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityA.this.select_dianwei.getText() != null && !MainActivityA.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivityA.this.alert_dialog_input2();
                } else {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass38());
        this.lastpoints2.setOnClickListener(new AnonymousClass39());
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.valueSelect = Integer.parseInt(mainActivityA.value_h.getText().toString().trim());
                MainActivityA.access$608(MainActivityA.this);
                if (MainActivityA.this.valueSelect > 13) {
                    MainActivityA.this.valueSelect = 13;
                    return;
                }
                MainActivityA mainActivityA2 = MainActivityA.this;
                ShowHelper.showProgressDialog(mainActivityA2, mainActivityA2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.40.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.40.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivityA.this.value_h.setText("" + MainActivityA.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.40.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.40.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivityA.this.checkIfUpdate();
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.valueSelect = Integer.parseInt(mainActivityA.value_h.getText().toString().trim());
                MainActivityA.access$610(MainActivityA.this);
                if (MainActivityA.this.valueSelect < 1) {
                    MainActivityA.this.valueSelect = 1;
                    return;
                }
                MainActivityA mainActivityA2 = MainActivityA.this;
                ShowHelper.showProgressDialog(mainActivityA2, mainActivityA2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.41.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1000L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.41.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1100L);
                MainActivityA.this.value_h.setText("" + MainActivityA.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.41.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 5L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.41.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivityA.this.checkIfUpdate();
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

    /* renamed from: com.pusun.pusuntennis.MainActivityA$38, reason: invalid class name */
    class AnonymousClass38 implements View.OnClickListener {
        AnonymousClass38() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivityA.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityA.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityA.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivityA.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityA.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityA.38.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivityA.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivityA.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivityA.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivityA.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivityA.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivityA.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivityA.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityA.this.freq.getProgress() - 1;
                                        MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivityA.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityA.this.velobar.getProgress() - 1;
                                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivityA.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivityA.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivityA.this.valueSelect = item2;
                                    MainActivityA.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.38.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityA.this.sendBaseData(1);
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
            MainActivityA mainActivityA = MainActivityA.this;
            ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_route_name));
        }
    }

    /* renamed from: com.pusun.pusuntennis.MainActivityA$39, reason: invalid class name */
    class AnonymousClass39 implements View.OnClickListener {
        AnonymousClass39() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivityA.this.daoSession.loadAll(VariDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((VariDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityA.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityA.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivityA.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityA.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new AnonymousClass1());
                optionPicker.show();
                return;
            }
            MainActivityA mainActivityA = MainActivityA.this;
            ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.no_route_name));
        }

        /* renamed from: com.pusun.pusuntennis.MainActivityA$39$1, reason: invalid class name */
        class AnonymousClass1 implements OptionPicker.OnOptionPickListener {
            AnonymousClass1() {
            }

            @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
            public void onOptionPicked(String str) {
                new ArrayList();
                List loadAll = MainActivityA.this.daoSession.loadAll(VariDao.class);
                for (int i = 0; i < loadAll.size(); i++) {
                    if (((VariDao) loadAll.get(i)).getDaoName().equals(str)) {
                        MainActivityA.this.select_dianwei.setText(((VariDao) loadAll.get(i)).getSeles());
                        String[] split = ((VariDao) loadAll.get(i)).getSeles().split(",");
                        MainActivityA.this.selectPoints.clear();
                        for (String str2 : split) {
                            MainActivityA.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                        MainActivityA.this.showPoints();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.39.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        BasicData9.b3[20][0] = (short) ((VariDao) loadAll.get(i)).getLr1();
                        BasicData9.b3[20][1] = (short) ((VariDao) loadAll.get(i)).getUd1();
                        BasicData9.b3[20][2] = (short) ((VariDao) loadAll.get(i)).getVelo1();
                        BasicData9.b3[20][3] = (short) ((VariDao) loadAll.get(i)).getFreq1();
                        BasicData9.b3[21][0] = (short) ((VariDao) loadAll.get(i)).getLr2();
                        BasicData9.b3[21][1] = (short) ((VariDao) loadAll.get(i)).getUd2();
                        BasicData9.b3[21][2] = (short) ((VariDao) loadAll.get(i)).getVelo2();
                        BasicData9.b3[21][3] = (short) ((VariDao) loadAll.get(i)).getFreq2();
                        BasicData9.b3[22][0] = (short) ((VariDao) loadAll.get(i)).getLr3();
                        BasicData9.b3[22][1] = (short) ((VariDao) loadAll.get(i)).getUd3();
                        BasicData9.b3[22][2] = (short) ((VariDao) loadAll.get(i)).getVelo3();
                        BasicData9.b3[22][3] = (short) ((VariDao) loadAll.get(i)).getFreq3();
                        BasicData9.b3[23][0] = (short) ((VariDao) loadAll.get(i)).getLr4();
                        BasicData9.b3[23][1] = (short) ((VariDao) loadAll.get(i)).getUd4();
                        BasicData9.b3[23][2] = (short) ((VariDao) loadAll.get(i)).getVelo4();
                        BasicData9.b3[23][3] = (short) ((VariDao) loadAll.get(i)).getFreq4();
                        BasicData9.b3[24][0] = (short) ((VariDao) loadAll.get(i)).getLr5();
                        BasicData9.b3[24][1] = (short) ((VariDao) loadAll.get(i)).getUd5();
                        BasicData9.b3[24][2] = (short) ((VariDao) loadAll.get(i)).getVelo5();
                        BasicData9.b3[24][3] = (short) ((VariDao) loadAll.get(i)).getFreq5();
                        BasicData9.b3[25][0] = (short) ((VariDao) loadAll.get(i)).getLr6();
                        BasicData9.b3[25][1] = (short) ((VariDao) loadAll.get(i)).getUd6();
                        BasicData9.b3[25][2] = (short) ((VariDao) loadAll.get(i)).getVelo6();
                        BasicData9.b3[25][3] = (short) ((VariDao) loadAll.get(i)).getFreq6();
                        MainActivityA.this.showSelectPoint(MainActivityA.this.vari_point_num);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.39.1.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                final int length = BasicData9.b3.length - 6;
                                while (length < BasicData9.b3.length) {
                                    int i2 = length + 1;
                                    short s = BasicData9.b3[length][0];
                                    short s2 = BasicData9.b3[length][0];
                                    short s3 = BasicData9.b3[length][1];
                                    short s4 = BasicData9.b3[length][1];
                                    final byte[] bArr = {-86, (byte) i2, (byte) (BasicData9.b3[length][0] >> 8), (byte) BasicData9.b3[length][0], (byte) (BasicData9.b3[length][1] >> 8), (byte) BasicData9.b3[length][1], (byte) (BasicData9.b3[length][2] >> 8), (byte) BasicData9.b3[length][2], (byte) BasicData9.b3[length][3], -91};
                                    AppLog.e("左右：" + ((int) BasicData9.b3[length][0]) + "上下：" + ((int) BasicData9.b3[length][1]) + "byte:" + MainActivityA.bytesToHexString(bArr));
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.39.1.2.1
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            AppLog.e("第" + length + "条指令");
                                            MainActivityA.this.writeBleData(bArr);
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
        sb.append(74 - (BasicData9.b3[i2][0] / 30));
        textView.setText(sb.toString());
        this.backvalue2.setText("" + (64 - (BasicData9.b3[i2][1] / 30)));
        TextView textView2 = this.front_m_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData9.b3[i2][2] - 30);
        textView2.setText(sb2.toString());
        TextView textView3 = this.back_m_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData9.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUrlTxt() {
        new Thread(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.42
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL("https://www.pusuntech.com/Download/stop.txt").openStream()));
                    final String str = "";
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            str = str + readLine;
                            AppLog.e("" + readLine);
                        } else {
                            bufferedReader.close();
                            Looper.prepare();
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.42.1
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    String[] split = str.split(";");
                                    int i = 0;
                                    while (true) {
                                        if (i < split.length) {
                                            if (MainActivityA.bleDevice.getName().toString().trim().equals(split[i].toString().trim())) {
                                                ShowHelper.showAlertDialog(MainActivityA.this, MainActivityA.this.getResources().getString(R.string.alert), MainActivityA.this.getResources().getString(R.string.forbid_alert));
                                                SharedPreferences.Editor edit = MainActivityA.this.getSharedPreferences(MainActivityA.FORBID_INFO, 0).edit();
                                                edit.putInt(MainActivityA.FORBID_INFO, 1);
                                                edit.commit();
                                                SharedPreferences sharedPreferences = MainActivityA.this.getSharedPreferences(MainActivityA.FORBID_INFO, 0);
                                                AppLog.e("" + sharedPreferences.getInt(MainActivityA.FORBID_INFO, 0));
                                                MainActivityA.this.forbid = sharedPreferences.getInt(MainActivityA.FORBID_INFO, 0);
                                                break;
                                            }
                                            i++;
                                        } else {
                                            SharedPreferences.Editor edit2 = MainActivityA.this.getSharedPreferences(MainActivityA.FORBID_INFO, 0).edit();
                                            edit2.putInt(MainActivityA.FORBID_INFO, 0);
                                            edit2.commit();
                                            SharedPreferences sharedPreferences2 = MainActivityA.this.getSharedPreferences(MainActivityA.FORBID_INFO, 0);
                                            AppLog.e("" + sharedPreferences2.getInt(MainActivityA.FORBID_INFO, 0));
                                            MainActivityA.this.forbid = sharedPreferences2.getInt(MainActivityA.FORBID_INFO, 0);
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
        final int i = 0;
        while (i < BasicData9.b2.length) {
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData9.b2[i][0] >> 8), (byte) BasicData9.b2[i][0], (byte) (BasicData9.b2[i][1] >> 8), (byte) BasicData9.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData9.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData9.b2[i][0])) ^ ((byte) (BasicData9.b2[i][1] >> 8))) ^ ((byte) BasicData9.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData9.b2[i][0]) + "上下：" + ((int) BasicData9.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.43
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + i + "条指令");
                    MainActivityA.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.44
            @Override // java.lang.Runnable
            public synchronized void run() {
                final int i3 = 10;
                while (i3 < BasicData9.b2.length) {
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData9.b2[i3][0] >> 8), (byte) BasicData9.b2[i3][0], (byte) (BasicData9.b2[i3][1] >> 8), (byte) BasicData9.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData9.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData9.b2[i3][0])) ^ ((byte) (BasicData9.b2[i3][1] >> 8))) ^ ((byte) BasicData9.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData9.b2[i3][0]) + "上下：" + ((int) BasicData9.b2[i3][1]) + "byte:" + MainActivityA.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.44.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + i3 + "条指令");
                            MainActivityA.this.writeBleData(bArr2);
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
            final int i3 = (this.valueSelect - this.baseHt) * 30;
            final int i4 = 0;
            while (i4 < BasicData9.b3.length - i2) {
                int i5 = i4 + 1;
                short s = BasicData9.b3[i4][0];
                short s2 = BasicData9.b3[i4][0];
                short s3 = BasicData9.b3[i4][c3];
                short s4 = BasicData9.b3[i4][c3];
                final byte[] bArr = {-86, (byte) i5, (byte) (BasicData9.b3[i4][0] >> 8), (byte) BasicData9.b3[i4][0], (byte) ((BasicData9.b3[i4][c3] - i3) >> 8), (byte) (BasicData9.b3[i4][c3] - i3), (byte) (BasicData9.b3[i4][c2] >> 8), (byte) BasicData9.b3[i4][c2], (byte) BasicData9.b3[i4][c], -91};
                AppLog.e("左右：" + ((int) BasicData9.b3[i4][0]) + "上下：" + (BasicData9.b3[i4][1] - i3) + "byte:" + bytesToHexString(bArr));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.45
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + i4 + "条指令");
                        MainActivityA.this.writeBleData(bArr);
                    }
                }, (long) (i4 * 10));
                i4 = i5;
                c3 = 1;
                c = 3;
                i2 = 6;
                c2 = 2;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.46
                @Override // java.lang.Runnable
                public synchronized void run() {
                    final int i6 = 10;
                    while (i6 < BasicData9.b3.length - 6) {
                        int i7 = i6 + 1;
                        short s5 = BasicData9.b3[i6][0];
                        short s6 = BasicData9.b3[i6][0];
                        short s7 = BasicData9.b3[i6][1];
                        short s8 = BasicData9.b3[i6][1];
                        final byte[] bArr2 = {-86, (byte) i7, (byte) (BasicData9.b3[i6][0] >> 8), (byte) BasicData9.b3[i6][0], (byte) ((BasicData9.b3[i6][1] - i3) >> 8), (byte) (BasicData9.b3[i6][1] - i3), (byte) (BasicData9.b3[i6][2] >> 8), (byte) BasicData9.b3[i6][2], (byte) BasicData9.b3[i6][3], -91};
                        AppLog.e("左右：" + ((int) BasicData9.b3[i6][0]) + "上下：" + (BasicData9.b3[i6][1] - i3) + "byte:" + MainActivityA.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.46.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + i6 + "条指令");
                                MainActivityA.this.writeBleData(bArr2);
                            }
                        }, (long) (i6 * 10));
                        i6 = i7;
                    }
                }
            }, 900L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.47
                @Override // java.lang.Runnable
                public synchronized void run() {
                    final int length = BasicData9.b3.length - 6;
                    while (length < BasicData9.b3.length) {
                        int i6 = length + 1;
                        short s5 = BasicData9.b3[length][0];
                        short s6 = BasicData9.b3[length][0];
                        short s7 = BasicData9.b3[length][1];
                        short s8 = BasicData9.b3[length][1];
                        final byte[] bArr2 = {-86, (byte) i6, (byte) (BasicData9.b3[length][0] >> 8), (byte) BasicData9.b3[length][0], (byte) (BasicData9.b3[length][1] >> 8), (byte) BasicData9.b3[length][1], (byte) (BasicData9.b3[length][2] >> 8), (byte) BasicData9.b3[length][2], (byte) BasicData9.b3[length][3], -91};
                        AppLog.e("左右：" + ((int) BasicData9.b3[length][0]) + "上下：" + ((int) BasicData9.b3[length][1]) + "byte:" + MainActivityA.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.47.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + length + "条指令");
                                MainActivityA.this.writeBleData(bArr2);
                            }
                        }, (long) (length * 10));
                        length = i6;
                    }
                }
            }, 1200L);
            return;
        }
        final int i6 = 0;
        while (i6 < BasicData9.b4.length) {
            int i7 = i6 + 1;
            byte b = (byte) i7;
            final byte[] bArr2 = {-86, b, (byte) (BasicData9.b4[i6][0] >> 8), (byte) BasicData9.b4[i6][0], (byte) (BasicData9.b4[i6][1] >> 8), (byte) BasicData9.b4[i6][1], 0, 0, (byte) ((((((byte) (BasicData9.b4[i6][0] >> 8)) ^ b) ^ ((byte) BasicData9.b4[i6][0])) ^ ((byte) (BasicData9.b4[i6][1] >> 8))) ^ ((byte) BasicData9.b4[i6][1])), -91};
            AppLog.e("左右：" + ((int) BasicData9.b4[i6][0]) + "上下：" + ((int) BasicData9.b4[i6][1]) + "byte:" + bytesToHexString(bArr2));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.48
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + i6 + "条指令");
                    MainActivityA.this.writeBleData(bArr2);
                }
            }, (long) (i6 * 10));
            i6 = i7;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.49
            @Override // java.lang.Runnable
            public synchronized void run() {
                final int i8 = 10;
                while (i8 < BasicData9.b4.length) {
                    int i9 = i8 + 1;
                    byte b2 = (byte) i9;
                    final byte[] bArr3 = {-86, b2, (byte) (BasicData9.b4[i8][0] >> 8), (byte) BasicData9.b4[i8][0], (byte) (BasicData9.b4[i8][1] >> 8), (byte) BasicData9.b4[i8][1], 0, 0, (byte) ((((((byte) (BasicData9.b4[i8][0] >> 8)) ^ b2) ^ ((byte) BasicData9.b4[i8][0])) ^ ((byte) (BasicData9.b4[i8][1] >> 8))) ^ ((byte) BasicData9.b4[i8][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData9.b4[i8][0]) + "上下：" + ((int) BasicData9.b4[i8][1]) + "byte:" + MainActivityA.bytesToHexString(bArr3));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.49.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + i8 + "条指令");
                            MainActivityA.this.writeBleData(bArr3);
                        }
                    }, (long) (i8 * 10));
                    i8 = i9;
                }
            }
        }, 900L);
    }

    private void sendBaseDataMore() {
        for (final int i = 0; i < BasicData9.b5.length; i++) {
            byte b = (byte) (i + 19);
            final byte[] bArr = {-86, b, (byte) (BasicData9.b5[i][0] >> 8), (byte) BasicData9.b5[i][0], (byte) (BasicData9.b5[i][1] >> 8), (byte) BasicData9.b5[i][1], 0, 0, (byte) ((((((byte) (BasicData9.b5[i][0] >> 8)) ^ b) ^ ((byte) BasicData9.b5[i][0])) ^ ((byte) (BasicData9.b5[i][1] >> 8))) ^ ((byte) BasicData9.b5[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData9.b5[i][0]) + "上下：" + ((int) BasicData9.b5[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.50
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + i + "条指令");
                    MainActivityA.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.51
            @Override // java.lang.Runnable
            public synchronized void run() {
                final int i2 = 0;
                while (i2 < BasicData9.b2.length) {
                    int i3 = i2 + 1;
                    byte b2 = (byte) i3;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData9.b2[i2][0] >> 8), (byte) BasicData9.b2[i2][0], (byte) (BasicData9.b2[i2][1] >> 8), (byte) BasicData9.b2[i2][1], 0, 0, (byte) ((((((byte) (BasicData9.b2[i2][0] >> 8)) ^ b2) ^ ((byte) BasicData9.b2[i2][0])) ^ ((byte) (BasicData9.b2[i2][1] >> 8))) ^ ((byte) BasicData9.b2[i2][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData9.b2[i2][0]) + "上下：" + ((int) BasicData9.b2[i2][1]) + "byte:" + MainActivityA.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.51.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + i2 + "条指令");
                            MainActivityA.this.writeBleData(bArr2);
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
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityA.52
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PT2") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PA6")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityA.53
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivityA.54
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.showProgressDialog(mainActivityA, mainActivityA.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.showProgressDialog(mainActivityA, mainActivityA.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastLong(mainActivityA, mainActivityA.getResources().getString(R.string.no_device_found));
                    if (MainActivityA.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.54.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityA.this.startScan();
                            }
                        }, 1000L);
                        MainActivityA.access$6308(MainActivityA.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PA")) {
                    MainActivityA.this.connect(list.get(0));
                    return;
                }
                final ArrayList arrayList = new ArrayList();
                arrayList.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName() != null && list.get(i).getName().trim().contains("PA")) {
                        arrayList.add(list.get(i));
                    }
                }
                if (arrayList.size() == 0) {
                    MainActivityA mainActivityA2 = MainActivityA.this;
                    ShowHelper.toastLong(mainActivityA2, mainActivityA2.getResources().getString(R.string.no_device_found));
                    if (MainActivityA.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.54.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityA.this.startScan();
                            }
                        }, 1000L);
                        MainActivityA.access$6308(MainActivityA.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivityA.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityA.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityA.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivityA.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityA.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityA.54.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivityA.this.connect((BleDevice) arrayList.get(i3));
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
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivityA.55
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.showProgressDialog(mainActivityA, mainActivityA.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(final BleDevice bleDevice3, BleException bleException) {
                MainActivityA mainActivityA = MainActivityA.this;
                ShowHelper.toastLong(mainActivityA, mainActivityA.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivityA.this.blenoty.setText(MainActivityA.this.getResources().getString(R.string.disconnected));
                MainActivityA.this.blenoty.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityA.this.signal.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityA.this.signal_note.setText(MainActivityA.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityA.this.signal_note.setTextColor(MainActivityA.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                if (MainActivityA.this.connNum >= 3 || bleDevice3 == null) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivityA.access$6308(MainActivityA.this);
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivityA.this.getResources().getString(R.string.initializing));
                MainActivityA.this.connNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivityA.this, MainActivityA.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                MainActivityA.this.nameStar = bleDevice3.getName().trim();
                MainActivityA.this.blenoty.setText(MainActivityA.this.getResources().getString(R.string.connected));
                MainActivityA.this.blenoty.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivityA.this.signal_note.setText(MainActivityA.this.nameStar + MainActivityA.this.getResources().getString(R.string.connected));
                MainActivityA.this.signal_note.setTextColor(MainActivityA.this.getResources().getColor(R.color.icon_green));
                MainActivityA.this.signal.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivityA.this.isFaultOn = 0;
                MainActivityA.this.gatt = bluetoothGatt;
                MainActivityA.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityA.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityA.this.velobar.getProgress() - 1;
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityA.this.freq.getProgress() - 1;
                        MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.7
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                if (!MainActivityA.this.btn_ball.getText().toString().equals(MainActivityA.this.getResources().getString(R.string.stop))) {
                    MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) 6, (byte) 1770, (byte) 6, (byte) 1590, 0, 0, 1, -91});
                }
                if (!MainActivityA.this.btn_ball.getText().toString().equals(MainActivityA.this.getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.9
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 107, 0, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 3530L);
                }
                MainActivityA.this.getDefaultPoint1();
                MainActivityA.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivityA.this.blenoty.setText(MainActivityA.this.getResources().getString(R.string.disconnected));
                MainActivityA.this.blenoty.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityA.this.signal.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityA.this.signal_note.setText(MainActivityA.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityA.this.signal_note.setTextColor(MainActivityA.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivityA.this.isFaultOn = 0;
                if (z || MainActivityA.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.55.10
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivityA.access$6308(MainActivityA.this);
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
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.56
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityA.this.freq.getProgress() - 1;
                        MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 30L);
                this.velobar.setProgress((float) this.defaultDaoList.get(i).getVelo());
                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i).getVelo() - 1]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.57
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityA.this.velobar.getProgress() - 1;
                        MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[progress] ^ 99), -91});
                    }
                }, 80L);
                this.rotatebar.setProgress((float) this.defaultDaoList.get(i).getRote());
                final int rote = this.defaultDaoList.get(i).getRote();
                if (rote < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.58
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i3) * 5), 0, 0, 0, 0, (byte) (((-i3) * 5) ^ 96), -91});
                        }
                    }, 120L);
                }
                if (rote > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.59
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i3 * 5), 0, 0, 0, 0, (byte) ((i3 * 5) ^ 99), -91});
                        }
                    }, 120L);
                }
                if (rote == 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.60
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                    }, 120L);
                }
                this.r_tv.setText("" + rote);
                if (this.rgheight.getVisibility() != 0 || (grade = this.defaultDaoList.get(i).getGrade()) == 0) {
                    return;
                }
                this.valueSelect = grade;
                this.value_h.setText("" + grade);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.61
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.sendBaseData(1);
                    }
                }, 160L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62
            @Override // java.lang.Runnable
            public void run() {
                MainActivityA.this.defaultDaoList = new ArrayList();
                MainActivityA.this.savedefault.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivityA.this.saveColor = 1;
                MainActivityA mainActivityA = MainActivityA.this;
                mainActivityA.defaultDaoList = mainActivityA.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivityA.this.defaultDaoList.size(); i++) {
                    if (MainActivityA.this.defaultDaoList.get(i).getDaoName() != null && MainActivityA.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivityA.this.savedefault.setBackground(MainActivityA.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivityA.this.saveColor = 0;
                        MainActivityA mainActivityA2 = MainActivityA.this;
                        mainActivityA2.lr = mainActivityA2.defaultDaoList.get(i).getItem2();
                        MainActivityA mainActivityA3 = MainActivityA.this;
                        mainActivityA3.ud = mainActivityA3.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivityA.this.lr;
                        short s2 = (short) MainActivityA.this.ud;
                        MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                        if (MainActivityA.this.ud > 1410) {
                            MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                        } else {
                            MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                        }
                        MainActivityA.this.freq.setProgress(MainActivityA.this.defaultDaoList.get(i).getFreq());
                        MainActivityA.this.f_tv.setText("" + MainActivityA.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityA.this.freq.getProgress() - 1;
                                MainActivityA.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityA.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivityA.this.velobar.setProgress((float) MainActivityA.this.defaultDaoList.get(i).getVelo());
                        MainActivityA.this.v_tv.setText("" + MainActivityA.this.veloTins[MainActivityA.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityA.this.velobar.getProgress() - 1;
                                MainActivityA.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityA.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityA.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivityA.this.rotatebar.setProgress((float) MainActivityA.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivityA.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityA.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityA.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.62.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityA.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivityA.this.r_tv.setText("" + rote);
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
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityA.63
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
                        MainActivityA.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityA.this.isFaultOn == 0) {
                        MainActivityA.access$6908(MainActivityA.this);
                        MainActivityA.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PT2") || this.nameStar.startsWith("PT3") || this.nameStar.startsWith("PT5") || this.nameStar.startsWith("PA6"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityA.64
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
                        MainActivityA.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityA.this.isFaultOn == 0) {
                        MainActivityA.access$6908(MainActivityA.this);
                        MainActivityA.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.65
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivityA.this.checkPower();
            }
        }, 3600L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.66
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivityA.this.sendBaseData(1);
            }
        }, 3800L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.67
            @Override // java.lang.Runnable
            public synchronized void run() {
                if (MainActivityA.this.forbid == 1) {
                    MainActivityA.this.writeBleData(new byte[]{-86, 99, 10, 0, 0, 0, 0, 0, 105, -91});
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
        if (i < 10) {
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
            this.w_ad.setVisibility(8);
            this.w_note.setVisibility(8);
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
            this.w_ad.setVisibility(8);
            this.w_note.setVisibility(8);
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
        this.w_ad.setVisibility(8);
        this.w_note.setVisibility(8);
        if (this.modeNum == 2) {
            this.w_ad.setVisibility(0);
            this.w_note.setVisibility(0);
        }
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.69
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityA.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.68
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityA.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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

    /* JADX WARN: Code restructure failed: missing block: B:287:0x0ce7, code lost:
    
        if (r1.get(r1.size() - 1).intValue() == 3) goto L250;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0d59, code lost:
    
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
            Method dump skipped, instructions count: 10083
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pusun.pusuntennis.MainActivityA.onClick(android.view.View):void");
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
            MainActivityA.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivityA.this.ud > 1400) {
                    MainActivityA.access$812(MainActivityA.this, 30);
                }
                if (MainActivityA.this.ud <= 1400) {
                    MainActivityA.access$812(MainActivityA.this, 100);
                }
                if (MainActivityA.this.ud < 800) {
                    MainActivityA.this.ud = 800;
                }
                if (MainActivityA.this.ud > 1860) {
                    MainActivityA.this.ud = 1860;
                }
                short s = (short) MainActivityA.this.lr;
                if (MainActivityA.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityA.this.ud;
                if (MainActivityA.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                if (MainActivityA.this.ud > 1410) {
                    MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                } else {
                    MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                }
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityA.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                if (MainActivityA.this.ud <= 1400) {
                    MainActivityA.access$820(MainActivityA.this, 100);
                }
                if (MainActivityA.this.ud > 1400) {
                    MainActivityA.access$820(MainActivityA.this, 30);
                }
                if (MainActivityA.this.ud < 800) {
                    MainActivityA.this.ud = 800;
                }
                if (MainActivityA.this.ud > 1860) {
                    MainActivityA.this.ud = 1860;
                }
                short s = (short) MainActivityA.this.lr;
                if (MainActivityA.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityA.this.ud;
                if (MainActivityA.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                if (MainActivityA.this.ud > 1410) {
                    MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                } else {
                    MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                }
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityA.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityA.access$712(MainActivityA.this, 30);
                if (MainActivityA.this.lr < 1140) {
                    MainActivityA.this.lr = 1140;
                }
                if (MainActivityA.this.lr > 2040) {
                    MainActivityA.this.lr = 2040;
                }
                short s = (short) MainActivityA.this.lr;
                if (MainActivityA.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityA.this.ud;
                if (MainActivityA.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                if (MainActivityA.this.ud > 1410) {
                    MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                } else {
                    MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityA.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityA.access$720(MainActivityA.this, 30);
                if (MainActivityA.this.lr < 1140) {
                    MainActivityA.this.lr = 1140;
                }
                if (MainActivityA.this.lr > 2040) {
                    MainActivityA.this.lr = 2040;
                }
                short s = (short) MainActivityA.this.lr;
                if (MainActivityA.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityA.this.ud;
                if (MainActivityA.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityA.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityA.this.num_lr.setText("" + (74 - (MainActivityA.this.lr / 30)));
                if (MainActivityA.this.ud > 1410) {
                    MainActivityA.this.num_ud.setText("" + (64 - (MainActivityA.this.ud / 30)));
                } else {
                    MainActivityA.this.num_ud.setText("" + (((1410 - MainActivityA.this.ud) / 50) + 17));
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityA.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityA.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.155
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.156
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivityA.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivityA.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivityA.this.freq.getProgress());
                seleDao.setItem1(MainActivityA.this.radioNum);
                seleDao.setItem2(MainActivityA.this.valueSelect);
                seleDao.setVelo(MainActivityA.this.velobar.getProgress());
                seleDao.setRote(MainActivityA.this.rotatebar.getProgress());
                MainActivityA.this.daoSession.insertOrReplace(seleDao);
                create.dismiss();
            }
        });
        create.show();
    }

    public void alert_dialog_input2() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.157
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityA.158
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivityA mainActivityA = MainActivityA.this;
                    ShowHelper.toastShort(mainActivityA, mainActivityA.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivityA.this.daoSession.loadAll(VariDao.class);
                VariDao variDao = new VariDao();
                variDao.setDaoName("" + editText.getText().toString().trim());
                variDao.setSeles(MainActivityA.this.select_dianwei.getText().toString().trim());
                variDao.setFreq1(BasicData9.b3[20][3]);
                variDao.setVelo1(BasicData9.b3[20][2]);
                variDao.setLr1(BasicData9.b3[20][0]);
                variDao.setUd1(BasicData9.b3[20][1]);
                variDao.setFreq2(BasicData9.b3[21][3]);
                variDao.setVelo2(BasicData9.b3[21][2]);
                variDao.setLr2(BasicData9.b3[21][0]);
                variDao.setUd2(BasicData9.b3[21][1]);
                variDao.setFreq3(BasicData9.b3[22][3]);
                variDao.setVelo3(BasicData9.b3[22][2]);
                variDao.setLr3(BasicData9.b3[22][0]);
                variDao.setUd3(BasicData9.b3[22][1]);
                variDao.setFreq4(BasicData9.b3[23][3]);
                variDao.setVelo4(BasicData9.b3[23][2]);
                variDao.setLr4(BasicData9.b3[23][0]);
                variDao.setUd4(BasicData9.b3[23][1]);
                variDao.setFreq5(BasicData9.b3[24][3]);
                variDao.setVelo5(BasicData9.b3[24][2]);
                variDao.setLr5(BasicData9.b3[24][0]);
                variDao.setUd5(BasicData9.b3[24][1]);
                variDao.setFreq6(BasicData9.b3[25][3]);
                variDao.setVelo6(BasicData9.b3[25][2]);
                variDao.setLr6(BasicData9.b3[25][0]);
                variDao.setUd6(BasicData9.b3[25][1]);
                MainActivityA.this.daoSession.insertOrReplace(variDao);
                create.dismiss();
            }
        });
        create.show();
    }
}
