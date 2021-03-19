package com.example.yourquiz.screens

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yourquiz.R
import com.example.yourquiz.model.DeckModel
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class Deck : Fragment() {
    lateinit var showQuestion: View
    lateinit var addQuestion: View
    lateinit var replyCard : MaterialCardView
    lateinit var trueCard : MaterialCardView
    lateinit var falseCard : MaterialCardView
    lateinit var replyListSize:TextView
    lateinit var trueListSize:TextView
    lateinit var falseListSize:TextView
    lateinit var deckName:TextView
    var deckList=ArrayList<DeckModel>()

    val args:DeckArgs by navArgs()

    var selectedList="Tekrar Listesi"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_deck, container, false)
        deckName = view.findViewById(R.id.deckName)
        showQuestion = view.findViewById(R.id.showQuestionBtn)
        addQuestion = view.findViewById(R.id.addQuestionBtn)
        replyCard = view.findViewById(R.id.replyList)
        trueCard = view.findViewById(R.id.trueList)
        falseCard = view.findViewById(R.id.falseList)
        replyListSize = view.findViewById(R.id.replyListSize)
        trueListSize = view.findViewById(R.id.trueListSize)
        falseListSize = view.findViewById(R.id.falseListSize)

        deckName.setText(args.deckName)
        getHabits()
        var rlSize=deckList.get(args.id).replyList.size
        var tSize=deckList.get(args.id).trueList.size
        var fSize=deckList.get(args.id).falseList.size
        replyListSize.setText(rlSize.toString())
        trueListSize.setText(tSize.toString())
        falseListSize.setText(fSize.toString())


        replyCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(28,75,130))
            falseCard.setCardBackgroundColor(Color.rgb(221,107,77))
            trueCard.setCardBackgroundColor(Color.rgb(221,107,77))
            selectedList="Tekrar Listesi"
        }
        trueCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(221,107,77))
            falseCard.setCardBackgroundColor(Color.rgb(221,107,77))
            trueCard.setCardBackgroundColor(Color.rgb(28,75,130))
            selectedList="Doğru Listesi"
        }
        falseCard.setOnClickListener {
            replyCard.setCardBackgroundColor(Color.rgb(221,107,77))
            falseCard.setCardBackgroundColor(Color.rgb(28,75,130))
            trueCard.setCardBackgroundColor(Color.rgb(221,107,77))
            selectedList="Yanlış Listesi"
        }
        showQuestion.setOnClickListener {

            val action = DeckDirections.actionDeckToShowQuestion()
            action.listName=selectedList
            Navigation.findNavController(view).navigate(action)
        }
        addQuestion.setOnClickListener {
            val action = DeckDirections.actionDeckToAddQuestion(args.id)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
    private fun getJsonData():String?{
        val fileName = requireContext().cacheDir.absolutePath+"/DeckJson.json"
        val jsonString:String
        try {
            jsonString = File(fileName).bufferedReader().use { it.readText() }
        }catch (ex:Exception){
            ex.printStackTrace()
            return null
        }
        return jsonString
    }
    private fun getHabits(){
        if(getJsonData()!=null){
            val jsonFileString = getJsonData()
            val gson = Gson()
            val listDeckType = object : TypeToken<ArrayList<DeckModel>>() {}.type
            deckList=gson.fromJson(jsonFileString,listDeckType)
        }
    }


}