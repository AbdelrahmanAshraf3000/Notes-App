package com.example.notesapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesapplication.database.Note
import com.example.notesapplication.routes.Route
import com.example.notesapplication.ui.viewmodels.NoteViewModel

@Composable
fun HomeScreen(navController: NavController,
               modifier: Modifier = Modifier,
               viewModel: NoteViewModel= androidx.lifecycle.viewmodel.compose.viewModel()
) {
     Scaffold(
         floatingActionButtonPosition = FabPosition.Center,
         floatingActionButton = {
             FloatingActionButton(
                 onClick={navController.navigate(Route.ADD_NOTE)},
                 containerColor= Color.Gray
             )
             {
                 Icon(imageVector = Icons.Filled.Add, contentDescription ="" )
             }
         },
     ) {_->
         val notes by viewModel.getNotes().collectAsState(initial = emptyList())
         LazyColumn(modifier=Modifier.fillMaxSize().padding(top = 32.dp)) {
             items(notes){note->
                 NoteListItem(note = note) {
                     navController.navigate("${Route.EDIT_NOTE}/${note.id}/${note.details}/${note.title}")

                 }

             }

         }

     }
}
@Composable
fun NoteListItem(note: Note, modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onNavigate() }
    ) {
        Text(
            text = note.title,
            fontSize = 30.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(8.dp)
                .defaultMinSize(minHeight = 80.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
        Text(
            text = note.details,
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(8.dp)
                .defaultMinSize(minHeight = 80.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {

}