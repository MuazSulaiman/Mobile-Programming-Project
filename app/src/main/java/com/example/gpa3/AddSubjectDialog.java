package com.example.gpa3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class AddSubjectDialog extends Dialog {

    private OnSubjectAddedListener listener;

    public interface OnSubjectAddedListener {
        void onSubjectAdded(String subjectName, String gradeLetter);
    }

    public AddSubjectDialog(@NonNull Context context, OnSubjectAddedListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_subject);

        EditText etSubjectName = findViewById(R.id.etSubjectName);
        Spinner spinnerGrade = findViewById(R.id.spinnerGrade);

        // Predefined list of grade letters
        List<String> gradeLetters = Arrays.asList("A", "B", "C", "D", "F");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, gradeLetters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrade.setAdapter(adapter);

        Button btnAddSubject = findViewById(R.id.btnAddSubject);
        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjectName = etSubjectName.getText().toString();
                String gradeLetter = spinnerGrade.getSelectedItem().toString();

                if (!subjectName.isEmpty()) {
                    listener.onSubjectAdded(subjectName, gradeLetter);
                    dismiss();
                }
            }
        });
    }
}
