// Nama package dari utils yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.ui.utils

/**
 * Jenis-jenis navigasi yang berbeda yang didukung oleh aplikasi tergantung pada ukuran dan status perangkat.
 */
enum class ReplyNavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Konten yang ditampilkan tergantung pada ukuran dan status perangkat.
 */
enum class ReplyContentType {
    LIST_ONLY, LIST_AND_DETAIL
}