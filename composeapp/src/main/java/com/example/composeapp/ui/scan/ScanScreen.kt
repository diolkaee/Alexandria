package com.example.composeapp.ui.scan

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.ui.common.AlexandriaScreenPreview
import com.example.composeapp.ui.theme.AlexandriaTheme

@Composable
fun ScanScreen(
    viewModel: ScanViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val context = LocalContext.current
    val surfaceRequest by viewModel.surfaceRequest.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var canShowCamera by remember { mutableStateOf(false) }
    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            canShowCamera = isGranted
        }

    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    surfaceRequest?.let {
        Box(Modifier.fillMaxSize()) {
            CameraXViewfinder(
                surfaceRequest = it,
            )
            ScanResults(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .heightIn(max = 200.dp),
                books = uiState.searchResults,
                onArchiveBook = viewModel::archive,
            )
        }
    }
}

@Composable
@AlexandriaScreenPreview
private fun Preview() {
    AlexandriaTheme {
        ScanScreen(viewModel = viewModel())
    }
}