package edu.towson.cosc435.mnrva.nav

sealed class Routes(val route: String) {
    //First view the user sees; summary of entries for the week
    object HomeView : Routes("HomeView")

    // View of the user's schedule day-to-day
    object ScheduleView : Routes("ScheduleView")

    //View of the user's entry on a calendar; can view a preview below the calendar by clicking a day on the calendar
    object CalendarView : Routes("CalendarView")

    //View where user can alter their settings
    object SettingsView : Routes("SettingsView")

    //view of a specific entry when a user clicks on it
    object EntryView : Routes("EntryView")

    //view for creating a new entry
    object NewEntryView : Routes("NewEntryView")
}