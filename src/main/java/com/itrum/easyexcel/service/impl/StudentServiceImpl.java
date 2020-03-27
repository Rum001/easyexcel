package com.itrum.easyexcel.service.impl;

import com.itrum.easyexcel.domain.Student;
import com.itrum.easyexcel.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void readExcel(List<Student> students) {
        students.forEach(System.out::println);
    }
}
