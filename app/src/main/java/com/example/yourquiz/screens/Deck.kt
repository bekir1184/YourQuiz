package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.yourquiz.R


class Deck : Fragment() {
//    lateinit var showQuestion: TextView
//    lateinit var addQuestion: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_deck, container, false)
        /*
        showQuestion = view.findViewById(R.id.showQuestion)
        addQuestion = view.findViewById(R.id.addQuestion)

        showQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deck_to_showQuestion)
        }
        addQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deck_to_addQuestion)
        }*/

        return view
    }


}