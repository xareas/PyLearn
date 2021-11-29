package com.xareas.pylearn.data.repository

import androidx.lifecycle.LiveData
import com.xareas.pylearn.data.model.LessonDetail
import com.xareas.pylearn.data.model.LessonHeader

interface LessonRepository {

    suspend fun saveLessonHeader(lessonHeader: LessonHeader)
    suspend fun saveLessonDetail(lessonDetail: LessonDetail)

    fun getCount() : Int

    fun getLessonHeader() : LiveData<List<LessonHeader>>

    fun getLessonDetail(lessonHeaderId : Int) : LiveData<List<LessonDetail>>

    suspend fun removeAllLessons()

}