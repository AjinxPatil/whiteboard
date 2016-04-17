package com.ajinx.whiteboard.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.factory.FactoryProvider;
import com.ajinx.whiteboard.factory.GradeFactory;
import com.ajinx.whiteboard.grade.GradeBook;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.GradedWorkItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;
import com.ajinx.whiteboard.service.GradeService;

public class JsonReader implements Reader {
  static final String fileName = "ungrad_data.json";

  public GradeBook read() throws IOException {
    // The factory
    final GradeFactory factory = FactoryProvider.createFactory(WhiteboardConstants.LEVEL_UNDERGRAD);

    // Gradebook
    final GradeBook book = factory.createGradeBook();

    try {
      JSONParser parser = new JSONParser();
      JSONObject json = (JSONObject) parser.parse(new FileReader(fileLocation + fileName));

      final JSONObject gradeBook = (JSONObject) json.get("GradeBook");

      // Class
      book.setCourse(gradeBook.get("-class").toString());

      // GradingSchema
      final JSONObject gradingSchema = (JSONObject) gradeBook.get("GradingSchema");
      final JSONArray gradeItem = (JSONArray) gradingSchema.get("GradeItem");
      final List<GradeItem> gradeItems = new ArrayList<GradeItem>();
      for (Object i : gradeItem) {
        // GradeItem
        JSONObject ijson = (JSONObject) i;
        final GradeItem item = factory.createGradeItem();
        item.setCategory(ijson.get("Category").toString());
        item.setPercentage(Integer.parseInt(ijson.get("Percentage").toString()));
        gradeItems.add(item);
      }
      book.setGradingSchema(gradeItems);

      final JSONObject grades = (JSONObject) gradeBook.get("Grades");
      // Student
      final JSONArray student = (JSONArray) grades.get("Student");
      final List<Student> students = new ArrayList<Student>();
      for (Object i : student) {
        JSONObject ijson = (JSONObject) i;
        final Student stud = factory.createStudent();
        // Name
        stud.setName(ijson.get("Name").toString());
        // ID
        stud.setId(ijson.get("ID").toString());

        final JSONArray assignedWork = (JSONArray) ijson.get("AssignedWork");
        final List<WorkItem> workItems = new ArrayList<WorkItem>();
        for (Object j : assignedWork) {
          JSONObject jjson = (JSONObject) j;
          final WorkItem item = factory.createWorkItem();
          // Category
          item.setCategory(jjson.get("-category").toString());
          final JSONArray gradedWork = (JSONArray) jjson.get("GradedWork");
          final List<GradedWorkItem> gradedItems = new ArrayList<GradedWorkItem>();
          for (Object k : gradedWork) {
            JSONObject kjson = (JSONObject) k;
            final GradedWorkItem gradedItem = new GradedWorkItem();
            // Name
            gradedItem.setName(kjson.get("Name").toString());
            // Grade
            int type = GradeService.identifyGradeType(kjson.get("Grade").toString());

            gradedItem.setGrade(type, kjson.get("Grade").toString());
            gradedItems.add(gradedItem);
          }
          item.setGradedWork(gradedItems);
          workItems.add(item);
        }
        stud.setAssignedWork(workItems);
        students.add(stud);
      }
      book.setStudents(students);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return book;
  }
}
