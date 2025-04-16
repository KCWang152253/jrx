package com.anytech.anytxn.biz.common.config.monitor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.stat.JdbcSqlStatValue;

import javax.sql.DataSource;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Description  将Druid sql监控写入Csv文件
 * Dept:		江融信
 * Author:		lichao
 * Version:		1.0
 * Create at:	2020-08-21 13:58
 *
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------
 **/
public class DruidSqlMonitorWriteCsv {

    private DataSource commonDataSource;

    private DataSource businessDataSource;

    private DataSource cidMappingDataSource;



    public DruidSqlMonitorWriteCsv(DataSource commonDataSource, DataSource businessDataSource, DataSource cidMappingDataSource){
        this.commonDataSource = commonDataSource;
        this.businessDataSource = businessDataSource;
        this.cidMappingDataSource = cidMappingDataSource;
    }

    /**
     * @Description 记录sql
     * @Method logSql
     * @Params [fileName, outPath]
     * @Return void
     * @Date 2020/8/25
     **/
    public void logSql(String fileName,String outPath) {
        DateTimeFormatter YMD_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss");
        if (commonDataSource != null) {
            try {
                createCSV(commonDataSource,"commonDb_"+fileName+"-"+ YMD_HMS.format(LocalDateTime.now()),outPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cidMappingDataSource!=null){
            createCSV(cidMappingDataSource,"mappingDb_"+fileName+"-"+ YMD_HMS.format(LocalDateTime.now()),outPath);

        }if(businessDataSource!=null){
            createCSV(businessDataSource,"bizDb_"+fileName+"-"+ YMD_HMS.format(LocalDateTime.now()),outPath);
        }
    }
   /* public void createExcel(List<JdbcSqlStatValue> sqlList,String fileName,String outPath) throws Exception {
        //表头数据
        String[] header = {"N", "SQL", "执行数", "执行时间", "最慢", "事务中","错误数","更新行数","读取行数","执行中","最大并发","执行时间分布","执行+RS时分布","读取行分布","更新行分布"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"Druid Sql监控"
        HSSFSheet sheet = workbook.createSheet("Druid Sql监控");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头()
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }
        for (int i = 0; i < sqlList.size(); i++) {

            JdbcSqlStatValue sqlStat=sqlList.get(i);
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
            //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(row1.toString()));
            //第二列创建并赋值
            row1.createCell(1).setCellValue(new HSSFRichTextString(sqlStat.getSql()));
            //第三列创建并赋值
            row1.createCell(2).setCellValue(new HSSFRichTextString(sqlStat.getExecuteCount()+""));
            //第4列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(sqlStat.getExecuteMillisTotal()+""));
            //第5列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(sqlStat.getExecuteMillisMax()+""));
            //第6列创建并赋值
            row1.createCell(5).setCellValue(new HSSFRichTextString(sqlStat.getInTransactionCount()+""));
            //第7列创建并赋值
            row1.createCell(6).setCellValue(new HSSFRichTextString(sqlStat.getExecuteErrorCount()+""));
            //籍贯
            row1.createCell(7).setCellValue(new HSSFRichTextString(sqlStat.getUpdateCount()+""));
            //第8列创建并赋值
            row1.createCell(8).setCellValue(new HSSFRichTextString(sqlStat.getReadStringLength()+""));
            //第9列创建并赋值
            row1.createCell(9).setCellValue(new HSSFRichTextString(sqlStat.getExecuteSuccessCount()+""));
            //第10列创建并赋值
            row1.createCell(10).setCellValue(new HSSFRichTextString(sqlStat.getExecuteBatchSizeMax()+""));
            //第11列创建并赋值
            row1.createCell(11).setCellValue(new HSSFRichTextString(sqlStat.getHistogram_100_1000()+""));
            //第12列创建并赋值
            row1.createCell(12).setCellValue(new HSSFRichTextString(sqlStat.getHistogram_1000_10000()+""));
            //第13列创建并赋值
            row1.createCell(13).setCellValue(new HSSFRichTextString(sqlStat.getHistogram_10000_100000()+""));
            //第14列创建并赋值
            row1.createCell(14).setCellValue(new HSSFRichTextString(sqlStat.getHistogram_100000_1000000()+""));
        }
        FileOutputStream fos =new FileOutputStream( outPath+fileName+".xlsx");
        workbook.write(fos);
        fos.close();
    }*/


    /**
     * @Description 创建CSV文件
     * @Method createCSV
     * @Params [sqlList, fileName, outPath]
     * @Return void
     * @Date 2020/8/21
     **/
    private  void createCSV(DataSource dataSource,String fileName,String outPath) {
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        DruidDataSourceStatValue statValue = druidDataSource.getStatValueAndReset();
        List<JdbcSqlStatValue> sqlList = statValue.getSqlList();
        if(sqlList.size()==0){
            return;
        }
        // 表格头
        Object[] head = {"N", "SQL", "执行数", "执行时间", "最慢", "事务中","错误数","更新行数","读取行数","执行中","最大并发","执行时间分布","执行+RS时分布","读取行分布","更新行分布"};

        List<Object> headList = Arrays.asList(head);

        //数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = null;
        for (int i = 0; i < sqlList.size(); i++) {
            JdbcSqlStatValue sqlStat=sqlList.get(i);
            rowList = new ArrayList<>();
            //第一列创建并赋值
            rowList.add(i+1+"");
            //第二列创建并赋值
            String sqlstr=sqlStat.getSql();
            rowList.add(csvHandlerStr(sqlstr));
            //第三列创建并赋值
            rowList.add(sqlStat.getExecuteCount()+"");
            //第4列创建并赋值
            rowList.add(sqlStat.getExecuteMillisTotal()+"");
            //第5列创建并赋值
            rowList.add(sqlStat.getExecuteMillisMax()+"");
            //第6列创建并赋值
            rowList.add(sqlStat.getInTransactionCount()+"");
            //第7列创建并赋值
            rowList.add(sqlStat.getExecuteErrorCount()+"");
            //籍贯
            rowList.add(sqlStat.getUpdateCount()+"");
            //第8列创建并赋值
            rowList.add(sqlStat.getReadStringLength()+"");
            //第9列创建并赋值
            rowList.add(sqlStat.getExecuteSuccessCount()+"");
            //第10列创建并赋值
            rowList.add(sqlStat.getExecuteBatchSizeMax()+"");
            //第11列创建并赋值
            rowList.add("["+toString(sqlStat.getExecuteHistogram())+"]");
            //第12列创建并赋值
            rowList.add("["+toString(sqlStat.getExecuteAndResultHoldHistogram())+"]");

            //第13列创建并赋值
            rowList.add("["+toString(sqlStat.getFetchRowHistogram())+"]");

            //第14列创建并赋值
            rowList.add("["+toString(sqlStat.getUpdateHistogram())+"]");


            dataList.add(rowList);
        }

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPath+fileName+".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            int num = headList.size() / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append(",");
            }
            csvWtriter.write(buffer.toString() + fileName + buffer.toString());
            csvWtriter.newLine();

            // 写入文件头部
            writeRow(headList, csvWtriter);

            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 写一行数据
     * @param row 数据列表
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
//            String rowStr = sb.append("\"").append(data).append("\",").toString();
            String rowStr = sb.append(data).append(",").toString();

            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    /**
     * @Description 处理csv字符串中的逗号、双引号
     * @Method csvHandlerStr
     * @Params [str]
     * @Return java.lang.String
     * @Date 2020/8/21
     **/
    private String csvHandlerStr(String str) {

        //csv格式如果有逗号，整体用双引号括起来；如果里面还有双引号就替换成两个双引号，这样导出来的格式就不会有问题了
        String tempDescription = str;
        //如果有逗号
        if (str.contains(",")) {
            //如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误
            if (str.contains("\"")) {
                tempDescription = str.replace("\"", "\"\"");
            }
            //在将逗号转义
            tempDescription = "\"" + tempDescription + "\"";
        }
        return tempDescription;
    }
        private static String toString(long[] array) {
            if (array == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                if (i != 0) {
                    sb.append('、');
                }
                sb.append(array[i]);
            }
            return sb.toString();
        }
}
