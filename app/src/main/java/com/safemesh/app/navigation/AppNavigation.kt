package com.safemesh.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.safemesh.app.auth.FirebaseAuthManager
import com.safemesh.app.data.ContactRepository
import com.safemesh.app.data.FirestoreContactRepository
import com.safemesh.app.model.EmergencyContact
import com.safemesh.app.ui.AddContactScreen
import com.safemesh.app.ui.CheckInScreen
import com.safemesh.app.ui.EditContactScreen
import com.safemesh.app.ui.EmergencyContactsScreen
import com.safemesh.app.ui.HomeScreen
import com.safemesh.app.ui.ProfileScreen
import com.safemesh.app.ui.SettingsScreen
import com.safemesh.app.ui.SosScreen

@Composable
fun AppNavigation(
    onLogout: () -> Unit
    ) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME) {
            HomeScreen(
                onSosClick = {
                    navController.navigate(Routes.SOS)
                },
                onCheckInClick = {
                    navController.navigate(Routes.CHECKIN)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                },
                onContactsClick = {
                    navController.navigate(Routes.CONTACTS)
                },

            )
        }

        composable(Routes.SOS) {
            SosScreen()
        }

        composable(Routes.CHECKIN) {
            CheckInScreen()
        }

        composable(Routes.SETTINGS) {

            SettingsScreen(

                userEmail =
                    FirebaseAuthManager()
                        .currentUser()
                        ?.email ?: "Unknown User",

                isDarkMode = false,

                onThemeChange = { },

                onLogout = onLogout,
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }
        composable(Routes.PROFILE) {

            ProfileScreen(

                onEditClick = {
                    navController.navigate(Routes.EDIT_PROFILE)
                }
            )
        }
//        composable(Routes.EDIT_PROFILE) {
//
//            EditProfileScreen(
//
//                profile = currentProfile,
//
//                onSave = { updatedProfile ->
//
//                    ProfileRepository.updateProfile(
//
//                        updatedProfile,
//
//                        onSuccess = {
//                            navController.popBackStack()
//                        },
//
//                        onFailure = {
//                            println(it)
//                        }
//                    )
//                },
//
//                onCancel = {
//                    navController.popBackStack()
//                }
//            )
//        }
        composable(Routes.CONTACTS) {

            EmergencyContactsScreen(



                onAddContactClick = {
                    navController.navigate(Routes.ADD_CONTACT)
                },

                onEditContactClick = { contact ->

                    ContactRepository.selectedContact = contact

                    navController.navigate(Routes.EDIT_CONTACT)
                }
            )
        }

        composable(Routes.ADD_CONTACT) {

            AddContactScreen(

                onSaveContact = { name, phone, relationship ->

                    FirestoreContactRepository.addContact(
                        EmergencyContact(
                            name = name,
                            phone = phone,
                            relationship = relationship
                        ),
                        onSuccess = {
                            navController.popBackStack()
                        },
                        onFailure = {
                            println(it)
                        }
                    )


                }
            )
        }

        composable(Routes.EDIT_CONTACT) {

            ContactRepository.selectedContact?.let { contact ->

                EditContactScreen(

                    contact = contact,

                    onUpdateContact = { updatedContact ->

                        FirestoreContactRepository.updateContact(
                            contact = updatedContact,

                            onSuccess = {
                                navController.popBackStack()
                            },

                            onFailure = { error ->
                                println(error)
                            }
                        )


                    },
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
