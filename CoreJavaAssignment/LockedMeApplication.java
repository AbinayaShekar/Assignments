package com.lowes.abi;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class LockedMeApplication {
    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("-              LockedMe File Handling System            -");
        System.out.println("-              -----------------------------            -");
        System.out.println("-                  Author:Abinaya Shekar                -");
        System.out.println("=========================================================");
        System.out.println();
        mainMenu();
    }

    public static void mainMenu() {
        Scanner sin = new Scanner(System.in);
        while (true) {
            System.out.println("Enter what you would like to do: ");
            System.out.println("==========================================");
            System.out.println("1 - View the current files");
            System.out.println("2 - Add a file to the existing directory");
            System.out.println("3 - Search a specified file");
            System.out.println("4 - Delete a specified file");
            System.out.println("0 - Close the application");
            System.out.print("Choice? ");
            int choice = sin.nextInt();
            sin.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    sortAndDisplay();
                    System.out.println();
                    break;
                case 2:
                    newFile();
                    System.out.println();
                    break;
                case 3:
                    searchFile();
                    System.out.println();
                    break;
                case 4:
                    deleteFile();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Exit.");
                    sin.close();
                    System.exit(0);
                default:
                    System.out.println("Illegal choice.");
                    System.out.println();
            }
        }
    }

    // Function To Make New File
    public static void newFile()
    {
        String fileLocation="C:\\LockedMe\\";
        try {
            Scanner sin = new Scanner(System.in);

            //We can uncomment this if we need to add file in a specific folder
            //System.out.println("Enter the file path to add : ");
            //String fileLocation = sin.nextLine();

            System.out.println("Enter the file name to add with extension:");
            // Reading File name
            String fileName = sin.nextLine();
            // Creating File Object
            File file1 = new File(fileLocation + fileName);
            // Method createNewFile() method creates blank file.
            if (file1.createNewFile()) {
                System.out.println("File " + file1.getName() + " is created successfully.");
            } else {
                System.out.println("File " + file1.getName() + " already exist in the directory.");
            }
        }
        // Try-Catch Block
        catch (Exception e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }
    }

    // Function To display files in ascending order
    public static void sortAndDisplay(){
        File dir = new File("C:\\LockedMe\\");
        File[] files = dir.listFiles();
        assert files != null;
        if(Arrays.stream(files).findAny().isPresent()) {
            Arrays.sort(files, Comparator.naturalOrder());
            System.out.println("List of files in " + "C:\\LockedMe\\");
            System.out.println("------------------------------------");

            for (File file : files) {
                if (!file.isHidden()) {
                    if (file.isDirectory()) {
                        System.out.println("Dir :  \t" + file.getName());
                    } else {
                        System.out.println("File : \t" + file.getName());
                    }
                }
            }
        }else {
            System.out.println("Folder is empty");
        }
    }

    //Function to search a file
    public static void searchFile(){
        File dir = new File("C:\\LockedMe\\");
        String[] flist = dir.list();
        Scanner sin = new Scanner(System.in);
        System.out.println("Enter the file name with extension to be searched:");
        String fileName = sin.nextLine();
        int flag = 0;
        if (flist == null) {
            System.out.println("Empty directory.");
        }
        else {
            // Linear search in the files list array
            for (String filename : flist) {
                if (filename.equals(fileName)) {
                    System.out.println(filename + " found");
                    flag = 1;
                }
            }
        }
        if (flag == 0) {
            System.out.println("File Not Found");
        }
    }

    //Function to delete a file
    public static void deleteFile(){
        String fileLocation="C:\\LockedMe\\";
        Scanner sin = new Scanner(System.in);
        System.out.println("Enter the file name to be deleted with extension:");
        String fileName = sin.nextLine();
        try
        {
            File f= new File(fileLocation+fileName);
            if(f.exists() && f.getCanonicalFile().getName().equals(f.getName())) {
                if (f.delete()) {
                    System.out.println(f.getName() + " deleted");   //getting and printing the file name
                }
            }
            else
            {
                System.out.println("Failed to delete:File not found");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}





