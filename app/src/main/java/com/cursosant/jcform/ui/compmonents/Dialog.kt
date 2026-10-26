package com.cursosant.jcform.ui.compmonents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cursosant.jcform.R

/**
 * Project: JC Form
 * From: com.cursosant.jcform.ui.compmonents
 * Created by: Alain Nicolás Tello
 * On: 25/04/25 at 16:10
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
@Composable
fun AntDialogInfo(info: String,
                  titleRes: Int,
                  confirmRes: Int = R.string.dialog_ok,
                  onDismissRequest: (Boolean) -> Unit) {
    AlertDialog(onDismissRequest = {onDismissRequest(false)},
        title = { Text(stringResource(titleRes)) },
        text = {Text(info)},
        confirmButton = {
            TextButton(onClick = {onDismissRequest(true)}) {
                Text(stringResource(confirmRes))
            }
        },
        dismissButton = {
            TextButton(onClick = {onDismissRequest(false)}) {
                Text(stringResource(R.string.dialog_cancel))
            }
        }
    )
}