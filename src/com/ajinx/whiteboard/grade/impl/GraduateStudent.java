package com.ajinx.whiteboard.grade.impl;

import java.util.List;

import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;

public class GraduateStudent implements Student {
  private String name;
  private String id;
  private List<WorkItem> assignedWork;
  private int grade;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<WorkItem> getAssignedWork() {
    return assignedWork;
  }

  public void setAssignedWork(List<WorkItem> assignedWork) {
    this.assignedWork = assignedWork;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

}