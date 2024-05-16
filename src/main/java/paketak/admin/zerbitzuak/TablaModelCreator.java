package paketak.admin.zerbitzuak;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablaModelCreator{

    /**
     *  Funtzio honek pasatako objetuaren klasearen DefaultTableModel bat sortuko du, datuak emanda
     *  @param data objecto motako datua,
     *  @param tabla modifikatu nahi den tabla
     *  @return sortutako DefaultTableModel-a
     */
    public static TableView createTableModel(Class<?> data, TableView tabla) {
        List<Field> fields = new ArrayList<Field>();
        while (data != null) {
            fields.addAll(Arrays.asList(data.getDeclaredFields()));
            data = data.getSuperclass();
        }
        String[] columnNames = new String[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            columnNames[i] = fields.get(i).getName();
        }



        for (String columnName : columnNames) {
            TableColumn<Object, String> column = new TableColumn<>(columnName);
            tabla.getColumns().add(column);
        }
        return tabla;
    }

}
