package com.example.bincardapp.core.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bincardapp.R
import com.example.bincardapp.extensions.openMap
import com.example.bincardapp.extensions.openPhone
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel

@Composable
fun ColumnBinInfo(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel,
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(R.string.country, binInfo.countryInfo.name))
        CommonClickableText(
            text = stringResource(R.string.coordinates),
            clickableText = stringResource(
                R.string.latitude_and_longitude,
                binInfo.countryInfo.latitude,
                binInfo.countryInfo.longitude
            ),
            interactionListener = {
                openMap(
                    context,
                    binInfo.countryInfo.latitude,
                    binInfo.countryInfo.longitude
                )
            }
        )
        Text(text = stringResource(R.string.card_type, binInfo.cardType))
        Text(text = stringResource(R.string.bank_name, binInfo.bankInfo.name))
        CommonClickableText(
            text = stringResource(R.string.url),
            clickableText = binInfo.bankInfo.url,
            interactionListener = { uriHandler.openUri("https://${binInfo.bankInfo.url}") }
        )
        CommonClickableText(
            text = stringResource(R.string.phone),
            clickableText = binInfo.bankInfo.phone,
            interactionListener = { openPhone(context, binInfo.bankInfo.phone) }
        )
        Text(text = stringResource(R.string.bank_city, binInfo.bankInfo.city))
    }
}