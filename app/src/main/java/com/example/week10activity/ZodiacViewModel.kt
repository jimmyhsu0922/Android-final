// ZodiacViewModel.kt
package com.example.week10activity.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// --- 替換 R.string 和 R.drawable 的純 Kotlin 資料 ---

// 星座名稱清單 (取代 R.string.*)
private val ZODIAC_NAMES = listOf(
    "摩羯座 (Capricorn)", "水瓶座 (Aquarius)", "雙魚座 (Pisces)",
    "白羊座 (Aries)", "金牛座 (Taurus)", "雙子座 (Gemini)",
    "巨蟹座 (Cancer)", "獅子座 (Leo)", "處女座 (Virgo)",
    "天秤座 (Libra)", "天蠍座 (Scorpio)", "射手座 (Sagittarius)"
)

// 錯誤訊息和介面字串
const val PROMPT_TEXT = "請輸入您的生日"
const val MONTH_LABEL = "月 (Month)"
const val DAY_LABEL = "日 (Day)"
const val GET_ZODIAC_BUTTON = "得知星座"
const val SET_BIRTHDAY_BUTTON = "設定生日"
const val INPUT_ERROR_MESSAGE = "輸入錯誤，月份需在 1-12，日期需在 1-31 之間。"

// Zodiac 資訊 (不使用 R.drawable，改用 Icon)
data class ZodiacState(
    val month: Int = 5,
    val day: Int = 10,
    val zodiacName: String = "金牛座 (Taurus)",
    // 使用 Compose 內建的 Icon 作為符號的替代品
    val symbol: ImageVector = Icons.Default.Star
)

class ZodiacViewModel : ViewModel() {
    private val _state = MutableStateFlow(ZodiacState())
    val state: StateFlow<ZodiacState> = _state.asStateFlow()

    // 星座截止日期 (boundary day of the month)
    private val bound = intArrayOf(20, 19, 21, 20, 21, 21, 23, 23, 23, 23, 22, 22)

    fun updateBirthday(month: Int, day: Int) {
        // 核心計算邏輯
        val zodiacIndex = if (day < bound[month - 1]) {
            month - 1
        } else {
            // 處理 12 月到 1 月的環繞
            if (month == 12) 0 else month
        }

        // 更新 StateFlow
        _state.value = _state.value.copy(
            month = month,
            day = day,
            zodiacName = ZODIAC_NAMES[zodiacIndex]
        )
    }
}