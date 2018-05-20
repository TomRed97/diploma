package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.SpreadsheetService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class SpreadsheetServiceImpl implements SpreadsheetService {

    @Override
    public String createSpreadsheet(List<List<String>> rows) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Online shop");

        List<String> firstRow = rows.get(0);

        for (int i = 0; i < firstRow.size(); i++) {
            sheet.setColumnWidth(i, 6000);
        }

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        IntStream
                .range(0, firstRow.size())
                .forEach(index -> {
                    Cell headerCell = header.createCell(index);
                    headerCell.setCellValue(firstRow.get(index));
                    headerCell.setCellStyle(headerStyle);
                });

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        IntStream
                .range(1, rows.size())
                .forEach(rowIndex -> {
                    Row row = sheet.createRow(rowIndex);
                    IntStream
                            .range(0, rows.get(rowIndex).size())
                            .forEach(cellIndex -> {
                                Cell cell = row.createCell(cellIndex);
                                cell.setCellValue(rows.get(rowIndex).get(cellIndex));
                                cell.setCellStyle(style);
                            });
                });


        File currDir = new File(".");
        String path = currDir.getAbsolutePath();

        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = "easy-scraping-" + localDateTime.toLocalDate() + "_" + localDateTime.getHour() + "-" + localDateTime.getMinute() + "-" + localDateTime.getSecond() + ".xlsx";

        String fileLocation = path.substring(0, path.length() - 1) + fileName;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();

        return fileName;
    }
}
