package com.example.gpa3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {

    private List<Semester> semesters;
    private OnItemClickListener listener;
    private Context context;
    private SharedPreferences sharedPreferences;

    public SemesterAdapter(List<Semester> semesters, OnItemClickListener listener, Context context) {
        this.semesters = semesters;
        this.listener = listener;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("SemesterPrefs", Context.MODE_PRIVATE);
        loadSemestersFromPreferences();
    }

    private void loadSemestersFromPreferences() {
        Set<String> semesterSet = sharedPreferences.getStringSet("semesters", null);
        if (semesterSet != null) {
            semesters.clear();
            for (String semesterString : semesterSet) {
                Semester semester = Semester.fromString(semesterString);
                if (semester != null) {
                    semesters.add(semester);
                }
            }
            notifyDataSetChanged();
        }
    }

    private void saveSemestersToPreferences() {
        Set<String> semesterSet = new HashSet<>();
        for (Semester semester : semesters) {
            semesterSet.add(semester.toString());
        }
        sharedPreferences.edit().putStringSet("semesters", semesterSet).apply();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_semester, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Semester semester = semesters.get(position);
        holder.bind(semester, listener);
    }

    @Override
    public int getItemCount() {
        return semesters.size();
    }

    public void addSemester() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter Semester Name");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String semesterName = input.getText().toString();
            long semesterId = generateUniqueId();

            Semester semester = new Semester(semesterId, semesterName);
            semesters.add(semester);
            saveSemestersToPreferences();
            notifyDataSetChanged();

            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    public void removeSemester(Semester semester) {
        semesters.remove(semester);
        saveSemestersToPreferences();
        notifyDataSetChanged();
    }

    private long generateUniqueId() {
        return System.currentTimeMillis();
    }

    public interface OnItemClickListener {
        void onItemClick(Semester semester);
        void onDeleteClick(Semester semester);

        void onViewSubjectsClick(Semester semester);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSemesterName;
        private Button btnDelete;
        private Button btnViewSubjects;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSemesterName = itemView.findViewById(R.id.tvSemesterName);
            btnDelete = itemView.findViewById(R.id.btnDeleteSemester);
            btnViewSubjects = itemView.findViewById(R.id.btnViewSubjects);
        }

        public void bind(Semester semester, OnItemClickListener listener) {
            tvSemesterName.setText(semester.getName());

            itemView.setOnClickListener(v -> listener.onItemClick(semester));
            btnDelete.setOnClickListener(v -> listener.onDeleteClick(semester));

            // Set click listener for the "View Subjects" button
            btnViewSubjects.setOnClickListener(v -> listener.onViewSubjectsClick(semester));
        }
    }

}
