package com.luismiguel.mdpbancocomercio.calendar

import java.text.SimpleDateFormat
import java.util.*

class CalendarUtil {
    private val dateTimeFormat = "dd-MM-yyyy HH:mm a"
    fun getDateTimeNow(): String? {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            dateTimeFormat,
            Locale("es", "ES")
        )
        return dateFormat.format(cal.time)
    }
}