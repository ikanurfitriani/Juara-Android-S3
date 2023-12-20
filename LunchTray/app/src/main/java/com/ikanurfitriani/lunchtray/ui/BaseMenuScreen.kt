package com.ikanurfitriani.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.ikanurfitriani.lunchtray.R
import com.ikanurfitriani.lunchtray.model.MenuItem

@Composable
fun BaseMenuScreen(
    options: List<MenuItem>,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    onSelectionChanged: (MenuItem) -> Unit,
) {

    var selectedItemName by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        options.forEach { item ->
            val onClick = {
                selectedItemName = item.name
                onSelectionChanged(item)
            }
            MenuItemRow(
                item = item,
                selectedItemName = selectedItemName,
                onClick = onClick,
                modifier = Modifier.selectable(
                    selected = selectedItemName == item.name,
                    onClick = onClick
                )
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
            )
        }

        MenuScreenButtonGroup(
            selectedItemName = selectedItemName,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked = {
                // Assert not null bc next button is not enabled unless selectedItem is not null.
                onNextButtonClicked()
            },
            modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}

@Composable
fun MenuItemRow(
    item: MenuItem,
    selectedItemName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == item.name,
            onClick = onClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
fun MenuScreenButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ){
        OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
            Text(stringResource(R.string.cancel).uppercase())
        }
        Button(
            modifier = Modifier.weight(1f),
            // the button is enabled when the user makes a selection
            enabled = selectedItemName.isNotEmpty(),
            onClick = onNextButtonClicked
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}