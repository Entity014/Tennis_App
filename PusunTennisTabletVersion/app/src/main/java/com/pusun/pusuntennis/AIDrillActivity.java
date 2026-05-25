package com.pusun.pusuntennis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.simple.eventbus.EventBus;

/* loaded from: classes3.dex */
public class AIDrillActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_btn;
    private Button create_drill;
    private int fm;
    private Button high;
    private Button left;
    private Button long_pause;
    private Button low;
    private Button mid;
    private Button no_pause;
    private TextView numadd;
    private TextView numde;
    private TextView numtv;
    private int pa;
    private Button right;
    private Button short_pause;
    private int lr = 1;
    private int lh = 1;
    private int ballnum = 3;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_aidrill);
        EventBus.getDefault().register(this);
        this.fm = getIntent().getIntExtra("fm", 1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.AIDrillActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return AIDrillActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.left = (Button) findViewById(R.id.left);
        this.right = (Button) findViewById(R.id.right);
        this.low = (Button) findViewById(R.id.low);
        this.mid = (Button) findViewById(R.id.mid);
        this.high = (Button) findViewById(R.id.high);
        this.no_pause = (Button) findViewById(R.id.no_pause);
        this.short_pause = (Button) findViewById(R.id.short_pause);
        this.long_pause = (Button) findViewById(R.id.long_pause);
        this.numde = (TextView) findViewById(R.id.numde);
        this.numadd = (TextView) findViewById(R.id.numadd);
        this.numtv = (TextView) findViewById(R.id.numtv);
        this.left.setOnClickListener(this);
        this.right.setOnClickListener(this);
        this.low.setOnClickListener(this);
        this.mid.setOnClickListener(this);
        this.high.setOnClickListener(this);
        this.no_pause.setOnClickListener(this);
        this.short_pause.setOnClickListener(this);
        this.long_pause.setOnClickListener(this);
        this.numadd.setOnClickListener(this);
        this.numde.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.back_btn);
        this.back_btn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.AIDrillActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AIDrillActivity.this.finish();
            }
        });
        Button button = (Button) findViewById(R.id.create_drill);
        this.create_drill = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.AIDrillActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent;
                if (AIDrillActivity.this.fm != 1 && AIDrillActivity.this.fm != 2) {
                    if (AIDrillActivity.this.fm == 3) {
                        intent = new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivityPTP.class);
                    } else if (AIDrillActivity.this.fm == 5) {
                        intent = new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivityDrill.class);
                    } else if (AIDrillActivity.this.fm == 6) {
                        intent = new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivityPPSP.class);
                    } else {
                        intent = AIDrillActivity.this.fm == 7 ? new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivityPPS.class) : new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivityPS.class);
                    }
                } else {
                    intent = new Intent(AIDrillActivity.this, (Class<?>) VaryDrillActivity.class);
                }
                intent.putExtra("ai", 6);
                intent.putExtra("lr", AIDrillActivity.this.lr);
                intent.putExtra("lh", AIDrillActivity.this.lh);
                intent.putExtra("pa", AIDrillActivity.this.pa);
                intent.putExtra("ballnum", AIDrillActivity.this.ballnum);
                AIDrillActivity.this.startActivity(intent);
                AIDrillActivity.this.finish();
            }
        });
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.high /* 2131362123 */:
                this.lh = 2;
                this.low.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.mid.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                break;
            case R.id.left /* 2131362168 */:
                this.lr = 0;
                this.left.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.right.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                break;
            case R.id.long_pause /* 2131362179 */:
                this.pa = 2;
                this.no_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.short_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.long_pause.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                break;
            case R.id.low /* 2131362180 */:
                this.lh = 0;
                this.low.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.mid.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.high.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                break;
            case R.id.mid /* 2131362200 */:
                this.lh = 1;
                this.low.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.mid.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.high.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                break;
            case R.id.no_pause /* 2131362239 */:
                this.pa = 0;
                this.no_pause.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.short_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.long_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                break;
            case R.id.numadd /* 2131362306 */:
                int i = this.ballnum + 1;
                this.ballnum = i;
                if (i > 9) {
                    this.ballnum = 9;
                }
                this.numtv.setText("" + this.ballnum);
                break;
            case R.id.numde /* 2131362308 */:
                int i2 = this.ballnum - 1;
                this.ballnum = i2;
                if (i2 < 2) {
                    this.ballnum = 2;
                }
                this.numtv.setText("" + this.ballnum);
                break;
            case R.id.right /* 2131362369 */:
                this.lr = 1;
                this.left.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.right.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                break;
            case R.id.short_pause /* 2131362430 */:
                this.pa = 1;
                this.no_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                this.short_pause.setBackground(getResources().getDrawable(R.drawable.corner_dark_green_default));
                this.long_pause.setBackground(getResources().getDrawable(R.drawable.code_button_bg_default));
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
