package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yourquiz.R
import com.google.android.material.card.MaterialCardView

class ShowQuestion : Fragment() {

    lateinit var trueBtn: View
    lateinit var falseBtn: View
    lateinit var showAnswerBtn: View
    lateinit var answerCard:MaterialCardView
    lateinit var listNameTV:TextView
    val args:ShowQuestionArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_show_question, container, false)
        trueBtn  = view.findViewById(R.id.trueBtn)
        falseBtn = view.findViewById(R.id.falseBtn)
        showAnswerBtn = view.findViewById(R.id.showAnswer)
        answerCard = view.findViewById(R.id.answerCard)
        listNameTV = view.findViewById(R.id.listName)
        showQues()

        val listName = args.listName
        listNameTV.setText(listName)

        showAnswerBtn.setOnClickListener {
            showAnswer()
        }
        trueBtn.setOnClickListener {
            showQues()
        }
        falseBtn.setOnClickListener {
            showQues()
        }

        return view
    }
    fun showAnswer(){
        showAnswerBtn.setVisibility(View.INVISIBLE)
        trueBtn.setVisibility(View.VISIBLE)
        falseBtn.setVisibility(View.VISIBLE)
        answerCard.setVisibility(View.VISIBLE)
    }
    fun showQues(){
        showAnswerBtn.setVisibility(View.VISIBLE)
        trueBtn.setVisibility(View.INVISIBLE)
        falseBtn.setVisibility(View.INVISIBLE)
        answerCard.setVisibility(View.INVISIBLE)
    }


}