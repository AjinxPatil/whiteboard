package com.ajinx.whiteboard.grade.impl;

import java.util.List;

import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.Student;

public class GraduateGradeBook implements GradeBook {
  private String course;
  private List<GradeItem> gradingSchema;
  private List<Student> students;

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public List<GradeItem> getGradingSchema() {
    return gradingSchema;
  }

  public void setGradingSchema(List<GradeItem> gradingSchema) {
    this.gradingSchema = gradingSchema;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
