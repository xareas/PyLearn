package com.xareas.pylearn.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.xareas.pylearn.data.model.Answer
import com.xareas.pylearn.data.model.Question

@Dao
interface QuizDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(question : Question)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(answer : Answer)

  @Query("DELETE FROM questions")
  fun clearQuestions()

  @Delete
  fun deleteQuestion(question : Question)

  @Query("SELECT * FROM questions ORDER BY question_id")
  fun getAllQuestions() : LiveData<List<Question>>

  @Query("SELECT * FROM answers where question_id = :questionId")
  fun getAllAnswers(questionId : Int) : LiveData<List<Answer>>


}