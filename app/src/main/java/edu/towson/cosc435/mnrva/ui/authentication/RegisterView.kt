package edu.towson.cosc435.mnrva.ui.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterView(vm: AuthenticationViewModel) {

    // Focus manager to hide keyboard
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxHeight(.8f).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Register for MNRVA", fontSize = 28.sp)

        // Name
        OutlinedTextField(
            value = vm.name.value,
            onValueChange = { vm.setName(it) },
            label = { Text("Name:") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        // Email field
        OutlinedTextField(
            value = vm.email.value,
            onValueChange = { vm.setEmail(it) },
            label = { Text("Email:") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        // Password field
        OutlinedTextField(
            value = vm.password.value,
            onValueChange = { vm.setPassword(it) },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Password:") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        // Confirm password field
        OutlinedTextField(
            value = vm.confirmPassword.value,
            onValueChange = { vm.setConfirmPassword(it) },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("Verify Password:") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        Spacer(Modifier.height(8.dp))
        Button(onClick = {}) {
            Text("Register")
        }
    }
}