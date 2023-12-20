// Untuk mendefinisikan package dari file ini
package com.ikanurfitriani.cupcake.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Untuk harga per cupcake
private const val PRICE_PER_CUPCAKE = 2.00

// Biaya tambahan untuk pengambilan pada hari yaang sama
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

// [OrderViewModel] menyimpan informasi tentang pesanan cupcake dalam hal kuantitas, rasa, dan
// tanggal pengambilan. Ia juga mengetahui cara menghitung harga total berdasarkan detail pesanan ini
class OrderViewModel : ViewModel() {

    // Cupcake state untuk pemesanan
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    // Untuk menetapkan jumlah [numberCupcakes] cupcakes untuk status pesanan ini dan perbarui harganya
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    // Untuk mengatur [desiredFlavor] cupcake untuk status pesanan ini
    // Hanya 1 rasa yang dapat dipilih untuk keseluruhan pesanan
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    // Menetapkan [pickupDate] untuk status pesanan ini dan perbarui harganya
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

    // Mengembalikan harga yang dihitung berdasarkan detail pesanan
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // Jika pengguna memilih opsi pertama (hari ini) untuk pengambilan, tambahkan biaya tambahan
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
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