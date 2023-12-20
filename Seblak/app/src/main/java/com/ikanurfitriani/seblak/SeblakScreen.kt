// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak

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
import com.ikanurfitriani.seblak.data.DataSource
import com.ikanurfitriani.seblak.ui.OrderSummaryScreen
import com.ikanurfitriani.seblak.ui.OrderViewModel
import com.ikanurfitriani.seblak.ui.SelectOptionScreen
import com.ikanurfitriani.seblak.ui.StartOrderScreen

// Enum yang merepresentasikan layar-layar dalam aplikasi
enum class SeblakScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Variant(title = R.string.choose_variant),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

// Komponen yang menampilkan AppBar dan tombol kembali jika navigasi kembali dimungkinkan.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeblakAppBar(
    currentScreen: SeblakScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Membuat AppBar
    TopAppBar(
        // Membuat judul AppBar
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        // Membuat tombol kembali jika dibutuhkan
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

// Anotasi yang menandakan bahwa fungsi SeblakApp adalah komponen Composable
@Composable
fun SeblakApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Mendapatkan entri back stack saat ini
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Mendapatkan nama layar saat ini
    val currentScreen = SeblakScreen.valueOf(
        backStackEntry?.destination?.route ?: SeblakScreen.Start.name
    )

    // Untuk membuat tata letak aplikasi dari Jetpack Compose
    Scaffold(
        topBar = {
            SeblakAppBar(
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
            startDestination = SeblakScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Mengimplementasikan Setiap layar sebagai composable dalam NavHost
            composable(route = SeblakScreen.Start.name) {
                // Menampilkan layar untuk memesan seblak
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        // Menentukan jumlah seblak
                        viewModel.setQuantity(it)
                        // Navigasi ke halaman variant
                        navController.navigate(SeblakScreen.Variant.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = SeblakScreen.Variant.name) {
                val context = LocalContext.current
                // Menampilkan layar untuk memilih variant
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(SeblakScreen.Pickup.name) },
                    onCancelButtonClicked = {
                        // Untuk membatalkan pesanan dan navigasi kembali ke layar awal
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    options = DataSource.variants.map { id -> context.resources.getString(id) },
                    onSelectionChanged = { viewModel.setVariant(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = SeblakScreen.Pickup.name) {
                // Menampilkan layar untuk memilih tanggal pengambilan
                SelectOptionScreen(
                    subtotal = uiState.price,
                    onNextButtonClicked = { navController.navigate(SeblakScreen.Summary.name) },
                    onCancelButtonClicked = {
                        // Untuk membatalkan pesanan dan navigasi kembali ke layar awal
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    options = uiState.pickupOptions,
                    onSelectionChanged = { viewModel.setDate(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = SeblakScreen.Summary.name) {
                val context = LocalContext.current
                // Menampilkan layar dari ringkasan pesanan seblak
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
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

// Mengatur ulang [OrderUiState] dan kembali ke [SeblakScreen.Start]
private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(SeblakScreen.Start.name, inclusive = false)
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
            context.getString(R.string.new_seblak_order)
        )
    )
}