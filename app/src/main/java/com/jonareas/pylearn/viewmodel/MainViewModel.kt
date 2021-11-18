package com.jonareas.pylearn.viewmodel

import androidx.lifecycle.*
import com.jonareas.pylearn.data.model.LessonDetail
import com.jonareas.pylearn.data.model.LessonHeader
import com.jonareas.pylearn.data.repository.LessonRepository
import com.jonareas.pylearn.data.repository.QuizRepository
import com.jonareas.pylearn.init.PyLearnProvider
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

    fun existData() : Boolean {
        return lessonRepository.getCount() > 0
    }

    fun lessons() : LiveData<List<LessonHeader>> {
       return lessonRepository.getLessonHeader();
    }

    fun lessonsDetail(lessonHeaderId : Int) : LiveData<List<LessonDetail>>{
        return lessonRepository.getLessonDetail((lessonHeaderId))
    }

}