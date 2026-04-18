/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab1;
import model.Mission;
import report.ShortReport;
import report.FullReport;
import report.ReportGenerator;
import service.MissionService;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author alina
 */
public class Lab2 {

    private static final Map<Integer, ReportGenerator> REPORT_OPTIONS = new LinkedHashMap<>();
    
    static {
        REPORT_OPTIONS.put(1, new ShortReport());
        REPORT_OPTIONS.put(2, new FullReport());
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("MISSION ANALYZER");
        System.out.println();
        
        try (Scanner scanner = new Scanner(System.in)) {
            MissionService service = new MissionService();

            System.out.println("Supported formats: " + service.getSupportedFormats());
            System.out.println();

            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine().trim();
            
            if (filePath.isEmpty()) {
                System.out.println("Error: File path cannot be empty.");
                return;
            }
            
            System.out.println();
            

            Mission mission = loadMissionSafe(service, filePath);
            if (mission == null) {
                return;
            }

            System.out.println("Select report type:");
            for (Map.Entry<Integer, ReportGenerator> entry : REPORT_OPTIONS.entrySet()) {
                System.out.println("  " + entry.getKey() + ". " + entry.getValue().getTypeName());
            }
            System.out.print("Your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }
            
            ReportGenerator selectedReport = REPORT_OPTIONS.get(choice);
            if (selectedReport == null) {
                System.out.println("Invalid choice. Please select 1-" + REPORT_OPTIONS.size() + ".");
                return;
            }

            service.setReportGenerator(selectedReport);
            
            System.out.println();
            String report = service.generateReport(mission);
            System.out.println(report);
            
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println();
        System.out.println("End.");
     
    }
    
    private static Mission loadMissionSafe(MissionService service, String filePath) {
    try {
        return service.loadMission(filePath);
    } catch (IllegalArgumentException e) {
        System.out.println(" Format error: " + e.getMessage());
        System.out.println("   Supported: " + service.getSupportedFormats());
    } catch (Exception e) {
        System.out.println(" Error loading file: " + e.getMessage());
    }
    return null;
    }
}