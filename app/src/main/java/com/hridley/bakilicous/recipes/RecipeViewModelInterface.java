package com.hridley.bakilicous.recipes;

import android.net.Uri;

import com.hridley.bakilicous.steps.RecipeStepViewModelInterface;
import com.hridley.bakilicous.ingredients.IngredientViewModelInterface;

import java.util.ArrayList;

public interface RecipeViewModelInterface {
    String getName();
    String getRecipeCountText();
    Boolean hasImage();
    Uri getImageUri();
    ArrayList<? extends IngredientViewModelInterface> getIngredients();
    ArrayList<? extends RecipeStepViewModelInterface> getSteps();
}
