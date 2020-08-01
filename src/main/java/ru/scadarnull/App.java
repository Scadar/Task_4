package ru.scadarnull;

import ru.scadarnull.entity.Group;
import ru.scadarnull.service.FileService;
import ru.scadarnull.service.HolidaysService;


public class App 
{
    public static void main( String[] args )
    {
        if(args.length < 1){
            System.out.println("Неверные аргументы программы, введите название файла");
        }else{
            String file = args[0];
            FileService fileService = new FileService(file);
            fileService.readFromFile();

            for(Group group : fileService.getGroups()){
                group.calc();
            }
        }

    }
}
