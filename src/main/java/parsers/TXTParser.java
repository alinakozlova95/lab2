/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import java.io.File;
import model.Curse;
import model.Mission;
import model.Sorcerer;
import model.Technique;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import builder.MissionBuilderImpl;

/**
 *
 * @author alina
 */
public class TXTParser extends AbstractParser {

    @Override
    protected Mission doParse(File file) throws Exception {
        String firstLine = null;
        try (BufferedReader probe = new BufferedReader(new FileReader(file))) {
            firstLine = probe.readLine();
        }
        if (firstLine != null && firstLine.startsWith("[")) {
            return parseSectionFormat(file);
        } else {
            return parseKeyValueFormat(file);
        }
    }
    private Mission parseSectionFormat(File file) throws Exception {
        MissionBuilderImpl builder = new MissionBuilderImpl();
        
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        
        String currentSection = null;
        List<String> sectionLines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                if (line.startsWith("[") && line.endsWith("]")) {
                    if (currentSection != null) {
                        processSection(currentSection, sectionLines, builder, sorcerers, techniques);
                    }
                    currentSection = line.substring(1, line.length() - 1);
                    sectionLines.clear();
                } else if (currentSection == null) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        applyField(parts[0].trim(), parts[1].trim(), builder);
                    }
                } else {
                    sectionLines.add(line);
                }
            }
            
            if (currentSection != null) {
                processSection(currentSection, sectionLines, builder, sorcerers, techniques);
            }
        }
        
        for (Sorcerer s : sorcerers) {
            builder.addSorcerer(s);
        }
        for (Technique t : techniques) {
            builder.addTechnique(t);
        }
        
        return builder.build();
    }
    
    private void processSection(String name, List<String> lines, MissionBuilderImpl builder,
                                List<Sorcerer> sorcerers, List<Technique> techniques) {
        switch (name) {
            case "MISSION":
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        applyField(parts[0].trim(), parts[1].trim(), builder);
                    }
                }
                break;
            case "CURSE":
                Curse curse = new Curse();
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String k = parts[0].trim(), v = parts[1].trim();
                        if (k.equals("name")) curse.setName(v);
                        else if (k.equals("threatLevel")) curse.setThreatLevel(v);
                        else builder.putExtra("curse." + k, v);
                    }
                }
                builder.setCurse(curse);
                break;
            case "SORCERER":
                Sorcerer s = new Sorcerer();
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String k = parts[0].trim(), v = parts[1].trim();
                        if (k.equals("name")) s.setName(v);
                        else if (k.equals("rank")) s.setRank(v);
                        else builder.putExtra("sorcerer." + k, v);
                    }
                }
                sorcerers.add(s);
                break;
            case "TECHNIQUE":
                Technique t = new Technique();
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        String k = parts[0].trim(), v = parts[1].trim();
                        if (k.equals("name")) t.setName(v);
                        else if (k.equals("type")) t.setType(v);
                        else if (k.equals("owner")) t.setOwner(v);
                        else if (k.equals("damage")) {
                            try { t.setDamage(Long.parseLong(v)); } 
                            catch (NumberFormatException e) { builder.putExtra("technique.damage_raw", v); }
                        }
                        else builder.putExtra("technique." + k, v);
                    }
                }
                techniques.add(t);
                break;
            case "ENVIRONMENT":
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        builder.putExtra("environment." + parts[0].trim(), parts[1].trim());
                    }
                }
                break;
            default:
                StringBuilder sb = new StringBuilder();
                for (String line : lines) sb.append(line).append("\n");
                builder.putExtra("section_" + name, sb.toString());
        }
    }
    
    private void applyField(String key, String value, MissionBuilderImpl builder) {
        switch (key) {
            case "missionId": builder.setMissionId(value); break;
            case "date": builder.setDate(value); break;
            case "location": builder.setLocation(value); break;
            case "outcome": builder.setOutcome(value); break;
            case "damageCost":
                try { builder.setDamageCost(Long.parseLong(value)); } 
                catch (NumberFormatException e) { builder.putExtra("damageCost_raw", value); }
                break;
            case "note": builder.setNote(value); break;
            case "comment": builder.setComment(value); break;
            default: builder.putExtra(key, value);
        }
    }

    private Mission parseKeyValueFormat(File file) throws Exception {
        MissionBuilderImpl builder = new MissionBuilderImpl();
        Curse curse = new Curse();
        builder.setCurse(curse);

        Map<Integer, Sorcerer> sorcererMap = new HashMap<>();
        Map<Integer, Technique> techniqueMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(": ", 2);
                if (parts.length < 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                if (key.equals("missionId")) builder.setMissionId(value);
                else if (key.equals("date")) builder.setDate(value);
                else if (key.equals("location")) builder.setLocation(value);
                else if (key.equals("outcome")) builder.setOutcome(value);
                else if (key.equals("damageCost")) {
                    try { builder.setDamageCost(Long.parseLong(value)); } 
                    catch (NumberFormatException e) { builder.putExtra("damageCost_raw", value); }
                }
                else if (key.equals("curse.name")) curse.setName(value);
                else if (key.equals("curse.threatLevel")) curse.setThreatLevel(value);
                else if (key.startsWith("sorcerer")) {
                    int idx = Integer.parseInt(key.substring(key.indexOf("[")+1, key.indexOf("]")));
                    Sorcerer s = sorcererMap.computeIfAbsent(idx, k -> new Sorcerer());
                    if (key.endsWith("name")) s.setName(value);
                    else if (key.endsWith("rank")) s.setRank(value);
                }
                else if (key.startsWith("technique")) {
                    int idx = Integer.parseInt(key.substring(key.indexOf("[")+1, key.indexOf("]")));
                    Technique t = techniqueMap.computeIfAbsent(idx, k -> new Technique());
                    if (key.endsWith("name")) t.setName(value);
                    else if (key.endsWith("type")) t.setType(value);
                    else if (key.endsWith("owner")) t.setOwner(value);
                    else if (key.endsWith("damage")) {
                        try { t.setDamage(Long.parseLong(value)); } 
                        catch (NumberFormatException e) {}
                    }
                }
                else if (key.equals("note")) builder.setNote(value);
                else if (key.equals("comment")) builder.setComment(value);
                else builder.putExtra(key, value);
            }
        }

        for (Sorcerer s : sorcererMap.values()) builder.addSorcerer(s);
        for (Technique t : techniqueMap.values()) builder.addTechnique(t);
        return builder.build();
    }

  @Override
  public boolean supports(String fileName) {
      File file = new File(fileName);
      if (!file.exists() || !file.isFile()) {
          return fileName.toLowerCase().endsWith(".txt");
      }
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
          String firstLine = reader.readLine();
          if (firstLine == null) return false;
          String trimmed = firstLine.trim();
          if (trimmed.startsWith("[")) return true;
          if (trimmed.contains(": ") && !trimmed.startsWith("{") && !trimmed.startsWith("<")) {
              return true;
          }
          return fileName.toLowerCase().endsWith(".txt");
      } catch (Exception e) {
          return fileName.toLowerCase().endsWith(".txt");
      }
  }
}