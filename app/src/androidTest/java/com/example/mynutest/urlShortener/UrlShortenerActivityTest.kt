package com.example.mynutest.urlShortener

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mynutest.R
import com.example.mynutest.urlShortener.presentation.ui.activity.UrlShortenerActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UrlShortenerActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(UrlShortenerActivity::class.java)

    @Test
    fun emptyUrlAndError(){
        onView(withId(R.id.editText_uri)).perform(typeText(" "))
        onView(withId(R.id.button_shorUrl)).perform(click())
        onView(withText(R.string.please_validate_data)).check(matches(isDisplayed()))
    }

    @Test
    fun writeUrlAndLoad() {
        val entryText = InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(R.string.url)
        onView(withId(R.id.editText_uri)).perform(typeText(entryText))
        onView(withId(R.id.button_shorUrl)).perform(click())
        onView(withId(R.id.progressBar_loader)).check(matches(isDisplayed()))
    }

    @Test
    fun checkFields() {
        onView(withId(R.id.textInputLayout_url)).check(matches(isDisplayed()))
        onView(withId(R.id.editText_uri)).check(matches(isDisplayed()))
        onView(withId(R.id.button_shorUrl)).check(matches(isDisplayed()))
        onView(withId(R.id.textView_recent_shortened_url)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView_recentUrls)).check(matches(not(isDisplayed())))
    }
}