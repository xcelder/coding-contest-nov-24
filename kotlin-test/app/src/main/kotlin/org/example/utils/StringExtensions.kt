package org.example.utils

fun String.splitPro(string: String) =
    split(string).toMutableList().also { it.removeIf { s -> s.isEmpty() } }

