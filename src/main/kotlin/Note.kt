data class Note(override val nameElement: String) : ElementInterface{
    var contentElement: String = ""
    fun addElement(content: String) {
        contentElement = content
    }
}