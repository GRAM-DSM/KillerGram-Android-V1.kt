package com.example.killergram_android_v1.feature.recyclerView.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.AsyncListDiffer.ListListener
import androidx.recyclerview.widget.RecyclerView
import com.example.killergram_android_v1.R
import com.example.killergram_android_v1.data.response.sport.GetSportResponse
import com.example.killergram_android_v1.databinding.ListItemBinding

class HomeAdapter(
    private val items: MutableList<GetSportResponse>,
    unFilteredList: ArrayList<String>,
    private val itemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<HomeAdapter.Holder>(), Filterable {

    companion object {
        // TODO: fullList
        private val fullList = arrayListOf<String>()
    }

    // TODO: filteredList
    private var filteredList = ArrayList<String>()

    init {
        // 초기화 시 fullList와 filteredList를 unFilteredList로 설정
        fullList.addAll(unFilteredList)
        filteredList.addAll(unFilteredList)
    }

    fun addList(item: List<GetSportResponse>) {
        items.addAll(item)
        Log.d("TEST", "Full List: $fullList")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                filteredList = if (charString.isEmpty()) {
                    ArrayList(null)  // TODO: fullList를 기반으로 필터링이 없을 때 존재하지 않는 리스트(null or "")를 반환
                } else {
                    // TODO: filteringList
                    val filteringList = ArrayList<String>()
                    for (name in fullList) { // 항상 fullList에서 필터링
                        if (name.lowercase().contains(charString.lowercase())) {
                            filteringList.add(name)
                        }
                    }
                    filteringList
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<String>
                Log.d("TEST", "Filtered List: $filteredList")
                notifyDataSetChanged()
            }
        }
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
        fun bind(sport: GetSportResponse) {
            binding.title.text = sport.sportName
            binding.subTitle.text = "${sport.personnel}명 중 ${sport.currentPersonnel}명 참여"
            if (!sport.enabled) {
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
