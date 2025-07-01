package com.quicknotes.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.quicknotes.models.Notes
import com.quicknotes.ui.theme.colorBlack
import com.quicknotes.ui.theme.colorGrey
import com.quicknotes.ui.theme.colorRed
import com.quicknotes.ui.theme.colorWhite
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertNotesScreen(navHostController: NavHostController, id: String?) {

    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()
    val notesDBRef = db.collection("notes")

    val tittle = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (id != "defaultId") {
            notesDBRef.document(id.toString()).get().addOnSuccessListener {
                val singleData = it.toObject(Notes::class.java)
                tittle.value = singleData!!.tittle
                description.value = singleData.description
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (id != "defaultId") "Edit Note" else "Create Note",
                        style = TextStyle(
                            color = colorWhite,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navHostController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = colorWhite
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorBlack,
                    titleContentColor = colorWhite,
                    navigationIconContentColor = colorWhite
                ),
                modifier = Modifier.shadow(8.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    if (tittle.value.isEmpty() || description.value.isEmpty()) {
                        Toast.makeText(context, "Enter Valid Data", Toast.LENGTH_LONG).show()
                    } else {


                        val myNotesID = if (id != "defaultId") {
                            id.toString()
                        } else {
                            notesDBRef.document().id
                        }

                        val notes = Notes(
                            id = myNotesID,
                            tittle = tittle.value,
                            description = description.value
                        )
                        notesDBRef.document(myNotesID).set(notes).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Notes Create Successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                navHostController.popBackStack()


                            } else {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        }


                    }

                },
                contentColor = colorWhite,
                containerColor = colorRed,
                shape = RoundedCornerShape(corner = CornerSize(100.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = ""
                )
            }
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = colorBlack)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Spacer(modifier = Modifier.height(10.dp))

                // Enhanced Title TextField
                OutlinedTextField(
                    value = tittle.value,
                    onValueChange = { tittle.value = it },
                    label = {
                        Text(
                            text = "Title",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = colorWhite,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter an amazing title...",
                            style = TextStyle(
                                color = colorWhite.copy(alpha = 0.6f),
                                fontSize = 16.sp
                            )
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Title Icon",
                            tint = colorWhite,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorWhite,
                        unfocusedTextColor = colorWhite,
                        focusedContainerColor = colorGrey.copy(alpha = 0.8f),
                        unfocusedContainerColor = colorGrey.copy(alpha = 0.6f),
                        focusedBorderColor = colorWhite,
                        unfocusedBorderColor = colorWhite.copy(alpha = 0.3f),
                        focusedLabelColor = colorWhite,
                        unfocusedLabelColor = colorWhite.copy(alpha = 0.7f),
                        focusedLeadingIconColor = colorWhite,
                        unfocusedLeadingIconColor = colorWhite.copy(alpha = 0.7f),
                        cursorColor = colorWhite
                    ),
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(
                        color = colorWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Enhanced Description TextField with fixed top-start icon
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = {
                        Text(
                            text = "Description",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = colorWhite,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter your details, notes, etc here !!...",
                            style = TextStyle(
                                color = colorWhite.copy(alpha = 0.6f),
                                fontSize = 16.sp
                            )
                        )
                    },
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = 15.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Description Icon",
                                tint = colorWhite,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = colorWhite,
                        unfocusedTextColor = colorWhite,
                        focusedContainerColor = colorGrey.copy(alpha = 0.8f),
                        unfocusedContainerColor = colorGrey.copy(alpha = 0.6f),
                        focusedBorderColor = colorWhite,
                        unfocusedBorderColor = colorWhite.copy(alpha = 0.3f),
                        focusedLabelColor = colorWhite,
                        unfocusedLabelColor = colorWhite.copy(alpha = 0.7f),
                        focusedLeadingIconColor = colorWhite,
                        unfocusedLeadingIconColor = colorWhite.copy(alpha = 0.7f),
                        cursorColor = colorWhite
                    ),
                    shape = RoundedCornerShape(16.dp),
                    textStyle = TextStyle(
                        color = colorWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp))
                )


            }
        }


    }

}