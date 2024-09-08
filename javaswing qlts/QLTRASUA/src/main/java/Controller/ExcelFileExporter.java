package Controller;

import Model.ChiTietHoaDon;
import Model.QLHoaDon;
import Model.QLNhanVien;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Font;

/**
 * Excel File Exporter
 */
public class ExcelFileExporter {
    public void exportNhanVienExcelFile(ArrayList<QLNhanVien> nv, String[] headers, String fileName) {
        // create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        // Create a new Sheet named "Contacts"
        Sheet sheet = workbook.createSheet("Nhân viên");
        // Create header row
        createHeaderRow(workbook, sheet, headers,0);
        // Create rows
        for(int i = 0; i < nv.size(); i++) {
            // row index equals i + 1 because the first row of Excel file is the header row.
            int rowIndex = i + 1;
            createNewRow(workbook, sheet, rowIndex, nv.get(i));
        }
        // Adjusts columns to set the width to fit the contents.
        for(int i=0;i<headers.length;i++){
            sheet.autoSizeColumn(i);
        }
        // Write to file
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Create a new row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param rowIndex the index of row to create
     * @param contact the Contact object which represent information to write to row.
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, QLNhanVien nv) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(nv.getMaNhanVien());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(nv.getTenNhanVien());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(nv.getPhone());
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(3);
        cell.setCellValue(nv.getEmail());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue(nv.getCMND());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue(nv.getNgayLamViec());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(6);
        cell.setCellValue(nv.getCaLamViec());
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(7);
        cell.setCellValue(nv.getLuongCoBan());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(8);
        cell.setCellValue(nv.getHeSoLuong());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(9);
        cell.setCellValue(nv.getTienLuong());
        cell.setCellStyle(cellStyle);
    }
    //<editor-fold defaultstate="collapsed" desc="HoaDon">
    public void exportHoaDonExcelFile(ArrayList<QLHoaDon> hd, String[] headers, String fileName) {
        // create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        // Create a new Sheet named "Contacts"
        Sheet sheet = workbook.createSheet("Hóa đơn");
        // Create header row
        createHeaderRow(workbook, sheet, headers,0);
        // Create rows
        for(int i = 0; i < hd.size(); i++) {
            // row index equals i + 1 because the first row of Excel file is the header row.
            int rowIndex = i + 1;
            createNewRow(workbook, sheet, rowIndex, hd.get(i));
        }
        // Adjusts columns to set the width to fit the contents.
        for(int i=0;i<headers.length;i++){
            sheet.autoSizeColumn(i);
        }
        // Write to file
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Create a new row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param rowIndex the index of row to create
     * @param contact the Contact object which represent information to write to row.
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, QLHoaDon hd) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(hd.getMaHD());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(hd.getTenNhanVien());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(hd.getNgayLap());
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(3);
        cell.setCellValue(hd.getMaBan());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue(hd.getThanhTien());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(5);
        cell.setCellValue(hd.getTinhTrang());
        cell.setCellStyle(cellStyle);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="ChiTietHoaDon">
    public void exportChiTietHoaDonExcelFile(QLHoaDon hd ,ArrayList<ChiTietHoaDon> cthd, String[] headers, String fileName) {
        // create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        // Create a new Sheet named "Contacts"
        Sheet sheet = workbook.createSheet("Chi tiết hóa đơn");
        // Create info
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Hóa Đơn");
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(hd.getMaHD());
        cell = row.createCell(1);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        String date = dateFormat.format(hd.getNgayLap());
        cell.setCellValue(date);
        // Create header row
        createHeaderRow(workbook, sheet, headers,2);
        // Create rows
        int rowIndex=2;
        for(int i = 0; i < cthd.size(); i++) {
            // row index equals i + 1 because the first row of Excel file is the header row.
            rowIndex = i + 3;
            createNewRow(workbook, sheet, rowIndex, cthd.get(i));
        }
        // Adjusts columns to set the width to fit the contents.
        for(int i=0;i<headers.length;i++){
            sheet.autoSizeColumn(i);
        }
        // Tổng
        row = sheet.createRow(rowIndex+1);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        
        cell = row.createCell(0);
        cell.setCellValue("");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(1);
        cell.setCellValue("");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue("Tổng");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(3);
        cell.setCellValue(hd.getThanhTien());
        cell.setCellStyle(cellStyle);
        // Write to file
        try (OutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Create a new row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param rowIndex the index of row to create
     * @param contact the Contact object which represent information to write to row.
     */
    private void createNewRow(Workbook workbook, Sheet sheet, int rowIndex, ChiTietHoaDon hd) {
        Row row = sheet.createRow(rowIndex);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        Cell cell = row.createCell(0);
        cell.setCellValue(hd.getTenDV());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        cell.setCellValue(hd.getSoLuong());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(hd.getDonGia());
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(3);
        cell.setCellValue(hd.getThanhTien());
        cell.setCellStyle(cellStyle);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Public">
    /**
     * Create header row
     * @param workbook the Workbook object
     * @param sheet the Sheet object
     * @param headers the headers text
     */
    private void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers, int index) {
        Row headerRow = sheet.createRow(index);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.index);

        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
    }
    //</editor-fold>
}