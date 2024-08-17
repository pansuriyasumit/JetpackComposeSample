package com.fifteen11.jetpacksamplelibrary.screens.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.fifteen11.jetpacksamplelibrary.data.model.ProblemsItem
import com.fifteen11.jetpacksamplelibrary.screens.component.MedicineCard

@Composable
fun MedicineList(medicine: List<ProblemsItem?>?, onClick: (problemId: Int) -> Unit) {

    LazyColumn(content = {
        items(items = medicine ?: emptyList(), itemContent = { medicine ->
            MedicineCard(medicine = medicine, onClick)
        })
    })
}