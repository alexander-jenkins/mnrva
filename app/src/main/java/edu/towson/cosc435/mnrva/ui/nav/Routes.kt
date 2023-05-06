package edu.towson.cosc435.mnrva.ui.nav

sealed class Routes(val route: String) {
    // First view the user sees
    // Summary of entries for the week
    object HomeView : Routes("home")

    // View of the user's schedule day-to-day
    object ScheduleView : Routes("schedule")

    // View of the user's entry on a calendar
    // Can view a preview below the calendar by clicking a day on the calendar
    object CalendarView : Routes("calendar")

    // View where user can alter their settings
    object SettingsView : Routes("settings")

    // View of a specific entry when a user clicks on it
    object entryView : Routes("viewEntry")

    // View for creating a new entry
    object NewEntryView : Routes("newEntry")

    // Login to the application
    object LoginView : Routes("login")

    // Register for the application
    object RegisterView : Routes("register")

}