package com.jonareas.pylearn.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "lessonDetail",
    foreignKeys = [
        ForeignKey(
            entity = LessonHeader::class,
            parentColumns = ["lesson_header_id"],
            childColumns =  ["lesson_header_id"],
            onDelete = CASCADE
        )
    ],
    indices = [Index("lesson_header_id")]
)
data class LessonDetail(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name  = "lesson_detail_id")
    val lessonDetailId : Int,

    @ColumnInfo(name = "lesson_header_id")
    val lessonHeaderId : Int,

    @ColumnInfo(name = "order")
    val order : Int,

    @ColumnInfo(name = "text")
    val text : String,

    @ColumnInfo(name = "image_name")
    val image : String

)