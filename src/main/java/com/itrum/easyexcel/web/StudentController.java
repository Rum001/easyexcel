package com.itrum.easyexcel.web;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.itrum.easyexcel.domain.Student;
import com.itrum.easyexcel.listener.StudentListener;
import com.itrum.easyexcel.web.listener.WebStudentListener;
import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private WebStudentListener webStudentListener;

    /**
     * 文件上传
     *
     * @param uploadFile
     * @return
     */
    @PostMapping("upload")
    public String upload(MultipartFile uploadFile) {
        //获取文件的输入流
        try {
            InputStream inputStream = uploadFile.getInputStream();
            //创建工作簿对象
            ExcelReaderBuilder readWorkBook = EasyExcel.read(inputStream,
                    Student.class, webStudentListener);
            //创建工作表对象
            ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
            //读取
            sheet.doRead();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 文件下载
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        //防止中文乱码
        String filename = URLEncoder.encode("测试.xlsm", "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=" + filename);
        //获取响应体的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //创建工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);
        //创建工作表对象
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        //写入
        sheet.doWrite(initData());
    }

    public List<Student> initData() {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setAge(1 + i);
            student.setBirthday(new Date());
            student.setName("itrum0" + i);
            students.add(student);
        }
        return students;
    }

}
