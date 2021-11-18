package com.jonareas.pylearn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonareas.pylearn.data.dao.LessonDao
import com.jonareas.pylearn.data.dao.QuizDao
import com.jonareas.pylearn.data.model.*

const val DATABASE_VERSION = 1

@Database(
    entities = [(User::class), (Concepts::class), (LessonHeader::class),
        (LessonDetail::class), (Question::class), (Answer::class)],
    version = DATABASE_VERSION
)

abstract class PyLearnDatabase : RoomDatabase() {

    abstract fun lessonDao() : LessonDao

    abstract fun quizDao() : QuizDao


    companion object {
        private const val DATABASE_NAME = "PyLearn"

        fun buildDatabase(context: Context): PyLearnDatabase {
            return Room.databaseBuilder(
                context,
                PyLearnDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }

    }
}