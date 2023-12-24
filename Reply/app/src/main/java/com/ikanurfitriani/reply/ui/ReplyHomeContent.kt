// Nama package dari ui yang dibuat dalam aplikasi
package com.ikanurfitriani.reply.ui

// Import library, kelas atau file yang dibutuhkan
import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ikanurfitriani.reply.R
import com.ikanurfitriani.reply.data.Email
import com.ikanurfitriani.reply.data.local.LocalAccountsDataProvider

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
fun ReplyListOnlyContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mendapatkan daftar email dari state UI
    val emails = replyUiState.currentMailboxEmails

    // Menggunakan LazyColumn untuk menampilkan daftar email
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.email_list_item_vertical_spacing)
        )
    ) {
        item {
            ReplyHomeTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.topbar_padding_vertical))
            )
        }
        // Menampilkan setiap item email dalam bentuk ReplyEmailListItem
        items(emails, key = { email -> email.id }) { email ->
            ReplyEmailListItem(
                email = email,
                selected = false,
                onCardClick = {
                    onEmailCardPressed(email)
                }
            )
        }
    }
}

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
fun ReplyListAndDetailContent(
    replyUiState: ReplyUiState,
    onEmailCardPressed: (Email) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mendapatkan daftar email dari state UI
    val emails = replyUiState.currentMailboxEmails
    // Menggunakan Row sebagai wadah untuk menampilkan layar list dan detail
    Row(modifier = modifier) {
        // Menampilkan daftar email menggunakan LazyColumn
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = dimensionResource(R.dimen.list_and_detail_list_padding_end),
                    top = dimensionResource(R.dimen.list_and_detail_list_padding_top)
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.email_list_item_vertical_spacing)
            )
        ) {
            // Menampilkan setiap item email dalam bentuk ReplyEmailListItem
            items(emails, key = { email -> email.id }) { email ->
                ReplyEmailListItem(
                    email = email,
                    selected = replyUiState.currentSelectedEmail.id == email.id,
                    onCardClick = {
                        onEmailCardPressed(email)
                    },
                )
            }
        }
        // Menampilkan layar detail email sebelah kanan
        val activity = LocalContext.current as Activity
        ReplyDetailsScreen(
            replyUiState = replyUiState,
            modifier = Modifier.weight(1f),
            onBackPressed = { activity.finish() }
        )
    }
}

// Anotasi yang menunjukkan penggunaan fitur eksperimental dari Material 3
@OptIn(ExperimentalMaterial3Api::class)
// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
fun ReplyEmailListItem(
    email: Email,
    selected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Kartu sebagai wadah untuk item email
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        ),
        onClick = onCardClick
    ) {
        // Kolom sebagai wadah untuk elemen-elemen dalam item email
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.email_list_item_inner_padding))
        ) {
            // Bagian header item email yang berisi informasi pengirim dan waktu pengiriman
            ReplyEmailItemHeader(
                email,
                Modifier.fillMaxWidth()
            )
            // Judul email
            Text(
                text = stringResource(email.subject),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.email_list_item_header_subject_spacing),
                    bottom = dimensionResource(R.dimen.email_list_item_subject_body_spacing)
                ),
            )
            // Isi email dengan maksimal 2 baris dan ellipsis jika terlalu panjang
            Text(
                text = stringResource(email.body),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
private fun ReplyEmailItemHeader(email: Email, modifier: Modifier = Modifier) {
    // Baris sebagai wadah untuk informasi pengirim dan waktu pengiriman
    Row(modifier = modifier) {
        // Gambar profil pengirim
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            description = stringResource(email.sender.firstName) + " "
                    + stringResource(email.sender.lastName),
            modifier = Modifier.size(dimensionResource(R.dimen.email_header_profile_size))
        )
        // Kolom sebagai wadah untuk nama pengirim dan waktu pengiriman
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            // Nama pengirim
            Text(
                text = stringResource(email.sender.firstName),
                style = MaterialTheme.typography.labelMedium
            )
            // Waktu pengiriman
            Text(
                text = stringResource(email.createdAt),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
fun ReplyProfileImage(
    @DrawableRes drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    // Kotak sebagai wadah untuk gambar profil
    Box(modifier = modifier) {
        // Menampilkan gambar profil dengan bentuk lingkaran
        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(drawableResource),
            contentDescription = description,
        )
    }
}

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
fun ReplyLogo(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    // Menampilkan gambar logo Reply
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo),
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

// Mendefinisikan fungsi komposabel bernama ReplyDetailsScreen
@Composable
private fun ReplyHomeTopBar(modifier: Modifier = Modifier) {
    // Baris sebagai wadah untuk elemen-elemen pada top bar layar utama
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        // Menampilkan logo Reply
        ReplyLogo(
            modifier = Modifier
                .size(dimensionResource(R.dimen.topbar_logo_size))
                .padding(start = dimensionResource(R.dimen.topbar_logo_padding_start))
        )
        // Menampilkan gambar profil pengguna
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
            description = stringResource(R.string.profile),
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.topbar_profile_image_padding_end))
                .size(dimensionResource(R.dimen.topbar_profile_image_size))
        )
    }
}