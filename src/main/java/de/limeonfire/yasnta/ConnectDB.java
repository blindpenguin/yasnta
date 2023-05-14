package de.limeonfire.yasnta;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class ConnectDB {
    public Dao<Note, Integer> loadDB() {
        Dao<Note, Integer> noteDao;
        try {
            JdbcConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:data/yasnta.db");
            noteDao = DaoManager.createDao(connectionSource, Note.class);
            TableUtils.createTableIfNotExists(connectionSource, Note.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return noteDao;
    }
}
