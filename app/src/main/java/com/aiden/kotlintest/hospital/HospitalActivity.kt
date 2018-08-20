package com.aiden.kotlintest.hospital

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.aiden.kotlintest.R
import com.aiden.kotlintest.base.BaseActivity
import com.aiden.kotlintest.base.BaseRecyclerViewAdapter
import com.aiden.kotlintest.extension.toast
import com.cgnpc.dnmc.extension.display
import kotlinx.android.synthetic.main.activity_hospital.*
import kotlinx.android.synthetic.main.layout_hospital_item.view.*
import java.util.*

class HospitalActivity : BaseActivity(), HospitalContract.View {

    private lateinit var mPresenter: HospitalContract.Presenter
    private lateinit var mAdapter: BaseRecyclerViewAdapter<HospitalBean>
    private var mHospitalList: ArrayList<HospitalBean> = ArrayList<HospitalBean>()

    override fun getLayout(): Int {
        return R.layout.activity_hospital
    }

    override fun initData() {
        setTitle(title = "医院列表", rightBtnRes = R.mipmap.ic_share)
        mPresenter = HospitalPresenter(this, this, this)
        rv_hospital.layoutManager = LinearLayoutManager(this)
        mAdapter = BaseRecyclerViewAdapter(this, mHospitalList, R.layout.layout_hospital_item) { view: View, hospitalBean: HospitalBean ->
            view.tv_name.text = hospitalBean.hosName
            view.tv_address.text = hospitalBean.addr
            view.tv_desc.text = hospitalBean.info
            view.iv_hospital.display(hospitalBean.img)
        }
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                toast("点击了第$position 个")
            }
        })
        mAdapter.setOnItemLongClickListener(object : BaseRecyclerViewAdapter.OnItemLongClickListener {
            override fun onItemLongClick(v: View, position: Int): Boolean {
                toast("长按了第$position 个")
                return true
            }
        })
        rv_hospital.adapter = mAdapter
        mPresenter.getHospitalList("深圳")

        srl_hospital.setOnRefreshListener {
            mPresenter.getHospitalList("深圳");
        }
    }

    override fun refreshUI(hospitalList: ArrayList<HospitalBean>?) {
        if (hospitalList != null) {
            mHospitalList.clear()
            mHospitalList.addAll(hospitalList)
            mAdapter.notifyDataSetChanged()

        }
    }

    override fun loadFailed() {
        toast("加载失败，请稍后再试")
    }

    override fun onFinish() {
        if (srl_hospital.isRefreshing) {
            srl_hospital.isRefreshing = false
        }
    }

    override fun setPresenter(presenter: HospitalContract.Presenter) {
        mPresenter = presenter
    }
}