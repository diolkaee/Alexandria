package com.example.composeapp.ui.scan

import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.viewfinder.compose.Viewfinder
import androidx.camera.viewfinder.surface.ImplementationMode
import androidx.camera.viewfinder.surface.TransformationInfo
import androidx.camera.viewfinder.surface.ViewfinderSurfaceRequest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.composeapp.ui.common.AlexandriaScreenPreview
import com.example.composeapp.ui.theme.AlexandriaTheme

@Composable
fun ScanScreen(uiState: ScanState, onSearchBooks: (List<Long>) -> Unit) {
    var canShowCamera by remember { mutableStateOf(false) }
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            canShowCamera = isGranted
        }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    Viewfinder(
        surfaceRequest = ViewfinderSurfaceRequest.Builder(Size(1440, 1440)).build(),
        implementationMode = ImplementationMode.EMBEDDED,
        transformationInfo = TransformationInfo(0, 0, 0, 0, 0, false),
    )
}

@Composable
@AlexandriaScreenPreview
private fun Preview() {
    AlexandriaTheme {
        ScanScreen(
            uiState = ScanState(),
            onSearchBooks = {},
        )
    }
}