package com.example.bincardapp.features.bin_history.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bincardapp.R
import com.example.bincardapp.core.ui.common.ColumnBinInfo
import com.example.bincardapp.features.bin_history.presentation.BinHistoryViewModel
import kotlinx.serialization.Serializable

@Serializable
object BinHistoryRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BinHistoryScreen(
    viewModel: BinHistoryViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val binHistoryList = viewModel.binHistoryList.collectAsStateWithLifecycle(emptyList()).value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack::invoke) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.request_history),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        val expandItemsState =
            remember(binHistoryList) { binHistoryList.map { false }.toMutableStateList() }
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(binHistoryList) { index, binInfo ->
                val expand = expandItemsState[index]
                Card(
                    modifier = Modifier.animateContentSize(animationSpec = tween(300)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expandItemsState[index] = !expand }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.bin_item, binInfo.bin),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Icon(
                            modifier = Modifier.rotate(if (expand) 90F else 0F),
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                    if (expand) {
                        ColumnBinInfo(
                            modifier = Modifier.padding(16.dp),
                            binInfo = binInfo,
                        )
                    }
                }
            }
        }
    }
}