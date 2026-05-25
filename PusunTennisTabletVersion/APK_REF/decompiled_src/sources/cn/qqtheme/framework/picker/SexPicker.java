package cn.qqtheme.framework.picker;

import android.app.Activity;

/* loaded from: classes.dex */
public class SexPicker extends OptionPicker {
    public SexPicker(Activity activity) {
        super(activity, new String[]{"男", "女", "保密"});
    }

    public void onlyMaleAndFemale() {
        this.options.remove(this.options.size() - 1);
    }
}
