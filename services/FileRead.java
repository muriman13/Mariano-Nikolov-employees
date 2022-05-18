package services;

import com.opencsv.bean.CsvToBeanBuilder;
import models.Project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class FileRead {

    public List<Project> getRecordFromLine(String path) throws FileNotFoundException {
            String fileName = path;

            List<Project> projects = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(Project.class)
                    .build()
                    .parse();

        for (Project n : projects) {
            System.out.println(n.getEmpID() +" "+ n.getProjects() + n.getDateFrom() + n.getDateTo());
        }
    return  projects;
        }

}
