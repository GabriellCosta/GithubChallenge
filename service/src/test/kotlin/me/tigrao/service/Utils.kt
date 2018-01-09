package me.tigrao.service

import com.google.gson.Gson

fun <T> readFile(path: String, type: Class<T>): T {
    val resource = type.classLoader.getResourceAsStream("fixtures/$path")
    val json = resource.reader().readText()
    return Gson().fromJson(json, type)
}
