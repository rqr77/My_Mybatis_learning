package com.mybatis.sqlsession;

import java.io.InputStream;

public interface SqlSessionFactory {
    public SqlSession openSession();
}
