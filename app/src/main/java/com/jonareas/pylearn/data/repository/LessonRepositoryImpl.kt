package com.jonareas.pylearn.data.repository

import androidx.lifecycle.LiveData
import com.jonareas.pylearn.PyLearnApplication
import com.jonareas.pylearn.data.dao.LessonDao
import com.jonareas.pylearn.data.model.LessonDetail
import com.jonareas.pylearn.data.model.LessonHeader

class LessonRepositoryImpl : LessonRepository {

    private val lessonDao : LessonDao by lazy {
        PyLearnApplication.pyLearnDatabase.lessonDao()
    }

    override suspend fun saveLessonHeader(lessonHeader: LessonHeader) {
        return lessonDao.insert(lessonHeader)
    }

    override suspend fun saveLessonDetail(lessonDetail: LessonDetail) {
        return lessonDao.insert(lessonDetail)
    }

    override fun getCount(): Int {
       return lessonDao.getCount()
    }


    override fun getLessonHeader(): LiveData<List<LessonHeader>> {
        return lessonDao.getAllLessonHeader()
    }

    override fun getLessonDetail(lessonHeaderId: Int): LiveData<List<LessonDetail>> {
        return lessonDao.getAllLessonDetail(lessonHeaderId)
    }

    override suspend fun removeAllLessons() {
        return lessonDao.clearLessons()
    }
}