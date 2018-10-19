package com.example.canyon.cs3270a7;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.canyon.cs3270a7.db.AppDatabase;
import com.example.canyon.cs3270a7.db.Course;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDialogCourseView extends DialogFragment {

    private View root;
    private TextView tvCourseID;
    private TextView tvCourseName;
    private TextView tvCourseCode;
    private TextView tvCourseStart;
    private TextView tvCourseEnd;
    public Course course;
    private CourseViewCallback mCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mCallback = (CourseViewCallback) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    public interface CourseViewCallback{
        void onDeleteCourse(Course c);
        void onEditCourse(Course c);
    }


    public FragmentDialogCourseView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dialog_course_view, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbarViewCourse);
        toolbar.setTitle("View Course");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        tvCourseID = (TextView) root.findViewById(R.id.tvCourseID);
        tvCourseName = (TextView) root.findViewById(R.id.tvCourseName);
        tvCourseCode = (TextView) root.findViewById(R.id.tvCourseCode);
        tvCourseStart = (TextView) root.findViewById(R.id.tvCourseStart);
        tvCourseEnd = (TextView) root.findViewById(R.id.tvCourseEnd);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();


        if(course != null){
            setTextViewText();

        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_view_course, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                mCallback.onDeleteCourse(this.course);
                return true;
            case R.id.action_edit_course:

                mCallback.onEditCourse(this.course);
                dismiss();
                return true;
            case android.R.id.home:
                dismiss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setTextViewText(){

        tvCourseID.setText(course.getId());
        tvCourseName.setText(course.getName());
        tvCourseCode.setText(course.getCourse_code());
        tvCourseStart.setText(course.getStart_at());
        tvCourseEnd.setText(course.getEnd_at());

    }




}
