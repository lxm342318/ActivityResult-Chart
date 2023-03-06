package com.lxm.test.ext

/**
 * NullSafetyTypesExt.kt
 * 安全的基础类型null处理
 */
fun Int?.getOrDefault(default: Int = 0) = this ?: default
fun Long?.getOrDefault(default: Long = 0L) = this ?: default
fun Short?.getOrDefault(default: Short = 0) = this ?: default
fun Float?.getOrDefault(default: Float = 0f) = this ?: default
fun Double?.getOrDefault(default: Double = 0.0) = this ?: default
fun Boolean?.getOrDefault(default: Boolean = false) = this ?: default
fun String?.getOrDefault(default: String = "") = this ?: default