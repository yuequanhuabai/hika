package com.e.hika.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExcelUtils {

//    public static <T> void exportExcel(HttpServletResponse response, List<T> data,
//                                       String sheetName, String fileName,
//                                       Class<T> clazz) throws IOException {
//        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//        response.setCharacterEncoding("UTF-8");
//        fileName = UriEncoder.encode(fileName).replaceAll("\\+", "%20");
//        response.setHeader("Content-disposition", "attachment; filename*=utf-8''" + fileName + ".xlsx");
//
//        EasyExcel.write(response.getOutputStream(), clazz)
////                .excelType(ExcelTypeEnum.CSV)
//                .sheet(sheetName)
//                .doWrite(data);
//
//    }


    public static <T> void exportCSV(HttpServletResponse response, List<T> data,
                                     String sheetName, String fileName,
                                     Class<T> clazz) throws IOException {
        response.setContentType("text/csv;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String encoded = UriEncoder.encode(fileName).replaceAll("\\+", "%20");
//        fileName = UriEncoder.encode(fileName).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment; filename*=utf-8''" + encoded + ".csv");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(new byte[]{(byte) 0xEF}, (byte) 0xBB, (byte) 0xBF);

        EasyExcel.write(response.getOutputStream(), clazz)
                .excelType(ExcelTypeEnum.CSV)
                .autoCloseStream(false)
                .sheet("data")
                .doWrite(data);

    }


    public static <T> List<T> importExcelMini(MultipartFile file, Class<T> clazz) throws IOException {
        return EasyExcel.read(file.getInputStream())
                .headRowNumber(1)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .head(clazz)
                .sheet()
                .doReadSync();  // 同步讀取，適合小文件；
    }


    public static <T> List<T> importExcelBig(MultipartFile file, Class<T> clazz) throws IOException {
        return EasyExcel.read(file.getInputStream())
                .headRowNumber(1)
                .excelType(ExcelTypeEnum.CSV)
                .charset(StandardCharsets.UTF_8)
                .head(clazz)
                .sheet()
                .doReadSync();  // 同步讀取，適合小文件；
    }


}
