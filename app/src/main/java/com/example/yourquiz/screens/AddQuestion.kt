package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.yourquiz.R
import com.example.yourquiz.model.DeckModel
import com.example.yourquiz.model.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class AddQuestion : Fragment() {

    lateinit var questionET:EditText
    lateinit var answerET:EditText
    lateinit var addBtn:Button

    var deckList = ArrayList<DeckModel>()


    val args:AddQuestionArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_add_question, container, false)
        questionET = view.findViewById(R.id.questionET)
        answerET = view.findViewById(R.id.answerET)
        addBtn = view.findViewById(R.id.addQuestionBtn)

        addBtn.setOnClickListener {
            getHabits()

            var questionID=deckList.get(args.id).replyList.size
            var question = Question(questionID,questionET.text.toString(),answerET.text.toString())
            deckList.get(args.id).replyList.add(question)
            writeJSONFile(deckList)
            questionET.setText("")
            answerET.setText("")

        }
        return view
    }
    private fun writeJSONFile(deckModel: List<DeckModel>){
        val fileName = requireContext().cacheDir.absolutePath+"/DeckJson.json"
        var gson = Gson()
        var jsonString:String=gson.toJson(deckModel)
        val file = File(fileName)
        file.writeText(jsonString)
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