package com.xareas.pylearn.viewmodel

import androidx.lifecycle.*
import com.xareas.pylearn.data.model.LessonDetail
import com.xareas.pylearn.data.model.LessonHeader
import com.xareas.pylearn.data.repository.LessonRepository
import com.xareas.pylearn.data.repository.QuizRepository
import com.xareas.pylearn.init.PyLearnProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val lessonRepository: LessonRepository,
                    private val  quizRepository: QuizRepository
) : ViewModel() {


    fun  initData(){
        viewModelScope.launch {
            //Lessons
            for (lesson in PyLearnProvider.lessonHeader){
                lessonRepository.saveLessonHeader(lesson);
            }

            for (lessonDetail in PyLearnProvider.lessonDetail){
                lessonRepository.saveLessonDetail(lessonDetail);
            }

        //Questions
            for(question in PyLearnProvider.questions){
                quizRepository.saveQuestion(question)
            }

            for(answer in PyLearnProvider.answers){
                quizRepository.saveAnswer(answer)
            }

        }


    }

    fun  clearData(){

        viewModelScope.launch {
            lessonRepository.removeAllLessons()
            quizRepository.removeAllQuestions()
        }

    }

    suspend fun existData() : Boolean {
       var result = viewModelScope.async {
             lessonRepository.getCount() > 0
        }
        return result.await()
    }

     fun lessons() : LiveData<List<LessonHeader>> {
       return  lessonRepository.getLessonHeader()

    }


    fun lessonsDetail(lessonHeaderId : Int) : LiveData<List<LessonDetail>>{
        return lessonRepository.getLessonDetail((lessonHeaderId))
    }

}