package com.aiden.kotlintest.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * RecyclerView通用适配器
 * @author aiden@tronsis.com
 * @date 2018/8/1 17:00
 */
class BaseRecyclerViewAdapter<T>(private var context: Context, private var data: ArrayList<T>, private var layoutId: Int, private val init: (View, T) -> Unit) :
        RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<T>>() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var onItemLongClickListener: OnItemClickListener

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindForest(data.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(inflater.inflate(layoutId, parent, false), init)
    }

    override fun getItemCount(): Int = data.size

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, position: Int)
    }

    class BaseViewHolder<in T>(v: View, val init: (View, T) -> Unit) : RecyclerView.ViewHolder(v) {
        fun bindForest(item: T) {
            with(item) {
                init(itemView, item)
            }
        }
    }
}