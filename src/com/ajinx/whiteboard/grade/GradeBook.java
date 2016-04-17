package com.ajinx.whiteboard.grade;

import java.util.List;

public interface GradeBook {
  String getCourse();

  void setCourse(String course);

  List<GradeItem> getGradingSchema();

  void setGradingSchema(List<GradeItem> gradingSchema);

  List<Student> getStudents();

  void setStudents(List<Student> students);
}
