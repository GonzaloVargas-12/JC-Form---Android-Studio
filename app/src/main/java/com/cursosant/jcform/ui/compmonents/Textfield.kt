package com.cursosant.jcform.ui.compmonents

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.cursosant.jcform.R
import com.cursosant.jcform.convertMillisToDate
import com.cursosant.jcform.ui.theme.JCFormTheme

/**
 * Project: JC Form
 * From: com.cursosant.jcform.ui.compmonents
 * Created by: Alain Nicolás Tello
 * On: 25/04/25 at 10:40
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

@Preview(showBackground = true)
@Composable
private fun AntTextFieldPreview() {
    JCFormTheme {
        AntTextField(Modifier,
            labelRes = R.string.hint_name,
            iconRes = R.drawable.ic_person,
            maxLengthRes = R.integer.name_max_length) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun AntTfDatePreview() {
    JCFormTheme {
        AntTfDate(Modifier, R.string.hint_birth_date) { }
    }
}

@Composable
fun AntTextField(modifier: Modifier = Modifier,
                 labelRes: Int,
                 iconRes: Int,
                 maxLengthRes: Int? = null,
                 minValue: Int = 0,
                 errorRes: Int = R.string.supporting_required,
                 keyboardOptions: KeyboardOptions? = null,
                 singleLine: Boolean = true,
                 isRequired: Boolean = true,
                 isClean: Boolean = false,
                 paddingTop: Dp = dimensionResource(R.dimen.common_padding_min),
                 onValueChange: (String) -> Unit) {
    var textValue by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val maxLength = if (maxLengthRes == null) null else integerResource(maxLengthRes)

    if (isClean) {
        textValue = ""
    }

    OutlinedTextField(value = textValue,
        onValueChange = {
            if (maxLength == null) {
                textValue = it
            } else {
                if (it.length <= maxLength) {
                    textValue = it
                }
            }

            isError = it.trim().isEmpty()

            if (minValue > 0) {
                isError = (textValue.toIntOrNull() ?: 0) < minValue
            }

            onValueChange(textValue)
        },
        isError = isError,
        singleLine = singleLine,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = paddingTop),
        label = {
            Text(stringResource(labelRes),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        leadingIcon = {
            Icon(painter = painterResource(iconRes), contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardOptions?.capitalization ?: KeyboardCapitalization.Sentences,
            keyboardType = keyboardOptions?.keyboardType ?: KeyboardType.Text,
            imeAction = if (keyboardOptions == null ||
                keyboardOptions.imeAction == ImeAction.Default) ImeAction.Next
            else keyboardOptions.imeAction
        ),
        supportingText = {
            Row {
                if (isRequired) {
                    Text(if (isError) stringResource(errorRes)
                    else stringResource(R.string.supporting_required))
                }

                Spacer(Modifier.weight(1f))

                if (maxLength != null && minValue == 0) {
                    Text("${textValue.length}/$maxLength")
                }
            }
        })
}

@Composable
fun AntTfDate(modifier: Modifier,
              labelRes: Int,
              selectedDate: Long? = null,
              onShowModal: () -> Unit) {
    OutlinedTextField(value = selectedDate?.let { convertMillisToDate(it) } ?: "",
        onValueChange = {},
        label = {
            Text(stringResource(labelRes),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        },
        trailingIcon = {
            Icon(painterResource(R.drawable.ic_calendar_today), contentDescription = null)
        },
        modifier = modifier.pointerInput(selectedDate) {
            awaitEachGesture {
                awaitFirstDown(pass = PointerEventPass.Initial)
                val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                if (upEvent != null) onShowModal()
            }
        })
}