package com.example.baseproject.ui.main

import android.os.Bundle
import android.util.Log
import com.example.baseproject.R
import dagger.android.DaggerActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable.add(mainActivityViewModel.showDataFromApi().subscribe(
            {
                Log.d("MainActivity", it.movies.toString())
            }, {
                Log.d("MainActivity", it.message)
            }
        ))

        compositeDisposable.add(mainActivityViewModel.showDataFromSearch("teste").subscribe(
            {
                Log.d("MainActivity", it.movies.toString())
            }, {
                Log.d("MainActivity", it.message)
            }
        ))

        compositeDisposable.add(mainActivityViewModel.showDataFromSearch2("teste").subscribe(
            {
                Log.d("MainActivity", it.movies.toString())
            }, {
                Log.d("MainActivity", it.message)
            }
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}
