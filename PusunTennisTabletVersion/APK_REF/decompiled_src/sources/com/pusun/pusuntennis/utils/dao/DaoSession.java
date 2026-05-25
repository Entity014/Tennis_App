package com.pusun.pusuntennis.utils.dao;

import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes3.dex */
public class DaoSession extends AbstractDaoSession {
    private final DefaultDaoDao defaultDaoDao;
    private final DaoConfig defaultDaoDaoConfig;
    private final SeleDaoDao seleDaoDao;
    private final DaoConfig seleDaoDaoConfig;
    private final VariDaoDao variDaoDao;
    private final DaoConfig variDaoDaoConfig;
    private final VariSpinDaoDao variSpinDaoDao;
    private final DaoConfig variSpinDaoDaoConfig;
    private final WholeVaryDao wholeVaryDao;
    private final DaoConfig wholeVaryDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        DaoConfig clone = map.get(DefaultDaoDao.class).clone();
        this.defaultDaoDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        DaoConfig clone2 = map.get(SeleDaoDao.class).clone();
        this.seleDaoDaoConfig = clone2;
        clone2.initIdentityScope(identityScopeType);
        DaoConfig clone3 = map.get(VariDaoDao.class).clone();
        this.variDaoDaoConfig = clone3;
        clone3.initIdentityScope(identityScopeType);
        DaoConfig clone4 = map.get(VariSpinDaoDao.class).clone();
        this.variSpinDaoDaoConfig = clone4;
        clone4.initIdentityScope(identityScopeType);
        DaoConfig clone5 = map.get(WholeVaryDao.class).clone();
        this.wholeVaryDaoConfig = clone5;
        clone5.initIdentityScope(identityScopeType);
        DefaultDaoDao defaultDaoDao = new DefaultDaoDao(clone, this);
        this.defaultDaoDao = defaultDaoDao;
        SeleDaoDao seleDaoDao = new SeleDaoDao(clone2, this);
        this.seleDaoDao = seleDaoDao;
        VariDaoDao variDaoDao = new VariDaoDao(clone3, this);
        this.variDaoDao = variDaoDao;
        VariSpinDaoDao variSpinDaoDao = new VariSpinDaoDao(clone4, this);
        this.variSpinDaoDao = variSpinDaoDao;
        WholeVaryDao wholeVaryDao = new WholeVaryDao(clone5, this);
        this.wholeVaryDao = wholeVaryDao;
        registerDao(DefaultDao.class, defaultDaoDao);
        registerDao(SeleDao.class, seleDaoDao);
        registerDao(VariDao.class, variDaoDao);
        registerDao(VariSpinDao.class, variSpinDaoDao);
        registerDao(WholeVary.class, wholeVaryDao);
    }

    public void clear() {
        this.defaultDaoDaoConfig.clearIdentityScope();
        this.seleDaoDaoConfig.clearIdentityScope();
        this.variDaoDaoConfig.clearIdentityScope();
        this.variSpinDaoDaoConfig.clearIdentityScope();
        this.wholeVaryDaoConfig.clearIdentityScope();
    }

    public DefaultDaoDao getDefaultDaoDao() {
        return this.defaultDaoDao;
    }

    public SeleDaoDao getSeleDaoDao() {
        return this.seleDaoDao;
    }

    public VariDaoDao getVariDaoDao() {
        return this.variDaoDao;
    }

    public VariSpinDaoDao getVariSpinDaoDao() {
        return this.variSpinDaoDao;
    }

    public WholeVaryDao getWholeVaryDao() {
        return this.wholeVaryDao;
    }
}
