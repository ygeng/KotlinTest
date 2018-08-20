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

    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnItemLongClickListener: OnItemLongClickListener? = null

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindForest(data.get(position))
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { mOnItemClickListener!!.onItemClick(v = holder.itemView, position = position) }
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener {
                mOnItemLongClickListener!!.onItemLongClick(v = holder.itemView, position = position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return BaseViewHolder(mInflater.inflate(layoutId, parent, false), init)
    }

    override fun getItemCount(): Int = data.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, position: Int): Boolean
    }

    class BaseViewHolder<in T>(v: View, private val init: (View, T) -> Unit) : RecyclerView.ViewHolder(v) {
        fun bindForest(item: T): Unit {
            with(item) {
                return init(itemView, item)
            }
        }
    }
}