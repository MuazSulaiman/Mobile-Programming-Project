package com.example.gpa3;// Semester.java
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Semester implements Parcelable {

    private long id;
    private String name;
    private List<Subject> subjects;

    public Semester(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Semester(String sampleSemester) {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Semester fromString(String semesterString) {
        String[] parts = semesterString.split(",");
        if (parts.length == 2) {
            try {
                long id = Long.parseLong(parts[0]);
                String name = parts[1];
                return new Semester(id, name);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Set<String> toStringSet(List<Semester> semesters) {
        List<String> semesterStrings = new ArrayList<>();
        for (Semester semester : semesters) {
            semesterStrings.add(semester.toString());
        }
        return Set.copyOf(semesterStrings);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }

    // Parcelable implementation

    protected Semester(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<Semester> CREATOR = new Creator<Semester>() {
        @Override
        public Semester createFromParcel(Parcel in) {
            return new Semester(in);
        }

        @Override
        public Semester[] newArray(int size) {
            return new Semester[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    public void setSubjects(List<Subject> sampleSubjects) {
    }
}
