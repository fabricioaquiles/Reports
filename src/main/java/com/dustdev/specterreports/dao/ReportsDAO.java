package com.dustdev.specterreports.dao;

import com.dustdev.specterreports.dao.adapter.ReportsAdapter;
import com.dustdev.specterreports.model.ReportsModel;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ReportsDAO {

    private final String table = "SpecterReports";

    private final SQLExecutor sqlExecutor;

    public void createTable() {
        sqlExecutor.updateQuery("CREATE TABLE IF NOT EXISTS " + table + "(" +
                "id INT AUTO_INCREMENT PRIMARY key," +
                "player VARCHAR(16)," +
                "motivo TEXT," +
                "autor VARCHAR(16)," +
                "data LONG" +
                ");"
        );
    }

    public ReportsModel selectOne(String player) {
        return sqlExecutor.resultOneQuery(
                "SELECT * FROM " + table + " WHERE player = ?",
                statement -> statement.set(1, player),
                ReportsAdapter.class
        );
    }

    public Set<ReportsModel> selectAll() {
        return sqlExecutor.resultManyQuery(
                "SELECT * FROM " + table + "",
                statement -> {},
                ReportsAdapter.class
        );
    }

    public void insertOne(ReportsModel reportsModel) {
        sqlExecutor.updateQuery(
                "INSERT INTO " + table + " VALUES(?,?,?,?,?);",
                statement -> {
                    statement.set(1, null);
                    statement.set(2, reportsModel.getPlayer());
                    statement.set(3, reportsModel.getMotivo());
                    statement.set(4, reportsModel.getAutor());
                    statement.set(5, reportsModel.getData());
                }
        );
    }

    public void deleteOne(String name) {
        sqlExecutor.updateQuery(
                "DELETE FROM `"+table+"` WHERE `"+table+"`.`player` = ?",
                statement -> {
                    statement.set(1, name);
                }
        );
    }

}
