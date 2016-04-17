package com.ajinx.whiteboard.grade;

import java.util.List;

public interface WorkItem {
  String getCategory();

  void setCategory(String category);

  List<GradedWorkItem> getGradedWork();

  void setGradedWork(List<GradedWorkItem> gradedWork);
}
