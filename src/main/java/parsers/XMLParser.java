/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Mission;

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
        return fileName.toLowerCase().endsWith(".xml");
    }
}