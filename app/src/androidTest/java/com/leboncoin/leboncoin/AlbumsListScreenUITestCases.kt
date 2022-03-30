package com.leboncoin.leboncoin

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.leboncoin.leboncoin.ui.activities.AlbumsListActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class AlbumsListScreenUITestCases {
    @get:Rule
    var activityRule = ActivityTestRule(AlbumsListActivity::class.java)

    @Test
    @Throws(InterruptedException::class)
    fun testVisibilityRecyclerView() {
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.albums_list))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun testCaseForRecyclerScroll() {
        Thread.sleep(5000)
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.albums_list)
        val itemCount = recyclerView.adapter!!.itemCount
        Espresso.onView(ViewMatchers.withId(R.id.albums_list))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }
}