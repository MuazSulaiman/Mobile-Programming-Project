package com.example.gpa3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubjectActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "SubjectPrefs";
    private static final String SEMESTER_NAME_KEY = "semester_name";
    private static final String SUBJECTS_KEY = "subjects";

    private String semesterName;
    private List<Subject> subjects;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        // Retrieve semester name from intent
        semesterName = getIntent().getStringExtra(SEMESTER_NAME_KEY);
        setTitle(semesterName);

        // Initialize subjects list
        subjects = getSubjectsFromPreferences();

        // Initialize ListView and adapter
        ListView listView = findViewById(R.id.listViewSubjects);
        subjectAdapter = new SubjectAdapter(this, subjects);
        listView.setAdapter(subjectAdapter);

        // Add Subject button
        Button addSubjectButton = findViewById(R.id.btnAddSubject);
        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddSubjectDialog();
            }
        });
    }

    private void showAddSubjectDialog() {
        AddSubjectDialog addSubjectDialog = new AddSubjectDialog(this, new AddSubjectDialog.OnSubjectAddedListener() {
            @Override
            public void onSubjectAdded(String subjectName, String gradeLetter) {
                Subject subject = new Subject(subjectName, gradeLetter, 0);
                subjects.add(subject);
                saveSubjectsToPreferences();
                subjectAdapter.setSubjects(subjects); // Update the adapter's data
            }
        });
        addSubjectDialog.show();
    }


    private List<Subject> getSubjectsFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> subjectSet = sharedPreferences.getStringSet(semesterName + SUBJECTS_KEY, null);

        List<Subject> subjectList = new ArrayList<>();

        if (subjectSet != null) {
            for (String subjectString : subjectSet) {
                Subject subject = Subject.fromString(subjectString);
                if (subject != null) {
                    subjectList.add(subject);
                }
            }
        }

        return subjectList;
    }

    private void saveSubjectsToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> subjectSet = new HashSet<>();
        for (Subject subject : subjects) {
            subjectSet.add(subject.toString());
        }

        editor.putStringSet(semesterName + SUBJECTS_KEY, subjectSet);
        editor.apply();
    }
}
