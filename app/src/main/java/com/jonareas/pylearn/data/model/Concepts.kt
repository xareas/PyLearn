package com.jonareas.pylearn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "concepts")
class Concepts(
    @PrimaryKey(autoGenerate = true)
    val conceptId : Int
)