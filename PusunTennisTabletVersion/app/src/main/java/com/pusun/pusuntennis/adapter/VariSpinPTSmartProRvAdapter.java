package com.pusun.pusuntennis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pusun.pusuntennis.R;
import com.pusun.pusuntennis.utils.BasicData31;
import com.pusun.pusuntennis.utils.VarispinMsg;
import java.util.List;
import org.simple.eventbus.EventBus;

/* loaded from: classes3.dex */
public class VariSpinPTSmartProRvAdapter extends RecyclerView.Adapter<VariSpinPTSmartProRvAdapter.ViewHolder> {
    private Context context;
    public List<Integer> infoDeviceDtos;

    public VariSpinPTSmartProRvAdapter(List<Integer> list, Context context) {
        this.infoDeviceDtos = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.varyspinrv_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final int intValue = this.infoDeviceDtos.get(i).intValue();
        TextView textView = viewHolder.s1;
        StringBuilder sb = new StringBuilder("");
        int i2 = intValue - 1;
        sb.append((int) BasicData31.b3[i2][5]);
        textView.setText(sb.toString());
        viewHolder.lr_value.setText("" + ((BasicData31.b3[i2][0] / 60) + 3));
        viewHolder.ud_value.setText("" + (BasicData31.b3[i2][1] / 100));
        TextView textView2 = viewHolder.sp_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData31.b3[i2][2]);
        textView2.setText(sb2.toString());
        TextView textView3 = viewHolder.fre_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData31.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
        viewHolder.spin_value.setText("" + ((BasicData31.b3[i2][4] - 50) / 5));
        viewHolder.pos = i + 1;
        viewHolder.l_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[0] = (short) (sArr[0] + 60);
                if (BasicData31.b3[intValue - 1][0] > 2370) {
                    BasicData31.b3[intValue - 1][0] = 2370;
                }
                viewHolder.lr_value.setText("" + ((BasicData31.b3[intValue - 1][0] / 60) + 3));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.l_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData31.b3[intValue - 1][0] = (short) (BasicData31.b3[intValue - 1][0] - 60);
                if (BasicData31.b3[intValue - 1][0] < 210) {
                    BasicData31.b3[intValue - 1][0] = 210;
                }
                viewHolder.lr_value.setText("" + ((BasicData31.b3[intValue - 1][0] / 60) + 3));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.u_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[1] = (short) (sArr[1] + 100);
                if (BasicData31.b3[intValue - 1][1] > 4500) {
                    BasicData31.b3[intValue - 1][1] = 4500;
                }
                viewHolder.ud_value.setText("" + (BasicData31.b3[intValue - 1][1] / 100));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.u_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData31.b3[intValue - 1][1] = (short) (BasicData31.b3[intValue - 1][1] - 100);
                if (BasicData31.b3[intValue - 1][1] < 500) {
                    BasicData31.b3[intValue - 1][1] = 500;
                }
                viewHolder.ud_value.setText("" + (BasicData31.b3[intValue - 1][1] / 100));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.v_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[2] = (short) (sArr[2] + 1);
                if (BasicData31.b3[intValue - 1][2] > 160) {
                    BasicData31.b3[intValue - 1][2] = 160;
                }
                TextView textView4 = viewHolder.sp_value;
                StringBuilder sb4 = new StringBuilder("");
                sb4.append(BasicData31.b3[intValue - 1][2]);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.v_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[2] = (short) (sArr[2] - 1);
                if (BasicData31.b3[intValue - 1][2] < 50) {
                    BasicData31.b3[intValue - 1][2] = 50;
                }
                TextView textView4 = viewHolder.sp_value;
                StringBuilder sb4 = new StringBuilder("");
                sb4.append(BasicData31.b3[intValue - 1][2]);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.f_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData31.b3[intValue - 1][3] > 65) {
                    BasicData31.b3[intValue - 1][3] = 65;
                }
                TextView textView4 = viewHolder.fre_value;
                StringBuilder sb4 = new StringBuilder("");
                double d2 = BasicData31.b3[intValue - 1][3];
                Double.valueOf(d2).getClass();
                sb4.append(d2 / 10.0d);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.f_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData31.b3[intValue - 1][3] < 25) {
                    BasicData31.b3[intValue - 1][3] = 25;
                }
                TextView textView4 = viewHolder.fre_value;
                StringBuilder sb4 = new StringBuilder("");
                double d2 = BasicData31.b3[intValue - 1][3];
                Double.valueOf(d2).getClass();
                sb4.append(d2 / 10.0d);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.s_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[4] = (short) (sArr[4] + 5);
                if (BasicData31.b3[intValue - 1][4] > 80) {
                    BasicData31.b3[intValue - 1][4] = 80;
                }
                viewHolder.spin_value.setText("" + ((BasicData31.b3[intValue - 1][4] - 50) / 5));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.s_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartProRvAdapter.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData31.b3[intValue - 1];
                sArr[4] = (short) (sArr[4] - 5);
                if (BasicData31.b3[intValue - 1][4] < 20) {
                    BasicData31.b3[intValue - 1][4] = 20;
                }
                viewHolder.spin_value.setText("" + ((BasicData31.b3[intValue - 1][4] - 50) / 5));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData31.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartProRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartProRvAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Integer> list = this.infoDeviceDtos;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView f_ad;
        TextView f_de;
        TextView fre_value;
        TextView l_ad;
        TextView l_de;
        TextView lr_value;
        int pos;
        TextView s1;
        TextView s_ad;
        TextView s_de;
        TextView sp_value;
        TextView spin_value;
        TextView u_ad;
        TextView u_de;
        TextView ud_value;
        TextView v_ad;
        TextView v_de;

        public ViewHolder(View view) {
            super(view);
            this.s1 = (TextView) view.findViewById(R.id.s1);
            this.lr_value = (TextView) view.findViewById(R.id.lr_value);
            this.ud_value = (TextView) view.findViewById(R.id.ud_value);
            this.sp_value = (TextView) view.findViewById(R.id.sp_value);
            this.fre_value = (TextView) view.findViewById(R.id.fre_value);
            this.spin_value = (TextView) view.findViewById(R.id.spin_value);
            this.l_ad = (TextView) view.findViewById(R.id.l_ad);
            this.l_de = (TextView) view.findViewById(R.id.l_de);
            this.u_ad = (TextView) view.findViewById(R.id.u_ad);
            this.u_de = (TextView) view.findViewById(R.id.u_de);
            this.v_ad = (TextView) view.findViewById(R.id.v_ad);
            this.v_de = (TextView) view.findViewById(R.id.v_de);
            this.f_ad = (TextView) view.findViewById(R.id.f_ad);
            this.f_de = (TextView) view.findViewById(R.id.f_de);
            this.s_ad = (TextView) view.findViewById(R.id.s_ad);
            this.s_de = (TextView) view.findViewById(R.id.s_de);
        }
    }
}
