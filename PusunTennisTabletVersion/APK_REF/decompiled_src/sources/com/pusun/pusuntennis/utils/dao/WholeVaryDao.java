package com.pusun.pusuntennis.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class WholeVaryDao extends AbstractDao<WholeVary, Long> {
    public static final String TABLENAME = "WHOLE_VARY";

    public static class Properties {
        public static final Property Number = new Property(0, Long.class, "number", true, "_id");
        public static final Property DaoName = new Property(1, String.class, "daoName", false, "DAO_NAME");
        public static final Property Seles = new Property(2, String.class, "seles", false, "SELES");
        public static final Property Item1 = new Property(3, Integer.TYPE, "item1", false, "ITEM1");
        public static final Property Item2 = new Property(4, Integer.TYPE, "item2", false, "ITEM2");
        public static final Property Item3 = new Property(5, Integer.TYPE, "item3", false, "ITEM3");
        public static final Property Para1 = new Property(6, String.class, "para1", false, "PARA1");
        public static final Property Para2 = new Property(7, String.class, "para2", false, "PARA2");
        public static final Property Para3 = new Property(8, String.class, "para3", false, "PARA3");
        public static final Property Para4 = new Property(9, String.class, "para4", false, "PARA4");
        public static final Property Para5 = new Property(10, String.class, "para5", false, "PARA5");
        public static final Property Para6 = new Property(11, String.class, "para6", false, "PARA6");
        public static final Property Para7 = new Property(12, String.class, "para7", false, "PARA7");
        public static final Property Para8 = new Property(13, String.class, "para8", false, "PARA8");
        public static final Property Para9 = new Property(14, String.class, "para9", false, "PARA9");
        public static final Property Para10 = new Property(15, String.class, "para10", false, "PARA10");
        public static final Property Para11 = new Property(16, String.class, "para11", false, "PARA11");
        public static final Property Para12 = new Property(17, String.class, "para12", false, "PARA12");
        public static final Property Para13 = new Property(18, String.class, "para13", false, "PARA13");
        public static final Property Para14 = new Property(19, String.class, "para14", false, "PARA14");
        public static final Property Para15 = new Property(20, String.class, "para15", false, "PARA15");
        public static final Property Back1 = new Property(21, String.class, "back1", false, "BACK1");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public WholeVaryDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public WholeVaryDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"WHOLE_VARY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DAO_NAME\" TEXT,\"SELES\" TEXT,\"ITEM1\" INTEGER NOT NULL ,\"ITEM2\" INTEGER NOT NULL ,\"ITEM3\" INTEGER NOT NULL ,\"PARA1\" TEXT,\"PARA2\" TEXT,\"PARA3\" TEXT,\"PARA4\" TEXT,\"PARA5\" TEXT,\"PARA6\" TEXT,\"PARA7\" TEXT,\"PARA8\" TEXT,\"PARA9\" TEXT,\"PARA10\" TEXT,\"PARA11\" TEXT,\"PARA12\" TEXT,\"PARA13\" TEXT,\"PARA14\" TEXT,\"PARA15\" TEXT,\"BACK1\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"WHOLE_VARY\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, WholeVary wholeVary) {
        databaseStatement.clearBindings();
        Long number = wholeVary.getNumber();
        if (number != null) {
            databaseStatement.bindLong(1, number.longValue());
        }
        String daoName = wholeVary.getDaoName();
        if (daoName != null) {
            databaseStatement.bindString(2, daoName);
        }
        String seles = wholeVary.getSeles();
        if (seles != null) {
            databaseStatement.bindString(3, seles);
        }
        databaseStatement.bindLong(4, wholeVary.getItem1());
        databaseStatement.bindLong(5, wholeVary.getItem2());
        databaseStatement.bindLong(6, wholeVary.getItem3());
        String para1 = wholeVary.getPara1();
        if (para1 != null) {
            databaseStatement.bindString(7, para1);
        }
        String para2 = wholeVary.getPara2();
        if (para2 != null) {
            databaseStatement.bindString(8, para2);
        }
        String para3 = wholeVary.getPara3();
        if (para3 != null) {
            databaseStatement.bindString(9, para3);
        }
        String para4 = wholeVary.getPara4();
        if (para4 != null) {
            databaseStatement.bindString(10, para4);
        }
        String para5 = wholeVary.getPara5();
        if (para5 != null) {
            databaseStatement.bindString(11, para5);
        }
        String para6 = wholeVary.getPara6();
        if (para6 != null) {
            databaseStatement.bindString(12, para6);
        }
        String para7 = wholeVary.getPara7();
        if (para7 != null) {
            databaseStatement.bindString(13, para7);
        }
        String para8 = wholeVary.getPara8();
        if (para8 != null) {
            databaseStatement.bindString(14, para8);
        }
        String para9 = wholeVary.getPara9();
        if (para9 != null) {
            databaseStatement.bindString(15, para9);
        }
        String para10 = wholeVary.getPara10();
        if (para10 != null) {
            databaseStatement.bindString(16, para10);
        }
        String para11 = wholeVary.getPara11();
        if (para11 != null) {
            databaseStatement.bindString(17, para11);
        }
        String para12 = wholeVary.getPara12();
        if (para12 != null) {
            databaseStatement.bindString(18, para12);
        }
        String para13 = wholeVary.getPara13();
        if (para13 != null) {
            databaseStatement.bindString(19, para13);
        }
        String para14 = wholeVary.getPara14();
        if (para14 != null) {
            databaseStatement.bindString(20, para14);
        }
        String para15 = wholeVary.getPara15();
        if (para15 != null) {
            databaseStatement.bindString(21, para15);
        }
        String back1 = wholeVary.getBack1();
        if (back1 != null) {
            databaseStatement.bindString(22, back1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, WholeVary wholeVary) {
        sQLiteStatement.clearBindings();
        Long number = wholeVary.getNumber();
        if (number != null) {
            sQLiteStatement.bindLong(1, number.longValue());
        }
        String daoName = wholeVary.getDaoName();
        if (daoName != null) {
            sQLiteStatement.bindString(2, daoName);
        }
        String seles = wholeVary.getSeles();
        if (seles != null) {
            sQLiteStatement.bindString(3, seles);
        }
        sQLiteStatement.bindLong(4, wholeVary.getItem1());
        sQLiteStatement.bindLong(5, wholeVary.getItem2());
        sQLiteStatement.bindLong(6, wholeVary.getItem3());
        String para1 = wholeVary.getPara1();
        if (para1 != null) {
            sQLiteStatement.bindString(7, para1);
        }
        String para2 = wholeVary.getPara2();
        if (para2 != null) {
            sQLiteStatement.bindString(8, para2);
        }
        String para3 = wholeVary.getPara3();
        if (para3 != null) {
            sQLiteStatement.bindString(9, para3);
        }
        String para4 = wholeVary.getPara4();
        if (para4 != null) {
            sQLiteStatement.bindString(10, para4);
        }
        String para5 = wholeVary.getPara5();
        if (para5 != null) {
            sQLiteStatement.bindString(11, para5);
        }
        String para6 = wholeVary.getPara6();
        if (para6 != null) {
            sQLiteStatement.bindString(12, para6);
        }
        String para7 = wholeVary.getPara7();
        if (para7 != null) {
            sQLiteStatement.bindString(13, para7);
        }
        String para8 = wholeVary.getPara8();
        if (para8 != null) {
            sQLiteStatement.bindString(14, para8);
        }
        String para9 = wholeVary.getPara9();
        if (para9 != null) {
            sQLiteStatement.bindString(15, para9);
        }
        String para10 = wholeVary.getPara10();
        if (para10 != null) {
            sQLiteStatement.bindString(16, para10);
        }
        String para11 = wholeVary.getPara11();
        if (para11 != null) {
            sQLiteStatement.bindString(17, para11);
        }
        String para12 = wholeVary.getPara12();
        if (para12 != null) {
            sQLiteStatement.bindString(18, para12);
        }
        String para13 = wholeVary.getPara13();
        if (para13 != null) {
            sQLiteStatement.bindString(19, para13);
        }
        String para14 = wholeVary.getPara14();
        if (para14 != null) {
            sQLiteStatement.bindString(20, para14);
        }
        String para15 = wholeVary.getPara15();
        if (para15 != null) {
            sQLiteStatement.bindString(21, para15);
        }
        String back1 = wholeVary.getBack1();
        if (back1 != null) {
            sQLiteStatement.bindString(22, back1);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        if (cursor.isNull(i)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public WholeVary readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = i + 6;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 7;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 8;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 9;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 10;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 11;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 13;
        String string10 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 14;
        String string11 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 15;
        String string12 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 16;
        String string13 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 17;
        String string14 = cursor.isNull(i18) ? null : cursor.getString(i18);
        int i19 = i + 18;
        String string15 = cursor.isNull(i19) ? null : cursor.getString(i19);
        int i20 = i + 19;
        String string16 = cursor.isNull(i20) ? null : cursor.getString(i20);
        int i21 = i + 20;
        String string17 = cursor.isNull(i21) ? null : cursor.getString(i21);
        int i22 = i + 21;
        return new WholeVary(valueOf, string, string2, i4, i5, i6, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, cursor.isNull(i22) ? null : cursor.getString(i22));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, WholeVary wholeVary, int i) {
        wholeVary.setNumber(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        wholeVary.setDaoName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        wholeVary.setSeles(cursor.isNull(i3) ? null : cursor.getString(i3));
        wholeVary.setItem1(cursor.getInt(i + 3));
        wholeVary.setItem2(cursor.getInt(i + 4));
        wholeVary.setItem3(cursor.getInt(i + 5));
        int i4 = i + 6;
        wholeVary.setPara1(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 7;
        wholeVary.setPara2(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 8;
        wholeVary.setPara3(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 9;
        wholeVary.setPara4(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 10;
        wholeVary.setPara5(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 11;
        wholeVary.setPara6(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 12;
        wholeVary.setPara7(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 13;
        wholeVary.setPara8(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 14;
        wholeVary.setPara9(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 15;
        wholeVary.setPara10(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 16;
        wholeVary.setPara11(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 17;
        wholeVary.setPara12(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 18;
        wholeVary.setPara13(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 19;
        wholeVary.setPara14(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 20;
        wholeVary.setPara15(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 21;
        wholeVary.setBack1(cursor.isNull(i19) ? null : cursor.getString(i19));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(WholeVary wholeVary, long j) {
        wholeVary.setNumber(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(WholeVary wholeVary) {
        if (wholeVary != null) {
            return wholeVary.getNumber();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(WholeVary wholeVary) {
        return wholeVary.getNumber() != null;
    }
}
