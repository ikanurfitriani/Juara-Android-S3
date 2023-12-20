// Nama package dari file yang dibuat
package com.ikanurfitriani.woof.ui.theme

// Import library yang akan digunakan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Untuk mendeklarasikan objek Shapes yang berisi definisi beberapa bentuk
val Shapes = Shapes(
    // Mendefinisikan bentuk kecil (small) sebagai sudut lengkungan dengan radius sudut sebesar 50.dp
    small = RoundedCornerShape(50.dp),
    // Mendefinisikan bentuk medium (medium) sebagai sudut lengkungan berbeda untuk sudut bottom start dan top end
    medium = RoundedCornerShape(bottomStart = 16.dp, topEnd = 16.dp)
)