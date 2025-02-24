package com.example.composeapp.ui.common

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = false, showBackground = true, name = "1. Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = false, showBackground = true, name = "2. Dark")
@Preview(fontScale = 2f, showSystemUi = false, showBackground = true, name = "3. FontScale")
annotation class AlexandriaPreview

// Preview that includes System UI & a big device, intended for Screen composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = false, showBackground = true, name = "1. Light")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = false, showBackground = true, name = "2. Dark")
@Preview(fontScale = 2f, showSystemUi = false, showBackground = true, name = "3. FontScale")
@Preview(device = Devices.PIXEL_XL, showSystemUi = false, showBackground = true, name = "4. Big device")
annotation class AlexandriaScreenPreview
