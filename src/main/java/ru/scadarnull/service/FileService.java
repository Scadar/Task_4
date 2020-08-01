package ru.scadarnull.service;

import ru.scadarnull.entity.Employee;
import ru.scadarnull.entity.Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {

    private final List<List<BigDecimal>> limits;
    private final List<Group> groups;
    private final String file;

    public FileService(String file) {
        this.file = file;
        limits = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public void readFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line = reader.readLine();
            while (line.trim().length() != 0){
                addLimits(line);
                line = reader.readLine();
            }

            for (List<BigDecimal> limit : limits) {
                Group group = addGroup();
                line = reader.readLine();
                while (line != null && line.trim().length() != 0) {
                    addEmployee(line, group, limit);
                    line = reader.readLine();
                }
            }
        } catch (IOException fileNotFoundException){
            System.out.println(fileNotFoundException.getMessage());
        }
    }

    private void addEmployee(String line, Group group, List<BigDecimal> limit) {
        String[] fullEmployee = line.split(";");
        String name = fullEmployee[0];
        BigDecimal salary = new BigDecimal(fullEmployee[1]);
        LocalDate start = LocalDate.parse(fullEmployee[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if(fullEmployee.length == 3){
            group.add(new Employee(name, salary, start));
        }else if(fullEmployee.length == 4){
            LocalDate end = LocalDate.parse(fullEmployee[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            group.add(new Employee(name, salary, start, end));
        }
        group.setLimits(limit);
    }

    private void addLimits(String line) {
        String[] fullLimits = line.split(";");
        List<BigDecimal> temp = new ArrayList<>();
        limits.add(temp);
        for(String limit : fullLimits){
            try {
                temp.add(new BigDecimal(limit));
            }catch (NumberFormatException exception){
                System.out.println(Arrays.toString(exception.getStackTrace()));
            }
        }
    }

    private Group addGroup(){
        Group group = new Group();
        groups.add(group);
        return group;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
