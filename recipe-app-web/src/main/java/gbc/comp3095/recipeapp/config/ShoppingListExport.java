package gbc.comp3095.recipeapp.config;

import gbc.comp3095.models.Ingredient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShoppingListExport {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: Assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588  - 101292266    - 101303158            - 101311327
    //* Date: 2022-10-23
    //* Description: This class utilizes the Apache POI which is used for handling
    //*              Microsoft Document Extension, to create a new worksheet, add the header
    //*              column names such as Id, recipe_name, ingredient description, quantity
    //*              and unit of measurement.
    // *********************************************************************************/
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Ingredient> shoppinglist;

    public ShoppingListExport(List<Ingredient> shoppinglist) {
        this.shoppinglist = shoppinglist;
        this.workbook = new XSSFWorkbook();
    }
    private void writeHeaderLine() {
        this.sheet = this.workbook.createSheet("ShoppingList");

        Row row = sheet.createRow(0);

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "#Ingredient", style);
        createCell(row, 1, "Recipe", style);
        createCell(row, 2, "Description", style);
        createCell(row, 3, "Amount", style);
        createCell(row, 4, "Unit of Measurement", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Ingredient i : this.shoppinglist) {
            Row row = this.sheet.createRow(rowCount++);
            int colCount = 0;

            createCell(row, colCount++, String.valueOf(i.getId()), style);
            createCell(row, colCount++, i.getRecipe().getName(), style);
            createCell(row, colCount++, i.getDescription(), style);
            createCell(row, colCount++, String.valueOf(i.getQuantity()), style);
            createCell(row, colCount++, String.valueOf(i.getUnitOfMeasurement().name()), style);
        }
    }
    public void export(HttpServletResponse res) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream output = res.getOutputStream();
        this.workbook.write(output);
        this.workbook.close();

        output.close();
    }
}
