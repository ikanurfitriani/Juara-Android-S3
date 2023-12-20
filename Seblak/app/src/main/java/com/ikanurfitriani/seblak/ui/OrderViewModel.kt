// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.seblak.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.seblak.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Untuk harga per seblak
private const val PRICE_PER_SEBLAK = 15000

// Biaya tambahan untuk pengambilan pada hari yaang sama
private const val PRICE_FOR_SAME_DAY_PICKUP = 3000


// [OrderViewModel] menyimpan informasi tentang pesanan seblak dalam hal kuantitas, variant, dan
// tanggal pengambilan. Ia juga mengetahui cara menghitung harga total berdasarkan detail pesanan ini
class OrderViewModel : ViewModel() {

    // Seblak state untuk pemesanan
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    // Digunakan untuk mengatur jumlah seblak dalam pesanan
    fun setQuantity(numberSeblak: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberSeblak,
                price = calculatePrice(quantity = numberSeblak)
            )
        }
    }

    // Digunakan untuk mengatur varian seblak dalam status UI pesanan
    fun setVariant(desiredVariant: String) {
        _uiState.update { currentState ->
            currentState.copy(variant = desiredVariant)
        }
    }

    // Digunakan untuk mengatur tanggal pengambilan dalam status UI pesanan
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    // Digunakan untuk mereset status UI pesanan menjadi nilai awal
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    // Menghitung harga total berdasarkan detail pesanan seperti jumlah seblak dan tanggal pengambilan
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_SEBLAK
        // Jika pengguna memilih opsi pertama (hari ini) untuk pengambilan, biaya tambahan akan ditambahkan
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        val formattedPrice = currencyFormat.format(calculatedPrice)
        return formattedPrice
    }

    // Mengembalikan daftar opsi tanggal yang dimulai dengan tanggal sekarang dan 3 tanggal berikutnya
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Menambahkan tanggal sekarang dan 3 tanggal berikutnya
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}