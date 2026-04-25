/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import model.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author alina
 */
public class YAMLParser extends AbstractParser {

    private final ObjectMapper mapper;

    public YAMLParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    @Override
    protected Mission doParse(File file) throws Exception {
        return mapper.readValue(file, Mission.class);
    }

    @Override
    public boolean supports(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            String lower = fileName.toLowerCase();
            return lower.endsWith(".yaml") || lower.endsWith(".yml");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            if (firstLine == null) return false;
            String trimmed = firstLine.trim();
            if (trimmed.startsWith("{") || trimmed.startsWith("[") || trimmed.startsWith("<") || trimmed.contains("|")) {
                return false;
            }
            return trimmed.contains(":") && !trimmed.contains("|");
        } catch (Exception e) {
            String lower = fileName.toLowerCase();
            return lower.endsWith(".yaml") || lower.endsWith(".yml");
        }
    }
}
