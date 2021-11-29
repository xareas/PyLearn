package com.xareas.pylearn.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xareas.pylearn.data.dao.LessonDao
import com.xareas.pylearn.data.dao.QuizDao
import com.xareas.pylearn.data.model.*

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
        private var INSTANCE: PyLearnDatabase? = null

        fun buildDatabase(context: Context): PyLearnDatabase {
            return Room.databaseBuilder(
                context,
                PyLearnDatabase::class.java,
                DATABASE_NAME
            )
             .allowMainThreadQueries()
             //.addCallback(object : RoomDatabase.Callback() {

                 //override fun onCreate(db: SupportSQLiteDatabase) {
                 //    super.onCreate(db)
                     //Crear aca
                 //    PyLearnDatabase.INSTANCE?.let {

                 //    }
               //  }
             // })
             .build()
        }

    }
}