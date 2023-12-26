package com.example.joglotrain

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joglotrain.data.DatabaseContract.TicketColumns.Companion.YOGYASOLO
import com.example.joglotrain.data.TicketModel
import com.example.joglotrain.databinding.ItemTicketBinding
import com.example.joglotrain.helper.MappingHelper
import java.text.NumberFormat
import java.util.Locale

class TicketAdapter(private val ticketList: List<TicketModel>, private val destination: String) :
    RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    inner class TicketViewHolder(private val binding: ItemTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TicketModel) {
            binding.tvTrainName.text = data.trainName
            binding.tvPrice.text = "Rp ${NumberFormat.getNumberInstance(Locale("in", "ID")).format(data.price)}/org"
            binding.tvDuration.text = data.duration
            binding.btnKeberangkatan.text = if (destination == YOGYASOLO) "YK" else "SLO"
            binding.btnTujuan.text = if (destination == YOGYASOLO) "SLO" else "YK"

            binding.root.setOnClickListener {
                MappingHelper.hargaTicket = data.price
                MappingHelper.duration = data.duration
                MappingHelper.destination = data.destination
                val intent = Intent(binding.root.context, CheckoutTicket::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding =
            ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val ticket = ticketList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }
}
