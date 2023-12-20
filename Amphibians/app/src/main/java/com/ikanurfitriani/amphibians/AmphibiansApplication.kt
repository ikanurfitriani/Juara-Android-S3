package com.ikanurfitriani.amphibians

import android.app.Application
import com.ikanurfitriani.amphibians.data.AppContainer
import com.ikanurfitriani.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}