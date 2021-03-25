package com.example.yourquiz.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.yourquiz.R
import com.example.yourquiz.model.DeckModel
import com.example.yourquiz.model.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class AddDeck : Fragment() {
    lateinit var addDeckBtn:Button
    lateinit var deckName: EditText
    var deckList = ArrayList<DeckModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_add_deck, container, false)
        addDeckBtn = view.findViewById(R.id.addDeckBtn)
        deckName  = view.findViewById(R.id.deckNameET)
        getHabits()
        addDeckBtn.setOnClickListener {
            var replyList = ArrayList<Question>()
            var trueList = ArrayList<Question>()
            var falseList = ArrayList<Question>()
            val name = deckName.text.toString()
            deckList.add(DeckModel(deckList.size,name,replyList,trueList,falseList))
            writeJSONFile(deckList)

            val action = AddDeckDirections.actionAddDeckToMain()
            Navigation.findNavController(view).navigate(action)
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