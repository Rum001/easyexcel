package com.itrum.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
    /**
     * 学生姓名
     */
    @ExcelProperty(value = {"学员信息表","学生姓名"})
    private String name;

    /**
     * 学生年龄
     */
    @ExcelProperty(value = {"学员信息表","学生年龄"})
    private Integer age;
    /**
     * 学生生日
     */
    @ExcelProperty(value = {"学员信息表","学生生日"})
    @ColumnWidth(25)
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;
    /**
     * 学生Id
     */
    @ExcelIgnore
    private String id;
}
