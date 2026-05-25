package com.pusun.pusuntennis.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.pusun.pusuntennis.R;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ShowHelper {
    private static Toast mToast;
    private static ProgressDialog progressDialog;

    public static void toastShort(Context context, CharSequence charSequence) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(context, charSequence, 0);
        } else {
            toast.setText(charSequence);
        }
        mToast.show();
    }

    public static void toastLong(Context context, CharSequence charSequence) {
        Toast toast = mToast;
        if (toast == null) {
            mToast = Toast.makeText(context, charSequence, 1);
        } else {
            toast.setText(charSequence);
        }
        mToast.show();
    }

    public static void toastShort(Context context, int i) {
        Toast.makeText(context, context.getText(i), 0).show();
    }

    public static void toastLong(Context context, int i) {
        toastLong(context, context.getText(i));
    }

    public static void showProgressDialog(Context context, String str) {
        ProgressDialog progressDialog2 = progressDialog;
        if (progressDialog2 == null) {
            progressDialog = ProgressDialog.show(context, "", str, true);
        } else {
            progressDialog2.setMessage(str);
        }
        if (progressDialog.isShowing()) {
            return;
        }
        progressDialog.show();
    }

    public static boolean isProgressDialogShowing() {
        ProgressDialog progressDialog2 = progressDialog;
        return progressDialog2 != null && progressDialog2.isShowing();
    }

    public static void setProgressDialogMessage(String str) {
        ProgressDialog progressDialog2 = progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.setMessage(str);
        }
    }

    public static String getMessage(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (jSONObject == null) {
            return "";
        }
        String optString = jSONObject.optString("message");
        if (TextUtils.isEmpty(optString)) {
            optString = jSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
        }
        if (TextUtils.isEmpty(optString) && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
            optString = optJSONObject.optString(NotificationCompat.CATEGORY_MESSAGE);
            if (TextUtils.isEmpty(optString)) {
                optString = optJSONObject.optString("message");
            }
        }
        return optString == null ? jSONObject.toString() : optString;
    }

    public static boolean isResponseOperationOk(Context context, JSONObject jSONObject) {
        boolean equals = jSONObject.optString("code", "2").equals(SessionDescription.SUPPORTED_SDP_VERSION);
        if (!equals) {
            toastLong(context, getMessage(jSONObject));
        }
        return equals;
    }

    public static void dismissProgressDialog() {
        ProgressDialog progressDialog2 = progressDialog;
        if (progressDialog2 != null && progressDialog2.getContext() != null && progressDialog.getWindow() != null) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    public static void showAlertDialog(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(str);
        builder.setPositiveButton("是", onClickListener);
        builder.setNegativeButton("否", (DialogInterface.OnClickListener) null);
        builder.show();
    }

    public static void showAlertDialogUS(Context context, String str, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Notice");
        builder.setMessage(str);
        builder.setPositiveButton("Confirm", onClickListener);
        builder.show();
    }

    public static void showAlertDialogSelect(Context context, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(str);
        builder.setPositiveButton("左侧", onClickListener);
        builder.setNegativeButton("右侧", onClickListener2);
        builder.show();
    }

    public static void showAlertDialog(Context context, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(str);
        builder.setPositiveButton("左场练习", onClickListener);
        builder.setNegativeButton("右场练习", onClickListener2);
        builder.show();
    }

    public static void showUpdateApkAlertDialog(Context context, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("版本升级提示");
        builder.setMessage(str);
        builder.setPositiveButton("是", onClickListener);
        builder.setNegativeButton("否", onClickListener2);
        builder.setCancelable(false);
        builder.show();
    }

    public static void showAlertDialog(Context context, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(str);
        builder.setPositiveButton("确认", (DialogInterface.OnClickListener) null);
        builder.show();
    }

    public static void showAlertDialog(Context context, String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton(context.getResources().getString(R.string.submit), (DialogInterface.OnClickListener) null);
        builder.show();
    }
}
