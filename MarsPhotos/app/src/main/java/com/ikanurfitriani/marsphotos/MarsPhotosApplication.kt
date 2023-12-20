package com.ikanurfitriani.marsphotos

import android.app.Application
import com.ikanurfitriani.marsphotos.data.AppContainer
import com.ikanurfitriani.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}