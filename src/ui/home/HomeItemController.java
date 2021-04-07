package ui.home;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import data.model.Food;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeItemController implements Initializable {


    private OnItemAddedCart listener;


    public HomeItemController(OnItemAddedCart listener){
        this.listener = listener;
    }

    @FXML
    private AnchorPane item_pane;

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
    private JFXButton btn_add_cart;

    private int qty = 1;

    private Food food;

    public void setView(Food food){
        this.food=food;
        tv_name.setText(food.getName());
        tv_price.setText("Rp."+ food.getPrice());
        tv_qty.setText(Integer.toString(qty));

        Thread newThread = new Thread(() -> {
            iv.setImage(new Image(food.getPict()));
        });
        newThread.start();
    }

    private void onClick(){
        btn_add_cart.setOnMouseClicked(event -> {
            listener.onItemAdded(food,qty);
        });

        btn_inc.setOnMouseClicked(event ->{
            qty++;
            tv_qty.setText(Integer.toString(qty));
        });

        btn_dec.setOnMouseClicked(event ->{
            if(qty>1){
                qty--;
                tv_qty.setText(Integer.toString(qty));
            }
        });
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onClick();


    }
}
