package com.youxiang8727.streamletmultiplatform.core.datetime

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import platform.Foundation.NSCalendar
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun format(localDate: LocalDate, pattern: String): String {
    val date = localDate.toNsDate() ?: throw IllegalStateException("Failed to convert LocalDateTime $LocalDateTime to NSDate")
    val formatter = NSDateFormatter().apply {
        dateFormat = pattern
        locale = NSLocale.currentLocale
    }
    return formatter.stringFromDate(date)
}

@OptIn(ExperimentalForeignApi::class)
private fun LocalDate.toNsDate(): NSDate? {
    val calendar = NSCalendar.currentCalendar
    val components = NSDateComponents()
    components.year = this.year.convert()
    components.month = month.number.convert()
    components.day = day.convert()
    return calendar.dateFromComponents(components)
}