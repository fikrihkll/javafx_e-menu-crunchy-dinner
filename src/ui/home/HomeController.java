package ui.home;

import com.jfoenix.controls.JFXButton;
import data.model.Food;
import data.model.FoodCart;
import general.creation.StackController;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.cart.CartController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable,OnItemAddedCart {


    @FXML
    private ImageView btn_exit_home;

    @FXML
    private ScrollPane scroll_home;

    @FXML
    private GridPane grid_home;

    @FXML
    private JFXButton btn_cart;

    @FXML
    private JFXButton btn_menu;

    @FXML
    private AnchorPane side_menu;

    @FXML
    private JFXButton btn_best_selling;

    @FXML
    private JFXButton btn_main_dish;

    @FXML
    private JFXButton btn_foods;

    @FXML
    private JFXButton btn_drinks;

    private List<Food> foods = new ArrayList<>();

    private boolean isExpanded = true;

    private List<FoodCart> cartItems = new ArrayList<>();

    private int rowAmount = 4;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onClick();
        prepareList(getBestSelling());
        changeSelected("BEST_SELLING");
    }

    private void prepareList(List<Food> newFoods){
        grid_home.getChildren().clear();
        foods.clear();
        foods.addAll(newFoods);

        int column = 1;
        int row = 1;

        try{
            for(int i =0 ; i < foods.size(); i ++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getClassLoader().getResource("ui/home/home_item.fxml"));
                HomeItemController itemController = new HomeItemController(this);
                fxmlLoader.setController(itemController);
                AnchorPane anchorPane = fxmlLoader.load();


                itemController.setView(foods.get(i));

                if(column==rowAmount){
                    column=1;
                    row++;
                }

                grid_home.add(anchorPane,column++,row);

                grid_home.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_home.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_home.setMaxWidth(Region.USE_PREF_SIZE);

                grid_home.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_home.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_home.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void changeSelected(String category){
        switch(category){
            case "BEST_SELLING" :
                btn_best_selling.setStyle("-fx-background-color: #fee7d6;\n" +
                        "    -fx-border-color: #172449;\n" +
                        "    -fx-border-width: 0px 0px 0px 2px;");
                btn_main_dish.setStyle("");
                btn_foods.setStyle("");
                btn_drinks.setStyle("");
                break;


            case "MAIN_DISH":
                btn_best_selling.setStyle("");
                btn_main_dish.setStyle("-fx-background-color: #fee7d6;\n" +
                        "    -fx-border-color: #172449;\n" +
                        "    -fx-border-width: 0px 0px 0px 2px;");
                btn_foods.setStyle("");
                btn_drinks.setStyle("");
                break;
            case "FOODS":
                btn_best_selling.setStyle("");
                btn_main_dish.setStyle("");
                btn_foods.setStyle("-fx-background-color: #fee7d6;\n" +
                        "    -fx-border-color: #172449;\n" +
                        "    -fx-border-width: 0px 0px 0px 2px;");
                btn_drinks.setStyle("");
                break;
            case "DRINKS":
                btn_best_selling.setStyle("");
                btn_main_dish.setStyle("");
                btn_foods.setStyle("");
                btn_drinks.setStyle("-fx-background-color: #fee7d6;\n" +
                        "    -fx-border-color: #172449;\n" +
                        "    -fx-border-width: 0px 0px 0px 2px;");
                break;
        }
    }

    private void onClick(){

        btn_best_selling.setOnMouseClicked(event ->{
            prepareList(getBestSelling());
            changeSelected("BEST_SELLING");
        });

        btn_main_dish.setOnMouseClicked(event ->{
            prepareList(getListMainDish());
            changeSelected("MAIN_DISH");
        });

        btn_foods.setOnMouseClicked(event ->{
            prepareList(getListFood());
            changeSelected("FOODS");
        });

        btn_drinks.setOnMouseClicked(event ->{
            prepareList(getListDrink());
            changeSelected("DRINKS");
        });

        btn_exit_home.setOnMouseClicked(event -> {
            System.exit(0);

        });


        btn_menu.setOnMouseClicked(event -> {
            if(isExpanded){
                TranslateTransition slide= new TranslateTransition();
                slide.setDuration(Duration.seconds(0.3));
                slide.setNode(side_menu);

                slide.setToX(-205);
                slide.play();

                side_menu.setTranslateX(-205);
                slide.setOnFinished(event1 -> {
                    isExpanded = false;
                });
            }else{
                TranslateTransition slide= new TranslateTransition();
                slide.setDuration(Duration.seconds(0.4));
                slide.setNode(side_menu);

                slide.setToX(0);
                slide.play();

                side_menu.setTranslateX(0);
                slide.setOnFinished(event1 -> {
                    isExpanded = true;
                });
            }
        });

        btn_cart.setOnMouseClicked(event ->{

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();


            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("ui/cart/cart.fxml"));

                CartController controller = new CartController();
                controller.setParams(cartItems);
                loader.setController(controller);


                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);

                StackController.addToStack(scene);

            }catch (Exception e){
                e.printStackTrace();
            }


        });

    }

    private List<Food> getBestSelling(){
        List<Food> data = new ArrayList<>();

        data.add(new Food(1234,"Double Cheese Burger",29000,"https://nos.jkt-1.neo.id/mcdonalds/foods/October2019/apZ1DxDmKvwS2lV12Elp.png"));
        data.add(new Food(374,"Frightened CoffeJelly",28000,"https://nos.jkt-1.neo.id/mcdonalds/foods/December2019/662lUgvaeMgXhgTnDqDS.png"));
        data.add(new Food(4126,"Fanta CruLoat", 10000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/4ANlG5rmRU06y7H2hgos.png"));
        data.add(new Food(512,"Coca Cola CruLoat", 10000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/bK5V4bpLZVcgQEgogv3c.png"));
        data.add(new Food(22,"Crunashy 2",19000,"https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/Lpv9lCHnzdtnFx1ZbGrj.png"));
        data.add(new Food(412,"Crunchy Uduk", 20000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2020/h3cZg2TVvmjkBHJYRpfw.png"));
        data.add(new Food(6342,"Crunashy with Egg", 18000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/H0YCaF0JIpZX2Jwzzx5O.png"));

        return data;
    }

    private List<Food> getListFood(){
        List<Food> data = new ArrayList<>();

        data.add(new Food(1234,"Double Cheese Burger",29000,"https://nos.jkt-1.neo.id/mcdonalds/foods/October2019/apZ1DxDmKvwS2lV12Elp.png"));
        data.add(new Food(4232,"Mini Spicy Crunchy", 30000, "https://nos.jkt-1.neo.id/mcdonalds/foods/February2021/uh4m8aQkykSupuQsGGVv.png"));
        data.add(new Food(531,"Crunchy Original", 13000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/Lff4QebQnB9aToyb5wtJ.png"));
        data.add(new Food(5123,"CrunchNuggets", 10000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/qFoLKbSe1R3OJ75zAm4B.png"));


        return data;
    }

    private List<Food> getListDrink(){
        List<Food> data = new ArrayList<>();

        data.add(new Food(374,"Frightened CoffeJelly",28000,"https://nos.jkt-1.neo.id/mcdonalds/foods/December2019/662lUgvaeMgXhgTnDqDS.png"));
        data.add(new Food(9230,"Coca Cola", 6000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/D8LeTajQpFSuUbAsugUq.png"));
        data.add(new Food(823,"Fanta", 6000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/4kZxwImpnHUrHGb4KDL5.png"));
        data.add(new Food(4126,"Fanta CruLoat", 10000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/4ANlG5rmRU06y7H2hgos.png"));
        data.add(new Food(512,"Coca Cola CruLoat", 10000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/bK5V4bpLZVcgQEgogv3c.png"));
        data.add(new Food(7122,"Teh Botol", 5000, "https://nos.jkt-1.neo.id/mcdonalds/foods/September2019/m9oUXe2Tuk0JUs2Zd5wP.png"));

        return data;
    }

    private List<Food> getListMainDish(){

        List<Food> data = new ArrayList<>();

        //MAIN DISH
        data.add(new Food(22,"Crunashy 2",19000,"https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/Lpv9lCHnzdtnFx1ZbGrj.png"));
        data.add(new Food(412,"Crunchy Uduk", 20000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2020/h3cZg2TVvmjkBHJYRpfw.png"));
        data.add(new Food(6342,"Crunashy with Egg", 18000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/H0YCaF0JIpZX2Jwzzx5O.png"));
        data.add(new Food(238,"Crunchicken with Fries", 18000, "https://nos.jkt-1.neo.id/mcdonalds/foods/August2019/dBkLAEZ7PxkjdgarPMbz.png"));


        return data;
    }

    @Override
    public void onItemAdded(Food food,int qty) {
        boolean isSimilar = false;

        for(int i =0 ; i < cartItems.size() ; i ++){
            if(cartItems.get(i).getId() == food.getId()){
                isSimilar=true;
            }
        }

       if(!isSimilar){
           cartItems.add(new FoodCart(food.getId(),food.getName(),food.getPrice(),food.getPict(),qty));
       }
    }
}
