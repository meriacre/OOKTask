package com.test.ooktask.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.ooktask.databinding.ActivityMainBinding
import com.test.ooktask.model.PostCardModel


class MainActivity : AppCompatActivity(), PostCardListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mainPresenter: MainPresenter

    lateinit var mainAdapter: MainAdapter
    val postCardList = ArrayList<PostCardModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter()
        bind()
        setUpAdapter()

    }


    private fun bind(){
        binding.tvHello.setOnClickListener{
            mainPresenter.getPhotos( this, 1)
        }
    }

    private fun setUpAdapter() {
        binding.rvCards.layoutManager =  GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        mainAdapter = MainAdapter(postCardList, this)
        binding.rvCards.adapter = mainAdapter
    }

    override fun getPostCards(postcardList: ArrayList<PostCardModel>) {
        postcardList.size
        Log.d("test", postcardList.toString())
    }

    override fun getPostCardsError(e: String) {

    }


}