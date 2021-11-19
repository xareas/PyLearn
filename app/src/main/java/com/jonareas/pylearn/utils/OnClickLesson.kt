package com.jonareas.pylearn.utils

import com.jonareas.pylearn.data.model.LessonHeader

//Interfaz para capturar el evento
interface OnClickLesson {

    fun OnClickItem(lesson: LessonHeader)

}