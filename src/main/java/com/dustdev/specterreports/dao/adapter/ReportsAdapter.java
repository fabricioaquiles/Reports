package com.dustdev.specterreports.dao.adapter;

import com.dustdev.specterreports.model.ReportsModel;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

public class ReportsAdapter implements SQLResultAdapter<ReportsModel> {

    @Override
    public ReportsModel adaptResult(SimpleResultSet resultSet) {

        int id = resultSet.get("id");
        String player = resultSet.get("player");
        String motivo = resultSet.get("motivo");
        String autor = resultSet.get("autor");
        Long data = Long.parseLong(resultSet.get("data"));

        return ReportsModel
                .builder()
                .id(id)
                .player(player)
                .motivo(motivo)
                .autor(autor)
                .data(data)
                .build();
    }

}
