// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.affirmations.data

// Import library yang akan digunakan
import com.ikanurfitriani.affirmations.R
import com.ikanurfitriani.affirmations.model.Affirmation

// Membuat class Datasource
class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        // Menampilkan daftar affirmation dan gambarnya
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2),
            Affirmation(R.string.affirmation3, R.drawable.image3),
            Affirmation(R.string.affirmation4, R.drawable.image4),
            Affirmation(R.string.affirmation5, R.drawable.image5),
            Affirmation(R.string.affirmation6, R.drawable.image6),
            Affirmation(R.string.affirmation7, R.drawable.image7),
            Affirmation(R.string.affirmation8, R.drawable.image8),
            Affirmation(R.string.affirmation9, R.drawable.image9),
            Affirmation(R.string.affirmation10, R.drawable.image10))
    }
}