package com.ajinx.whiteboard.grade;

public class GradedWorkItem {
  public class Grade {
    private int type;
    private String grade;

    public int getType() {
      return type;
    }

    public void setType(int type) {
      this.type = type;
    }

    public String getGrade() {
      return grade;
    }

    public void setGrade(String grade) {
      this.grade = grade;
    }
  }

  private String name;
  private Grade grade;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Grade getGrade() {
    return grade;
  }

  public void setGrade(int type, String value) {
    Grade grade = new Grade();
    grade.setType(type);
    grade.setGrade(value);
    this.grade = grade;
  }

}
