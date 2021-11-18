package com.jonareas.pylearn.data.repository

import androidx.lifecycle.LiveData
import com.jonareas.pylearn.PyLearnApplication
import com.jonareas.pylearn.data.dao.QuizDao
import com.jonareas.pylearn.data.model.Answer
import com.jonareas.pylearn.data.model.Question

class QuizRepositoryImpl : QuizRepository {

    private val quizDao : QuizDao by lazy {
        PyLearnApplication.pyLearnDatabase.quizDao()
    }

    override fun getQuestions(): LiveData<List<Question>>  = quizDao.getAllQuestions()


    override suspend fun saveQuestion(question: Question) = quizDao.insert(question)


    override suspend fun saveAnswer(answer: Answer) = quizDao.insert(answer)


    override fun getAllAnswers(questionId: Int):
            LiveData<List<Answer>> = quizDao.getAllAnswers(questionId)


    override suspend fun removeAllQuestions() = quizDao.clearQuestions()
}