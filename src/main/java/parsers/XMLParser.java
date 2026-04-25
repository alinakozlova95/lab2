/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Mission;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 *
 * @author alina
 */
public class XMLParser extends AbstractParser {

    private final XmlMapper mapper;

    public XMLParser() {
        this.mapper = new XmlMapper();
    }

    @Override
    protected Mission doParse(File file) throws Exception {
        return mapper.readValue(file, Mission.class);
    }

    @Override
    public boolean supports(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            return fileName.toLowerCase().endsWith(".xml");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            if (firstLine == null) return false;
            String trimmed = firstLine.trim();
            return trimmed.startsWith("<");
        } catch (Exception e) {
            return fileName.toLowerCase().endsWith(".xml");
        }
    }
}