package com.jonareas.pylearn.init

import com.jonareas.pylearn.R
import com.jonareas.pylearn.data.model.Answer
import com.jonareas.pylearn.data.model.LessonDetail
import com.jonareas.pylearn.data.model.LessonHeader
import com.jonareas.pylearn.data.model.Question
import com.jonareas.pylearn.utils.Strings

object PyLearnProvider {
    val lessonHeader = initLessonHeader()
    val lessonDetail = initLessonDetail()
    val questions = initQuestions()
    val answers = initAnswers()


    private fun initLessonHeader(): MutableList<LessonHeader> {
        val lessonHeader = mutableListOf<LessonHeader>()

        lessonHeader.add(
            LessonHeader(
                1,
                Strings.get(R.string.lesson1),
                Strings.getDrawName(R.drawable.dino),
                false,
                "Leccion 1"
            )
        )
        lessonHeader.add(
            LessonHeader(
                2,
                Strings.get(R.string.lesson2),
                Strings.getDrawName(R.drawable.snake),
                false,
                "Leccion 2"
            )
        )
        lessonHeader.add(
            LessonHeader(
                3,
                Strings.get(R.string.lesson3),
                Strings.getDrawName(R.drawable.cat),
                false,
                "Leccion 3"
            )
        )
        lessonHeader.add(
            LessonHeader(
                4,
                Strings.get(R.string.lesson4),
                Strings.getDrawName(R.drawable.chameleon),
                false,
                "Leccion 4"
            )
        )
        lessonHeader.add(
            LessonHeader(
                5,
                Strings.get(R.string.lesson5),
                Strings.getDrawName(R.drawable.duck),
                false,
                "Leccion 5"
            )
        )

        return lessonHeader
    }

    private fun initLessonDetail(): MutableList<LessonDetail> {
        val lessonDetail = mutableListOf<LessonDetail>()
        //Lesson 1
        lessonDetail.add(LessonDetail(1, 1, 1, "Informacion primera",   Strings.getDrawName(R.drawable.dino)))
        lessonDetail.add(LessonDetail(2, 1, 2, "Informacion primera 2", Strings.getDrawName(R.drawable.dino)))
        lessonDetail.add(LessonDetail(3, 1, 3, "Informacion primera 3", Strings.getDrawName(R.drawable.dino)))
        lessonDetail.add(LessonDetail(4, 1, 4, "Informacion primera 4", Strings.getDrawName(R.drawable.dino)))

        //Lesson 2
        lessonDetail.add(LessonDetail(5, 2, 1, "Informacion Segunda", Strings.getDrawName(R.drawable.snake)))
        lessonDetail.add(LessonDetail(6, 2, 2, "Informacion Segunda", Strings.getDrawName(R.drawable.snake)))
        lessonDetail.add(LessonDetail(7, 2, 3, "Informacion Segunda", Strings.getDrawName(R.drawable.snake)))
        lessonDetail.add(LessonDetail(8, 2, 4, "Informacion Segunda", Strings.getDrawName(R.drawable.snake)))

        //Lesson 3
        lessonDetail.add(LessonDetail(9, 3, 1, "Informacion primera", Strings.getDrawName(R.drawable.cat)))
        lessonDetail.add(LessonDetail(10, 3, 2, "Informacion primera", Strings.getDrawName(R.drawable.cat)))
        lessonDetail.add(LessonDetail(11, 3, 3, "Informacion primera", Strings.getDrawName(R.drawable.cat)))
        lessonDetail.add(LessonDetail(12, 3, 4, "Informacion primera", Strings.getDrawName(R.drawable.cat)))

        //Lesson 4
        lessonDetail.add(LessonDetail(13, 4, 1, "Informacion primera", Strings.getDrawName(R.drawable.chameleon)))
        lessonDetail.add(LessonDetail(14, 4, 2, "Informacion primera", Strings.getDrawName(R.drawable.chameleon)))
        lessonDetail.add(LessonDetail(15, 4, 3, "Informacion primera", Strings.getDrawName(R.drawable.chameleon)))
        lessonDetail.add(LessonDetail(16, 4, 4, "Informacion primera", Strings.getDrawName(R.drawable.chameleon)))

        //Lesson 5
        lessonDetail.add(LessonDetail(17, 5, 1, "Informacion primera", Strings.getDrawName(R.drawable.fish)))
        lessonDetail.add(LessonDetail(18, 5, 2, "Informacion primera", Strings.getDrawName(R.drawable.fish)))
        lessonDetail.add(LessonDetail(19, 5, 3, "Informacion primera", Strings.getDrawName(R.drawable.fish)))
        lessonDetail.add(LessonDetail(20, 5, 4, "Informacion primera", Strings.getDrawName(R.drawable.fish)))


        return lessonDetail
    }


    private fun initQuestions(): MutableList<Question> {
        val question = mutableListOf<Question>()

        question.add(Question(1,"Donde Vives ?"))
        return question
    }

    private fun initAnswers(): MutableList<Answer> {
        val answer = mutableListOf<Answer>()

        answer.add(Answer(1,1,true, "Managua"))
        answer.add(Answer(2,1,false, "Leon"))
        answer.add(Answer(3,1,false, "Chinandega"))
        return answer
    }


}