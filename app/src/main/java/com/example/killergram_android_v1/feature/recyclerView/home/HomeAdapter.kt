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
    private val itemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<HomeAdapter.Holder>(), Filterable {

    companion object {
        // TODO: fullList
        private val fullList = mutableListOf<GetSportResponse>()
    }

    // TODO: filteredList
    private var filteredList = mutableListOf<GetSportResponse>()

    init {
        // 초기화 시 fullList와 filteredList를 unFilteredList로 설정
        fullList.addAll(items)
        filteredList.addAll(items)
        Log.d("TEST", "Full List: $fullList")
    }

    fun addList(item: List<GetSportResponse>) {
        fullList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""
                filteredList = if (query.isEmpty()) {
                    mutableListOf()
                } else {
                    val filteringList = mutableListOf<GetSportResponse>()
                    filteredList.clear()
                    for (date in fullList) {
                        if (date.createdDate.substring(8 until 10) == query) {
                            filteringList.add(date)
                        }
                    }
                    filteringList
                }
                Log.d("TEST", filteredList.toString())
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // 필터링 결과를 filteredList에 반영

                if (results?.values != null) {
                    @Suppress("UNCHECKED_CAST")
                    Log.d("TEST", "Filtered List: $filteredList")
                    filteredList.addAll(results.values as List<GetSportResponse>)
                }

                notifyDataSetChanged() // 화면 갱신
            }
        }
    }


    inner class Holder(
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
