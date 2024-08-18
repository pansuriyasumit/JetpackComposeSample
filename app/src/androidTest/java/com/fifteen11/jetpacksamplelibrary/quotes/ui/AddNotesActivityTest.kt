package com.fifteen11.jetpacksamplelibrary.quotes.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.fifteen11.jetpacksamplelibrary.R
import org.junit.Rule
import org.junit.Test

class AddNotesActivityTest {

    /**
     * Defile a rule which will open the [AddNotesActivity]
     */
    @get:Rule
    val activityRule = activityScenarioRule<AddNotesActivity>()


    @Test
    fun testSubmitButton_expectedCorrectValues() {
        onView(withId(R.id.txt_title)).perform(typeText("Test Title"), closeSoftKeyboard()) //Type the Text in EditText View
        onView(withId(R.id.txt_description)).perform(typeText("Test Description"), closeSoftKeyboard()) //Type the Text in EditText View

        onView(withId(R.id.btn_submit)).perform(click()) //Click on Button

        onView(withId(R.id.txt_message)).check(matches(withText("Title - Test Title | Description - Test Description"))) //Check & Match the Text in TextView
    }
}