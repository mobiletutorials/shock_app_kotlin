package com.example.countershockkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AudioPickerAdapter(var items:List<AudioModel>, var callback: AudioPickerAdapter.Callback):
    RecyclerView.Adapter<AudioPickerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.audio_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.itemView.setOnClickListener {
            callback.itemSelected(item)
        }

        holder.textView.setText(item.descriptionMessage);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView:TextView

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    interface Callback{
        fun itemSelected(item:AudioModel)
    }

}