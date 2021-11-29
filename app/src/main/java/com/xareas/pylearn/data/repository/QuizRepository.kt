package com.xareas.pylearn.data.repository

import androidx.lifecycle.LiveData
import com.xareas.pylearn.data.model.Answer
import com.xareas.pylearn.data.model.Question

interface QuizRepository {

    fun getQuestions() : LiveData<List<Question>>

    suspend fun saveQuestion(question : Question)

    suspend fun saveAnswer(answer : Answer)

    fun getAllAnswers(questionId : Int) : LiveData<List<Answer>>

    suspend fun removeAllQuestions()

}