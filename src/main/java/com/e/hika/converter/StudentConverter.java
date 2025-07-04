package com.e.hika.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.e.hika.pojo.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StudentConverter implements Converter<List<Student>> {

    private static final Gson gson = new Gson();

    @Override
    public Class<?> supportJavaTypeKey() {
        return List.class;               // 也可以直接寫 List.class
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;  // Excel 以字串存 JSON
    }


    /**
     * 讀取時：字串 → List<Student>
     */
    @Override
    public List<Student> convertToJavaData(ReadCellData<?> cellData,
                                           ExcelContentProperty property,
                                           GlobalConfiguration globalCfg) throws Exception {
        String json = cellData.getStringValue();
        if (json == null || json.isBlank()) {
            return Collections.emptyList();
        }
        return gson.fromJson(json, new TypeToken<List<Student>>() {
        }.getType());
    }

    /**
     * 寫出時：List<Student> → JSON 字串
     */
    @Override
    public WriteCellData<String> convertToExcelData(List<Student> value,
                                                    ExcelContentProperty property,
                                                    GlobalConfiguration globalCfg) throws Exception {
        return new WriteCellData<>(gson.toJson(value));
    }

}
