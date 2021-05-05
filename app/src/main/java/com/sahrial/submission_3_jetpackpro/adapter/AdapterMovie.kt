package com.sahrial.submission_3_jetpackpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sahrial.submission_3_jetpackpro.BuildConfig
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.adapter.callback.MovieCallback
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity
import com.sahrial.submission_3_jetpackpro.helper.Const
import com.sahrial.submission_3_jetpackpro.helper.loadUrl
import kotlinx.android.synthetic.main.cardmovietv.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class AdapterMovie(private val callback: MovieCallback) :
    PagedListAdapter<MovEntity, AdapterMovie.ViewHolder>(CALLBACK) {
    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<MovEntity>() {
            override fun areItemsTheSame(prevItem: MovEntity, nextItem: MovEntity): Boolean {
                return prevItem.movieId == nextItem.movieId
            }
            override fun areContentsTheSame(prevItem: MovEntity, nextItem: MovEntity): Boolean {
                return prevItem == nextItem
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovEntity) {
            with(itemView) {
                data.poster?.let {
                    iv_img_poster.loadUrl(BuildConfig.IMAGE_DB + Const.POSTERSIZE + it)
                }
                tv_title.text = data.title
                val dateFormat: DateFormat = SimpleDateFormat("dd MMM yyyy")
                val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                val tglformat: String = dateFormat.format(df.parse(data.date))
                tv_date.text = tglformat

                cv_movtv.setOnClickListener {
                    callback.itemClicked(data)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardmovietv, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}