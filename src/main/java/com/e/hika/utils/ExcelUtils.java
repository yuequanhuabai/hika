package com.e.hika.utils;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.util.List;

public class ExcelUtils {

    public static <T> void exportExcel(HttpServletResponse response, List<T> data,
                                       String sheetName, String fileName,
                                       Class<T> clazz) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        fileName = UriEncoder.encode(fileName).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment; filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(data);

    }


    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz) throws IOException {
        return EasyExcel.read(file.getInputStream()).headRowNumber(1).head(clazz).sheet()
                .doReadSync();  // 同步讀取，適合小文件；
    }


}
