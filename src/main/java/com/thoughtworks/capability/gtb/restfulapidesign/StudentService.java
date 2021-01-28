package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.Entity.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.Entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<Student>(){{
        add(new Student(1, "张三", "男", ""));
        add(new Student(2, "李四", "女", ""));
        add(new Student(3, "赵五", "女", ""));
        add(new Student(4, "孙六", "男", ""));
        add(new Student(5, "钱七", "男", ""));
        add(new Student(6, "王八", "女", ""));
    }};

    private final List<Group> groups = new ArrayList<Group>(){{
        add(new Group("1 组"));
        add(new Group("2 组"));
        add(new Group("3 组"));
        add(new Group("4 组"));
        add(new Group("5 组"));
        add(new Group("6 组"));
    }};

    public List<Student> getAllStudents() {
        return students;
    }

    public List<Student> getAllStudentsByGender(String gender) {
        return students.stream().filter(x -> x.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Student getStudentById(int id) {
        return students.stream().filter(x -> x.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void add(Student student) {
        students.add(student);
    }

    public void updateStudentById(int id, Student student) {
        Student student1 = students.stream().filter(x -> x.getId() == id).collect(Collectors.toList()).get(0);
        if(student.getName() != null) {
            student1.setName(student.getName());
        }
        if(student.getGender() != null) {
            student1.setGender(student.getGender());
        }
        if(student.getNote() != null) {
            student1.setGender(student.getNote());
        }
    }

    public void deleteStudentById(int id) {
        Student student = students.stream().filter(x -> x.getId() == id).collect(Collectors.toList()).get(0);
        students.remove(student);
    }

    public boolean isStudentExist(int id) {
        return students.stream().anyMatch(x -> x.getId() == id);
    }

    public List<Group> getAllGroups() {
        return groups;
    }

    public void updateGroupNameById(int id, String name) {
        Group group = groups.get(id);
        group.setName(name);
        groups.set(id, group);
    }

    public void changeGroupPartition() {
        for(Group group:groups) {
            group.setMembers(new ArrayList<Integer>());
        }
        List<Student> shuffledStudents = new ArrayList<Student>(students);
        Collections.shuffle(shuffledStudents);
        int i = 0;
        for(Student student: shuffledStudents) {
            List<Integer> members = groups.get(i).getMembers();
            members.add(student.getId());
            i = (i+1)%6;
        }
    }


}
