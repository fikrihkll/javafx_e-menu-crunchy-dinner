package ui.home;

import data.model.Food;

public interface OnItemAddedCart {
    void onItemAdded(Food food,int qty);
}
