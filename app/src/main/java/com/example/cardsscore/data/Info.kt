package com.example.cardsscore.data


class PlayerInfo{
    private var info = mutableMapOf("" to 0)
    fun addScore(name:String, score:Int){
        info[name] = info[name]!! + score
    }
    fun getScore(name:String):Int{
        return info[name]!!
    }
    fun addPlayer(name:String){
        info[name] = 0
    }
    fun removePlayer(name:String){
        info.remove(name)
    }
}