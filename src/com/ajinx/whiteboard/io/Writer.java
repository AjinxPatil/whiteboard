package com.ajinx.whiteboard.io;

import java.io.IOException;

import com.ajinx.whiteboard.grade.GradeBook;

public interface Writer {
  static final String fileName = "input";
  static final String fileLocation = "output/";

  public GradeBook write(final GradeBook gradeBook) throws IOException;
}
