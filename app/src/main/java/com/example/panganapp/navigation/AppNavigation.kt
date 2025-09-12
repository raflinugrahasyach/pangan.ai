// LOKASI: app/src/main/java/com/example/panganapp/navigation/AppNavigation.kt
package com.example.panganapp.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight // <-- INI IMPORT YANG HILANG
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.panganapp.ui.screens.*

sealed class Screen(
    val route: String,
    val label: String,
    val outlinedIcon: ImageVector,
    val filledIcon: ImageVector
) {
    object Home : Screen("home", "Beranda", Icons.Outlined.Home, Icons.Filled.Home)
    object EWS : Screen("ews", "EWS", Icons.Outlined.Warning, Icons.Filled.Warning)
    object Calendar : Screen("calendar", "Kalender", Icons.Outlined.CalendarMonth, Icons.Filled.CalendarMonth)
    object Market : Screen("market", "Pasar", Icons.Outlined.QueryStats, Icons.Filled.QueryStats)
}

val navItems = listOf(Screen.Home, Screen.EWS, Screen.Calendar, Screen.Market)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(80.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 16.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEach { screen ->
                        AppNavigationItem(
                            screen = screen,
                            isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                if (currentDestination?.route != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.EWS.route) { EwsScreen() }
            composable(Screen.Calendar.route) { CalendarScreen() }
            composable(Screen.Market.route) { MarketScreen() }
        }
    }
}

@Composable
private fun AppNavigationItem(screen: Screen, isSelected: Boolean, onClick: () -> Unit) {
    val highlightColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
        label = "highlight color",
        animationSpec = tween(400)
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        label = "content color",
        animationSpec = tween(400)
    )
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 30.dp else 26.dp,
        label = "icon size"
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(highlightColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (isSelected) screen.filledIcon else screen.outlinedIcon,
                contentDescription = screen.label,
                tint = contentColor,
                modifier = Modifier.size(iconSize)
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = screen.label, color = contentColor, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}