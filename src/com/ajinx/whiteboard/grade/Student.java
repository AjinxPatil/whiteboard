package com.ajinx.whiteboard.grade;

import java.util.List;

public interface Student {
  String getName();

  void setName(String name);

  String getId();

  void setId(String id);

  List<WorkItem> getAssignedWork();

  void setAssignedWork(List<WorkItem> assignedWork);

  int getGrade();

  void setGrade(int grade);
}
