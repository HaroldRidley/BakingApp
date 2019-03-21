package com.hridley.bakilicous.recipes;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hridley.bakilicous.ItemOffsetDecoration;
import com.hridley.bakilicous.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.RecyclerView.*;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class RecipeListFragment extends Fragment implements RecipeRecyclerViewAdapter.OnClickHandler {
    @BindInt(R.integer.recipeGridSpanCount)
    int spanCount;

    @BindView(R.id.recipeRecyclerView)
    RecyclerView recipeRecyclerView;

    @BindView(R.id.recipeListProgressBar)
    ProgressBar recipeListProgressBar;

    RecipeAPI api;
    ArrayList<RecipeViewModelInterface> recipes;

    public RecipeListFragment() {
        api = new RecipeAPI();
    }

    @Nullable
    @Override
    public View onCreateView(
        LayoutInflater inflater,
        @Nullable ViewGroup container,
        Bundle savedInstanceState
    ) {
        final View rootView = inflater.inflate(R.layout.recipe_list_fragment, container, false);
        ButterKnife.bind(this, rootView);

        final RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter();
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);

        recipeRecyclerView.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.collectionItemMargin));

        adapter.setOnClickHandler(this);
        adapter.setRecipes(new ArrayList<RecipeViewModelInterface>());
        recipeRecyclerView.setAdapter(adapter);
        recipeRecyclerView.setLayoutManager(layoutManager);

        api.recipes().enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                recipes = new ArrayList<RecipeViewModelInterface>();

                for (Recipe recipe: response.body()) {
                    recipes.add(new RecipeViewModel(recipe));
                }

                adapter.setRecipes(recipes);
                recipeListProgressBar.setVisibility(INVISIBLE);
                recipeRecyclerView.setVisibility(VISIBLE);
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<Recipe>> call, Throwable t) {
                Log.e(getClass().toString(), t.getMessage());
            }
        });

        return rootView;
    }

    @Override
    public void onRecipeClicked(int clickedIndex, RecipeViewModelInterface viewModel) {
        Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
        intent.putExtra(Intent.EXTRA_INTENT, Parcels.wrap(recipes));
        intent.putExtra(Intent.EXTRA_INDEX, clickedIndex);

        startActivity(intent);
    }
}
