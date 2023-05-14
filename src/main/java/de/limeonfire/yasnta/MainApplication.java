package de.limeonfire.yasnta;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainApplication extends Application {
    private Dao<Note, Integer> noteDao;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:"+ MainApplication.class.getResource("yasnta.db"));
            noteDao = DaoManager.createDao(connectionSource, Note.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        MainController mainController = new MainController(noteDao);
        fxmlLoader.setController(mainController);
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Yet Another Shitty Note Taking App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}