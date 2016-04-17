package com.ajinx.whiteboard.factory;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.io.CsvWriter;
import com.ajinx.whiteboard.io.HtmlWriter;
import com.ajinx.whiteboard.io.Writer;
import com.ajinx.whiteboard.io.XmlWriter;

/**
 * Factory class for writers
 * @author Ajinkya Patil
 *
 */
public class WriterFactory {
  public static Writer createWriter(final String option) {
    if (WhiteboardConstants.OUT_CSV.equals(option)) {
      return new CsvWriter();
    } else if (WhiteboardConstants.OUT_HTML.equals(option)) {
      return new HtmlWriter();
    } else if (WhiteboardConstants.OUT_XML.equals(option)) {
      return new XmlWriter();
    }
    return null;
  }
}
