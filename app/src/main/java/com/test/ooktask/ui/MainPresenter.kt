package com.test.ooktask.ui

import android.util.Log
import com.test.ooktask.api.RetrofitInstance
import com.test.ooktask.model.Postcard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter {

    private var postCardsList: List<Postcard> = ArrayList()

    fun getPhotos(listener: PostCardListener, page: Int) {

        MainScope().launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance().api.geImages(3, page)
                postCardsList = response.data.postcards
                withContext(Dispatchers.Main) {
                    listener.getPostCards(postCardsList)
                }
            } catch (e: Exception) {
                Log.e("error", "${e.message}")
            }
        }
    }

}