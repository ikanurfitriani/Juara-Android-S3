// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.lunchtray.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.lunchtray.model.MenuItem
import com.ikanurfitriani.lunchtray.model.MenuItem.AccompanimentItem
import com.ikanurfitriani.lunchtray.model.MenuItem.EntreeItem
import com.ikanurfitriani.lunchtray.model.MenuItem.SideDishItem
import com.ikanurfitriani.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

// Kelas ViewModel untuk mengelola pesanan
class OrderViewModel : ViewModel() {

    // Menentukan tarif pajak tetap
    private val taxRate = 0.08

    // MutableStateFlow untuk menyimpan dan mengirimkan status UI
    private val _uiState = MutableStateFlow(OrderUiState())
    // StateFlow yang hanya dapat diakses untuk observasi
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    // Fungsi untuk memperbarui entree dalam pesanan
    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    // Fungsi untuk memperbarui side dish dalam pesanan
    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    // Fungsi untuk memperbarui accompaniment dalam pesanan
    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }

    // Fungsi untuk mereset pesanan
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    // Fungsi untuk memperbarui item dalam pesanan
    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            // Harga item sebelumnya, 0.0 jika tidak ada
            val previousItemPrice = previousItem?.price ?: 0.0
            // Menghitung total harga item dengan mengurangkan harga item sebelumnya dan menambahkan harga item baru
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // Menghitung pajak ulang
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is EntreeItem) newItem else currentState.entree,
                sideDish = if (newItem is SideDishItem) newItem else currentState.sideDish,
                accompaniment =
                if (newItem is AccompanimentItem) newItem else currentState.accompaniment
            )
        }
    }
}

// Fungsi ekstensi untuk mengubah Double menjadi format mata uang
fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}