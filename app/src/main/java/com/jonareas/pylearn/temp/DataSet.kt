package com.jonareas.pylearn.temp

import com.jonareas.pylearn.R

object DataSet {
    fun loadData(): List<Lesson> {
        return listOf(
            Lesson(R.string.lesson1, R.drawable.dino),
            Lesson(R.string.lesson2, R.drawable.snake),
            Lesson(R.string.lesson3, R.drawable.cat),
            Lesson(R.string.lesson4, R.drawable.chameleon),
            Lesson(R.string.lesson5, R.drawable.fish),
            Lesson(R.string.lesson6, R.drawable.duck),
            Lesson(R.string.lesson7, R.drawable.panda),
            Lesson(R.string.lesson8, R.drawable.pig),
            Lesson(R.string.lesson9, R.drawable.spider),
            Lesson(R.string.lesson10, R.drawable.penguin_plane),
            Lesson(R.string.lesson11, R.drawable.yeti)
        )
    }

}