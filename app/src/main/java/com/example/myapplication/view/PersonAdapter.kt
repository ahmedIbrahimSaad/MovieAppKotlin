package com.example.myapplication.view

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.modelkotlin.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.person_list_item.view.*


class PersonAdapter: RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var movies: ArrayList<Result> = arrayListOf()
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        context=parent.context
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_list_item, parent, false))
    }

    override fun getItemCount() =  movies.size


    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185"+movies.get(position).poster_path).into(holder.iv_poster)
        holder.tv_name.text=movies.get(position).title
        if(!TextUtils.isEmpty(movies.get(position).release_date))
        holder.tv_age.text=movies.get(position).release_date
        else holder.tv_age.text=context.getString(R.string.no_date)
        if(!TextUtils.isEmpty(movies.get(position).overview))
        holder.overView.text=movies.get(position).overview
        else holder.overView.text=context.getString(R.string.no_overview)
    }
    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.movie_name
        val tv_age =itemView.release_date
        val overView=itemView.tv_review
        val iv_poster=itemView.iv_poster


    }

    fun setData(people: ArrayList<Result>){
        if (!people.isNullOrEmpty()){
            this.movies = people
        }
        notifyDataSetChanged()
    }

    fun addData(persons: ArrayList<Result>) {
        if (!persons.isNullOrEmpty()){
            this.movies.addAll(persons)
        }
        notifyDataSetChanged()
    }

    fun clearAdapter() {
        this.movies= ArrayList()
        notifyDataSetChanged()
    }
}