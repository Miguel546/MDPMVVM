package com.luismiguel.mdpbancocomercio.calendar

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtil {
    private val DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm a"
    val DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy"
    val DATE_FORMAT_YYYYMMYDD = "yyyyMMdd"
    fun getDateTimeNow(): String? {
        val cal = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(
            DATE_TIME_FORMAT,
            Locale("es", "ES")
        )
        return dateFormat.format(cal.time)
    }

    fun parseDateDMY(date: Date): String? {
        val dateFormat = SimpleDateFormat(
            DATE_FORMAT_DDMMYYYY,
            Locale("es", "ES")
        )
        return dateFormat.format(date.time)
    }

    fun getCalendarFromDateMDY(date: String): Calendar? {
        val calendar = Calendar.getInstance()
        val arrDate = date.split("/").toTypedArray()
        calendar[Calendar.DATE] = arrDate[1].toInt()
        calendar[Calendar.MONTH] = arrDate[0].toInt() - 1
        calendar[Calendar.YEAR] = arrDate[2].toInt()
        return calendar
    }

    fun parseDateYMD(date: Date): String? {
        val dateFormat = SimpleDateFormat(
            DATE_FORMAT_YYYYMMYDD,
            Locale("es", "ES")
        )
        return dateFormat.format(date.time)
    }

    @Throws(Exception::class)
    fun getDateFormatFromString(date: String?, format: String?): Date? {
        val dateFormat = SimpleDateFormat(
            format,
            Locale("es", "ES")
        )
        return dateFormat.parse(date)
    }

    fun convertDMYToYMD(date: String): String? {
        val arrDate = date.split("/").toTypedArray()
        val year = arrDate[2]
        arrDate[2] = arrDate[0]
        arrDate[0] = year
        return TextUtils.join("-", arrDate)
    }
}