/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.api;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Pair;
import tn.esprit.entity.Abonnement;
import tn.esprit.services.AbnService;
/**
 *
 * @author 21626
 */
public class StatsAPI extends Application  {
AbnService as = new AbnService();
private long count(int id){
   
List<Abonnement> abonnements = as.getAll();
return abonnements.stream()
                 .filter(Abonnement -> Abonnement.getIdtypeA() == id)
                 .count();
}
//@Overwrite
public void start(Stage stage) {

//List<Abonnement> abonnements = as.getAll();





// Creating a list of subscriptions
List<Pair<String, Long>> subs = new ArrayList<>(Arrays.asList(
        new Pair<>("Mensuel",count(1)),
        new Pair<>("Semestriel",count(2)),
        new Pair<>("Annuel",count(3))
));



// Creating the x-axis and y-axis
CategoryAxis xAxis = new CategoryAxis();
NumberAxis yAxis = new NumberAxis();

// Creating the bar chart
BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
xAxis.setLabel("Plan");
yAxis.setLabel("Abonnées");
barChart.setTitle("Popularité par plan");


   // Adding data to the chart
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        subs.forEach(sub -> series.getData().add(new XYChart.Data<>(sub.getKey(), sub.getValue())));
        barChart.getData().add(series);
  // Displaying the chart
        Scene scene = new Scene(barChart, 800, 600);
       // Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
 }
    public static void main(String[] args) {
        launch(args);
    }
}
