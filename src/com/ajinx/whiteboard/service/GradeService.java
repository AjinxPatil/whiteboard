package com.ajinx.whiteboard.service;

import java.util.List;

import com.ajinx.whiteboard.etc.WhiteboardConstants;
import com.ajinx.whiteboard.grade.GradeItem;
import com.ajinx.whiteboard.grade.GradedWorkItem;
import com.ajinx.whiteboard.grade.Student;
import com.ajinx.whiteboard.grade.WorkItem;

/**
 * Service class for providing functionality to models
 * @author Ajinkya Patil
 *
 */
public class GradeService {
  public static String calculateFinalGrade(final Student student, final List<GradeItem> gradingSchema) {
    boolean isLetterGradeCalc = false;
    final List<WorkItem> workItems = student.getAssignedWork();
    double finalScore = 0;
    for (GradeItem i : gradingSchema) {
      for (WorkItem j : workItems) {
        double score = 0;
        double count = 0;
        if (i.getCategory().equals(j.getCategory())) {
          for (GradedWorkItem k : j.getGradedWork()) {
            int marks = 0;
            if (WhiteboardConstants.GRADE_LETTER == k.getGrade().getType()) {
              isLetterGradeCalc = true;
              marks = GradeService.convertLetterGradeToNumeric(k.getGrade().getGrade());
            } else {
              marks = Integer.parseInt(k.getGrade().getGrade());
            }
            score += marks;
            count++;
          }
          finalScore += score / (count * 100) * ((double) i.getPercentage() / 100) * 100;
        }
      }
    }
    int finalGrade = (int) finalScore;
    if (isLetterGradeCalc) {
      if (finalGrade >= WhiteboardConstants.GRADE_A_PLUS) {
        return "A+";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_A) {
        return "A";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_A_MINUS) {
        return "A-";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_B_PLUS) {
        return "B+";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_B) {
        return "B";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_B_MINUS) {
        return "B-";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_C_PLUS) {
        return "C+";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_C) {
        return "C";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_D) {
        return "D";
      }
      if (finalGrade >= WhiteboardConstants.GRADE_E) {
        return "E";
      }
    }
    return String.valueOf(finalGrade);
  }

  public static int identifyGradeType(final String value) {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return WhiteboardConstants.GRADE_LETTER;
    }
    return WhiteboardConstants.GRADE_NUMERIC;
  }

  public static int convertLetterGradeToNumeric(final String value) {
    /*
     * switch statement on string value could have also work but there is a
     * limitation that it is supported only by Java 7+
     */
    if ("A+".equals(value)) {
      return WhiteboardConstants.GRADE_A_PLUS;
    }
    if ("A".equals(value)) {
      return WhiteboardConstants.GRADE_A;
    }
    if ("A-".equals(value)) {
      return WhiteboardConstants.GRADE_A_MINUS;
    }
    if ("B+".equals(value)) {
      return WhiteboardConstants.GRADE_B_PLUS;
    }
    if ("B".equals(value)) {
      return WhiteboardConstants.GRADE_B;
    }
    if ("B-".equals(value)) {
      return WhiteboardConstants.GRADE_B_MINUS;
    }
    if ("C+".equals(value)) {
      return WhiteboardConstants.GRADE_C_PLUS;
    }
    if ("C".equals(value)) {
      return WhiteboardConstants.GRADE_C;
    }
    if ("D".equals(value)) {
      return WhiteboardConstants.GRADE_D;
    }
    if ("E".equals(value)) {
      return WhiteboardConstants.GRADE_E;
    }
    return 0;
  }
}
