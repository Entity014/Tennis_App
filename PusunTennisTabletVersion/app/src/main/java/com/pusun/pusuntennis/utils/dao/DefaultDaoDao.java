package com.pusun.pusuntennis.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class DefaultDaoDao extends AbstractDao<DefaultDao, Long> {
    public static final String TABLENAME = "DEFAULT_DAO";

    public static class Properties {
        public static final Property Number = new Property(0, Long.class, "number", true, "_id");
        public static final Property DaoName = new Property(1, String.class, "daoName", false, "DAO_NAME");
        public static final Property Seles = new Property(2, String.class, "seles", false, "SELES");
        public static final Property Grade = new Property(3, Integer.TYPE, "grade", false, "GRADE");
        public static final Property Item2 = new Property(4, Integer.TYPE, "item2", false, "ITEM2");
        public static final Property Item3 = new Property(5, Integer.TYPE, "item3", false, "ITEM3");
        public static final Property Item4 = new Property(6, Integer.TYPE, "item4", false, "ITEM4");
        public static final Property Item5 = new Property(7, Integer.TYPE, "item5", false, "ITEM5");
        public static final Property Item6 = new Property(8, Integer.TYPE, "item6", false, "ITEM6");
        public static final Property Freq = new Property(9, Integer.TYPE, "freq", false, "FREQ");
        public static final Property Velo = new Property(10, Integer.TYPE, "velo", false, "VELO");
        public static final Property Rote = new Property(11, Integer.TYPE, "rote", false, "ROTE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public DefaultDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DefaultDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"DEFAULT_DAO\" (\"_id\" INTEGER PRIMARY KEY UNIQUE ,\"DAO_NAME\" TEXT,\"SELES\" TEXT,\"GRADE\" INTEGER NOT NULL ,\"ITEM2\" INTEGER NOT NULL ,\"ITEM3\" INTEGER NOT NULL ,\"ITEM4\" INTEGER NOT NULL ,\"ITEM5\" INTEGER NOT NULL ,\"ITEM6\" INTEGER NOT NULL ,\"FREQ\" INTEGER NOT NULL ,\"VELO\" INTEGER NOT NULL ,\"ROTE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DEFAULT_DAO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DefaultDao defaultDao) {
        databaseStatement.clearBindings();
        Long number = defaultDao.getNumber();
        if (number != null) {
            databaseStatement.bindLong(1, number.longValue());
        }
        String daoName = defaultDao.getDaoName();
        if (daoName != null) {
            databaseStatement.bindString(2, daoName);
        }
        String seles = defaultDao.getSeles();
        if (seles != null) {
            databaseStatement.bindString(3, seles);
        }
        databaseStatement.bindLong(4, defaultDao.getGrade());
        databaseStatement.bindLong(5, defaultDao.getItem2());
        databaseStatement.bindLong(6, defaultDao.getItem3());
        databaseStatement.bindLong(7, defaultDao.getItem4());
        databaseStatement.bindLong(8, defaultDao.getItem5());
        databaseStatement.bindLong(9, defaultDao.getItem6());
        databaseStatement.bindLong(10, defaultDao.getFreq());
        databaseStatement.bindLong(11, defaultDao.getVelo());
        databaseStatement.bindLong(12, defaultDao.getRote());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DefaultDao defaultDao) {
        sQLiteStatement.clearBindings();
        Long number = defaultDao.getNumber();
        if (number != null) {
            sQLiteStatement.bindLong(1, number.longValue());
        }
        String daoName = defaultDao.getDaoName();
        if (daoName != null) {
            sQLiteStatement.bindString(2, daoName);
        }
        String seles = defaultDao.getSeles();
        if (seles != null) {
            sQLiteStatement.bindString(3, seles);
        }
        sQLiteStatement.bindLong(4, defaultDao.getGrade());
        sQLiteStatement.bindLong(5, defaultDao.getItem2());
        sQLiteStatement.bindLong(6, defaultDao.getItem3());
        sQLiteStatement.bindLong(7, defaultDao.getItem4());
        sQLiteStatement.bindLong(8, defaultDao.getItem5());
        sQLiteStatement.bindLong(9, defaultDao.getItem6());
        sQLiteStatement.bindLong(10, defaultDao.getFreq());
        sQLiteStatement.bindLong(11, defaultDao.getVelo());
        sQLiteStatement.bindLong(12, defaultDao.getRote());
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
    public DefaultDao readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        int i3 = i + 2;
        return new DefaultDao(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DefaultDao defaultDao, int i) {
        defaultDao.setNumber(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        defaultDao.setDaoName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        defaultDao.setSeles(cursor.isNull(i3) ? null : cursor.getString(i3));
        defaultDao.setGrade(cursor.getInt(i + 3));
        defaultDao.setItem2(cursor.getInt(i + 4));
        defaultDao.setItem3(cursor.getInt(i + 5));
        defaultDao.setItem4(cursor.getInt(i + 6));
        defaultDao.setItem5(cursor.getInt(i + 7));
        defaultDao.setItem6(cursor.getInt(i + 8));
        defaultDao.setFreq(cursor.getInt(i + 9));
        defaultDao.setVelo(cursor.getInt(i + 10));
        defaultDao.setRote(cursor.getInt(i + 11));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(DefaultDao defaultDao, long j) {
        defaultDao.setNumber(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(DefaultDao defaultDao) {
        if (defaultDao != null) {
            return defaultDao.getNumber();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DefaultDao defaultDao) {
        return defaultDao.getNumber() != null;
    }
}
