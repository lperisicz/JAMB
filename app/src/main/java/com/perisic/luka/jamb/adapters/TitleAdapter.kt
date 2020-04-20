package com.perisic.luka.jamb.adapters

import androidx.core.content.ContextCompat
import com.perisic.luka.jamb.R
import com.perisic.luka.jamb.data.Title
import com.perisic.luka.jamb.databinding.ItemTitleBinding
import com.perisic.luka.jamb.util.BaseAdapter

class TitleAdapter : BaseAdapter<Title, ItemTitleBinding>(
    inflater = ItemTitleBinding::inflate,
    binder = {
        title = it
        val titles = listOf("Sum", "Difference")
        textViewTitle.setTextColor(
            ContextCompat.getColor(
                root.context,
                if (titles.contains(it.text)) {
                    R.color.colorAccent
                } else {
                    R.color.textColorDark
                }
            )
        )
    }
)