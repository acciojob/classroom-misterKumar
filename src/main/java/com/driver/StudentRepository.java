package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    //@Autowired
    HashMap<String,Student> studentRepo;
    //@Autowired
    HashMap<String,Teacher> teacherRepo;
    //@Autowired
    HashMap<String,ArrayList<String>> teacherStudentRepo;
    public StudentRepository(){
        studentRepo=new HashMap<>();
        teacherRepo=new HashMap<>();
        teacherStudentRepo=new HashMap<>();
    }
    public void addStudent(Student student){
        studentRepo.put(student.getName(),student);
    }
    public void addTeacher(Teacher teacher){
        teacherRepo.put(teacher.getName(), teacher);
    }
    public void addStudentTeacherPair(String student, String teacher){
        if(teacherStudentRepo.containsKey(teacher)){
            ArrayList<String> temp =teacherStudentRepo.get(teacher);
            temp.add(student);
            teacherStudentRepo.put(teacher,temp);
        }
        else{
            ArrayList<String> temp =new ArrayList<>();
            temp.add(student);
            teacherStudentRepo.put(teacher,temp);
        }
        Teacher teacherObj=teacherRepo.get(teacher);
        teacherObj.setNumberOfStudents(teacherObj.getNumberOfStudents()+1);

    }
    public Student getStudentByName(String name){
        return studentRepo.getOrDefault(name, null);
    }
    public Teacher getTeacherByName(String name){
        return teacherRepo.getOrDefault(name, null);
    }
    public List<String> getStudentsByTeacherName(String teacher){
        return teacherStudentRepo.getOrDefault(teacher,new ArrayList<>());
    }
    public List<String> getAllStudents(){
//        ArrayList<String> temp=new ArrayList<>();
//        for(String name:studentRepo.keySet())
//            temp.add(name);
//        return temp;
        return new ArrayList<>(studentRepo.keySet());
    }
    public void deleteTeacherByName(String teacher){
        //removing students who are assigned to given teacher also
        for(String name:teacherStudentRepo.get(teacher))
            studentRepo.remove(name);

        //removing teacher
        teacherRepo.remove(teacher);
        teacherStudentRepo.remove(teacher);
    }
    public void deleteAllTeachers(){
        //removing all students that are linked to teachers also
        for(String teacher:teacherRepo.keySet()){
            deleteTeacherByName(teacher);
            teacherRepo.remove(teacher);
        }
        // teacherRepo.clear();
        teacherStudentRepo.clear();
    }
}