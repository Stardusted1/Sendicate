package com.stardusted1.Sendicate.app.core.reporting;

import java.util.LinkedList;

public interface Reportable {

    public LinkedList<Report> GetReports();
    public boolean AddReport(Report report);
    public boolean DeleteReport(Report report);
}
