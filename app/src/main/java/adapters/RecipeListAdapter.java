package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailActivity;
import com.example.android.bakingapp.RecipeDetailFragment;

import java.util.ArrayList;

import model.Recipe;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    public static final String TAG = "**RECIPE ADAPTER LOG**";
    public static final String RECIPE_ITEM_KEY = "recipe_item_key";
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mRecipeData;

    private Context mContext;
    private boolean mTablet;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipeData, boolean tablet) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mRecipeData = recipeData;
        this.mTablet = tablet;
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.main_recipe_item, viewGroup, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder recipeViewHolder, int position) {
        Recipe currentRecipe = mRecipeData.get(position);
        recipeViewHolder.recipeNameTV.setText(currentRecipe.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipeData.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recipeNameTV;
        RecipeListAdapter mAdapter;

        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeNameTV = itemView.findViewById(R.id.recipe_name_tv);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();

            /*Intent stepActivityIntent = new Intent(view.getContext(), RecipeStepActivity.class);
            stepActivityIntent.putExtra("current_recipe", mRecipeData.get(mPosition));
            view.getContext().startActivity(stepActivityIntent);*/

            if (mTablet){
                RecipeDetailFragment fragment =
                        RecipeDetailFragment.newInstance(mRecipeData.get(mPosition));
                ((MainActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_detail_container, fragment)
                        .addToBackStack(null)
                        .commit();

            } else {
                Intent detailActivityIntent = new Intent(view.getContext(), RecipeDetailActivity.class);
                detailActivityIntent.putExtra("current_recipe", mRecipeData.get(mPosition));
                view.getContext().startActivity(detailActivityIntent);
            }
        }
    }
}
