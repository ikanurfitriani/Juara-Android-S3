// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.superheroes

// Import library, kelas atau file yang dibutuhkan
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ikanurfitriani.superheroes.model.Hero
import com.ikanurfitriani.superheroes.model.HeroesRepository
import com.ikanurfitriani.superheroes.ui.theme.SuperheroesTheme

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalAnimationApi::class)
// Mendefinisikan fungsi komposabel bernama HeroesList
@Composable
fun HeroesList(
    // List dari objek Hero yang akan ditampilkan dalam daftar
    heroes: List<Hero>,
    // Untuk menyesuaikan tata letak dan penampilan
    modifier: Modifier = Modifier,
    // Menentukan padding untuk konten dalam daftar
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    // Untuk menyimpan dan mengelola state
    val visibleState = remember {
        // Untuk mengelola transisi antara state
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            // tujuan akhir animasi atau transisi
            targetState = true
        }
    }

    // Menerapkan animasi efek fade in untuk seluruh daftar
    AnimatedVisibility(
        // Mengaitkan state visibleState yang telah dibuat sebelumnya dengan AnimatedVisibility
        visibleState = visibleState,
        // Menentukan efek animasi masuk
        enter = fadeIn(
            // Menentukan spesifikasi animasi untuk efek fade in menggunakan spring
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        // Menentukan efek animasi keluar
        exit = fadeOut(),
        // Mengontrol tata letak dan penampilan animasi
        modifier = modifier
    ) {
        // Daftar item dihasilkan secara lazy
        LazyColumn(contentPadding = contentPadding) {
            // Membuat item daftar menggunakan itemsIndexed
            itemsIndexed(heroes) { index, hero ->
                // Membuat elemen daftar untuk setiap pahlawan
                HeroListItem(
                    // Mengaitkan pahlawan yang sesuai dengan item daftar saat ini
                    hero = hero,
                    // Mengontrol tata letak dan penampilan item daftar
                    modifier = Modifier
                        // Menentukan padding horizontal dan vertikal untuk setiap item daftar
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        // Untuk memberikan efek animasi masuk dan keluar
                        .animateEnterExit(
                            // Menentukan efek animasi masuk dimana item akan terlihat bergeser ke dalam secara vertikal
                            enter = slideInVertically(
                                // Menentukan spesifikasi animasi menggunakan spring untuk efek yang lebih dinamis
                                animationSpec = spring(
                                    // Menentukan kekakuan pegas
                                    stiffness = StiffnessVeryLow,
                                    // Menentukan rasio redaman pegas
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                // Memberikan offset awal pada sumbu Y dengan meresapi indeks item dan menghasilkan efek masuk bertahap
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

// Mendefinisikan fungsi komposabel bernama HeroesListItem
@Composable
fun HeroListItem(
    hero: Hero,
    // Untuk mengontrol tata letak dan penampilan
    modifier: Modifier = Modifier
) {
    // Membuat elemen kartu
    Card(
        // Menentukan ketinggian elemen kartu untuk memberikan efek terangkat
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        // Mengontrol tata letak dan penampilan kartu
        modifier = modifier,
    ) {
        // Untuk menyusun konten dalam kartu secara horizontal
        Row(
            // Untuk mengontrol tata letak dan penampilan
            modifier = Modifier
                // Mengisi lebar baris agar mengisi seluruh lebar kartu
                .fillMaxWidth()
                // Menentukan padding sebesar 16 dp untuk memberikan ruang
                .padding(16.dp)
                // Menentukan ketinggian minimum baris
                .sizeIn(minHeight = 72.dp)
        ) {
            // Untuk menyusun konten dalam baris secara vertikal dan memberikan bobot sebesar 1f agar dapat memanfaatkan sisa ruang yang tersedia
            Column(modifier = Modifier.weight(1f)) {
                // Membuat elemen teks
                Text(
                    // Untuk menampilkan teks dengan menggunakan sumber daya string
                    text = stringResource(hero.nameRes),
                    // Memberikan gaya tipografi display small pada text
                    style = MaterialTheme.typography.displaySmall
                )
                // Membuat elemen teks
                Text(
                    // Untuk menampilkan teks dengan menggunakan sumber daya string
                    text = stringResource(hero.descriptionRes),
                    // Memberikan gaya tipografi body large pada text
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            // Menambahkan elemen spasi horizontal sebesar 16 dp untuk memberikan jarak
            Spacer(Modifier.width(16.dp))
            // Membuat elemen kotak (Box) untuk menyusun elemen gambar
            Box(
                // Menetapkan modifier untuk mengontrol ukuran dan bentuk elemen kotak
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                // Membuat elemen gambar
                Image(
                    // Mendapatkan painter untuk gambar pahlawan menggunakan ID sumber daya gambar
                    painter = painterResource(hero.imageRes),
                    // Menetapkan deskripsi konten gambar sebagai null
                    contentDescription = null,
                    // Menetapkan alignment (penyelarasan) gambar ke bagian atas dan tengah kotak
                    alignment = Alignment.TopCenter,
                    // Menetapkan skala konten untuk mengisi lebar kotak
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

// Menampilkan dengan pratinjau tema terang
@Preview("Light Theme")
// Menampilkan dengan pratinjau tema gelap
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
// Menandakan bahwa fungsi HeroPreview adalah komposabel
@Composable
fun HeroPreview() {
    // Membuat objek Hero dengan menggunakan ID sumber daya untuk nama, deskripsi, dan gambar
    val hero = Hero(
        R.string.hero1,
        R.string.description1,
        R.drawable.android_superhero1
    )
    // Membuka blok komposabel tema SuperheroesTheme
    SuperheroesTheme {
        // Memanggil fungsi HeroListItem dengan menggunakan objek Hero yang telah dibuat
        HeroListItem(hero = hero)
    }
}
// Menampilkan pratinjau untuk daftar pahlawan
@Preview("Heroes List")
// Menandakan bahwa fungsi HeroPreview adalah komposabel
@Composable
fun HeroesPreview() {
    // Mengatur tema menjadi tema terang
    SuperheroesTheme(darkTheme = false) {
        // Membuat elemen permukaan (Surface) untuk menempatkan kontennya
        Surface (
            // Menetapkan warna latar belakang permukaan sesuai dengan warna latar belakang dari tema Material
            color = MaterialTheme.colorScheme.background
        ) {
            // Memanggil fungsi HeroesList dengan menggunakan daftar pahlawan yang diperoleh dari HeroesRepository
            HeroesList(heroes = HeroesRepository.heroes)
        }
    }
}