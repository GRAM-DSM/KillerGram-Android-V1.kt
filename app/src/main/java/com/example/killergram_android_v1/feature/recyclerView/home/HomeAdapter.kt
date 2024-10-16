package com.example.killergram_android_v1.feature.recyclerView.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ListItemBinding
import com.example.killergram_android_v1.feature.recyclerView.home.data.Sport
import com.example.killergram_android_v1.feature.utils.dpToPx

class
HomeAdapter(
    private val items: List<Sport>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<HomeAdapter.Holder>() {

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams

//    val marginInDp = 16
//    val marginInPx = dpToPx(parent.context, marginInDp)
//    layoutParams.setMargins(0, marginInPx, 0, 0)
//
//    binding.root.layoutParams = layoutParams


    return Holder(binding, itemClickListener)
}

override fun getItemCount(): Int {
    return items.size
}

override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(items[position])
}

class Holder(
    private val binding: ListItemBinding,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener(position)
            }
        }
    }
    fun bind(sport: Sport) {
        binding.title.text = sport.sportName
        binding.subTitle.text = "${sport.personnel}명 중 ${sport.participate}명 참여"
        if (sport.isEnd) {
            binding.tvItemComplete.text =  "완료"
            binding.constraintSport.setBackgroundResource(R.drawable.button_unselected)
            binding.subTitle.setTextColor(Color.parseColor("#FF8F9094")) // gray
            binding.tvItemComplete.setTextColor(Color.parseColor("#FF8F9094"))
        } else {
            binding.tvItemComplete.text =  "진행 중"
            binding.constraintSport.setBackgroundResource(R.drawable.button_selected)
            binding.subTitle.setTextColor(Color.parseColor("#FF9EFF00")) // main
            binding.tvItemComplete.setTextColor(Color.parseColor("#FF9EFF00"))
        }
    }
}
}
