package com.ethan.sodium.extension

import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


abstract class MoshiTypeReference<T>

inline fun <reified T> getGenericType(): Type {
    val type = object : MoshiTypeReference<T>() {}::class.java
        .genericSuperclass
        .let { it as ParameterizedType }
        .actualTypeArguments
        .first()

    return type
}

/**
 * 将任意对象序列化为JSON字符串
 * @param any 需要序列化的对象
 * @param indent JSON格式化时的缩进字符串，默认为空格
 * @return 序列化后的JSON字符串，如果序列化失败则返回空字符串
 */

inline fun <reified T> Moshi.toJson(any:T, indent: String =""): String {
    return try {
        adapter<T>(getGenericType<T>()).indent(indent).toJson(any)
    }catch (e: Exception){
        println(e.message)
        ""
    }
}


/**
 * 从JSON字符串反序列化为指定类型的对象
 *
 * @param T 反序列化的目标类型
 * @param json 要反序列化的JSON字符串
 * @return 反序列化成功的对象，如果失败则返回null
 */
inline fun <reified T> Moshi.fromJson(json: String): T? {
    return try {
        adapter<T>(getGenericType<T>()).fromJson(json)
    }catch (e: Exception){
        println(e.message)
        null
    }
}