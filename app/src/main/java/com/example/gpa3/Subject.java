package com.example.gpa3;

import java.util.List;

public class Subject {
    private String name;
    private String gradeLetter; // New field for grade letter
    private int creditHours;

    public Subject(String name, String gradeLetter, int creditHours) {
        this.name = name;
        this.gradeLetter = gradeLetter;
        this.creditHours = creditHours;
    }

    public static Subject fromString(String subjectString) {
        // Parse the subjectString and create a Subject object
        // Example: "Math,A,3" -> Subject("Math", "A", 3)
        String[] parts = subjectString.split(",");
        if (parts.length == 3) {
            return new Subject(parts[0], parts[1], Integer.parseInt(parts[2]));
        } else {
            return null; // Handle the case when the string is not in the expected format
        }
    }

    public String getName() {
        return name;
    }

    public String getGradeLetter() {
        return gradeLetter;
    }

    public int getCreditHours() {
        return creditHours;
    }

    // Calculate GPA for this subject based on grade letter and grade weights
    public double calculateGradePoints(List<GradeWeight> gradeWeights) {
        for (GradeWeight gradeWeight : gradeWeights) {
            if (gradeWeight.getGrade().equals(gradeLetter)) {
                return Double.parseDouble(gradeWeight.getValue());
            }
        }
        return 0.0; // Default value if grade letter is not found
    }
}
