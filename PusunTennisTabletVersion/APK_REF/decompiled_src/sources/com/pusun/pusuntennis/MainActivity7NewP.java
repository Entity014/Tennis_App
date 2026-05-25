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
import android.os.LocaleList;
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
import cn.qqtheme.framework.picker.OptionPicker;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.base.Ascii;
import com.kyleduo.switchbutton.SwitchButton;
import com.pusun.pusuntennis.utils.AppLog;
import com.pusun.pusuntennis.utils.BLEServiceParameters;
import com.pusun.pusuntennis.utils.BasicData3;
import com.pusun.pusuntennis.utils.BasicData36;
import com.pusun.pusuntennis.utils.BasicData37;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.NoteVariMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.VarispinMsg;
import com.pusun.pusuntennis.utils.VarispinStartMsg;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.DefaultDao;
import com.pusun.pusuntennis.utils.dao.SeleDao;
import com.pusun.pusuntennis.utils.dao.VariSpinDao;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivity7NewP extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;
    public static BleDevice bleDevice;
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
    private TextView front_s_add;
    private TextView front_s_de;
    private TextView front_s_value;
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
    private int isNewDeve;
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
    private Button onekey;
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
    private int vari_point_num = 1;
    private int[] frequentNums = {65, 55, 46, 38, 30, 25, 21, 18, 16, 14};
    private int[] veloNums = {46, 52, 73, 85, 95, 102, 110, 120, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 140, 155, 175, 190};
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

    static /* synthetic */ int access$308(MainActivity7NewP mainActivity7NewP) {
        int i = mainActivity7NewP.valueSelect;
        mainActivity7NewP.valueSelect = i + 1;
        return i;
    }

    static /* synthetic */ int access$310(MainActivity7NewP mainActivity7NewP) {
        int i = mainActivity7NewP.valueSelect;
        mainActivity7NewP.valueSelect = i - 1;
        return i;
    }

    static /* synthetic */ int access$412(MainActivity7NewP mainActivity7NewP, int i) {
        int i2 = mainActivity7NewP.lr + i;
        mainActivity7NewP.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$420(MainActivity7NewP mainActivity7NewP, int i) {
        int i2 = mainActivity7NewP.lr - i;
        mainActivity7NewP.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$512(MainActivity7NewP mainActivity7NewP, int i) {
        int i2 = mainActivity7NewP.ud + i;
        mainActivity7NewP.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$520(MainActivity7NewP mainActivity7NewP, int i) {
        int i2 = mainActivity7NewP.ud - i;
        mainActivity7NewP.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$7508(MainActivity7NewP mainActivity7NewP) {
        int i = mainActivity7NewP.connNum;
        mainActivity7NewP.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$8108(MainActivity7NewP mainActivity7NewP) {
        int i = mainActivity7NewP.isFaultOn;
        mainActivity7NewP.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_activity7_new_p);
        EventBus.getDefault().register(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.density = displayMetrics.scaledDensity;
        bleDevice = (BleDevice) getIntent().getParcelableExtra("device");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity7NewP.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.switchbtn = (SwitchButton) findViewById(R.id.switchbtn);
        this.tableName = (TextView) findViewById(R.id.tableName);
        this.self = (LinearLayout) findViewById(R.id.self);
        this.group = (LinearLayout) findViewById(R.id.group);
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
        Button button = (Button) findViewById(R.id.change_course);
        this.change_course = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP.this.startActivity(new Intent(MainActivity7NewP.this, (Class<?>) CourseListActivity.class));
            }
        });
        Button button2 = (Button) findViewById(R.id.change_tennis);
        this.change_tennis = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BleManager.getInstance().disconnectAllDevice();
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.changing));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ShowHelper.dismissProgressDialog();
                        Intent intent = new Intent(MainActivity7NewP.this, (Class<?>) MainActivityPadX.class);
                        intent.putExtra("device", MainActivity7NewP.bleDevice);
                        MainActivity7NewP.this.startActivity(intent);
                    }
                }, 1500L);
            }
        });
        Button button3 = (Button) findViewById(R.id.savedefault);
        this.savedefault = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivity7NewP.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivity7NewP.this.freq.getProgress());
                    defaultDao.setGrade(MainActivity7NewP.this.valueSelect);
                    defaultDao.setItem2(MainActivity7NewP.this.lr);
                    defaultDao.setItem3(MainActivity7NewP.this.ud);
                    defaultDao.setVelo(MainActivity7NewP.this.velobar.getProgress());
                    defaultDao.setRote(MainActivity7NewP.this.rotatebar.getProgress());
                    if (MainActivity7NewP.this.modeCate == 0) {
                        if (MainActivity7NewP.this.modeNum != 1) {
                            if (MainActivity7NewP.this.modeNum != 7) {
                                if (MainActivity7NewP.this.modeNum != 5) {
                                    if (MainActivity7NewP.this.modeNum != 4) {
                                        if (MainActivity7NewP.this.modeNum != 2) {
                                            if (MainActivity7NewP.this.modeNum != 3) {
                                                if (MainActivity7NewP.this.modeNum != 6) {
                                                    if (MainActivity7NewP.this.modeNum != 8) {
                                                        if (MainActivity7NewP.this.modeNum != 9) {
                                                            if (MainActivity7NewP.this.modeNum == 11) {
                                                                defaultDao.setDaoName("one");
                                                                defaultDao.setNumber(25L);
                                                                MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                                            }
                                                        } else {
                                                            defaultDao.setDaoName("moon");
                                                            defaultDao.setNumber(24L);
                                                            MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                                        }
                                                    } else {
                                                        defaultDao.setDaoName("place");
                                                        defaultDao.setNumber(23L);
                                                        MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                                    }
                                                } else {
                                                    defaultDao.setDaoName("cross" + MainActivity7NewP.this.tagC);
                                                    if (MainActivity7NewP.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivity7NewP.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivity7NewP.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivity7NewP.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivity7NewP.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivity7NewP.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivity7NewP.this.tagV);
                                                if (MainActivity7NewP.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivity7NewP.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivity7NewP.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivity7NewP.this.tagH);
                                            if (MainActivity7NewP.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivity7NewP.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivity7NewP.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivity7NewP.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivity7NewP.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivity7NewP.this.tagHT);
                                if (MainActivity7NewP.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivity7NewP.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivity7NewP.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivity7NewP.this.tagFix);
                            if (MainActivity7NewP.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivity7NewP.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivity7NewP.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivity7NewP.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.save_default_success));
                    MainActivity7NewP.this.savedefault.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivity7NewP.this.saveColor = 0;
                    return;
                }
                MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                ShowHelper.toastShort(mainActivity7NewP2, mainActivity7NewP2.getResources().getString(R.string.no_change_default));
            }
        });
        this.frontvalue2 = (TextView) findViewById(R.id.frontvalue2);
        this.backvalue2 = (TextView) findViewById(R.id.backvalue2);
        this.front_m_value = (TextView) findViewById(R.id.front_m_value);
        this.back_m_value = (TextView) findViewById(R.id.back_m_value);
        this.front_s_value = (TextView) findViewById(R.id.front_s_value);
        this.frontde2 = (TextView) findViewById(R.id.frontde2);
        this.backde2 = (TextView) findViewById(R.id.backde2);
        this.frontadd2 = (TextView) findViewById(R.id.frontadd2);
        this.backadd2 = (TextView) findViewById(R.id.backadd2);
        this.front_m_de = (TextView) findViewById(R.id.front_m_de);
        this.front_m_add = (TextView) findViewById(R.id.front_m_add);
        this.front_s_de = (TextView) findViewById(R.id.front_s_de);
        this.front_s_add = (TextView) findViewById(R.id.front_s_add);
        this.back_m_de = (TextView) findViewById(R.id.back_m_de);
        this.back_m_add = (TextView) findViewById(R.id.back_m_add);
        this.frontde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][0] > 2250) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][0] = 2250;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[1] = (short) (sArr[1] + 100);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][1] > 4300) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][1] = 4300;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.frontadd2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[0] = (short) (sArr[0] - 30);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][0] < 30) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][0] = 30;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.backde2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][1] = (short) (r1[1] - 100);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][1] < 300) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][1] = 300;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[2] = (short) (sArr[2] - 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][2] < 50) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][2] = 50;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[2] = (short) (sArr[2] + 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][2] > 120) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][2] = 120;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_s_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[4] = (short) (sArr[4] - 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][4] < 20) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][4] = 20;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.front_s_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[4] = (short) (sArr[4] + 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][4] > 80) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][4] = 80;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][3] < 25) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][3] = 25;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.back_m_add.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][3] > 65) {
                    BasicData36.b5[MainActivity7NewP.this.vari_point_num - 1][3] = 65;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
                int i = MainActivity7NewP.this.vari_point_num - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) (MainActivity7NewP.this.vari_point_num + 35), (byte) (BasicData36.b5[i][0] >> 8), (byte) BasicData36.b5[i][0], (byte) (BasicData36.b5[i][1] >> 8), (byte) BasicData36.b5[i][1], (byte) BasicData36.b5[i][4], (byte) BasicData36.b5[i][2], (byte) BasicData36.b5[i][3], -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.u_dian.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity7NewP.this.dian_num.getText().toString().trim()).intValue();
                int intValue2 = Integer.valueOf(MainActivity7NewP.this.l_r.getText().toString().trim()).intValue();
                int intValue3 = Integer.valueOf(MainActivity7NewP.this.u_d.getText().toString().trim()).intValue();
                int intValue4 = Integer.valueOf(MainActivity7NewP.this.s_d.getText().toString().trim()).intValue();
                MainActivity7NewP.this.writeBleData(new byte[]{-86, (byte) intValue, (byte) (intValue2 >> 8), (byte) intValue2, (byte) (intValue3 >> 8), (byte) intValue3, (byte) (intValue4 >> 8), (byte) intValue4, (byte) Integer.valueOf(MainActivity7NewP.this.interval.getText().toString().trim()).intValue(), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.14.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
            }
        });
        this.lastpoints3.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP.this.showBottomDialog();
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
        this.lr = BasicData36.m18[0];
        this.ud = BasicData36.m18[1];
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
        this.onekey = (Button) findViewById(R.id.onekey);
        this.aidrill = (Button) findViewById(R.id.aidrill);
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
        this.onekey.setOnClickListener(this);
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
        this.spinner.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.catenames)));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.16
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 1) {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    mainActivity7NewP.showPos(mainActivity7NewP.gr1);
                    return;
                }
                if (i == 2) {
                    MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                    mainActivity7NewP2.showPos(mainActivity7NewP2.gr2);
                    return;
                }
                if (i == 3) {
                    MainActivity7NewP mainActivity7NewP3 = MainActivity7NewP.this;
                    mainActivity7NewP3.showPos(mainActivity7NewP3.gr3);
                    return;
                }
                if (i == 4) {
                    MainActivity7NewP mainActivity7NewP4 = MainActivity7NewP.this;
                    mainActivity7NewP4.showPos(mainActivity7NewP4.gr4);
                    return;
                }
                if (i == 5) {
                    MainActivity7NewP mainActivity7NewP5 = MainActivity7NewP.this;
                    mainActivity7NewP5.showPos(mainActivity7NewP5.gr5);
                    return;
                }
                if (i == 6) {
                    MainActivity7NewP mainActivity7NewP6 = MainActivity7NewP.this;
                    mainActivity7NewP6.showPos(mainActivity7NewP6.gr6);
                    return;
                }
                if (i == 7) {
                    MainActivity7NewP mainActivity7NewP7 = MainActivity7NewP.this;
                    mainActivity7NewP7.showPos(mainActivity7NewP7.gr7);
                    return;
                }
                if (i == 8) {
                    MainActivity7NewP mainActivity7NewP8 = MainActivity7NewP.this;
                    mainActivity7NewP8.showPos(mainActivity7NewP8.gr8);
                    return;
                }
                if (i == 9) {
                    MainActivity7NewP mainActivity7NewP9 = MainActivity7NewP.this;
                    mainActivity7NewP9.showPos(mainActivity7NewP9.gr9);
                    return;
                }
                if (i == 10) {
                    MainActivity7NewP mainActivity7NewP10 = MainActivity7NewP.this;
                    mainActivity7NewP10.showPos(mainActivity7NewP10.gr10);
                } else if (i == 11) {
                    MainActivity7NewP mainActivity7NewP11 = MainActivity7NewP.this;
                    mainActivity7NewP11.showPos(mainActivity7NewP11.gr11);
                } else if (i == 12) {
                    MainActivity7NewP mainActivity7NewP12 = MainActivity7NewP.this;
                    mainActivity7NewP12.showPos(mainActivity7NewP12.gr12);
                } else {
                    MainActivity7NewP mainActivity7NewP13 = MainActivity7NewP.this;
                    mainActivity7NewP13.showPos(mainActivity7NewP13.poids);
                }
            }
        });
        this.spinner_zu = (Spinner) findViewById(R.id.spinner_zu);
        this.spinner_zu.setAdapter((android.widget.SpinnerAdapter) new SpinnerAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.point_vari)));
        this.spinner_zu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.17
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    MainActivity7NewP.this.vari_point_num = 1;
                } else if (i == 1) {
                    MainActivity7NewP.this.vari_point_num = 2;
                } else if (i == 2) {
                    MainActivity7NewP.this.vari_point_num = 3;
                } else if (i == 3) {
                    MainActivity7NewP.this.vari_point_num = 4;
                } else if (i == 4) {
                    MainActivity7NewP.this.vari_point_num = 5;
                } else if (i == 5) {
                    MainActivity7NewP.this.vari_point_num = 6;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.showSelectPoint(mainActivity7NewP.vari_point_num);
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
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.18
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("up:1");
                    MainActivity7NewP.access$512(MainActivity7NewP.this, 100);
                    if (MainActivity7NewP.this.ud < 300) {
                        MainActivity7NewP.this.ud = 300;
                    }
                    if (MainActivity7NewP.this.ud > 4300) {
                        MainActivity7NewP.this.ud = 4300;
                    }
                    if (MainActivity7NewP.this.modeCate == 0 && ((MainActivity7NewP.this.modeNum == 1 || MainActivity7NewP.this.modeNum == 2) && MainActivity7NewP.this.ud > 2000 && MainActivity7NewP.this.velobar.getProgress() > 5)) {
                        MainActivity7NewP.this.velobar.setProgress(5.0f);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.18.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                            }
                        }, 30L);
                        MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                    }
                    short s = (short) MainActivity7NewP.this.lr;
                    if (MainActivity7NewP.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity7NewP.this.ud;
                    if (MainActivity7NewP.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                    MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.18.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 60L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.18.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount1 != null) {
                                MainActivity7NewP.this.timeCount1.cancel();
                            }
                            MainActivity7NewP.this.timeCount1 = MainActivity7NewP.this.new TimeCount1(20000L, 200L);
                            MainActivity7NewP.this.timeCount1.start();
                        }
                    }, 1L);
                    MainActivity7NewP.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivity7NewP.this.isTouch = false;
                    if (MainActivity7NewP.this.timeCount1 != null) {
                        MainActivity7NewP.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.18.4
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount1 != null) {
                                MainActivity7NewP.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.19
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("down:1");
                    MainActivity7NewP.access$520(MainActivity7NewP.this, 100);
                    if (MainActivity7NewP.this.ud < 300) {
                        MainActivity7NewP.this.ud = 300;
                    }
                    if (MainActivity7NewP.this.ud > 4300) {
                        MainActivity7NewP.this.ud = 4300;
                    }
                    if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 5 && MainActivity7NewP.this.ud < 2000) {
                        MainActivity7NewP.this.ud = 2000;
                    }
                    short s = (short) MainActivity7NewP.this.lr;
                    if (MainActivity7NewP.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity7NewP.this.ud;
                    if (MainActivity7NewP.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                    MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.19.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.19.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount2 != null) {
                                MainActivity7NewP.this.timeCount2.cancel();
                            }
                            MainActivity7NewP.this.timeCount2 = MainActivity7NewP.this.new TimeCount2(20000L, 200L);
                            MainActivity7NewP.this.timeCount2.start();
                        }
                    }, 1L);
                    MainActivity7NewP.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity7NewP.this.isTouch = false;
                    if (MainActivity7NewP.this.timeCount2 != null) {
                        MainActivity7NewP.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.19.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount2 != null) {
                                MainActivity7NewP.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.20
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("left:1");
                    MainActivity7NewP.access$412(MainActivity7NewP.this, 30);
                    if (MainActivity7NewP.this.lr < 1) {
                        MainActivity7NewP.this.lr = 1;
                    }
                    if (MainActivity7NewP.this.lr > 2280) {
                        MainActivity7NewP.this.lr = 2280;
                    }
                    short s = (short) MainActivity7NewP.this.lr;
                    if (MainActivity7NewP.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity7NewP.this.ud;
                    if (MainActivity7NewP.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                    MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.20.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.20.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount3 != null) {
                                MainActivity7NewP.this.timeCount3.cancel();
                            }
                            MainActivity7NewP.this.timeCount3 = MainActivity7NewP.this.new TimeCount3(20000L, 200L);
                            MainActivity7NewP.this.timeCount3.start();
                        }
                    }, 1L);
                    MainActivity7NewP.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity7NewP.this.isTouch = false;
                    if (MainActivity7NewP.this.timeCount3 != null) {
                        MainActivity7NewP.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.20.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount3 != null) {
                                MainActivity7NewP.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.21
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("right:1");
                    MainActivity7NewP.access$420(MainActivity7NewP.this, 30);
                    if (MainActivity7NewP.this.lr < 1) {
                        MainActivity7NewP.this.lr = 1;
                    }
                    if (MainActivity7NewP.this.lr > 2280) {
                        MainActivity7NewP.this.lr = 2280;
                    }
                    short s = (short) MainActivity7NewP.this.lr;
                    if (MainActivity7NewP.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivity7NewP.this.ud;
                    if (MainActivity7NewP.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                    MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.21.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.21.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount4 != null) {
                                MainActivity7NewP.this.timeCount4.cancel();
                            }
                            MainActivity7NewP.this.timeCount4 = MainActivity7NewP.this.new TimeCount4(20000L, 200L);
                            MainActivity7NewP.this.timeCount4.start();
                        }
                    }, 1L);
                    MainActivity7NewP.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivity7NewP.this.isTouch = false;
                    if (MainActivity7NewP.this.timeCount4 != null) {
                        MainActivity7NewP.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.21.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivity7NewP.this.timeCount4 != null) {
                                MainActivity7NewP.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.24
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                MainActivity7NewP.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.24.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivity7NewP.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity7NewP.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivity7NewP.this.f_tv.setText("" + i);
                    MainActivity7NewP.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.25.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity7NewP.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivity7NewP.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    MainActivity7NewP.this.f_tv.setText("" + i);
                    MainActivity7NewP.this.freq.setProgress((float) i);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[intValue], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[intValue] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.26.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity7NewP.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.27
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                final int progress = indicatorSeekBar3.getProgress();
                if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 8) {
                    indicatorSeekBar3.setProgress(0.0f);
                    progress = 0;
                }
                if (progress != 0 && MainActivity7NewP.this.velobar.getProgress() < 5) {
                    MainActivity7NewP.this.velobar.setProgress(5.0f);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                    MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.27.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.27.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.27.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity7NewP.this.r_tv.setText("" + progress);
                MainActivity7NewP.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity7NewP.this.rotatebar.getProgress();
                if (progress > -6) {
                    final int i = progress - 1;
                    if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 8) {
                        MainActivity7NewP.this.rotatebar.setProgress(0.0f);
                        i = 0;
                    }
                    if (i != 0 && MainActivity7NewP.this.velobar.getProgress() < 5) {
                        MainActivity7NewP.this.velobar.setProgress(5.0f);
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                        MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.28.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity7NewP.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.28.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.28.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity7NewP.this.r_tv.setText("" + i);
                    MainActivity7NewP.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity7NewP.this.rotatebar.getProgress();
                if (progress < 6) {
                    final int i = progress + 1;
                    if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 8) {
                        MainActivity7NewP.this.rotatebar.setProgress(0.0f);
                        i = 0;
                    }
                    if (i != 0 && MainActivity7NewP.this.velobar.getProgress() < 5) {
                        MainActivity7NewP.this.velobar.setProgress(5.0f);
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                        MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.29.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivity7NewP.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.29.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.29.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity7NewP.this.r_tv.setText("" + i);
                    MainActivity7NewP.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.30
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivity7NewP.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivity7NewP.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivity7NewP.this.rotatebar.getProgress() != 0) {
                    MainActivity7NewP.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                    MainActivity7NewP.this.rotatebar.setProgress(0.0f);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.30.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivity7NewP.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity7NewP.this.velobar.getProgress();
                if (progress > 1) {
                    int i = progress - 1;
                    if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 5 && i > 6) {
                        MainActivity7NewP.this.velobar.setProgress(6.0f);
                        i = 6;
                    }
                    TextView textView12 = MainActivity7NewP.this.v_tv;
                    StringBuilder sb = new StringBuilder("");
                    int i2 = i - 1;
                    sb.append(MainActivity7NewP.this.veloTins[i2]);
                    textView12.setText(sb.toString());
                    MainActivity7NewP.this.velobar.setProgress(i);
                    if (i < 5 && MainActivity7NewP.this.rotatebar.getProgress() != 0) {
                        MainActivity7NewP.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                        MainActivity7NewP.this.rotatebar.setProgress(0.0f);
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[i2] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.31.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivity7NewP.this.checkIfUpdate();
                }
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivity7NewP.this.velobar.getProgress();
                if (progress < 13) {
                    int i = progress + 1;
                    if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 5 && i > 6) {
                        MainActivity7NewP.this.velobar.setProgress(6.0f);
                        i = 6;
                    }
                    TextView textView12 = MainActivity7NewP.this.v_tv;
                    StringBuilder sb = new StringBuilder("");
                    int i2 = i - 1;
                    sb.append(MainActivity7NewP.this.veloTins[i2]);
                    textView12.setText(sb.toString());
                    MainActivity7NewP.this.velobar.setProgress(i);
                    if (i < 5 && MainActivity7NewP.this.rotatebar.getProgress() != 0) {
                        MainActivity7NewP.this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                        MainActivity7NewP.this.rotatebar.setProgress(0.0f);
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[i2] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.32.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivity7NewP.this.checkIfUpdate();
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
        textView12.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP.this.showBottomDialog();
            }
        });
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.34
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivity7NewP.this.self.setVisibility(8);
                    MainActivity7NewP.this.group.setVisibility(0);
                    MainActivity7NewP.this.points.setVisibility(0);
                    MainActivity7NewP.this.hintpoints.setVisibility(0);
                    MainActivity7NewP.this.group_cate.setVisibility(0);
                    MainActivity7NewP.this.tennipic2.setVisibility(8);
                    MainActivity7NewP.this.tennipic3.setVisibility(8);
                    MainActivity7NewP.this.tennipic4.setVisibility(8);
                    MainActivity7NewP.this.tennipic5.setVisibility(8);
                    MainActivity7NewP.this.fourbtn.setVisibility(8);
                    MainActivity7NewP.this.bg_four.setVisibility(8);
                    MainActivity7NewP.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivity7NewP.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivity7NewP.this.density * 36.0f);
                    MainActivity7NewP.this.start_layout.setLayoutParams(layoutParams);
                    MainActivity7NewP.this.start_layout.setGravity(17);
                    MainActivity7NewP.this.modeCate = 1;
                    return;
                }
                MainActivity7NewP.this.self.setVisibility(0);
                MainActivity7NewP.this.group.setVisibility(8);
                MainActivity7NewP.this.points.setVisibility(8);
                MainActivity7NewP.this.hintpoints.setVisibility(8);
                MainActivity7NewP.this.tennipic2.setVisibility(0);
                MainActivity7NewP.this.bg_input.setVisibility(8);
                MainActivity7NewP.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivity7NewP.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivity7NewP.this.density * 90.0f);
                MainActivity7NewP.this.start_layout.setLayoutParams(layoutParams2);
                MainActivity7NewP.this.start_layout.setGravity(83);
                MainActivity7NewP.this.fourbtn.setVisibility(0);
                MainActivity7NewP.this.bg_four.setVisibility(0);
                if (MainActivity7NewP.this.modeNum == 2) {
                    MainActivity7NewP.this.tennipic3.setVisibility(0);
                    MainActivity7NewP.this.tennipic4.setVisibility(8);
                    MainActivity7NewP.this.tennipic5.setVisibility(8);
                }
                if (MainActivity7NewP.this.modeNum == 3) {
                    MainActivity7NewP.this.tennipic3.setVisibility(8);
                    MainActivity7NewP.this.tennipic4.setVisibility(0);
                    MainActivity7NewP.this.tennipic5.setVisibility(8);
                }
                if (MainActivity7NewP.this.modeNum == 4) {
                    MainActivity7NewP.this.tennipic3.setVisibility(8);
                    MainActivity7NewP.this.tennipic4.setVisibility(8);
                    MainActivity7NewP.this.tennipic5.setVisibility(0);
                }
                MainActivity7NewP.this.modeCate = 0;
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity7NewP.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity7NewP.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity7NewP.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity7NewP.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity7NewP.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.35.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity7NewP.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity7NewP.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity7NewP.this, MainActivity7NewP.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_route_name));
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivity7NewP.this.daoSession.loadAll(VariSpinDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((VariSpinDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivity7NewP.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivity7NewP.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivity7NewP.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivity7NewP.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.36.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivity7NewP.this.daoSession.loadAll(VariSpinDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((VariSpinDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivity7NewP.this.daoSession.delete((VariSpinDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivity7NewP.this, MainActivity7NewP.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.select_dianwei.getText() != null && !MainActivity7NewP.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity7NewP.this.alert_dialog_input();
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.select_dianwei.getText() != null && !MainActivity7NewP.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivity7NewP.this.alert_dialog_input2();
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass39());
        this.lastpoints2.setOnClickListener(new AnonymousClass40());
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.blenoty.getText().toString().trim().contains(MainActivity7NewP.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivity7NewP.this.checkPermissions();
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivity7NewP.this.blenoty.setText(MainActivity7NewP.this.getResources().getString(R.string.disconnected));
                    MainActivity7NewP.this.blenoty.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.42
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivity7NewP.this.radioNum = 0;
                        MainActivity7NewP.this.sendBaseData(0);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivity7NewP.this.radioNum = 1;
                        MainActivity7NewP.this.sendBaseData(1);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivity7NewP.this.radioNum = 2;
                        MainActivity7NewP.this.sendBaseData(2);
                        break;
                }
            }
        });
        this.h_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.valueSelect = Integer.parseInt(mainActivity7NewP.value_h.getText().toString().trim());
                MainActivity7NewP.access$308(MainActivity7NewP.this);
                if (MainActivity7NewP.this.valueSelect > 34) {
                    MainActivity7NewP.this.valueSelect = 34;
                    return;
                }
                if (MainActivity7NewP.this.valueSelect > 20 && MainActivity7NewP.this.velobar.getProgress() > 5) {
                    MainActivity7NewP.this.velobar.setProgress(5.0f);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                    MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                }
                MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP2, mainActivity7NewP2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.43.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1300L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.43.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1400L);
                MainActivity7NewP.this.value_h.setText("" + MainActivity7NewP.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.43.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 25L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.43.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity7NewP.this.checkIfUpdate();
            }
        });
        this.h_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.44
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.valueSelect = Integer.parseInt(mainActivity7NewP.value_h.getText().toString().trim());
                MainActivity7NewP.access$310(MainActivity7NewP.this);
                if (MainActivity7NewP.this.valueSelect < 4) {
                    MainActivity7NewP.this.valueSelect = 4;
                    return;
                }
                if (MainActivity7NewP.this.valueSelect > 20 && MainActivity7NewP.this.velobar.getProgress() > 5) {
                    MainActivity7NewP.this.velobar.setProgress(5.0f);
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                    MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
                }
                MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP2, mainActivity7NewP2.getResources().getString(R.string.change_point));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.44.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1300L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.44.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                    }
                }, 1400L);
                MainActivity7NewP.this.value_h.setText("" + MainActivity7NewP.this.valueSelect);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.44.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 25L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.44.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.sendBaseData(1);
                    }
                }, 50L);
                MainActivity7NewP.this.checkIfUpdate();
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

    /* renamed from: com.pusun.pusuntennis.MainActivity7NewP$39, reason: invalid class name */
    class AnonymousClass39 implements View.OnClickListener {
        AnonymousClass39() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity7NewP.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity7NewP.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity7NewP.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity7NewP.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity7NewP.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivity7NewP.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivity7NewP.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivity7NewP.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivity7NewP.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivity7NewP.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivity7NewP.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivity7NewP.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity7NewP.this.freq.getProgress() - 1;
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivity7NewP.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivity7NewP.this.velobar.getProgress() - 1;
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivity7NewP.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivity7NewP.this.r_tv.setText("" + rote);
                                ((SeleDao) loadAll2.get(i)).getItem1();
                                int item2 = ((SeleDao) loadAll2.get(i)).getItem2();
                                if (item2 != 0) {
                                    MainActivity7NewP.this.valueSelect = item2;
                                    MainActivity7NewP.this.value_h.setText("" + item2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.39.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivity7NewP.this.sendBaseData(1);
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
            MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
            ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_route_name));
        }
    }

    /* renamed from: com.pusun.pusuntennis.MainActivity7NewP$40, reason: invalid class name */
    class AnonymousClass40 implements View.OnClickListener {
        AnonymousClass40() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivity7NewP.this.daoSession.loadAll(VariSpinDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((VariSpinDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity7NewP.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity7NewP.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivity7NewP.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity7NewP.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new AnonymousClass1());
                optionPicker.show();
                return;
            }
            MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
            ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_route_name));
        }

        /* renamed from: com.pusun.pusuntennis.MainActivity7NewP$40$1, reason: invalid class name */
        class AnonymousClass1 implements OptionPicker.OnOptionPickListener {
            AnonymousClass1() {
            }

            @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
            public void onOptionPicked(String str) {
                new ArrayList();
                List loadAll = MainActivity7NewP.this.daoSession.loadAll(VariSpinDao.class);
                for (int i = 0; i < loadAll.size(); i++) {
                    if (((VariSpinDao) loadAll.get(i)).getDaoName().equals(str)) {
                        MainActivity7NewP.this.select_dianwei.setText(((VariSpinDao) loadAll.get(i)).getSeles());
                        String[] split = ((VariSpinDao) loadAll.get(i)).getSeles().split(",");
                        MainActivity7NewP.this.selectPoints.clear();
                        for (String str2 : split) {
                            MainActivity7NewP.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                        }
                        MainActivity7NewP.this.showPoints();
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.40.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        BasicData36.b5[0][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr1();
                        BasicData36.b5[0][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd1();
                        BasicData36.b5[0][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo1();
                        BasicData36.b5[0][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq1();
                        BasicData36.b5[0][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin1();
                        BasicData36.b5[1][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr2();
                        BasicData36.b5[1][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd2();
                        BasicData36.b5[1][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo2();
                        BasicData36.b5[1][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq2();
                        BasicData36.b5[1][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin2();
                        BasicData36.b5[2][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr3();
                        BasicData36.b5[2][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd3();
                        BasicData36.b5[2][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo3();
                        BasicData36.b5[2][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq3();
                        BasicData36.b5[2][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin3();
                        BasicData36.b5[3][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr4();
                        BasicData36.b5[3][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd4();
                        BasicData36.b5[3][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo4();
                        BasicData36.b5[3][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq4();
                        BasicData36.b5[3][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin4();
                        BasicData36.b5[4][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr5();
                        BasicData36.b5[4][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd5();
                        BasicData36.b5[4][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo5();
                        BasicData36.b5[4][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq5();
                        BasicData36.b5[4][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin5();
                        BasicData36.b5[5][0] = (short) ((VariSpinDao) loadAll.get(i)).getLr6();
                        BasicData36.b5[5][1] = (short) ((VariSpinDao) loadAll.get(i)).getUd6();
                        BasicData36.b5[5][2] = (short) ((VariSpinDao) loadAll.get(i)).getVelo6();
                        BasicData36.b5[5][3] = (short) ((VariSpinDao) loadAll.get(i)).getFreq6();
                        BasicData36.b5[5][4] = (short) ((VariSpinDao) loadAll.get(i)).getSpin6();
                        MainActivity7NewP.this.showSelectPoint(MainActivity7NewP.this.vari_point_num);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.40.1.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                final int i2 = 0;
                                while (i2 < BasicData36.b5.length) {
                                    int i3 = i2 + 1;
                                    short s = BasicData36.b5[i2][0];
                                    short s2 = BasicData36.b5[i2][0];
                                    short s3 = BasicData36.b5[i2][1];
                                    short s4 = BasicData36.b5[i2][1];
                                    final byte[] bArr = {-86, (byte) i3, (byte) (BasicData36.b5[i2][0] >> 8), (byte) BasicData36.b5[i2][0], (byte) (BasicData36.b5[i2][1] >> 8), (byte) BasicData36.b5[i2][1], (byte) BasicData36.b5[i2][4], (byte) BasicData36.b5[i2][2], (byte) BasicData36.b5[i2][3], -91};
                                    AppLog.e("左右：" + ((int) BasicData36.b5[i2][0]) + "上下：" + ((int) BasicData36.b5[i2][1]) + "byte:" + MainActivity7NewP.bytesToHexString(bArr));
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.40.1.2.1
                                        @Override // java.lang.Runnable
                                        public synchronized void run() {
                                            AppLog.e("第" + i2 + "条指令");
                                            MainActivity7NewP.this.writeBleData(bArr);
                                        }
                                    }, (long) (i2 * 10));
                                    i2 = i3;
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
        int i2 = i - 1;
        sb.append((BasicData36.b5[i2][0] / 30) + 2);
        textView.setText(sb.toString());
        this.backvalue2.setText("" + (BasicData36.b5[i2][1] / 100));
        TextView textView2 = this.front_m_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData36.b5[i2][2] - 30);
        textView2.setText(sb2.toString());
        TextView textView3 = this.back_m_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData36.b5[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
        this.front_s_value.setText("" + ((BasicData36.b5[i2][4] - 50) / 5));
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
                    this.pos[i2].setBackground(getResources().getDrawable(R.drawable.pickle));
                }
                StringBuffer stringBuffer = new StringBuffer();
                for (int i3 = 0; i3 < this.selectPoints.size(); i3++) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= this.poids.length) {
                            break;
                        }
                        if (this.selectPoints.get(i3).intValue() == this.poids[i4] + 1) {
                            this.pos[i4].setBackground(getResources().getDrawable(R.drawable.pickle_sele));
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
                this.pos[i].setBackground(getResources().getDrawable(R.drawable.pickle));
                i++;
            }
            return;
        }
        if (this.selectPoints.size() != 0) {
            for (int i5 = 0; i5 < this.poids.length; i5++) {
                this.pos[i5].setBackground(getResources().getDrawable(R.drawable.pickle));
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            for (int i6 = 0; i6 < this.selectPoints.size(); i6++) {
                int i7 = 0;
                while (true) {
                    if (i7 >= this.poids.length) {
                        break;
                    }
                    if (this.selectPoints.get(i6).intValue() + 35 == this.poids[i7] + 1) {
                        this.pos[i7].setBackground(getResources().getDrawable(R.drawable.pickle_sele));
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
            this.pos[i].setBackground(getResources().getDrawable(R.drawable.pickle));
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
        while (i < BasicData3.b2.length) {
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData3.b2[i][0] >> 8), (byte) BasicData3.b2[i][0], (byte) (BasicData3.b2[i][1] >> 8), (byte) BasicData3.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData3.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData3.b2[i][0])) ^ ((byte) (BasicData3.b2[i][1] >> 8))) ^ ((byte) BasicData3.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData3.b2[i][0]) + "上下：" + ((int) BasicData3.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.45
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + i + "条指令");
                    MainActivity7NewP.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.46
            @Override // java.lang.Runnable
            public synchronized void run() {
                final int i3 = 20;
                while (i3 < BasicData3.b2.length) {
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData3.b2[i3][0] >> 8), (byte) BasicData3.b2[i3][0], (byte) (BasicData3.b2[i3][1] >> 8), (byte) BasicData3.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData3.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData3.b2[i3][0])) ^ ((byte) (BasicData3.b2[i3][1] >> 8))) ^ ((byte) BasicData3.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData3.b2[i3][0]) + "上下：" + ((int) BasicData3.b2[i3][1]) + "byte:" + MainActivity7NewP.bytesToHexString(bArr2));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.46.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + i3 + "条指令");
                            MainActivity7NewP.this.writeBleData(bArr2);
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
            final int i2 = (this.baseHt - this.valueSelect) * 100;
            final int i3 = 0;
            for (int i4 = 6; i3 < BasicData36.b3.length - i4; i4 = 6) {
                int i5 = i3 + 1;
                short s = BasicData36.b3[i3][c3];
                short s2 = BasicData36.b3[i3][c3];
                short s3 = BasicData36.b3[i3][c2];
                short s4 = BasicData36.b3[i3][c2];
                short s5 = BasicData36.b3[i3][2];
                short s6 = BasicData36.b3[i3][2];
                final byte[] bArr = {-86, (byte) i5, (byte) (BasicData36.b3[i3][c3] >> 8), (byte) BasicData36.b3[i3][c3], (byte) ((BasicData36.b3[i3][c2] - i2) >> 8), (byte) (BasicData36.b3[i3][c2] - i2), (byte) (BasicData36.b3[i3][2] >> 8), (byte) BasicData36.b3[i3][2], (byte) BasicData36.b3[i3][c], -91};
                AppLog.e("左右：" + ((int) BasicData36.b3[i3][0]) + "上下：" + (BasicData36.b3[i3][1] - i2) + "byte:" + bytesToHexString(bArr));
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.47
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + i3 + "条指令");
                        MainActivity7NewP.this.writeBleData(bArr);
                    }
                }, (long) (i3 * 10));
                i3 = i5;
                c2 = 1;
                c3 = 0;
                c = 3;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.48
                @Override // java.lang.Runnable
                public synchronized void run() {
                    final int i6 = 20;
                    while (i6 < BasicData36.b3.length - 6) {
                        int i7 = i6 + 1;
                        short s7 = BasicData36.b3[i6][0];
                        short s8 = BasicData36.b3[i6][0];
                        short s9 = BasicData36.b3[i6][1];
                        short s10 = BasicData36.b3[i6][1];
                        short s11 = BasicData36.b3[i6][2];
                        short s12 = BasicData36.b3[i6][2];
                        final byte[] bArr2 = {-86, (byte) i7, (byte) (BasicData36.b3[i6][0] >> 8), (byte) BasicData36.b3[i6][0], (byte) ((BasicData36.b3[i6][1] - i2) >> 8), (byte) (BasicData36.b3[i6][1] - i2), (byte) (BasicData36.b3[i6][2] >> 8), (byte) BasicData36.b3[i6][2], (byte) BasicData36.b3[i6][3], -91};
                        AppLog.e("左右：" + ((int) BasicData36.b3[i6][0]) + "上下：" + (BasicData36.b3[i6][1] - i2) + "byte:" + MainActivity7NewP.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.48.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + i6 + "条指令");
                                MainActivity7NewP.this.writeBleData(bArr2);
                            }
                        }, (long) (i6 * 10));
                        i6 = i7;
                    }
                }
            }, 900L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.49
                @Override // java.lang.Runnable
                public synchronized void run() {
                    final int length = BasicData36.b3.length - 6;
                    while (length < BasicData36.b3.length) {
                        int length2 = (length + 6) - BasicData36.b3.length;
                        int i6 = length + 1;
                        short s7 = BasicData36.b3[length][0];
                        short s8 = BasicData36.b3[length][0];
                        short s9 = BasicData36.b3[length][1];
                        short s10 = BasicData36.b3[length][1];
                        short s11 = BasicData36.b3[length][2];
                        short s12 = BasicData36.b3[length][2];
                        final byte[] bArr2 = {-86, (byte) i6, (byte) (BasicData36.b5[length2][0] >> 8), (byte) BasicData36.b5[length2][0], (byte) (BasicData36.b5[length2][1] >> 8), (byte) BasicData36.b5[length2][1], (byte) BasicData36.b5[length2][4], (byte) BasicData36.b5[length2][2], (byte) BasicData36.b5[length2][3], -91};
                        AppLog.e("左右：" + ((int) BasicData36.b3[length][0]) + "上下：" + ((int) BasicData36.b3[length][1]) + "byte:" + MainActivity7NewP.bytesToHexString(bArr2));
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.49.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + length + "条指令");
                                MainActivity7NewP.this.writeBleData(bArr2);
                            }
                        }, (long) (length * 10));
                        length = i6;
                    }
                }
            }, 1200L);
            return;
        }
        final int i6 = 0;
        while (i6 < BasicData3.b4.length) {
            int i7 = i6 + 1;
            byte b = (byte) i7;
            final byte[] bArr2 = {-86, b, (byte) (BasicData3.b4[i6][0] >> 8), (byte) BasicData3.b4[i6][0], (byte) (BasicData3.b4[i6][1] >> 8), (byte) BasicData3.b4[i6][1], 0, 0, (byte) ((((((byte) (BasicData3.b4[i6][0] >> 8)) ^ b) ^ ((byte) BasicData3.b4[i6][0])) ^ ((byte) (BasicData3.b4[i6][1] >> 8))) ^ ((byte) BasicData3.b4[i6][1])), -91};
            AppLog.e("左右：" + ((int) BasicData3.b4[i6][0]) + "上下：" + ((int) BasicData3.b4[i6][1]) + "byte:" + bytesToHexString(bArr2));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.50
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + i6 + "条指令");
                    MainActivity7NewP.this.writeBleData(bArr2);
                }
            }, (long) (i6 * 10));
            i6 = i7;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.51
            @Override // java.lang.Runnable
            public synchronized void run() {
                final int i8 = 20;
                while (i8 < BasicData3.b4.length) {
                    int i9 = i8 + 1;
                    byte b2 = (byte) i9;
                    final byte[] bArr3 = {-86, b2, (byte) (BasicData3.b4[i8][0] >> 8), (byte) BasicData3.b4[i8][0], (byte) (BasicData3.b4[i8][1] >> 8), (byte) BasicData3.b4[i8][1], 0, 0, (byte) ((((((byte) (BasicData3.b4[i8][0] >> 8)) ^ b2) ^ ((byte) BasicData3.b4[i8][0])) ^ ((byte) (BasicData3.b4[i8][1] >> 8))) ^ ((byte) BasicData3.b4[i8][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData3.b4[i8][0]) + "上下：" + ((int) BasicData3.b4[i8][1]) + "byte:" + MainActivity7NewP.bytesToHexString(bArr3));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.51.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + i8 + "条指令");
                            MainActivity7NewP.this.writeBleData(bArr3);
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
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.52
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PP3") || this.nameStar.startsWith("PT8") || this.nameStar.startsWith("PM")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.53
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.54
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.no_device_found));
                    if (MainActivity7NewP.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.54.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.startScan();
                            }
                        }, 1000L);
                        MainActivity7NewP.access$7508(MainActivity7NewP.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PT")) {
                    MainActivity7NewP.this.connect(list.get(0));
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
                    MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP2, mainActivity7NewP2.getResources().getString(R.string.no_device_found));
                    if (MainActivity7NewP.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.54.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.startScan();
                            }
                        }, 1000L);
                        MainActivity7NewP.access$7508(MainActivity7NewP.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivity7NewP.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivity7NewP.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivity7NewP.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivity7NewP.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivity7NewP.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.54.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivity7NewP.this.connect((BleDevice) arrayList.get(i3));
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
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.showProgressDialog(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivity7NewP.this.blenoty.setText(MainActivity7NewP.this.getResources().getString(R.string.disconnected));
                MainActivity7NewP.this.blenoty.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity7NewP.this.signal.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity7NewP.this.signal_note.setText(MainActivity7NewP.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity7NewP.this.signal_note.setTextColor(MainActivity7NewP.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivity7NewP.this.getResources().getString(R.string.initializing));
                MainActivity7NewP.this.connNum = 0;
                MyApplication.machineNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.1
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivity7NewP.this, MainActivity7NewP.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                MainActivity7NewP.this.nameStar = bleDevice3.getName().trim();
                MainActivity7NewP.this.blenoty.setText(MainActivity7NewP.this.getResources().getString(R.string.connected));
                MainActivity7NewP.this.blenoty.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivity7NewP.this.signal_note.setText(MainActivity7NewP.this.nameStar + MainActivity7NewP.this.getResources().getString(R.string.connected));
                MainActivity7NewP.this.signal_note.setTextColor(MainActivity7NewP.this.getResources().getColor(R.color.icon_green));
                MainActivity7NewP.this.signal.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivity7NewP.this.isFaultOn = 0;
                MainActivity7NewP.this.gatt = bluetoothGatt;
                MainActivity7NewP.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.sendBaseData(1);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity7NewP.this.velobar.getProgress() - 1;
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity7NewP.this.freq.getProgress() - 1;
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.7
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                MainActivity7NewP.this.getDefaultPoint1();
                MainActivity7NewP.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivity7NewP.this.blenoty.setText(MainActivity7NewP.this.getResources().getString(R.string.disconnected));
                MainActivity7NewP.this.blenoty.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivity7NewP.this.signal.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivity7NewP.this.signal_note.setText(MainActivity7NewP.this.getResources().getString(R.string.device_is_disconnect));
                MainActivity7NewP.this.signal_note.setTextColor(MainActivity7NewP.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivity7NewP.this.isFaultOn = 0;
                if (z || MainActivity7NewP.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.55.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivity7NewP.access$7508(MainActivity7NewP.this);
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
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.56
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity7NewP.this.freq.getProgress() - 1;
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 30L);
                this.velobar.setProgress((float) this.defaultDaoList.get(i).getVelo());
                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i).getVelo() - 1]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.57
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivity7NewP.this.velobar.getProgress() - 1;
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[progress] ^ 99), -91});
                    }
                }, 80L);
                this.rotatebar.setProgress((float) this.defaultDaoList.get(i).getRote());
                final int rote = this.defaultDaoList.get(i).getRote();
                if (rote < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.58
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i3) * 5), 0, 0, 0, 0, (byte) (((-i3) * 5) ^ 96), -91});
                        }
                    }, 120L);
                }
                if (rote > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.59
                        @Override // java.lang.Runnable
                        public void run() {
                            int i3 = rote;
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i3 * 5), 0, 0, 0, 0, (byte) ((i3 * 5) ^ 99), -91});
                        }
                    }, 120L);
                }
                if (rote == 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.60
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                        }
                    }, 120L);
                }
                this.r_tv.setText("" + rote);
                if (this.rgheight.getVisibility() != 0 || (grade = this.defaultDaoList.get(i).getGrade()) == 0) {
                    return;
                }
                this.valueSelect = grade;
                this.value_h.setText("" + grade);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.61
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.sendBaseData(1);
                    }
                }, 160L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62
            @Override // java.lang.Runnable
            public void run() {
                MainActivity7NewP.this.defaultDaoList = new ArrayList();
                MainActivity7NewP.this.savedefault.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivity7NewP.this.saveColor = 1;
                MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                mainActivity7NewP.defaultDaoList = mainActivity7NewP.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivity7NewP.this.defaultDaoList.size(); i++) {
                    if (MainActivity7NewP.this.defaultDaoList.get(i).getDaoName() != null && MainActivity7NewP.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivity7NewP.this.savedefault.setBackground(MainActivity7NewP.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivity7NewP.this.saveColor = 0;
                        MainActivity7NewP mainActivity7NewP2 = MainActivity7NewP.this;
                        mainActivity7NewP2.lr = mainActivity7NewP2.defaultDaoList.get(i).getItem2();
                        MainActivity7NewP mainActivity7NewP3 = MainActivity7NewP.this;
                        mainActivity7NewP3.ud = mainActivity7NewP3.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivity7NewP.this.lr;
                        short s2 = (short) MainActivity7NewP.this.ud;
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                        MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                        MainActivity7NewP.this.freq.setProgress((float) MainActivity7NewP.this.defaultDaoList.get(i).getFreq());
                        MainActivity7NewP.this.f_tv.setText("" + MainActivity7NewP.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity7NewP.this.freq.getProgress() - 1;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 97, (byte) MainActivity7NewP.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivity7NewP.this.velobar.setProgress((float) MainActivity7NewP.this.defaultDaoList.get(i).getVelo());
                        MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[MainActivity7NewP.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivity7NewP.this.velobar.getProgress() - 1;
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivity7NewP.this.rotatebar.setProgress((float) MainActivity7NewP.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivity7NewP.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.62.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivity7NewP.this.r_tv.setText("" + rote);
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
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.63
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
                        MainActivity7NewP.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity7NewP.this.isFaultOn == 0) {
                        MainActivity7NewP.access$8108(MainActivity7NewP.this);
                        MainActivity7NewP.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PP3") || this.nameStar.startsWith("PT8") || this.nameStar.startsWith("PM"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivity7NewP.64
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
                        MainActivity7NewP.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivity7NewP.this.isFaultOn == 0) {
                        MainActivity7NewP.access$8108(MainActivity7NewP.this);
                        MainActivity7NewP.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.65
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivity7NewP.this.checkPower();
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
        NoteVariMsg noteVariMsg = new NoteVariMsg();
        noteVariMsg.msg = i;
        EventBus.getDefault().post(noteVariMsg);
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
                writeBleData(new byte[]{-86, (byte) (varispinMsg.position + 35), (byte) (BasicData37.b3[i][0] >> 8), (byte) BasicData37.b3[i][0], (byte) (BasicData37.b3[i][1] >> 8), (byte) BasicData37.b3[i][1], (byte) BasicData37.b3[i][4], (byte) BasicData37.b3[i][2], (byte) (BasicData37.b3[i][3] + (this.pause * 20)), -91});
            } else {
                writeBleData(new byte[]{-86, (byte) (varispinMsg.position + 35), (byte) (BasicData37.b3[i][0] >> 8), (byte) BasicData37.b3[i][0], (byte) (BasicData37.b3[i][1] >> 8), (byte) BasicData37.b3[i][1], (byte) BasicData37.b3[i][4], (byte) BasicData37.b3[i][2], (byte) BasicData37.b3[i][3], -91});
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.66
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                }
            }, 50L);
            if (BasicData37.b3[i][4] == 0 || BasicData37.b3[i][4] == 50 || Integer.valueOf(bleDevice.getName().toString().trim().substring(3, 9)).intValue() >= 250101) {
                return;
            }
            final int i2 = BasicData37.b3[i][4] - 50;
            if (i2 < 0) {
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.67
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 2, (byte) (-i2), 0, 0, 0, 0, 0, -91});
                    }
                }, 100L);
            }
            if (i2 > 0) {
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.68
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, (byte) i2, 0, 0, 0, 0, 0, -91});
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
                        final byte[] bArr = {-86, (byte) (i2 + 36), (byte) (BasicData37.b3[intValue][c] >> 8), (byte) BasicData37.b3[intValue][c], (byte) (BasicData37.b3[intValue][i] >> 8), (byte) BasicData37.b3[intValue][i], (byte) BasicData37.b3[intValue][4], (byte) BasicData37.b3[intValue][2], (byte) (BasicData37.b3[intValue][3] + 20), -91};
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.69
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivity7NewP.this.writeBleData(bArr);
                            }
                        }, i2 * 50);
                    }
                    if (varispinStartMsg.pause == 2) {
                        final byte[] bArr2 = {-86, (byte) (i2 + 36), (byte) (BasicData37.b3[intValue][0] >> 8), (byte) BasicData37.b3[intValue][0], (byte) (BasicData37.b3[intValue][1] >> 8), (byte) BasicData37.b3[intValue][1], (byte) BasicData37.b3[intValue][4], (byte) BasicData37.b3[intValue][2], (byte) (BasicData37.b3[intValue][3] + 40), -91};
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.70
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                MainActivity7NewP.this.writeBleData(bArr2);
                            }
                        }, i2 * 50);
                    }
                } else {
                    final byte[] bArr3 = {-86, (byte) (i2 + 36), (byte) (BasicData37.b3[intValue][0] >> 8), (byte) BasicData37.b3[intValue][0], (byte) (BasicData37.b3[intValue][1] >> 8), (byte) BasicData37.b3[intValue][1], (byte) BasicData37.b3[intValue][4], (byte) BasicData37.b3[intValue][2], (byte) BasicData37.b3[intValue][3], -91};
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.71
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity7NewP.this.writeBleData(bArr3);
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
            this.t29 = 0;
            this.t30 = 0;
            this.t31 = 0;
            this.t32 = 0;
            this.t33 = 0;
            this.t34 = 0;
            this.t35 = 0;
            if (length >= 1) {
                this.t1 = 36;
            }
            if (length >= 2) {
                this.t2 = 37;
            }
            if (length >= 3) {
                this.t3 = 38;
            }
            if (length >= 4) {
                this.t4 = 39;
            }
            if (length >= 5) {
                this.t5 = 40;
            }
            if (length >= 6) {
                this.t6 = 41;
            }
            if (length >= 7) {
                this.t7 = 42;
            }
            if (length >= 8) {
                this.t8 = 43;
            }
            if (length >= 9) {
                this.t9 = 44;
            }
            if (length >= 10) {
                this.t10 = 45;
            }
            if (length >= 11) {
                this.t11 = 46;
            }
            if (length >= 12) {
                this.t12 = 47;
            }
            if (length >= 13) {
                this.t13 = 48;
            }
            if (length >= 14) {
                this.t14 = 49;
            }
            if (length >= 15) {
                this.t15 = 50;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.72
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity7NewP.this.t1, (byte) MainActivity7NewP.this.t2, (byte) MainActivity7NewP.this.t3, (byte) MainActivity7NewP.this.t4, (byte) MainActivity7NewP.this.t5, (byte) (((((MainActivity7NewP.this.t1 ^ 108) ^ MainActivity7NewP.this.t2) ^ MainActivity7NewP.this.t3) ^ MainActivity7NewP.this.t4) ^ MainActivity7NewP.this.t5), -91});
                }
            }, 5L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.73
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity7NewP.this.t6, (byte) MainActivity7NewP.this.t7, (byte) MainActivity7NewP.this.t8, (byte) MainActivity7NewP.this.t9, (byte) MainActivity7NewP.this.t10, (byte) (((((MainActivity7NewP.this.t6 ^ 111) ^ MainActivity7NewP.this.t7) ^ MainActivity7NewP.this.t8) ^ MainActivity7NewP.this.t9) ^ MainActivity7NewP.this.t10), -91});
                }
            }, 50L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.74
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity7NewP.this.t11, (byte) MainActivity7NewP.this.t12, (byte) MainActivity7NewP.this.t13, (byte) MainActivity7NewP.this.t14, (byte) MainActivity7NewP.this.t15, (byte) (((((MainActivity7NewP.this.t11 ^ 110) ^ MainActivity7NewP.this.t12) ^ MainActivity7NewP.this.t13) ^ MainActivity7NewP.this.t14) ^ MainActivity7NewP.this.t15), -91});
                }
            }, 100L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.75
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity7NewP.this.t16, (byte) MainActivity7NewP.this.t17, (byte) MainActivity7NewP.this.t18, (byte) MainActivity7NewP.this.t19, (byte) MainActivity7NewP.this.t20, (byte) (((((MainActivity7NewP.this.t16 ^ 105) ^ MainActivity7NewP.this.t17) ^ MainActivity7NewP.this.t18) ^ MainActivity7NewP.this.t19) ^ MainActivity7NewP.this.t20), -91});
                }
            }, 150L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.76
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity7NewP.this.t21, (byte) MainActivity7NewP.this.t22, (byte) MainActivity7NewP.this.t23, (byte) MainActivity7NewP.this.t24, (byte) MainActivity7NewP.this.t25, (byte) (((((MainActivity7NewP.this.t21 ^ 104) ^ MainActivity7NewP.this.t22) ^ MainActivity7NewP.this.t23) ^ MainActivity7NewP.this.t24) ^ MainActivity7NewP.this.t25), -91});
                }
            }, 200L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.77
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity7NewP.this.t26, (byte) MainActivity7NewP.this.t27, (byte) MainActivity7NewP.this.t28, 0, 0, (byte) (((MainActivity7NewP.this.t26 ^ 107) ^ MainActivity7NewP.this.t27) ^ MainActivity7NewP.this.t28), -91});
                }
            }, 250L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.78
                @Override // java.lang.Runnable
                public void run() {
                    if (Integer.valueOf(MainActivity7NewP.bleDevice.getName().toString().trim().substring(3, 9)).intValue() < 250101) {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
                    } else {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
                    }
                }
            }, 320L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.79
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                }
            }, 360L);
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.80
            @Override // java.lang.Runnable
            public void run() {
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
            }
        }, 260L);
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.81
            @Override // java.lang.Runnable
            public void run() {
                MainActivity7NewP.this.checkPower();
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
        this.group_cate.setVisibility(8);
        this.control_all.setVisibility(0);
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.83
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity7NewP.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.82
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity7NewP.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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
        int i3;
        int i4;
        int id = view.getId();
        switch (id) {
            case R.id.aidrill /* 2131361863 */:
                Intent intent = new Intent(this, (Class<?>) AIDrillActivity.class);
                intent.putExtra("fm", 5);
                startActivity(intent);
                break;
            case R.id.btn_ball /* 2131361912 */:
                if (this.blenoty.getText().toString().equals(getResources().getString(R.string.disconnected))) {
                    ShowHelper.toastShort(this, getResources().getString(R.string.check_connect));
                    break;
                } else if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                    this.isFaultOn = 0;
                    int i5 = this.modeCate;
                    if (i5 == 0) {
                        this.savedefault.setVisibility(0);
                        int i6 = this.modeNum;
                        if (i6 == 1 || i6 == 7) {
                            AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.122
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                        }
                        if (this.modeNum == 11) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.123
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 7, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                            ShowHelper.showProgressDialog(this, getResources().getString(R.string.serve_one_ball));
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.124
                                @Override // java.lang.Runnable
                                public void run() {
                                    ShowHelper.dismissProgressDialog();
                                }
                            }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.125
                                @Override // java.lang.Runnable
                                public void run() {
                                    ShowHelper.dismissProgressDialog();
                                }
                            }, 2100L);
                        }
                        if (this.modeNum == 2) {
                            if (this.tagH == 1) {
                                i = 10;
                                writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                            } else {
                                i = 10;
                            }
                            if (this.tagH == 2) {
                                byte[] bArr = new byte[i];
                                // fill-array-data instruction
                                bArr[0] = -86;
                                bArr[1] = 106;
                                bArr[2] = 2;
                                bArr[3] = 15;
                                bArr[4] = 21;
                                bArr[5] = 0;
                                bArr[6] = 0;
                                bArr[7] = 0;
                                bArr[8] = 114;
                                bArr[9] = -91;
                                writeBleData(bArr);
                            }
                            if (this.tagH == 3) {
                                byte[] bArr2 = new byte[i];
                                // fill-array-data instruction
                                bArr2[0] = -86;
                                bArr2[1] = 106;
                                bArr2[2] = 2;
                                bArr2[3] = 16;
                                bArr2[4] = 20;
                                bArr2[5] = 0;
                                bArr2[6] = 0;
                                bArr2[7] = 0;
                                bArr2[8] = 110;
                                bArr2[9] = -91;
                                writeBleData(bArr2);
                            }
                            if (this.tagH == 4) {
                                byte[] bArr3 = new byte[i];
                                // fill-array-data instruction
                                bArr3[0] = -86;
                                bArr3[1] = 106;
                                bArr3[2] = 2;
                                bArr3[3] = 17;
                                bArr3[4] = 19;
                                bArr3[5] = 0;
                                bArr3[6] = 0;
                                bArr3[7] = 0;
                                bArr3[8] = 106;
                                bArr3[9] = -91;
                                writeBleData(bArr3);
                            }
                            if (this.tagH == 5) {
                                byte[] bArr4 = new byte[i];
                                // fill-array-data instruction
                                bArr4[0] = -86;
                                bArr4[1] = 106;
                                bArr4[2] = 2;
                                bArr4[3] = 15;
                                bArr4[4] = 18;
                                bArr4[5] = 21;
                                bArr4[6] = 0;
                                bArr4[7] = 0;
                                bArr4[8] = 96;
                                bArr4[9] = -91;
                                writeBleData(bArr4);
                            }
                        } else {
                            i = 10;
                        }
                        if (this.modeNum == 3) {
                            if (this.tagV == 1) {
                                byte[] bArr5 = new byte[i];
                                // fill-array-data instruction
                                bArr5[0] = -86;
                                bArr5[1] = 106;
                                bArr5[2] = 3;
                                bArr5[3] = 0;
                                bArr5[4] = 0;
                                bArr5[5] = 0;
                                bArr5[6] = 0;
                                bArr5[7] = 0;
                                bArr5[8] = 105;
                                bArr5[9] = -91;
                                writeBleData(bArr5);
                            }
                            if (this.tagV == 2) {
                                byte[] bArr6 = new byte[i];
                                // fill-array-data instruction
                                bArr6[0] = -86;
                                bArr6[1] = 106;
                                bArr6[2] = 3;
                                bArr6[3] = 4;
                                bArr6[4] = 11;
                                bArr6[5] = 18;
                                bArr6[6] = 25;
                                bArr6[7] = 32;
                                bArr6[8] = 77;
                                bArr6[9] = -91;
                                writeBleData(bArr6);
                            }
                            if (this.tagV == 3) {
                                byte[] bArr7 = new byte[i];
                                // fill-array-data instruction
                                bArr7[0] = -86;
                                bArr7[1] = 106;
                                bArr7[2] = 3;
                                bArr7[3] = 4;
                                bArr7[4] = 25;
                                bArr7[5] = 0;
                                bArr7[6] = 0;
                                bArr7[7] = 0;
                                bArr7[8] = 116;
                                bArr7[9] = -91;
                                writeBleData(bArr7);
                            }
                        }
                        if (this.modeNum == 4) {
                            byte[] bArr8 = new byte[i];
                            // fill-array-data instruction
                            bArr8[0] = -86;
                            bArr8[1] = 106;
                            bArr8[2] = 4;
                            bArr8[3] = 0;
                            bArr8[4] = 0;
                            bArr8[5] = 0;
                            bArr8[6] = 0;
                            bArr8[7] = 0;
                            bArr8[8] = 110;
                            bArr8[9] = -91;
                            writeBleData(bArr8);
                        }
                        int i7 = this.modeNum;
                        if (i7 == 5 || i7 == 8 || i7 == 9) {
                            AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.126
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                        }
                        if (this.modeNum == 6) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.127
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i8;
                                    int unused = MainActivity7NewP.this.tagC;
                                    int i9 = 28;
                                    int i10 = 22;
                                    int i11 = MainActivity7NewP.this.tagC == 2 ? 28 : 22;
                                    int i12 = 25;
                                    if (MainActivity7NewP.this.tagC == 3) {
                                        i11 = 25;
                                        i8 = 1;
                                    } else {
                                        i8 = 4;
                                    }
                                    if (MainActivity7NewP.this.tagC == 4) {
                                        i8 = 7;
                                    } else {
                                        i12 = i11;
                                    }
                                    if (MainActivity7NewP.this.tagC == 5) {
                                        i8 = 7;
                                    } else {
                                        i10 = i12;
                                    }
                                    if (MainActivity7NewP.this.tagC == 6) {
                                        i8 = 1;
                                    } else {
                                        i9 = i10;
                                    }
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 1, (byte) i9, (byte) i8, 0, 0, 0, (byte) ((i9 ^ 108) ^ i8), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.128
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.129
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                }
                            }, 80L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.130
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                }
                            }, 110L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.131
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                }
                            }, 140L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.132
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 170L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.133
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 250L);
                        }
                        if (this.modeNum == 0) {
                            ShowHelper.toastShort(this, getResources().getString(R.string.select_one_mode));
                        }
                    } else if (i5 == 2) {
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
                            this.t29 = 0;
                            this.t30 = 0;
                            this.t31 = 0;
                            this.t32 = 0;
                            this.t33 = 0;
                            this.t34 = 0;
                            this.t35 = 0;
                            for (int i8 = 0; i8 < this.selectPoints.size(); i8++) {
                                if (i8 == 0) {
                                    this.t1 = this.selectPoints.get(0).intValue() + 35;
                                }
                                if (i8 == 1) {
                                    this.t2 = this.selectPoints.get(1).intValue() + 35;
                                }
                                if (i8 == 2) {
                                    this.t3 = this.selectPoints.get(2).intValue() + 35;
                                }
                                if (i8 == 3) {
                                    this.t4 = this.selectPoints.get(3).intValue() + 35;
                                }
                                if (i8 == 4) {
                                    this.t5 = this.selectPoints.get(4).intValue() + 35;
                                }
                                if (i8 == 5) {
                                    this.t6 = this.selectPoints.get(5).intValue() + 35;
                                }
                                if (i8 == 6) {
                                    this.t7 = this.selectPoints.get(6).intValue() + 35;
                                }
                                if (i8 == 7) {
                                    this.t8 = this.selectPoints.get(7).intValue() + 35;
                                }
                                if (i8 == 8) {
                                    this.t9 = this.selectPoints.get(8).intValue() + 35;
                                }
                                if (i8 == 9) {
                                    this.t10 = this.selectPoints.get(9).intValue() + 35;
                                }
                                if (i8 == 10) {
                                    this.t11 = this.selectPoints.get(10).intValue() + 35;
                                }
                                if (i8 == 11) {
                                    this.t12 = this.selectPoints.get(11).intValue() + 35;
                                }
                                if (i8 == 12) {
                                    this.t13 = this.selectPoints.get(12).intValue() + 35;
                                }
                                if (i8 == 13) {
                                    this.t14 = this.selectPoints.get(13).intValue() + 35;
                                }
                                if (i8 == 14) {
                                    this.t15 = this.selectPoints.get(14).intValue() + 35;
                                }
                                if (i8 == 15) {
                                    this.t16 = this.selectPoints.get(15).intValue() + 35;
                                }
                                if (i8 == 16) {
                                    this.t17 = this.selectPoints.get(16).intValue() + 35;
                                }
                                if (i8 == 17) {
                                    this.t18 = this.selectPoints.get(17).intValue() + 35;
                                }
                                if (i8 == 18) {
                                    this.t19 = this.selectPoints.get(18).intValue() + 35;
                                }
                                if (i8 == 19) {
                                    this.t20 = this.selectPoints.get(19).intValue() + 35;
                                }
                                if (i8 == 20) {
                                    this.t21 = this.selectPoints.get(20).intValue() + 35;
                                }
                                if (i8 == 21) {
                                    this.t22 = this.selectPoints.get(21).intValue() + 35;
                                }
                                if (i8 == 22) {
                                    this.t23 = this.selectPoints.get(22).intValue() + 35;
                                }
                                if (i8 == 23) {
                                    this.t24 = this.selectPoints.get(23).intValue() + 35;
                                }
                                if (i8 == 24) {
                                    this.t25 = this.selectPoints.get(24).intValue() + 35;
                                }
                                if (i8 == 25) {
                                    this.t26 = this.selectPoints.get(25).intValue() + 35;
                                }
                                if (i8 == 26) {
                                    this.t27 = this.selectPoints.get(26).intValue() + 35;
                                }
                                if (i8 == 27) {
                                    this.t28 = this.selectPoints.get(27).intValue() + 35;
                                }
                            }
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.134
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity7NewP.this.t1, (byte) MainActivity7NewP.this.t2, (byte) MainActivity7NewP.this.t3, (byte) MainActivity7NewP.this.t4, (byte) MainActivity7NewP.this.t5, (byte) (((((MainActivity7NewP.this.t1 ^ 108) ^ MainActivity7NewP.this.t2) ^ MainActivity7NewP.this.t3) ^ MainActivity7NewP.this.t4) ^ MainActivity7NewP.this.t5), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.135
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity7NewP.this.t6, (byte) MainActivity7NewP.this.t7, (byte) MainActivity7NewP.this.t8, (byte) MainActivity7NewP.this.t9, (byte) MainActivity7NewP.this.t10, (byte) (((((MainActivity7NewP.this.t6 ^ 111) ^ MainActivity7NewP.this.t7) ^ MainActivity7NewP.this.t8) ^ MainActivity7NewP.this.t9) ^ MainActivity7NewP.this.t10), -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.136
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity7NewP.this.t11, (byte) MainActivity7NewP.this.t12, (byte) MainActivity7NewP.this.t13, (byte) MainActivity7NewP.this.t14, (byte) MainActivity7NewP.this.t15, (byte) (((((MainActivity7NewP.this.t11 ^ 110) ^ MainActivity7NewP.this.t12) ^ MainActivity7NewP.this.t13) ^ MainActivity7NewP.this.t14) ^ MainActivity7NewP.this.t15), -91});
                                }
                            }, 100L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.137
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity7NewP.this.t16, (byte) MainActivity7NewP.this.t17, (byte) MainActivity7NewP.this.t18, (byte) MainActivity7NewP.this.t19, (byte) MainActivity7NewP.this.t20, (byte) (((((MainActivity7NewP.this.t16 ^ 105) ^ MainActivity7NewP.this.t17) ^ MainActivity7NewP.this.t18) ^ MainActivity7NewP.this.t19) ^ MainActivity7NewP.this.t20), -91});
                                }
                            }, 150L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.138
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity7NewP.this.t21, (byte) MainActivity7NewP.this.t22, (byte) MainActivity7NewP.this.t23, (byte) MainActivity7NewP.this.t24, (byte) MainActivity7NewP.this.t25, (byte) (((((MainActivity7NewP.this.t21 ^ 104) ^ MainActivity7NewP.this.t22) ^ MainActivity7NewP.this.t23) ^ MainActivity7NewP.this.t24) ^ MainActivity7NewP.this.t25), -91});
                                }
                            }, 200L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.139
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity7NewP.this.t26, (byte) MainActivity7NewP.this.t27, (byte) MainActivity7NewP.this.t28, 0, 0, (byte) (((MainActivity7NewP.this.t26 ^ 107) ^ MainActivity7NewP.this.t27) ^ MainActivity7NewP.this.t28), -91});
                                }
                            }, 250L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.140
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 6, 0, 0, 0, 0, 0, 111, -91});
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
                        this.t29 = 0;
                        this.t30 = 0;
                        this.t31 = 0;
                        this.t32 = 0;
                        this.t33 = 0;
                        this.t34 = 0;
                        this.t35 = 0;
                        for (int i9 = 0; i9 < this.selectPoints.size(); i9++) {
                            if (i9 == 0) {
                                this.t1 = this.selectPoints.get(0).intValue();
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
                        }
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.141
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivity7NewP.this.t1, (byte) MainActivity7NewP.this.t2, (byte) MainActivity7NewP.this.t3, (byte) MainActivity7NewP.this.t4, (byte) MainActivity7NewP.this.t5, (byte) (((((MainActivity7NewP.this.t1 ^ 108) ^ MainActivity7NewP.this.t2) ^ MainActivity7NewP.this.t3) ^ MainActivity7NewP.this.t4) ^ MainActivity7NewP.this.t5), -91});
                            }
                        }, 5L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.142
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivity7NewP.this.t6, (byte) MainActivity7NewP.this.t7, (byte) MainActivity7NewP.this.t8, (byte) MainActivity7NewP.this.t9, (byte) MainActivity7NewP.this.t10, (byte) (((((MainActivity7NewP.this.t6 ^ 111) ^ MainActivity7NewP.this.t7) ^ MainActivity7NewP.this.t8) ^ MainActivity7NewP.this.t9) ^ MainActivity7NewP.this.t10), -91});
                            }
                        }, 50L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.143
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivity7NewP.this.t11, (byte) MainActivity7NewP.this.t12, (byte) MainActivity7NewP.this.t13, (byte) MainActivity7NewP.this.t14, (byte) MainActivity7NewP.this.t15, (byte) (((((MainActivity7NewP.this.t11 ^ 110) ^ MainActivity7NewP.this.t12) ^ MainActivity7NewP.this.t13) ^ MainActivity7NewP.this.t14) ^ MainActivity7NewP.this.t15), -91});
                            }
                        }, 100L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.144
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivity7NewP.this.t16, (byte) MainActivity7NewP.this.t17, (byte) MainActivity7NewP.this.t18, (byte) MainActivity7NewP.this.t19, (byte) MainActivity7NewP.this.t20, (byte) (((((MainActivity7NewP.this.t16 ^ 105) ^ MainActivity7NewP.this.t17) ^ MainActivity7NewP.this.t18) ^ MainActivity7NewP.this.t19) ^ MainActivity7NewP.this.t20), -91});
                            }
                        }, 150L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.145
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivity7NewP.this.t21, (byte) MainActivity7NewP.this.t22, (byte) MainActivity7NewP.this.t23, (byte) MainActivity7NewP.this.t24, (byte) MainActivity7NewP.this.t25, (byte) (((((MainActivity7NewP.this.t21 ^ 104) ^ MainActivity7NewP.this.t22) ^ MainActivity7NewP.this.t23) ^ MainActivity7NewP.this.t24) ^ MainActivity7NewP.this.t25), -91});
                            }
                        }, 200L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.146
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivity7NewP.this.t26, (byte) MainActivity7NewP.this.t27, (byte) MainActivity7NewP.this.t28, 0, 0, (byte) (((MainActivity7NewP.this.t26 ^ 107) ^ MainActivity7NewP.this.t27) ^ MainActivity7NewP.this.t28), -91});
                            }
                        }, 250L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.147
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                            }
                        }, 320L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.148
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 270L);
                    this.signal_note.setText(getResources().getString(R.string.status_on));
                    this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                    if (this.modeNum != 11 || this.modeCate != 0) {
                        this.btn_ball.setText(getResources().getString(R.string.stop));
                        this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                        break;
                    }
                } else {
                    this.stopMode = this.modeNum;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.149
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 260L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.150
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.checkPower();
                        }
                    }, 190L);
                    writeBleData(new byte[]{-86, 107, 0, 0, 0, 0, 0, 0, 107, -91});
                    this.btn_ball.setText(getResources().getString(R.string.start));
                    this.signal_note.setText(getResources().getString(R.string.status_stop));
                    this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                    this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_dark_selector));
                    break;
                }
                break;
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                this.lr = 1140;
                this.ud = 1400;
                short s = (short) 1140;
                short s2 = (short) 1400;
                writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                hideUD();
                if (this.modeNum != 3) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.90
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.91
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            i2 = 10;
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        } else {
                            i2 = 10;
                        }
                        if (this.modeNum == 2) {
                            if (this.tagH == 1) {
                                byte[] bArr9 = new byte[i2];
                                // fill-array-data instruction
                                bArr9[0] = -86;
                                bArr9[1] = 106;
                                bArr9[2] = 2;
                                bArr9[3] = 0;
                                bArr9[4] = 0;
                                bArr9[5] = 0;
                                bArr9[6] = 0;
                                bArr9[7] = 0;
                                bArr9[8] = 104;
                                bArr9[9] = -91;
                                writeBleData(bArr9);
                            }
                            if (this.tagH == 2) {
                                byte[] bArr10 = new byte[i2];
                                // fill-array-data instruction
                                bArr10[0] = -86;
                                bArr10[1] = 106;
                                bArr10[2] = 2;
                                bArr10[3] = 15;
                                bArr10[4] = 21;
                                bArr10[5] = 0;
                                bArr10[6] = 0;
                                bArr10[7] = 0;
                                bArr10[8] = 114;
                                bArr10[9] = -91;
                                writeBleData(bArr10);
                            }
                            if (this.tagH == 3) {
                                byte[] bArr11 = new byte[i2];
                                // fill-array-data instruction
                                bArr11[0] = -86;
                                bArr11[1] = 106;
                                bArr11[2] = 2;
                                bArr11[3] = 16;
                                bArr11[4] = 20;
                                bArr11[5] = 0;
                                bArr11[6] = 0;
                                bArr11[7] = 0;
                                bArr11[8] = 110;
                                bArr11[9] = -91;
                                writeBleData(bArr11);
                            }
                            if (this.tagH == 4) {
                                byte[] bArr12 = new byte[i2];
                                // fill-array-data instruction
                                bArr12[0] = -86;
                                bArr12[1] = 106;
                                bArr12[2] = 2;
                                bArr12[3] = 17;
                                bArr12[4] = 19;
                                bArr12[5] = 0;
                                bArr12[6] = 0;
                                bArr12[7] = 0;
                                bArr12[8] = 106;
                                bArr12[9] = -91;
                                writeBleData(bArr12);
                            }
                            if (this.tagH == 5) {
                                byte[] bArr13 = new byte[i2];
                                // fill-array-data instruction
                                bArr13[0] = -86;
                                bArr13[1] = 106;
                                bArr13[2] = 2;
                                bArr13[3] = 15;
                                bArr13[4] = 18;
                                bArr13[5] = 21;
                                bArr13[6] = 0;
                                bArr13[7] = 0;
                                bArr13[8] = 96;
                                bArr13[9] = -91;
                                writeBleData(bArr13);
                            }
                        }
                        if (this.modeNum == 3) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.92
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity7NewP.this.tagV == 1) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                    if (MainActivity7NewP.this.tagV == 2) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.VT, Ascii.DC2, Ascii.EM, 32, 77, -91});
                                    }
                                    if (MainActivity7NewP.this.tagV == 3) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.EM, 0, 0, 0, 116, -91});
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                if (this.modeNum != 6) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.96
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.97
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 250L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.98
                        @Override // java.lang.Runnable
                        public void run() {
                            int i12;
                            int unused = MainActivity7NewP.this.tagC;
                            int i13 = 28;
                            int i14 = 22;
                            int i15 = MainActivity7NewP.this.tagC == 2 ? 28 : 22;
                            int i16 = 25;
                            if (MainActivity7NewP.this.tagC == 3) {
                                i15 = 25;
                                i12 = 1;
                            } else {
                                i12 = 4;
                            }
                            if (MainActivity7NewP.this.tagC == 4) {
                                i12 = 7;
                            } else {
                                i16 = i15;
                            }
                            if (MainActivity7NewP.this.tagC == 5) {
                                i12 = 7;
                            } else {
                                i14 = i16;
                            }
                            if (MainActivity7NewP.this.tagC == 6) {
                                i12 = 1;
                            } else {
                                i13 = i14;
                            }
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 1, (byte) i13, (byte) i12, 0, 0, 0, (byte) ((i13 ^ 108) ^ i12), -91});
                        }
                    }, 5L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.99
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.100
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                        }
                    }, 80L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.101
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                        }
                    }, 110L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.102
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                        }
                    }, 140L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.103
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 170L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.104
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                this.isNumDing = 1;
                if (this.modeNum != 5) {
                    this.isNumDing = 0;
                    this.lr = 1140;
                    this.ud = 4200;
                    short s3 = (short) 1140;
                    short s4 = (short) 4200;
                    writeBleData(new byte[]{-86, 108, (byte) (s3 >> 8), (byte) s3, (byte) (s4 >> 8), (byte) s4, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 5;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(3.0f);
                this.v_tv.setText("" + this.veloTins[2]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.93
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.velobar.getProgress();
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[2], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[2] ^ 99), -91});
                    }
                }, 50L);
                this.daoNameIng = "high";
                setDefaultMode("high");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.94
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 5) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.95
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i13 = this.tagHT;
                if (i13 == 1) {
                    this.lr = 1140;
                }
                if (i13 == 2) {
                    this.lr = 210;
                }
                if (i13 == 3) {
                    this.lr = 2070;
                }
                this.ud = 1300;
                short s5 = (short) this.lr;
                short s6 = (short) 1300;
                writeBleData(new byte[]{-86, 108, (byte) (s5 >> 8), (byte) s5, (byte) (s6 >> 8), (byte) s6, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                this.isNumDing = 1;
                if (this.modeNum != 7) {
                    this.isNumDing = 0;
                }
                this.modeNum = 7;
                this.modeCate = 0;
                changeOperate();
                this.velobar.setProgress(5.0f);
                this.v_tv.setText("" + this.veloTins[4]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.105
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.106
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.107
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                hideLR();
                this.lr = 1140;
                this.ud = 1400;
                short s7 = (short) 1140;
                short s8 = (short) 1400;
                writeBleData(new byte[]{-86, 108, (byte) (s7 >> 8), (byte) s7, (byte) (s8 >> 8), (byte) s8, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                showUD();
                if (this.modeNum != 2) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.87
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.88
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2 && this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.89
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivity7NewP.this.tagH == 1) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                    if (MainActivity7NewP.this.tagH == 2) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.NAK, 0, 0, 0, 114, -91});
                                    }
                                    if (MainActivity7NewP.this.tagH == 3) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 2, Ascii.DLE, Ascii.DC4, 0, 0, 0, 110, -91});
                                    }
                                    if (MainActivity7NewP.this.tagH == 4) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 2, 17, 19, 0, 0, 0, 106, -91});
                                    }
                                    if (MainActivity7NewP.this.tagH == 5) {
                                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.DC2, Ascii.NAK, 0, 0, 96, -91});
                                    }
                                }
                            }, 30L);
                        }
                        if (this.modeNum == 3) {
                            i3 = 10;
                            writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                        } else {
                            i3 = 10;
                        }
                        if (this.modeNum == 4) {
                            byte[] bArr14 = new byte[i3];
                            // fill-array-data instruction
                            bArr14[0] = -86;
                            bArr14[1] = 106;
                            bArr14[2] = 4;
                            bArr14[3] = 0;
                            bArr14[4] = 0;
                            bArr14[5] = 0;
                            bArr14[6] = 0;
                            bArr14[7] = 0;
                            bArr14[8] = 110;
                            bArr14[9] = -91;
                            writeBleData(bArr14);
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
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
                    this.lr = 1140;
                    this.ud = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
                    short s9 = (short) 1140;
                    short s10 = (short) DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS;
                    writeBleData(new byte[]{-86, 108, (byte) (s9 >> 8), (byte) s9, (byte) (s10 >> 8), (byte) s10, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 9;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(4.0f);
                this.v_tv.setText("" + this.veloTins[3]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.112
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.velobar.getProgress();
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[3], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[3] ^ 99), -91});
                    }
                }, 50L);
                this.rotatebar.setProgress(6.0f);
                this.r_tv.setText("6");
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.113
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.rotatebar.setProgress(6.0f);
                        MainActivity7NewP.this.r_tv.setText("6");
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 1, Ascii.RS, 0, 0, 0, 0, 122, -91});
                    }
                }, 75L);
                this.daoNameIng = "moon";
                setDefaultMode("moon");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.114
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 9) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.115
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
            case R.id.onekey /* 2131362313 */:
                this.tableName.setText(getResources().getString(R.string.onekey));
                this.self_point.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.left_right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.down_up.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high_point.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.onekey.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
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
                if (this.modeNum != 11) {
                    this.isNumDing = 0;
                    this.lr = 1200;
                    this.ud = 2000;
                    short s11 = (short) 1200;
                    short s12 = (short) 2000;
                    writeBleData(new byte[]{-86, 108, (byte) (s11 >> 8), (byte) s11, (byte) (s12 >> 8), (byte) s12, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 11;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(5.0f);
                this.v_tv.setText("" + this.veloTins[4]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.119
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.velobar.getProgress();
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                    }
                }, 50L);
                this.daoNameIng = "one";
                setDefaultMode("one");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.120
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 11) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.121
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 107, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
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
                    this.lr = 1140;
                    this.ud = 4200;
                    short s13 = (short) 1140;
                    short s14 = (short) 4200;
                    writeBleData(new byte[]{-86, 108, (byte) (s13 >> 8), (byte) s13, (byte) (s14 >> 8), (byte) s14, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 8;
                this.modeCate = 0;
                changeOperate();
                showTvs(this.fx1);
                this.velobar.setProgress(1.0f);
                this.v_tv.setText("" + this.veloTins[0]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.108
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.velobar.getProgress();
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[0], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[0] ^ 99), -91});
                    }
                }, 50L);
                this.rotatebar.setProgress(0.0f);
                this.r_tv.setText(SessionDescription.SUPPORTED_SDP_VERSION);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.109
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 99, -91});
                    }
                }, 75L);
                this.daoNameIng = "place";
                setDefaultMode("place");
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.110
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 8) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.111
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(0);
                this.group_cate.setVisibility(0);
                this.selectPoints.clear();
                showPoints();
                this.modeCate = 1;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.116
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
                        MainActivity7NewP.this.velobar.setProgress(5.0f);
                        MainActivity7NewP.this.v_tv.setText("" + MainActivity7NewP.this.veloTins[4]);
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i16 = this.tagFix;
                if (i16 == 1) {
                    this.lr = 1140;
                }
                if (i16 == 2) {
                    this.lr = 210;
                }
                if (i16 == 3) {
                    this.lr = 2070;
                }
                this.ud = 1400;
                short s15 = (short) this.lr;
                short s16 = (short) 1400;
                writeBleData(new byte[]{-86, 108, (byte) (s15 >> 8), (byte) s15, (byte) (s16 >> 8), (byte) s16, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                this.isNumDing = 1;
                if (this.modeNum != 1) {
                    this.isNumDing = 0;
                }
                this.modeNum = 1;
                this.modeCate = 0;
                changeOperate();
                this.velobar.setProgress(5.0f);
                this.v_tv.setText("" + this.veloTins[4]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.84
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.85
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.86
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 200L);
                    break;
                }
                break;
            case R.id.step /* 2131362542 */:
                startActivity(new Intent(this, (Class<?>) VaryDrillActivityDrill.class));
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
                this.onekey.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.step.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.points.setVisibility(8);
                this.group_cate.setVisibility(8);
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showTvs(this.wh1);
                if (this.modeNum != 4) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.117
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 99, (byte) MainActivity7NewP.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivity7NewP.this.veloNums[4] ^ 99), -91});
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
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.118
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            i4 = 10;
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        } else {
                            i4 = 10;
                        }
                        if (this.modeNum == 2) {
                            byte[] bArr15 = new byte[i4];
                            // fill-array-data instruction
                            bArr15[0] = -86;
                            bArr15[1] = 106;
                            bArr15[2] = 2;
                            bArr15[3] = 0;
                            bArr15[4] = 0;
                            bArr15[5] = 0;
                            bArr15[6] = 0;
                            bArr15[7] = 0;
                            bArr15[8] = 104;
                            bArr15[9] = -91;
                            writeBleData(bArr15);
                        }
                        if (this.modeNum == 3) {
                            byte[] bArr16 = new byte[i4];
                            // fill-array-data instruction
                            bArr16[0] = -86;
                            bArr16[1] = 106;
                            bArr16[2] = 3;
                            bArr16[3] = 0;
                            bArr16[4] = 0;
                            bArr16[5] = 0;
                            bArr16[6] = 0;
                            bArr16[7] = 0;
                            bArr16[8] = 105;
                            bArr16[9] = -91;
                            writeBleData(bArr16);
                        }
                        if (this.modeNum == 4) {
                            byte[] bArr17 = new byte[i4];
                            // fill-array-data instruction
                            bArr17[0] = -86;
                            bArr17[1] = 106;
                            bArr17[2] = 4;
                            bArr17[3] = 0;
                            bArr17[4] = 0;
                            bArr17[5] = 0;
                            bArr17[6] = 0;
                            bArr17[7] = 0;
                            bArr17[8] = 110;
                            bArr17[9] = -91;
                            writeBleData(bArr17);
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
        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.151
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.negative).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.152
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() > 0) {
                    MainActivity7NewP.this.selectPoints.remove(MainActivity7NewP.this.selectPoints.size() - 1);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp1).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.153
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(1);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp2).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.154
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(2);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp3).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.155
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(3);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp4).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.156
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(4);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp5).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.157
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(5);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp6).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.158
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(6);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp7).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.159
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(7);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp8).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.160
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(8);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp9).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.161
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(9);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp10).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.162
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(10);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp11).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.163
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(11);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp12).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.164
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(12);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp13).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.165
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(13);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp14).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.166
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(14);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp15).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.167
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(15);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp16).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.168
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(16);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp17).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.169
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(17);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp18).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.170
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(18);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp19).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.171
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(19);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp20).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.172
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(20);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp21).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.173
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(21);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp22).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.174
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(22);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp23).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.175
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(23);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp24).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.176
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(24);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp25).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.177
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(25);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp26).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.178
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(26);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp27).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.179
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(27);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp28).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.180
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(28);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp29).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.181
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(29);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp30).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.182
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(30);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp31).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.183
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(31);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp32).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.184
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(32);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp33).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.185
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(33);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp34).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.186
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(34);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
            }
        });
        dialog.findViewById(R.id.sp35).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.187
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivity7NewP.this.selectPoints.size() < 35) {
                    MainActivity7NewP.this.selectPoints.add(35);
                } else {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastLong(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.up_to));
                }
                MainActivity7NewP.this.showPoints();
                MainActivity7NewP.this.select_dianwei2.setText(MainActivity7NewP.this.select_dianwei.getText().toString());
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
            MainActivity7NewP.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity7NewP.access$512(MainActivity7NewP.this, 100);
                if (MainActivity7NewP.this.ud < 300) {
                    MainActivity7NewP.this.ud = 300;
                }
                if (MainActivity7NewP.this.ud > 4300) {
                    MainActivity7NewP.this.ud = 4300;
                }
                if (MainActivity7NewP.this.modeCate == 0 && ((MainActivity7NewP.this.modeNum == 1 || MainActivity7NewP.this.modeNum == 2) && MainActivity7NewP.this.ud > 2000)) {
                    MainActivity7NewP.this.ud = 2000;
                }
                short s = (short) MainActivity7NewP.this.lr;
                if (MainActivity7NewP.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity7NewP.this.ud;
                if (MainActivity7NewP.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity7NewP.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity7NewP.access$520(MainActivity7NewP.this, 100);
                if (MainActivity7NewP.this.ud < 300) {
                    MainActivity7NewP.this.ud = 300;
                }
                if (MainActivity7NewP.this.ud > 4300) {
                    MainActivity7NewP.this.ud = 4300;
                }
                if (MainActivity7NewP.this.modeCate == 0 && MainActivity7NewP.this.modeNum == 5 && MainActivity7NewP.this.ud < 2000) {
                    MainActivity7NewP.this.ud = 2000;
                }
                short s = (short) MainActivity7NewP.this.lr;
                if (MainActivity7NewP.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity7NewP.this.ud;
                if (MainActivity7NewP.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity7NewP.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity7NewP.access$412(MainActivity7NewP.this, 30);
                if (MainActivity7NewP.this.lr < 90) {
                    MainActivity7NewP.this.lr = 90;
                }
                if (MainActivity7NewP.this.lr > 2370) {
                    MainActivity7NewP.this.lr = 2370;
                }
                short s = (short) MainActivity7NewP.this.lr;
                if (MainActivity7NewP.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity7NewP.this.ud;
                if (MainActivity7NewP.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                AppLog.e("count3:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivity7NewP.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivity7NewP.access$420(MainActivity7NewP.this, 30);
                if (MainActivity7NewP.this.lr < 90) {
                    MainActivity7NewP.this.lr = 90;
                }
                if (MainActivity7NewP.this.lr > 2370) {
                    MainActivity7NewP.this.lr = 2370;
                }
                short s = (short) MainActivity7NewP.this.lr;
                if (MainActivity7NewP.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivity7NewP.this.ud;
                if (MainActivity7NewP.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivity7NewP.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivity7NewP.this.num_lr.setText("" + ((MainActivity7NewP.this.lr / 30) + 2));
                MainActivity7NewP.this.num_ud.setText("" + (MainActivity7NewP.this.ud / 100));
                AppLog.e("count4:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivity7NewP.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity7NewP.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.188
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.189
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList().clear();
                List loadAll = MainActivity7NewP.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivity7NewP.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivity7NewP.this.freq.getProgress());
                seleDao.setItem1(MainActivity7NewP.this.radioNum);
                seleDao.setItem2(MainActivity7NewP.this.valueSelect);
                seleDao.setVelo(MainActivity7NewP.this.velobar.getProgress());
                seleDao.setRote(MainActivity7NewP.this.rotatebar.getProgress());
                MainActivity7NewP.this.daoSession.insertOrReplace(seleDao);
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.190
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivity7NewP.191
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivity7NewP mainActivity7NewP = MainActivity7NewP.this;
                    ShowHelper.toastShort(mainActivity7NewP, mainActivity7NewP.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivity7NewP.this.daoSession.loadAll(VariSpinDao.class);
                VariSpinDao variSpinDao = new VariSpinDao();
                variSpinDao.setDaoName("" + editText.getText().toString().trim());
                variSpinDao.setSeles(MainActivity7NewP.this.select_dianwei.getText().toString().trim());
                variSpinDao.setFreq1(BasicData36.b5[0][3]);
                variSpinDao.setVelo1(BasicData36.b5[0][2]);
                variSpinDao.setLr1(BasicData36.b5[0][0]);
                variSpinDao.setUd1(BasicData36.b5[0][1]);
                variSpinDao.setSpin1(BasicData36.b5[0][4]);
                variSpinDao.setFreq2(BasicData36.b5[1][3]);
                variSpinDao.setVelo2(BasicData36.b5[1][2]);
                variSpinDao.setLr2(BasicData36.b5[1][0]);
                variSpinDao.setSpin2(BasicData36.b5[1][4]);
                variSpinDao.setUd2(BasicData36.b5[1][1]);
                variSpinDao.setFreq3(BasicData36.b5[2][3]);
                variSpinDao.setVelo3(BasicData36.b5[2][2]);
                variSpinDao.setLr3(BasicData36.b5[2][0]);
                variSpinDao.setUd3(BasicData36.b5[2][1]);
                variSpinDao.setSpin3(BasicData36.b5[2][4]);
                variSpinDao.setFreq4(BasicData36.b5[3][3]);
                variSpinDao.setVelo4(BasicData36.b5[3][2]);
                variSpinDao.setLr4(BasicData36.b5[3][0]);
                variSpinDao.setUd4(BasicData36.b5[3][1]);
                variSpinDao.setSpin4(BasicData36.b5[3][4]);
                variSpinDao.setFreq5(BasicData36.b5[4][3]);
                variSpinDao.setVelo5(BasicData36.b5[4][2]);
                variSpinDao.setLr5(BasicData36.b5[4][0]);
                variSpinDao.setUd5(BasicData36.b5[4][1]);
                variSpinDao.setSpin5(BasicData36.b5[4][4]);
                variSpinDao.setFreq6(BasicData36.b5[5][3]);
                variSpinDao.setVelo6(BasicData36.b5[5][2]);
                variSpinDao.setLr6(BasicData36.b5[5][0]);
                variSpinDao.setUd6(BasicData36.b5[5][1]);
                variSpinDao.setSpin6(BasicData36.b5[5][4]);
                MainActivity7NewP.this.daoSession.insertOrReplace(variSpinDao);
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
