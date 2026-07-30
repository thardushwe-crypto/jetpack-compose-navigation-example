# jetpack-compose-navigation-example
🚀 A clean example of Type-Safe Screen Navigation in Jetpack Compose using Navigation Compose 2.9.8+ and Kotlinx Serialization.
# 📱 Jetpack Compose Type-Safe Navigation Example

A simple and clean Android starter project demonstrating **Type-Safe Screen Navigation with Parameter Passing** in Jetpack Compose, built using `androidx.navigation:navigation-compose:2.9.8` and `kotlinx.serialization`.

## ✨ Features
- 🛡️ **Type-Safe Navigation**: No more string route errors (`"screen/{id}"`).
- 📦 **Parameter Passing**: Passing primitive data types (`Int`, `String`) between screens safely using `@Serializable`.
- 📁 **Clean Architecture**: Separation of UI Screens, Navigation Graph, and Routes.
- ⚡ **Latest Dependencies**: Built with Kotlin `2.4.10` and Compose BOM `2026.06.01`.

---

## 🏗️ Project Structure

```text
com.td.testchangescreeneasy/
├── navigation/
│   ├── AppRoute.kt          # @Serializable Navigation Routes
│   └── AppNavigation.kt     # NavHost setup & route handlers
├── ui/screens/
│   ├── HomeScreen.kt        # Home Screen UI & trigger events
│   └── DetailScreen.kt      # Detail Screen UI & argument display
└── MainActivity.kt          # App Entry point
```

---

## 🚀 How It Works

### 1. Define Routes (`AppRoute.kt`)
```kotlin
@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val id: Int, val name: String)
```

### 2. Setup NavHost (`AppNavigation.kt`)
```kotlin
NavHost(navController = navController, startDestination = HomeRoute) {
    composable<HomeRoute> {
        HomeScreen(
            onNavigateToDetail = { id, name ->
                navController.navigate(DetailRoute(id = id, name = name))
            }
        )
    }

    composable<DetailRoute> { backStackEntry ->
        val args = backStackEntry.toRoute<DetailRoute>()
        DetailScreen(
            id = args.id,
            name = args.name,
            onBack = { navController.popBackStack() }
        )
    }
}
```

---

## 🛠️ Tech Stack & Dependencies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material3)
- **Navigation**: Navigation Compose `2.9.8`
- **Serialization**: Kotlinx Serialization JSON `1.11.0`

---

## 📄 License
This project is open-source and available under the [MIT License](LICENSE).
