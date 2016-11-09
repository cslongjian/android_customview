package com.ycf.chenlongjian.exceldemo;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IntegerField;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by chenlongjian on 2016/9/14.
 */


public class ReadExcel {

    public static void main(String[] args) {

//        String Path = "E:\\android_git\\android_customview\\ExcelDemo\\app\\src\\main\\java\\com\\ycf\\chenlongjian\\exceldemo\\20070920.xls";
        String Path = "E:\\android_git\\android_customview\\ExcelDemo\\app\\src\\main\\java\\com\\ycf\\chenlongjian\\exceldemo\\20071025.xls";

        System.out.println("测试输出");
        int Arraymark[] = new int[1100];
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int countAll = 0;
        try {
            System.out.println("测试--读取");

            FileInputStream fileIn = new FileInputStream(Path);
            Workbook wb0 = new HSSFWorkbook(fileIn);

            Sheet sht0 = wb0.getSheetAt(1);

            System.out.println("一共多少行" + sht0.getLastRowNum());




            for (Row r : sht0) {
                //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
                if (r.getRowNum() < 1) {
                    continue;
                }
//                 System.out.println("-----------------测试--读取------------------");
//                 System.out.print("红ID"+r.getCell(0).getNumericCellValue()+"----");
//                 System.out.print("诸葛ID"+r.getCell(2).getNumericCellValue());
//                 System.out.println("属性"+r.getCell(5).getStringCellValue());


//                 读取0 ，2 ，5 第0个有，第2个有，第5个没的条件下才打印出来。
//                StringBuilder stringBuilder = new StringBuilder();
////                 mJavaIDToZhuGeEventID.put(1l,1l);
//                stringBuilder.append("mJavaIDToZhuGeEventID.put(");
////                 stringBuilder.append(r.getCell(2).getNumericCellValue());
////                 stringBuilder.append("L,");
//                if ((r.getCell(0).getNumericCellValue() != 0.0) && (r.getCell(5).getStringCellValue().equals(""))) {
////
//                    stringBuilder.append(r.getCell(0).getNumericCellValue());
//                    stringBuilder.append("L,");
//                    stringBuilder.append(r.getCell(2).getNumericCellValue());
//                    stringBuilder.append("L");
//                    stringBuilder.append(");");
//                    System.out.println(stringBuilder.toString());
//
////                     过滤重复出现的
//                    int mark = (int) r.getCell(0).getNumericCellValue();
//                    Arraymark[mark] = Arraymark[mark] + 1;
//
//                    if (Arraymark[mark]>2)
//                    {
//                        arrayList.add((int)r.getCell(2).getNumericCellValue());
//                    }
//                    countAll = countAll+1;
//
//                }


                 StringBuilder stringBuilder = new StringBuilder();

//                 //------正确读取 导出MAP    注意数对。列数的位置。。
                 stringBuilder.append("mZhuGeNewEventMapWithValue.put(");
                 stringBuilder.append(r.getCell(2).getNumericCellValue());
                 stringBuilder.append("L,");
                 stringBuilder.append("new String[] { \"");
                 stringBuilder.append(r.getCell(3).getStringCellValue()+"\"");


                 if (r.getCell(5).getStringCellValue()!=null&&!r.getCell(5).getStringCellValue().equals(""))
                 {
                     stringBuilder.append(",\""+r.getCell(5).getStringCellValue()+"\"");
                 }

                 if (r.getCell(7).getStringCellValue()!=null&&!r.getCell(7).getStringCellValue().equals(""))
                 {
                     stringBuilder.append(",\""+r.getCell(7).getStringCellValue()+"\"");
                 }

                 if (r.getCell(9).getStringCellValue()!=null&&!r.getCell(9).getStringCellValue().equals(""))
                 {
                     stringBuilder.append(",\""+r.getCell(9).getStringCellValue()+"\"");
                 }

                 if (r.getCell(11).getStringCellValue()!=null&&!r.getCell(11).getStringCellValue().equals(""))
                 {
                     stringBuilder.append(",\""+r.getCell(11).getStringCellValue()+"\"");
                 }

                 stringBuilder.append("});");

                 System.out.println(stringBuilder.toString());
//                 //------正确读取
//                 new String[] { "aaa" });

                //导出输出ID
//                StringBuilder stringBuilder2 = new StringBuilder();
//                stringBuilder2.append("public static final long ZG_EVENT_ID_");
//                stringBuilder2.append(r.getCell(2).getNumericCellValue());
//                stringBuilder2.append("=");
//                stringBuilder2.append(r.getCell(2).getNumericCellValue());
//                stringBuilder2.append(";//");
//                stringBuilder2.append(r.getCell(3).getStringCellValue());
//                 System.out.println(stringBuilder2.toString());
                //输出ID
//                public static final long EVENT_ID_394 = 394;
//


            }





        } catch (Exception e) {

            System.out.println(e.toString());
            int count = 0;
            for (int i = 0 ;i<Arraymark.length;i++) {
                if (Arraymark[i]>1) {
                    System.out.println("重复出现的数据："+i+"  出现次数"+Arraymark[i]);
                    count = count+Arraymark[i];
                }
            }
            System.out.println("处理的总数："+countAll);
            System.out.println("重复的总数："+count);
            System.out.println("最后真实的处理的总数："+(countAll-count));


        }
    }
}
