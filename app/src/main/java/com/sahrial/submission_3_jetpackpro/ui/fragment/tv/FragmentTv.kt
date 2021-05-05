package com.sahrial.submission_3_jetpackpro.ui.fragment.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.ui.activity.detail.DetailActivity
import com.sahrial.submission_3_jetpackpro.adapter.AdapterTv
import com.sahrial.submission_3_jetpackpro.adapter.callback.TvCallback
import com.sahrial.submission_3_jetpackpro.data.source.control.status.Status
import com.sahrial.submission_3_jetpackpro.data.source.local.entity.TvEntity
import com.sahrial.submission_3_jetpackpro.helper.Const.TVTYPE
import com.sahrial.submission_3_jetpackpro.viewmodel.FactoryViewModel
import com.sahrial.submission_3_jetpackpro.viewmodel.MainviewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject


class FragmentTv : DaggerFragment(), TvCallback {

    private lateinit var viewModel: MainviewModel

    @Inject
    lateinit var factory: FactoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showRecyclerview()

        activity?.let { configureViewmodel(it) }
        getAllTv()

    }

    private fun configureViewmodel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                    it,
                    factory
            )[MainviewModel::class.java]
        }
    }

    private fun getAllTv() {
        viewModel.getAllTvNow().observe(viewLifecycleOwner, Observer { listTv ->
            if (listTv != null) {
                when (listTv.status) {
                    Status.LOADING -> pb_tv.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        pb_tv.visibility = View.GONE
                        rv_tv.adapter?.let { adapter ->
                            when (adapter) {
                                is AdapterTv -> {
                                    adapter.submitList(listTv.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }!!
                    }
                    Status.ERROR -> {
                        pb_tv.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.nointernet), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showRecyclerview() {
        rv_tv.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = AdapterTv(this@FragmentTv)
        }
    }

    override fun itemClicked(data: TvEntity) {
        startActivity(Intent( context,DetailActivity::class.java) .putExtra(DetailActivity.EX_DATA, data.tvId).putExtra(DetailActivity.EX_TYPE, TVTYPE))
    }

}