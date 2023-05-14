package de.limeonfire.yasnta;

import com.j256.ormlite.dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Objects;

public class MainController {

    private final Dao<Note, Integer> noteDao;
    @FXML
    public TextField textFieldTitle;
    @FXML
    public Button buttonDelete;
    @FXML
    private ListView<String> listViewNotes;
    private ObservableList<String> notes;
    private String selectedNote;

    public MainController(Dao<Note, Integer> noteDao) {
        this.noteDao = noteDao;
    }

    public void initialize() {
        notes = FXCollections.observableArrayList();

        listViewNotes.setItems(notes);

        listViewNotes.setOnMouseClicked(event -> {
            selectedNote = listViewNotes.getSelectionModel().getSelectedItem();
            if(selectedNote != null) {
                buttonDelete.setDisable(false);
            } else {
                buttonDelete.setDisable(true);
            }
        });
    }

    @FXML
    protected void onQuitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onAddButtonClick() throws SQLException {
        String note_title = textFieldTitle.getText().trim();
        if(!Objects.equals(note_title, "")) {
            Note note = new Note();
            note.setTitle(note_title);
            noteDao.create(note);
            notes.add(note_title);
            textFieldTitle.setText("");
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        if(selectedNote != null) {
            System.out.println(selectedNote);
            listViewNotes.getItems().remove(selectedNote);
            buttonDelete.setDisable(true);
        }
    }
}
