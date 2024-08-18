package com.fifteen11.jetpacksamplelibrary.quotes.ui

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.fifteen11.jetpacksamplelibrary.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeoutException

/**
 * UI Test Cases Like,
 * 1. Input into TextField
 * 2. Button Press
 * 3. Scroll
 * 4. CheckBox or RadioButton checked/unchecked
 * 5. Click on UI Element
 *
 * Simple interact with the [View]
 *
 * For that,
 * 1. First we need to get View Reference - View Access
 * 2. After that perform some action on that View element - Interaction on View
 * 3. Assert the result - Test the UI after performing some action on View
 *
 * *** hamcrest *** -> This library is used for the matches()
 */

class MainActivityTest {

    /**
     * [@Rule] is an instance of the class whereas [@Before] is a function.
     * To share common code among different test classes, rules are used.
     *
     * For Example, If you have one class and multiple methods to test them and for that
     * you need some common environment (common code execution), then we need to define some rules.
     */

    /**
     * We need to Test [MainActivity], so for that we need to launch [MainActivity]. For that we define the rules
     */
    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()
    //Alternative Way -->ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        disableAnimations()
    }

    private fun disableAnimations() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        instrumentation.uiAutomation.executeShellCommand(
            "settings put global window_animation_scale 0.0"
        )
        instrumentation.uiAutomation.executeShellCommand(
            "settings put global transition_animation_scale 0.0"
        )
        instrumentation.uiAutomation.executeShellCommand(
            "settings put global animator_duration_scale 0.0"
        )
    }

    /**
     * For this test case, we required [MainActivity] instance so we just define the rule
     */
    @Test
    fun testNextButton_expectedCorrectQuote() {
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.btnNext)).perform(click())
        //onView(withId(R.id.btnNext)).perform(typeText("Required to Type Something in the EditText Filed"))

        onView(withId(R.id.quoteText)).check(matches(withText("Difficulties increase the nearer we get to the goal.")))
    }

    @Test
    fun testShareButton_expectedIntentChooser() {
        Intents.init() //For Starting the Explicit Intent
        val expected = allOf(hasAction(Intent.ACTION_SEND)) //For Matching the Intent Action

        onView(withId(R.id.floatingActionButton)).perform(click()) //For Performing the Click Action
        intended(expected) //For Checking the Intent - Assertion Statement
        Intents.release() //For Releasing the Intent
    }


    fun waitUntil(condition: View.() -> Boolean, timeout: Long = 5000L): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait until view satisfies condition"
            }

            override fun perform(uiController: UiController, view: View) {
                val endTime = System.currentTimeMillis() + timeout
                do {
                    if (condition(view)) return
                    uiController.loopMainThreadForAtLeast(100)
                } while (System.currentTimeMillis() < endTime)

                throw PerformException.Builder()
                    .withActionDescription(description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException("Waited for condition but it was not met"))
                    .build()
            }
        }
    }
}