package com.cursosant.jcform.ui.compmonents

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cursosant.jcform.R

/**
 * Project: JC Form
 * From: com.cursosant.jcform.ui.compmonents
 * Created by: Alain Nicolás Tello
 * On: 28/04/25 at 08:02
 * All rights reserved 2025.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(onDateSelected: (Long?) -> Unit,
                    onDismiss: () -> Unit) {
    val datePickerState = rememberDatePickerState(System.currentTimeMillis())

    DatePickerDialog(
        onDismissRequest = {onDismiss()},
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.dialog_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.dialog_cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}