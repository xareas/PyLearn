package com.xareas.pylearn.utils

import com.xareas.pylearn.data.model.LessonHeader

//Interfaz para capturar el evento
interface OnClickLesson {

    fun OnClickItem(lesson: LessonHeader)

}