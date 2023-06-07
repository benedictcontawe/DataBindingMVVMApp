package com.example.jetpackcomponentsapp.view

import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.jetpackcomponentsapp.R

class IndicatorViewHolder : BaseViewHolder {

    companion object {
        private val TAG = IndicatorViewHolder::class.java.getSimpleName()
    }
    //region cell_circle
    private val image : AppCompatImageView
    //endregion
    constructor(context : Context, itemView : View) : super(context, itemView) { Log.d(TAG, "constructor")
        image = itemView.findViewById(R.id.image)
    }

    public fun bindDataToViewHolder() {

    }
}