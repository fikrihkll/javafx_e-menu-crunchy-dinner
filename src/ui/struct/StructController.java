package ui.struct;

import com.jfoenix.controls.JFXButton;
import data.model.FoodCart;
import data.model.StructItem;
import general.creation.StackController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class StructController implements Initializable {
    @FXML
    private AnchorPane anchor_struct;

    @FXML
    private JFXButton tv_new_order;

    @FXML
    private ImageView btn_new_order;

    @FXML
    private Label tv_id;

    @FXML
    private Label tv_date;

    @FXML
    private Label tv_total_struct;

    @FXML
    private ScrollPane scroll_struct;

    @FXML
    private ListView<StructItem> list_item_struct;


    public StructController() {
        listItems = FXCollections.observableArrayList();
    }

    private List<FoodCart> cartItems = new ArrayList<>();
    ObservableList<StructItem> listItems ;

    int total=0;

    double x,y = 0;

    public void setParams(List<FoodCart> newList, int total){
        cartItems.addAll(newList);
        for(int i =0 ; i < cartItems.size();i++){
            listItems.add(new StructItem(cartItems.get(i).getName(),cartItems.get(i).getPrice(),cartItems.get(i).getQty()));
        }
        this.total = total;
    }

    private void onWindowDragged(){

        anchor_struct.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        anchor_struct.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    private void onClick(){
        btn_new_order.setOnMouseClicked(event ->{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            try {
                stage.setScene(StackController.getLastScene());

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        tv_new_order.setOnMouseClicked(event ->{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            try {
                stage.setScene(StackController.getLastScene());

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list_item_struct.setItems(listItems);
        list_item_struct.setCellFactory(listItem -> new StructItemController());

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
        tv_date.setText(format.format(cal.getTime()));

        Random rand = new Random();

        int randId = rand.nextInt(20000);
        tv_id.setText("ID "+Integer.toString(randId));


        onClick();
        onWindowDragged();

        tv_total_struct.setText("Rp."+total);

    }
}
