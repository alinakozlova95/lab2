/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import model.Mission;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
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
        String lower = fileName.toLowerCase();
        return lower.endsWith(".yaml") || lower.endsWith(".yml");
    }
}
