package data.util

fun String.removeHtmlTags(): String = replace(Regex("<[^>]*>"), "")