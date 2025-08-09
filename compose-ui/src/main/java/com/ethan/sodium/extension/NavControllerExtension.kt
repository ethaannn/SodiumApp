package com.ethan.sodium.extension

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination

fun NavController.navigate(route: String, bundle: Bundle) {
    val node: NavDestination = this.graph.findNode(route) ?: throw NullPointerException("not find node")
    this.navigate(node.id, bundle)
}