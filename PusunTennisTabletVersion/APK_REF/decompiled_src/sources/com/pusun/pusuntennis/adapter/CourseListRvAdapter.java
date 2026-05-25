package com.pusun.pusuntennis.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pusun.pusuntennis.CourseDetailActivity;
import com.pusun.pusuntennis.MyApplication;
import com.pusun.pusuntennis.R;
import com.pusun.pusuntennis.utils.GlideUtil;
import com.pusun.pusuntennis.utils.vo.TutorialItem;
import java.util.List;

/* loaded from: classes3.dex */
public class CourseListRvAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<TutorialItem> infoDeviceDtos;

    public CourseListRvAdapter(List<TutorialItem> list, Context context) {
        this.infoDeviceDtos = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.course_list_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final TutorialItem tutorialItem = this.infoDeviceDtos.get(i);
        viewHolder.title.setText(tutorialItem.getDaoName());
        viewHolder.praise_num.setText((this.infoDeviceDtos.get(i).getUseTimes() + 60) + "人练过");
        viewHolder.drill_tv.setText(this.infoDeviceDtos.get(i).getInfo() + "");
        if (this.infoDeviceDtos.get(i).getImageRoute() != null) {
            if (!this.infoDeviceDtos.get(i).getImageRoute().contains("http")) {
                GlideUtil.loadImage(this.context, MyApplication.BASE_URL + "/" + this.infoDeviceDtos.get(i).getImageRoute(), viewHolder.img1);
            } else {
                GlideUtil.loadImage(this.context, this.infoDeviceDtos.get(i).getImageRoute(), viewHolder.img1);
            }
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.CourseListRvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(CourseListRvAdapter.this.context, (Class<?>) CourseDetailActivity.class);
                intent.putExtra("data", tutorialItem);
                CourseListRvAdapter.this.context.startActivity(intent);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TutorialItem> list = this.infoDeviceDtos;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled((CourseListRvAdapter) viewHolder);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView drill_tv;
        ImageView img1;
        TextView[] pos;
        TextView praise_num;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.img1 = (ImageView) view.findViewById(R.id.img1);
            this.praise_num = (TextView) view.findViewById(R.id.praise_num);
            this.drill_tv = (TextView) view.findViewById(R.id.drill_tv);
        }
    }
}
