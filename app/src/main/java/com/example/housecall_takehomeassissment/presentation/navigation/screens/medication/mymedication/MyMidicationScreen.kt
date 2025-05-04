package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.housecall_takehomeassissment.R
import com.example.housecall_takehomeassissment.presentation.Routes
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models.MyMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.theme.Blue
import com.example.housecall_takehomeassissment.presentation.theme.White
import kotlinx.coroutines.delay

@Composable
fun MyMedicationScreen(
    navController: NavController,
    viewModel: MyMedicationViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp),
    ) {
        Text(
            "My Medications",
            style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(
                items = uiState.medicineList,
                key = { it.rxcui.orEmpty() }
            ) { medicine ->
                SwipeToDeleteContainer(
                    item = medicine,
                    onDelete = {
                        viewModel.onEvent(MyMedicationUiEvents.OnDelete(it))
                    }
                ) { medicine ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 54.dp)
                            .background(color = White)
                            .clickable {
                                navController.navigate(
                                    Routes.Companion.MEDICATION_DETAILS.createRoute(
                                        medicine,
                                        false
                                    )
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_medicine),
                            contentDescription = "icon_medicine"
                        )

                        Text(
                            text = "${medicine.rxcui.orEmpty()}\n${medicine.name.orEmpty()}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate(Routes.SEARCH_MEDICATION) }
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.ic_plus), contentDescription = "")

            Spacer(Modifier.width(10.dp))

            Text(
                "Search Medications",
                style = TextStyle(color = Blue, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember {
        mutableStateOf(false)
    }

    val state = rememberDismissState(
        confirmStateChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text("Delete", style = TextStyle(color = White))
    }
}

@Preview
@Composable
private fun MyMedicationScreenPreview() {
    MyMedicationScreen(rememberNavController())
}