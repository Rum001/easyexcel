package com.itrum.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.itrum.easyexcel.domain.Student;

public class StudentListener extends AnalysisEventListener<Student> {

    //每次调用监听器都会执行invoke方法
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student = " + student);
    }
    //读取完之后调用的方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
