package com.it1901.booking.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableViewMaker {


    public static TableView makeTable(ResultSet rs, List<String> labels) {
        TableView tableView = new TableView();
        ObservableList<TableColumn> columnList = FXCollections.observableArrayList();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String columnName = meta.getColumnName(i);
                TableColumn tableColumn = new TableColumn(labels.get(i-1));
                tableColumn.setCellValueFactory(new PropertyValueFactory<List<String>, String>(columnName)); //se mer inn i denne
                columnList.add(tableColumn);
            }
            ObservableList<List<String>> data = FXCollections.observableArrayList();
            while(rs.next()){
                List<String> row = new ArrayList();
                for(int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tableView.setItems(data);

            System.out.println(tableView.getItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableView;
    }

}
