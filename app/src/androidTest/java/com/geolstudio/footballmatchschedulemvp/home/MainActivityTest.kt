package com.geolstudio.footballmatchschedulemvp.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.geolstudio.footballmatchschedulemvp.R
import com.geolstudio.footballmatchschedulemvp.R.id.*
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.view.View
import android.widget.Button
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.v7.widget.SearchView
import android.widget.ImageButton
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class MainActivityTest {
    private var millis: Long = 1000
    private var leagueName = "German Bundesliga"
    private var queryTeam = "arsenal"

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAllAppBehaviour() {
        testMatchFragmentAndAddtoFavorite()
        testTeamsFragmentAndAddtoFavorite()
        testRemoveFavoriteMatchAndTeam()
        testClickCreateEvent()
    }

    @Test
    fun testMatchFragmentAndAddtoFavorite() {
        onView(withIndex(withId(R.id.spinner), 0)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.spinner), 0)).perform(click())
        onView(withText(leagueName)).perform(click())
        Thread.sleep(millis)

        onView(withIndex(withId(R.id.recycler_match), 0)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.recycler_match), 0))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(millis)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_FAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        pressBack()

        onView(allOf(withText(R.string.lastmatch), isDescendantOfA(withId(R.id.tablayout))))
            .perform(click())
            .check(matches(isDisplayed()))

        Thread.sleep(millis)

        onView(withIndex(withId(R.id.spinner), 1)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.spinner), 1)).perform(click())
        onView(withText(leagueName)).perform(click())
        Thread.sleep(millis)

        onView(withIndex(withId(R.id.recycler_match), 1)).check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.recycler_match), 1))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        Thread.sleep(millis)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_FAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun testTeamsFragmentAndAddtoFavorite() {
        Thread.sleep(millis)
        onView(allOf(withId(teams))).check(matches(isDisplayed()))

        onView(withId(teams)).perform(click())
        Thread.sleep(millis)

        onView(withId(spinner)).check(matches(isDisplayed()))
        Thread.sleep(millis)

        onView(withId(spinner)).perform(click())
        onView(withText(leagueName)).perform(click())
        Thread.sleep(millis * 2)

        onView(withId(recycler_teams)).check(matches(isDisplayed()))

        onView(withId(m_search)).perform(click())
        Thread.sleep(millis)

        onView(withId(R.id.m_search)).perform(typeSearchViewText(queryTeam))

        Thread.sleep(millis)

        onView(withId(recycler_teams))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        Thread.sleep(millis)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_FAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        onView(withId(tablayout)).check(matches(isDisplayed()))
        onView(withId(vp_teamdetail)).check(matches(isDisplayed()))
        onView(allOf(withText(R.string.players), isDescendantOfA(withId(R.id.tablayout))))
            .perform(click())
            .check(matches(isDisplayed()))

        onView(withId(recycler_players))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(isDisplayed()))
        onView(allOf(withId(recycler_players), isDescendantOfA(withId(R.id.vp_teamdetail))))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        Thread.sleep(millis * 2)

        pressBack()

        onView(allOf(withText(R.string.overview), isDescendantOfA(withId(R.id.tablayout))))
            .perform(click())
            .check(matches(isDisplayed()))

        Thread.sleep(millis)

        pressBack()

    }

    @Test
    fun testRemoveFavoriteMatchAndTeam() {
        onView(withId(favorites)).check(matches(isDisplayed()))

        onView(withId(favorites)).perform(click())
        onView(withId(recycler_favmatch)).check(matches(isDisplayed()))

        Thread.sleep(millis)
        onView(withId(recycler_favmatch))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    buttonclicklistener(btn_detail)
                )
            )

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_UNFAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        pressBack()

        onView(withId(refresh_favmatch)).perform(swipeDown())
        Thread.sleep(millis)

        onView(withId(recycler_favmatch))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    buttonclicklistener(btn_detail)
                )
            )

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_UNFAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        pressBack()

        onView(withId(refresh_favmatch)).perform(swipeDown())
        Thread.sleep(millis)

        onView(withId(vp_favorites)).perform(swipeLeft())
        Thread.sleep(millis)

        onView(withId(recycler_favteam)).check(matches(isDisplayed()))
        onView(withId(recycler_favteam))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText(R.string.TOAST_UNFAVORITE))
            .inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()))

        pressBack()

        onView(withId(recycler_favteam)).perform(swipeDown())
    }

    @Test
    fun testClickCreateEvent() {
        Thread.sleep(millis)
        onView(withId(bottom_match)).check(matches(isDisplayed()))

        onView(withId(bottom_match)).perform(click())
        Thread.sleep(millis)
        onView(withIndex(withId(R.id.recycler_match), 0))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    imagebuttonclicklistener(add_to_calendar)
                )
            )
    }

    private fun buttonclicklistener(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<Button>(id)
                v.performClick()
            }
        }
    }

    private fun imagebuttonclicklistener(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<ImageButton>(id)
                v.performClick()
            }
        }
    }

    fun typeSearchViewText(text: String): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //Ensure that only apply if it is a SearchView and if it is visible.
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController, view: View) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }

    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }
}