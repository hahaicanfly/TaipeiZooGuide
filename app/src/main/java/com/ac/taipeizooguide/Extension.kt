package com.ac.taipeizooguide

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ac.taipeizooguide.ui.OnItemClickListener
import com.bumptech.glide.Glide

/**
 * Created on 2021/3/11.
 */
fun TextView.setMemo(memo: String?) {
    text = memo ?: this.context.getString(R.string.no_official_holiday_message)
}

fun ImageView.loadUrl(picUrl: String?) {
    Glide.with(this.context)
        .load(picUrl)
        .placeholder(R.drawable.ic_zoo)
        .into(this)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object :
        RecyclerView.OnChildAttachStateChangeListener {

        override fun onChildViewAttachedToWindow(view: View) {
            //catch item click event when rv attached
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }
    })
}

fun Fragment.setActionBar(enable: Boolean) {
    if (enable) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}