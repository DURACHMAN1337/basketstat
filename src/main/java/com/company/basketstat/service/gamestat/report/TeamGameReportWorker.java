package com.company.basketstat.service.gamestat.report;

import java.util.List;
import java.util.Map;

public interface TeamGameReportWorker {
    String NAME = "TeamGameReportWorker";

    List<Map<String, Object>> generateHeader(Map<String, Object> params);

    List<Map<String, Object>> generateTable(Map<String, Object> params);
}
