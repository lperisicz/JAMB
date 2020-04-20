package com.perisic.luka.jamb.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, D : ViewDataBinding>(
    private val inflater: ((inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> D)? = null,
    private val binder: D.(data: T) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<T, D>>() {

    protected val data: ArrayList<T> = arrayListOf()

    fun submitData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    final override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T, D>, position: Int) {
        holder.bindTo(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, D> {
        inflater?.let {
            return BaseViewHolder(
                binding = parent.inflate(inflater),
                binder = binder
            )
        } ?: let {
            throw RuntimeException("Inflater passed is null, but BaseAdapter.onCreateViewHolder not overridden")
        }
    }

}

class BaseViewHolder<T, D : ViewDataBinding>(
    val binding: D,
    internal val binder: D.(data: T) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(data: T) = binding.binder(data)

}

private fun <T : ViewDataBinding> ViewGroup.inflate(binder: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> T): T {

    val inflater = LayoutInflater.from(context)

    return binder(inflater, this, false)

}