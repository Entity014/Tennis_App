package com.pusun.pusuntennis;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pusun.pusuntennis.CourseListActivity;
import com.pusun.pusuntennis.adapter.CourseListRvAdapter;
import com.pusun.pusuntennis.utils.MyHttpUtils;
import com.pusun.pusuntennis.utils.vo.TutorialItem;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: classes3.dex */
public class CourseListActivity extends AppCompatActivity {
    private ImageView back_btn;
    private CourseListRvAdapter courseListRvAdapter;
    private RecyclerView list_rv;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_list);
        this.list_rv = (RecyclerView) findViewById(R.id.list_rv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout), new OnApplyWindowInsetsListener() { // from class: com.pusun.pusuntennis.CourseListActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return CourseListActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.back_btn);
        this.back_btn = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.CourseListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CourseListActivity.this.finish();
            }
        });
        MyHttpUtils.sendGetRequest("https://app.api.pusuntech.com/TutorialBallWay/get", new AnonymousClass2());
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        view.setPadding(view.getPaddingLeft(), insets.top, view.getPaddingRight(), insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* renamed from: com.pusun.pusuntennis.CourseListActivity$2, reason: invalid class name */
    class AnonymousClass2 implements Callback {
        AnonymousClass2() {
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            iOException.printStackTrace();
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                final List list = (List) new Gson().fromJson(response.body().string(), new TypeToken<List<TutorialItem>>() { // from class: com.pusun.pusuntennis.CourseListActivity.2.1
                }.getType());
                CourseListActivity.this.runOnUiThread(new Runnable() { // from class: com.pusun.pusuntennis.CourseListActivity$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        CourseListActivity.AnonymousClass2.this.m376lambda$onResponse$0$compusunpusuntennisCourseListActivity$2(list);
                    }
                });
                return;
            }
            CourseListActivity.this.runOnUiThread(new Runnable() { // from class: com.pusun.pusuntennis.CourseListActivity$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CourseListActivity.AnonymousClass2.this.m377lambda$onResponse$1$compusunpusuntennisCourseListActivity$2();
                }
            });
        }

        /* renamed from: lambda$onResponse$0$com-pusun-pusuntennis-CourseListActivity$2, reason: not valid java name */
        /* synthetic */ void m376lambda$onResponse$0$compusunpusuntennisCourseListActivity$2(List list) {
            if (list.isEmpty()) {
                return;
            }
            CourseListActivity.this.courseListRvAdapter = new CourseListRvAdapter(list, CourseListActivity.this);
            CourseListActivity.this.list_rv.setNestedScrollingEnabled(false);
            CourseListActivity.this.list_rv.setLayoutManager(new GridLayoutManager(CourseListActivity.this, 1));
            CourseListActivity.this.list_rv.setAdapter(CourseListActivity.this.courseListRvAdapter);
        }

        /* renamed from: lambda$onResponse$1$com-pusun-pusuntennis-CourseListActivity$2, reason: not valid java name */
        /* synthetic */ void m377lambda$onResponse$1$compusunpusuntennisCourseListActivity$2() {
            Toast.makeText(CourseListActivity.this, "网络异常，请检查网络连接", 0).show();
        }
    }
}
