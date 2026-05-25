package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AddressPicker extends LinkagePicker {
    private boolean hideCounty;
    private boolean hideProvince;
    private OnAddressPickListener onAddressPickListener;

    public static class County extends Area {
    }

    public interface OnAddressPickListener {
        void onAddressPicked(String str, String str2, String str3);
    }

    public AddressPicker(Activity activity, ArrayList<Province> arrayList) {
        super(activity);
        this.hideProvince = false;
        this.hideCounty = false;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Province province = arrayList.get(i);
            this.firstList.add(province.getAreaName());
            ArrayList<City> cities = province.getCities();
            ArrayList<String> arrayList2 = new ArrayList<>();
            ArrayList<ArrayList<String>> arrayList3 = new ArrayList<>();
            int size2 = cities.size();
            for (int i2 = 0; i2 < size2; i2++) {
                City city = cities.get(i2);
                arrayList2.add(city.getAreaName());
                ArrayList<County> counties = city.getCounties();
                ArrayList<String> arrayList4 = new ArrayList<>();
                int size3 = counties.size();
                if (size3 == 0) {
                    arrayList4.add(city.getAreaName());
                } else {
                    for (int i3 = 0; i3 < size3; i3++) {
                        arrayList4.add(counties.get(i3).getAreaName());
                    }
                }
                arrayList3.add(arrayList4);
            }
            this.secondList.add(arrayList2);
            this.thirdList.add(arrayList3);
        }
    }

    @Override // cn.qqtheme.framework.picker.LinkagePicker
    public void setSelectedItem(String str, String str2, String str3) {
        super.setSelectedItem(str, str2, str3);
    }

    public void setHideProvince(boolean z) {
        this.hideProvince = z;
    }

    public void setHideCounty(boolean z) {
        this.hideCounty = z;
    }

    public void setOnAddressPickListener(OnAddressPickListener onAddressPickListener) {
        this.onAddressPickListener = onAddressPickListener;
    }

    @Override // cn.qqtheme.framework.picker.LinkagePicker
    @Deprecated
    public void setOnLinkageListener(LinkagePicker.OnLinkageListener onLinkageListener) {
        throw new UnsupportedOperationException("Please use setOnAddressPickListener instead.");
    }

    @Override // cn.qqtheme.framework.picker.LinkagePicker, cn.qqtheme.framework.popup.ConfirmPopup
    protected View makeCenterView() {
        if (this.hideCounty) {
            this.hideProvince = false;
        }
        if (this.firstList.size() == 0) {
            throw new IllegalArgumentException("please initial data at first, can't be empty");
        }
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity);
        int i = this.screenWidthPixels / 3;
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        wheelView.setOffset(this.offset);
        linearLayout.addView(wheelView);
        if (this.hideProvince) {
            wheelView.setVisibility(8);
        }
        final WheelView wheelView2 = new WheelView(this.activity);
        wheelView2.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView2.setTextSize(this.textSize);
        wheelView2.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView2.setLineVisible(this.lineVisible);
        wheelView2.setLineColor(this.lineColor);
        wheelView2.setOffset(this.offset);
        linearLayout.addView(wheelView2);
        final WheelView wheelView3 = new WheelView(this.activity);
        wheelView3.setLayoutParams(new LinearLayout.LayoutParams(i, -2));
        wheelView3.setTextSize(this.textSize);
        wheelView3.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView3.setLineVisible(this.lineVisible);
        wheelView3.setLineColor(this.lineColor);
        wheelView3.setOffset(this.offset);
        linearLayout.addView(wheelView3);
        if (this.hideCounty) {
            wheelView3.setVisibility(8);
        }
        wheelView.setItems(this.firstList, this.selectedFirstIndex);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.AddressPicker.1
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i2, String str) {
                AddressPicker.this.selectedFirstText = str;
                AddressPicker.this.selectedFirstIndex = i2;
                AddressPicker.this.selectedThirdIndex = 0;
                wheelView2.setItems(AddressPicker.this.secondList.get(AddressPicker.this.selectedFirstIndex), z ? 0 : AddressPicker.this.selectedSecondIndex);
                wheelView3.setItems(AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex).get(0), z ? 0 : AddressPicker.this.selectedThirdIndex);
            }
        });
        wheelView2.setItems(this.secondList.get(this.selectedFirstIndex), this.selectedSecondIndex);
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.AddressPicker.2
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i2, String str) {
                AddressPicker.this.selectedSecondText = str;
                AddressPicker.this.selectedSecondIndex = i2;
                wheelView3.setItems(AddressPicker.this.thirdList.get(AddressPicker.this.selectedFirstIndex).get(AddressPicker.this.selectedSecondIndex), z ? 0 : AddressPicker.this.selectedThirdIndex);
            }
        });
        wheelView3.setItems(this.thirdList.get(this.selectedFirstIndex).get(this.selectedSecondIndex), this.selectedThirdIndex);
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.AddressPicker.3
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i2, String str) {
                AddressPicker.this.selectedThirdText = str;
                AddressPicker.this.selectedThirdIndex = i2;
            }
        });
        return linearLayout;
    }

    @Override // cn.qqtheme.framework.picker.LinkagePicker, cn.qqtheme.framework.popup.ConfirmPopup
    public void onSubmit() {
        OnAddressPickListener onAddressPickListener = this.onAddressPickListener;
        if (onAddressPickListener != null) {
            if (this.hideCounty) {
                onAddressPickListener.onAddressPicked(this.selectedFirstText, this.selectedSecondText, null);
            } else {
                onAddressPickListener.onAddressPicked(this.selectedFirstText, this.selectedSecondText, this.selectedThirdText);
            }
        }
    }

    public static abstract class Area {
        String areaId;
        String areaName;

        public String getAreaId() {
            return this.areaId;
        }

        public void setAreaId(String str) {
            this.areaId = str;
        }

        public String getAreaName() {
            return this.areaName;
        }

        public void setAreaName(String str) {
            this.areaName = str;
        }

        public String toString() {
            return "areaId=" + this.areaId + ",areaName=" + this.areaName;
        }
    }

    public static class Province extends Area {
        ArrayList<City> cities = new ArrayList<>();

        public ArrayList<City> getCities() {
            return this.cities;
        }

        public void setCities(ArrayList<City> arrayList) {
            this.cities = arrayList;
        }
    }

    public static class City extends Area {
        private ArrayList<County> counties = new ArrayList<>();

        public ArrayList<County> getCounties() {
            return this.counties;
        }

        public void setCounties(ArrayList<County> arrayList) {
            this.counties = arrayList;
        }
    }
}
