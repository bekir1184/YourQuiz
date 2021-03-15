package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.yourquiz.R


class AddQuestion : Fragment() {

    //lateinit var deck:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_add_question, container, false)
        /*
        deck = view.findViewById(R.id.deck)
        deck.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addQuestion_to_deck)
        }*/
        return view
    }


}