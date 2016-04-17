package com.ajinx.whiteboard.io;

import java.io.IOException;

import com.ajinx.whiteboard.grade.GradeBook;

public interface Reader {
  static final String fileName = "input";
  static final String fileLocation = "data/";

  public GradeBook read() throws IOException;
}
