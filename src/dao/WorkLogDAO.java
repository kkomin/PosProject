package dao;

import java.sql.Connection;

public class WorkLogDAO {
    private Connection connection;

    public WorkLogDAO(Connection connectiobn) {
        this.connection = connectiobn;
    }
}
