/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import java.util.List;
import model.Mission;
import Validation.RequiredFieldsValidator;
import Validation.Validator;
/**
 *
 * @author alina
 */
public abstract class AbstractParser implements Parser {
    private Validator validator;

    public AbstractParser() {
        this(new RequiredFieldsValidator()); 
    }

    public AbstractParser(Validator validator) {
        this.validator = validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public final Mission parse(String filePath) throws Exception {
        File file = new File(filePath);

        validateFile(file);

        logStart(file);

        Mission mission = doParse(file);

        validateMission(mission);

        postProcess(mission);
       
        logSuccess(file);
        
        return mission;
    }

    protected abstract Mission doParse(File file) throws Exception;

    @Override
    public abstract boolean supports(String fileName);

    protected void validateFile(File file) throws Exception {
        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Not a file: " + file.getAbsolutePath());
        }
        if (file.length() == 0) {
            throw new IllegalArgumentException("File is empty: " + file.getName());
        }
    }

   protected void validateMission(Mission mission) throws Exception {
    if (validator != null) {
        List<String> errors = validator.validate(mission);
        if (!errors.isEmpty()) {
            System.out.println("  Warning: incomplete data detected");
            }
        }
    }


    protected void postProcess(Mission mission) {
    }

    protected void logStart(File file) {
        System.out.println("Parsing: " + file.getName());
    }

    protected void logSuccess(File file) {
        System.out.println("Success: " + file.getName());
    }
}
