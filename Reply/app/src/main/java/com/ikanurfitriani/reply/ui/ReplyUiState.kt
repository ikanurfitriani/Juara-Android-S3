// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.ui

// Import library, kelas atau file yang dibutuhkan
import com.ikanurfitriani.reply.data.Email
import com.ikanurfitriani.reply.data.MailboxType
import com.ikanurfitriani.reply.data.local.LocalEmailsDataProvider

// Data class untuk menyimpan status UI pada layar Reply
data class ReplyUiState(
    // Map mailboxes berisi data email untuk setiap jenis mailbox
    val mailboxes: Map<MailboxType, List<Email>> = emptyMap(),
    // currentMailbox menyimpan jenis mailbox yang sedang aktif
    val currentMailbox: MailboxType = MailboxType.Inbox,
    // currentSelectedEmail menyimpan email yang sedang dipilih
    val currentSelectedEmail: Email = LocalEmailsDataProvider.defaultEmail,
    // isShowingHomepage menentukan apakah layar utama sedang ditampilkan
    val isShowingHomepage: Boolean = true
) {
    // Properti lazy currentMailboxEmails memberikan daftar email dari mailbox saat ini
    val currentMailboxEmails: List<Email> by lazy { mailboxes[currentMailbox]!! }
}