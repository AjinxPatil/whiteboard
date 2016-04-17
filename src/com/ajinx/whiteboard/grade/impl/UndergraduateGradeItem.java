package com.ajinx.whiteboard.grade.impl;

import com.ajinx.whiteboard.grade.GradeItem;

public class UndergraduateGradeItem implements GradeItem {
  private String category;
  private int percentage;

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getPercentage() {
    return percentage;
  }

  public void setPercentage(int percentage) {
    this.percentage = percentage;
  }

}
