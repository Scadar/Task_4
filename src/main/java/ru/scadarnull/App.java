package ru.scadarnull;

import ru.scadarnull.entity.Employee;
import ru.scadarnull.entity.Group;
import ru.scadarnull.service.FileService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App 
{
    public static void main( String[] args )
    {
        FileService fileService = new FileService();
        fileService.readFromFile();

        for(Group group : fileService.getGroups()){
            group.calc();
        }
    }
}
