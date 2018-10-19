package com.example.canyon.cs3270a7;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.example.canyon.cs3270a7.db.AppDatabase;
import com.example.canyon.cs3270a7.db.Course;

public class FragmentDialogDeleteConfirmation extends DialogFragment {

    public Course course;

    public FragmentDialogDeleteConfirmation() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.are_you)
                .setMessage(R.string.this_will_delete)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                AppDatabase.getInstance(getContext())
                                        .courseDAO()
                                        .delete(course);
                            }
                        }).start();

                        getFragmentManager().popBackStack();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });



        return builder.create();
    }
}
