package br.com.devdojo.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.model.Student;
import br.com.devdojo.error.CumstomErrorType;
// import br.com.devdojo.util.DateUtil;

// import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentControllers {

    // @Autowired
    // private DateUtil dateUtil;
    private List<Student> students = new ArrayList<>();

    @GetMapping("/list")
    public ResponseEntity<?> listAll() {
        // System.out.println("Date now
        // "+dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(this.students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Optional<Student> studentFind = students.stream().filter(student -> student.getId() == id).findFirst();

        if (studentFind.isPresent()) {
            return new ResponseEntity<>(studentFind, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CumstomErrorType("Student not fount"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<?> register(@RequestBody Student student) {
        this.students.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {

        this.students.removeIf(student -> student.getId() == id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
