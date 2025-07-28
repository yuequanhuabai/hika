package com.e.hika.ex;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.e.hika.pojo.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class LambdaFieldMapGenerator {

    Map<String, SFunction<Student, ?>> map = Map.of(
            "name", Student::getName,
            "id", Student::getId,
            "ids", Student::getIds,
            "sUpdateTime", Student::getsUpdateTime,
            "sCreateTime", Student::getsCreateTime,
            "at", Student::getAt
    );

    @Test
    public void test() {
        generate(Student.class);
    }

    public static <T> void generate(Class<T> clazz) {
        String className = clazz.getSimpleName();
        StringBuilder builder = new StringBuilder();
        builder.append("Map<String, SFunction<")
                .append(className).append(", ?>> map = Map.of(\n");

        Method[] methods = clazz.getDeclaredMethods();

        Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0)
                .forEach(m -> {
                    String methodName = m.getName();         // getCreateTime
                    String fieldName = decapitalize(methodName.substring(3));  // createTime
                    builder.append("    \"").append(fieldName).append("\", ")
                            .append(className).append("::").append(methodName)
                            .append(",\n");
                });

        builder.setLength(builder.length() - 2); // remove last comma
        builder.append("\n);");

        System.out.println("👉 複製以下代碼：\n");
        System.out.println(builder);
    }


    private static String decapitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }


}
