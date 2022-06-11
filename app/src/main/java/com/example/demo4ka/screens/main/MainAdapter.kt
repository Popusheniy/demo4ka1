package com.example.demo4ka.screens.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo4ka.R
import com.example.demo4ka.databinding.NoteItemBinding
import com.example.demo4ka.model.AppNote


private var mListNotes = emptyList<AppNote>()

class MainAdapter(private val context:Context) : RecyclerView.Adapter<MainAdapter.MainHolder>() {
    class MainHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    var onNoteItemClickListener: ((AppNote) -> Unit)? = null

    override fun onViewAttachedToWindow(holder: MainHolder) {
        //  super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.click(mListNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val binding = holder.binding
        val note = mListNotes[position]
        val troubleStr = context.resources.getString(R.string.counter_text)
        binding.itemNoteName.text = note.name
        binding.itemNoteText.text = note.text
        binding.itemNoteText2.text = note.text2


        binding.btnCounter.setOnClickListener {
            onNoteItemClickListener?.invoke(note)
        }
        binding.counter.text = String.format(troubleStr, note.troubleCount)

    }

    override fun getItemCount(): Int = mListNotes.size

    fun setList(list: List<AppNote>) {
        mListNotes = list
        notifyDataSetChanged()
    }

}




