package com.perisic.luka.jamb.adapters

import androidx.core.content.ContextCompat
import com.perisic.luka.jamb.R
import com.perisic.luka.jamb.data.Cell
import com.perisic.luka.jamb.databinding.ItemCellBinding
import com.perisic.luka.jamb.util.BaseAdapter
import com.perisic.luka.jamb.util.BaseViewHolder

class CellAdapter(
    private val onClick: (position: Int) -> Unit
) : BaseAdapter<Cell, ItemCellBinding>(
    inflater = ItemCellBinding::inflate,
    binder = {
        cell = it
        textViewCell.setBackgroundColor(
            ContextCompat.getColor(
                root.context,
                if (it.display) {
                    R.color.colorSum
                } else {
                    if (it.locked) {
                        R.color.colorLocked
                    } else {
                        R.color.colorPending
                    }
                }
            )
        )
    }
) {

    override fun onBindViewHolder(holder: BaseViewHolder<Cell, ItemCellBinding>, position: Int) {
        super.onBindViewHolder(holder, position)
        if (!data[position].locked) {
            holder.binding.textViewCell.setOnClickListener {
                onClick(position)
            }
        } else {
            holder.binding.textViewCell.setOnClickListener {}
        }
    }

}