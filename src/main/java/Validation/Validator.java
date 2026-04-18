/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validation;
import model.Mission;
import java.util.List;
/**
 *
 * @author alina
 */
public interface Validator {
    List<String> validate(Mission mission);
}