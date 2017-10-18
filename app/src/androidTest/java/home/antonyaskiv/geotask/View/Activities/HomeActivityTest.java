package home.antonyaskiv.geotask.View.Activities;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.GeneralLocation;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Swipe;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import home.antonyaskiv.geotask.R;
import home.antonyaskiv.geotask.View.Matchers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by AntonYaskiv on 01.10.2017.
 */
public class HomeActivityTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
            HomeActivity.class);

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void onClick() throws Exception {

        onView(withId(R.id.whence_edit_text)).perform(typeText("Gagarina 35"));
        onView(withId(R.id.search_of_button)).perform(click());
        onView(withId(R.id.list_of_3_elements)).check(matches(Matchers.withListSize(7)));
    }



    @Test
    public void searchWay() throws Exception {
        onView(withId(R.id.viewpager)).perform(swipeFromTopRightToTopLeft());
        onView(withId(R.id.where_edit_text)).perform(typeText("Gagarina 35"));
        onView(withId(R.id.search_of_button_where)).perform(click());
        onView(withId(R.id.list_of_3_elements_where)).check(matches(Matchers.withListSize(7)));
    }


    private static ViewAction swipeFromTopRightToTopLeft() {
        return new GeneralSwipeAction(Swipe.FAST, GeneralLocation.TOP_RIGHT,
                GeneralLocation.TOP_LEFT, Press.FINGER);
    }





}







