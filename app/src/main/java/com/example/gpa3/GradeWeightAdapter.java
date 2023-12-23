package com.example.gpa3;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GradeWeightAdapter extends RecyclerView.Adapter<GradeWeightAdapter.ViewHolder> {

    private List<GradeWeight> gradeWeights;
    private OnItemClickListener listener;

    public GradeWeightAdapter(List<GradeWeight> gradeWeights, OnItemClickListener listener) {
        this.gradeWeights = gradeWeights;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grade_weight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GradeWeight gradeWeight = gradeWeights.get(position);
        holder.bind(gradeWeight, listener);
    }

    @Override
    public int getItemCount() {
        return gradeWeights.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvGrade;
        private EditText editTextGrade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGrade = itemView.findViewById(R.id.tvGrade);
            editTextGrade = itemView.findViewById(R.id.editTextGrade);
        }

        public void bind(GradeWeight gradeWeight, OnItemClickListener listener) {
            tvGrade.setText(gradeWeight.getGrade());
            editTextGrade.setText(gradeWeight.getValue());

            // Set a text change listener to handle changes in the EditText
            editTextGrade.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // Do nothing before text changes
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    // Do nothing on text changing
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    // Save the new value when text changes
                    listener.onItemClick(getAdapterPosition(), editable.toString());
                }
            });
        }


    }

    public interface OnItemClickListener {
        void onItemClick(int position, String newValue);
    }
}
