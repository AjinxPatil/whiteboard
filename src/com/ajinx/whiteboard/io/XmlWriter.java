package com.ajinx.whiteboard.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradedWorkItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;
import com.ajinx.whiteboard.service.GradeService;

public class XmlWriter implements Writer {
  static final String fileName = "gradebook.xml";

  public GradeBook write(final GradeBook gradeBook) throws IOException {
    try {
      final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      final Document doc = docBuilder.newDocument();

      /*
       * Its better to store the xml tag strings in another constants file and
       * use those constants here. Due to my time constraint, that is skipped.
       */

      final Element gradeBookNode = doc.createElement("GradeBook");
      doc.appendChild(gradeBookNode);
      final Attr classAttr = doc.createAttribute("class");
      classAttr.setValue(gradeBook.getCourse());
      gradeBookNode.setAttributeNode(classAttr);

      final Element gradesNode = doc.createElement("Grades");
      for (Student student : gradeBook.getStudents()) {
        final Element studentNode = doc.createElement("Student");
        final Element studentNameNode = doc.createElement("Name");
        studentNameNode.appendChild(doc.createTextNode(student.getName()));
        studentNode.appendChild(studentNameNode);
        final Element studentIdNode = doc.createElement("ID");
        studentIdNode.appendChild(doc.createTextNode(student.getId()));
        studentNode.appendChild(studentIdNode);
        for (WorkItem item : student.getAssignedWork()) {
          final Element assignedWorkNode = doc.createElement("AssignedWork");
          final Attr workCategoryAttr = doc.createAttribute("category");
          workCategoryAttr.setValue(item.getCategory());
          assignedWorkNode.setAttributeNode(workCategoryAttr);
          for (GradedWorkItem gradedItem : item.getGradedWork()) {
            final Element gradedWorkNode = doc.createElement("GradedWork");
            final Element gradedWorkNameNode = doc.createElement("Name");
            gradedWorkNameNode.appendChild(doc.createTextNode(gradedItem.getName()));
            final Element gradeNode = doc.createElement("Grade");
            gradeNode.appendChild(doc.createTextNode(gradedItem.getGrade().getGrade()));
            gradedWorkNode.appendChild(gradedWorkNameNode);
            gradedWorkNode.appendChild(gradeNode);
            assignedWorkNode.appendChild(gradedWorkNode);
          }
          studentNode.appendChild(assignedWorkNode);
        }
        final Element finalGradeNode = doc.createElement("FinalGrade");
        final String finalGrade = GradeService.calculateFinalGrade(student, gradeBook.getGradingSchema());
        finalGradeNode.appendChild(doc.createTextNode(finalGrade));
        studentNode.appendChild(finalGradeNode);
        gradesNode.appendChild(studentNode);
        gradeBookNode.appendChild(gradesNode);
      }
      final TransformerFactory transformerFactory = TransformerFactory.newInstance();
      final Transformer transformer = transformerFactory.newTransformer();
      final DOMSource source = new DOMSource(doc);
      final StreamResult result = new StreamResult(new File(fileLocation + fileName));
      transformer.transform(source, result);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
    return null;
  }
}
