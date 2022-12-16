package com.test.ooktask.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.ooktask.databinding.ActivityMainBinding
import com.test.ooktask.model.Postcard


class MainActivity : AppCompatActivity(), PostCardListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mainPresenter: MainPresenter

    lateinit var mainAdapter: MainAdapter
    private val postCards = ArrayList<Postcard>()
    private var i = 1
    var isLoading: Boolean = false
    var visibleThreshold = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainPresenter = MainPresenter()
        bind()
        setUpAdapter()
        mainPresenter.getPhotos( this, i)

    }


    private fun bind(){
        binding.rvCards.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayout = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = linearLayout.itemCount
                val lastVisibleItem = linearLayout.findLastVisibleItemPosition()
                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    mainPresenter.getPhotos(this@MainActivity, i)
                    isLoading = true
                }
            }
        })
    }

    private fun setUpAdapter() {
        binding.rvCards.layoutManager =  GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        mainAdapter = MainAdapter(postCards, this)
        binding.rvCards.adapter = mainAdapter

    }

    override fun getPostCards(postcardList: ArrayList<Postcard>) {
        Log.d("test", postcardList.toString())
        for (item in postCards){
          item.image =   item.image.substring(4)
            item.image = item.image + ".jpg"}
        postCards.addAll(postcardList)
        mainAdapter.updateData(postCards)
        isLoading = false
        i++
    }

    override fun getPostCardsError(e: String) {
        Log.d("error", e)

    }

}