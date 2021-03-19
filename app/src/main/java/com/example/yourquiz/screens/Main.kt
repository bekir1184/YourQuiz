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
import com.example.yourquiz.model.DeckModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class Main : Fragment() {
    //lateinit var addDeckTv:TextView
    lateinit var floating:View
    var decks = ArrayList<DeckModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_main, container, false)

        floating = view.findViewById(R.id.floating)

        floating.setOnClickListener {
            var action = MainDirections.actionMainToAddDeck()
            Navigation.findNavController(view).navigate(action)
        }


        getHabits()

        val recyclerView: RecyclerView =view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter = DeckAdapter(decks,view)


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
            decks=gson.fromJson(jsonFileString,listDeckType)
        }
    }


}