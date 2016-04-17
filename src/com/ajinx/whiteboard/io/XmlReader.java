package com.ajinx.whiteboard.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.factory.FactoryProvider;
import com.ajinx.whiteboard.factory.GradeFactory;
import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.GradedWorkItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;
import com.ajinx.whiteboard.service.GradeService;

public class XmlReader implements Reader {
  static final String fileName = "grad_data.xml";

  public GradeBook read() throws IOException {
    // The factory
    final GradeFactory factory = FactoryProvider.createFactory(WhiteboardConstants.LEVEL_GRAD);

    // Gradebook
    final GradeBook book = factory.createGradeBook();

    try {
      final File xmlFile = new File(fileLocation + fileName);
      final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      final Document doc = dBuilder.parse(xmlFile);

      doc.getDocumentElement().normalize();

      final Element gradeBookElem = doc.getDocumentElement();
      book.setCourse(gradeBookElem.getAttribute("class"));

      final List<GradeItem> gradingSchema = new ArrayList<GradeItem>();
      final NodeList gradeItemNodes = doc.getElementsByTagName("GradeItem");
      for (int i = 0; i < gradeItemNodes.getLength(); i++) {
        final Node gradeItemNode = gradeItemNodes.item(i);

        final Element gradeItemElem = (Element) gradeItemNode;
        final GradeItem gradeItem = factory.createGradeItem();
        gradeItem.setCategory(gradeItemElem.getElementsByTagName("Category").item(0).getTextContent());
        gradeItem
            .setPercentage(Integer.parseInt(gradeItemElem.getElementsByTagName("Percentage").item(0).getTextContent()));
        gradingSchema.add(gradeItem);

      }
      book.setGradingSchema(gradingSchema);

      final List<Student> students = new ArrayList<Student>();
      final NodeList studentNodes = doc.getElementsByTagName("Student");
      for (int i = 0; i < studentNodes.getLength(); i++) {
        final Node studentNode = studentNodes.item(i);
        final Element studentElem = (Element) studentNode;
        final Student student = factory.createStudent();
        student.setName(studentElem.getElementsByTagName("Name").item(0).getTextContent());
        student.setId(studentElem.getElementsByTagName("ID").item(0).getTextContent());

        final List<WorkItem> assignedWork = new ArrayList<WorkItem>();
        final NodeList assignedWorkNodes = studentElem.getElementsByTagName("AssignedWork");
        for (int j = 0; j < assignedWorkNodes.getLength(); j++) {
          final Node assignedWorkNode = assignedWorkNodes.item(j);
          final Element assignedWorkElem = (Element) assignedWorkNode;
          final WorkItem workItem = factory.createWorkItem();
          workItem.setCategory(assignedWorkElem.getAttribute("category"));

          final List<GradedWorkItem> gradedWork = new ArrayList<GradedWorkItem>();
          final NodeList gradedWorkNodes = assignedWorkElem.getElementsByTagName("GradedWork");
          for (int k = 0; k < gradedWorkNodes.getLength(); k++) {
            final Node gradedWorkNode = gradedWorkNodes.item(k);
            final Element gradedWorkElem = (Element) gradedWorkNode;
            final GradedWorkItem gradedItem = new GradedWorkItem();
            gradedItem.setName(gradedWorkElem.getElementsByTagName("Name").item(0).getTextContent());
            int type = GradeService
                .identifyGradeType(gradedWorkElem.getElementsByTagName("Grade").item(0).getTextContent());

            gradedItem.setGrade(type, gradedWorkElem.getElementsByTagName("Grade").item(0).getTextContent());
            gradedWork.add(gradedItem);
          }
          workItem.setGradedWork(gradedWork);
          assignedWork.add(workItem);
        }
        student.setAssignedWork(assignedWork);
        students.add(student);
      }
      book.setStudents(students);
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }
    return book;
  }
}
