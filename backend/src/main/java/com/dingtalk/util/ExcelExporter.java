/**
 * @author OpenTheDoor
 * @date 2024/4/21 21:17
 * @version 1.0
 */
package com.dingtalk.util;

import com.dingtalk.model.ProductInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void exportProducts(List<ProductInfo> products, String excelFilePath, HttpServletResponse response) throws IOException {

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=products.xlsx");
        // 创建工作簿
        Workbook workbook = new XSSFWorkbook();

        // 创建一个工作表sheet
        Sheet sheet = workbook.createSheet("Products");

        // 创建表头
        String[] headers = {"ID", "Name", "Description", "Price"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 填充数据
        int rowIndex = 1;
        for (ProductInfo product : products) {
            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(product.getProductId());
            row.createCell(1).setCellValue(product.getProductName());
            row.createCell(2).setCellValue(product.getProductCode());
            row.createCell(3).setCellValue(String.valueOf(product.getDealerPrice()));
        }

        // 自动调整列宽
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        ServletOutputStream outputStream = response.getOutputStream();
        // 使用文件输出流写入磁盘
        workbook.write(outputStream);
        // 关闭工作簿
        workbook.close();
    }
}
