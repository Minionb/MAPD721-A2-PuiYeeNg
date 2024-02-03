package com.example.mapd721_a2_puiyeeng

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapd721_a2_puiyeeng.ui.theme.MAPD721A2PuiYeeNgTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAPD721A2PuiYeeNgTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    var contactName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Contact App",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Contact Name field
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = contactName,
            onValueChange = { contactName = it },
            label = { Text(text = "Contact Name", color = Color.Gray, fontSize = 12.sp) },
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Contact Number field
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            value = contactNumber,
            onValueChange = { contactNumber = it },
            label = { Text(text = "Contact Number", color = Color.Gray, fontSize = 12.sp) },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            // Fetch Contacts button
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {

                },
            )
            {
                // button text
                Text(
                    text = "Fetch Contacts",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            // Add Contact button
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF006400)),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(start = 16.dp, end = 16.dp),
                onClick = {

                },
            )
            {
                // button text
                Text(
                    text = "Add Contact",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

            Box(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.fillMaxWidth())
            }
            // Box with student info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(16.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = "Pui Yee Ng",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "301366105",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }


// Preview of Main Screen
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MAPD721A2PuiYeeNgTheme {
        MainScreen()
    }
}