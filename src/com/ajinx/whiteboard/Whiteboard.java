package com.ajinx.whiteboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.factory.ReaderFactory;
import com.ajinx.whiteboard.factory.WriterFactory;
import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.io.Reader;
import com.ajinx.whiteboard.io.Writer;

/**
 * The main class
 * @author Ajinkya Patil
 *
 */
public class Whiteboard {
  public static void main(String[] args) {
    System.out.println("Whiteboard");
    System.out.println("----------");
    System.out.println("Grade books - Select the level:");
    System.out.println(
        WhiteboardConstants.LEVEL_UNDERGRAD + ". Undergraduate\t\t" + WhiteboardConstants.LEVEL_GRAD + ". Graduate");
    try {
      final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      final String opt = br.readLine();
      System.out.println("Gathering data...");
      final Reader reader = ReaderFactory.createReader(opt);
      final GradeBook gradeBook = reader.read();
      System.out.println("Grade book is ready. Generating...");
      // TODO other output formats
      final Writer writer = WriterFactory.createWriter(WhiteboardConstants.OUT_XML);
      System.out.println("Grade book is generated at the default project folder in output directory.");
      writer.write(gradeBook);
    } catch (IOException e) {
      System.out.println("ERROR: IOException");
      e.printStackTrace();
    }
  }
}