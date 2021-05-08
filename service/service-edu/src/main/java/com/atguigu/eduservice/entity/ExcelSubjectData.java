package com.atguigu.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelSubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;


    //第二列
    @ExcelProperty(index = 1)
    private String twoSubjectName;

    public String getOneSubjectName() {
        return oneSubjectName;
    }

    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getTwoSubjectName() {
        return twoSubjectName;
    }

    public void setTwoSubjectName(String twoSubjectName) {
        this.twoSubjectName = twoSubjectName;
    }


    @Override
    public String toString() {
        return "ExcelSubjectData{" +
                "oneSubjectName=" + oneSubjectName +
                ", twoSubjectName='" + twoSubjectName + '\'' +
                '}';
    }
}
