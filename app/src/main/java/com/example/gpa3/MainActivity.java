package com.example.gpa3;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SemesterAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private SemesterAdapter semesterAdapter;

    // Broadcast Receiver
    private StudyBroadcastReceiver myReceiver;
    private static final String ACTION_STUDY_BROADCAST = "com.example.gpa3.STUDY_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the service to show the notification
        startService(new Intent(this, StudyNotificationService.class));

        Button broadcastButton = findViewById(R.id.btnBroadcast);
        broadcastButton.setOnClickListener(v -> sendBroadcast());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        semesterAdapter = new SemesterAdapter(getSemesters(), this, this);
        recyclerView.setAdapter(semesterAdapter);

        Button addSemesterButton = findViewById(R.id.btnAddSemester);
        addSemesterButton.setOnClickListener(v -> semesterAdapter.addSemester());

        Button gradeWeightButton = findViewById(R.id.btnGradeWeight);
        gradeWeightButton.setOnClickListener(v -> startActivity(new Intent(this, GradeWeightActivity.class)));

        Button faqButton = findViewById(R.id.btnFAQ);
        faqButton.setOnClickListener(v -> startActivity(new Intent(this, FAQActivity.class)));

        Button btnOpenStudyWebsite = findViewById(R.id.btnOpenStudyWebsite);
        btnOpenStudyWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the URL of the website you want to open
                String websiteUrl = "https://www.google.com";

                // Create an Intent to open a web page
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));

                // Check if there's an app to handle this intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where there's no app to handle the intent
                    // You can display a message or choose another action.
                }
            }
        });

        // Initialize and register the broadcast receiver
        myReceiver = new StudyBroadcastReceiver();
        registerReceiver(myReceiver, new IntentFilter(ACTION_STUDY_BROADCAST));
    }

    private void sendBroadcast() {
        Intent intent = new Intent("com.example.gpa3.STUDY_BROADCAST");
        sendBroadcast(intent);
    }

    @Override
    public void onItemClick(Semester semester) {
        try {
            Intent intent = new Intent(this, SubjectActivity.class);
            intent.putExtra("semester_name", semester.getName());
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Error navigating to SubjectActivity", e);
        }
    }

    @Override
    public void onDeleteClick(Semester semester) {
        semesterAdapter.removeSemester(semester);
    }

    @Override
    protected void onDestroy() {
        // Unregister the receiver when the activity is destroyed
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Override
    public void onViewSubjectsClick(Semester semester) {
        // Handle the click on the "View Subjects" button for the selected semester
        Intent intent = new Intent(this, SubjectActivity.class);
        intent.putExtra("semester_name", semester.getName());
        startActivity(intent);
    }

    private List<Semester> getSemesters() {
        List<Semester> semesters = new ArrayList<>();

        // Create a sample semester with subjects
        Semester sampleSemester = new Semester("Sample Semester");
        List<Subject> sampleSubjects = new ArrayList<>();
        sampleSubjects.add(new Subject("Math", "A", 3));
        sampleSubjects.add(new Subject("Physics", "B", 4));
        // Add more subjects as needed
        sampleSemester.setSubjects(sampleSubjects);

        // Add the sample semester to the list
        semesters.add(sampleSemester);

        return semesters;
    }

}
