// Nama package dari aplikasi yang dibuat
package com.ikanurfitriani.cupcake

// Import library, kelas atau file yang dibutuhkan
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ikanurfitriani.cupcake.data.DataSource
import com.ikanurfitriani.cupcake.ui.OrderSummaryScreen
import com.ikanurfitriani.cupcake.ui.OrderViewModel
import com.ikanurfitriani.cupcake.ui.SelectOptionScreen
import com.ikanurfitriani.cupcake.ui.StartOrderScreen

// Enum yang merepresentasikan layar-layar dalam aplikasi
enum class CupcakeScreen(@StringRes val title: Int) {
    // Mengambil judul dari sumber daya string
    Start(title = R.string.app_name),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

// Komponen yang menampilkan AppBar dan tombol kembali jika navigasi kembali dimungkinkan
// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    // Enumerasi yang mewakili layar saat ini dalam aplikasi
    currentScreen: CupcakeScreen,
    // Untuk menunjukkan apakah ada kemungkinan navigasi ke layar sebelumnya
    canNavigateBack: Boolean,
    // Fungsi yang dipanggil ketika tombol navigasi (kembali) ditekan
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Membuat AppBar
    TopAppBar(
        // Menampilkan judul AppBar dari sumber daya string
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            // Untuk menggunakan warna dari tema Material
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        // Membuat tombol kembali jika dibutuhkan
        navigationIcon = {
            if (canNavigateBack) {
                // Untuk menampilkan ikon dan dapat di-klik
                IconButton(onClick = navigateUp) {
                    // Untuk menampilkan ikon
                    Icon(
                        // Menunjukkan ikon panah kembali
                        imageVector = Icons.Filled.ArrowBack,
                        // Untuk memberikan deskripsi konten untuk keperluan aksesibilitas
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

// Anotasi yang menandakan bahwa fungsi CupcakeApp adalah komponen Composable
@Composable
fun CupcakeApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Mendapatkan entri back stack saat ini
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Mendapatkan nama layar saat ini
    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )

    // Untuk membuat tata letak aplikasi dari Jetpack Compose
    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        // Mengambil status UI dari ViewModel sebagai state
        val uiState by viewModel.uiState.collectAsState()

        // Menangani navigasi antara destinasi berdasarkan route dan back stack
        NavHost(
            navController = navController,
            startDestination = CupcakeScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Mengimplementasikan Setiap layar sebagai composable dalam NavHost
            composable(route = CupcakeScreen.Start.name) {
                // Menampilkan layar untuk memesan cupcake
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        // Menentukan jumlah cupcake
                        viewModel.setQuantity(it)
                        // Navigasi ke halaman rasa
                        navController.navigate(CupcakeScreen.Flavor.name)
                    },
                    modifier = Modifier
                        // Untuk mengisi seluruh lebar dan tinggi yang tersedia di ruang yang diberikan
                        .fillMaxSize()
                        // Untuk memberikan padding sesuai dengan dimensi yang diambil dari sumber daya
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = CupcakeScreen.Flavor.name) {
                val context = LocalContext.current
                // Menampilkan layar untuk memilih rasa
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
                    onCancelButtonClicked = {
                        // Untuk membatalkan pesanan dan navigasi kembali ke layar awal
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setFlavor(it) },
                    // Untuk mengisi seluruh tinggi yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = CupcakeScreen.Pickup.name) {
                // Menampilkan layar untuk memilih tanggal pengambilan
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) },
                    onCancelButtonClicked = {
                        // Untuk membatalkan pesanan dan navigasi kembali ke layar awal
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    options = uiState.pickupOptions,
                    onSelectionChanged = { viewModel.setDate(it) },
                    // Untuk mengisi seluruh tinggi yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = CupcakeScreen.Summary.name) {
                val context = LocalContext.current
                // Menampilkan layar dari ringkasan pesanan cupcake
                OrderSummaryScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = {
                        // Untuk membatalkan pesanan dan navigasi kembali ke layar awal
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onSendButtonClicked = { subject: String, summary: String ->
                        // Berbagi ringkasan pesanan melalui berbagai aplikasi
                        shareOrder(context, subject = subject, summary = summary)
                    },
                    // Untuk mengisi seluruh tinggi yang tersedia dalam konteksnya
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

// Mengatur ulang [OrderUiState] dan kembali ke [CupcakeScreen.Start]
private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(CupcakeScreen.Start.name, inclusive = false)
}

// Membuat intent untuk berbagi rincian pesanan
private fun shareOrder(context: Context, subject: String, summary: String) {
    // Membuat intent implisit ACTION_SEND dengan rincian pesanan di dalam intent extras
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}