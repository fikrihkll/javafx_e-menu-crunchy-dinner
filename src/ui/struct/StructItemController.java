package ui.struct;

import data.model.StructItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class StructItemController extends ListCell<StructItem> {

    @FXML
    private AnchorPane anchor_struct_item;

    @FXML
    private Label tv_price;

    @FXML
    private Label tv_name;
    @FXML
    private Label tv_qty_struct;

    private FXMLLoader mLoadder;

    @Override
    protected void updateItem(StructItem item, boolean empty) {
        super.updateItem(item, empty);

        if(item!=null && !empty){

            if(mLoadder == null){
                mLoadder = new FXMLLoader(getClass().getClassLoader().getResource("ui/struct/struct_item.fxml"));
                mLoadder.setController(this);

                try {
                    mLoadder.load();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            tv_name.setText(item.getName());
            tv_price.setText("Rp."+item.getPrice());
            tv_qty_struct.setText(Integer.toString(item.getQty()));

            setText(null);
            setGraphic(anchor_struct_item);

        }

    }
}
