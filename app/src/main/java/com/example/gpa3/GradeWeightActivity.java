package com.example.gpa3;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GradeWeightActivity extends AppCompatActivity implements GradeWeightAdapter.OnItemClickListener {

    private List<GradeWeight> gradeWeights;
    private GradeWeightAdapter adapter;
    private SharedPreferences preferences;
    private static final String PREFS_NAME = "GradePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_weight);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        gradeWeights = new ArrayList<>();
        adapter = new GradeWeightAdapter(gradeWeights, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadGradeWeights();
    }


    @Override
    public void onItemClick(int position, String newValue) {
        if (position >= 0 && position < gradeWeights.size()) {
            GradeWeight gradeWeight = gradeWeights.get(position);
            gradeWeight.setValue(newValue);
            saveGradeWeights();  // Save immediately after modifying the list
        }
    }



    private void loadGradeWeights() {
        // Load values from SharedPreferences and populate the list
        for (String grade : GradeWeight.GRADE_LETTERS) {
            String value = preferences.getString(grade, "0.0");
            gradeWeights.add(new GradeWeight(grade, value));
        }
        adapter.notifyDataSetChanged();
    }

    private void saveGradeWeights() {
        // Save values to SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        for (GradeWeight gradeWeight : gradeWeights) {
            editor.putString(gradeWeight.getGrade(), gradeWeight.getValue());
        }
        editor.apply();
    }
}
