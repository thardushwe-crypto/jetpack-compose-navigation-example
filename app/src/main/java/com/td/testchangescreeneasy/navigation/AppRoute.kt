package com.td.testchangescreeneasy.navigation

import kotlinx.serialization.Serializable

// Home Screen Route (Parameter မပါ)
@Serializable
object HomeRoute

// Detail Screen Route (Parameter ပါ)
@Serializable
data class DetailRoute(
    val id: Int = 1,
    val name: String = "add something"
)

@Serializable
data class NiceDetailRoute(
    val name: String = "add name"
)