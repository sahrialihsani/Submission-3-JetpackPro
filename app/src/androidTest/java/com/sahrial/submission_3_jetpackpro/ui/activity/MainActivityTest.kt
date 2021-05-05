package com.sahrial.submission_3_jetpackpro.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.sahrial.submission_3_jetpackpro.R
import com.sahrial.submission_3_jetpackpro.helper.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(IdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idlingResource)
    }
    @Test
    fun loadMovieandTV(){
        Espresso.onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(click()).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        //Masuk ke Nav Tv
        Espresso.onView(withId(R.id.nav_tv)).perform(click())
        Espresso.onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        //kembali ke Nav Movie
        Espresso.onView(withId(R.id.nav_movie)).perform(click())
    }
    @Test
    fun displayView(){
        Espresso.onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //Masuk ke nav TV
        Espresso.onView(withId(R.id.nav_tv)).perform(click())
        Espresso.onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //Cek teks pada tab sesuai atau tidak
        Espresso.onView(withId(R.id.nav_favorite)).perform(click())
        Espresso.onView(withText("MOVIE")).perform(click())
        Espresso.onView(withText("TV SHOW")).perform(click())
        //Kembali ke Nav Movie
        Espresso.onView(withId(R.id.nav_movie)).perform(click())
    }
    @Test
    fun insertUpdateFav(){
        Espresso.onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_fav)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.nav_tv)).perform(click())
        Espresso.onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_fav)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.nav_favorite)).perform(click())
        Espresso.onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        Espresso.onView(withId(R.id.fab_fav)).perform(click())
        Espresso.pressBack()

        Espresso.onView(withId(R.id.nav_favorite)).perform(click())
        Espresso.onView(withText("TV SHOW")).perform(click())
        Espresso.onView(withId(R.id.rv_fav_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Espresso.onView(withId(R.id.fab_fav)).perform(click())
        Espresso.pressBack()
    }
    @Test
    fun detailMovie(){
        Espresso.onView(withId(R.id.rv_movie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Espresso.onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Espresso.onView(withId(R.id.iv_backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fab_fav)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }
    @Test
    fun detailTv(){
        Espresso.onView(withId(R.id.nav_tv)).perform(click())
        Espresso.onView(withId(R.id.rv_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Espresso.onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Espresso.onView(withId(R.id.iv_backdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_detail_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_detail_date)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fab_fav)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

}