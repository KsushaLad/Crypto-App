package com.ksusha.crypto.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.text.DecimalFormat

private val formatTwo = DecimalFormat("##,##")
private val formatThree = DecimalFormat("##,###")

fun Double.roundToTwoDecimals() = formatTwo.format(this).toString()

fun Double.roundToThreeDecimals() = formatThree.format(this).toString()

fun List<Double?>?.toDoubleToFloat(): List<Pair<String, Float>> {
    return this!!.map { double ->
        val float = double!!.toFloat()
        val string = double.toString()
        Pair(string, float)
    }
}

fun RecyclerView.initRecyclerView(
    layoutManager: RecyclerView.LayoutManager,
    adapter: Adapter<*>
) {
    this.adapter = adapter
    this.layoutManager = layoutManager
}

fun View.isVisible(
    isShowLoading: Boolean,
    container: View
) {
    if (isShowLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    } else {
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}