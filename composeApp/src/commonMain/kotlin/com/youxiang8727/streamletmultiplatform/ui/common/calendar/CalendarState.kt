package com.youxiang8727.streamletmultiplatform.ui.common.calendar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class CalendarState(
    val initialDate: LocalDate,
    val pagerState: PagerState,
    val coroutineScope: CoroutineScope,
    val onDateSelected: (LocalDate) -> Unit
) {
    var selectDate by mutableStateOf(initialDate)
        private set

    fun selectDate(date: LocalDate) {
        selectDate = date
        onDateSelected(date)

        val newPage = (date.year - 1970) * 12 + date.month.number - 1
        if (newPage != pagerState.currentPage) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(newPage)
            }
        }
    }
}

@Composable
fun rememberCalendarState(
    initialDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
    onDateSelected: (LocalDate) -> Unit = {}
): CalendarState {
    val year = initialDate.year

    val month = initialDate.month

    val initialMonthPage = (year - 1970) * 12 + month.number - 1

    val pagerState = rememberPagerState(
        initialPage = initialMonthPage,
        pageCount = { Int.MAX_VALUE }
    )
    val coroutineScope = rememberCoroutineScope()

    return remember { CalendarState(initialDate, pagerState, coroutineScope, onDateSelected) }
}