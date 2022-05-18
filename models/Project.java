package models;

import com.opencsv.bean.CsvBindByPosition;
//the model
public class Project {
    //anotations used by opecsv
    @CsvBindByPosition(position = 0)
    private int EmpID;
    @CsvBindByPosition(position = 1)
    private int projects;
    @CsvBindByPosition(position = 2)
    private String DateFrom;
    @CsvBindByPosition(position = 3)
    private String DateTo;

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public int getProjects() {
        return projects;
    }

    public void setProjects(int projects) {
        this.projects = projects;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }


    public Project(int empID, int projects, String dateFrom, String dateTo) {
        EmpID = empID;
        this.projects = projects;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

    public Project() {
    }
}
