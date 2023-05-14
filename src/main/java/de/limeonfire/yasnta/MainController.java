package de.limeonfire.yasnta;

import com.j256.ormlite.dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
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

    public MainController() {
        this.noteDao = new ConnectDB().loadDB();
    }

    public void initialize() throws SQLException {
        notes = FXCollections.observableArrayList();

        List<Note> db_notes = this.noteDao.queryForAll();

        for(Note note : db_notes) {
            notes.add(note.getTitle());
        }

        listViewNotes.setItems(notes);

        listViewNotes.setOnMouseClicked(event -> {
            selectedNote = listViewNotes.getSelectionModel().getSelectedItem();
            // This is a simplified if-else according to IntelliJ.
            buttonDelete.setDisable(selectedNote == null);
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
    protected void onDeleteButtonClick() throws SQLException {
        if(selectedNote != null) {
            List<Note> db_note = this.noteDao.queryForEq("title", selectedNote);
            this.noteDao.deleteById(db_note.get(0).getNoteId());
            listViewNotes.getItems().remove(selectedNote);
            buttonDelete.setDisable(true);
        }
    }
}
