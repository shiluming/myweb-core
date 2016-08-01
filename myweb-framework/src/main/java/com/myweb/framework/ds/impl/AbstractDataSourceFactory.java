package com.myweb.framework.ds.impl;

import com.myweb.framework.ds.DataSourceFactory;
import com.myweb.framework.helper.ConfigHelper;

import javax.sql.DataSource;

/**
 * Created by rola
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory{

    protected final String driver = ConfigHelper.getJdbcDriver();

    protected final String url = ConfigHelper.getJdbcUrl();

    protected final String username = ConfigHelper.getJdbcUserName();

    protected final String password = ConfigHelper.getJdbcPassword();


    @Override
    public final T getDataSource() {
        //创建数据源
        T ds = createDataSource();
        //设置基础属性
        setDriver(ds,driver);
        setUrl(ds,url);
        setUsername(ds,username);
        setPassword(ds,password);
        setAdvanceConfig(ds);

        return ds;
    }

    public abstract T createDataSource();

    public abstract void setDriver(T ds,String driver);

    public abstract void setUrl(T ds,String url);

    public abstract void setUsername(T ds,String username);

    public abstract void setPassword(T ds,String password);

    public abstract void setAdvanceConfig(T ds);
}
