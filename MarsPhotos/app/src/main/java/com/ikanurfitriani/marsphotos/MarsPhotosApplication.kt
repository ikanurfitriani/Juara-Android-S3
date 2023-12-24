// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.marsphotos

// Import library, kelas atau file yang dibutuhkan
import android.app.Application
import com.ikanurfitriani.marsphotos.data.AppContainer
import com.ikanurfitriani.marsphotos.data.DefaultAppContainer

// Kelas aplikasi Android yang merupakan titik masuk utama untuk aplikasi
class MarsPhotosApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    // Properti yang menyimpan instance dari AppContainer
    lateinit var container: AppContainer
    // Metode yang dipanggil saat aplikasi dibuat
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}