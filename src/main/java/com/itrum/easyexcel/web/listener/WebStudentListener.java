package com.itrum.easyexcel.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.itrum.easyexcel.domain.Student;
import com.itrum.easyexcel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<Student> {

    @Autowired
    private StudentService studentService;


    List<Student> students = new ArrayList<>();

    @Override
    public void invoke(Student student, AnalysisContext context) {
        students.add(student);
        //做一个优化处理 没读取5个 存入数据库 减少数据的访问压力
        if (students.size()%5==0){
            //存入
            studentService.readExcel(students);
            //然后清空
            students.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
