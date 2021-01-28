package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.Entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.Entity.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.Exceptions.StudentExistsException;
import com.thoughtworks.capability.gtb.restfulapidesign.Exceptions.StudentNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import java.util.List;

@RestController
@Validated
public class ManagementSystemController {

    private StudentService studentService;

    public ManagementSystemController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(@RequestParam(name="gender", required=false) String gender) {
        if(gender == null) {
            return studentService.getAllStudents();
        } else {
            return studentService.getAllStudentsByGender(gender);
        }
    }

    @GetMapping("/student/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable int id) {
        if(!studentService.isStudentExist(id)) {
            throw new StudentNotExistsException("id not exists.");
        }
        return studentService.getStudentById(id);
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody @Valid Student student) {
        if(studentService.isStudentExist(student.getId())) {
            throw new StudentExistsException("same id exists.");
        }
        studentService.add(student);
    }

    @DeleteMapping("/student/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable int id) {
        if(!studentService.isStudentExist(id)) {
            throw new StudentNotExistsException("id not exists.");
        }
        studentService.deleteStudentById(id);
    }

    @PutMapping("/student/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudentById(@PathVariable int id, @RequestBody @Valid Student student) {
        if(!studentService.isStudentExist(id)) {
            throw new StudentNotExistsException("id not exists.");
        }
        studentService.updateStudentById(id, student);
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<Group> getAllGroups() {
        return studentService.getAllGroups();
    }

    @PutMapping("/group/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGroupNameById(@PathVariable @Min(1) @Max(6) int id, @RequestBody @Valid Group group) {
        studentService.updateGroupNameById(id-1, group.getName());
    }

    @PostMapping("/groups/partition")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGroupPartition() {
        studentService.changeGroupPartition();
    }
}
