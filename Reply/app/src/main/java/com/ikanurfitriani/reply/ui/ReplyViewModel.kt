// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.ui

// Import library, kelas atau file yang dibutuhkan
import androidx.lifecycle.ViewModel
import com.ikanurfitriani.reply.data.Email
import com.ikanurfitriani.reply.data.MailboxType
import com.ikanurfitriani.reply.data.local.LocalEmailsDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// Kelas ReplyViewModel adalah kelas turunan dari ViewModel, digunakan untuk mengelola logika dan status UI pada layar Reply
class ReplyViewModel : ViewModel() {

    // MutableStateFlow yang menyimpan status UI pada layar Reply
    private val _uiState = MutableStateFlow(ReplyUiState())
    // StateFlow yang dapat di-observe untuk mendapatkan status UI saat ini
    val uiState: StateFlow<ReplyUiState> = _uiState

    // Blok inisialisasi yang dipanggil saat objek ReplyViewModel dibuat
    init {
        // Inisialisasi status UI
        initializeUIState()
    }

    // Fungsi untuk menginisialisasi status UI pada layar Reply
    private fun initializeUIState() {
        // Membuat map mailboxes yang mengelompokkan email berdasarkan jenis mailbox
        val mailboxes: Map<MailboxType, List<Email>> =
            LocalEmailsDataProvider.allEmails.groupBy { it.mailbox }
        // Menginisialisasi _uiState dengan ReplyUiState awal
        _uiState.value =
            ReplyUiState(
                mailboxes = mailboxes,
                // Memilih email pertama dari jenis mailbox Inbox sebagai email terpilih saat ini
                currentSelectedEmail = mailboxes[MailboxType.Inbox]?.get(0)
                    ?: LocalEmailsDataProvider.defaultEmail
            )
    }

    // Fungsi untuk memperbarui status UI saat layar detail email diakses
    fun updateDetailsScreenStates(email: Email) {
        // Memperbarui _uiState dengan mengganti email terpilih dan mengubah isShowingHomepage menjadi false
        _uiState.update {
            it.copy(
                currentSelectedEmail = email,
                isShowingHomepage = false
            )
        }
    }

    // Fungsi untuk mengatur ulang status UI saat kembali ke layar utama
    fun resetHomeScreenStates() {
        // Memperbarui _uiState dengan memilih email pertama dari jenis mailbox yang sedang aktif dan mengatur isShowingHomepage menjadi true
        _uiState.update {
            it.copy(
                currentSelectedEmail = it.mailboxes[it.currentMailbox]?.get(0)
                    ?: LocalEmailsDataProvider.defaultEmail,
                isShowingHomepage = true
            )
        }
    }

    // Fungsi untuk memperbarui jenis mailbox yang sedang aktif
    fun updateCurrentMailbox(mailboxType: MailboxType) {
        // Memperbarui _uiState dengan mengganti jenis mailbox yang sedang aktif
        _uiState.update {
            it.copy(
                currentMailbox = mailboxType
            )
        }
    }
}