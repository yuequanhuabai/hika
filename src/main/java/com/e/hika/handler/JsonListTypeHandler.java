//package com.e.hika.handler;
//
//import com.e.hika.converter.LocalDateTimeAdaptor;
//import com.e.hika.pojo.Student;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@MappedTypes(List.class)
//@MappedJdbcTypes(JdbcType.VARCHAR)
//public class JsonListTypeHandler extends BaseTypeHandler<List<Student>> {
//
//    private static final Logger logger = LoggerFactory.getLogger(JsonListTypeHandler.class);
//
////    private static final Gson gson = new Gson();
//
//        private static final Gson gson = new GsonBuilder()
//            .registerTypeHierarchyAdapter(LocalDateTime.class, new LocalDateTimeAdaptor())
//            .create();
//
//
//    public JsonListTypeHandler() {
//        logger.info("JsonListTypeHandler");
//    }
//
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, List<Student> parameter, JdbcType jdbcType) throws SQLException {
//        ps.setString(i, gson.toJson(parameter));
//    }
//
//    @Override
//    public List<Student> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        String json = rs.getString(columnName);
//        return parseJson(json);
//    }
//
//    @Override
//    public List<Student> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String json = rs.getString(columnIndex);
//        return parseJson(json);
//    }
//
//    @Override
//    public List<Student> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String json = cs.getString(columnIndex);
//        return parseJson(json);
//    }
//
//
//    private List<Student> parseJson(String json) {
//        if (json == null || json.isEmpty()) {
//            return null;
//        }
//        return gson.fromJson(json, new TypeToken<List<Student>>() {
//        }.getType());
//    }
//}
