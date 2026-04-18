/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Mission;
/**
 *
 * @author alina
 */
public class JsonParser extends AbstractParser {

    private final ObjectMapper mapper;

    public JsonParser() {
        this.mapper = new ObjectMapper();
    }

    @Override
    protected Mission doParse(File file) throws Exception {
        return mapper.readValue(file, Mission.class);
    }

    @Override
    public boolean supports(String fileName) {
        return fileName.toLowerCase().endsWith(".json");
    }
}
