package com.youxiang8727.streamletmultiplatform.ui.common.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@Composable
@Preview(
    name = "BasicCalendarView",
    showBackground = true,
)
fun BasicCalendarView(
    modifier: Modifier = Modifier,
    state: CalendarState = rememberCalendarState(),
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {}
) {
    val today: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header()
        
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            listOf(
                DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
            ).forEach { dayOfWeek ->
                Text(
                    modifier = Modifier.weight(1f),
                    text = dayOfWeek.name.take(3),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }
        }

        HorizontalPager(
            state = state.pagerState
        ) { page ->
            val year = (page / 12) + 1970
            val month = Month(page % 12 + 1)
            val firstDayOfMonth = LocalDate(year, month, 1)
            val firstDayOfWeekIndex = firstDayOfMonth.dayOfWeek.isoDayNumber % 7
            val startDate = firstDayOfMonth.minus(firstDayOfWeekIndex, DateTimeUnit.DAY)

            var currentDate = startDate
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(6) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        repeat(7) {
                            val date = currentDate
                            val isSelected = date == state.selectDate
                            val isToday = date == today
                            val isCurrentMonth = date.month == month
                            Box(
                                modifier = Modifier.weight(1f)
                                    .aspectRatio(1f)
                                    .clickable {
                                        state.selectDate(date)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (isSelected) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(.8f)
                                            .clip(
                                                RoundedCornerShape(4.dp)
                                            )
                                            .background(
                                                MaterialTheme.colorScheme.primary
                                            )
                                    )
                                }

                                Text(
                                    text = date.day.toString(),
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.background
                                    } else if (isToday) {
                                        MaterialTheme.colorScheme.primary
                                    } else if (isCurrentMonth) {
                                        MaterialTheme.colorScheme.onSurface
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                    },
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            }

                            currentDate = currentDate.plus(1, DateTimeUnit.DAY)
                        }
                    }
                }
            }
        }

        footer()
    }
}