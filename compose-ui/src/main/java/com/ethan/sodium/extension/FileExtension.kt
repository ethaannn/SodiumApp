package com.ethan.sodium.extension

import java.io.File

/**
 * 将字符串路径转换为 File 对象
 */
fun String.toFile(): File {
    return File(this)
}