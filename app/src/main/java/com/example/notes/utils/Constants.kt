package com.example.notes.utils

import androidx.lifecycle.viewmodel.CreationExtras
import com.example.notes.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Contants {

    object Keys {
        const val NOTE_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val ADD_NEW_NOTE = "Добавление заметки"
        const val NOTE_TITLE = "Заголовок"
        const val NOTE_SUBTITLE = "Текст"
        const val ADD_NOTE = "Добавить"
        const val TITLE = "Заголовок"
        const val SUBTITLE = "Текст"
        const val ID = "Id"
        const val NONE = "none"
        const val UPDATE = "Обновить"
        const val DELETE = "Удалить"
        const val NAV_BACK = "Назад"
        const val EDIT_NOTE = "Редактирование заметки"
        const val EMPTY = ""
        const val UPDATE_NOTE = "Обновить"
    }

    object Screens {
        const val START_SCREEN = "start_screen"
        const val MAIN_SCREEN = "main_screen"
        const val ADD_SCREEN = "add_screen"
        const val NOTE_SCREEN = "note_screen"
    }

}