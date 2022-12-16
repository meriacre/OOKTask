package com.test.ooktask.ui

import android.util.Log
import com.test.ooktask.api.RetrofitInstance
import com.test.ooktask.model.PostCardModel
import com.test.ooktask.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainPresenter {

    private var postCardsList: ArrayList<PostCardModel> = ArrayList()

    fun getPhotos(listener: PostCardListener, page: Int){

        MainScope().launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance().api.geImages(3, page)
                if (response.isSuccessful){
                    val data = response.body()!!
                    Log.d("CharacterList:", data.toString())
                    val newText = data.toString().replace("/", "")
                    jsonToObj(newText)
                    withContext(Dispatchers.Main){
                        listener.getPostCards(postCardsList)
                    }
                }
                else {
                    Log.d("error", response.code().toString())
                }}catch (e: java.net.SocketTimeoutException){
                Log.e("NoInternet", "No Internet Connection: ${e.message}")
            }catch (e:Exception){
                Log.e("error", "${e.message}")
            }
        }
    }

    fun jsonToObj(json:String){
        val jsonObj = JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1))
        val myJson = jsonObj.getJSONArray("data")
        for (i in 0..myJson!!.length() - 1) {
            val id = myJson.getJSONObject(i).getString("id").toInt()
            val url = myJson.getJSONObject(i).getString("image")
            postCardsList.add(PostCardModel(id, url))
        }
    }

}