package de.limeonfire.yasnta;

import com.j256.ormlite.dao.Dao;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController {

    protected final Dao<Note, Integer> noteDao;
    public BaseController() {
        this.noteDao = new ConnectDB().loadDB();
    }

}
