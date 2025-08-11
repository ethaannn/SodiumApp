package com.ethan.sodium.extension

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.get


/**
 * @param route 跳转路由
 * @param bundle 跳转参数
 */
fun NavController.navigate(route: String, bundle: Bundle) {
    val node: NavDestination = this.graph.findNode(route) ?: throw NullPointerException("not find node")
    this.navigate(node.id, bundle)
}

/**
 * @param route 跳转路由
 * @param bundle 参数
 */
fun NavController.navigate(route: Any, bundle: Bundle) = this.navigate(graph[route].id, bundle)



