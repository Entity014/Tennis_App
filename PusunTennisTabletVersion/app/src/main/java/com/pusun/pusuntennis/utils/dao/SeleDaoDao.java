package com.pusun.pusuntennis.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class SeleDaoDao extends AbstractDao<SeleDao, Long> {
    public static final String TABLENAME = "SELE_DAO";

    public static class Properties {
        public static final Property Number = new Property(0, Long.class, "number", true, "_id");
        public static final Property DaoName = new Property(1, String.class, "daoName", false, "DAO_NAME");
        public static final Property Seles = new Property(2, String.class, "seles", false, "SELES");
        public static final Property Item1 = new Property(3, Integer.TYPE, "item1", false, "ITEM1");
        public static final Property Item2 = new Property(4, Integer.TYPE, "item2", false, "ITEM2");
        public static final Property Item3 = new Property(5, Integer.TYPE, "item3", false, "ITEM3");
        public static final Property Freq = new Property(6, Integer.TYPE, "freq", false, "FREQ");
        public static final Property Velo = new Property(7, Integer.TYPE, "velo", false, "VELO");
        public static final Property Rote = new Property(8, Integer.TYPE, "rote", false, "ROTE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SeleDaoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SeleDaoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SELE_DAO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DAO_NAME\" TEXT,\"SELES\" TEXT,\"ITEM1\" INTEGER NOT NULL ,\"ITEM2\" INTEGER NOT NULL ,\"ITEM3\" INTEGER NOT NULL ,\"FREQ\" INTEGER NOT NULL ,\"VELO\" INTEGER NOT NULL ,\"ROTE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SELE_DAO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SeleDao seleDao) {
        databaseStatement.clearBindings();
        Long number = seleDao.getNumber();
        if (number != null) {
            databaseStatement.bindLong(1, number.longValue());
        }
        String daoName = seleDao.getDaoName();
        if (daoName != null) {
            databaseStatement.bindString(2, daoName);
        }
        String seles = seleDao.getSeles();
        if (seles != null) {
            databaseStatement.bindString(3, seles);
        }
        databaseStatement.bindLong(4, seleDao.getItem1());
        databaseStatement.bindLong(5, seleDao.getItem2());
        databaseStatement.bindLong(6, seleDao.getItem3());
        databaseStatement.bindLong(7, seleDao.getFreq());
        databaseStatement.bindLong(8, seleDao.getVelo());
        databaseStatement.bindLong(9, seleDao.getRote());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SeleDao seleDao) {
        sQLiteStatement.clearBindings();
        Long number = seleDao.getNumber();
        if (number != null) {
            sQLiteStatement.bindLong(1, number.longValue());
        }
        String daoName = seleDao.getDaoName();
        if (daoName != null) {
            sQLiteStatement.bindString(2, daoName);
        }
        String seles = seleDao.getSeles();
        if (seles != null) {
            sQLiteStatement.bindString(3, seles);
        }
        sQLiteStatement.bindLong(4, seleDao.getItem1());
        sQLiteStatement.bindLong(5, seleDao.getItem2());
        sQLiteStatement.bindLong(6, seleDao.getItem3());
        sQLiteStatement.bindLong(7, seleDao.getFreq());
        sQLiteStatement.bindLong(8, seleDao.getVelo());
        sQLiteStatement.bindLong(9, seleDao.getRote());
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
    public SeleDao readEntity(Cursor cursor, int i) {
        Long valueOf = cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i));
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        return new SeleDao(valueOf, string, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SeleDao seleDao, int i) {
        seleDao.setNumber(cursor.isNull(i) ? null : Long.valueOf(cursor.getLong(i)));
        int i2 = i + 1;
        seleDao.setDaoName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        seleDao.setSeles(cursor.isNull(i3) ? null : cursor.getString(i3));
        seleDao.setItem1(cursor.getInt(i + 3));
        seleDao.setItem2(cursor.getInt(i + 4));
        seleDao.setItem3(cursor.getInt(i + 5));
        seleDao.setFreq(cursor.getInt(i + 6));
        seleDao.setVelo(cursor.getInt(i + 7));
        seleDao.setRote(cursor.getInt(i + 8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SeleDao seleDao, long j) {
        seleDao.setNumber(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SeleDao seleDao) {
        if (seleDao != null) {
            return seleDao.getNumber();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SeleDao seleDao) {
        return seleDao.getNumber() != null;
    }
}
