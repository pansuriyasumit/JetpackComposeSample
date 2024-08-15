package com.fifteen11.jetpacksamplelibrary.quotestesting.ui

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.fifteen11.jetpacksamplelibrary.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun testNextButton_expectedCorrectQuotes() {
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())

        //If you want to type something into the text field use this syntax
        //onView(withId(R.id.btnNext)).perform(typeText("CSS: Where 1px off can ruin your entire day. #frontend"))

        onView(withId(R.id.quoteText)).check(matches(withText("CSS: Where 1px off can ruin your entire day. #frontend")))
    }

    @Test
    fun testPrevious_expectedCorrectQuotes() {

    }

    @Test
    fun testShareButton_expectedIntentChooser() {
        Intents.init() //Initialize Intents Library
        val expected = allOf(hasAction(Intent.ACTION_SEND)) //Expected intent action
        onView(withId(R.id.floatingActionButton)).perform(click()) //Perform action
        Intents.intended(expected) //Check if expected intent was sent.
        Intents.release() //Release Intents
    }
}