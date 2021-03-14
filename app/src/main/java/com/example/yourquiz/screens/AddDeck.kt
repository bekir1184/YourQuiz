package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.yourquiz.R

class AddDeck : Fragment() {

    //lateinit var mainTv:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_add_deck, container, false)
        /*mainTv = view.findViewById(R.id.main)
        mainTv.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addDeck_to_main)
        }*/
        return view
    }

}