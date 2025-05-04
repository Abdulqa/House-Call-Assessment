package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.housecall_takehomeassissment.R
import com.example.housecall_takehomeassissment.presentation.theme.Blue

@Composable
fun MedicationDetailScreen(
    navController: NavController,
    medicineName: String = "Medicine 1",
    genericName: String = "Generic Name",
    dosageInfo: String = """
        Tablet:

        • Adult: 1-2 tablets every 4 to 6 hours up to a maximum of 4 gm (8 tablets) daily.
        • Children (6-12 years): ½ to 1 tablet 3 to 4 times daily. For long term treatment it is wise not to exceed the dose beyond 2.6 gm/day.

        Extended Release Tablet:
        • Adults & Children over 12 years: Two tablets, swallowed whole, every 6 to 8 hours (maximum of 6 tablets in any 24 hours).The tablet must not be crushed.

        Syrup/Suspension:
        • Children under 3 months: 10 mg/kg body weight (reduce to 5 mg/kg if jaundiced) 3 to 4 times daily.
        • 3 months to below 1 year: ½ to 1 teaspoonful 3 to 4 times daily.
        • 1-5 years: 1 -2 teaspoonful 3 to 4 times daily.
        • 6-12 years: 2-A teaspoonful 3 to 4 times daily.
        • Adults: 4-8 teaspoonful 3 to 4 times daily.

        Suppository:
        • Children 3-12 months: 60-120 mg,4 times daily.
        • Children 1-5 years: 125-250 mg 4 times daily.
        • Children 6-12 years: 250-500 mg 4 times daily.
        • Adults & children over 12 years: 0.5-1 gm 4 times daily.

        Paediatric Drop:
        • Children Upto 3 months: 0.5 ml (40 mg)
        • 4 to 11 months: 1.0 ml (80 mg)
        • 7 to 2 years: 1.5 ml (120 mg). Do not exceed more than 5 dose daily for a maximum of 5 days.
        • Paracetamol tablet with actizorb technology: It dissolves up to five times faster than standard Paracetamol tablets. It is a fast acting and safe analgesic with marked antipyretic property. It is specially suitable for patients who, for any reason, can not tolerate aspirin or other analgesics.

        Adults and children (aged 12 years and over): Take 1 to 2 Tablets every four to six hours as needed. Do not take more than 8 caplets in 24 hours.
        Children (7 to 11 years): Take ½-1 Tablet every four to six hours as needed. Do not take more than 4 caplets in 24 hours. Not recommended in children under 7 years.
    """.trimIndent()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F9))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            Text(
                text = "Back",
                color = Blue,
                fontSize = 18.sp,
                modifier = Modifier.clickable { navController.popBackStack() },
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_medicine),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Text(
                text = medicineName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = genericName,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 0.dp,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Details",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = dosageInfo,
                    color = Color.Black,
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                )
            }
        }

        Button(
            onClick = { /* Handle add action */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF007AFF))
        ) {
            Text("Add Medication to List", fontSize = 17.sp, color = Color.White)
        }
    }
}

@Preview
@Composable
private fun MedicationDetailScreenPreview() {
    MedicationDetailScreen(rememberNavController())
}