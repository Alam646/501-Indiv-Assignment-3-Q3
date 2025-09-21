package com.example.indivassignment3q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.indivassignment3q3.ui.theme.IndivAssignment3Q3Theme

// Data class for a contact
data class Contact(val id: Int, val name: String)

// Sample contacts
val sampleContactsStep1 = listOf(
    Contact(1, "Alice Smith"),
    Contact(2, "Bob Johnson"),
    Contact(3, "Charlie Brown"),
    Contact(4, "David Wilson"),
    Contact(5, "Eva Green"),
    Contact(6, "Fiona Apple"),
    Contact(7, "George Harrison"),
    Contact(8, "Helen Hunt"),
    Contact(9, "Ivan Drago"),
    Contact(10, "Julia Roberts"),
    Contact(11, "Kevin Bacon")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndivAssignment3Q3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactListScreen(
                        contacts = sampleContactsStep1, // Pass sample contacts
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactListScreen(contacts: List<Contact>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(contacts) { contact ->
            Text(
                text = contact.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    IndivAssignment3Q3Theme {
        ContactListScreen(contacts = sampleContactsStep1, modifier = Modifier.fillMaxSize())
    }
}
