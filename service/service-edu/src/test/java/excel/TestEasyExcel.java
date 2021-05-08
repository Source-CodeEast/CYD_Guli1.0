package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) throws Exception{
        String fileName = "D:\\11.xlsx";
        //写入操作
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("写入方法二").build();
//        excelWriter.write(data(), writeSheet);
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();

        //读操作
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> data(){

        List<DemoData> list = new ArrayList<DemoData>();
        for (int i=0;i<10;i++){
            DemoData data = new DemoData();
            data.setSname("zen"+i);
            data.setSno(i);
            list.add(data);
        }
        return list;
    }

}
