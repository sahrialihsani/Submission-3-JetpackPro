package com.sahrial.submission_3_jetpackpro.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_tv")
@Parcelize
data class TvEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int = 0,

    @ColumnInfo(name = "tvTitle")
    var title: String? = null,

    @ColumnInfo(name = "tvVote")
    var vote_average: Float? = 0F,

    @ColumnInfo(name = "tvOverview")
    var overview: String? = null,

    @ColumnInfo(name = "tvDate")
    var date: String? = null,

    @ColumnInfo(name = "tvPoster")
    var poster: String? = null,

    @ColumnInfo(name = "tvBackdrop")
    var backdrop: String? = null,

    @NonNull
    @ColumnInfo(name = "isFav")
    var isFavorite: Boolean = false
): Parcelable