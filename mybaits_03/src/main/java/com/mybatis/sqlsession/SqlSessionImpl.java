package com.mybatis.sqlsession;

import com.cfg.Configuration;
import com.dao.UserDao;
import com.utils.DataSourceUtils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlSessionImpl  implements SqlSession{
    private Configuration cfg;
    private Connection connection;
    public SqlSessionImpl(Configuration cfg){
        this.cfg=cfg;
        connection = DataSourceUtils.getConnection(cfg);
    }
    public <T> T getMapper(Class<T> daoInterfaceClass) {
/*        Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(),connection));*/
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(),connection));
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
