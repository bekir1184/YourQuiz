package com.example.yourquiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yourquiz.R
import com.example.yourquiz.model.DeckModel
import com.example.yourquiz.screens.DeckDirections
import com.example.yourquiz.screens.MainDirections

class DeckAdapter(val deckModels: ArrayList<DeckModel>, val view: View):RecyclerView.Adapter<DeckAdapter.DeckViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckAdapter.DeckViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deck_item,parent,false)
        return DeckViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeckAdapter.DeckViewHolder, position: Int) {
        holder.bindItem(deckModels.get(position),view)
    }

    override fun getItemCount(): Int {
        return deckModels.size
    }
    class DeckViewHolder(view:View):RecyclerView.ViewHolder(view){
        val deck_name:TextView = view.findViewById(R.id.deckName)
        val reply_question :TextView = view.findViewById(R.id.totalQuestion)
        val true_question :TextView = view.findViewById(R.id.trueQuestion)
        val false_question :TextView = view.findViewById(R.id.falseQuestion)
        val button : Button = view.findViewById(R.id.button)
        fun bindItem(item : DeckModel, view: View){
            deck_name.setText(item.deckName)
            reply_question.setText((item.replyList.size).toString())
            true_question.setText((item.trueList.size).toString())
            false_question.setText((item.falseList.size).toString())

            button.setOnClickListener {
                val action = MainDirections.actionMainToDeck(item.id,item.deckName)
                Navigation.findNavController(view).navigate(action)
            }
        }
    }
}