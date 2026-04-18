/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import parsers.Parser;
import model.Mission;
import parsers.RegistryParser;
import report.ReportGenerator;

/**
 *
 * @author alina
 */
public class MissionService {

    private final RegistryParser registryParser;
    private ReportGenerator reportGenerator;

    public MissionService() {
        this.registryParser = new RegistryParser();
        registerDefaultParsers();
        this.reportGenerator = new report.ShortReport(); 
    }
    private void registerDefaultParsers() {
        registryParser.register(new parsers.JsonParser());
        registryParser.register(new parsers.XMLParser());
        registryParser.register(new parsers.YAMLParser());
        registryParser.register(new parsers.TXTParser());
    }


    public void registerParser(Parser parser) {
        registryParser.register(parser);
    }

    public void setReportGenerator(ReportGenerator generator) {
        this.reportGenerator = generator;
    }

    public Mission loadMission(String filePath) throws Exception {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be empty");
        }
        Parser parser = registryParser.getParser(filePath);
        Mission mission = parser.parse(filePath);
        
        return mission;
    }

    public String generateReport(Mission mission) {
        if (reportGenerator == null) {
            throw new IllegalStateException("Report generator is not set");
        }
        return reportGenerator.generate(mission);
    }

    public String loadAndReport(String filePath) throws Exception {
        Mission mission = loadMission(filePath);
        return generateReport(mission);
    }

    public ReportGenerator getReportGenerator() {
        return reportGenerator;
    }

    public RegistryParser getParserRegistry() {
        return registryParser;
    }

    public boolean isFormatSupported(String filePath) {
        return registryParser.hasParser(filePath);
    }

    public String getSupportedFormats() {
        StringBuilder sb = new StringBuilder();
        var parsers = registryParser.getRegisteredParsers();
        for (int i = 0; i < parsers.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(parsers.get(i).getClass().getSimpleName().replace("Parser", ""));
        }
        return sb.toString();
    }
}