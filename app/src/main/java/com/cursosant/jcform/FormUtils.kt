package com.cursosant.jcform

import android.content.Context
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val dateFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())

    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(millis),
        ZoneId.of("UTC")
    ).format(dateFormatter)
}

fun foundErrors(context: Context,
                name: String,
                surname: String,
                height: String): String? {
    return if (name.isBlank() || surname.isBlank() ||
        (height.toIntOrNull() ?: 0) < context.resources.getInteger(R.integer.height_min_value)) {
        context.getString(R.string.form_invalid)
    } else {
        return null
    }
}

fun userFormatter(user: User): String {
    val result = StringBuilder()

    result.append("Nombre: ${user.name} ${user.surname}\n")
    result.append("Estatura: ${user.height}\n")
    result.append("Fecha de nacimiento: ${convertMillisToDate(user.birthDate)}\n")
    result.append("Ocupación: ${user.occupation}\n")
    result.append("Notas: ${user.notes}")

    return result.toString()
}