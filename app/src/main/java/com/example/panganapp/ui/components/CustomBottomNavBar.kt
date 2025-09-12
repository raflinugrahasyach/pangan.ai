// LOKASI: app/src/main/java/com/example/panganapp/ui/components/CustomBottomNavBar.kt
package com.example.panganapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.panganapp.navigation.Screen
import com.example.panganapp.ui.theme.TextGray

@Composable
fun CustomBottomNavBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    items: List<Screen>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Latar Belakang Kustom dengan Lekukan
        Canvas(modifier = Modifier.fillMaxSize()) {
            val fabRadius = 38.dp.toPx()
            val cradlePath = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width / 2 - fabRadius * 1.2f, 0f)
                cubicTo(
                    x1 = size.width / 2 - fabRadius * 0.8f, y1 = 0f,
                    x2 = size.width / 2 - fabRadius, y2 = fabRadius * 0.9f,
                    x3 = size.width / 2, y3 = fabRadius * 0.9f
                )
                cubicTo(
                    x1 = size.width / 2 + fabRadius, y1 = fabRadius * 0.9f,
                    x2 = size.width / 2 + fabRadius * 0.8f, y2 = 0f,
                    x3 = size.width / 2 + fabRadius * 1.2f, y3 = 0f
                )
                lineTo(size.width, 0f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            drawPath(path = cradlePath, brush = SolidColor(Color.White))
        }

        // FAB di Tengah
        FloatingActionButton(
            onClick = {
                if (currentDestination?.route != Screen.Market.route) {
                    navController.navigate(Screen.Market.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true; restoreState = true
                    }
                }
            },
            modifier = Modifier.align(Alignment.TopCenter).shadow(elevation = 8.dp, shape = CircleShape),
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.QueryStats, contentDescription = "Analisis", tint = Color.Black, modifier = Modifier.size(28.dp))
        }

        // Ikon Navigasi
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                AppNavigationItem(
                    screen = screen,
                    isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        if (currentDestination?.route != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true; restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RowScope.AppNavigationItem(screen: Screen, isSelected: Boolean, onClick: () -> Unit) {
    // Spacer Kosong untuk area FAB
    if (screen.route == Screen.Calendar.route) {
        Spacer(modifier = Modifier.weight(1.2f))
    }

    val iconColor by animateColorAsState(targetValue = if (isSelected) MaterialTheme.colorScheme.primary else TextGray, label = "", animationSpec = tween(300))
    val iconSize by animateDpAsState(targetValue = if (isSelected) 30.dp else 26.dp, label = "", animationSpec = tween(300))

    Column(
        modifier = Modifier.weight(1f).fillMaxHeight().clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isSelected) screen.filledIcon else screen.outlinedIcon,
            contentDescription = screen.label,
            tint = iconColor,
            modifier = Modifier.size(iconSize)
        )
        if (isSelected) {
            Text(text = screen.label, color = iconColor, style = MaterialTheme.typography.labelSmall)
        }
    }
}