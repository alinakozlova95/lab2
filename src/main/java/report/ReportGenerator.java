/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;
import model.Mission;
/**
 *
 * @author alina
 */
public interface ReportGenerator {
    String generate(Mission mission);
    String getTypeName();
}