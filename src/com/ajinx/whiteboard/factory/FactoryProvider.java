package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.etc.WhiteboardConstants;

/**
 * The factory provider class
 * @author Ajinkya Patil
 *
 */
public class FactoryProvider {
  public static GradeFactory createFactory(final String level) {
    if (WhiteboardConstants.LEVEL_UNDERGRAD.equals(level)) {
      return UndergraduateFactory.getInstance();
    } else if (WhiteboardConstants.LEVEL_GRAD.equals(level)) {
      return GraduateFactory.getInstance();
    }
    return null;
  }
}
