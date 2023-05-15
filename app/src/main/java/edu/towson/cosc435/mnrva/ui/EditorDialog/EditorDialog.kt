package edu.towson.cosc435.mnrva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditEndTimeDialog
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditStartTimeDialog
import edu.towson.cosc435.mnrva.ui.EditorDialog.EditorDialogViewModel

@Composable
fun EditDialogBox(vm: EditorDialogViewModel) {
    val title by remember { vm.title }
    val description by remember { vm.description }
    val tags by remember { vm.tags }
    val scrollState = rememberScrollState()

    if (vm.showEditor.value) {
        Dialog(
            onDismissRequest = { vm.setShowEditor(false) }, properties = DialogProperties(
                dismissOnBackPress = true, dismissOnClickOutside = true
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f)
                    .background(color = MaterialTheme.colors.background)
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(value = title, onValueChange = vm::setTitle, label = {
                        Text(text = "Title")
                    })

                    Spacer(modifier = Modifier.height(50.dp))

                    EditStartTimeDialog()

                    Spacer(modifier = Modifier.height(60.dp))

                    EditEndTimeDialog()

                    Spacer(modifier = Modifier.height(60.dp))

                    OutlinedTextField(value = description.orEmpty(),
                        onValueChange = vm::setDescription,
                        label = { Text(text = "Description") })

                    Spacer(modifier = Modifier.height(60.dp))

                    OutlinedTextField(value = tags.orEmpty(),
                        onValueChange = vm::setTags,
                        label = { Text(text = "Tags") })

                    Spacer(modifier = Modifier.height(60.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //Confirmation Button
                        Button(onClick = vm::updateEvent) {
                            Text(text = "Confirm")
                        }

                        Spacer(modifier = Modifier.width(40.dp))

                        //Delete Button
                        Button(
                            onClick = vm::deleteEvent, colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Red
                            )
                        ) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
        }
    }
}