package com.e.hika.handler;

import com.e.hika.i18npojo.Name;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Name.class)
public class NameReturnHandler extends BaseTypeHandler<Name> {

    private static final Gson gson = new Gson();


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Name parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, gson.toJson(parameter));
    }

    @Override
    public Name getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        System.out.println("读取的json字段" + json);

        return json != null ? gson.fromJson(json, Name.class) : null;
    }

    @SneakyThrows
    @Override
    public Name getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        System.out.println("读取的json字段" + json);
        return json != null ? gson.fromJson(json, Name.class) : null;
    }

    @Override
    public Name getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);

        return json != null ? gson.fromJson(json, Name.class) : null;
        //        return null;
    }
}
