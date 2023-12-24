// Nama package dari data yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.data

// Import library, kelas atau file yang dibutuhkan
import androidx.annotation.StringRes

/**
 * Kelas data sederhana untuk merepresentasikan sebuah Email.
 */
data class Email(
    /** ID unik dari email **/
    val id: Long,
    /** Pengirim email **/
    val sender: Account,
    /** Penerima email **/
    val recipients: List<Account> = emptyList(),
    /** Judul email **/
    @StringRes val subject: Int = -1,
    /** Isi konten email **/
    @StringRes val body: Int = -1,
    /** Kotak surat tempat email disimpan **/
    var mailbox: MailboxType = MailboxType.Inbox,
    /**
     * Durasi relatif sejak email dibuat. (contoh: 20 menit yang lalu)
     * Harus dihitung dari waktu relatif di masa depan.
     * Untuk saat ini, nilainya dihardcode menjadi nilai [String].
     */
    var createdAt: Int = -1
)