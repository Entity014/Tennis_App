package com.pusun.pusuntennis;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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
import com.clj.fastble.scan.BleScanRuleConfig;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.common.base.Ascii;
import com.kyleduo.switchbutton.SwitchButton;
import com.pusun.pusuntennis.utils.AppLog;
import com.pusun.pusuntennis.utils.BLEServiceParameters;
import com.pusun.pusuntennis.utils.BasicData7;
import com.pusun.pusuntennis.utils.BatteryVolumeMsg;
import com.pusun.pusuntennis.utils.ShowFaultMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.DefaultDao;
import com.pusun.pusuntennis.utils.dao.SeleDao;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;
import com.zhy.http.okhttp.OkHttpUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class MainActivityPU extends AppCompatActivity implements View.OnClickListener {
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
    private TextView freqadd;
    private TextView freqde;
    BluetoothGatt gatt;
    private LinearLayout group;
    private RelativeLayout group_cate;
    private Button group_cross;
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
    private RadioGroup rgheight;
    private TextView ri_ad;
    private TextView roadd;
    private TextView rode;
    private IndicatorSeekBar rotatebar;
    private Button savedefault;
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
    private TextView spadd;
    private TextView spde;
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
    private TextView[] tvs;
    private TextView u_ad;
    private int ud;
    private TextView v_ad;
    private TextView v_de;
    private TextView v_tv;
    private IndicatorSeekBar velobar;
    private TextView vo_tv;
    private Button whole;
    private List<Integer> selectPoints = new ArrayList();
    private int modeNum = 1;
    private int modeCate = 0;
    private int[] frequentNums = {65, 55, 46, 38, 30, 25, 21, 18, 15, 13};
    private int[] veloNums = {46, 61, 71, 83, 95, 105, 115, 125, TsExtractor.TS_STREAM_TYPE_E_AC3, 145, 155, 175, 200};
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
    List<DefaultDao> defaultDaoList = new ArrayList();
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
    private String daoNameIng = "fix1";
    private int saveColor = 0;

    static /* synthetic */ int access$412(MainActivityPU mainActivityPU, int i) {
        int i2 = mainActivityPU.lr + i;
        mainActivityPU.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$420(MainActivityPU mainActivityPU, int i) {
        int i2 = mainActivityPU.lr - i;
        mainActivityPU.lr = i2;
        return i2;
    }

    static /* synthetic */ int access$512(MainActivityPU mainActivityPU, int i) {
        int i2 = mainActivityPU.ud + i;
        mainActivityPU.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$520(MainActivityPU mainActivityPU, int i) {
        int i2 = mainActivityPU.ud - i;
        mainActivityPU.ud = i2;
        return i2;
    }

    static /* synthetic */ int access$6608(MainActivityPU mainActivityPU) {
        int i = mainActivityPU.connNum;
        mainActivityPU.connNum = i + 1;
        return i;
    }

    static /* synthetic */ int access$7208(MainActivityPU mainActivityPU) {
        int i = mainActivityPU.isFaultOn;
        mainActivityPU.isFaultOn = i + 1;
        return i;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main_pu);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.MainActivityPU$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivityPU.lambda$onCreate$0(view, windowInsetsCompat);
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
        this.freqde = (TextView) findViewById(R.id.freqde);
        this.freqadd = (TextView) findViewById(R.id.freqadd);
        this.spde = (TextView) findViewById(R.id.spde);
        this.spadd = (TextView) findViewById(R.id.spadd);
        this.rode = (TextView) findViewById(R.id.rode);
        this.roadd = (TextView) findViewById(R.id.roadd);
        this.rgheight = (RadioGroup) findViewById(R.id.rgheight);
        this.rg_hint = (TextView) findViewById(R.id.rg_hint);
        Button button = (Button) findViewById(R.id.savedefault);
        this.savedefault = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPU.this.saveColor == 1) {
                    DefaultDao defaultDao = new DefaultDao();
                    defaultDao.setSeles(MainActivityPU.this.select_dianwei.getText().toString().trim());
                    defaultDao.setFreq(MainActivityPU.this.freq.getProgress());
                    defaultDao.setGrade(MainActivityPU.this.radioNum);
                    defaultDao.setItem2(MainActivityPU.this.lr);
                    defaultDao.setItem3(MainActivityPU.this.ud);
                    defaultDao.setVelo(MainActivityPU.this.velobar.getProgress());
                    defaultDao.setRote(MainActivityPU.this.rotatebar.getProgress());
                    if (MainActivityPU.this.modeCate == 0) {
                        if (MainActivityPU.this.modeNum != 1) {
                            if (MainActivityPU.this.modeNum != 7) {
                                if (MainActivityPU.this.modeNum != 5) {
                                    if (MainActivityPU.this.modeNum != 4) {
                                        if (MainActivityPU.this.modeNum != 2) {
                                            if (MainActivityPU.this.modeNum != 3) {
                                                if (MainActivityPU.this.modeNum == 6) {
                                                    defaultDao.setDaoName("cross" + MainActivityPU.this.tagC);
                                                    if (MainActivityPU.this.tagC == 1) {
                                                        defaultDao.setNumber(17L);
                                                    }
                                                    if (MainActivityPU.this.tagC == 2) {
                                                        defaultDao.setNumber(18L);
                                                    }
                                                    if (MainActivityPU.this.tagC == 3) {
                                                        defaultDao.setNumber(19L);
                                                    }
                                                    if (MainActivityPU.this.tagC == 4) {
                                                        defaultDao.setNumber(20L);
                                                    }
                                                    if (MainActivityPU.this.tagC == 5) {
                                                        defaultDao.setNumber(21L);
                                                    }
                                                    if (MainActivityPU.this.tagC == 6) {
                                                        defaultDao.setNumber(22L);
                                                    }
                                                    MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                                                }
                                            } else {
                                                defaultDao.setDaoName("ud" + MainActivityPU.this.tagV);
                                                if (MainActivityPU.this.tagV == 1) {
                                                    defaultDao.setNumber(14L);
                                                }
                                                if (MainActivityPU.this.tagV == 2) {
                                                    defaultDao.setNumber(15L);
                                                }
                                                if (MainActivityPU.this.tagV == 3) {
                                                    defaultDao.setNumber(16L);
                                                }
                                                MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                                            }
                                        } else {
                                            defaultDao.setDaoName("lr" + MainActivityPU.this.tagH);
                                            if (MainActivityPU.this.tagH == 1) {
                                                defaultDao.setNumber(8L);
                                            }
                                            if (MainActivityPU.this.tagH == 2) {
                                                defaultDao.setNumber(9L);
                                            }
                                            if (MainActivityPU.this.tagH == 3) {
                                                defaultDao.setNumber(10L);
                                            }
                                            if (MainActivityPU.this.tagH == 4) {
                                                defaultDao.setNumber(11L);
                                            }
                                            if (MainActivityPU.this.tagH == 5) {
                                                defaultDao.setNumber(12L);
                                            }
                                            MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                                        }
                                    } else {
                                        defaultDao.setDaoName("whole");
                                        defaultDao.setNumber(13L);
                                        MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                                    }
                                } else {
                                    defaultDao.setDaoName("high");
                                    defaultDao.setNumber(7L);
                                    MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                                }
                            } else {
                                defaultDao.setDaoName("hit" + MainActivityPU.this.tagHT);
                                if (MainActivityPU.this.tagHT == 1) {
                                    defaultDao.setNumber(4L);
                                }
                                if (MainActivityPU.this.tagHT == 2) {
                                    defaultDao.setNumber(5L);
                                }
                                if (MainActivityPU.this.tagHT == 3) {
                                    defaultDao.setNumber(6L);
                                }
                                MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                            }
                        } else {
                            defaultDao.setDaoName("fix" + MainActivityPU.this.tagFix);
                            if (MainActivityPU.this.tagFix == 1) {
                                defaultDao.setNumber(1L);
                            }
                            if (MainActivityPU.this.tagFix == 2) {
                                defaultDao.setNumber(2L);
                            }
                            if (MainActivityPU.this.tagFix == 3) {
                                defaultDao.setNumber(3L);
                            }
                            MainActivityPU.this.daoSession.insertOrReplace(defaultDao);
                        }
                    }
                    ShowHelper.toastShort(MainActivityPU.this, "Current values have been saved as default.");
                    MainActivityPU.this.savedefault.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                    MainActivityPU.this.saveColor = 0;
                    return;
                }
                ShowHelper.toastShort(MainActivityPU.this, "No change for default value.");
            }
        });
        this.start_layout = (LinearLayout) findViewById(R.id.start_layout);
        this.note_ud = (TextView) findViewById(R.id.note_ud);
        this.note_lr = (TextView) findViewById(R.id.note_lr);
        this.num_ud = (TextView) findViewById(R.id.num_ud);
        this.num_lr = (TextView) findViewById(R.id.num_lr);
        this.lr = BasicData7.a31[0];
        this.ud = BasicData7.a31[1];
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
        TextView textView = (TextView) findViewById(R.id.snum28);
        this.snum28 = textView;
        this.tvs = new TextView[]{this.snum1, this.snum2, this.snum3, this.snum4, this.snum5, this.snum6, this.snum7, this.snum8, this.snum9, this.snum10, this.snum11, this.snum12, this.snum13, this.snum14, this.snum15, this.snum16, this.snum17, this.snum18, this.snum19, this.snum20, this.snum21, this.snum22, this.snum23, this.snum24, this.snum25, this.snum26, this.snum27, textView};
        this.tvids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
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
        Button button2 = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.stop_ball);
        this.stop_ball = button3;
        button3.setOnClickListener(this);
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
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.pusun.pusuntennis.MainActivityPU.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 1) {
                    MainActivityPU mainActivityPU = MainActivityPU.this;
                    mainActivityPU.showPos(mainActivityPU.gr1);
                    return;
                }
                if (i == 2) {
                    MainActivityPU mainActivityPU2 = MainActivityPU.this;
                    mainActivityPU2.showPos(mainActivityPU2.gr2);
                    return;
                }
                if (i == 3) {
                    MainActivityPU mainActivityPU3 = MainActivityPU.this;
                    mainActivityPU3.showPos(mainActivityPU3.gr3);
                    return;
                }
                if (i == 4) {
                    MainActivityPU mainActivityPU4 = MainActivityPU.this;
                    mainActivityPU4.showPos(mainActivityPU4.gr4);
                    return;
                }
                if (i == 5) {
                    MainActivityPU mainActivityPU5 = MainActivityPU.this;
                    mainActivityPU5.showPos(mainActivityPU5.gr5);
                    return;
                }
                if (i == 6) {
                    MainActivityPU mainActivityPU6 = MainActivityPU.this;
                    mainActivityPU6.showPos(mainActivityPU6.gr6);
                    return;
                }
                if (i == 7) {
                    MainActivityPU mainActivityPU7 = MainActivityPU.this;
                    mainActivityPU7.showPos(mainActivityPU7.gr7);
                    return;
                }
                if (i == 8) {
                    MainActivityPU mainActivityPU8 = MainActivityPU.this;
                    mainActivityPU8.showPos(mainActivityPU8.gr8);
                    return;
                }
                if (i == 9) {
                    MainActivityPU mainActivityPU9 = MainActivityPU.this;
                    mainActivityPU9.showPos(mainActivityPU9.gr9);
                    return;
                }
                if (i == 10) {
                    MainActivityPU mainActivityPU10 = MainActivityPU.this;
                    mainActivityPU10.showPos(mainActivityPU10.gr10);
                } else if (i == 11) {
                    MainActivityPU mainActivityPU11 = MainActivityPU.this;
                    mainActivityPU11.showPos(mainActivityPU11.gr11);
                } else if (i == 12) {
                    MainActivityPU mainActivityPU12 = MainActivityPU.this;
                    mainActivityPU12.showPos(mainActivityPU12.gr12);
                } else {
                    MainActivityPU mainActivityPU13 = MainActivityPU.this;
                    mainActivityPU13.showPos(mainActivityPU13.poids);
                }
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
        this.u_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPU.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("up:1");
                    MainActivityPU.access$512(MainActivityPU.this, 100);
                    if (MainActivityPU.this.ud < 300) {
                        MainActivityPU.this.ud = 300;
                    }
                    if (MainActivityPU.this.ud > 4300) {
                        MainActivityPU.this.ud = 4300;
                    }
                    if (MainActivityPU.this.modeCate == 0 && ((MainActivityPU.this.modeNum == 1 || MainActivityPU.this.modeNum == 2) && MainActivityPU.this.ud > 3000)) {
                        MainActivityPU.this.ud = PathInterpolatorCompat.MAX_NUM_POINTS;
                    }
                    short s = (short) MainActivityPU.this.lr;
                    if (MainActivityPU.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPU.this.ud;
                    if (MainActivityPU.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                    MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.3.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount1 != null) {
                                MainActivityPU.this.timeCount1.cancel();
                            }
                            MainActivityPU.this.timeCount1 = MainActivityPU.this.new TimeCount1(20000L, 200L);
                            MainActivityPU.this.timeCount1.start();
                        }
                    }, 1L);
                    MainActivityPU.this.checkIfUpdate();
                } else if (action == 1) {
                    AppLog.e("touch up1");
                    MainActivityPU.this.isTouch = false;
                    if (MainActivityPU.this.timeCount1 != null) {
                        MainActivityPU.this.timeCount1.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.3.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount1 != null) {
                                MainActivityPU.this.timeCount1.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.d_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPU.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("down:1");
                    MainActivityPU.access$520(MainActivityPU.this, 100);
                    if (MainActivityPU.this.ud < 300) {
                        MainActivityPU.this.ud = 300;
                    }
                    if (MainActivityPU.this.ud > 4300) {
                        MainActivityPU.this.ud = 4300;
                    }
                    if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 5 && MainActivityPU.this.ud < 2000) {
                        MainActivityPU.this.ud = 2000;
                    }
                    short s = (short) MainActivityPU.this.lr;
                    if (MainActivityPU.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPU.this.ud;
                    if (MainActivityPU.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                    MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.4.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount2 != null) {
                                MainActivityPU.this.timeCount2.cancel();
                            }
                            MainActivityPU.this.timeCount2 = MainActivityPU.this.new TimeCount2(20000L, 200L);
                            MainActivityPU.this.timeCount2.start();
                        }
                    }, 1L);
                    MainActivityPU.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPU.this.isTouch = false;
                    if (MainActivityPU.this.timeCount2 != null) {
                        MainActivityPU.this.timeCount2.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.4.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount2 != null) {
                                MainActivityPU.this.timeCount2.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.l_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPU.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("left:1");
                    MainActivityPU.access$412(MainActivityPU.this, 30);
                    if (MainActivityPU.this.lr < 210) {
                        MainActivityPU.this.lr = 210;
                    }
                    if (MainActivityPU.this.lr > 2070) {
                        MainActivityPU.this.lr = 2070;
                    }
                    short s = (short) MainActivityPU.this.lr;
                    if (MainActivityPU.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPU.this.ud;
                    if (MainActivityPU.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                    MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.5.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount3 != null) {
                                MainActivityPU.this.timeCount3.cancel();
                            }
                            MainActivityPU.this.timeCount3 = MainActivityPU.this.new TimeCount3(20000L, 200L);
                            MainActivityPU.this.timeCount3.start();
                        }
                    }, 1L);
                    MainActivityPU.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPU.this.isTouch = false;
                    if (MainActivityPU.this.timeCount3 != null) {
                        MainActivityPU.this.timeCount3.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.5.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount3 != null) {
                                MainActivityPU.this.timeCount3.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.ri_ad.setOnTouchListener(new View.OnTouchListener() { // from class: com.pusun.pusuntennis.MainActivityPU.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    AppLog.e("right:1");
                    MainActivityPU.access$420(MainActivityPU.this, 30);
                    if (MainActivityPU.this.lr < 210) {
                        MainActivityPU.this.lr = 210;
                    }
                    if (MainActivityPU.this.lr > 2070) {
                        MainActivityPU.this.lr = 2070;
                    }
                    short s = (short) MainActivityPU.this.lr;
                    if (MainActivityPU.this.modeNum == 2) {
                        s = 0;
                    }
                    short s2 = (short) MainActivityPU.this.ud;
                    if (MainActivityPU.this.modeNum == 3) {
                        s2 = 0;
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                    MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                    MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.6.2
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount4 != null) {
                                MainActivityPU.this.timeCount4.cancel();
                            }
                            MainActivityPU.this.timeCount4 = MainActivityPU.this.new TimeCount4(20000L, 200L);
                            MainActivityPU.this.timeCount4.start();
                        }
                    }, 1L);
                    MainActivityPU.this.checkIfUpdate();
                } else if (action == 1) {
                    MainActivityPU.this.isTouch = false;
                    if (MainActivityPU.this.timeCount4 != null) {
                        MainActivityPU.this.timeCount4.cancel();
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.6.3
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            if (MainActivityPU.this.timeCount4 != null) {
                                MainActivityPU.this.timeCount4.cancel();
                            }
                        }
                    }, 200L);
                }
                return false;
            }
        });
        this.change_point.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPU.this.writeBleData(new byte[]{-86, 112, 3, Ascii.SYN, 5, Ascii.FF, 1, 0, 1, -91});
            }
        });
        this.change_get.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivityPU.this.writeBleData(new byte[]{-86, 113, 0, 0, 0, 0, 0, 0, 1, -91});
            }
        });
        IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) findViewById(R.id.freq);
        this.freq = indicatorSeekBar;
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPU.9
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar2) {
                int progress = indicatorSeekBar2.getProgress();
                if (progress > 9 && MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum != 1 && MainActivityPU.this.modeNum != 5 && MainActivityPU.this.modeNum != 7) {
                    indicatorSeekBar2.setProgress(9.0f);
                    progress = 9;
                }
                MainActivityPU.this.f_tv.setText("" + progress);
                int i = progress - 1;
                MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[i] ^ 97), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                MainActivityPU.this.checkIfUpdate();
            }
        });
        this.freqde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityPU.this.f_tv.getText().toString().trim()).intValue();
                if (intValue > 1) {
                    int i = intValue - 1;
                    MainActivityPU.this.f_tv.setText("" + i);
                    MainActivityPU.this.freq.setProgress((float) i);
                    int i2 = intValue + (-2);
                    MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        this.freqadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int intValue = Integer.valueOf(MainActivityPU.this.f_tv.getText().toString().trim()).intValue();
                if (intValue < 10) {
                    int i = intValue + 1;
                    if (i > 9 && MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum != 1 && MainActivityPU.this.modeNum != 5 && MainActivityPU.this.modeNum != 7) {
                        i = 9;
                    }
                    MainActivityPU.this.f_tv.setText("" + i);
                    MainActivityPU.this.freq.setProgress((float) i);
                    int i2 = i - 1;
                    MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[i2] ^ 97), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.11.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar2 = (IndicatorSeekBar) findViewById(R.id.rotatebar);
        this.rotatebar = indicatorSeekBar2;
        indicatorSeekBar2.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPU.12
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar3) {
                int progressVal = indicatorSeekBar3.getProgress();

                if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 8) {

                    indicatorSeekBar3.setProgress(0.0f);

                    progressVal = 0;

                }

                final int progress = progressVal;
                if (progress != 0 && MainActivityPU.this.velobar.getProgress() < 5) {
                    MainActivityPU.this.velobar.setProgress(5.0f);
                    MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                    MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[4]);
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 50L);
                if (progress < 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.12.2
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i) * 5), 0, 0, 0, 0, (byte) (((-i) * 5) ^ 96), -91});
                        }
                    }, 100L);
                }
                if (progress > 0) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.12.3
                        @Override // java.lang.Runnable
                        public void run() {
                            int i = progress;
                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i * 5), 0, 0, 0, 0, (byte) ((i * 5) ^ 99), -91});
                        }
                    }, 100L);
                }
                if (progress == 0) {
                    MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityPU.this.r_tv.setText("" + progress);
                MainActivityPU.this.checkIfUpdate();
            }
        });
        this.rode.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPU.this.rotatebar.getProgress();
                if (progress > -6) {
                    int iVal = progress - 1;

                    if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 8) {

                        MainActivityPU.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivityPU.this.velobar.getProgress() < 5) {
                        MainActivityPU.this.velobar.setProgress(5.0f);
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.13.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPU.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.13.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.13.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPU.this.r_tv.setText("" + i);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        this.roadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPU.this.rotatebar.getProgress();
                if (progress < 6) {
                    int iVal = progress + 1;

                    if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 8) {

                        MainActivityPU.this.rotatebar.setProgress(0.0f);

                        iVal = 0;

                    }

                    final int i = iVal;
                    if (i != 0 && MainActivityPU.this.velobar.getProgress() < 5) {
                        MainActivityPU.this.velobar.setProgress(5.0f);
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[4]);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.14.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 50L);
                    MainActivityPU.this.rotatebar.setProgress(i);
                    if (i < 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.14.2
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                            }
                        }, 100L);
                    }
                    if (i > 0) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.14.3
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2 = i;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                            }
                        }, 100L);
                    }
                    if (i == 0) {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPU.this.r_tv.setText("" + i);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        IndicatorSeekBar indicatorSeekBar3 = (IndicatorSeekBar) findViewById(R.id.velobar);
        this.velobar = indicatorSeekBar3;
        indicatorSeekBar3.setOnSeekChangeListener(new OnSeekChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPU.15
            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onSeeking(SeekParams seekParams) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
            }

            @Override // com.warkiz.widget.OnSeekChangeListener
            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar4) {
                int progress = indicatorSeekBar4.getProgress();
                if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 5 && progress > 6) {
                    indicatorSeekBar4.setProgress(6.0f);
                    progress = 6;
                }
                TextView textView12 = MainActivityPU.this.v_tv;
                StringBuilder sb = new StringBuilder("");
                int i = progress - 1;
                sb.append(MainActivityPU.this.veloTins[i]);
                textView12.setText(sb.toString());
                if (progress < 5 && MainActivityPU.this.rotatebar.getProgress() != 0) {
                    MainActivityPU.this.r_tv.setText("0");
                    MainActivityPU.this.rotatebar.setProgress(0.0f);
                    MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                }
                MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[i], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[i] ^ 99), -91});
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.15.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 100L);
                MainActivityPU.this.checkIfUpdate();
            }
        });
        this.spde.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPU.this.velobar.getProgress();
                if (progress > 1) {
                    int i = progress - 1;
                    TextView textView12 = MainActivityPU.this.v_tv;
                    StringBuilder sb = new StringBuilder("");
                    int i2 = progress - 2;
                    sb.append(MainActivityPU.this.veloTins[i2]);
                    textView12.setText(sb.toString());
                    MainActivityPU.this.velobar.setProgress(i);
                    if (i < 4 && MainActivityPU.this.rotatebar.getProgress() != 0) {
                        MainActivityPU.this.r_tv.setText("0");
                        MainActivityPU.this.rotatebar.setProgress(0.0f);
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[i2], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[i2] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.16.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        this.spadd.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int progress = MainActivityPU.this.velobar.getProgress();
                if (progress < 13) {
                    int i = progress + 1;
                    MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[progress]);
                    MainActivityPU.this.velobar.setProgress((float) i);
                    if (i < 4 && MainActivityPU.this.rotatebar.getProgress() != 0) {
                        MainActivityPU.this.r_tv.setText("0");
                        MainActivityPU.this.rotatebar.setProgress(0.0f);
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                    }
                    MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.17.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    MainActivityPU.this.checkIfUpdate();
                }
            }
        });
        this.layout_adjust = (RelativeLayout) findViewById(R.id.layout_adjust);
        this.tennipic2 = (RelativeLayout) findViewById(R.id.tennipic2);
        this.tennipic3 = (RelativeLayout) findViewById(R.id.tennipic3);
        this.tennipic4 = (RelativeLayout) findViewById(R.id.tennipic4);
        this.tennipic5 = (RelativeLayout) findViewById(R.id.tennipic5);
        this.blenoty = (TextView) findViewById(R.id.connect);
        this.select_dianwei = (TextView) findViewById(R.id.select_dianwei);
        this.switchbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPU.18
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    MainActivityPU.this.self.setVisibility(8);
                    MainActivityPU.this.group.setVisibility(0);
                    MainActivityPU.this.points.setVisibility(0);
                    MainActivityPU.this.hintpoints.setVisibility(0);
                    MainActivityPU.this.group_cate.setVisibility(0);
                    MainActivityPU.this.tennipic2.setVisibility(8);
                    MainActivityPU.this.tennipic3.setVisibility(8);
                    MainActivityPU.this.tennipic4.setVisibility(8);
                    MainActivityPU.this.tennipic5.setVisibility(8);
                    MainActivityPU.this.fourbtn.setVisibility(8);
                    MainActivityPU.this.bg_four.setVisibility(8);
                    MainActivityPU.this.bg_input.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) MainActivityPU.this.start_layout.getLayoutParams();
                    layoutParams.height = (int) (MainActivityPU.this.density * 36.0f);
                    MainActivityPU.this.start_layout.setLayoutParams(layoutParams);
                    MainActivityPU.this.start_layout.setGravity(17);
                    MainActivityPU.this.modeCate = 1;
                    return;
                }
                MainActivityPU.this.self.setVisibility(0);
                MainActivityPU.this.group.setVisibility(8);
                MainActivityPU.this.points.setVisibility(8);
                MainActivityPU.this.hintpoints.setVisibility(8);
                MainActivityPU.this.tennipic2.setVisibility(0);
                MainActivityPU.this.bg_input.setVisibility(8);
                MainActivityPU.this.group_cate.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) MainActivityPU.this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (MainActivityPU.this.density * 90.0f);
                MainActivityPU.this.start_layout.setLayoutParams(layoutParams2);
                MainActivityPU.this.start_layout.setGravity(83);
                MainActivityPU.this.fourbtn.setVisibility(0);
                MainActivityPU.this.bg_four.setVisibility(0);
                if (MainActivityPU.this.modeNum == 2) {
                    MainActivityPU.this.tennipic3.setVisibility(0);
                    MainActivityPU.this.tennipic4.setVisibility(8);
                    MainActivityPU.this.tennipic5.setVisibility(8);
                }
                if (MainActivityPU.this.modeNum == 3) {
                    MainActivityPU.this.tennipic3.setVisibility(8);
                    MainActivityPU.this.tennipic4.setVisibility(0);
                    MainActivityPU.this.tennipic5.setVisibility(8);
                }
                if (MainActivityPU.this.modeNum == 4) {
                    MainActivityPU.this.tennipic3.setVisibility(8);
                    MainActivityPU.this.tennipic4.setVisibility(8);
                    MainActivityPU.this.tennipic5.setVisibility(0);
                }
                MainActivityPU.this.modeCate = 0;
            }
        });
        this.delepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = MainActivityPU.this.daoSession.loadAll(SeleDao.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(MainActivityPU.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(MainActivityPU.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(MainActivityPU.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(MainActivityPU.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.19.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str) {
                            new ArrayList();
                            List loadAll2 = MainActivityPU.this.daoSession.loadAll(SeleDao.class);
                            for (int i = 0; i < loadAll2.size(); i++) {
                                if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                    MainActivityPU.this.daoSession.delete((SeleDao) loadAll2.get(i));
                                    ShowHelper.toastShort(MainActivityPU.this, MainActivityPU.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                MainActivityPU mainActivityPU = MainActivityPU.this;
                ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.no_route_name));
            }
        });
        this.lastpoints.setOnClickListener(new AnonymousClass20());
        this.savepoints.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPU.this.select_dianwei.getText() != null && !MainActivityPU.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    MainActivityPU.this.alert_dialog_input();
                } else {
                    MainActivityPU mainActivityPU = MainActivityPU.this;
                    ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.no_point_select));
                }
            }
        });
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance().enableLog(true).setReConnectCount(1, 5000L).setConnectOverTime(OkHttpUtils.DEFAULT_MILLISECONDS).setOperateTimeout(5000);
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().setAutoConnect(false).setScanTimeOut(5000L).build());
        this.blenoty.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MainActivityPU.this.blenoty.getText().toString().trim().contains(MainActivityPU.this.getResources().getString(R.string.disconnected))) {
                    BleManager.getInstance().disconnectAllDevice();
                    if (MainActivityPU.bleDevice != null) {
                        MainActivityPU.this.connect(MainActivityPU.bleDevice);
                    } else {
                        MainActivityPU.this.checkPermissions();
                    }
                } else {
                    BleManager.getInstance().disconnectAllDevice();
                    MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.disconnected));
                    MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_stop_selector));
                }
            }
        });
        this.rgheight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.pusun.pusuntennis.MainActivityPU.23
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio0 /* 2131362353 */:
                        MainActivityPU.this.radioNum = 0;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.sendBaseData(0);
                            }
                        }, 50L);
                        break;
                    case R.id.radio1 /* 2131362354 */:
                        MainActivityPU.this.radioNum = 1;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.3
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.4
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.sendBaseData(1);
                            }
                        }, 50L);
                        break;
                    case R.id.radio2 /* 2131362355 */:
                        MainActivityPU.this.radioNum = 2;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.5
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                            }
                        }, 5L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.23.6
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.sendBaseData(2);
                            }
                        }, 50L);
                        break;
                }
                MainActivityPU.this.checkIfUpdate();
            }
        });
        LocationManager locationManager = (LocationManager) getSystemService("location");
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            locationManager.requestLocationUpdates("gps", 1000L, 1.0f, new LocationListener() { // from class: com.pusun.pusuntennis.MainActivityPU.24
                @Override // android.location.LocationListener
                public void onLocationChanged(Location location) {
                }
            });
            locationUpdates(locationManager.getLastKnownLocation("gps"));
            connect(bleDevice);
            getWindow().setSoftInputMode(2);
            showTvs(this.fx1);
            getDefaultPoint1();
        }
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* renamed from: com.pusun.pusuntennis.MainActivityPU$20, reason: invalid class name */
    class AnonymousClass20 implements View.OnClickListener {
        AnonymousClass20() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            new ArrayList();
            List loadAll = MainActivityPU.this.daoSession.loadAll(SeleDao.class);
            if (loadAll != null && loadAll.size() != 0) {
                String[] strArr = new String[loadAll.size()];
                for (int size = loadAll.size() - 1; size >= 0; size--) {
                    strArr[(loadAll.size() - 1) - size] = ((SeleDao) loadAll.get(size)).getDaoName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityPU.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityPU.this.getResources().getString(R.string.select_route_name));
                optionPicker.setCancelText(MainActivityPU.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityPU.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        new ArrayList();
                        List loadAll2 = MainActivityPU.this.daoSession.loadAll(SeleDao.class);
                        for (int i = 0; i < loadAll2.size(); i++) {
                            if (((SeleDao) loadAll2.get(i)).getDaoName().equals(str)) {
                                MainActivityPU.this.select_dianwei.setText(((SeleDao) loadAll2.get(i)).getSeles());
                                String[] split = ((SeleDao) loadAll2.get(i)).getSeles().split(",");
                                MainActivityPU.this.selectPoints.clear();
                                for (String str2 : split) {
                                    MainActivityPU.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str2)));
                                }
                                MainActivityPU.this.showPoints();
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                                    }
                                }, 5L);
                                MainActivityPU.this.freq.setProgress(((SeleDao) loadAll2.get(i)).getFreq());
                                MainActivityPU.this.f_tv.setText("" + ((SeleDao) loadAll2.get(i)).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.2
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                MainActivityPU.this.velobar.setProgress((float) ((SeleDao) loadAll2.get(i)).getVelo());
                                MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[((SeleDao) loadAll2.get(i)).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.3
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                MainActivityPU.this.rotatebar.setProgress((float) ((SeleDao) loadAll2.get(i)).getRote());
                                final int rote = ((SeleDao) loadAll2.get(i)).getRote();
                                if (rote < 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.4
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.5
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i2 = rote;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                        }
                                    }, 120L);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.6
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, 120L);
                                }
                                MainActivityPU.this.r_tv.setText("" + rote);
                                int item1 = ((SeleDao) loadAll2.get(i)).getItem1();
                                if (item1 == 0) {
                                    MainActivityPU.this.rgheight.check(R.id.radio0);
                                }
                                if (item1 == 1) {
                                    MainActivityPU.this.rgheight.check(R.id.radio1);
                                }
                                if (item1 == 2) {
                                    MainActivityPU.this.rgheight.check(R.id.radio2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.20.1.7
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.sendBaseData(0);
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
            MainActivityPU mainActivityPU = MainActivityPU.this;
            ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.no_route_name));
        }
    }

    public void locationUpdates(Location location) {
        if (location != null) {
            AppLog.e("l:" + location.getLongitude() + "la:" + location.getLatitude());
            getAddress(location.getLongitude(), location.getLatitude());
            return;
        }
        AppLog.e("location:null");
    }

    private void getAddress(double d, double d2) {
        try {
            List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(d2, d, 1);
            if (fromLocation != null) {
                Address address = fromLocation.get(0);
                String countryName = address.getCountryName();
                String countryCode = address.getCountryCode();
                Log.d("TAG", "getAddress: " + countryCode);
                String adminArea = address.getAdminArea();
                address.getSubAdminArea();
                String subLocality = address.getSubLocality();
                String featureName = address.getFeatureName();
                address.getAddressLine(0);
                AppLog.e(countryName + adminArea + subLocality + featureName);
                AppLog.e(countryCode);
                if (countryName.contains("United States")) {
                    ShowHelper.showAlertDialogUS(this, "This machine is prohibited from use in the United States", new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.25
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EventBus.getDefault().unregister(this);
                            BleManager.getInstance().disconnectAllDevice();
                            MainActivityPU.this.finish();
                        }
                    });
                }
            }
        } catch (Exception e) {
            AppLog.e("exception：" + e.toString());
            e.printStackTrace();
        }
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
        if (this.selectPoints.size() != 0) {
            for (int i = 0; i < this.poids.length; i++) {
                this.pos[i].setBackground(getResources().getDrawable(R.drawable.pickle));
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < this.selectPoints.size(); i2++) {
                int i3 = 0;
                while (true) {
                    if (i3 >= this.poids.length) {
                        break;
                    }
                    if (this.selectPoints.get(i2).intValue() == this.poids[i3] + 1) {
                        this.pos[i3].setBackground(getResources().getDrawable(R.drawable.pickle_sele));
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
            this.pos[i4].setBackground(getResources().getDrawable(R.drawable.pickle));
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
        while (i < BasicData7.b2.length) {
            final int final_i = i;
            int i2 = i + 1;
            byte b = (byte) i2;
            final byte[] bArr = {-86, b, (byte) (BasicData7.b2[i][0] >> 8), (byte) BasicData7.b2[i][0], (byte) (BasicData7.b2[i][1] >> 8), (byte) BasicData7.b2[i][1], 0, 0, (byte) ((((((byte) (BasicData7.b2[i][0] >> 8)) ^ b) ^ ((byte) BasicData7.b2[i][0])) ^ ((byte) (BasicData7.b2[i][1] >> 8))) ^ ((byte) BasicData7.b2[i][1])), -91};
            AppLog.e("左右：" + ((int) BasicData7.b2[i][0]) + "上下：" + ((int) BasicData7.b2[i][1]) + "byte:" + bytesToHexString(bArr));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.26
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i + "条指令");
                    MainActivityPU.this.writeBleData(bArr);
                }
            }, (long) (i * 10));
            i = i2;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.27
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i3 = 20;
                while (i3 < BasicData7.b2.length) {
                    int i4 = i3 + 1;
                    byte b2 = (byte) i4;
                    final byte[] bArr2 = {-86, b2, (byte) (BasicData7.b2[i3][0] >> 8), (byte) BasicData7.b2[i3][0], (byte) (BasicData7.b2[i3][1] >> 8), (byte) BasicData7.b2[i3][1], 0, 0, (byte) ((((((byte) (BasicData7.b2[i3][0] >> 8)) ^ b2) ^ ((byte) BasicData7.b2[i3][0])) ^ ((byte) (BasicData7.b2[i3][1] >> 8))) ^ ((byte) BasicData7.b2[i3][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData7.b2[i3][0]) + "上下：" + ((int) BasicData7.b2[i3][1]) + "byte:" + MainActivityPU.bytesToHexString(bArr2));
                final int final_i3 = i3;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.27.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i3 + "条指令");
                            MainActivityPU.this.writeBleData(bArr2);
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
            int i2 = 0;
            while (i2 < BasicData7.b3.length) {
                int i3 = i2 + 1;
                byte b = (byte) i3;
                final byte[] bArr = {-86, b, (byte) (BasicData7.b3[i2][0] >> s), (byte) BasicData7.b3[i2][0], (byte) (BasicData7.b3[i2][1] >> 8), (byte) BasicData7.b3[i2][1], 0, 0, (byte) ((((((byte) (BasicData7.b3[i2][0] >> 8)) ^ b) ^ ((byte) BasicData7.b3[i2][0])) ^ ((byte) (BasicData7.b3[i2][1] >> s))) ^ ((byte) BasicData7.b3[i2][1])), -91};
                AppLog.e("左右：" + ((int) BasicData7.b3[i2][0]) + "上下：" + ((int) BasicData7.b3[i2][1]) + "byte:" + bytesToHexString(bArr));
                final int final_i2 = i2;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.28
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        AppLog.e("第" + final_i2 + "条指令");
                        MainActivityPU.this.writeBleData(bArr);
                    }
                }, (long) (i2 * 10));
                i2 = i3;
                s = 8;
            }
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.29
                @Override // java.lang.Runnable
                public synchronized void run() {
                    int i4 = 20;
                    while (i4 < BasicData7.b3.length) {
                        int i5 = i4 + 1;
                        byte b2 = (byte) i5;
                        final byte[] bArr2 = {-86, b2, (byte) (BasicData7.b3[i4][0] >> 8), (byte) BasicData7.b3[i4][0], (byte) (BasicData7.b3[i4][1] >> 8), (byte) BasicData7.b3[i4][1], 0, 0, (byte) ((((((byte) (BasicData7.b3[i4][0] >> 8)) ^ b2) ^ ((byte) BasicData7.b3[i4][0])) ^ ((byte) (BasicData7.b3[i4][1] >> 8))) ^ ((byte) BasicData7.b3[i4][1])), -91};
                        AppLog.e("左右：" + ((int) BasicData7.b3[i4][0]) + "上下：" + ((int) BasicData7.b3[i4][1]) + "byte:" + MainActivityPU.bytesToHexString(bArr2));
                final int final_i4 = i4;
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.29.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                AppLog.e("第" + final_i4 + "条指令");
                                MainActivityPU.this.writeBleData(bArr2);
                            }
                        }, (long) (i4 * 10));
                        i4 = i5;
                    }
                }
            }, 900L);
            return;
        }
        int i4 = 0;
        while (i4 < BasicData7.b4.length) {
            int i5 = i4 + 1;
            byte b2 = (byte) i5;
            byte b3 = (byte) ((((((byte) (BasicData7.b4[i4][0] >> 8)) ^ b2) ^ ((byte) BasicData7.b4[i4][0])) ^ ((byte) (BasicData7.b4[i4][c] >> 8))) ^ ((byte) BasicData7.b4[i4][c]));
            byte b4 = (byte) (BasicData7.b4[i4][0] >> 8);
            byte b5 = (byte) BasicData7.b4[i4][0];
            byte b6 = (byte) (BasicData7.b4[i4][c] >> 8);
            byte b7 = (byte) BasicData7.b4[i4][c];
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
            AppLog.e("左右：" + ((int) BasicData7.b4[i4][0]) + "上下：" + ((int) BasicData7.b4[i4][c]) + "byte:" + bytesToHexString(bArr2));
                final int final_i4 = i4;
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.30
                @Override // java.lang.Runnable
                public synchronized void run() {
                    AppLog.e("第" + final_i4 + "条指令");
                    MainActivityPU.this.writeBleData(bArr2);
                }
            }, (long) (i4 * 10));
            i4 = i5;
            c = 1;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.31
            @Override // java.lang.Runnable
            public synchronized void run() {
                int i6 = 20;
                while (i6 < BasicData7.b4.length) {
                    int i7 = i6 + 1;
                    byte b8 = (byte) i7;
                    final byte[] bArr3 = {-86, b8, (byte) (BasicData7.b4[i6][0] >> 8), (byte) BasicData7.b4[i6][0], (byte) (BasicData7.b4[i6][1] >> 8), (byte) BasicData7.b4[i6][1], 0, 0, (byte) ((((((byte) (BasicData7.b4[i6][0] >> 8)) ^ b8) ^ ((byte) BasicData7.b4[i6][0])) ^ ((byte) (BasicData7.b4[i6][1] >> 8))) ^ ((byte) BasicData7.b4[i6][1])), -91};
                    AppLog.e("左右：" + ((int) BasicData7.b4[i6][0]) + "上下：" + ((int) BasicData7.b4[i6][1]) + "byte:" + MainActivityPU.bytesToHexString(bArr3));
                final int final_i6 = i6;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.31.1
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            AppLog.e("第" + final_i6 + "条指令");
                            MainActivityPU.this.writeBleData(bArr3);
                        }
                    }, (long) (i6 * 10));
                    i6 = i7;
                }
            }
        }, 900L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeBleData(byte[] bArr) {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PP0") || this.nameStar.startsWith("PP1"))) {
            BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUIDA, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUIDA, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.32
                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteFailure(BleException bleException) {
                }

                @Override // com.clj.fastble.callback.BleWriteCallback
                public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                    MainActivityPU mainActivityPU = MainActivityPU.this;
                    ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.order_executed));
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null) {
            if (str2.startsWith("PP2") || this.nameStar.startsWith("PP3")) {
                BleManager.getInstance().write(bleDevice, BLEServiceParameters.BLE_WRITE_SERVICE_UUID, BLEServiceParameters.BLE_WRITE_SERVICE_CHARACTER_UUID, bArr, new BleWriteCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.33
                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteFailure(BleException bleException) {
                    }

                    @Override // com.clj.fastble.callback.BleWriteCallback
                    public void onWriteSuccess(int i, int i2, byte[] bArr2) {
                        MainActivityPU mainActivityPU = MainActivityPU.this;
                        ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.order_executed));
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
        BleManager.getInstance().scan(new BleScanCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.34
            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanStarted(boolean z) {
                MainActivityPU mainActivityPU = MainActivityPU.this;
                ShowHelper.showProgressDialog(mainActivityPU, mainActivityPU.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanPresenterImp
            public void onScanning(BleDevice bleDevice2) {
                MainActivityPU mainActivityPU = MainActivityPU.this;
                ShowHelper.showProgressDialog(mainActivityPU, mainActivityPU.getResources().getString(R.string.scanning));
            }

            @Override // com.clj.fastble.callback.BleScanCallback
            public void onScanFinished(List<BleDevice> list) {
                if (list == null || list.size() == 0) {
                    ShowHelper.dismissProgressDialog();
                    MainActivityPU mainActivityPU = MainActivityPU.this;
                    ShowHelper.toastLong(mainActivityPU, mainActivityPU.getResources().getString(R.string.no_device_found));
                    if (MainActivityPU.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.34.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.startScan();
                            }
                        }, 1000L);
                        MainActivityPU.access$6608(MainActivityPU.this);
                        return;
                    }
                    return;
                }
                ShowHelper.dismissProgressDialog();
                if (list.size() == 1 && list.get(0).getName() != null && list.get(0).getName().startsWith("PP")) {
                    MainActivityPU.this.connect(list.get(0));
                    return;
                }
                final ArrayList arrayList = new ArrayList();
                arrayList.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName() != null && list.get(i).getName().trim().contains("PP")) {
                        arrayList.add(list.get(i));
                    }
                }
                if (arrayList.size() == 0) {
                    MainActivityPU mainActivityPU2 = MainActivityPU.this;
                    ShowHelper.toastLong(mainActivityPU2, mainActivityPU2.getResources().getString(R.string.no_device_found));
                    if (MainActivityPU.this.connNum < 3) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.34.2
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.startScan();
                            }
                        }, 1000L);
                        MainActivityPU.access$6608(MainActivityPU.this);
                        return;
                    }
                    return;
                }
                if (arrayList.size() == 1) {
                    MainActivityPU.this.connect((BleDevice) arrayList.get(0));
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    strArr[i2] = ((BleDevice) arrayList.get(i2)).getName();
                }
                OptionPicker optionPicker = new OptionPicker(MainActivityPU.this, strArr);
                optionPicker.setOffset(2);
                optionPicker.setSelectedIndex(0);
                optionPicker.setTextSize(18);
                optionPicker.setTitleText(MainActivityPU.this.getResources().getString(R.string.select_device));
                optionPicker.setCancelText(MainActivityPU.this.getResources().getString(R.string.cancel));
                optionPicker.setSubmitText(MainActivityPU.this.getResources().getString(R.string.submit));
                optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.34.3
                    @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                    public void onOptionPicked(String str) {
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            if (((BleDevice) arrayList.get(i3)).getName().equals(str)) {
                                MainActivityPU.this.connect((BleDevice) arrayList.get(i3));
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
                        MainActivityPU mainActivity = MainActivityPU.this;
                        ShowHelper.showProgressDialog(mainActivity, mainActivity.getResources().getString(R.string.connecting_device));
                    }
                    @Override
                    public void onConnectFail(BleDevice bleDevice3, BleException bleException) {
                        MainActivityPU mainActivity = MainActivityPU.this;
                        ShowHelper.toastLong(mainActivity, mainActivity.getResources().getString(R.string.connect_failure_check));
                        ShowHelper.dismissProgressDialog();
                        MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.disconnected));
                        MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivityPU.this.signal_note.setText(MainActivityPU.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                    }
                    @Override
                    public void onConnectSuccess(BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        ShowHelper.setProgressDialogMessage(MainActivityPU.this.getResources().getString(R.string.initializing));
                        MainActivityPU.this.connNum = 0;
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public synchronized void run() {
                                ShowHelper.dismissProgressDialog();
                                ShowHelper.toastShort(MainActivityPU.this, MainActivityPU.this.getResources().getString(R.string.please_use));
                            }
                        }, com.google.android.exoplayer2.C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        String resolvedName = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                        if (resolvedName == null || resolvedName.isEmpty()) resolvedName = MainActivityPU.this.deviceName != null ? MainActivityPU.this.deviceName : "";
                        MainActivityPU.this.nameStar = resolvedName;
                        MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.connected));
                        MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_selector));
                        MainActivityPU.this.signal_note.setText(MainActivityPU.this.nameStar + MainActivityPU.this.getResources().getString(R.string.connected));
                        MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_green));
                        MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_blue));
                        MainActivityPU.this.isFaultOn = 0;
                        MainActivityPU.this.gatt = bluetoothGatt;
                        MainActivityPU.bleDevice = bleDevice3;
                        if (bleDevice3.getMac() != null) MainActivityPU.this.deviceMac = bleDevice3.getMac();
                        MainActivityPU.this.startNotify();
                    }
                    @Override
                    public void onDisConnected(boolean z, final BleDevice bleDevice3, android.bluetooth.BluetoothGatt bluetoothGatt, int i) {
                        MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.disconnected));
                        MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_stop_selector));
                        MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_gray));
                        MainActivityPU.this.signal_note.setText(MainActivityPU.this.getResources().getString(R.string.device_is_disconnect));
                        MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_gray));
                        BleManager.getInstance().disconnectAllDevice();
                        MainActivityPU.this.isFaultOn = 0;
                        if (z || MainActivityPU.this.connNum >= 3) { return; }
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override public void run() { MainActivityPU.this.connect(bleDevice3); }
                        }, 1000L);
                    }
                });
            } else {
                android.util.Log.e("MainActivityPU", "connect: bleDevice and mac are both null, cannot connect");
            }
            return;
        }
        BleManager.getInstance().connect(bleDevice2, new BleGattCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.35
            @Override // com.clj.fastble.callback.BleGattCallback
            public void onStartConnect() {
                MainActivityPU mainActivityPU = MainActivityPU.this;
                ShowHelper.showProgressDialog(mainActivityPU, mainActivityPU.getResources().getString(R.string.connecting_device));
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectFail(final BleDevice bleDevice3, BleException bleException) {
                MainActivityPU mainActivityPU = MainActivityPU.this;
                ShowHelper.toastLong(mainActivityPU, mainActivityPU.getResources().getString(R.string.connect_failure_check));
                ShowHelper.dismissProgressDialog();
                MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.disconnected));
                MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityPU.this.signal_note.setText(MainActivityPU.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                if (MainActivityPU.this.connNum >= 3 || bleDevice3 == null) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivityPU.access$6608(MainActivityPU.this);
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onConnectSuccess(BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                ShowHelper.setProgressDialogMessage(MainActivityPU.this.getResources().getString(R.string.initializing));
                MainActivityPU.this.connNum = 0;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.2
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        ShowHelper.dismissProgressDialog();
                        ShowHelper.toastShort(MainActivityPU.this, MainActivityPU.this.getResources().getString(R.string.please_use));
                    }
                }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                String rawNameMainAc = com.pusun.pusuntennis.utils.Util.getDeviceName(bleDevice3);
                if (rawNameMainAc == null || rawNameMainAc.isEmpty()) rawNameMainAc = MainActivityPU.this.deviceName != null ? MainActivityPU.this.deviceName : "";
                MainActivityPU.this.nameStar = rawNameMainAc;
                MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.connected));
                MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_selector));
                MainActivityPU.this.signal_note.setText(MainActivityPU.this.nameStar + MainActivityPU.this.getResources().getString(R.string.connected));
                MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_green));
                MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_blue));
                MainActivityPU.this.isFaultOn = 0;
                MainActivityPU.this.gatt = bluetoothGatt;
                MainActivityPU.bleDevice = bleDevice3;
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.3
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.sendBaseData(MainActivityPU.this.radioNum);
                    }
                }, 1500L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.4
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                    }
                }, 3200L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.5
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                    }
                }, 3350L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.6
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 100, (byte) 8, (byte) 2070, (byte) 0, (byte) 210, 0, 0, 1, -91});
                    }
                }, 3400L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.7
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 101, (byte) 3, (byte) 1000, (byte) 2, (byte) 700, 0, 0, 1, -91});
                    }
                }, 3450L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.8
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                    }
                }, 3500L);
                MainActivityPU.this.getDefaultPoint1();
                MainActivityPU.this.startNotify();
            }

            @Override // com.clj.fastble.callback.BleGattCallback
            public void onDisConnected(boolean z, final BleDevice bleDevice3, BluetoothGatt bluetoothGatt, int i) {
                MainActivityPU.this.blenoty.setText(MainActivityPU.this.getResources().getString(R.string.disconnected));
                MainActivityPU.this.blenoty.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.button_stop_selector));
                MainActivityPU.this.signal.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.bicon_gray));
                MainActivityPU.this.signal_note.setText(MainActivityPU.this.getResources().getString(R.string.device_is_disconnect));
                MainActivityPU.this.signal_note.setTextColor(MainActivityPU.this.getResources().getColor(R.color.icon_gray));
                BleManager.getInstance().disconnectAllDevice();
                MainActivityPU.this.isFaultOn = 0;
                if (z || MainActivityPU.this.connNum >= 3) {
                    return;
                }
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.35.9
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.connect(bleDevice3);
                    }
                }, 1000L);
                MainActivityPU.access$6608(MainActivityPU.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDefaultPoint1() {
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36
            @Override // java.lang.Runnable
            public void run() {
                MainActivityPU.this.defaultDaoList = new ArrayList();
                MainActivityPU.this.savedefault.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.corner_dark_green_default));
                MainActivityPU.this.saveColor = 1;
                MainActivityPU mainActivityPU = MainActivityPU.this;
                mainActivityPU.defaultDaoList = mainActivityPU.daoSession.loadAll(DefaultDao.class);
                for (int i = 0; i < MainActivityPU.this.defaultDaoList.size(); i++) {
                    if (MainActivityPU.this.defaultDaoList.get(i).getDaoName() != null && MainActivityPU.this.defaultDaoList.get(i).getDaoName().equals("fix1")) {
                        MainActivityPU.this.savedefault.setBackground(MainActivityPU.this.getResources().getDrawable(R.drawable.code_button_bg_default));
                        MainActivityPU.this.saveColor = 0;
                        MainActivityPU mainActivityPU2 = MainActivityPU.this;
                        mainActivityPU2.lr = mainActivityPU2.defaultDaoList.get(i).getItem2();
                        MainActivityPU mainActivityPU3 = MainActivityPU.this;
                        mainActivityPU3.ud = mainActivityPU3.defaultDaoList.get(i).getItem3();
                        short s = (short) MainActivityPU.this.lr;
                        short s2 = (short) MainActivityPU.this.ud;
                        MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                        MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                        MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                        MainActivityPU.this.freq.setProgress((float) MainActivityPU.this.defaultDaoList.get(i).getFreq());
                        MainActivityPU.this.f_tv.setText("" + MainActivityPU.this.defaultDaoList.get(i).getFreq());
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36.1
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityPU.this.freq.getProgress() - 1;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                            }
                        }, 30L);
                        MainActivityPU.this.velobar.setProgress((float) MainActivityPU.this.defaultDaoList.get(i).getVelo());
                        MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[MainActivityPU.this.defaultDaoList.get(i).getVelo() - 1]);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36.2
                            @Override // java.lang.Runnable
                            public synchronized void run() {
                                int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                            }
                        }, 80L);
                        MainActivityPU.this.rotatebar.setProgress((float) MainActivityPU.this.defaultDaoList.get(i).getRote());
                        final int rote = MainActivityPU.this.defaultDaoList.get(i).getRote();
                        if (rote < 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i2) * 5), 0, 0, 0, 0, (byte) (((-i2) * 5) ^ 96), -91});
                                }
                            }, 120L);
                        }
                        if (rote > 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36.4
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i2 = rote;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i2 * 5), 0, 0, 0, 0, (byte) ((i2 * 5) ^ 99), -91});
                                }
                            }, 120L);
                        }
                        if (rote == 0) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.36.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                }
                            }, 120L);
                        }
                        MainActivityPU.this.r_tv.setText("" + rote);
                        return;
                    }
                }
            }
        }, 3650L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNotify() {
        String str = this.nameStar;
        if (str != null && (str.startsWith("PP0") || this.nameStar.startsWith("PP1"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUIDA, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUIDA, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.37
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
                        MainActivityPU.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityPU.this.isFaultOn == 0) {
                        MainActivityPU.access$7208(MainActivityPU.this);
                        MainActivityPU.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        String str2 = this.nameStar;
        if (str2 != null && (str2.startsWith("PP2") || this.nameStar.startsWith("PP3"))) {
            BleManager.getInstance().notify(bleDevice, BLEServiceParameters.BLE_NOTIFY_SERVICE_UUID, BLEServiceParameters.BLE_NOTIFY_SERVICE_CHARACTERISTIC_UUID, new BleNotifyCallback() { // from class: com.pusun.pusuntennis.MainActivityPU.38
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
                        MainActivityPU.this.batteryVolumeMsg(bArr[2] & 255);
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 48) {
                        AppLog.e("va1:" + (bArr[2] & 255) + "va2：" + (bArr[3] & 255));
                    }
                    if (bArr.length > 2 && (bArr[1] & 255) == 94 && MainActivityPU.this.isFaultOn == 0) {
                        MainActivityPU.access$7208(MainActivityPU.this);
                        MainActivityPU.this.faultMsg(bArr[2] & 255);
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.39
            @Override // java.lang.Runnable
            public synchronized void run() {
                MainActivityPU.this.checkPower();
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
    }

    @Subcriber
    private void dealBatteryVolumeEvent(BatteryVolumeMsg batteryVolumeMsg) {
        this.vo_tv.setText("" + batteryVolumeMsg.volume);
        int i = batteryVolumeMsg.volume;
        if (i < 35) {
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
        if (i < 50 && i >= 35) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(8);
            this.power3.setVisibility(8);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i < 65 && i >= 50) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(8);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i < 80 && i >= 65) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(0);
            this.power4.setVisibility(8);
            this.power_no.setVisibility(8);
        }
        if (i >= 80) {
            this.power1.setVisibility(0);
            this.power2.setVisibility(0);
            this.power3.setVisibility(0);
            this.power4.setVisibility(0);
            this.power_no.setVisibility(8);
        }
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
                if (this.rgheight.getVisibility() == 0 && this.radioNum != this.defaultDaoList.get(i).getGrade()) {
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

    private void changeOperate() {
        if (this.modeCate == 1) {
            this.savedefault.setVisibility(8);
            this.points.setVisibility(0);
            this.group_cate.setVisibility(8);
            String str = this.nameStar;
            if (str != null && (str.startsWith("PP1") || this.nameStar.startsWith("PP2"))) {
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
        this.savedefault.setVisibility(0);
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

    public static class GpsUtils {
        public static String getCountryNameFromGps(Context context, Location location) {
            if (location == null) {
                return null;
            }
            try {
                List<Address> fromLocation = new Geocoder(context, Locale.getDefault()).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (fromLocation == null || fromLocation.isEmpty()) {
                    return null;
                }
                Address address = fromLocation.get(0);
                if (address.getMaxAddressLineIndex() != -1) {
                    return address.getCountryName();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
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
                new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.notice)).setMessage(getResources().getString(R.string.blue_need_setting)).setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.41
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityPU.this.finish();
                    }
                }).setPositiveButton(getResources().getString(R.string.go_setting), new DialogInterface.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.40
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivityPU.this.startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 1);
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
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        int id = view.getId();
        switch (id) {
            case R.id.btn_ball /* 2131361912 */:
                if (this.blenoty.getText().toString().equals(getResources().getString(R.string.disconnected))) {
                    ShowHelper.toastShort(this, getResources().getString(R.string.check_connect));
                    break;
                } else if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                    if (this.modeCate == 0) {
                        this.savedefault.setVisibility(0);
                        int i = this.modeNum;
                        if (i == 1 || i == 7) {
                            AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.116
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                                writeBleData(new byte[]{-86, 106, 3, 4, Ascii.VT, Ascii.DC2, Ascii.EM, 0, 109, -91});
                            }
                            if (this.tagV == 3) {
                                writeBleData(new byte[]{-86, 106, 3, 4, Ascii.EM, 0, 0, 0, 116, -91});
                            }
                        }
                        if (this.modeNum == 4) {
                            writeBleData(new byte[]{-86, 106, 4, 0, 0, 0, 0, 0, 110, -91});
                        }
                        int i2 = this.modeNum;
                        if (i2 == 5 || i2 == 8 || i2 == 9) {
                            AppLog.e("isN:" + this.isNumDing + "sM:" + this.stopMode);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.117
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 200L);
                        }
                        if (this.modeNum == 6) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.118
                                @Override // java.lang.Runnable
                                public void run() {
                                    int i3;
                                    int unused = MainActivityPU.this.tagC;
                                    int i4 = 28;
                                    int i5 = 22;
                                    int i6 = MainActivityPU.this.tagC == 2 ? 28 : 22;
                                    int i7 = 25;
                                    if (MainActivityPU.this.tagC == 3) {
                                        i6 = 25;
                                        i3 = 1;
                                    } else {
                                        i3 = 4;
                                    }
                                    if (MainActivityPU.this.tagC == 4) {
                                        i3 = 7;
                                    } else {
                                        i7 = i6;
                                    }
                                    if (MainActivityPU.this.tagC == 5) {
                                        i3 = 7;
                                    } else {
                                        i5 = i7;
                                    }
                                    if (MainActivityPU.this.tagC == 6) {
                                        i3 = 1;
                                    } else {
                                        i4 = i5;
                                    }
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 1, (byte) i4, (byte) i3, 0, 0, 0, (byte) ((i4 ^ 108) ^ i3), -91});
                                }
                            }, 5L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.119
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 50L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.120
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                                }
                            }, 80L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.121
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                                }
                            }, 110L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.122
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                                }
                            }, 140L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.123
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                                }
                            }, 170L);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.124
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                                }
                            }, 250L);
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
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.125
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 1, (byte) MainActivityPU.this.t1, (byte) MainActivityPU.this.t2, (byte) MainActivityPU.this.t3, (byte) MainActivityPU.this.t4, (byte) MainActivityPU.this.t5, (byte) (((((MainActivityPU.this.t1 ^ 108) ^ MainActivityPU.this.t2) ^ MainActivityPU.this.t3) ^ MainActivityPU.this.t4) ^ MainActivityPU.this.t5), -91});
                            }
                        }, 5L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.126
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 2, (byte) MainActivityPU.this.t6, (byte) MainActivityPU.this.t7, (byte) MainActivityPU.this.t8, (byte) MainActivityPU.this.t9, (byte) MainActivityPU.this.t10, (byte) (((((MainActivityPU.this.t6 ^ 111) ^ MainActivityPU.this.t7) ^ MainActivityPU.this.t8) ^ MainActivityPU.this.t9) ^ MainActivityPU.this.t10), -91});
                            }
                        }, 50L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.127
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 3, (byte) MainActivityPU.this.t11, (byte) MainActivityPU.this.t12, (byte) MainActivityPU.this.t13, (byte) MainActivityPU.this.t14, (byte) MainActivityPU.this.t15, (byte) (((((MainActivityPU.this.t11 ^ 110) ^ MainActivityPU.this.t12) ^ MainActivityPU.this.t13) ^ MainActivityPU.this.t14) ^ MainActivityPU.this.t15), -91});
                            }
                        }, 100L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.128
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 4, (byte) MainActivityPU.this.t16, (byte) MainActivityPU.this.t17, (byte) MainActivityPU.this.t18, (byte) MainActivityPU.this.t19, (byte) MainActivityPU.this.t20, (byte) (((((MainActivityPU.this.t16 ^ 105) ^ MainActivityPU.this.t17) ^ MainActivityPU.this.t18) ^ MainActivityPU.this.t19) ^ MainActivityPU.this.t20), -91});
                            }
                        }, 150L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.129
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 5, (byte) MainActivityPU.this.t21, (byte) MainActivityPU.this.t22, (byte) MainActivityPU.this.t23, (byte) MainActivityPU.this.t24, (byte) MainActivityPU.this.t25, (byte) (((((MainActivityPU.this.t21 ^ 104) ^ MainActivityPU.this.t22) ^ MainActivityPU.this.t23) ^ MainActivityPU.this.t24) ^ MainActivityPU.this.t25), -91});
                            }
                        }, 200L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.130
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 109, 6, (byte) MainActivityPU.this.t26, (byte) MainActivityPU.this.t27, (byte) MainActivityPU.this.t28, 0, 0, (byte) (((MainActivityPU.this.t26 ^ 107) ^ MainActivityPU.this.t27) ^ MainActivityPU.this.t28), -91});
                            }
                        }, 250L);
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.131
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
                            }
                        }, 320L);
                    }
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.132
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 270L);
                    this.btn_ball.setText(getResources().getString(R.string.stop));
                    this.signal_note.setText(getResources().getString(R.string.status_on));
                    this.signal_note.setTextColor(getResources().getColor(R.color.icon_green));
                    this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                    break;
                } else {
                    this.stopMode = this.modeNum;
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.133
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 260L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.134
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.checkPower();
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
                int i4 = this.tagV + 1;
                this.tagV = i4;
                if (i4 > 3) {
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
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                this.lr = BasicData7.a31[0];
                short s = BasicData7.a31[1];
                this.ud = s;
                short s2 = (short) this.lr;
                short s3 = s;
                writeBleData(new byte[]{-86, 108, (byte) (s2 >> 8), (byte) s2, (byte) (s3 >> 8), (byte) s3, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                hideUD();
                if (this.modeNum != 3) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.58
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
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
                this.daoNameIng = "ud" + this.tagV;
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i5 = 0; i5 < this.defaultDaoList.size(); i5++) {
                        if (this.defaultDaoList.get(i5).getDaoName() != null) {
                            if (this.defaultDaoList.get(i5).getDaoName().equals("ud" + this.tagV)) {
                                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                                this.saveColor = 0;
                                this.lr = this.defaultDaoList.get(i5).getItem2();
                                int item3 = this.defaultDaoList.get(i5).getItem3();
                                this.ud = item3;
                                short s4 = (short) this.lr;
                                short s5 = (short) item3;
                                writeBleData(new byte[]{-86, 108, (byte) (s4 >> 8), (byte) s4, (byte) (s5 >> 8), (byte) s5, 0, 0, 1, -91});
                                this.num_lr.setText("" + ((this.lr / 30) + 2));
                                this.num_ud.setText("" + (this.ud / 100));
                                this.freq.setProgress((float) this.defaultDaoList.get(i5).getFreq());
                                this.f_tv.setText("" + this.defaultDaoList.get(i5).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.59
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                this.velobar.setProgress((float) this.defaultDaoList.get(i5).getVelo());
                                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i5).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.60
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                this.rotatebar.setProgress((float) this.defaultDaoList.get(i5).getRote());
                                final int rote = this.defaultDaoList.get(i5).getRote();
                                if (rote < 0) {
                                    j = 120;
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.61
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i6 = rote;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i6) * 5), 0, 0, 0, 0, (byte) (((-i6) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                } else {
                                    j = 120;
                                }
                                if (rote > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.62
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i6 = rote;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i6 * 5), 0, 0, 0, 0, (byte) ((i6 * 5) ^ 99), -91});
                                        }
                                    }, j);
                                }
                                if (rote == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.63
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, j);
                                }
                                this.r_tv.setText("" + rote);
                                final int grade = this.defaultDaoList.get(i5).getGrade();
                                this.radioNum = grade;
                                if (grade == 0) {
                                    this.rgheight.check(R.id.radio0);
                                }
                                if (grade == 1) {
                                    this.rgheight.check(R.id.radio1);
                                }
                                if (grade == 2) {
                                    this.rgheight.check(R.id.radio2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.64
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.sendBaseData(grade);
                                    }
                                }, 160L);
                            }
                        }
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.65
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.66
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivityPU.this.tagV == 1) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 3, 0, 0, 0, 0, 0, 105, -91});
                                    }
                                    if (MainActivityPU.this.tagV == 2) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.VT, Ascii.DC2, Ascii.EM, 0, 109, -91});
                                    }
                                    if (MainActivityPU.this.tagV == 3) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 3, 4, Ascii.EM, 0, 0, 0, 116, -91});
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
                int i6 = this.tagC + 1;
                this.tagC = i6;
                if (i6 > 6) {
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
                if (this.modeNum != 6) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.75
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
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
                layoutParams.height = (int) (this.density * 70.0f);
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
                this.daoNameIng = "cross" + this.tagC;
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i7 = 0; i7 < this.defaultDaoList.size(); i7++) {
                        if (this.defaultDaoList.get(i7).getDaoName() != null) {
                            if (this.defaultDaoList.get(i7).getDaoName().equals("cross" + this.tagC)) {
                                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                                this.saveColor = 0;
                                this.lr = this.defaultDaoList.get(i7).getItem2();
                                int item32 = this.defaultDaoList.get(i7).getItem3();
                                this.ud = item32;
                                short s6 = (short) this.lr;
                                short s7 = (short) item32;
                                writeBleData(new byte[]{-86, 108, (byte) (s6 >> 8), (byte) s6, (byte) (s7 >> 8), (byte) s7, 0, 0, 1, -91});
                                this.num_lr.setText("" + ((this.lr / 30) + 2));
                                this.num_ud.setText("" + (this.ud / 100));
                                this.freq.setProgress((float) this.defaultDaoList.get(i7).getFreq());
                                this.f_tv.setText("" + this.defaultDaoList.get(i7).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.76
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                this.velobar.setProgress((float) this.defaultDaoList.get(i7).getVelo());
                                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i7).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.77
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                this.rotatebar.setProgress((float) this.defaultDaoList.get(i7).getRote());
                                final int rote2 = this.defaultDaoList.get(i7).getRote();
                                if (rote2 < 0) {
                                    j2 = 120;
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.78
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i8 = rote2;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i8) * 5), 0, 0, 0, 0, (byte) (((-i8) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                } else {
                                    j2 = 120;
                                }
                                if (rote2 > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.79
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i8 = rote2;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i8 * 5), 0, 0, 0, 0, (byte) ((i8 * 5) ^ 99), -91});
                                        }
                                    }, j2);
                                }
                                if (rote2 == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.80
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, j2);
                                }
                                this.r_tv.setText("" + rote2);
                                final int grade2 = this.defaultDaoList.get(i7).getGrade();
                                this.radioNum = grade2;
                                if (grade2 == 0) {
                                    this.rgheight.check(R.id.radio0);
                                }
                                if (grade2 == 1) {
                                    this.rgheight.check(R.id.radio1);
                                }
                                if (grade2 == 2) {
                                    this.rgheight.check(R.id.radio2);
                                }
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.81
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.sendBaseData(grade2);
                                    }
                                }, 160L);
                            }
                        }
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.82
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 250L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.83
                        @Override // java.lang.Runnable
                        public void run() {
                            int i8;
                            int unused = MainActivityPU.this.tagC;
                            int i9 = 28;
                            int i10 = 22;
                            int i11 = MainActivityPU.this.tagC == 2 ? 28 : 22;
                            int i12 = 25;
                            if (MainActivityPU.this.tagC == 3) {
                                i11 = 25;
                                i8 = 1;
                            } else {
                                i8 = 4;
                            }
                            if (MainActivityPU.this.tagC == 4) {
                                i8 = 7;
                            } else {
                                i12 = i11;
                            }
                            if (MainActivityPU.this.tagC == 5) {
                                i8 = 7;
                            } else {
                                i10 = i12;
                            }
                            if (MainActivityPU.this.tagC == 6) {
                                i8 = 1;
                            } else {
                                i9 = i10;
                            }
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 1, (byte) i9, (byte) i8, 0, 0, 0, (byte) ((i9 ^ 108) ^ i8), -91});
                        }
                    }, 5L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.84
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 2, 0, 0, 0, 0, 0, 111, -91});
                        }
                    }, 50L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.85
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 3, 0, 0, 0, 0, 0, 110, -91});
                        }
                    }, 80L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.86
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 4, 0, 0, 0, 0, 0, 105, -91});
                        }
                    }, 110L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.87
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 5, 0, 0, 0, 0, 0, 104, -91});
                        }
                    }, 140L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.88
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 109, 6, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 170L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.89
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 106, 5, 0, 0, 0, 0, 0, 111, -91});
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
                if (this.modeNum != 5) {
                    this.isNumDing = 0;
                    this.lr = 1140;
                    this.ud = 4200;
                    short s8 = (short) 1140;
                    short s9 = (short) 4200;
                    writeBleData(new byte[]{-86, 108, (byte) (s8 >> 8), (byte) s8, (byte) (s9 >> 8), (byte) s9, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 5;
                this.modeCate = 0;
                changeOperate();
                this.velobar.setProgress(3.0f);
                this.v_tv.setText("" + this.veloTins[2]);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.67
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.velobar.getProgress();
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[2], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[2] ^ 99), -91});
                    }
                }, 20L);
                this.daoNameIng = "high";
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i8 = 0; i8 < this.defaultDaoList.size(); i8++) {
                    if (this.defaultDaoList.get(i8).getDaoName() != null && this.defaultDaoList.get(i8).getDaoName().equals("high")) {
                            this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                            this.saveColor = 0;
                            this.lr = this.defaultDaoList.get(i8).getItem2();
                            int item33 = this.defaultDaoList.get(i8).getItem3();
                            this.ud = item33;
                            short s10 = (short) this.lr;
                            short s11 = (short) item33;
                            writeBleData(new byte[]{-86, 108, (byte) (s10 >> 8), (byte) s10, (byte) (s11 >> 8), (byte) s11, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                            this.freq.setProgress((float) this.defaultDaoList.get(i8).getFreq());
                            this.f_tv.setText("" + this.defaultDaoList.get(i8).getFreq());
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.68
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    int progress = MainActivityPU.this.freq.getProgress() - 1;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                }
                            }, 30L);
                            this.velobar.setProgress((float) this.defaultDaoList.get(i8).getVelo());
                            this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i8).getVelo() - 1]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.69
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                }
                            }, 80L);
                            this.rotatebar.setProgress((float) this.defaultDaoList.get(i8).getRote());
                            final int rote3 = this.defaultDaoList.get(i8).getRote();
                            if (rote3 < 0) {
                                j3 = 120;
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.70
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i9 = rote3;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i9) * 5), 0, 0, 0, 0, (byte) (((-i9) * 5) ^ 96), -91});
                                    }
                                }, 120L);
                            } else {
                                j3 = 120;
                            }
                            if (rote3 > 0) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.71
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i9 = rote3;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i9 * 5), 0, 0, 0, 0, (byte) ((i9 * 5) ^ 99), -91});
                                    }
                                }, j3);
                            }
                            if (rote3 == 0) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.72
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                    }
                                }, j3);
                            }
                            this.r_tv.setText("" + rote3);
                        }
                    }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.73
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 5) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.74
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                            }
                        }, 200L);
                        break;
                    }
                }
                break;
            case R.id.hit /* 2131362126 */:
                int i9 = this.tagHT + 1;
                this.tagHT = i9;
                if (i9 > 3) {
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
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i10 = this.tagHT;
                if (i10 == 1) {
                    this.lr = 1140;
                }
                if (i10 == 2) {
                    this.lr = 210;
                }
                if (i10 == 3) {
                    this.lr = 2070;
                }
                this.ud = 1300;
                short s12 = (short) this.lr;
                short s13 = (short) 1300;
                writeBleData(new byte[]{-86, 108, (byte) (s12 >> 8), (byte) s12, (byte) (s13 >> 8), (byte) s13, 0, 0, 1, -91});
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
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.90
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                    }
                }, 30L);
                if (this.tagHT == 1) {
                    showTvs(this.ht1);
                }
                if (this.tagHT == 2) {
                    showTvs(this.ht2);
                }
                if (this.tagHT == 3) {
                    showTvs(this.ht3);
                }
                this.daoNameIng = "hit" + this.tagHT;
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i11 = 0; i11 < this.defaultDaoList.size(); i11++) {
                        if (this.defaultDaoList.get(i11).getDaoName() != null) {
                            if (this.defaultDaoList.get(i11).getDaoName().equals("hit" + this.tagHT)) {
                                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                                this.saveColor = 0;
                                this.lr = this.defaultDaoList.get(i11).getItem2();
                                int item34 = this.defaultDaoList.get(i11).getItem3();
                                this.ud = item34;
                                short s14 = (short) this.lr;
                                short s15 = (short) item34;
                                writeBleData(new byte[]{-86, 108, (byte) (s14 >> 8), (byte) s14, (byte) (s15 >> 8), (byte) s15, 0, 0, 1, -91});
                                this.num_lr.setText("" + ((this.lr / 30) + 2));
                                this.num_ud.setText("" + (this.ud / 100));
                                this.freq.setProgress((float) this.defaultDaoList.get(i11).getFreq());
                                this.f_tv.setText("" + this.defaultDaoList.get(i11).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.91
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                this.velobar.setProgress((float) this.defaultDaoList.get(i11).getVelo());
                                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i11).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.92
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                this.rotatebar.setProgress((float) this.defaultDaoList.get(i11).getRote());
                                final int rote4 = this.defaultDaoList.get(i11).getRote();
                                if (rote4 < 0) {
                                    j4 = 120;
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.93
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i12 = rote4;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i12) * 5), 0, 0, 0, 0, (byte) (((-i12) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                } else {
                                    j4 = 120;
                                }
                                if (rote4 > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.94
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i12 = rote4;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i12 * 5), 0, 0, 0, 0, (byte) ((i12 * 5) ^ 99), -91});
                                        }
                                    }, j4);
                                }
                                if (rote4 == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.95
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, j4);
                                }
                                this.r_tv.setText("" + rote4);
                            }
                        }
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.96
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.97
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                    }, 200L);
                    break;
                }
                break;
            case R.id.left_right /* 2131362169 */:
                int i12 = this.tagH + 1;
                this.tagH = i12;
                if (i12 > 5) {
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
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                hideLR();
                this.lr = BasicData7.a31[0];
                short s16 = BasicData7.a31[1];
                this.ud = s16;
                short s17 = (short) this.lr;
                short s18 = s16;
                writeBleData(new byte[]{-86, 108, (byte) (s17 >> 8), (byte) s17, (byte) (s18 >> 8), (byte) s18, 0, 0, 1, -91});
                this.num_lr.setText("" + ((this.lr / 30) + 2));
                this.num_ud.setText("" + (this.ud / 100));
                showUD();
                if (this.modeNum != 2) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.50
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        }
                    }, 15L);
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
                this.daoNameIng = "lr" + this.tagH;
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i13 = 0; i13 < this.defaultDaoList.size(); i13++) {
                        if (this.defaultDaoList.get(i13).getDaoName() != null) {
                            if (this.defaultDaoList.get(i13).getDaoName().equals("lr" + this.tagH)) {
                                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                                this.saveColor = 0;
                                this.lr = this.defaultDaoList.get(i13).getItem2();
                                int item35 = this.defaultDaoList.get(i13).getItem3();
                                this.ud = item35;
                                short s19 = (short) this.lr;
                                short s20 = (short) item35;
                                writeBleData(new byte[]{-86, 108, (byte) (s19 >> 8), (byte) s19, (byte) (s20 >> 8), (byte) s20, 0, 0, 1, -91});
                                this.num_lr.setText("" + ((this.lr / 30) + 2));
                                this.num_ud.setText("" + (this.ud / 100));
                                this.freq.setProgress((float) this.defaultDaoList.get(i13).getFreq());
                                this.f_tv.setText("" + this.defaultDaoList.get(i13).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.51
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                this.velobar.setProgress((float) this.defaultDaoList.get(i13).getVelo());
                                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i13).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.52
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                this.rotatebar.setProgress((float) this.defaultDaoList.get(i13).getRote());
                                final int rote5 = this.defaultDaoList.get(i13).getRote();
                                if (rote5 < 0) {
                                    j5 = 120;
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.53
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i14 = rote5;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i14) * 5), 0, 0, 0, 0, (byte) (((-i14) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                } else {
                                    j5 = 120;
                                }
                                if (rote5 > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.54
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i14 = rote5;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i14 * 5), 0, 0, 0, 0, (byte) ((i14 * 5) ^ 99), -91});
                                        }
                                    }, j5);
                                }
                                if (rote5 == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.55
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, j5);
                                }
                                this.r_tv.setText("" + rote5);
                            }
                        }
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.56
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0) {
                        if (this.modeNum == 1) {
                            writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
                        }
                        if (this.modeNum == 2 && this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.57
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (MainActivityPU.this.tagH == 1) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 2, 0, 0, 0, 0, 0, 104, -91});
                                    }
                                    if (MainActivityPU.this.tagH == 2) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.NAK, 0, 0, 0, 114, -91});
                                    }
                                    if (MainActivityPU.this.tagH == 3) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 2, Ascii.DLE, Ascii.DC4, 0, 0, 0, 110, -91});
                                    }
                                    if (MainActivityPU.this.tagH == 4) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 2, 17, 19, 0, 0, 0, 106, -91});
                                    }
                                    if (MainActivityPU.this.tagH == 5) {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 106, 2, Ascii.SI, Ascii.DC2, Ascii.NAK, 0, 0, 96, -91});
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
                this.tableName.setText(getResources().getString(R.string.smash_p));
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
                if (this.modeNum != 9) {
                    this.isNumDing = 0;
                    this.lr = 1140;
                    this.ud = 1600;
                    short s21 = (short) 1140;
                    short s22 = (short) 1600;
                    writeBleData(new byte[]{-86, 108, (byte) (s21 >> 8), (byte) s21, (byte) (s22 >> 8), (byte) s22, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 9;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.102
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.velobar.getProgress();
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        MainActivityPU.this.velobar.setProgress(5.0f);
                        MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[4]);
                    }
                }, 50L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.103
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.rotatebar.setProgress(6.0f);
                        MainActivityPU.this.r_tv.setText("6");
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, Ascii.RS, 0, 0, 0, 0, 122, -91});
                    }
                }, 150L);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.104
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 9) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.105
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(8);
                showLR();
                showUD();
                this.isNumDing = 1;
                if (this.modeNum != 8) {
                    this.isNumDing = 0;
                    this.lr = 1140;
                    this.ud = 1600;
                    short s23 = (short) 1140;
                    short s24 = (short) 1600;
                    writeBleData(new byte[]{-86, 108, (byte) (s23 >> 8), (byte) s23, (byte) (s24 >> 8), (byte) s24, 0, 0, 1, -91});
                    this.num_lr.setText("" + ((this.lr / 30) + 2));
                    this.num_ud.setText("" + (this.ud / 100));
                }
                this.modeNum = 8;
                this.modeCate = 0;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.98
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        if (MainActivityPU.this.velobar.getProgress() > 2) {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[0], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[0] ^ 99), -91});
                            MainActivityPU.this.velobar.setProgress(1.0f);
                            MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[0]);
                        }
                    }
                }, 50L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.99
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.rotatebar.setProgress(0.0f);
                        MainActivityPU.this.r_tv.setText("0");
                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 99, -91});
                    }
                }, 150L);
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.100
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
                        }
                    }, 100L);
                    if (this.modeCate == 0 && this.modeNum == 8) {
                        new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.101
                            @Override // java.lang.Runnable
                            public void run() {
                                MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                this.group_cate.setVisibility(0);
                this.modeCate = 1;
                changeOperate();
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.106
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        MainActivityPU.this.velobar.setProgress(5.0f);
                        MainActivityPU.this.v_tv.setText("" + MainActivityPU.this.veloTins[4]);
                    }
                }, 50L);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.107
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.f_tv.setText("7");
                        MainActivityPU.this.freq.setProgress(7.0f);
                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[6], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[6] ^ 97), -91});
                    }
                }, 80L);
                break;
            case R.id.self_point /* 2131362423 */:
                int i14 = this.tagFix + 1;
                this.tagFix = i14;
                if (i14 > 3) {
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
                this.moon.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.place.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.whole.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.tennipic3.setVisibility(8);
                this.tennipic4.setVisibility(8);
                this.tennipic5.setVisibility(0);
                showLR();
                showUD();
                int i15 = this.tagFix;
                if (i15 == 1) {
                    this.lr = 1140;
                }
                if (i15 == 2) {
                    this.lr = 210;
                }
                if (i15 == 3) {
                    this.lr = 2070;
                }
                this.ud = 1400;
                short s25 = (short) this.lr;
                short s26 = (short) 1400;
                writeBleData(new byte[]{-86, 108, (byte) (s25 >> 8), (byte) s25, (byte) (s26 >> 8), (byte) s26, 0, 0, 1, -91});
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
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.42
                    @Override // java.lang.Runnable
                    public synchronized void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                    }
                }, 30L);
                if (this.tagFix == 1) {
                    showTvs(this.fx1);
                }
                if (this.tagFix == 2) {
                    showTvs(this.fx2);
                }
                if (this.tagFix == 3) {
                    showTvs(this.fx3);
                }
                this.daoNameIng = "fix" + this.tagFix;
                this.defaultDaoList = new ArrayList();
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                for (int i16 = 0; i16 < this.defaultDaoList.size(); i16++) {
                        if (this.defaultDaoList.get(i16).getDaoName() != null) {
                            if (this.defaultDaoList.get(i16).getDaoName().equals("fix" + this.tagFix)) {
                                this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                                this.saveColor = 0;
                                this.lr = this.defaultDaoList.get(i16).getItem2();
                                int item36 = this.defaultDaoList.get(i16).getItem3();
                                this.ud = item36;
                                short s27 = (short) this.lr;
                                short s28 = (short) item36;
                                writeBleData(new byte[]{-86, 108, (byte) (s27 >> 8), (byte) s27, (byte) (s28 >> 8), (byte) s28, 0, 0, 1, -91});
                                this.num_lr.setText("" + ((this.lr / 30) + 2));
                                this.num_ud.setText("" + (this.ud / 100));
                                this.freq.setProgress((float) this.defaultDaoList.get(i16).getFreq());
                                this.f_tv.setText("" + this.defaultDaoList.get(i16).getFreq());
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.43
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.freq.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                    }
                                }, 30L);
                                this.velobar.setProgress((float) this.defaultDaoList.get(i16).getVelo());
                                this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i16).getVelo() - 1]);
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.44
                                    @Override // java.lang.Runnable
                                    public synchronized void run() {
                                        int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                    }
                                }, 80L);
                                this.rotatebar.setProgress((float) this.defaultDaoList.get(i16).getRote());
                                final int rote6 = this.defaultDaoList.get(i16).getRote();
                                if (rote6 < 0) {
                                    j6 = 120;
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.45
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i17 = rote6;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i17) * 5), 0, 0, 0, 0, (byte) (((-i17) * 5) ^ 96), -91});
                                        }
                                    }, 120L);
                                } else {
                                    j6 = 120;
                                }
                                if (rote6 > 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.46
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            int i17 = rote6;
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i17 * 5), 0, 0, 0, 0, (byte) ((i17 * 5) ^ 99), -91});
                                        }
                                    }, j6);
                                }
                                if (rote6 == 0) {
                                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.47
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                        }
                                    }, j6);
                                }
                                this.r_tv.setText("" + rote6);
                            }
                        }
                }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.48
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 102, -91});
                        }
                    }, 100L);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.49
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 106, 1, 0, 0, 0, 0, 0, 107, -91});
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
                showTvs(this.tvids);
                if (this.modeNum != 4) {
                    this.velobar.setProgress(5.0f);
                    this.v_tv.setText("" + this.veloTins[4]);
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.108
                        @Override // java.lang.Runnable
                        public synchronized void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[4], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[4] ^ 99), -91});
                        }
                    }, 15L);
                }
                this.modeNum = 4;
                this.modeCate = 0;
                changeOperate();
                this.fourbtn.setVisibility(8);
                this.bg_four.setVisibility(8);
                this.bg_input.setVisibility(8);
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.start_layout.getLayoutParams();
                layoutParams2.height = (int) (this.density * 70.0f);
                layoutParams2.topMargin = (int) (this.density * 20.0f);
                this.start_layout.setLayoutParams(layoutParams2);
                this.start_layout.setGravity(1);
                this.daoNameIng = "whole";
                this.defaultDaoList = new ArrayList();
                this.savedefault.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.saveColor = 1;
                this.defaultDaoList = this.daoSession.loadAll(DefaultDao.class);
                for (int i17 = 0; i17 < this.defaultDaoList.size(); i17++) {
                    if (this.defaultDaoList.get(i17).getDaoName() != null && this.defaultDaoList.get(i17).getDaoName().equals("whole")) {
                            this.savedefault.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                            this.saveColor = 0;
                            this.lr = this.defaultDaoList.get(i17).getItem2();
                            int item37 = this.defaultDaoList.get(i17).getItem3();
                            this.ud = item37;
                            short s29 = (short) this.lr;
                            short s30 = (short) item37;
                            writeBleData(new byte[]{-86, 108, (byte) (s29 >> 8), (byte) s29, (byte) (s30 >> 8), (byte) s30, 0, 0, 1, -91});
                            this.num_lr.setText("" + ((this.lr / 30) + 2));
                            this.num_ud.setText("" + (this.ud / 100));
                            this.freq.setProgress((float) this.defaultDaoList.get(i17).getFreq());
                            this.f_tv.setText("" + this.defaultDaoList.get(i17).getFreq());
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.109
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    int progress = MainActivityPU.this.freq.getProgress() - 1;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 97, (byte) MainActivityPU.this.frequentNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.frequentNums[progress] ^ 97), -91});
                                }
                            }, 30L);
                            this.velobar.setProgress((float) this.defaultDaoList.get(i17).getVelo());
                            this.v_tv.setText("" + this.veloTins[this.defaultDaoList.get(i17).getVelo() - 1]);
                            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.110
                                @Override // java.lang.Runnable
                                public synchronized void run() {
                                    int progress = MainActivityPU.this.velobar.getProgress() - 1;
                                    MainActivityPU.this.writeBleData(new byte[]{-86, 99, (byte) MainActivityPU.this.veloNums[progress], 0, 0, 0, 0, 0, (byte) (MainActivityPU.this.veloNums[progress] ^ 99), -91});
                                }
                            }, 80L);
                            this.rotatebar.setProgress((float) this.defaultDaoList.get(i17).getRote());
                            final int rote7 = this.defaultDaoList.get(i17).getRote();
                            if (rote7 < 0) {
                                j7 = 120;
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.111
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i18 = rote7;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 2, (byte) ((-i18) * 5), 0, 0, 0, 0, (byte) (((-i18) * 5) ^ 96), -91});
                                    }
                                }, 120L);
                            } else {
                                j7 = 120;
                            }
                            if (rote7 > 0) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.112
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        int i18 = rote7;
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 1, (byte) (i18 * 5), 0, 0, 0, 0, (byte) ((i18 * 5) ^ 99), -91});
                                    }
                                }, j7);
                            }
                            if (rote7 == 0) {
                                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.113
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        MainActivityPU.this.writeBleData(new byte[]{-86, 98, 0, 0, 0, 0, 0, 0, 98, -91});
                                    }
                                }, j7);
                            }
                            this.r_tv.setText("" + rote7);
                            final int grade3 = this.defaultDaoList.get(i17).getGrade();
                            this.radioNum = grade3;
                            if (grade3 == 0) {
                                this.rgheight.check(R.id.radio0);
                            }
                            if (grade3 == 1) {
                                this.rgheight.check(R.id.radio1);
                            }
                            if (grade3 == 2) {
                                this.rgheight.check(R.id.radio2);
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override // java.lang.Runnable
                                public void run() {
                                    MainActivityPU.this.sendBaseData(grade3);
                                }
                            }, 160L);
                        }
                    }
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.stop))) {
                    new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.115
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPU.this.timeCount1.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPU.access$512(MainActivityPU.this, 100);
                if (MainActivityPU.this.ud < 300) {
                    MainActivityPU.this.ud = 300;
                }
                if (MainActivityPU.this.ud > 4300) {
                    MainActivityPU.this.ud = 4300;
                }
                if (MainActivityPU.this.modeCate == 0 && ((MainActivityPU.this.modeNum == 1 || MainActivityPU.this.modeNum == 2) && MainActivityPU.this.ud > 2000)) {
                    MainActivityPU.this.ud = 2000;
                }
                short s = (short) MainActivityPU.this.lr;
                if (MainActivityPU.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPU.this.ud;
                if (MainActivityPU.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                AppLog.e("count1:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.TimeCount1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPU.this.timeCount2.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPU.access$520(MainActivityPU.this, 100);
                if (MainActivityPU.this.ud < 300) {
                    MainActivityPU.this.ud = 300;
                }
                if (MainActivityPU.this.ud > 4300) {
                    MainActivityPU.this.ud = 4300;
                }
                if (MainActivityPU.this.modeCate == 0 && MainActivityPU.this.modeNum == 5 && MainActivityPU.this.ud < 2000) {
                    MainActivityPU.this.ud = 2000;
                }
                short s = (short) MainActivityPU.this.lr;
                if (MainActivityPU.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPU.this.ud;
                if (MainActivityPU.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                AppLog.e("count2:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.TimeCount2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPU.this.timeCount3.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPU.access$412(MainActivityPU.this, 30);
                if (MainActivityPU.this.lr < 210) {
                    MainActivityPU.this.lr = 210;
                }
                if (MainActivityPU.this.lr > 2070) {
                    MainActivityPU.this.lr = 2070;
                }
                short s = (short) MainActivityPU.this.lr;
                if (MainActivityPU.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPU.this.ud;
                if (MainActivityPU.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                AppLog.e("count3:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.TimeCount3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
            MainActivityPU.this.timeCount4.cancel();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            if (20000 - j > 700) {
                MainActivityPU.access$420(MainActivityPU.this, 30);
                if (MainActivityPU.this.lr < 210) {
                    MainActivityPU.this.lr = 210;
                }
                if (MainActivityPU.this.lr > 2070) {
                    MainActivityPU.this.lr = 2070;
                }
                short s = (short) MainActivityPU.this.lr;
                if (MainActivityPU.this.modeNum == 2) {
                    s = 0;
                }
                short s2 = (short) MainActivityPU.this.ud;
                if (MainActivityPU.this.modeNum == 3) {
                    s2 = 0;
                }
                MainActivityPU.this.writeBleData(new byte[]{-86, 108, (byte) (s >> 8), (byte) s, (byte) (s2 >> 8), (byte) s2, 0, 0, 1, -91});
                MainActivityPU.this.num_lr.setText("" + ((MainActivityPU.this.lr / 30) + 2));
                MainActivityPU.this.num_ud.setText("" + (MainActivityPU.this.ud / 100));
                AppLog.e("count4:1until:" + j);
                new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.MainActivityPU.TimeCount4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivityPU.this.writeBleData(new byte[]{-86, 102, 0, 0, 0, 0, 0, 0, 103, -91});
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
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.135
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.MainActivityPU.136
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() == null || editText.getText().toString().trim().equals("")) {
                    MainActivityPU mainActivityPU = MainActivityPU.this;
                    ShowHelper.toastShort(mainActivityPU, mainActivityPU.getResources().getString(R.string.input_route_name));
                    return;
                }
                new ArrayList();
                MainActivityPU.this.daoSession.loadAll(SeleDao.class);
                SeleDao seleDao = new SeleDao();
                seleDao.setDaoName("" + editText.getText().toString().trim());
                seleDao.setSeles(MainActivityPU.this.select_dianwei.getText().toString().trim());
                seleDao.setFreq(MainActivityPU.this.freq.getProgress());
                seleDao.setItem1(MainActivityPU.this.radioNum);
                seleDao.setVelo(MainActivityPU.this.velobar.getProgress());
                seleDao.setRote(MainActivityPU.this.rotatebar.getProgress());
                MainActivityPU.this.daoSession.insertOrReplace(seleDao);
                create.dismiss();
            }
        });
        create.show();
    }
}
