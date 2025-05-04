package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.housecall_takehomeassissment.R
import com.example.housecall_takehomeassissment.presentation.Routes
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model.SearchMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.theme.White

@Composable
fun SearchMedicationScreen(
    navController: NavController,
    viewModel: SearchMedicationViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Back",
                color = Color(0xFF007AFF),
                modifier = Modifier.clickable { navController.popBackStack() },
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Search Medication",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))
        }

        OutlinedTextField(
            value = uiState.query,
            onValueChange = { viewModel.onEvent(SearchMedicationUiEvents.OnQueryChange(it)) },
            placeholder = { Text("Search Medication") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(12.dp)),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(0xFFEFEFF4),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                textColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Search Results",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(uiState.medicineList) { medicine ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 54.dp)
                            .padding(horizontal = 12.dp)
                            .background(color = White)
                            .clickable {
                                navController.navigate(
                                    Routes.Companion.MEDICATION_DETAILS.createRoute(
                                        medicine,
                                        true
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
                                .weight(1f)
                                .padding(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }
        }

        Button(
            onClick = { viewModel.onEvent(SearchMedicationUiEvents.OnSearchSubmit) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007AFF))
        ) {
            Text("Search", fontSize = 17.sp, color = Color.White)
        }
    }
}

@Preview
@Composable
private fun SearchMedicationScreenPreview() {
    SearchMedicationScreen(rememberNavController())
}