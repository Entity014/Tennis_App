package com.pusun.pusuntennis;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.qqtheme.framework.picker.OptionPicker;
import com.google.android.exoplayer2.ExoPlayer;
import com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter;
import com.pusun.pusuntennis.utils.BasicData33;
import com.pusun.pusuntennis.utils.NoteVariMsg;
import com.pusun.pusuntennis.utils.ShowHelper;
import com.pusun.pusuntennis.utils.VarispinStartMsg;
import com.pusun.pusuntennis.utils.dao.DaoSession;
import com.pusun.pusuntennis.utils.dao.WholeVary;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subcriber;

/* loaded from: classes3.dex */
public class VaryDrillActivityPS extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_btn;
    private int ballnum;
    private Button btn_ball;
    private Button clear;
    private DaoSession daoSession;
    private TextView delepoints2;
    private TextView lastpoints2;
    private int lh;
    private RecyclerView list_dev;
    private int lr;
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
    private int pa;
    private int[] poids;
    private TextView[] pos;
    private TextView savepoints2;
    private TextView select_dianwei;
    private String seles;
    private VariSpinPTSmartRvAdapter variSpinRvAdapter;
    private List<Integer> selectPoints = new ArrayList();
    private int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
    private int[] numright = {19, 20, 21, 26, 27, 28};
    private int[] numleft = {17, 16, 15, 24, 23, 22};

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        char c;
        char c2;
        char c3;
        char c4;
        super.onCreate(bundle);
        setContentView(R.layout.activity_vary_drill);
        this.list_dev = (RecyclerView) findViewById(R.id.list_dev);
        EventBus.getDefault().register(this);
        int intExtra = getIntent().getIntExtra("ai", 1);
        this.lr = getIntent().getIntExtra("lr", 0);
        this.lh = getIntent().getIntExtra("lh", 0);
        this.pa = getIntent().getIntExtra("pa", 0);
        this.ballnum = getIntent().getIntExtra("ballnum", 3);
        this.seles = getIntent().getStringExtra("seles");
        for (int i = 0; i < BasicData33.b3.length; i++) {
            BasicData33.b3[i][3] = 40;
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return VaryDrillActivityPS.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.select_dianwei = (TextView) findViewById(R.id.select_dianwei);
        Button button = (Button) findViewById(R.id.btn_ball);
        this.btn_ball = button;
        button.setOnClickListener(this);
        this.daoSession = MyApplication.getmDaoSession();
        this.lastpoints2 = (TextView) findViewById(R.id.lastpoints2);
        this.savepoints2 = (TextView) findViewById(R.id.savepoints2);
        this.delepoints2 = (TextView) findViewById(R.id.delepoints2);
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
        this.poids = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        this.pos = new TextView[]{this.num1, this.num2, this.num3, this.num4, this.num5, this.num6, this.num7, this.num8, this.num9, this.num10, this.num11, this.num12, this.num13, this.num14, this.num15, this.num16, this.num17, this.num18, this.num19, this.num20, this.num21, this.num22, this.num23, this.num24, this.num25, this.num26, this.num27, this.num28};
        ImageView imageView = (ImageView) findViewById(R.id.back_btn2);
        this.back_btn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VaryDrillActivityPS.this.finish();
            }
        });
        Button button2 = (Button) findViewById(R.id.clear);
        this.clear = button2;
        button2.setOnClickListener(this);
        if (intExtra == 10) {
            this.selectPoints.clear();
            for (String str : this.seles.split(",")) {
                this.selectPoints.add(Integer.valueOf(Integer.parseInt(str)));
            }
            ShowHelper.showProgressDialog(this, "球路加载中...");
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.2
                @Override // java.lang.Runnable
                public synchronized void run() {
                    VaryDrillActivityPS.this.showPoints();
                }
            }, 600L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.3
                @Override // java.lang.Runnable
                public synchronized void run() {
                    ShowHelper.dismissProgressDialog();
                }
            }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
        if (intExtra == 6) {
            this.selectPoints.clear();
            for (int i2 = 0; i2 < this.ballnum; i2++) {
                Random random = new Random();
                if (i2 == 0) {
                    if (this.lr == 0) {
                        int nextInt = random.nextInt(this.numleft.length);
                        this.selectPoints.add(Integer.valueOf(this.numleft[nextInt]));
                        if (this.lh == 0) {
                            short[] sArr = BasicData33.b3[this.numleft[nextInt] - 1];
                            c4 = 3;
                            sArr[3] = (short) (sArr[3] + 5);
                        } else {
                            c4 = 3;
                        }
                        if (this.lh == 2) {
                            short[] sArr2 = BasicData33.b3[this.numleft[nextInt] - 1];
                            sArr2[c4] = (short) (sArr2[c4] - 5);
                        }
                    } else {
                        int nextInt2 = random.nextInt(this.numright.length);
                        this.selectPoints.add(Integer.valueOf(this.numright[nextInt2]));
                        if (this.lh == 0) {
                            short[] sArr3 = BasicData33.b3[this.numright[nextInt2] - 1];
                            c3 = 3;
                            sArr3[3] = (short) (sArr3[3] + 5);
                        } else {
                            c3 = 3;
                        }
                        if (this.lh == 2) {
                            short[] sArr4 = BasicData33.b3[this.numright[nextInt2] - 1];
                            sArr4[c3] = (short) (sArr4[c3] - 5);
                        }
                    }
                } else {
                    if (i2 == this.ballnum - 1 && i2 != 0) {
                        int nextInt3 = random.nextInt(this.nums1.length);
                        this.selectPoints.add(Integer.valueOf(this.nums1[nextInt3]));
                        if (this.lh == 0) {
                            short[] sArr5 = BasicData33.b3[this.nums1[nextInt3] - 1];
                            c2 = 3;
                            sArr5[3] = (short) (sArr5[3] + 5);
                        } else {
                            c2 = 3;
                        }
                        if (this.lh == 2) {
                            short[] sArr6 = BasicData33.b3[this.nums1[nextInt3] - 1];
                            sArr6[c2] = (short) (sArr6[c2] - 5);
                        }
                    } else {
                        int nextInt4 = random.nextInt(this.nums1.length);
                        this.selectPoints.add(Integer.valueOf(this.nums1[nextInt4]));
                        if (this.lh == 0) {
                            short[] sArr7 = BasicData33.b3[this.nums1[nextInt4] - 1];
                            c = 3;
                            sArr7[3] = (short) (sArr7[3] + 5);
                        } else {
                            c = 3;
                        }
                        if (this.lh == 2) {
                            short[] sArr8 = BasicData33.b3[this.nums1[nextInt4] - 1];
                            sArr8[c] = (short) (sArr8[c] - 5);
                        }
                    }
                }
            }
            ShowHelper.showProgressDialog(this, getResources().getString(R.string.aigenerating));
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.4
                @Override // java.lang.Runnable
                public synchronized void run() {
                    VaryDrillActivityPS.this.showPoints();
                }
            }, 600L);
            new Handler().postDelayed(new Runnable() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.5
                @Override // java.lang.Runnable
                public synchronized void run() {
                    ShowHelper.dismissProgressDialog();
                }
            }, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
        this.lastpoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = VaryDrillActivityPS.this.daoSession.loadAll(WholeVary.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((WholeVary) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(VaryDrillActivityPS.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(VaryDrillActivityPS.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(VaryDrillActivityPS.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(VaryDrillActivityPS.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.6.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str2) {
                            new ArrayList();
                            List loadAll2 = VaryDrillActivityPS.this.daoSession.loadAll(WholeVary.class);
                            for (int i3 = 0; i3 < loadAll2.size(); i3++) {
                                if (((WholeVary) loadAll2.get(i3)).getDaoName().equals(str2)) {
                                    VaryDrillActivityPS.this.select_dianwei.setText(((WholeVary) loadAll2.get(i3)).getSeles());
                                    String[] split = ((WholeVary) loadAll2.get(i3)).getSeles().split(",");
                                    VaryDrillActivityPS.this.selectPoints.clear();
                                    for (String str3 : split) {
                                        VaryDrillActivityPS.this.selectPoints.add(Integer.valueOf(Integer.parseInt(str3)));
                                    }
                                    VaryDrillActivityPS.this.pa = ((WholeVary) loadAll2.get(i3)).getItem3();
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara1());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara2());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara3());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara4());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara5());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara6());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara7());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara8());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara9());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara10());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara11());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara12());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara13());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara14());
                                    arrayList.add(((WholeVary) loadAll2.get(i3)).getPara15());
                                    for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                        if (arrayList.get(i4) != null && !"".equals(arrayList.get(i4))) {
                                            VaryDrillActivityPS.this.setPara(((String) arrayList.get(i4)).split(","));
                                        }
                                    }
                                    VaryDrillActivityPS.this.showPoints();
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                VaryDrillActivityPS varyDrillActivityPS = VaryDrillActivityPS.this;
                ShowHelper.toastShort(varyDrillActivityPS, varyDrillActivityPS.getResources().getString(R.string.no_route_name));
            }
        });
        this.savepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VaryDrillActivityPS.this.select_dianwei.getText() != null && !VaryDrillActivityPS.this.select_dianwei.getText().toString().trim().isEmpty()) {
                    VaryDrillActivityPS.this.alert_dialog_input2();
                } else {
                    VaryDrillActivityPS varyDrillActivityPS = VaryDrillActivityPS.this;
                    ShowHelper.toastShort(varyDrillActivityPS, varyDrillActivityPS.getResources().getString(R.string.no_point_select));
                }
            }
        });
        this.delepoints2.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new ArrayList();
                List loadAll = VaryDrillActivityPS.this.daoSession.loadAll(WholeVary.class);
                if (loadAll != null && loadAll.size() != 0) {
                    String[] strArr = new String[loadAll.size()];
                    for (int size = loadAll.size() - 1; size >= 0; size--) {
                        strArr[(loadAll.size() - 1) - size] = ((WholeVary) loadAll.get(size)).getDaoName();
                    }
                    OptionPicker optionPicker = new OptionPicker(VaryDrillActivityPS.this, strArr);
                    optionPicker.setOffset(2);
                    optionPicker.setSelectedIndex(0);
                    optionPicker.setTextSize(18);
                    optionPicker.setTitleText(VaryDrillActivityPS.this.getResources().getString(R.string.select_route_name));
                    optionPicker.setCancelText(VaryDrillActivityPS.this.getResources().getString(R.string.cancel));
                    optionPicker.setSubmitText(VaryDrillActivityPS.this.getResources().getString(R.string.submit));
                    optionPicker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.8.1
                        @Override // cn.qqtheme.framework.picker.OptionPicker.OnOptionPickListener
                        public void onOptionPicked(String str2) {
                            new ArrayList();
                            List loadAll2 = VaryDrillActivityPS.this.daoSession.loadAll(WholeVary.class);
                            for (int i3 = 0; i3 < loadAll2.size(); i3++) {
                                if (((WholeVary) loadAll2.get(i3)).getDaoName().equals(str2)) {
                                    VaryDrillActivityPS.this.daoSession.delete((WholeVary) loadAll2.get(i3));
                                    ShowHelper.toastShort(VaryDrillActivityPS.this, VaryDrillActivityPS.this.getResources().getString(R.string.already_dele));
                                    return;
                                }
                            }
                        }
                    });
                    optionPicker.show();
                    return;
                }
                VaryDrillActivityPS varyDrillActivityPS = VaryDrillActivityPS.this;
                ShowHelper.toastShort(varyDrillActivityPS, varyDrillActivityPS.getResources().getString(R.string.no_route_name));
            }
        });
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPara(String[] strArr) {
        BasicData33.b3[Integer.parseInt(strArr[5]) - 1][0] = Short.parseShort(strArr[0]);
        BasicData33.b3[Integer.parseInt(strArr[5]) - 1][1] = Short.parseShort(strArr[1]);
        BasicData33.b3[Integer.parseInt(strArr[5]) - 1][2] = Short.parseShort(strArr[2]);
        BasicData33.b3[Integer.parseInt(strArr[5]) - 1][3] = Short.parseShort(strArr[3]);
        BasicData33.b3[Integer.parseInt(strArr[5]) - 1][4] = Short.parseShort(strArr[4]);
    }

    public void alert_dialog_input2() {
        View inflate = View.inflate(this, R.layout.dialog_input, null);
        final AlertDialog create = new AlertDialog.Builder(this).setView(inflate).create();
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        ((Button) inflate.findViewById(R.id.negative)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                create.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.positive)).setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.VaryDrillActivityPS.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString() != null && !editText.getText().toString().trim().equals("")) {
                    int size = VaryDrillActivityPS.this.selectPoints.size();
                    new ArrayList();
                    VaryDrillActivityPS.this.daoSession.loadAll(WholeVary.class);
                    WholeVary wholeVary = new WholeVary();
                    wholeVary.setDaoName("" + editText.getText().toString().trim());
                    wholeVary.setSeles(VaryDrillActivityPS.this.select_dianwei.getText().toString().trim());
                    wholeVary.setItem3(VaryDrillActivityPS.this.pa);
                    if (size >= 1) {
                        wholeVary.setPara1(VaryDrillActivityPS.this.setString(0));
                    }
                    if (size >= 2) {
                        wholeVary.setPara2(VaryDrillActivityPS.this.setString(1));
                    }
                    if (size >= 3) {
                        wholeVary.setPara3(VaryDrillActivityPS.this.setString(2));
                    }
                    if (size >= 4) {
                        wholeVary.setPara4(VaryDrillActivityPS.this.setString(3));
                    }
                    if (size >= 5) {
                        wholeVary.setPara5(VaryDrillActivityPS.this.setString(4));
                    }
                    if (size >= 6) {
                        wholeVary.setPara6(VaryDrillActivityPS.this.setString(5));
                    }
                    if (size >= 7) {
                        wholeVary.setPara7(VaryDrillActivityPS.this.setString(6));
                    }
                    if (size >= 8) {
                        wholeVary.setPara8(VaryDrillActivityPS.this.setString(7));
                    }
                    if (size >= 9) {
                        wholeVary.setPara9(VaryDrillActivityPS.this.setString(8));
                    }
                    if (size >= 10) {
                        wholeVary.setPara10(VaryDrillActivityPS.this.setString(9));
                    }
                    if (size >= 11) {
                        wholeVary.setPara11(VaryDrillActivityPS.this.setString(10));
                    }
                    if (size >= 12) {
                        wholeVary.setPara12(VaryDrillActivityPS.this.setString(11));
                    }
                    if (size >= 13) {
                        wholeVary.setPara13(VaryDrillActivityPS.this.setString(12));
                    }
                    if (size >= 14) {
                        wholeVary.setPara14(VaryDrillActivityPS.this.setString(13));
                    }
                    if (size >= 15) {
                        wholeVary.setPara15(VaryDrillActivityPS.this.setString(14));
                    }
                    VaryDrillActivityPS.this.daoSession.insertOrReplace(wholeVary);
                    create.dismiss();
                    return;
                }
                VaryDrillActivityPS varyDrillActivityPS = VaryDrillActivityPS.this;
                ShowHelper.toastShort(varyDrillActivityPS, varyDrillActivityPS.getResources().getString(R.string.input_route_name));
            }
        });
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String setString(int i) {
        return ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][0]) + "," + ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][1]) + "," + ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][2]) + "," + ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][3]) + "," + ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][4]) + "," + ((int) BasicData33.b3[this.selectPoints.get(i).intValue() - 1][5]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_ball /* 2131361912 */:
                if (this.btn_ball.getText().toString().equals(getResources().getString(R.string.start))) {
                    if (!"".equals(this.select_dianwei.getText().toString().trim())) {
                        this.btn_ball.setText(getResources().getString(R.string.stop));
                        this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_stop_selector));
                        VarispinStartMsg varispinStartMsg = new VarispinStartMsg();
                        varispinStartMsg.mode = 1;
                        varispinStartMsg.pause = this.pa;
                        varispinStartMsg.str = this.select_dianwei.getText().toString().trim();
                        EventBus.getDefault().post(varispinStartMsg);
                        break;
                    }
                } else {
                    this.btn_ball.setText(getResources().getString(R.string.start));
                    this.btn_ball.setBackground(getResources().getDrawable(R.drawable.button_dark_selector));
                    VarispinStartMsg varispinStartMsg2 = new VarispinStartMsg();
                    varispinStartMsg2.mode = 0;
                    EventBus.getDefault().post(varispinStartMsg2);
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
            case R.id.num22 /* 2131362267 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(22);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num23 /* 2131362269 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(23);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num24 /* 2131362271 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(24);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num25 /* 2131362273 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(25);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num26 /* 2131362275 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(26);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num3 /* 2131362280 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(3);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            case R.id.num4 /* 2131362291 */:
                if (this.selectPoints.size() < 15) {
                    this.selectPoints.add(4);
                } else {
                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                }
                showPoints();
                break;
            default:
                switch (id) {
                    case R.id.num1 /* 2131362251 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(1);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num10 /* 2131362252 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(10);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num11 /* 2131362253 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(11);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num12 /* 2131362254 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(12);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num13 /* 2131362255 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(13);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num14 /* 2131362256 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(14);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num15 /* 2131362257 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(15);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    case R.id.num16 /* 2131362258 */:
                        if (this.selectPoints.size() < 15) {
                            this.selectPoints.add(16);
                        } else {
                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                        }
                        showPoints();
                        break;
                    default:
                        switch (id) {
                            case R.id.num17 /* 2131362260 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(17);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num18 /* 2131362261 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(18);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num19 /* 2131362262 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(19);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num2 /* 2131362263 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(2);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num20 /* 2131362264 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(20);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            case R.id.num21 /* 2131362265 */:
                                if (this.selectPoints.size() < 15) {
                                    this.selectPoints.add(21);
                                } else {
                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                }
                                showPoints();
                                break;
                            default:
                                switch (id) {
                                    case R.id.num27 /* 2131362277 */:
                                        if (this.selectPoints.size() < 15) {
                                            this.selectPoints.add(27);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    case R.id.num28 /* 2131362278 */:
                                        if (this.selectPoints.size() < 15) {
                                            this.selectPoints.add(28);
                                        } else {
                                            ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                        }
                                        showPoints();
                                        break;
                                    default:
                                        switch (id) {
                                            case R.id.num5 /* 2131362299 */:
                                                if (this.selectPoints.size() < 15) {
                                                    this.selectPoints.add(5);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num6 /* 2131362300 */:
                                                if (this.selectPoints.size() < 15) {
                                                    this.selectPoints.add(6);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num7 /* 2131362301 */:
                                                if (this.selectPoints.size() < 15) {
                                                    this.selectPoints.add(7);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num8 /* 2131362302 */:
                                                if (this.selectPoints.size() < 15) {
                                                    this.selectPoints.add(8);
                                                } else {
                                                    ShowHelper.toastLong(this, getResources().getString(R.string.up_to));
                                                }
                                                showPoints();
                                                break;
                                            case R.id.num9 /* 2131362303 */:
                                                if (this.selectPoints.size() < 15) {
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
            this.variSpinRvAdapter = new VariSpinPTSmartRvAdapter(this.selectPoints, this);
            this.list_dev.setNestedScrollingEnabled(false);
            this.list_dev.setLayoutManager(new GridLayoutManager(this, 1));
            this.list_dev.setAdapter(this.variSpinRvAdapter);
            return;
        }
        this.selectPoints.clear();
        this.variSpinRvAdapter = new VariSpinPTSmartRvAdapter(this.selectPoints, this);
        this.list_dev.setNestedScrollingEnabled(false);
        this.list_dev.setLayoutManager(new GridLayoutManager(this, 1));
        this.list_dev.setAdapter(this.variSpinRvAdapter);
        this.select_dianwei.setText("");
        for (int i4 = 0; i4 < this.poids.length; i4++) {
            this.pos[i4].setBackground(getResources().getDrawable(R.drawable.tenniball2));
        }
    }

    @Subcriber
    private void dealFaultDataEvent(NoteVariMsg noteVariMsg) {
        if (noteVariMsg.msg == 1) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.wheel_alert));
        }
        if (noteVariMsg.msg == 2) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.gate_alert));
        }
        if (noteVariMsg.msg == 3) {
            ShowHelper.showAlertDialog(this, getResources().getString(R.string.alert), getResources().getString(R.string.no_ball_alert));
        }
    }
}
