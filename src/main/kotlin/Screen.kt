import java.util.Scanner
import kotlin.system.exitProcess


class Screen(private val archiveList: MutableList<Archive>) {

    private fun inputInt(): Int {
        return Scanner(System.`in`).nextLine().toInt()
    }

    private fun inputStr(): String {
        while (true) {
            val string = Scanner(System.`in`).nextLine().toString()
            if (string.isNotBlank()) return string else println("Поле не может быть пустым \nВведите текст:")
        }
    }

    fun selectArchive() {
        val menuArchive = mutableListOf(
            Menu("Создать архив")
            {
                println("Ведите название архива:")
                val newArchive = Archive(inputStr())
                archiveList.add(newArchive)
                println("Архив '${newArchive.nameElement}' создан")
                selectArchive()
            },
            Menu("Выход") { exitProcess(0) })
        archiveList.forEach { element ->
            menuArchive.add(
                1,
                Menu(element.nameElement) { selectNote(element.contentElement) })
        }
        println("Выберите архив:")
        menuArchive.forEach { number -> println("${menuArchive.indexOf(number)}. ${number.name} ") }
        while (true) {
            try {
                val index: Int = inputInt()
                if (menuArchive.size > index) menuArchive[index].onSelect()
                else {
                    println("Такой цифры нет в меню")
                    selectArchive()
                }
            } catch (e: Exception) {
                println("Введите цифру:")
                selectArchive()
            }
        }
    }

    private fun selectNote(archive: MutableList<Note>) {
        val menuNote = mutableListOf(
            Menu("Создать заметку")
            {
                println("Введите название заметки:")
                val note = Note(inputStr())
                println("Введите содержание заметки:")
                note.addElement(inputStr())
                archive.add(note)
                println("Заметка '${note.nameElement}' создана")
                selectNote(archive)
            },
            Menu("Назад") { selectArchive() })
        archive.forEach { note ->
            menuNote.add(1, Menu(note.nameElement) {
                println("Заметка '${note.nameElement}': \n${note.contentElement}")
                println("Введите любой символ что бы вернутся назад")
                inputStr()
                selectNote(archive)
            })
        }
        println("Выберите заметку: ")
        menuNote.forEach { number -> println("${menuNote.indexOf(number)}. ${number.name} ") }
        while (true) {
            try {
                val index: Int = inputInt()
                if (menuNote.size > index) menuNote[index].onSelect()
                else {
                    println("Такой цифры нет в меню")
                    selectNote(archive)
                }
            } catch (e: Exception) {
                println("Введите цифру")
                selectNote(archive)
            }
        }
    }
}
