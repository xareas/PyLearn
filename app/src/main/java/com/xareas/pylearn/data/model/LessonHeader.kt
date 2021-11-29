package com.xareas.pylearn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "lessonHeader",
        indices = [Index("lesson_header_id")]
    )
data class LessonHeader(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lesson_header_id")
    val lessonHeaderId : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "image_name")
    val imageName : String,

    @ColumnInfo(name = "is_favorite")
    val isFavorite : Boolean,

    @ColumnInfo(name = "description")
    val description : String
)