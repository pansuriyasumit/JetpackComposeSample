package com.fifteen11.jetpacksamplelibrary.screens.component

import androidx.compose.runtime.Composable
import com.fifteen11.jetpacksamplelibrary.data.model.ProblemsItem

@Composable
fun ProblemCard(problem: ProblemsItem?) {
    problem?.medications?.forEach { medication ->
        medication?.medicationsClasses?.forEach { medicationsClass ->
            medicationsClass?.drugsName?.forEach { drug ->
                DrugCard(drug)
            }
        }
    }
}