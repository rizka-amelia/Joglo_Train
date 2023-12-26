package com.example.joglotrain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joglotrain.data.DatabaseContract
import com.example.joglotrain.data.TicketModel
import com.example.joglotrain.databinding.ItemMyTicketBinding
import java.text.NumberFormat
import java.util.Locale

class MyTicketAdapter(private val ticketList: List<TicketModel>, private val onClick: (TicketModel) -> Unit) :
    RecyclerView.Adapter<MyTicketAdapter.MyTicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTicketViewHolder {
        val binding = ItemMyTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyTicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyTicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    inner class MyTicketViewHolder(private val binding: ItemMyTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TicketModel) {
            binding.tvDate.text = data.date
            binding.tvName.text = data.passangerName
            binding.tvDuration.text = data.duration
            binding.tvPrice.text = "Rp ${NumberFormat.getNumberInstance(Locale("in", "ID")).format(data.price * data.jumlahPenumpang)}"
            binding.tvBerangkat.text = if (data.destination == DatabaseContract.TicketColumns.YOGYASOLO) "YK\nYogyakarta" else "SLO\nSolo Balapan"
            binding.tvTujuan.text = if (data.destination == DatabaseContract.TicketColumns.YOGYASOLO) "SLO\n" +
                "Solo Balapan" else "YK\n" +
                "Yogyakarta"
            binding.root.setOnClickListener {
                onClick.invoke(data)
            }
        }
    }
}
