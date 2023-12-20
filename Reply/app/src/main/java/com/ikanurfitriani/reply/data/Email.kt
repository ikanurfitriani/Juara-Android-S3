package com.ikanurfitriani.reply.data

import androidx.annotation.StringRes

/**
 * A simple data class to represent an Email
 */
data class Email(
    /** Unique ID of the email **/
    val id: Long,
    /** Sender of the email **/
    val sender: Account,
    /** Recipient(s) of the email **/
    val recipients: List<Account> = emptyList(),
    /** Title of the email **/
    @StringRes val subject: Int = -1,
    /** Content of the email **/
    @StringRes val body: Int = -1,
    /** Which mailbox it is in **/
    var mailbox: MailboxType = MailboxType.Inbox,
    /**
     * Relative duration in which it was created. (e.g. 20 mins ago)
     * It should be calculated from relative time in the future.
     * For now it's hard coded to a [String] value.
     */
    var createdAt: Int = -1
)