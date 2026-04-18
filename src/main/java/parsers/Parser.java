/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import model.Mission;
/**
 *
 * @author alina
 */
public interface Parser {
    Mission parse(String filePath) throws Exception;
    boolean supports(String fileName);
}