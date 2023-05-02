package com.enjoytrip.global.mybatis;

import com.enjoytrip.attraction.entity.AttractionCategory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttractionCategoryTypeHandler implements TypeHandler<AttractionCategory> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, AttractionCategory attractionCategory, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, attractionCategory.getCODE());
    }

    @Override
    public AttractionCategory getResult(ResultSet resultSet, String s) throws SQLException {
        return AttractionCategory.valueOfCode(resultSet.getInt(Integer.parseInt(s)));
    }

    @Override
    public AttractionCategory getResult(ResultSet resultSet, int i) throws SQLException {
        return AttractionCategory.valueOfCode(resultSet.getInt(i));
    }

    @Override
    public AttractionCategory getResult(CallableStatement callableStatement, int i) throws SQLException {
        return AttractionCategory.valueOfCode(callableStatement.getInt(i));
    }
}
