package com.example.indivassignment3q3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState // Added
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState // Added
import androidx.compose.material.icons.Icons // Added
import androidx.compose.material.icons.filled.KeyboardArrowUp // Added
import androidx.compose.material3.FloatingActionButton // Added
import androidx.compose.material3.Icon // Added
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf // Added
import androidx.compose.runtime.getValue // Added
import androidx.compose.runtime.remember // For derivedStateOf
import androidx.compose.runtime.rememberCoroutineScope // Added
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indivassignment3q3.ui.theme.IndivAssignment3Q3Theme
import kotlinx.coroutines.launch // Added

// Data class for a contact
data class Contact(val id: Int, val name: String)

// Expanded sample contacts (more than 50 for demonstration)
val sampleContactsStep3 = listOf( // Renamed for clarity, content is the same as sampleContactsStep2
    Contact(1, "Alice Smith"), Contact(2, "Bob Johnson"), Contact(3, "Charlie Brown"),
    Contact(4, "David Wilson"), Contact(5, "Daniel Miller"), Contact(6, "Daisy Garcia"),
    Contact(7, "Eva Green"), Contact(8, "Edward Davis"), Contact(9, "Emily White"),
    Contact(10, "Fiona Apple"), Contact(11, "Frank Harris"), Contact(12, "Flora Clark"),
    Contact(13, "George Harrison"), Contact(14, "Grace Lee"), Contact(15, "Gary Lewis"),
    Contact(16, "Helen Hunt"), Contact(17, "Henry Walker"), Contact(18, "Hannah Hall"),
    Contact(19, "Ivan Drago"), Contact(20, "Ivy Allen"), Contact(21, "Isaac Young"),
    Contact(22, "Julia Roberts"), Contact(23, "Jack King"), Contact(24, "Jessica Wright"),
    Contact(25, "Kevin Bacon"), Contact(26, "Kate Scott"), Contact(27, "Kyle Green"),
    Contact(28, "Linda Carter"), Contact(29, "Liam Adams"), Contact(30, "Laura Baker"),
    Contact(31, "Michael Jordan"), Contact(32, "Megan Nelson"), Contact(33, "Mark Hill"),
    Contact(34, "Nancy Drew"), Contact(35, "Noah Roberts"), Contact(36, "Nora Campbell"),
    Contact(37, "Oliver Twist"), Contact(38, "Olivia Phillips"), Contact(39, "Oscar Evans"),
    Contact(40, "Peter Pan"), Contact(41, "Penelope Edwards"), Contact(42, "Paul Turner"),
    Contact(43, "Quincy Jones"), Contact(44, "Queenie Collins"), Contact(45, "Quinn Stewart"),
    Contact(46, "Robert De Niro"), Contact(47, "Rachel Morris"), Contact(48, "Ryan Murphy"),
    Contact(49, "Sophia Loren"), Contact(50, "Samuel Rogers"), Contact(51, "Sarah Bell"),
    Contact(52, "Thomas Edison"), Contact(53, "Tina Cook"), Contact(54, "Tom Bailey"),
    Contact(55, "Ursula Andress"), Contact(56, "Ulysses Moore"), Contact(57, "Uma Price"),
    Contact(58, "Victor Hugo"), Contact(59, "Victoria Wood"), Contact(60, "Vincent Perry"),
    Contact(61, "Walter White"), Contact(62, "Wendy Barnes"), Contact(63, "William Ross"),
    Contact(64, "Xenia Onatopp"), Contact(65, "Xavier Butler"), Contact(66, "Xia Henderson"),
    Contact(67, "Yvonne De Carlo"), Contact(68, "Yosef Coleman"), Contact(69, "Yasmin Griffin"),
    Contact(70, "Zack Morris"), Contact(71, "Zoe Simmons"), Contact(72, "Zelda Patterson")
).sortedBy { it.name }


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndivAssignment3Q3Theme {
                // Remember LazyListState for scroll position
                val listState = rememberLazyListState()
                // Remember CoroutineScope for launching scroll animation
                val coroutineScope = rememberCoroutineScope()

                // Derived state to control FAB visibility
                val showFab by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex > 10
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (showFab) { // Show FAB only when scrolled past item 10
                            FloatingActionButton(
                                onClick = {
                                    coroutineScope.launch {
                                        // Animate scroll to the top of the list
                                        listState.animateScrollToItem(index = 0)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Scroll to Top"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    ContactListScreen(
                        contacts = sampleContactsStep3,
                        modifier = Modifier.padding(innerPadding),
                        listState = listState // Pass the listState here
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactListScreen(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState() // Added listState parameter with default
) {
    val groupedContacts = contacts.groupBy { it.name.first().uppercaseChar() }

    LazyColumn(
        modifier = modifier,
        state = listState // Use the passed listState
    ) {
        groupedContacts.forEach { (letter, contactsInGroup) ->
            stickyHeader {
                Text(
                    text = letter.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.9f))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            }
            items(contactsInGroup, key = { contact -> contact.id }) { contact ->
                Text(
                    text = contact.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    IndivAssignment3Q3Theme {
        // For preview, we can use a default state or pass one if needed for specific scenarios
        val listState = rememberLazyListState()
        Scaffold( // Added Scaffold to preview FAB behavior if needed
            floatingActionButton = {
                 FloatingActionButton(onClick = {}) { // Dummy FAB for preview
                     Icon(Icons.Filled.KeyboardArrowUp, "Scroll to top")
                 }
            }
        ) { paddingValues ->
            ContactListScreen(
                contacts = sampleContactsStep3,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                listState = listState
            )
        }
    }
}
