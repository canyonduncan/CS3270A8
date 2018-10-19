package com.example.canyon.cs3270a7;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.canyon.cs3270a7.db.Course;

import java.util.List;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.ViewHolder> {

    private final List<Course> courses;
    private AdapterCallback mCallback;

    public interface AdapterCallback {
        void onCourseSelected(Course c);
    }

    public CourseRecyclerViewAdapter(List<Course> courses, Activity activity) {
        this.courses = courses;
        try{
            mCallback = (AdapterCallback) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " need to implement AdapterCallback");
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Course c = courses.get(i);
        if (c != null){
            viewHolder.course = c;
            viewHolder.tvLineOne.setText(c.getName() + " " + c.getCourse_code() + " - " + c.getStart_at());

            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onCourseSelected(c);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void addItems(List<Course> courses) {
        this.courses.clear();
        this.courses.addAll(courses);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLineOne;
        public TextView tvLineTwo;
        public Course course;
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvLineOne = (TextView) itemView.findViewById(R.id.lineOne);
            tvLineTwo = (TextView) itemView.findViewById(R.id.lineTwo);
        }
    }
}
