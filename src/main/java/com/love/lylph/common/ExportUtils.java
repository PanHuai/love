package com.love.lylph.common;

import com.love.lylph.pojo.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author PanHuai
 * @data 2018/8/7 15:38
 */
public class ExportUtils {

    private static final ExportUtils export = new ExportUtils();

    public static ExportUtils getExport() {
        return export;
    }

    /**
     * 判断导入文件格式 .xls / .xlsx
     */
    public int isFile(String name) {

        if (name.matches("^.+\\.(?i)(xls)$")) {
            return 1;
        } else if (name.matches("^.+\\.(?i)(xlsx)$")) {
            return 2;
        }
        return 0;
    }

    /**
     *  导入表格
     * @param file
     * @return
     * @throws IOException
     */
    public List<User> imports(MultipartFile file) throws IOException {

        Workbook wb = null;
        POIFSFileSystem ps = null;
        if (isFile(file.getOriginalFilename()) == 1 ) {
            ps = new POIFSFileSystem(file.getInputStream());
            wb = new HSSFWorkbook(ps);
        } else if (isFile(file.getOriginalFilename()) == 2) {
            wb = new XSSFWorkbook(file.getInputStream());
        } else {
            return null;
        }
        List<User> list = new ArrayList<>();
        User user = new User();
        //获取一共有多少sheet
        int num = wb.getNumberOfSheets();
        for (int i = 0; i < num; i++) {
            Sheet sheet = wb.getSheetAt(i);
            //获取sheet一共有多少行
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++) {
                if (j == 0) {
                    //第一行是标题
                    continue;
                }
                Row row = sheet.getRow(j);
                user.setName(row.getCell(1).getStringCellValue());
                user.setUsername(row.getCell(2).getStringCellValue());
                user.setPassword(row.getCell(1).getStringCellValue());
                user.setVersion(row.getCell(1).getStringCellValue());
                list.add(user);
            }
        }
        if (ps != null) {
            ps.close();
        }
        if (wb != null) {
            wb.close();
        }
        return list;
    }

    /**
     * 导出表格
     * @param list
     * @param response
     * @throws IOException
     */
    public void export(List<User> list,String name, HttpServletResponse response) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("测试");
        setTitle(wb,sheet);
        //普通for比foreach效率高
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(list.get(i).getUsername());
            row.createCell(1).setCellValue(list.get(i).getPassword());
        }
        response.reset();
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.addHeader("Content-Disposition", "attachment;filename="+ name+".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        wb.write(os);
        os.flush();
        if (os != null) {
            os.close();
        }
        if (wb != null) {
            wb.close();
        }
    }

    /**
     * 创建表头
     */
    public void setTitle(Workbook wb,Sheet sheet ) {
        Row row = sheet.createRow(0);



    }

}
