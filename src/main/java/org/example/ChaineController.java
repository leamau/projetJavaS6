package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.app.Chaine;
import org.app.Element;

public class ChaineController  implements Initializable {
    public TableView<Chaine> tableChaine;
    public TableColumn<Chaine,String> codeChaine;
    public TableColumn<Chaine,String> nomChaine;
    public TableColumn<Chaine,Integer> niveauAct;
    public TableColumn<Chaine, String> elementEntree;
    public TableColumn<Chaine, String> elementSortie;
    ObservableMap<Element,Integer> mapElementE = new ObservableMap<>() {
        @Override
        public void addListener(MapChangeListener<? super Element, ? super Integer> mapChangeListener) {

        }

        @Override
        public void removeListener(MapChangeListener<? super Element, ? super Integer> mapChangeListener) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Integer get(Object key) {
            return null;
        }

        @Override
        public Integer put(Element key, Integer value) {
            return null;
        }

        @Override
        public Integer remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Element, ? extends Integer> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Element> keySet() {
            return null;
        }

        @Override
        public Collection<Integer> values() {
            return null;
        }

        @Override
        public Set<Entry<Element, Integer>> entrySet() {
            return null;
        }

        @Override
        public void addListener(InvalidationListener invalidationListener) {

        }

        @Override
        public void removeListener(InvalidationListener invalidationListener) {

        }
    };
    ObservableMap<Element,Integer> mapElementS = new ObservableMap<>() {
        @Override
        public void addListener(MapChangeListener<? super Element, ? super Integer> mapChangeListener) {

        }

        @Override
        public void removeListener(MapChangeListener<? super Element, ? super Integer> mapChangeListener) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Integer get(Object key) {
            return null;
        }

        @Override
        public Integer put(Element key, Integer value) {
            return null;
        }

        @Override
        public Integer remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Element, ? extends Integer> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Element> keySet() {
            return null;
        }

        @Override
        public Collection<Integer> values() {
            return null;
        }

        @Override
        public Set<Entry<Element, Integer>> entrySet() {
            return null;
        }

        @Override
        public void addListener(InvalidationListener invalidationListener) {

        }

        @Override
        public void removeListener(InvalidationListener invalidationListener) {

        }
    };

    @FXML
    public void switchToStocks() throws IOException {
        App.setRoot("Stocks");
    }

    @FXML
    public void switchToAchats() throws IOException {
        App.setRoot("Achats");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomChaine.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        niveauAct.setCellValueFactory(cellData -> cellData.getValue().niveauActivationProperty().asObject());
        elementEntree.setCellValueFactory(cellData -> cellData.getValue().toStringElementsEnEntreeProperty());
        elementSortie.setCellValueFactory(cellData -> cellData.getValue().toStringElementsEnSortieProperty());
        codeChaine.setCellValueFactory(cellData -> cellData.getValue().codeCProperty());

        mapElementE.put(new Element("Etest","nom","g"),3);
        mapElementS.put(new Element("Etest2","nom2","g"),3);

        tableChaine.setItems(observableList);
    }

    ObservableList<Chaine> observableList = FXCollections.observableArrayList(
            new Chaine("C001")
            //,"nom",1,new SimpleMapProperty<>(mapElementE),new SimpleMapProperty<>(mapElementS)
    );

}