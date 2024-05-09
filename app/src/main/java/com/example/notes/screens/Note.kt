package com.example.notes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notes.ViewModel
import com.example.notes.model.Note
import com.example.notes.navigation.NavRoute
import com.example.notes.ui.theme.NoTeSTheme
import com.example.notes.utils.Contants
import com.example.notes.utils.Contants.Keys.DELETE
import com.example.notes.utils.Contants.Keys.EDIT_NOTE
import com.example.notes.utils.Contants.Keys.EMPTY
import com.example.notes.utils.Contants.Keys.NAV_BACK
import com.example.notes.utils.Contants.Keys.NONE
import com.example.notes.utils.Contants.Keys.SUBTITLE
import com.example.notes.utils.Contants.Keys.TITLE
import com.example.notes.utils.Contants.Keys.UPDATE
import com.example.notes.utils.Contants.Keys.UPDATE_NOTE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController: NavHostController, viewModel: ViewModel, noteId: String?) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{ it.id == noteId?.toInt() } ?: Note(title = NONE, subtitle = NONE)
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(EMPTY) }
    var subtitle by remember { mutableStateOf(EMPTY) }

    if (bottomSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {  },
            sheetState = bottomSheetState,
            content = {

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = EDIT_NOTE,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = TITLE) },
                    isError = title.isEmpty()
                )
                OutlinedTextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(text = SUBTITLE) },
                    isError = subtitle.isEmpty()
                )
                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        viewModel.updateNote(
                            note =
                            Note(id = note.id, title = title, subtitle = subtitle)
                        ) {
                            navController.navigate(NavRoute.Main.route)
                        }
                    }
                ) {
                    Text(text = UPDATE_NOTE)
                }
            }
        }
    } )

    } else {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = note.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = note.subtitle,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    coroutineScope.launch {
                        title = note.title
                        subtitle = note.subtitle
                        bottomSheetState.show()
                    }
                }) {
                    Text(text = UPDATE)
                }
                Button(onClick = {
                    viewModel.deleteNote(note = note) {
                        navController.navigate(NavRoute.Main.route)
                    }
                }) {
                    Text(text = DELETE)
                }
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.navigate(NavRoute.Main.route)
                }) {
                Text(text = NAV_BACK)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevNoteScreen() {
    NoTeSTheme {
        val context = LocalContext.current
        val mViewModel: ViewModel =
            viewModel()
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}