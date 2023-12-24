// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ikanurfitriani.reply.data.Email
import com.ikanurfitriani.reply.data.MailboxType
import com.ikanurfitriani.reply.ui.utils.ReplyContentType
import com.ikanurfitriani.reply.ui.utils.ReplyNavigationType

// Mendefinisikan fungsi komposabel bernama ReplyApp
@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    // Mendeklarasikan variabel untuk menampung jenis navigasi dan tipe konten yang sesuai dengan ukuran jendela.
    val navigationType: ReplyNavigationType
    val contentType: ReplyContentType
    // Membuat instance dari ViewModel untuk mengelola data aplikasi.
    val viewModel: ReplyViewModel = viewModel()
    // Mengambil data UI dari ViewModel sebagai StateFlow dan mengonversinya menjadi State.
    val replyUiState = viewModel.uiState.collectAsState().value

    // Menentukan jenis navigasi dan tipe konten berdasarkan ukuran jendela.
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = ReplyContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ReplyContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.LIST_ONLY
        }
    }
    // Menampilkan layar utama aplikasi dengan menggunakan komposabel ReplyHomeScreen.
    ReplyHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        replyUiState = replyUiState,
        // Menggunakan lambda untuk menangani tab yang dipilih pada layar utama.
        onTabPressed = { mailboxType: MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType = mailboxType)
            viewModel.resetHomeScreenStates()
        },
        // Menggunakan lambda untuk menangani klik pada kartu email.
        onEmailCardPressed = { email: Email ->
            viewModel.updateDetailsScreenStates(
                email = email
            )
        },
        // Menggunakan lambda untuk menangani penekanan tombol kembali pada layar detail.
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}