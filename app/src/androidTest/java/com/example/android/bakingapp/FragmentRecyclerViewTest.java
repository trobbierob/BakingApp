package com.example.android.bakingapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FragmentRecyclerViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * This test is to check the values of the list of recipes in the RecyclerView
     */
    @Test
    public void fragmentRecyclerViewTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button), withText("Add Recipes"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.recipe_name_tv), withText("Nutella Pie"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recipe_fragment_rv),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Nutella Pie")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.recipe_name_tv), withText("Brownies"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recipe_fragment_rv),
                                        1),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Brownies")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.recipe_name_tv), withText("Yellow Cake"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recipe_fragment_rv),
                                        2),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Yellow Cake")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.recipe_name_tv), withText("Cheesecake"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recipe_fragment_rv),
                                        3),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Cheesecake")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}