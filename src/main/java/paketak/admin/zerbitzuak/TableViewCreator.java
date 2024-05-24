package paketak.admin.zerbitzuak;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class TableViewCreator {

    /**
     * Metodo honek TableView bat Modifikatzen du, honen zutabeak klasearen atributuekin lotuz.
     * @param klasea Tablan erabili nahi den klassea
     * @param basea Basetzat artukoden TableView-a
     */
    public static void createTableView(Class klasea, TableView basea){
        Field[] fields = klasea.getDeclaredFields();

        for(int i = 0; i < basea.getColumns().size(); i++){
            TableColumn column = (TableColumn) basea.getColumns().get(i);

            Field field = fields[i];
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
        }
    }
}
