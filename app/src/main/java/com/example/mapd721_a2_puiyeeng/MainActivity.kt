package com.example.mapd721_a2_puiyeeng

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
                    MainScreen(context = this)
                }
            }
        }
    }
}

@Composable
fun MainScreen(context: ComponentActivity) {

    var contactName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var contacts by remember { mutableStateOf(emptyList<Contact>()) }
    var fetchButtonClicked by remember { mutableStateOf(false) }


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
                    fetchButtonClicked = true
                    contacts = loadContacts(context)
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
                    addContact(context, contactName, contactNumber)
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

        Spacer(modifier = Modifier.height(20.dp))
        ContactsList(contacts, fetchButtonClicked)
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

@Composable
fun ContactsList(contacts: List<Contact>, fetchButtonClicked:Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Contacts",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }

    // Display the list of contacts or a message if the list is empty
    if (fetchButtonClicked) {
        if (contacts.isEmpty()) {
            Text(text = "No contacts available")
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Display each contact in a row
                items(contacts) { contact ->
                    ContactItem(contact)
                }
            }
        }
    }
}


@Composable
fun ContactItem(contact: Contact) {
    // Display each contact in a row with an icon and text

        Row(
            modifier = Modifier
                .fillMaxSize()

                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = contact.displayName)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = contact.phoneNumber)
        }

}

// Data class to represent a contact
data class Contact(val displayName: String, val phoneNumber: String)

@SuppressLint("Range")
fun loadContacts(context: ComponentActivity): List<Contact> {
    val contacts = mutableListOf<Contact>()

    // Specify the columns to retrieve from the contacts table
    val projection = arrayOf(
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    // Use the content resolver to query contacts
    context.contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        projection,
        null,
        null,
        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    )?.use { cursor ->
        // Check if the cursor has data
        if (cursor.moveToFirst()) {
            val displayNameIndex =
                cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
            val phoneNumberIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            do {
                // Retrieve display name and phone number from the cursor and add to the list
                val displayName = cursor.getString(displayNameIndex)
                val phoneNumber = cursor.getString(phoneNumberIndex)

                contacts.add(Contact(displayName, phoneNumber))
            } while (cursor.moveToNext())
        }
    }

    return contacts
}

fun addContact(context: Context, name: String, phoneNumber: String): Boolean {
    val contentResolver: ContentResolver = context.contentResolver
    val rawContactValues = ContentValues()

    // Insert empty ContentValues
    val nameUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, rawContactValues)
    // get rawContactId, unique identifier for each raw contact, for new contact creation
    val rawContactId = nameUri?.lastPathSegment?.toLongOrNull()

    // Check if the raw contact ID is valid
    if (rawContactId != null) {
        val nameValues = ContentValues()
        nameValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        nameValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        nameValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
        // Insert contact name
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, nameValues)

        val numberValues = ContentValues()
        numberValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        numberValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        numberValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
        // Insert contact phone number
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, numberValues)

        return true
    }

    return false
}

// Preview of Main Screen
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MAPD721A2PuiYeeNgTheme {
        MainScreen(context = ComponentActivity())
    }
}