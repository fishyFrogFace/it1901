package com.it1901.booking.Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.List;


public class TableViewMaker {

    public static TableView makeTable(ResultSet rs, List<String> labels) {
        TableView tableView = new TableView();
        tableView.setEditable(false);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        try {
            //Add table columns dynamically
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i; // used in string property down below
                TableColumn col = new TableColumn(labels.get(i)); //sets column label
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    } //magic to make javafx behave properly
                });
                tableView.getColumns().addAll(col);
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
        return tableView;
    }

}
