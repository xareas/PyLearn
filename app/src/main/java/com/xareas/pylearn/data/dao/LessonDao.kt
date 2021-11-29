package com.xareas.pylearn.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xareas.pylearn.data.model.LessonDetail
import com.xareas.pylearn.data.model.LessonHeader


@Dao
interface LessonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lessonHeader: LessonHeader)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lessonDetail : LessonDetail)

    @Query("DELETE FROM lessonHeader")
    fun clearLessons()

    @Query("SELECT count(*) FROM lessonHeader")
    fun getCount(): Int

    @Query("SELECT * FROM lessonHeader")
    fun getAllLessonHeader() : LiveData<List<LessonHeader>>

    @Query("SELECT * FROM lessonDetail where lesson_header_id = :lessonHeaderId")
    fun getAllLessonDetail(lessonHeaderId : Int) : LiveData<List<LessonDetail>>
}