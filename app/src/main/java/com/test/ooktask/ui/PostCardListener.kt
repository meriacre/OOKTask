package com.test.ooktask.ui

import com.test.ooktask.model.Postcard

interface PostCardListener {
    fun getPostCards(postcardList: List<Postcard>)
    fun getPostCardsError(e: String)
}