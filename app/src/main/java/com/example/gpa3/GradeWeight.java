package com.example.gpa3;

public class GradeWeight {
    private String grade;
    private String value;

    public GradeWeight(String grade, String value) {
        this.grade = grade;
        this.value = value;
    }

    public String getGrade() {
        return grade;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // You can define GRADE_LETTERS as needed, for example:
    public static final String[] GRADE_LETTERS = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
}
