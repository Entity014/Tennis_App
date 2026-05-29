package com.pusun.pusuntennis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pusun.pusuntennis.R;
import com.pusun.pusuntennis.utils.BasicData33;
import com.pusun.pusuntennis.utils.VarispinMsg;
import java.util.List;
import org.simple.eventbus.EventBus;

/* loaded from: classes3.dex */
public class VariSpinPTSmartRvAdapter extends RecyclerView.Adapter<VariSpinPTSmartRvAdapter.ViewHolder> {
    private Context context;
    public List<Integer> infoDeviceDtos;

    public VariSpinPTSmartRvAdapter(List<Integer> list, Context context) {
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
        sb.append((int) BasicData33.b3[i2][5]);
        textView.setText(sb.toString());
        viewHolder.lr_value.setText("" + (74 - (BasicData33.b3[i2][0] / 30)));
        viewHolder.ud_value.setText("" + (64 - (BasicData33.b3[i2][1] / 30)));
        TextView textView2 = viewHolder.sp_value;
        StringBuilder sb2 = new StringBuilder("");
        sb2.append(BasicData33.b3[i2][2]);
        textView2.setText(sb2.toString());
        TextView textView3 = viewHolder.fre_value;
        StringBuilder sb3 = new StringBuilder("");
        double d = BasicData33.b3[i2][3];
        Double.valueOf(d).getClass();
        sb3.append(d / 10.0d);
        textView3.setText(sb3.toString());
        viewHolder.spin_value.setText("" + ((BasicData33.b3[i2][4] - 50) / 5));
        viewHolder.pos = i + 1;
        viewHolder.l_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[0] = (short) (sArr[0] + 30);
                if (BasicData33.b3[intValue - 1][0] > 2190) {
                    BasicData33.b3[intValue - 1][0] = 2190;
                }
                viewHolder.lr_value.setText("" + (74 - (BasicData33.b3[intValue - 1][0] / 30)));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.l_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData33.b3[intValue - 1][0] = (short) (BasicData33.b3[intValue - 1][0] - 30);
                if (BasicData33.b3[intValue - 1][0] < 1260) {
                    BasicData33.b3[intValue - 1][0] = 1260;
                }
                viewHolder.lr_value.setText("" + (74 - (BasicData33.b3[intValue - 1][0] / 30)));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.u_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasicData33.b3[intValue - 1][1] = (short) (BasicData33.b3[intValue - 1][1] - 30);
                if (BasicData33.b3[intValue - 1][1] < 1110) {
                    BasicData33.b3[intValue - 1][1] = 1110;
                }
                viewHolder.ud_value.setText("" + (64 - (BasicData33.b3[intValue - 1][1] / 30)));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.u_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[1] = (short) (sArr[1] + 30);
                if (BasicData33.b3[intValue - 1][1] > 1830) {
                    BasicData33.b3[intValue - 1][1] = 1830;
                }
                viewHolder.ud_value.setText("" + (64 - (BasicData33.b3[intValue - 1][1] / 30)));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.v_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[2] = (short) (sArr[2] + 1);
                if (BasicData33.b3[intValue - 1][2] > 20) {
                    BasicData33.b3[intValue - 1][2] = 20;
                }
                TextView textView4 = viewHolder.sp_value;
                StringBuilder sb4 = new StringBuilder("");
                sb4.append(BasicData33.b3[intValue - 1][2]);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.v_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[2] = (short) (sArr[2] - 1);
                if (BasicData33.b3[intValue - 1][2] < 45) {
                    BasicData33.b3[intValue - 1][2] = 45;
                }
                TextView textView4 = viewHolder.sp_value;
                StringBuilder sb4 = new StringBuilder("");
                sb4.append(BasicData33.b3[intValue - 1][2]);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.f_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[3] = (short) (sArr[3] + 5);
                if (BasicData33.b3[intValue - 1][3] > 65) {
                    BasicData33.b3[intValue - 1][3] = 65;
                }
                TextView textView4 = viewHolder.fre_value;
                StringBuilder sb4 = new StringBuilder("");
                double d2 = BasicData33.b3[intValue - 1][3];
                Double.valueOf(d2).getClass();
                sb4.append(d2 / 10.0d);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.f_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[3] = (short) (sArr[3] - 5);
                if (BasicData33.b3[intValue - 1][3] < 25) {
                    BasicData33.b3[intValue - 1][3] = 25;
                }
                TextView textView4 = viewHolder.fre_value;
                StringBuilder sb4 = new StringBuilder("");
                double d2 = BasicData33.b3[intValue - 1][3];
                Double.valueOf(d2).getClass();
                sb4.append(d2 / 10.0d);
                textView4.setText(sb4.toString());
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue - 1][5] - 1;
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.s_ad.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[4] = (short) (sArr[4] + 5);
                if (BasicData33.b3[intValue - 1][4] > 80) {
                    BasicData33.b3[intValue - 1][4] = 80;
                }
                viewHolder.spin_value.setText("" + ((BasicData33.b3[intValue - 1][4] - 50) / 5));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.s_de.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.VariSpinPTSmartRvAdapter.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                short[] sArr = BasicData33.b3[intValue - 1];
                sArr[4] = (short) (sArr[4] - 5);
                if (BasicData33.b3[intValue - 1][4] < 20) {
                    BasicData33.b3[intValue - 1][4] = 20;
                }
                viewHolder.spin_value.setText("" + ((BasicData33.b3[intValue - 1][4] - 50) / 5));
                VarispinMsg varispinMsg = new VarispinMsg();
                varispinMsg.num = BasicData33.b3[intValue + (-1)][5] + (-1);
                varispinMsg.position = viewHolder.pos;
                varispinMsg.length = VariSpinPTSmartRvAdapter.this.infoDeviceDtos.size();
                EventBus.getDefault().post(varispinMsg);
                VariSpinPTSmartRvAdapter.this.notifyDataSetChanged();
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
