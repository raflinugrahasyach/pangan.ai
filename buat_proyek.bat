@echo off
:: Script untuk membuat struktur direktori dan file template kosong untuk aplikasi PanganApp.
:: Jalankan script ini dari dalam direktori root proyek Anda (misal: D:\...\App)

set "base_path=app\src\main\java\com\example\panganapp"

echo.
echo == MEMBUAT STRUKTUR DIREKTORI ==
mkdir "%base_path%\data"
mkdir "%base_path%\model"
mkdir "%base_path%\navigation"
mkdir "%base_path%\ui"
mkdir "%base_path%\ui\components"
mkdir "%base_path%\ui\screens"
mkdir "%base_path%\ui\theme"
echo [OK] Struktur direktori berhasil dibuat.
echo.

echo == MEMBUAT FILE TEMPLATE KOSONG ==

:: --- Direktori model ---
(
    echo package com.example.panganapp.model
    echo.
    echo // File template untuk data model Commodity
) > "%base_path%\model\Commodity.kt"
echo [OK] model\Commodity.kt

(
    echo package com.example.panganapp.model
    echo.
    echo // File template untuk data model EwsAlert
) > "%base_path%\model\EwsAlert.kt"
echo [OK] model\EwsAlert.kt

(
    echo package com.example.panganapp.model
    echo.
    echo // File template untuk data model CalendarEvent
) > "%base_path%\model\CalendarEvent.kt"
echo [OK] model\CalendarEvent.kt

(
    echo package com.example.panganapp.model
    echo.
    echo // File template untuk data model MarketData
) > "%base_path%\model\MarketData.kt"
echo [OK] model\MarketData.kt

:: --- Direktori data ---
(
    echo package com.example.panganapp.data
    echo.
    echo // File template untuk Mock Data
) > "%base_path%\data\MockData.kt"
echo [OK] data\MockData.kt

:: --- Direktori ui\theme ---
(
    echo package com.example.panganapp.ui.theme
    echo.
    echo // File template untuk definisi Warna
) > "%base_path%\ui\theme\Color.kt"
echo [OK] ui\theme\Color.kt

(
    echo package com.example.panganapp.ui.theme
    echo.
    echo // File template untuk definisi Tipografi
) > "%base_path%\ui\theme\Type.kt"
echo [OK] ui\theme\Type.kt

(
    echo package com.example.panganapp.ui.theme
    echo.
    echo // File template untuk setup Tema Aplikasi
) > "%base_path%\ui\theme\Theme.kt"
echo [OK] ui\theme\Theme.kt

:: --- Direktori ui\components ---
(
    echo package com.example.panganapp.ui.components
    echo.
    echo // File template untuk komponen UI Chart
) > "%base_path%\ui\components\ChartComponents.kt"
echo [OK] ui\components\ChartComponents.kt

(
    echo package com.example.panganapp.ui.components
    echo.
    echo // File template untuk komponen UI EwsCard
) > "%base_path%\ui\components\EwsCard.kt"
echo [OK] ui\components\EwsCard.kt

(
    echo package com.example.panganapp.ui.components
    echo.
    echo // File template untuk komponen UI PriceCard
) > "%base_path%\ui\components\PriceCard.kt"
echo [OK] ui\components\PriceCard.kt

:: --- Direktori ui\screens ---
(
    echo package com.example.panganapp.ui.screens
    echo.
    echo // File template untuk layar Beranda (Home)
) > "%base_path%\ui\screens\HomeScreen.kt"
echo [OK] ui\screens\HomeScreen.kt

(
    echo package com.example.panganapp.ui.screens
    echo.
    echo // File template untuk layar EWS
) > "%base_path%\ui\screens\EwsScreen.kt"
echo [OK] ui\screens\EwsScreen.kt

(
    echo package com.example.panganapp.ui.screens
    echo.
    echo // File template untuk layar Kalender
) > "%base_path%\ui\screens\CalendarScreen.kt"
echo [OK] ui\screens\CalendarScreen.kt

(
    echo package com.example.panganapp.ui.screens
    echo.
    echo // File template untuk layar Pasar
) > "%base_path%\ui\screens\MarketScreen.kt"
echo [OK] ui\screens\MarketScreen.kt

:: --- Direktori navigation ---
(
    echo package com.example.panganapp.navigation
    echo.
    echo // File template untuk setup Navigasi Aplikasi
) > "%base_path%\navigation\AppNavigation.kt"
echo [OK] navigation\AppNavigation.kt

:: --- File MainActivity ---
(
    echo package com.example.panganapp
    echo.
    echo // File template untuk MainActivity
) > "%base_path%\MainActivity.kt"
echo [OK] MainActivity.kt

echo.
echo ======================================================
echo SEMUA FILE TEMPLATE BERHASIL DIBUAT.
echo ======================================================
echo.

pause
