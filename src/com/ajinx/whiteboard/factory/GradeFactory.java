package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;

/**
 * The abstract factory
 * @author Ajinkya Patil
 *
 */
public interface GradeFactory {
  GradeBook createGradeBook();

  GradeItem createGradeItem();

  Student createStudent();

  WorkItem createWorkItem();
}
