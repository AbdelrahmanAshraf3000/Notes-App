package com.example.notesapplication.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapplication.database.Note
import com.example.notesapplication.ui.viewmodels.NoteViewModel

@Composable
fun AddingNoteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel:NoteViewModel= androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp)
    ) {

        val context = LocalContext.current
        var details by remember { mutableStateOf("") }
        var tilteNote by remember { mutableStateOf("") }
        OutlinedTextField(
            value = tilteNote,
            onValueChange = { tilteNote = it },
            label = {
                Text(text = "Note Title")
            },
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = {
                Text(text = "Note Details")
            },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = {

                viewModel.upsert(Note(details=details, title = tilteNote))
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show();
                details=""
                tilteNote=""
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text(text = "Save")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddingNoteScreenPreview() {
    AddingNoteScreen(navController = rememberNavController())
}