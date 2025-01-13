package com.example.bincardapp.features.bin_lookup.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bincardapp.R
import com.example.bincardapp.core.models.TextField
import com.example.bincardapp.core.ui.common.ColumnBinInfo
import com.example.bincardapp.core.ui.theme.LightBlue
import com.example.bincardapp.features.bin_lookup.domain.model.BinInfoModel
import com.example.bincardapp.features.bin_lookup.presentation.BinLookupScreenEvents
import com.example.bincardapp.features.bin_lookup.presentation.BinLookupViewModel
import com.example.bincardapp.features.bin_lookup.ui.components.BinMaskVisualTransformation
import kotlinx.serialization.Serializable

@Serializable
object BinLookupRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BinLookupScreen(
    viewModel: BinLookupViewModel = hiltViewModel(),
    onBinHistoryScreen: () -> Unit,
) {
    val isLoading = viewModel.isLoading
    val binInfo = viewModel.binInfo
    val errorMessage = viewModel.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.bin_lookup), fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.request_count),
                textAlign = TextAlign.Center,
            )
            SearchBinRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                enabled = !isLoading,
                binTextFieldState = viewModel.binTextFieldState,
                binValueChanged = { viewModel.onEvent(BinLookupScreenEvents.BinValueChanged(it)) },
                onSearchButtonClick = { viewModel.onEvent(BinLookupScreenEvents.GetBinInfo) }
            )
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = LightBlue)
                    }
                }

                errorMessage != null -> {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(errorMessage),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                    )
                }

                binInfo != null -> {
                    CardBinInfo(binInfo = binInfo)
                }
            }
            Spacer(modifier = Modifier.weight(1F))
            ButtonBinHistory(onClick = onBinHistoryScreen::invoke)
        }
    }
}

@Composable
private fun BinTextField(
    modifier: Modifier = Modifier,
    binTextFieldState: TextField,
    enabled: Boolean = true,
    binValueChanged: (newValue: String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = binTextFieldState.value,
        onValueChange = {
            binValueChanged(it.take(8))
        },
        singleLine = true,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = {
            Text(text = stringResource(R.string.bin_label), color = Gray)
        },
        visualTransformation = BinMaskVisualTransformation("#### ####"),
        shape = RoundedCornerShape(12.dp),
        supportingText = {
            if (binTextFieldState.value.isEmpty() && !binTextFieldState.isError) {
                Text(text = stringResource(R.string.bin_field_supporting_text), fontSize = 12.sp)
            }
            if (binTextFieldState.isError) {
                Text(text = stringResource(binTextFieldState.errorMessage), fontSize = 12.sp)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LightBlue
        ),
        trailingIcon = {
            if (binTextFieldState.value.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .background(LightGray, CircleShape)
                        .clickable { binValueChanged("") },
                    imageVector = Icons.Filled.Close,
                    contentDescription = stringResource(R.string.clear_field),
                    tint = LightBlue,
                )
            }
        },
        isError = binTextFieldState.isError,
    )
}

@Composable
private fun SearchBinRow(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    binTextFieldState: TextField,
    binValueChanged: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BinTextField(
            modifier = Modifier.weight(1F),
            enabled = enabled,
            binTextFieldState = binTextFieldState,
            binValueChanged = binValueChanged::invoke
        )
        Button(
            modifier = Modifier.size(56.dp),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(8.dp),
            onClick = onSearchButtonClick::invoke,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue,
            )
        ) {
            Image(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.bin_search_button_description),
                colorFilter = ColorFilter.tint(White)
            )
        }
    }
}

@Composable
private fun CardBinInfo(
    modifier: Modifier = Modifier,
    binInfo: BinInfoModel,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, Gray),
        colors = CardDefaults.cardColors(
            containerColor = Transparent,
        ),
    ) {
        ColumnBinInfo(
            modifier = Modifier.padding(8.dp),
            binInfo = binInfo,
        )
    }
}

@Composable
private fun ButtonBinHistory(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue,
            contentColor = White,
        ),
        onClick = onClick::invoke
    ) {
        Text(text = stringResource(R.string.request_history))
        Spacer(modifier = Modifier.weight(1F))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(R.string.request_history)
        )
    }
}