package services;

import models.Project;
import org.apache.commons.lang3.time.DateUtils;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Calculate  {

    FileRead fileRead = new FileRead();

    public Date convertStringToDate(String path) throws FileNotFoundException, ParseException {
        List<Project> projectsList = fileRead.getRecordFromLine(path);
        for (Project n : projectsList) {
            System.out.println(convertToDate(n.getDateTo()));
        }
        return null;
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        if (dateToConvert == null) {
            return new Date().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Date convertToDate(String date) throws ParseException {
        if (date.isBlank() || date.isEmpty() || date.equals(" NULL")) {
            return new Date();
        }
        Date date1 = null;
        String[] possibleDateFormats =
                {
                        "yyyy.MM.dd G 'at' HH:mm:ss z",
                        "EEE, MMM d, ''yy",
                        "h:mm a",
                        "hh 'o''clock' a, zzzz",
                        "K:mm a, z",
                        "yyyyy.MMMMM.dd GGG hh:mm aaa",
                        "EEE, d MMM yyyy HH:mm:ss Z",
                        "yyMMddHHmmssZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                        "YYYY-'W'ww-u",
                        "EEE, dd MMM yyyy HH:mm:ss z",
                        "EEE, dd MMM yyyy HH:mm zzzz",
                        "yyyy-MM-dd'T'HH:mm:ssZ",
                        "yyyy-MM-dd'T'HH:mm:ss.SSSzzzz",
                        "yyyy-MM-dd'T'HH:mm:sszzzz",
                        "yyyy-MM-dd'T'HH:mm:ss z",
                        "yyyy-MM-dd'T'HH:mm:ssz",
                        "yyyy-MM-dd'T'HH:mm:ss",
                        "yyyy-MM-dd'T'HHmmss.SSSz",
                        "yyyy-MM-dd",
                        "yyyyMMdd",
                        "dd/MM/yy",
                        "dd/MM/yyyy"
                };

        try {

            date1 = DateUtils.parseDate(date, possibleDateFormats);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return date1;
    }

    public ArrayList<String[]> getEmployees(String path) throws FileNotFoundException, ParseException {
        String []result = new String[4];// simple string array to  add to allProjects, we use array because we know the prefixed size
        ArrayList<String[]> allProjects = new ArrayList<String[]>();
        allProjects.add(new String[]{"", "", "", ""});// generating 0 index element with empty strings in ArrayList that is later just set
        Date date1 = null, date2 = null;
        long days = 0, maxDays = 0;
        //calling FileREad claas that uses opencsv dependency to map the csv file to an object. Note that we could of used a scanner instead, but it could have produced errors with "" and other symbols
        FileRead fileRead = new FileRead();
        List<Project> projectsList = fileRead.getRecordFromLine(path);
        Project[] test = new Project[projectsList.size()];
        //converting ArrayList to array. Note that we could have used - test = projectsList.stream().toArray();
        for (int i = 0; i < test.length; i++) {
            test[i] = projectsList.get(i);
        }
        //sorting by projectId
        Arrays.sort(test, Comparator.comparing(Project::getProjects));
        //checks if 2 people have worked on common project, and if so, for how long. it checks if in the sorted (by projectId) array the next element  is with the same projectId
        //then it checks if the end date of one of them isn't before the start date of the other one and vise versa, then it calculates the difference in days
        for (int i = 0; i < test.length - 1; i++) {
            if (test[i].getEmpID() != test[i + 1].getEmpID() && test[i].getProjects() == test[i + 1].getProjects() && this.convertToDate(test[i].getDateTo()).after(this.convertToDate(test[i + 1].getDateFrom()))) {
                if (this.convertToDate(test[i].getDateFrom()).after(this.convertToDate(test[i + 1].getDateFrom()))) {
                    date1 = this.convertToDate(test[i].getDateFrom());

                } else {
                    date1 = this.convertToDate(test[i + 1].getDateFrom());
                }
                if (this.convertToDate(test[i].getDateTo()).before(this.convertToDate(test[i + 1].getDateTo()))) {
                    date2 = this.convertToDate(test[i].getDateTo());
                } else {
                    date2 = this.convertToDate(test[i + 1].getDateTo());
                }
            }
            //uses chronounits from time library to calculate time period
            days = ChronoUnit.DAYS.between(convertToLocalDateTimeViaInstant(date1), convertToLocalDateTimeViaInstant(date2));
            //checks for new max days
            if (days > maxDays) {
            maxDays = days;
            //prints max on first row
                allProjects.set(0,new String[] {String.valueOf(test[i].getEmpID()) , String.valueOf(test[i+1].getEmpID()) , String.valueOf(test[i].getProjects()), String.valueOf(maxDays)});
            } else {

            }



        }
        //prints all common project betwen the people working the longest on single project
        for (int i = 0; i < test.length-1; i++) {
            if( test[i].getEmpID() != test[i + 1].getEmpID()
                    &&  (test[i]).getProjects()==test[i+1].getProjects()
                    && (test[i].getEmpID() == Integer.valueOf(allProjects.get(0)[0]) || test[i].getEmpID() == Integer.valueOf(allProjects.get(0)[1]))
                    && (test[i+1].getEmpID() == Integer.valueOf(allProjects.get(0)[0]) || test[i+1].getEmpID() == Integer.valueOf(allProjects.get(0)[1])

            )
            )
            {
                allProjects.add(new String[] {String.valueOf(test[i].getEmpID()) , String.valueOf(test[i+1].getEmpID()) , String.valueOf(test[i].getProjects()),"  "});
            }
        }



        System.out.println("days worked together: " + maxDays);
        return allProjects;
    }
}









