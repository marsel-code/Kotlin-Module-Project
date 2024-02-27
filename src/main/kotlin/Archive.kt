data class Archive(override val nameElement: String) :
    ElementInterface {
    val contentElement: MutableList<Note> = ArrayList()
}