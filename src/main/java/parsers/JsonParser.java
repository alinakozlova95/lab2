/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Mission;
import java.io.BufferedReader;
import java.io.FileReader;
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
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            return fileName.toLowerCase().endsWith(".json");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            if (firstLine == null) return false;
            String trimmed = firstLine.trim();
            return trimmed.startsWith("{");
        } catch (Exception e) {
            return fileName.toLowerCase().endsWith(".json");
        }
    }
}
