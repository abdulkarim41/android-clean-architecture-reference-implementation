package com.abdulkarim.ui.extfun

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun<viewHolder,T:RecyclerView.Adapter<viewHolder>> Context.setUpVerticalRecyclerView(view: RecyclerView,viewAdapter:T){
    view.layoutManager = LinearLayoutManager(this)
    view.adapter = viewAdapter
}
