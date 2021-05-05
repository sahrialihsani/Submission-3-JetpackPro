package com.sahrial.submission_3_jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_mov")
@Parcelize
data class MovEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "movId")
    var movieId: Int = 0,

    @ColumnInfo(name = "movTitle")
    var title: String? = null,

    @ColumnInfo(name = "movVote")
    var vote_average: Float? = 0F,

    @ColumnInfo(name = "movOverview")
    var overview: String? = null,

    @ColumnInfo(name = "movDate")
    var date: String? = null,

    @ColumnInfo(name = "movPoster")
    var poster: String? = null,

    @ColumnInfo(name = "movBackdrop")
    var backdrop: String? = null,

    @NonNull
    @ColumnInfo(name = "isFav")
    var isFavorite: Boolean = false
): Parcelable