package br.com.devdojo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;
import br.com.devdojo.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("student")
public class StudentControllers {

    @Autowired
    private DateUtil dateUtil;
    private List<Student> students = new ArrayList<>();

    @RequestMapping("/list")
    public List<Student> listAll() {
        System.out.println("Date now "+dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return this.students;
    }

    @RequestMapping("/cadastrar")
    public Student cadastrar(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }
}
