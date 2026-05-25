package com.pusun.pusuntennis.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class VariDaoDao extends AbstractDao<VariDao, Long> {
    public static final String TABLENAME = "VARI_DAO";

    public static class Properties {
        public static final Property Number = new Property(0, Long.class, "number", true, "_id");
        public static final Property DaoName = new Property(1, String.class, "daoName", false, "DAO_NAME");
        public static final Property Seles = new Property(2, String.class, "seles", false, "SELES");
        public static final Property Item1 = new Property(3, Integer.TYPE, "item1", false, "ITEM1");
        public static final Property Item2 = new Property(4, Integer.TYPE, "item2", false, "ITEM2");
        public static final Property Item3 = new Property(5, Integer.TYPE, "item3", false, "ITEM3");
        public static final Property Freq1 = new Property(6, Integer.TYPE, "freq1", false, "FREQ1");
        public static final Property Velo1 = new Property(7, Integer.TYPE, "velo1", false, "VELO1");
        public static final Property Lr1 = new Property(8, Integer.TYPE, "lr1", false, "LR1");
        public static final Property Ud1 = new Property(9, Integer.TYPE, "ud1", false, "UD1");
        public static final Property Freq2 = new Property(10, Integer.TYPE, "freq2", false, "FREQ2");
        public static final Property Velo2 = new Property(11, Integer.TYPE, "velo2", false, "VELO2");
        public static final Property Lr2 = new Property(12, Integer.TYPE, "lr2", false, "LR2");
        public static final Property Ud2 = new Property(13, Integer.TYPE, "ud2", false, "UD2");
        public static final Property Freq3 = new Property(14, Integer.TYPE, "freq3", false, "FREQ3");
        public static final Property Velo3 = new Property(15, Integer.TYPE, "velo3", false, "VELO3");
        public static final Property Lr3 = new Property(16, Integer.TYPE, "lr3", false, "LR3");
        public static final Property Ud3 = new Property(17, Integer.TYPE, "ud3", false, "UD3");
        public static final Property Freq4 = new Property(18, Integer.TYPE, "freq4", false, "FREQ4");
        public static final Property Velo4 = new Property(19, Integer.TYPE, "velo4", false, "VELO4");
        public static final Property Lr4 = new Property(20, Integer.TYPE, "lr4", false, "LR4");
        public static final Property Ud4 = new Property(21, Integer.TYPE, "ud4", false, "UD4");
        public static final Property Freq5 = new Property(22, Integer.TYPE, "freq5", false, "FREQ5");
        public static final Property Velo5 = new Property(23, Integer.TYPE, "velo5", false, "VELO5");
        public static final Property Lr5 = new Property(24, Integer.TYPE, "lr5", false, "LR5");
        public static final Property Ud5 = new Property(25, Integer.TYPE, "ud5", false, "UD5");
        public static final Property Freq6 = new Property(26, Integer.TYPE, "freq6", false, "FREQ6");
        public static final Property Velo6 = new Property(27, Integer.TYPE, "velo6", false, "VELO6");
        public static final Property Lr6 = new Property(28, Integer.TYPE, "lr6", false, "LR6");
        public static final Property Ud6 = new Property(29, Integer.TYPE, "ud6", false, "UD6");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public VariDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public VariDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"VARI_DAO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DAO_NAME\" TEXT,\"SELES\" TEXT,\"ITEM1\" INTEGER NOT NULL ,\"ITEM2\" INTEGER NOT NULL ,\"ITEM3\" INTEGER NOT NULL ,\"FREQ1\" INTEGER NOT NULL ,\"VELO1\" INTEGER NOT NULL ,\"LR1\" INTEGER NOT NULL ,\"UD1\" INTEGER NOT NULL ,\"FREQ2\" INTEGER NOT NULL ,\"VELO2\" INTEGER NOT NULL ,\"LR2\" INTEGER NOT NULL ,\"UD2\" INTEGER NOT NULL ,\"FREQ3\" INTEGER NOT NULL ,\"VELO3\" INTEGER NOT NULL ,\"LR3\" INTEGER NOT NULL ,\"UD3\" INTEGER NOT NULL ,\"FREQ4\" INTEGER NOT NULL ,\"VELO4\" INTEGER NOT NULL ,\"LR4\" INTEGER NOT NULL ,\"UD4\" INTEGER NOT NULL ,\"FREQ5\" INTEGER NOT NULL ,\"VELO5\" INTEGER NOT NULL ,\"LR5\" INTEGER NOT NULL ,\"UD5\" INTEGER NOT NULL ,\"FREQ6\" INTEGER NOT NULL ,\"VELO6\" INTEGER NOT NULL ,\"LR6\" INTEGER NOT NULL ,\"UD6\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"VARI_DAO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, VariDao variDao) {
        databaseStatement.clearBindings();
        Long number = variDao.getNumber();
        if (number != null) {
            databaseStatement.bindLong(1, number.longValue());
        }
        String daoName = variDao.getDaoName();
        if (daoName != null) {
            databaseStatement.bindString(2, daoName);
        }
        String seles = variDao.getSeles();
        if (seles != null) {
            databaseStatement.bindString(3, seles);
        }
        databaseStatement.bindLong(4, variDao.getItem1());
        databaseStatement.bindLong(5, variDao.getItem2());
        databaseStatement.bindLong(6, variDao.getItem3());
        databaseStatement.bindLong(7, variDao.getFreq1());
        databaseStatement.bindLong(8, variDao.getVelo1());
        databaseStatement.bindLong(9, variDao.getLr1());
        databaseStatement.bindLong(10, variDao.getUd1());
        databaseStatement.bindLong(11, variDao.getFreq2());
        databaseStatement.bindLong(12, variDao.getVelo2());
        databaseStatement.bindLong(13, variDao.getLr2());
        databaseStatement.bindLong(14, variDao.getUd2());
        databaseStatement.bindLong(15, variDao.getFreq3());
        databaseStatement.bindLong(16, variDao.getVelo3());
        databaseStatement.bindLong(17, variDao.getLr3());
        databaseStatement.bindLong(18, variDao.getUd3());
        databaseStatement.bindLong(19, variDao.getFreq4());
        databaseStatement.bindLong(20, variDao.getVelo4());
        databaseStatement.bindLong(21, variDao.getLr4());
        databaseStatement.bindLong(22, variDao.getUd4());
        databaseStatement.bindLong(23, variDao.getFreq5());
        databaseStatement.bindLong(24, variDao.getVelo5());
        databaseStatement.bindLong(25, variDao.getLr5());
        databaseStatement.bindLong(26, variDao.getUd5());
        databaseStatement.bindLong(27, variDao.getFreq6());
        databaseStatement.bindLong(28, variDao.getVelo6());
        databaseStatement.bindLong(29, variDao.getLr6());
        databaseStatement.bindLong(30, variDao.getUd6());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, VariDao variDao) {
        sQLiteStatement.clearBindings();
        Long number = variDao.getNumber();
        if (number != null) {
            sQLiteStatement.bindLong(1, number.longValue());
        }
        String daoName = variDao.getDaoName();
        if (daoName != null) {
            sQLiteStatement.bindString(2, daoName);
        }
        String seles = variDao.getSeles();
        if (seles != null) {
            sQLiteStatement.bindString(3, seles);
        }
        sQLiteStatement.bindLong(4, variDao.getItem1());
        sQLiteStatement.bindLong(5, variDao.getItem2());
        sQLiteStatement.bindLong(6, variDao.getItem3());
        sQLiteStatement.bindLong(7, variDao.getFreq1());
        sQLiteStatement.bindLong(8, variDao.getVelo1());
        sQLiteStatement.bindLong(9, variDao.getLr1());
        sQLiteStatement.bindLong(10, variDao.getUd1());
        sQLiteStatement.bindLong(11, variDao.getFreq2());
        sQLiteStatement.bindLong(12, variDao.getVelo2());
        sQLiteStatement.bindLong(13, variDao.getLr2());
        sQLiteStatement.bindLong(14, variDao.getUd2());
        sQLiteStatement.bindLong(15, variDao.getFreq3());
        sQLiteStatement.bindLong(16, variDao.getVelo3());
        sQLiteStatement.bindLong(17, variDao.getLr3());
        sQLiteStatement.bindLong(18, variDao.getUd3());
        sQLiteStatement.bindLong(19, variDao.getFreq4());
        sQLiteStatement.bindLong(20, variDao.getVelo4());
        sQLiteStatement.bindLong(21, variDao.getLr4());
        sQLiteStatement.bindLong(22, variDao.getUd4());
        sQLiteStatement.bindLong(23, variDao.getFreq5());
        sQLiteStatement.bindLong(24, variDao.getVelo5());
        sQLiteStatement.bindLong(25, variDao.getLr5());
        sQLiteStatement.bindLong(26, variDao.getUd5());
        sQLiteStatement.bindLong(27, variDao.getFreq6());
        sQLiteStatement.bindLong(28, variDao.getVelo6());
        sQLiteStatement.bindLong(29, variDao.getLr6());
        sQLiteStatement.bindLong(30, variDao.getUd6());
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
    public VariDao readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        return new VariDao(valueOf, string, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getInt(i + 17), cursor.getInt(i + 18), cursor.getInt(i + 19), cursor.getInt(i + 20), cursor.getInt(i + 21), cursor.getInt(i + 22), cursor.getInt(i + 23), cursor.getInt(i + 24), cursor.getInt(i + 25), cursor.getInt(i + 26), cursor.getInt(i + 27), cursor.getInt(i + 28), cursor.getInt(i + 29));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, VariDao variDao, int i) {
        variDao.setNumber(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        variDao.setDaoName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        variDao.setSeles(cursor.isNull(i3) ? null : cursor.getString(i3));
        variDao.setItem1(cursor.getInt(i + 3));
        variDao.setItem2(cursor.getInt(i + 4));
        variDao.setItem3(cursor.getInt(i + 5));
        variDao.setFreq1(cursor.getInt(i + 6));
        variDao.setVelo1(cursor.getInt(i + 7));
        variDao.setLr1(cursor.getInt(i + 8));
        variDao.setUd1(cursor.getInt(i + 9));
        variDao.setFreq2(cursor.getInt(i + 10));
        variDao.setVelo2(cursor.getInt(i + 11));
        variDao.setLr2(cursor.getInt(i + 12));
        variDao.setUd2(cursor.getInt(i + 13));
        variDao.setFreq3(cursor.getInt(i + 14));
        variDao.setVelo3(cursor.getInt(i + 15));
        variDao.setLr3(cursor.getInt(i + 16));
        variDao.setUd3(cursor.getInt(i + 17));
        variDao.setFreq4(cursor.getInt(i + 18));
        variDao.setVelo4(cursor.getInt(i + 19));
        variDao.setLr4(cursor.getInt(i + 20));
        variDao.setUd4(cursor.getInt(i + 21));
        variDao.setFreq5(cursor.getInt(i + 22));
        variDao.setVelo5(cursor.getInt(i + 23));
        variDao.setLr5(cursor.getInt(i + 24));
        variDao.setUd5(cursor.getInt(i + 25));
        variDao.setFreq6(cursor.getInt(i + 26));
        variDao.setVelo6(cursor.getInt(i + 27));
        variDao.setLr6(cursor.getInt(i + 28));
        variDao.setUd6(cursor.getInt(i + 29));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(VariDao variDao, long j) {
        variDao.setNumber(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(VariDao variDao) {
        if (variDao != null) {
            return variDao.getNumber();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(VariDao variDao) {
        return variDao.getNumber() != null;
    }
}
