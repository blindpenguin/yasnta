package de.limeonfire.yasnta;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="notes")
public class Note {

    @DatabaseField(columnName = "note_id", generatedId = true)
    private Integer noteId;

    @DatabaseField(canBeNull = false)
    private String title;

    public Note() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
