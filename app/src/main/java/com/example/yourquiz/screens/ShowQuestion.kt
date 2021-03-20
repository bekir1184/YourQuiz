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
import com.example.yourquiz.model.DeckModel
import com.example.yourquiz.model.Question
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class ShowQuestion : Fragment() {

    lateinit var trueBtn: View
    lateinit var falseBtn: View
    lateinit var showAnswerBtn: View
    lateinit var answerCard:MaterialCardView
    lateinit var listNameTV:TextView
    lateinit var questionNoTW:TextView
    lateinit var questionTW:TextView
    lateinit var answerTW:TextView
    lateinit var rootView: View

    var listSize = 0
    var deckList = ArrayList<DeckModel>()
    val args:ShowQuestionArgs by navArgs()
    var list = ArrayList<Question>()
    var listNo = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView =inflater.inflate(R.layout.fragment_show_question, container, false)
        trueBtn  = rootView.findViewById(R.id.trueBtn)
        falseBtn = rootView.findViewById(R.id.falseBtn)
        showAnswerBtn = rootView.findViewById(R.id.showAnswer)
        answerCard = rootView.findViewById(R.id.answerCard)
        listNameTV = rootView.findViewById(R.id.listName)
        questionNoTW = rootView.findViewById(R.id.questionNo)
        questionTW = rootView.findViewById(R.id.question)
        answerTW = rootView.findViewById(R.id.answer)

        getDecks()
        selectedList(args.listName)
        showQues()

        listNameTV.setText(args.listName)

        showAnswerBtn.setOnClickListener {
            showAnswer()
        }
        trueBtn.setOnClickListener {
            showQues()
            trueAnswer()
        }
        falseBtn.setOnClickListener {
            showQues()
            falseAnswer()
        }

        return rootView
    }
    fun trueAnswer(){

    }
    fun falseAnswer(){

    }
    fun selectedList(name:String){
        when(name){
            "Tekrar Listesi" -> {
                list = deckList.get(args.id).replyList
                listSize = deckList.get(args.id).replyList.size
            }
            "Doğru Listesi"  -> {
                list = deckList.get(args.id).trueList
                listSize = deckList.get(args.id).trueList.size
            }
            else -> {
                list = deckList.get(args.id).falseList
                listSize = deckList.get(args.id).falseList.size
            }
        }
    }
    fun showAnswer(){
        showAnswerBtn.setVisibility(View.INVISIBLE)
        trueBtn.setVisibility(View.VISIBLE)
        falseBtn.setVisibility(View.VISIBLE)
        answerCard.setVisibility(View.VISIBLE)
    }
    fun showQues(){
        if(listNo < listSize){
            showQuestionItem(listNo++)
            showAnswerBtn.setVisibility(View.VISIBLE)
            trueBtn.setVisibility(View.INVISIBLE)
            falseBtn.setVisibility(View.INVISIBLE)
            answerCard.setVisibility(View.INVISIBLE)
        }else{
            var action = ShowQuestionDirections.actionShowQuestionToDeck(args.id, deckList.get(args.id).deckName)
            Navigation.findNavController(rootView).navigate(action)
        }

    }
    fun showQuestionItem(i:Int){
        var question = list.get(i).question
        var answer =  list.get(i).answer
        questionTW.setText(question)
        answerTW.setText(answer)
        questionNoTW.setText((i+1).toString())
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
    private fun getDecks(){
        if(getJsonData()!=null){
            val jsonFileString = getJsonData()
            val gson = Gson()
            val listDeckType = object : TypeToken<ArrayList<DeckModel>>() {}.type
            deckList=gson.fromJson(jsonFileString,listDeckType)
        }
    }


}