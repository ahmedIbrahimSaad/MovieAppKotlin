package com.example.myapplication.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.MovieRemoteDataSource
import com.example.myapplication.modelkotlin.ResponseModel
import com.example.myapplication.modelkotlin.Result
import com.franmontiel.localechanger.LocaleChanger
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var page = 1
    private val movieDataSource = MovieRemoteDataSource()
    private var persons = ArrayList<Result>()
    private val adapter = PersonAdapter()

    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayoutManager = LinearLayoutManager(this@MainActivity)
        search_list.layoutManager = linearLayoutManager
        search_list.adapter = adapter
        adapter.setData(persons)
        et_search.setOnClickListener{
            et_search.showPopupWindow()
        }
        search_list.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getMoreItems()
            }


        })
        btn_search.setOnClickListener {
            adapter.clearAdapter()
            if (!TextUtils.isEmpty(et_search.text))
                getPersons()
            else{
                et_search.setError(this.getString(R.string.emptydata))
                adapter.clearAdapter()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lang_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.arabic ->{LocaleChanger.setLocale(Locale("ar"))
            startActivity(Intent(this,MainActivity::class.java))
                finish()}
            R.id.english->{LocaleChanger.setLocale(Locale("en"))
                startActivity(Intent(this,MainActivity::class.java))
                finish()}
        }
        return super.onOptionsItemSelected(item)

    }
    private fun getPersons() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.INTERNET
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.INTERNET),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            movieDataSource.getMoviesByName(
                "c3757a2fa6f856cf857f86b6376c5396",
                et_search.text!!.toString(),
                page
            ).enqueue(
                object : Callback<ResponseModel> {
                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        Log.d("TAG", call.request().body().toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        Log.d("TAG", response.isSuccessful.toString())
                        if (page < response.body()?.total_pages!!) {
                            page++
                            adapter.addData(response.body()?.results as ArrayList<Result>)
                        }
                    }

                })
        }
    }

    private fun getMoreItems() {
        getPersons()
    }
    override fun attachBaseContext(newBase: Context?) {
        var newBase = newBase
        newBase = LocaleChanger.configureBaseContext(newBase)
        super.attachBaseContext(newBase)
    }
}
