package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;
import com.ajinx.whiteboard.grade.impl.GraduateGradeBook;
import com.ajinx.whiteboard.grade.impl.GraduateGradeItem;
import com.ajinx.whiteboard.grade.impl.GraduateStudent;
import com.ajinx.whiteboard.grade.impl.GraduateWorkItem;

/**
 * Factory class for graduate course
 * @author Ajinkya Patil
 *
 */
public class GraduateFactory implements GradeFactory {

  // Singleton
  private static GraduateFactory graduateFactory = new GraduateFactory();

  private GraduateFactory() {
  }

  public static GraduateFactory getInstance() {
    return graduateFactory;
  }

  public GradeBook createGradeBook() {
    return new GraduateGradeBook();
  }

  public GradeItem createGradeItem() {
    return new GraduateGradeItem();
  }

  public Student createStudent() {
    return new GraduateStudent();
  }

  public WorkItem createWorkItem() {
    return new GraduateWorkItem();
  }
}
