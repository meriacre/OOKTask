package com.test.ooktask.ui

import com.test.ooktask.model.PostCardModel

interface PostCardListener {
    fun getPostCards(postcardList: ArrayList<PostCardModel>)
    fun getPostCardsError(e: String)
}