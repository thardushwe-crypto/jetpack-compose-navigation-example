🚀 Compose Type-Safe Navigation Setup Guide
Jetpack Compose (Navigation 2.8.0+ / 2.9.8+) တွင် Kotlin Serialization အသုံးပြု၍ Screen Navigation နှင့် Parameter များ ပေးပို့နိုင်ရန် Setup ပြုလုပ်နည်း လမ်းညွှန်။

🛠️ Step 1: Version Catalog (gradle/libs.versions.toml) တွင် ဖြည့်စွက်ပါ
Project ၏ gradle/libs.versions.toml file တွင် အောက်ပါလိုင်းများကို ဖြည့်ပေးပါ။

Ini, TOML
[versions]
# kotlinxSerialization နှင့် navigationCompose တို့၏ အသစ်ဆုံး version များ
kotlinxSerialization = "1.11.0"
navigationCompose = "2.9.8"

[libraries]
androidx-navigation-compose = { module = "androidx.navigation:navigation-compose", version.ref = "navigationCompose" }
kotlinx-serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "kotlinxSerialization" }

[plugins]
# kotlin plugin version နှင့် version.ref ချိတ်ထားပါ
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
📦 Step 2: Module Gradle (app/build.gradle.kts) တွင် ဖြည့်စွက်ပါ
app/build.gradle.kts file ထဲရှိ plugins နှင့် dependencies block များတွင် ဖြည့်ပေးပါ။

Kotlin
plugins {
// ... အခြား plugins များ
alias(libs.plugins.kotlin.serialization) // 👈 Serialization Plugin ဖွင့်ပါ
}

dependencies {
// ... အခြား dependencies များ
implementation(libs.androidx.navigation.compose) // 👈 Navigation Compose
implementation(libs.kotlinx.serialization.json)  // 👈 Serialization JSON
}
⚠️ အရေးကြီး: အထက်ပါ Step 1 နှင့် 2 ပြီးပါက Android Studio ၏ ညာဘက်အပေါ်ထောင့်မှ Sync Now ကို နှိပ်ပါ။

💻 Step 3: Navigation Code ရေးသားနည်း (Quick Template)
MainActivity.kt သို့မဟုတ် သက်ဆိုင်ရာ File တွင် အောက်ပါအတိုင်း သုံးနိုင်ပါပြီ-

Kotlin
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// 1. Routes (Screens) များ သတ်မှတ်ခြင်း
@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val id: Int, val name: String)

// 2. NavHost Setup ပြုလုပ်ခြင်း
@Composable
fun AppNavigation() {
val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        // Home Screen Route
        composable<HomeRoute> {
            HomeScreen(
                onNavigateToDetail = { id, name ->
                    // Parameter များ ပေးပို့ခြင်း
                    navController.navigate(DetailRoute(id = id, name = name))
                }
            )
        }

        // Detail Screen Route
        composable<DetailRoute> { backStackEntry ->
            // Parameter များကို ပြန်ထုတ်ယူခြင်း
            val args = backStackEntry.toRoute<DetailRoute>()
            
            DetailScreen(
                id = args.id,
                name = args.name,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
⚡ Checklists (စစ်ဆေးရန် အချက်များ)
[ ] libs.versions.toml တွင် plugin နှင့် library များ ထည့်ပြီးပြီလား?

[ ] app/build.gradle.kts တွင် alias(libs.plugins.kotlin.serialization) ပါသလား?

[ ] dependencies တွင် navigation.compose နှင့် serialization.json ပါပြီးပြီလား?

[ ] Gradle Sync Now လုပ်ပြီးပြီလား?

[ ] Navigation Route အဖြစ်သုံးမည့် Class/Object များပေါ်တွင် @Serializable ပါပြီးပြီလား?