package com.hridley.bakilicous;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hridley.bakilicous.ingredients.Ingredient;
import com.hridley.bakilicous.recipes.Recipe;
import com.hridley.bakilicous.recipes.RecipeDetailActivity;
import com.hridley.bakilicous.recipes.RecipeViewModel;
import com.hridley.bakilicous.steps.RecipeStep;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityTest {
    @Rule
    public ActivityTestRule<RecipeDetailActivity> activityRule = new ActivityTestRule<>(
        RecipeDetailActivity.class,
        true,
        false // Lazily create activity
    );

    @Before
    public void setup() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Sugar", 10, "Gram"));

        Recipe recipe = new Recipe(1, "Chocolate Cake", ingredients, new ArrayList<RecipeStep>(),"");

        ArrayList<RecipeViewModel> recipes = new ArrayList<>();
        recipes.add(new RecipeViewModel(recipe));

        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_INDEX, 0);
        intent.putExtra(Intent.EXTRA_INTENT, Parcels.wrap(recipes));

        activityRule.launchActivity(intent);
    }

    @Test
    public void checkIngredients() {
        onView(withId(R.id.recipeDetailScrollView))
            .check(matches(isDisplayed()));

        onView(withId(R.id.ingredientListHeader))
            .check(matches(withText("Ingredients")));
    }
}

