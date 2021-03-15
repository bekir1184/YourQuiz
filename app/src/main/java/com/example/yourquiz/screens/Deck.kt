package com.example.yourquiz.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.yourquiz.R
import com.google.android.material.card.MaterialCardView


class Deck : Fragment() {
    lateinit var showQuestion: View
    lateinit var addQuestion: View
    lateinit var replyCard : MaterialCardView
    lateinit var trueCard : MaterialCardView
    lateinit var falseCard : MaterialCardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_deck, container, false)

        showQuestion = view.findViewById(R.id.showQuestionBtn)
        addQuestion = view.findViewById(R.id.addQuestionBtn)
        replyCard = view.findViewById(R.id.replyList)
        trueCard = view.findViewById(R.id.trueList)
        falseCard = view.findViewById(R.id.falseList)


        replyCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(28,75,130))
            falseCard.setCardBackgroundColor(Color.rgb(221,107,77))
            trueCard.setCardBackgroundColor(Color.rgb(221,107,77))
        }
        trueCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(221,107,77))
            falseCard.setCardBackgroundColor(Color.rgb(221,107,77))
            trueCard.setCardBackgroundColor(Color.rgb(28,75,130))

        }
        falseCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(221,107,77))
            falseCard.setCardBackgroundColor(Color.rgb(28,75,130))
            trueCard.setCardBackgroundColor(Color.rgb(221,107,77))

        }
        showQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deck_to_showQuestion)
        }
        addQuestion.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_deck_to_addQuestion)
        }

        return view
    }


}