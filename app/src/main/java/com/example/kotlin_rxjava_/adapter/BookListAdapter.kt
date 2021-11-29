package com.example.kotlin_rxjava_.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_rxjava_.R
import com.example.kotlin_rxjava_.model.Book

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {
    private var bookList = ArrayList<Book>()

    fun setBookList(bookList: ArrayList<Book>) {
        this.bookList = bookList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvPublisher = view.findViewById<TextView>(R.id.tvPublisher)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val thumbImageView = view.findViewById<ImageView>(R.id.thumbImageView)

        fun bind(data: Book) {
            tvTitle.text = data.name_product
            tvPublisher.text = "Rp. " + data.price
            tvDescription.text = data.description

            val url = data.image_url
            Glide.with(thumbImageView).load(url).circleCrop().into(thumbImageView)
        }
    }

}