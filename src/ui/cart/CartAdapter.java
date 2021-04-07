//package ui.cart;
//
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CartAdapter {
//
//    GridPane grid;
//
//    int column = 1;
//    int row = 1;
//    int maxColumn;
//
//    List<?> listData = new ArrayList<>();
//
//    public CartAdapter(GridPane grid,int maxColumn){
//        this.grid = grid;
//        this.maxColumn = maxColumn+1;
//    }
//
//    class ViewHolder{
//
//    }
//
//    public void build(){
//        for(int i =0 ; i < cartItems.size(); i ++){
//
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getClassLoader().getResource("ui/cart/cart_item.fxml"));
//            CartItemController itemController = new CartItemController(this);
//            fxmlLoader.setController(itemController);
//            AnchorPane anchorPane = fxmlLoader.load();
//
//            itemController.position=i;
//            itemController.setView(cartItems.get(i));
//
//            if(column==5){
//                column=1;
//                row++;
//            }
//
//            grid.add(anchorPane,column++,row);
//
//
//
//            GridPane.setMargin(anchorPane,new Insets(10));
//        }
//    }
//}
