package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName().trim(), student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        teacherMap.put(teacher.getName().trim(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student.trim()) && teacherMap.containsKey(teacher.trim())){
            // your code goes here
            List<String> students = teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
            students.add(student.trim());
            teacherStudentMapping.put(teacher.trim(), students);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.get(student.trim());
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher.trim());
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher.trim(), new ArrayList<>());
    }

    public List<String> findAllStudents(){
        // your code goes here
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherMap.remove(teacher.trim());
        teacherStudentMapping.remove(teacher.trim());
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherMap.clear();
        teacherStudentMapping.clear();
    }
}