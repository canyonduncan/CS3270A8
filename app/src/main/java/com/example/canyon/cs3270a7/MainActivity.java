package com.example.canyon.cs3270a7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.canyon.cs3270a7.db.Course;

public class MainActivity extends AppCompatActivity implements CourseRecyclerViewAdapter.AdapterCallback, FragmentDialogCourseView.CourseViewCallback {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm = getSupportFragmentManager();

        fm.beginTransaction()
                .replace(R.id.fragMain, new FragmentCourseList())
                .commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogCourseEdit frag = new FragmentDialogCourseEdit();
                fm.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, frag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void onCourseSelected(Course c) {

        FragmentDialogCourseView fragCourseView = new FragmentDialogCourseView();
        fragCourseView.course = c;

        fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(android.R.id.content, fragCourseView, "fragCourseView")
                .addToBackStack(null)
                .commit();


    }

    @Override
    public void onDeleteCourse(Course c) {
        FragmentDialogDeleteConfirmation dialog = new FragmentDialogDeleteConfirmation();
        dialog.course = c;
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "delete_dialog");
    }

    @Override
    public void onEditCourse(Course c) {
        FragmentDialogCourseEdit frag = new FragmentDialogCourseEdit();
        frag.course = c;
        fm.beginTransaction()
                //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(android.R.id.content, frag)
                .addToBackStack(null)
                .commit();
    }
}
