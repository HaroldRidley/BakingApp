package com.hridley.bakilicous.steps;

import android.net.Uri;
import android.text.TextUtils;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class RecipeStepViewModel implements RecipeStepViewModelInterface {
    public final RecipeStep recipeStep;

    @ParcelConstructor
    public RecipeStepViewModel(RecipeStep recipeStep) {
        this.recipeStep = recipeStep;
    }

    public String getDescription() {
        return recipeStep.description;
    }

    @Override
    public String getShortDescription() {
        return recipeStep.shortDescription;
    }

    @Override
    public Uri getVideoUri() {
        if (TextUtils.isEmpty(recipeStep.videoURL)) {
            return null;
        }

        return Uri.parse(recipeStep.videoURL);
    }

    @Override
    public Boolean hasVideo() {
        return !TextUtils.isEmpty(recipeStep.videoURL);
    }

    @Override
    public Boolean hasThumbnail() {
        return !TextUtils.isEmpty(recipeStep.thumbnailURL);
    }

    @Override
    public Uri getThumbnailUri() {
        if (TextUtils.isEmpty(recipeStep.thumbnailURL)) {
            return null;
        }

        return Uri.parse(recipeStep.thumbnailURL);
    }

    @Override
    public Uri getDefaultMediaPicture() {
        return Uri.parse("http://www.luxuryroomdecor.com/mediaimages/kitchen_table_top_view816758858.jpg");
    }
}
