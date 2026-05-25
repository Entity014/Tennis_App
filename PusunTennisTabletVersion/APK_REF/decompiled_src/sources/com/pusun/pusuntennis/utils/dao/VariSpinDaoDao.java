package com.pusun.pusuntennis.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class VariSpinDaoDao extends AbstractDao<VariSpinDao, Long> {
    public static final String TABLENAME = "VARI_SPIN_DAO";

    public static class Properties {
        public static final Property Number = new Property(0, Long.class, "number", true, "_id");
        public static final Property DaoName = new Property(1, String.class, "daoName", false, "DAO_NAME");
        public static final Property Seles = new Property(2, String.class, "seles", false, "SELES");
        public static final Property Personname = new Property(3, String.class, "personname", false, "PERSONNAME");
        public static final Property Item1 = new Property(4, Integer.TYPE, "item1", false, "ITEM1");
        public static final Property Item2 = new Property(5, Integer.TYPE, "item2", false, "ITEM2");
        public static final Property Item3 = new Property(6, Integer.TYPE, "item3", false, "ITEM3");
        public static final Property Freq1 = new Property(7, Integer.TYPE, "freq1", false, "FREQ1");
        public static final Property Velo1 = new Property(8, Integer.TYPE, "velo1", false, "VELO1");
        public static final Property Lr1 = new Property(9, Integer.TYPE, "lr1", false, "LR1");
        public static final Property Ud1 = new Property(10, Integer.TYPE, "ud1", false, "UD1");
        public static final Property Spin1 = new Property(11, Integer.TYPE, "spin1", false, "SPIN1");
        public static final Property Freq2 = new Property(12, Integer.TYPE, "freq2", false, "FREQ2");
        public static final Property Velo2 = new Property(13, Integer.TYPE, "velo2", false, "VELO2");
        public static final Property Lr2 = new Property(14, Integer.TYPE, "lr2", false, "LR2");
        public static final Property Ud2 = new Property(15, Integer.TYPE, "ud2", false, "UD2");
        public static final Property Spin2 = new Property(16, Integer.TYPE, "spin2", false, "SPIN2");
        public static final Property Freq3 = new Property(17, Integer.TYPE, "freq3", false, "FREQ3");
        public static final Property Velo3 = new Property(18, Integer.TYPE, "velo3", false, "VELO3");
        public static final Property Lr3 = new Property(19, Integer.TYPE, "lr3", false, "LR3");
        public static final Property Ud3 = new Property(20, Integer.TYPE, "ud3", false, "UD3");
        public static final Property Spin3 = new Property(21, Integer.TYPE, "spin3", false, "SPIN3");
        public static final Property Freq4 = new Property(22, Integer.TYPE, "freq4", false, "FREQ4");
        public static final Property Velo4 = new Property(23, Integer.TYPE, "velo4", false, "VELO4");
        public static final Property Lr4 = new Property(24, Integer.TYPE, "lr4", false, "LR4");
        public static final Property Ud4 = new Property(25, Integer.TYPE, "ud4", false, "UD4");
        public static final Property Spin4 = new Property(26, Integer.TYPE, "spin4", false, "SPIN4");
        public static final Property Freq5 = new Property(27, Integer.TYPE, "freq5", false, "FREQ5");
        public static final Property Velo5 = new Property(28, Integer.TYPE, "velo5", false, "VELO5");
        public static final Property Lr5 = new Property(29, Integer.TYPE, "lr5", false, "LR5");
        public static final Property Ud5 = new Property(30, Integer.TYPE, "ud5", false, "UD5");
        public static final Property Spin5 = new Property(31, Integer.TYPE, "spin5", false, "SPIN5");
        public static final Property Freq6 = new Property(32, Integer.TYPE, "freq6", false, "FREQ6");
        public static final Property Velo6 = new Property(33, Integer.TYPE, "velo6", false, "VELO6");
        public static final Property Lr6 = new Property(34, Integer.TYPE, "lr6", false, "LR6");
        public static final Property Ud6 = new Property(35, Integer.TYPE, "ud6", false, "UD6");
        public static final Property Spin6 = new Property(36, Integer.TYPE, "spin6", false, "SPIN6");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public VariSpinDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public VariSpinDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"VARI_SPIN_DAO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DAO_NAME\" TEXT,\"SELES\" TEXT,\"PERSONNAME\" TEXT,\"ITEM1\" INTEGER NOT NULL ,\"ITEM2\" INTEGER NOT NULL ,\"ITEM3\" INTEGER NOT NULL ,\"FREQ1\" INTEGER NOT NULL ,\"VELO1\" INTEGER NOT NULL ,\"LR1\" INTEGER NOT NULL ,\"UD1\" INTEGER NOT NULL ,\"SPIN1\" INTEGER NOT NULL ,\"FREQ2\" INTEGER NOT NULL ,\"VELO2\" INTEGER NOT NULL ,\"LR2\" INTEGER NOT NULL ,\"UD2\" INTEGER NOT NULL ,\"SPIN2\" INTEGER NOT NULL ,\"FREQ3\" INTEGER NOT NULL ,\"VELO3\" INTEGER NOT NULL ,\"LR3\" INTEGER NOT NULL ,\"UD3\" INTEGER NOT NULL ,\"SPIN3\" INTEGER NOT NULL ,\"FREQ4\" INTEGER NOT NULL ,\"VELO4\" INTEGER NOT NULL ,\"LR4\" INTEGER NOT NULL ,\"UD4\" INTEGER NOT NULL ,\"SPIN4\" INTEGER NOT NULL ,\"FREQ5\" INTEGER NOT NULL ,\"VELO5\" INTEGER NOT NULL ,\"LR5\" INTEGER NOT NULL ,\"UD5\" INTEGER NOT NULL ,\"SPIN5\" INTEGER NOT NULL ,\"FREQ6\" INTEGER NOT NULL ,\"VELO6\" INTEGER NOT NULL ,\"LR6\" INTEGER NOT NULL ,\"UD6\" INTEGER NOT NULL ,\"SPIN6\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"VARI_SPIN_DAO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, VariSpinDao variSpinDao) {
        databaseStatement.clearBindings();
        Long number = variSpinDao.getNumber();
        if (number != null) {
            databaseStatement.bindLong(1, number.longValue());
        }
        String daoName = variSpinDao.getDaoName();
        if (daoName != null) {
            databaseStatement.bindString(2, daoName);
        }
        String seles = variSpinDao.getSeles();
        if (seles != null) {
            databaseStatement.bindString(3, seles);
        }
        String personname = variSpinDao.getPersonname();
        if (personname != null) {
            databaseStatement.bindString(4, personname);
        }
        databaseStatement.bindLong(5, variSpinDao.getItem1());
        databaseStatement.bindLong(6, variSpinDao.getItem2());
        databaseStatement.bindLong(7, variSpinDao.getItem3());
        databaseStatement.bindLong(8, variSpinDao.getFreq1());
        databaseStatement.bindLong(9, variSpinDao.getVelo1());
        databaseStatement.bindLong(10, variSpinDao.getLr1());
        databaseStatement.bindLong(11, variSpinDao.getUd1());
        databaseStatement.bindLong(12, variSpinDao.getSpin1());
        databaseStatement.bindLong(13, variSpinDao.getFreq2());
        databaseStatement.bindLong(14, variSpinDao.getVelo2());
        databaseStatement.bindLong(15, variSpinDao.getLr2());
        databaseStatement.bindLong(16, variSpinDao.getUd2());
        databaseStatement.bindLong(17, variSpinDao.getSpin2());
        databaseStatement.bindLong(18, variSpinDao.getFreq3());
        databaseStatement.bindLong(19, variSpinDao.getVelo3());
        databaseStatement.bindLong(20, variSpinDao.getLr3());
        databaseStatement.bindLong(21, variSpinDao.getUd3());
        databaseStatement.bindLong(22, variSpinDao.getSpin3());
        databaseStatement.bindLong(23, variSpinDao.getFreq4());
        databaseStatement.bindLong(24, variSpinDao.getVelo4());
        databaseStatement.bindLong(25, variSpinDao.getLr4());
        databaseStatement.bindLong(26, variSpinDao.getUd4());
        databaseStatement.bindLong(27, variSpinDao.getSpin4());
        databaseStatement.bindLong(28, variSpinDao.getFreq5());
        databaseStatement.bindLong(29, variSpinDao.getVelo5());
        databaseStatement.bindLong(30, variSpinDao.getLr5());
        databaseStatement.bindLong(31, variSpinDao.getUd5());
        databaseStatement.bindLong(32, variSpinDao.getSpin5());
        databaseStatement.bindLong(33, variSpinDao.getFreq6());
        databaseStatement.bindLong(34, variSpinDao.getVelo6());
        databaseStatement.bindLong(35, variSpinDao.getLr6());
        databaseStatement.bindLong(36, variSpinDao.getUd6());
        databaseStatement.bindLong(37, variSpinDao.getSpin6());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, VariSpinDao variSpinDao) {
        sQLiteStatement.clearBindings();
        Long number = variSpinDao.getNumber();
        if (number != null) {
            sQLiteStatement.bindLong(1, number.longValue());
        }
        String daoName = variSpinDao.getDaoName();
        if (daoName != null) {
            sQLiteStatement.bindString(2, daoName);
        }
        String seles = variSpinDao.getSeles();
        if (seles != null) {
            sQLiteStatement.bindString(3, seles);
        }
        String personname = variSpinDao.getPersonname();
        if (personname != null) {
            sQLiteStatement.bindString(4, personname);
        }
        sQLiteStatement.bindLong(5, variSpinDao.getItem1());
        sQLiteStatement.bindLong(6, variSpinDao.getItem2());
        sQLiteStatement.bindLong(7, variSpinDao.getItem3());
        sQLiteStatement.bindLong(8, variSpinDao.getFreq1());
        sQLiteStatement.bindLong(9, variSpinDao.getVelo1());
        sQLiteStatement.bindLong(10, variSpinDao.getLr1());
        sQLiteStatement.bindLong(11, variSpinDao.getUd1());
        sQLiteStatement.bindLong(12, variSpinDao.getSpin1());
        sQLiteStatement.bindLong(13, variSpinDao.getFreq2());
        sQLiteStatement.bindLong(14, variSpinDao.getVelo2());
        sQLiteStatement.bindLong(15, variSpinDao.getLr2());
        sQLiteStatement.bindLong(16, variSpinDao.getUd2());
        sQLiteStatement.bindLong(17, variSpinDao.getSpin2());
        sQLiteStatement.bindLong(18, variSpinDao.getFreq3());
        sQLiteStatement.bindLong(19, variSpinDao.getVelo3());
        sQLiteStatement.bindLong(20, variSpinDao.getLr3());
        sQLiteStatement.bindLong(21, variSpinDao.getUd3());
        sQLiteStatement.bindLong(22, variSpinDao.getSpin3());
        sQLiteStatement.bindLong(23, variSpinDao.getFreq4());
        sQLiteStatement.bindLong(24, variSpinDao.getVelo4());
        sQLiteStatement.bindLong(25, variSpinDao.getLr4());
        sQLiteStatement.bindLong(26, variSpinDao.getUd4());
        sQLiteStatement.bindLong(27, variSpinDao.getSpin4());
        sQLiteStatement.bindLong(28, variSpinDao.getFreq5());
        sQLiteStatement.bindLong(29, variSpinDao.getVelo5());
        sQLiteStatement.bindLong(30, variSpinDao.getLr5());
        sQLiteStatement.bindLong(31, variSpinDao.getUd5());
        sQLiteStatement.bindLong(32, variSpinDao.getSpin5());
        sQLiteStatement.bindLong(33, variSpinDao.getFreq6());
        sQLiteStatement.bindLong(34, variSpinDao.getVelo6());
        sQLiteStatement.bindLong(35, variSpinDao.getLr6());
        sQLiteStatement.bindLong(36, variSpinDao.getUd6());
        sQLiteStatement.bindLong(37, variSpinDao.getSpin6());
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
    public VariSpinDao readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 3;
        return new VariSpinDao(valueOf, string, string2, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getInt(i + 17), cursor.getInt(i + 18), cursor.getInt(i + 19), cursor.getInt(i + 20), cursor.getInt(i + 21), cursor.getInt(i + 22), cursor.getInt(i + 23), cursor.getInt(i + 24), cursor.getInt(i + 25), cursor.getInt(i + 26), cursor.getInt(i + 27), cursor.getInt(i + 28), cursor.getInt(i + 29), cursor.getInt(i + 30), cursor.getInt(i + 31), cursor.getInt(i + 32), cursor.getInt(i + 33), cursor.getInt(i + 34), cursor.getInt(i + 35), cursor.getInt(i + 36));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, VariSpinDao variSpinDao, int i) {
        variSpinDao.setNumber(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        variSpinDao.setDaoName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        variSpinDao.setSeles(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        variSpinDao.setPersonname(cursor.isNull(i4) ? null : cursor.getString(i4));
        variSpinDao.setItem1(cursor.getInt(i + 4));
        variSpinDao.setItem2(cursor.getInt(i + 5));
        variSpinDao.setItem3(cursor.getInt(i + 6));
        variSpinDao.setFreq1(cursor.getInt(i + 7));
        variSpinDao.setVelo1(cursor.getInt(i + 8));
        variSpinDao.setLr1(cursor.getInt(i + 9));
        variSpinDao.setUd1(cursor.getInt(i + 10));
        variSpinDao.setSpin1(cursor.getInt(i + 11));
        variSpinDao.setFreq2(cursor.getInt(i + 12));
        variSpinDao.setVelo2(cursor.getInt(i + 13));
        variSpinDao.setLr2(cursor.getInt(i + 14));
        variSpinDao.setUd2(cursor.getInt(i + 15));
        variSpinDao.setSpin2(cursor.getInt(i + 16));
        variSpinDao.setFreq3(cursor.getInt(i + 17));
        variSpinDao.setVelo3(cursor.getInt(i + 18));
        variSpinDao.setLr3(cursor.getInt(i + 19));
        variSpinDao.setUd3(cursor.getInt(i + 20));
        variSpinDao.setSpin3(cursor.getInt(i + 21));
        variSpinDao.setFreq4(cursor.getInt(i + 22));
        variSpinDao.setVelo4(cursor.getInt(i + 23));
        variSpinDao.setLr4(cursor.getInt(i + 24));
        variSpinDao.setUd4(cursor.getInt(i + 25));
        variSpinDao.setSpin4(cursor.getInt(i + 26));
        variSpinDao.setFreq5(cursor.getInt(i + 27));
        variSpinDao.setVelo5(cursor.getInt(i + 28));
        variSpinDao.setLr5(cursor.getInt(i + 29));
        variSpinDao.setUd5(cursor.getInt(i + 30));
        variSpinDao.setSpin5(cursor.getInt(i + 31));
        variSpinDao.setFreq6(cursor.getInt(i + 32));
        variSpinDao.setVelo6(cursor.getInt(i + 33));
        variSpinDao.setLr6(cursor.getInt(i + 34));
        variSpinDao.setUd6(cursor.getInt(i + 35));
        variSpinDao.setSpin6(cursor.getInt(i + 36));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(VariSpinDao variSpinDao, long j) {
        variSpinDao.setNumber(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(VariSpinDao variSpinDao) {
        if (variSpinDao != null) {
            return variSpinDao.getNumber();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(VariSpinDao variSpinDao) {
        return variSpinDao.getNumber() != null;
    }
}
