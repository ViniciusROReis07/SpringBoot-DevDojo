package br.com.devdojo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;
import java.util.ArrayList;

@RestController
@RequestMapping("student")
public class StudentControllers {

    private List<Student> students = new ArrayList<>();

    public List<Student> listAll() {
        return this.students;
    }
}