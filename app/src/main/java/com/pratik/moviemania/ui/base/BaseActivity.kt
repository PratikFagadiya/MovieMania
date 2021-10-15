package com.pratik.moviemania.ui.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity : AppCompatActivity() {

    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this;
    }

    fun <T : ViewDataBinding> setView(activity: AppCompatActivity, layoutId: Int): T {
        return DataBindingUtil.setContentView(activity, layoutId)
    }
}