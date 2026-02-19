package com.abdulkarim.ui.extfun

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun <VH : RecyclerView.ViewHolder, T : RecyclerView.Adapter<VH>> Context.setUpVerticalRecyclerView(
    view: RecyclerView,
    viewAdapter: T
) {
    view.layoutManager = LinearLayoutManager(this)
    view.adapter = viewAdapter
}
