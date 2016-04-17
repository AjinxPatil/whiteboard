package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.io.JsonReader;
import com.ajinx.whiteboard.io.Reader;
import com.ajinx.whiteboard.io.XmlReader;

/**
 * Factory class for readers
 * @author Ajinkya Patil
 *
 */
public class ReaderFactory {
  public static Reader createReader(final String level) {
    if (WhiteboardConstants.LEVEL_UNDERGRAD.equals(level)) {
      return new JsonReader();
    } else if (WhiteboardConstants.LEVEL_GRAD.equals(level)) {
      return new XmlReader();
    }
    System.out.println("ERROR: " + level + " is not a valid level!");
    return null;
  }
}