package ru.scadarnull.service;

import ru.scadarnull.entity.Employee;
import ru.scadarnull.entity.Group;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {

    private List<List<BigDecimal>> limits;
    private List<Group> groups;

    public FileService() {
        limits = new ArrayList<>();
        groups = new ArrayList<>();
    }

    public void readFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader("info.txt")))
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
        String[] fullEmployee = line.split(" ");
        LocalDate localDate = LocalDate.parse(fullEmployee[0]);
        BigDecimal bigDecimal = new BigDecimal(fullEmployee[1]);
        group.add(new Employee(localDate, bigDecimal));
        group.setLimits(limit);
    }

    private void addLimits(String line) {
        String[] fullLimits = line.split(" ");
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
