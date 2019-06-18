package com.mybatis.sqlsession;

import com.cfg.Configuration;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = com.utils.XMLConfigBuilder.loadConfiguration(config);
        return new SqlSessionFactoryImpl(cfg);
    }

}
