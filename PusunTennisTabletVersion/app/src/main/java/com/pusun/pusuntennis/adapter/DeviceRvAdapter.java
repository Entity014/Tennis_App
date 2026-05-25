package com.pusun.pusuntennis.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.clj.fastble.data.BleDevice;
import com.pusun.pusuntennis.MainActivity;
import com.pusun.pusuntennis.MainActivity3;
import com.pusun.pusuntennis.MainActivity6;
import com.pusun.pusuntennis.MainActivity6L;
import com.pusun.pusuntennis.MainActivity6New;
import com.pusun.pusuntennis.MainActivity7New;
import com.pusun.pusuntennis.MainActivity7NewP;
import com.pusun.pusuntennis.MainActivity8;
import com.pusun.pusuntennis.MainActivity9;
import com.pusun.pusuntennis.MainActivityA;
import com.pusun.pusuntennis.MainActivityPM;
import com.pusun.pusuntennis.MainActivityPMP;
import com.pusun.pusuntennis.MainActivityPP;
import com.pusun.pusuntennis.MainActivityPad1;
import com.pusun.pusuntennis.MainActivityPad2;
import com.pusun.pusuntennis.MainActivityPadPro;
import com.pusun.pusuntennis.MainActivityPadX;
import com.pusun.pusuntennis.MainActivityXB;
import com.pusun.pusuntennis.MainActivityXV;
import com.pusun.pusuntennis.MainActivitys7New;
import com.pusun.pusuntennis.MainActivitys9;
import com.pusun.pusuntennis.R;
import java.util.List;
import org.simple.eventbus.EventBus;

/* loaded from: classes3.dex */
public class DeviceRvAdapter extends RecyclerView.Adapter<DeviceRvAdapter.ViewHolder> {
    private Context context;
    public List<BleDevice> infoDeviceDtos;

    public DeviceRvAdapter(List<BleDevice> list, Context context) {
        this.infoDeviceDtos = list;
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.devicerv_item, (ViewGroup) null));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final BleDevice bleDevice = this.infoDeviceDtos.get(i);
        viewHolder.title.setText(this.context.getResources().getString(R.string.pt9001_series));
        if (bleDevice.getName().startsWith("PT5") || bleDevice.getName().startsWith("PT6") || bleDevice.getName().startsWith("PA6")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.smart_series));
        }
        if (bleDevice.getName().startsWith("PT0") && Integer.valueOf(bleDevice.getName().toString().trim().substring(3, 9)).intValue() > 240712) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.smart_lite_series));
        }
        if (bleDevice.getName().startsWith("PT9")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.mini_series));
        }
        if (bleDevice.getName().startsWith("PP9")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ppmini_series));
        }
        if (bleDevice.getName().startsWith("PM6")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.pt_minipro_series));
        }
        if (bleDevice.getName().startsWith("PA8")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.smartpro_series));
        }
        if (bleDevice.getName().startsWith("PR1")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.smartpri_series));
        }
        if (bleDevice.getName().startsWith("PA9")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.smartpro_pp));
        }
        if (bleDevice.getName().startsWith("PM8")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.pp_minipro_series));
        }
        if (bleDevice.getName().startsWith("PM1")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ptmax_series));
        }
        if (bleDevice.getName().startsWith("PM2")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ptmax_series));
        }
        if (bleDevice.getName().startsWith("PM3")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ptmax_series));
        }
        if (bleDevice.getName().startsWith("PP6")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ppsmart_series));
        }
        if (bleDevice.getName().startsWith("PP3")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.pp8001_series));
        }
        if (bleDevice.getName().startsWith("PU3")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.pp8001_series));
        }
        if (bleDevice.getName().startsWith("PT4")) {
            viewHolder.title.setText(this.context.getResources().getString(R.string.ptsmart_padel_series));
        }
        viewHolder.name.setText(bleDevice.getName());
        viewHolder.paddle.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.DeviceRvAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPadPro.class);
                intent.putExtra("device", bleDevice);
                DeviceRvAdapter.this.context.startActivity(intent);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pusun.pusuntennis.adapter.DeviceRvAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (bleDevice.getName().startsWith("PT5")) {
                    Intent intent = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity3.class);
                    intent.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent);
                    return;
                }
                if (bleDevice.getName().startsWith("PM6")) {
                    Intent intent2 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPM.class);
                    intent2.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent2);
                    return;
                }
                if (bleDevice.getName().startsWith("PM8")) {
                    Intent intent3 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPMP.class);
                    intent3.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent3);
                    return;
                }
                if (bleDevice.getName().startsWith("PM2")) {
                    Intent intent4 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityXB.class);
                    intent4.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent4);
                    return;
                }
                if (bleDevice.getName().startsWith("PM1")) {
                    Intent intent5 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityXV.class);
                    intent5.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent5);
                    return;
                }
                if (bleDevice.getName().startsWith("PM3")) {
                    Intent intent6 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPadX.class);
                    intent6.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent6);
                    return;
                }
                if (bleDevice.getName().startsWith("PT2")) {
                    Intent intent7 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPad2.class);
                    intent7.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent7);
                    return;
                }
                if (bleDevice.getName().startsWith("PT6")) {
                    Intent intent8 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity6New.class);
                    intent8.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent8);
                    return;
                }
                if (bleDevice.getName().startsWith("PR1")) {
                    Intent intent9 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity6.class);
                    intent9.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent9);
                    return;
                }
                if (bleDevice.getName().startsWith("PT0") && Integer.valueOf(bleDevice.getName().toString().trim().substring(3, 9)).intValue() > 240712) {
                    Intent intent10 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity6L.class);
                    intent10.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent10);
                    return;
                }
                if (bleDevice.getName().startsWith("PA6")) {
                    Intent intent11 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityA.class);
                    intent11.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent11);
                    return;
                }
                if (bleDevice.getName().startsWith("PA8")) {
                    Intent intent12 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivitys7New.class);
                    intent12.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent12);
                    return;
                }
                if (bleDevice.getName().startsWith("PA9")) {
                    Intent intent13 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivitys9.class);
                    intent13.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent13);
                    return;
                }
                if (bleDevice.getName().startsWith("PP6")) {
                    Intent intent14 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity8.class);
                    intent14.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent14);
                    return;
                }
                if (bleDevice.getName().startsWith("PT9")) {
                    Intent intent15 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity9.class);
                    intent15.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent15);
                    return;
                }
                if (bleDevice.getName().startsWith("PP3")) {
                    Intent intent16 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity7NewP.class);
                    intent16.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent16);
                    return;
                }
                if (bleDevice.getName().startsWith("PP9")) {
                    Intent intent17 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPP.class);
                    intent17.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent17);
                } else if (bleDevice.getName().startsWith("PT7") || bleDevice.getName().startsWith("PT8")) {
                    Intent intent18 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity7New.class);
                    intent18.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent18);
                } else if (bleDevice.getName().startsWith("PT4")) {
                    Intent intent19 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivityPad1.class);
                    intent19.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent19);
                } else {
                    Intent intent20 = new Intent(DeviceRvAdapter.this.context, (Class<?>) MainActivity.class);
                    intent20.putExtra("device", bleDevice);
                    DeviceRvAdapter.this.context.startActivity(intent20);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BleDevice> list = this.infoDeviceDtos;
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
        ImageView image;
        TextView name;
        TextView paddle;
        TextView title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.name = (TextView) view.findViewById(R.id.promote);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.paddle = (TextView) view.findViewById(R.id.paddle);
        }
    }
}
