package com.example.canyon.cs3270a7;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
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

import com.example.canyon.cs3270a7.db.AppDatabase;
import com.example.canyon.cs3270a7.db.Course;

public class FragmentDialogCourseEdit extends DialogFragment {

    private TextInputEditText etCourseID;
    private TextInputEditText etCourseName;
    private TextInputEditText etCourseCode;
    private TextInputEditText etStartDate;
    private TextInputEditText etEndDate;
    public Course course;
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root =  inflater.inflate(R.layout.dialog_course_edit, container, false);

        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle("Edit Course");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }

        setHasOptionsMenu(true);

        etCourseID = (TextInputEditText) root.findViewById(R.id.edtCourseID);
        etCourseName = (TextInputEditText) root.findViewById(R.id.edtCourseName);
        etCourseCode = (TextInputEditText) root.findViewById(R.id.edtCourseCode);
        etStartDate = (TextInputEditText) root.findViewById(R.id.edtStartDate);
        etEndDate = (TextInputEditText) root.findViewById(R.id.edtEndDate);

        if(course != null){
            setTextForCourse();
        }

        return root;
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
        getActivity().getMenuInflater().inflate(R.menu.menu_create_dialog, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_save:
                if(this.course != null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            course.setId(etCourseID.getText().toString());
                            course.setName(etCourseName.getText().toString());
                            course.setCourse_code(etCourseCode.getText().toString());
                            course.setStart_at(etStartDate.getText().toString());
                            course.setEnd_at(etEndDate.getText().toString());
                            AppDatabase.getInstance(getContext())
                                    .courseDAO()
                                    .update(course);
                        }
                    }).start();

                }
                else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Course c = new Course(etCourseID.getText().toString(), etCourseName.getText().toString(), etCourseCode.getText().toString(), etStartDate.getText().toString(), etEndDate.getText().toString());
                            AppDatabase.getInstance(getContext())
                                    .courseDAO()
                                    .insert(c);
                        }
                    }).start();
                }

                dismiss();
                return true;

            case android.R.id.home:
                dismiss();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void setTextForCourse(){

        etCourseID.setText(course.getId());
        etCourseName.setText(course.getName());
        etCourseCode.setText(course.getCourse_code());
        etStartDate.setText(course.getStart_at());
        etEndDate.setText(course.getEnd_at());
    }
}
