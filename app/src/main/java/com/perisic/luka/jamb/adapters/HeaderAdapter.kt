package com.perisic.luka.jamb.adapters

import com.perisic.luka.jamb.data.Header
import com.perisic.luka.jamb.databinding.ItemHeaderBinding
import com.perisic.luka.jamb.util.BaseAdapter

class HeaderAdapter : BaseAdapter<Header, ItemHeaderBinding>(
    inflater = ItemHeaderBinding::inflate,
    binder = {
        header = it
    }
)