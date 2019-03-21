package com.hridley.bakilicous.recipes;

import android.net.Uri;
import android.text.TextUtils;

import com.hridley.bakilicous.steps.RecipeStep;
import com.hridley.bakilicous.steps.RecipeStepViewModel;
import com.hridley.bakilicous.steps.RecipeStepViewModelInterface;
import com.hridley.bakilicous.ingredients.Ingredient;
import com.hridley.bakilicous.ingredients.IngredientViewModel;
import com.hridley.bakilicous.ingredients.IngredientViewModelInterface;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.ArrayList;

@Parcel
public class RecipeViewModel implements RecipeViewModelInterface {
    public Recipe recipe;
    public ArrayList<IngredientViewModel> ingredients;
    public ArrayList<RecipeStepViewModel> steps;

    @ParcelConstructor
    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;

        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();

        for (Ingredient ingredient: recipe.ingredients) {
            this.ingredients.add(new IngredientViewModel(ingredient));
        }

        for (RecipeStep step: recipe.steps) {
            this.steps.add(new RecipeStepViewModel(step));
        }
    }

    public String getName() {
        return this.recipe.name;
    }

    public String getRecipeCountText() {
        return steps.size() + " Steps";
    }

    public Boolean hasImage() {
        return !TextUtils.isEmpty(recipe.imageURL);
    }

    public Uri getImageUri() {
        if (TextUtils.isEmpty(recipe.imageURL)) {
            return null;
        }

        return Uri.parse(recipe.imageURL);
    }

    public ArrayList<? extends IngredientViewModelInterface> getIngredients() {
        return this.ingredients;
    }

    public ArrayList<? extends RecipeStepViewModelInterface> getSteps() {
        return this.steps;
    }
}
