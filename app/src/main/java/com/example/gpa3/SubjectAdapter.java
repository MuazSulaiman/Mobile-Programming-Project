package com.example.gpa3;

import static android.text.method.TextKeyListener.clear;
import static java.util.Collections.addAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SubjectAdapter extends BaseAdapter {

    private Context context;
    private List<Subject> subjects;

    public SubjectAdapter(Context context, List<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false);
        }

        TextView tvSubjectName = convertView.findViewById(R.id.tvSubjectName);
        TextView tvGradeLetter = convertView.findViewById(R.id.tvGradeLetter);

        Subject subject = subjects.get(position);

        tvSubjectName.setText(subject.getName());
        tvGradeLetter.setText(subject.getGradeLetter());

        return convertView;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }
}
