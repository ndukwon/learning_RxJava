package com.duk.lab.rxandroid

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duk.lab.rxandroid.R

/**
 * A placeholder fragment containing a simple view.
 */
class RxAndroidBasicActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rx_android_basic, container, false)
    }
}
