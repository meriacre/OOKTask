package com.test.ooktask.ui

import com.test.ooktask.model.Postcard

interface PostCardListener {
    fun getPostCards(postcardList: ArrayList<Postcard>)
    fun getPostCardsError(e: String)
}