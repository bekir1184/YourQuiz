package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourquiz.R
import com.example.yourquiz.adapter.DeckAdapter
import com.example.yourquiz.model.Deck
import com.example.yourquiz.model.Question

class Main : Fragment() {
    //lateinit var addDeckTv:TextView
    lateinit var floating:View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_main, container, false)

        floating = view.findViewById(R.id.floating)

        floating.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_main_to_addDeck)
        }
        var decks = ArrayList<Deck>()
        var trueList = ArrayList<Question>()
        var replyList = ArrayList<Question>()
        var falseList = ArrayList<Question>()

        decks.add(Deck(1,"Deneme",trueList ,replyList ,falseList))
        decks.add(Deck(2,"Falan",trueList ,replyList ,falseList))
        decks.add(Deck(3,"Filan",trueList ,replyList ,falseList))
        decks.add(Deck(3,"Filan",trueList ,replyList ,falseList))

        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter = DeckAdapter(decks,view)


        return view
    }


}