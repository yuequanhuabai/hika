//package com.e.hika.converter;
//
//import com.alibaba.excel.converters.Converter;
//import com.alibaba.excel.metadata.GlobalConfiguration;
//import com.alibaba.excel.metadata.data.WriteCellData;
//import com.alibaba.excel.metadata.property.ExcelContentProperty;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.util.List;
//
//public class GenericListConverter implements Converter<List<?>> {
//
//    private static final Gson gson = new Gson();
//
//    @Override
//    public Class<?> supportJavaTypeKey() {
//        return List.class;
//    }
//
//    @Override
//    public WriteCellData<?> convertToExcelData(List<?> value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        if (value == null || value.isEmpty()) {
//            return new WriteCellData<>("");
//        }
//
//        String json = gson.toJson(value, new TypeToken<List<Object>>() {
//        }.getType());
//
//        return new WriteCellData<>(json);
//    }
//}
