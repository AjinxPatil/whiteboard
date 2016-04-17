package com.ajinx.whiteboard.grade.impl;

import java.util.List;

import com.ajinx.whiteboard.grade.GradedWorkItem;
import com.ajinx.whiteboard.grade.WorkItem;

public class UndergraduateWorkItem implements WorkItem {
  private String category;
  private List<GradedWorkItem> gradedWork;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public List<GradedWorkItem> getGradedWork() {
    return gradedWork;
  }

  public void setGradedWork(List<GradedWorkItem> gradedWork) {
    this.gradedWork = gradedWork;
  }
}
