package br.com.devdojo.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;
import br.com.devdojo.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentControllers {

    @Autowired
    private DateUtil dateUtil;
    private List<Student> students = new ArrayList<>();

    @RequestMapping("/list")
    public ResponseEntity<?> listAll() {
        // System.out.println("Date now
        // "+dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(this.students, HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Optional<Student> studentFind = students.stream().filter(student -> student.getId() == id).findFirst();

        if (studentFind.isPresent()) {
            return new ResponseEntity<>(studentFind, HttpStatus.OK);
        }

        return new ResponseEntity<>("Não foi possivel localizar estudante", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody Student student) {
        this.students.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
