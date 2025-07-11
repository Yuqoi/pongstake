package com.pongstake.notifications.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class IntArrayType implements UserType {
    protected static final int[] SQL_TYPES = { Types.ARRAY };

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return this.deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Integer[]) this.deepCopy(value);
    }

    @Override
    public int getSqlType() {
        return 0;
    }

    @Override
    public Class returnedClass() {
        return null;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {

        if (x == null) {
            return y == null;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {

    }
}
