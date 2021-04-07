package ui.cart;

import com.jfoenix.controls.JFXButton;
import data.model.Food;
import data.model.FoodCart;
import general.creation.StackController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import ui.home.HomeItemController;
import ui.struct.StructController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable,OnItemChanged {

    @FXML
    private GridPane grid_list_cart;
    
    @FXML
    private JFXButton btn_back_cart;

    @FXML
    private JFXButton btn_make_order;

    @FXML
    private Label tv_total;

    @FXML
    private AnchorPane cart_constraint;

    private int total;

    private List<FoodCart> cartItems = new ArrayList<>();
    private List<Integer> listCartId = new ArrayList<>();

    private double x,y = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onClick();
        prepareList();
        countTotal();

        onWindowDragged();
    }

    public void setParams(List<FoodCart> cartItems){
        this.cartItems = cartItems;
    }

    private void onWindowDragged(){

        cart_constraint.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        cart_constraint.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    private void onClick(){
        btn_back_cart.setOnMouseClicked(event->{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            try {
                StackController.removeCurrentFromStack();
                stage.setScene(StackController.getLastScene());

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btn_make_order.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to make this order? ", ButtonType.YES,ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                if(cartItems.size()>0){

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();


                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getClassLoader().getResource("ui/struct/struct.fxml"));

                        StructController controller = new StructController();
                        controller.setParams(cartItems,total);
                        cartItems.clear();
                        loader.setController(controller);


                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        StackController.removeStackAt(StackController.stack.size()-1);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    private void prepareList(){

        int column = 1;
        int row = 1;

        try{
            for(int i =0 ; i < cartItems.size(); i ++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("ui/cart/cart_item.fxml"));
                CartItemController itemController = new CartItemController(this);
                fxmlLoader.setController(itemController);
                AnchorPane anchorPane = fxmlLoader.load();

                itemController.setView(cartItems.get(i));
                listCartId.add(cartItems.get(i).getCartId());

                if(column==5){
                    column=1;
                    row++;
                }

                grid_list_cart.add(anchorPane,column++,row);

                grid_list_cart.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_list_cart.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_list_cart.setMaxWidth(Region.USE_PREF_SIZE);

                grid_list_cart.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_list_cart.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_list_cart.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onItemRemoved(int cartId) {
        int pos = listCartId.indexOf(cartId);
        cartItems.remove(pos);
        listCartId.remove(pos);
        Node node = grid_list_cart.getChildren().get(pos);
        grid_list_cart.getChildren().remove(node);
        countTotal();
    }

    @Override
    public void onQtyChanged(int newQty,int cartId) {
        int pos = listCartId.indexOf(cartId);
        cartItems.get(pos).setQty(newQty);
        countTotal();
    }

    private void countTotal(){
        int value = 0;

        for(int i = 0 ; i < cartItems.size() ; i++){
            value+= cartItems.get(i).getPrice() * cartItems.get(i).getQty();
        }
        total=value;
        tv_total.setText("Rp."+value);

    }
}
