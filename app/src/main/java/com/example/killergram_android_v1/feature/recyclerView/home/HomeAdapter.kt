package com.example.killergram_android_v1.feature.recyclerView.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.databinding.ListItemBinding
import com.example.killergram_android_v1.feature.recyclerView.home.data.Sport

class
HomeAdapter(
    private val items: List<Sport>
) : RecyclerView.Adapter<HomeAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))

//        val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams
//
//        val marginInDp = 16
//        val marginInPx = dpToPx(parent.context, marginInDp)
//        layoutParams.setMargins(marginInPx, marginInPx, marginInPx, marginInPx)
//
//        binding.root.layoutParams = layoutParams

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    class Holder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sport: Sport) {
            binding.title.text = sport.sportName
            binding.subTitle.text = "${sport.personnel}명 중 ${sport.participate}명 참여"
            if (sport.isEnd) {
                binding.tvItemComplete.text =  "완료"
                binding.constraintSport.setBackgroundResource(R.drawable.bg_button_gray_radius_8)
                binding.subTitle.setTextColor(Color.parseColor("#FF8F9094")) // gray
                binding.tvItemComplete.setTextColor(Color.parseColor("#FF8F9094"))
            } else {
                binding.tvItemComplete.text =  "진행 중"
                binding.subTitle.resources.getColor(R.color.white)
                binding.constraintSport.setBackgroundResource(R.drawable.bg_button_main_radius_8)
                binding.subTitle.setTextColor(Color.parseColor("#FF9EFF00")) // main
                binding.tvItemComplete.setTextColor(Color.parseColor("#FF9EFF00"))

            }
        }
    }

    fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}