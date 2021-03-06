package com.it1901.booking.Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.table.TableFilter;

import java.sql.ResultSet;
import java.util.List;


public class TableViewMaker {

    public static TableView makeTable(ResultSet rs, List<String> labels) {
        TableView<ObservableList> tableView = new TableView<>();
        tableView.setEditable(false);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {
            //Add table columns dynamically
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i; // used in string property down below
                TableColumn<ObservableList, String> col = new TableColumn<>(labels.get(i)); //sets column label
                col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                tableView.getColumns().add(col);
            }
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

        TableFilter filter = new TableFilter<>(tableView);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        return tableView;
    }

}
