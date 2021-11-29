package com.xareas.pylearn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "questions",
        indices = [Index("question_id")]
    )
data class Question(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    val questionId : Int,

    @ColumnInfo(name = "question")
    val question : String

)