package com.example.canyon.cs3270a7.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

//     -   Add a new course to the database (via the UI & in the DAO)
    @Insert
    void insert(Course... courses);
//   -   Retrieve a list of courses (in the DAO)
    @Query("select * from course")
    LiveData<List<Course>> getAll();
//   -   View the details of a selected course (in the DAO)
    @Query("select * from course where uid = :uid")
    Course getCourseDetails(int uid);
//   -   Edit a selected course (in the DAO)
    @Update
    void update(Course course);
//   -   Delete a selected course (in the DAO)
    @Delete
    void delete(Course course);

}
