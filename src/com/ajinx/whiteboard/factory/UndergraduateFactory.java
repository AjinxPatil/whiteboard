package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;
import com.ajinx.whiteboard.grade.impl.UndergraduateGradeBook;
import com.ajinx.whiteboard.grade.impl.UndergraduateGradeItem;
import com.ajinx.whiteboard.grade.impl.UndergraduateStudent;
import com.ajinx.whiteboard.grade.impl.UndergraduateWorkItem;

/**
 * Factory class for undergraduate course
 * @author Ajinkya Patil
 *
 */
public class UndergraduateFactory implements GradeFactory {

  // Singleton
  private static UndergraduateFactory undergraduateFactory = new UndergraduateFactory();

  private UndergraduateFactory() {
  }

  public static UndergraduateFactory getInstance() {
    return undergraduateFactory;
  }

  public GradeBook createGradeBook() {
    return new UndergraduateGradeBook();
  }

  public GradeItem createGradeItem() {
    return new UndergraduateGradeItem();
  }

  public Student createStudent() {
    return new UndergraduateStudent();
  }

  public WorkItem createWorkItem() {
    return new UndergraduateWorkItem();
  }
}
