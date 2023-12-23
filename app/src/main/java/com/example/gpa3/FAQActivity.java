package com.example.gpa3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // Sample FAQ data
        ArrayList<String> faqList = new ArrayList<>();
        faqList.add("Q: What is GPA?");
        faqList.add("A: GPA stands for Grade Point Average. It is a standardized way of measuring academic performance. GPAs are often used by educational institutions to evaluate a student's overall performance.");

        faqList.add("Q: How is GPA calculated?");
        faqList.add("A: GPA is typically calculated on a scale of 0 to 4.0 in the United States. Each letter grade (A, B, C, D, F) is assigned a corresponding point value, and the GPA is the average of these points.");

        faqList.add("Q: Can I calculate my GPA for a specific semester?");
        faqList.add("A: Yes, you can calculate your GPA for a specific semester by summing the grade points earned in each course and dividing by the total credit hours. The formula is (Σ (Grade Points * Credit Hours)) / Total Credit Hours.");

        // Display FAQs in a ListView
        ListView faqListView = findViewById(R.id.faqListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faqList);
        faqListView.setAdapter(arrayAdapter);
    }
}
