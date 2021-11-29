package com.example.kotlin_rxjava_.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlin_rxjava_.R
import com.example.kotlin_rxjava_.adapter.BookListAdapter
import com.example.kotlin_rxjava_.model.BookList
import com.example.kotlin_rxjava_.view_model.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var bookListAdapter: BookListAdapter

    lateinit var recyclerView : RecyclerView
    lateinit var inputBookName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        inputBookName = findViewById(R.id.inputBookName)

        initSearchBook()
        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)

            //
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
        }
    }

    fun initSearchBook() {
        inputBookName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
                loadApiData(s.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    fun loadApiData(input: String) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObservable().observe(this, Observer {
            if (it != null) {
                bookListAdapter.setBookList(it.data)
                bookListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getBookListOfData(input)
    }
}