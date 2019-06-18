package com.mybatis.sqlsession;

import com.cfg.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryImpl  implements SqlSessionFactory{
    private Configuration cfg;
    public SqlSessionFactoryImpl(Configuration cfg){
        this.cfg=cfg;

    }
    public SqlSession openSession() {
        return new SqlSessionImpl(cfg);
    }
}
