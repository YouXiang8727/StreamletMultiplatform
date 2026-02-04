package com.youxiang8727.streamletmultiplatform.core.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun format(localDate: LocalDate, pattern: String): String {
    return DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        .format(
            localDate.toJavaLocalDate()
        )
}