package com.tm.expensetracker

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun formatDateToHumanReadableForm(dateInMillis: Long): String {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatToDecimalValue(d: Double): String {
        return String.format(Locale.getDefault(), "%.2f", d)
    }
}