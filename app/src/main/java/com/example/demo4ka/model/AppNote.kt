package com.example.demo4ka.model

import android.os.Parcelable
import android.renderscript.Sampler
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.demo4ka.utilits.PROGRESS
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.temporal.ValueRange

@Parcelize
@Entity(tableName = "notes_tables")
data class AppNote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String = "",
    @ColumnInfo val text: String = "",
    @ColumnInfo val text2: String = "",
    @ColumnInfo val idFirebase: String = "",
    @ColumnInfo val troubleCount: Int = 1,
    @ColumnInfo val progressStatus: Int = 0


) : Parcelable


