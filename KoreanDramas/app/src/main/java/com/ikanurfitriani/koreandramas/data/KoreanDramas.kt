// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.koreandramas.data

// Import library yang akan digunakan
import com.ikanurfitriani.koreandramas.R
import com.ikanurfitriani.koreandramas.model.Drama

// Menampilkan daftar nama, tahun rilis, gambar dan deskripsi dari drama korea detektif terbaik
object KoreanDramas {
    val dramas = listOf(
        Drama(R.string.decoy, 2023, R.drawable.dramadecoy, R.string.decoy_description),
        Drama(R.string.mouse, 2021, R.drawable.mouse, R.string.mouse_description),
        Drama(R.string.tunnel, 2017, R.drawable.tunnel, R.string.tunnel_description),
        Drama(R.string.partnersforjustice, 2014, R.drawable.partners_for_justice, R.string.partnersforjustice_description),
        Drama(R.string.vagabond, 2019, R.drawable.vagabond, R.string.vagabond_description),
        Drama(R.string.secretroyalinspectorandjoy, 2021, R.drawable.secretroyalinspectorandjoy, R.string.secretroyalinspectorandjoy_description),
        Drama(R.string.lawschool, 2021, R.drawable.lawschool, R.string.lawschool_description),
        Drama(R.string.tellmewhatyousaw, 2020, R.drawable.tellmewhatyousaw, R.string.tellmewhatyousaw_description),
        Drama(R.string.thegooddetective, 2020, R.drawable.the_good_detective, R.string.thegooddetective_description),
        Drama(R.string.beyondevil, 2021, R.drawable.beyondevil, R.string.beyondevil_description),
        Drama(R.string.stranger, 2017, R.drawable.stranger, R.string.stranger_description),
        Drama(R.string.inspectorkoo, 2021, R.drawable.inspectorkoo, R.string.inspectorkoo_description),
    )
}