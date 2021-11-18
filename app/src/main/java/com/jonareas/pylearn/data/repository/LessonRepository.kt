package com.jonareas.pylearn.data.repository

import androidx.lifecycle.LiveData
import com.jonareas.pylearn.data.model.LessonDetail
import com.jonareas.pylearn.data.model.LessonHeader

interface LessonRepository {

    suspend fun saveLessonHeader(lessonHeader: LessonHeader)
    suspend fun saveLessonDetail(lessonDetail: LessonDetail)

    fun getCount() : Int

    fun getLessonHeader() : LiveData<List<LessonHeader>>

    fun getLessonDetail(lessonHeaderId : Int) : LiveData<List<LessonDetail>>

    suspend fun removeAllLessons()

}