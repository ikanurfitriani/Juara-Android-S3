// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.amphibians

// Import library, kelas atau file yang dibutuhkan
import android.app.Application
import com.ikanurfitriani.amphibians.data.AppContainer
import com.ikanurfitriani.amphibians.data.DefaultAppContainer

// Kelas aplikasi Android yang merupakan titik masuk utama untuk aplikasi
class AmphibiansApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    // Properti yang menyimpan instance dari AppContainer
    lateinit var container: AppContainer
    // Metode yang dipanggil saat aplikasi dibuat
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}