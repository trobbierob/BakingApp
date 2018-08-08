package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    private String recipeName;
    private String quantity;
    private String measure;
    private String ingredients;
    private String stepId;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
    private String servingSize;

    public Recipe(String recipeName) {
        this.recipeName = recipeName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.recipeName);
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredients);
        dest.writeString(this.stepId);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoUrl);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.servingSize);
    }

    protected Recipe(Parcel in) {
        this.recipeName = in.readString();
        this.quantity = in.readString();
        this.measure = in.readString();
        this.ingredients = in.readString();
        this.stepId = in.readString();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoUrl = in.readString();
        this.thumbnailUrl = in.readString();
        this.servingSize = in.readString();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
