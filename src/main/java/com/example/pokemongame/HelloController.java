package com.example.pokemongame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController<Private> implements Initializable {

    @FXML
    public AnchorPane actualizatodo;

    @FXML
    private GridPane escenarioPokemons;

    @FXML
    private Label textoelegir;

    @FXML
    public Label textosalir;

    @FXML
    public Button bBatalla;

    @FXML
    void actualizar(MouseEvent event) {

    }


    @FXML
    void iniciarBatalla(MouseEvent event) {


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Escenario.fxml"));
            EscenarioController escenarioController = fxmlLoader.getController();

            Scene scene = new Scene(fxmlLoader.load(), 900, 550);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e);

        }

    }




    ArrayList<ModelController> listaController = new ArrayList<>();

    Random r=new Random();
    public List<Pokemons> pokemons = new ArrayList<>();

    public List<Pokemons> getData() {
        List<Pokemons> pokemons = new ArrayList<>();
        List<Pokemons> listaPokemons=new ArrayList<>();
        Pokemons p1=new Pokemons("Miutu",126,"src\\main\\java\\com\\example\\pokemongame\\img\\mewtwo.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",200,200,true);
        Pokemons p2=new Pokemons("Aggron",173,"src\\main\\java\\com\\example\\pokemongame\\img\\aggron-mega.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",180,180,false);
        Pokemons p3=new Pokemons("Arceus",128,"src\\main\\java\\com\\example\\pokemongame\\img\\arceus.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",190,190,false);
        Pokemons p4=new Pokemons("dialga",165,"src\\main\\java\\com\\example\\pokemongame\\img\\dialga.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",160,160,false);
        Pokemons p5=new Pokemons("Dragonite",140,"src\\main\\java\\com\\example\\pokemongame\\img\\dragonite.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",250,250,false);
        Pokemons p6=new Pokemons("Gyarados",142,"src\\main\\java\\com\\example\\pokemongame\\img\\gyarados-f.gif","src\\main\\java\\com\\example\\pokemongame\\img\\male.png","src\\main\\java\\com\\example\\pokemongame\\img\\ps.png",220,220,false);
        listaPokemons.add(p1);
        listaPokemons.add(p2);
        listaPokemons.add(p3);
        listaPokemons.add(p4);
        listaPokemons.add(p5);
        listaPokemons.add(p6);


        Pokemons pokemon;

        for (int i = 0; i < 6; i++) {
            pokemon = new Pokemons();
            pokemon.setNombrepokemon(listaPokemons.get(i).getNombrepokemon());
            pokemon.setNivelpokemon(listaPokemons.get(i).getNivelpokemon());
            File f = new File(listaPokemons.get(i).getImgenpokemon());
            pokemon.setImgenpokemon(f.toURI().toString());

            File f1 = new File("src\\main\\java\\com\\example\\pokemongame\\img\\male.png");
            pokemon.setImagengenero(f1.toURI().toString());

            File f2 = new File("src\\main\\java\\com\\example\\pokemongame\\img\\ps.png");
            pokemon.setImagenps(f2.toURI().toString());

            pokemon.setVidaMaxima(listaPokemons.get(i).getVidaMaxima());
            pokemon.setVidaActual(listaPokemons.get(i).getVidaActual());

            pokemon.seleccionado=listaPokemons.get(i).seleccionado;
            pokemons.add(pokemon);

        }
        return pokemons;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pokemons.addAll(getData());

        int columnas=0;
        int filas=0;

        try {
        for(int i=0;i<pokemons.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("modelo.fxml"));

            AnchorPane anchorPane = fxmlLoader.load();

            ModelController modelController = fxmlLoader.getController();
            modelController.setData(pokemons.get(i), this);
            listaController.add(modelController);

            escenarioPokemons.add(anchorPane,columnas++,filas);
            if(columnas==3){
                columnas=0;
                filas++;
            }


        }

        } catch (IOException e) {
            e.printStackTrace();
        }

        bBatalla.setVisible(false);
    }

    public void pokemonSeleccionado() {

        for(ModelController controller: listaController) {
            controller.pokemonNoSeleccionado();
        }

    }

}