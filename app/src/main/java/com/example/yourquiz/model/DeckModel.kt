package com.example.yourquiz.model


data class DeckModel(var id:Int, var deckName:String, var replyList:ArrayList<Question>, var trueList:ArrayList<Question>, var falseList:ArrayList<Question>)
