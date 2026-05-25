package cn.qqtheme.framework.util;

import android.R;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class ConvertUtils {
    public static final long GB = 1073741824;
    public static final long KB = 1024;
    public static final long MB = 1048576;

    public static int toShort(byte b, byte b2) {
        return (b << 8) + (b2 & 255);
    }

    public static int toInt(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception unused) {
            return -1;
        }
    }

    public static int toInt(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += (bArr[i2] & 255) << (i2 * 8);
        }
        return i;
    }

    public static long toLong(Object obj) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception unused) {
            return -1L;
        }
    }

    public static float toFloat(Object obj) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception unused) {
            return -1.0f;
        }
    }

    public static byte[] toByteArray(int i) {
        return ByteBuffer.allocate(4).putInt(i).array();
    }

    public static byte[] toByteArray(String str, boolean z) {
        if (str == null || str.equals("")) {
            return null;
        }
        if (z) {
            String replaceAll = str.replaceAll("\\s+", "");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(replaceAll.length() / 2);
            for (int i = 0; i < replaceAll.length(); i += 2) {
                byteArrayOutputStream.write(("0123456789ABCDEF".indexOf(replaceAll.charAt(i)) << 4) | "0123456789ABCDEF".indexOf(replaceAll.charAt(i + 1)));
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return byteArray;
        }
        return str.getBytes();
    }

    public static String toHexString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : str.getBytes()) {
            int i = b & 255;
            Integer.valueOf(i).getClass();
            sb.append(Integer.toHexString(i));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String toHexString(byte... bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (int i2 : bArr) {
            if (i2 < 0) {
                i2 += 256;
            }
            int i3 = i + 1;
            cArr2[i] = cArr[i2 >>> 4];
            i += 2;
            cArr2[i3] = cArr[i2 & 15];
        }
        return new String(cArr2);
    }

    public static String toHexString(int i) {
        String hexString = Integer.toHexString(i);
        LogUtils.debug(String.format("%d to hex string is %s", Integer.valueOf(i), hexString));
        return hexString;
    }

    public static String toBinaryString(byte... bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 8];
        int i = 0;
        for (int i2 : bArr) {
            if (i2 < 0) {
                i2 += 256;
            }
            cArr2[i] = cArr[(i2 >>> 7) & 1];
            cArr2[i + 1] = cArr[(i2 >>> 6) & 1];
            cArr2[i + 2] = cArr[(i2 >>> 5) & 1];
            cArr2[i + 3] = cArr[(i2 >>> 4) & 1];
            cArr2[i + 4] = cArr[(i2 >>> 3) & 1];
            cArr2[i + 5] = cArr[(i2 >>> 2) & 1];
            int i3 = i + 7;
            cArr2[i + 6] = cArr[(i2 >>> 1) & 1];
            i += 8;
            cArr2[i3] = cArr[i2 & 1];
        }
        return new String(cArr2);
    }

    public static String toBinaryString(int i) {
        String binaryString = Integer.toBinaryString(i);
        LogUtils.debug(String.format("%d to binary string is %s", Integer.valueOf(i), binaryString));
        return binaryString;
    }

    public static String toColorString(int i) {
        return toColorString(i, false);
    }

    public static String toColorString(int i, boolean z) {
        String hexString = Integer.toHexString(Color.alpha(i));
        String hexString2 = Integer.toHexString(Color.red(i));
        String hexString3 = Integer.toHexString(Color.green(i));
        String hexString4 = Integer.toHexString(Color.blue(i));
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        if (hexString3.length() == 1) {
            hexString3 = "0" + hexString3;
        }
        if (hexString4.length() == 1) {
            hexString4 = "0" + hexString4;
        }
        if (z) {
            String str = hexString + hexString2 + hexString3 + hexString4;
            LogUtils.debug(String.format("%d to color string is %s", Integer.valueOf(i), str));
            return str;
        }
        String str2 = hexString2 + hexString3 + hexString4;
        LogUtils.debug(String.format("%d to color string is %s%s%s%s, exclude alpha is %s", Integer.valueOf(i), hexString, hexString2, hexString3, hexString4, str2));
        return str2;
    }

    public static String toDateString(Date date, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }

    public static String toDateString(String str) {
        return toDateString(Calendar.getInstance(Locale.CHINA).getTime(), str);
    }

    public static Date toDate(String str) {
        return DateUtils.parseDate(str);
    }

    public static long toTimemillis(String str) {
        return toDate(str).getTime();
    }

    public static String toSlashString(String str) {
        String str2 = "";
        for (char c : str.toCharArray()) {
            if (c == '\"' || c == '\'' || c == '\\') {
                str2 = str2 + "\\";
            }
            str2 = str2 + c;
        }
        return str2;
    }

    public static <T> T[] toArray(List<T> list) {
        return (T[]) list.toArray();
    }

    public static <T> List<T> toList(T[] tArr) {
        return Arrays.asList(tArr);
    }

    public static String toString(Object[] objArr) {
        return Arrays.deepToString(objArr);
    }

    public static String toString(Object[] objArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj);
            sb.append(str);
        }
        return sb.toString();
    }

    public static byte[] toByteArray(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[100];
            while (true) {
                int read = inputStream.read(bArr, 0, 100);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toByteArray(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static Bitmap toBitmap(byte[] bArr, int i, int i2) {
        if (bArr.length == 0) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inPreferredConfig = null;
            if (i > 0 && i2 > 0) {
                options.outWidth = i;
                options.outHeight = i2;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        } catch (Exception e) {
            LogUtils.error(e);
            return null;
        }
    }

    public static Bitmap toBitmap(byte[] bArr) {
        return toBitmap(bArr, -1, -1);
    }

    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof ColorDrawable) {
            Bitmap createBitmap = Bitmap.createBitmap(32, 32, Bitmap.Config.ARGB_8888);
            new Canvas(createBitmap).drawColor(((ColorDrawable) drawable).getColor());
            return createBitmap;
        }
        if (!(drawable instanceof NinePatchDrawable)) {
            return null;
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return createBitmap2;
    }

    public static String toPath(Context context, Uri uri) {
        LogUtils.debug("uri: " + uri.toString());
        String path = uri.getPath();
        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (authority.equals("com.android.externalstorage.documents")) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else {
                if (authority.equals("com.android.providers.downloads.documents")) {
                    return _queryPathFromMediaStore(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (authority.equals("com.android.providers.media.documents")) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return _queryPathFromMediaStore(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else {
            if ("content".equalsIgnoreCase(scheme)) {
                if (authority.equals("com.google.android.apps.photos.content")) {
                    return uri.getLastPathSegment();
                }
                return _queryPathFromMediaStore(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(scheme)) {
                return uri.getPath();
            }
        }
        LogUtils.debug("uri to path: " + path);
        return path;
    }

    private static String _queryPathFromMediaStore(Context context, Uri uri, String str, String[] strArr) {
        String str2 = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
            query.moveToFirst();
            str2 = query.getString(columnIndexOrThrow);
            query.close();
            return str2;
        } catch (Exception e) {
            LogUtils.error(e);
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap toBitmap(android.view.View r7) {
        /*
            int r0 = r7.getWidth()
            int r1 = r7.getHeight()
            boolean r2 = r7 instanceof android.widget.ListView
            r3 = 0
            if (r2 == 0) goto L24
            r1 = r7
            android.widget.ListView r1 = (android.widget.ListView) r1
            r2 = 0
            r4 = 0
        L12:
            int r5 = r1.getChildCount()
            if (r4 >= r5) goto L3f
            android.view.View r5 = r1.getChildAt(r4)
            int r5 = r5.getHeight()
            int r2 = r2 + r5
            int r4 = r4 + 1
            goto L12
        L24:
            boolean r2 = r7 instanceof android.widget.ScrollView
            if (r2 == 0) goto L40
            r1 = r7
            android.widget.ScrollView r1 = (android.widget.ScrollView) r1
            r2 = 0
            r4 = 0
        L2d:
            int r5 = r1.getChildCount()
            if (r4 >= r5) goto L3f
            android.view.View r5 = r1.getChildAt(r4)
            int r5 = r5.getHeight()
            int r2 = r2 + r5
            int r4 = r4 + 1
            goto L2d
        L3f:
            r1 = r2
        L40:
            r2 = 1
            r7.setDrawingCacheEnabled(r2)
            r7.clearFocus()
            r7.setPressed(r3)
            boolean r2 = r7.willNotCacheDrawing()
            r7.setWillNotCacheDrawing(r3)
            int r3 = r7.getDrawingCacheBackgroundColor()
            r4 = -1
            r7.setDrawingCacheBackgroundColor(r4)
            if (r3 == r4) goto L5e
            r7.destroyDrawingCache()
        L5e:
            r7.buildDrawingCache()
            android.graphics.Bitmap r4 = r7.getDrawingCache()
            r5 = 0
            if (r4 != 0) goto L69
            return r5
        L69:
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r1, r6)
            android.graphics.Canvas r1 = new android.graphics.Canvas
            r1.<init>(r0)
            r6 = 0
            r1.drawBitmap(r4, r6, r6, r5)
            r5 = 31
            r1.save(r5)
            r1.restore()
            r4.recycle()
            r7.destroyDrawingCache()
            r7.setWillNotCacheDrawing(r2)
            r7.setDrawingCacheBackgroundColor(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.qqtheme.framework.util.ConvertUtils.toBitmap(android.view.View):android.graphics.Bitmap");
    }

    public static Drawable toDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable((Resources) null, bitmap);
    }

    public static byte[] toByteArray(Drawable drawable) {
        return toByteArray(toBitmap(drawable));
    }

    public static Drawable toDrawable(byte[] bArr) {
        return toDrawable(toBitmap(bArr));
    }

    public static int toPx(Context context, float f) {
        int i = (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
        LogUtils.debug(f + " dp == " + i + " px");
        return i;
    }

    public static int toPx(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int toDp(Context context, float f) {
        int i = (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
        LogUtils.debug(f + " px == " + i + " dp");
        return i;
    }

    public static int toSp(Context context, float f) {
        int i = (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
        LogUtils.debug(f + " px == " + i + " sp");
        return i;
    }

    public static String toGbk(String str) {
        try {
            return new String(str.getBytes("utf-8"), "gbk");
        } catch (UnsupportedEncodingException e) {
            LogUtils.warn(e.getMessage());
            return str;
        }
    }

    public static String toFileSizeString(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (j < 1024) {
            return j + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(j / 1024.0d) + "K";
        }
        if (j < GB) {
            return decimalFormat.format(j / 1048576.0d) + "M";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "G";
    }

    public static String toString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            inputStream.close();
        } catch (IOException e) {
            LogUtils.error(e);
        }
        return sb.toString();
    }

    public static ShapeDrawable toRoundDrawable(int i, int i2) {
        float px = toPx(i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{px, px, px, px, px, px, px, px}, null, null));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }

    public static ColorStateList toColorStateList(int i, int i2, int i3, int i4) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed, R.attr.state_enabled}, new int[]{R.attr.state_enabled, R.attr.state_focused}, new int[]{R.attr.state_enabled}, new int[]{R.attr.state_focused}, new int[]{R.attr.state_window_focused}, new int[0]}, new int[]{i2, i3, i, i3, i4, i});
    }

    public static ColorStateList toColorStateList(int i, int i2) {
        return toColorStateList(i, i2, i2, i);
    }

    public static StateListDrawable toStateListDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, drawable2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, drawable);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, drawable4);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static StateListDrawable toStateListDrawable(int i, int i2, int i3, int i4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        ColorDrawable colorDrawable = new ColorDrawable(i);
        ColorDrawable colorDrawable2 = new ColorDrawable(i2);
        ColorDrawable colorDrawable3 = new ColorDrawable(i3);
        ColorDrawable colorDrawable4 = new ColorDrawable(i4);
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, colorDrawable2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, colorDrawable3);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, colorDrawable);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, colorDrawable3);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, colorDrawable4);
        stateListDrawable.addState(new int[0], colorDrawable);
        return stateListDrawable;
    }

    public static StateListDrawable toStateListDrawable(Drawable drawable, Drawable drawable2) {
        return toStateListDrawable(drawable, drawable2, drawable2, drawable);
    }

    public static StateListDrawable toStateListDrawable(int i, int i2) {
        return toStateListDrawable(i, i2, i2, i);
    }
}
