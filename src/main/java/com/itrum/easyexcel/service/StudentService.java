package com.itrum.easyexcel.service;

import com.itrum.easyexcel.domain.Student;

import java.util.List;

public interface StudentService {
    void readExcel(List<Student> students);
}
