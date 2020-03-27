package com.itrum.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.itrum.easyexcel.domain.Student;
import com.itrum.easyexcel.domain.TestStudent;
import com.itrum.easyexcel.listener.StudentListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyexcelApplicationTests {
    /**
     * excel的读取
     */
    @Test
    public void test01() {
        //写入的路径
        String path = "学员信息文件-度.xlsm";
        //创建工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read(path,
                Student.class, new StudentListener());
        //创建工作表对象
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
        //读取
        sheet.doRead();
    }

    /**
     * excel的写入
     */
    @Test
    public void test02() {
        //写入的路径
        String path = "学员信息文件-度.xlsm";

        //创建工作簿对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(path, Student.class);
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

    public List<TestStudent> initData2() {
        List<TestStudent> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestStudent student = new TestStudent();
            student.setAge(1 + i);
            student.setName("itrum0" + i);
            students.add(student);
        }
        return students;
    }

    /**
     * 单组填充
     */
    @Test
    public void test03() {
        //获取模板
        String template = "fill_data_template1.xlsm";
        //创建工作簿对象
        ExcelWriterBuilder writerWorkBook = EasyExcel.write("学员信息文件-单组.xlsm", TestStudent.class).withTemplate(template);
        //创建工作表对象
        ExcelWriterSheetBuilder sheet = writerWorkBook.sheet();
        //准备数据
        TestStudent testStudent = new TestStudent("zs", 15);
        //写入
        sheet.doFill(testStudent);
    }

    /**
     * 多组填充
     */
    @Test
    public void test04() {
        //获取模板
        String template = "fill_data_template2.xlsm";
        //创建工作簿对象
        ExcelWriterBuilder writerWorkBook = EasyExcel.write("学员信息文件-多组.xlsm", TestStudent.class).withTemplate(template);
        //创建工作表对象
        ExcelWriterSheetBuilder sheet = writerWorkBook.sheet();
        //准备数据
        List<TestStudent> testStudents = initData2();
        //写入
        sheet.doFill(testStudents);
    }

    /**
     * 组合填充
     */
    @Test
    public void test05() {
        //获取模板
        String template = "fill_data_template3.xlsm";
        //创建工作簿对象
        ExcelWriter workBook = EasyExcel.write("学员信息文件-组合.xlsm", TestStudent.class).withTemplate(template).build();
        //换行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();
        //创建工作表对象
        WriteSheet build = EasyExcel.writerSheet().build();
        //准备数据
        List<TestStudent> testStudents = initData2();
        //单组数据
        Map<String, String> map = new HashMap<>();
        map.put("date", "2020-5-12");
        map.put("total", "1000");
        //写入多组
        workBook.fill(testStudents,fillConfig, build);
        //写入单组
        workBook.fill(map, build);
        //关闭流
        workBook.finish();
    }
    /**
     * 水平填充
     */
    @Test
    public void test06() {
        //获取模板
        String template = "fill_data_template4.xlsm";
        //创建工作簿对象
        ExcelWriter workBook = EasyExcel.write("学员信息文件-水平.xlsm", TestStudent.class).withTemplate(template).build();
        //换行
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
        //创建工作表对象
        WriteSheet build = EasyExcel.writerSheet().build();
        //单组数据
        Map<String, String> map = new HashMap<>();
        map.put("name", "zs");
        map.put("age", "10");
        //写入单组
        workBook.fill(map, fillConfig,build);
        //关闭流
        workBook.finish();
    }
}
