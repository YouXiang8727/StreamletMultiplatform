package com.youxiang8727.streamletmultiplatform.core.datetime

import kotlinx.datetime.LocalDate

/**
 * REF: https://raed-o-ghazal.medium.com/kotlinx-localdatetime-manipulation-for-kmm-eacfede93aba
 */

expect fun format(localDate: LocalDate, pattern: String): String