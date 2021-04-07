package ui.cart;

import com.jfoenix.controls.JFXButton;
import data.model.Food;
import data.model.FoodCart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartItemController implements Initializable {

    OnItemChanged listener;

    public CartItemController(OnItemChanged listener){
        this.listener = listener;
    }

    @FXML
    private ImageView iv;

    @FXML
    private Label tv_name;

    @FXML
    private Label tv_price;

    @FXML
    private JFXButton btn_dec;

    @FXML
    private Label tv_qty;

    @FXML
    private JFXButton btn_inc;

    @FXML
    private JFXButton btn_remove_cart;

    private FoodCart food;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onClick();
    }

    private void onClick(){
        btn_remove_cart.setOnMouseClicked(event -> {
            listener.onItemRemoved(food.getCartId());
        });

        btn_inc.setOnMouseClicked(event ->{
            int qty = food.getQty();
            qty++;
            food.setQty(qty);
            tv_qty.setText(Integer.toString(qty));
            listener.onQtyChanged(qty,food.getCartId());
        });

        btn_dec.setOnMouseClicked(event ->{
            if(food.getQty()>1){
                int qty = food.getQty();
                qty--;
                food.setQty(qty);
                tv_qty.setText(Integer.toString(qty));
                listener.onQtyChanged(qty,food.getCartId());
            }
        });
    }

    public void setView(FoodCart food){
        this.food = food;

        food.getName();
        tv_qty.setText(Integer.toString(food.getQty()));
        tv_name.setText(food.getName());
        tv_price.setText("Rp."+ food.getPrice());
        iv.setImage(new Image(food.getPict()));
    }
}
