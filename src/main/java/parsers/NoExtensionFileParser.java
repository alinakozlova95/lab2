/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsers;
import builder.MissionBuilderImpl;
import model.Curse;
import model.Mission;
import model.Sorcerer;
import model.Technique;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alina
 */

public class NoExtensionFileParser extends AbstractParser {

    @Override
    protected Mission doParse(File file) throws Exception {
        MissionBuilderImpl builder = new MissionBuilderImpl();
        
        List<Sorcerer> sorcerers = new ArrayList<>();
        List<Technique> techniques = new ArrayList<>();
        List<String> timelineEvents = new ArrayList<>();
        List<String> enemyActions = new ArrayList<>();

        int unknownCounter = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split("\\|");
                if (parts.length == 0) {
                    continue;
                }
                
                String command = parts[0].trim();
                
                switch (command) {
                    case "MISSION_CREATED":
                        if (parts.length >= 4) {
                            builder.setMissionId(parts[1].trim());
                            builder.setDate(parts[2].trim());
                            builder.setLocation(parts[3].trim());
                        }
                        break;
                        
                    case "CURSE_DETECTED":
                        if (parts.length >= 3) {
                            Curse curse = new Curse();
                            curse.setName(parts[1].trim());
                            curse.setThreatLevel(parts[2].trim());
                            builder.setCurse(curse);
                        }
                        break;
                        
                    case "SORCERER_ASSIGNED":
                        if (parts.length >= 3) {
                            Sorcerer sorcerer = new Sorcerer();
                            sorcerer.setName(parts[1].trim());
                            sorcerer.setRank(parts[2].trim());
                            sorcerers.add(sorcerer);
                        }
                        break;
                        
                    case "TECHNIQUE_USED":
                        if (parts.length >= 5) {
                            Technique technique = new Technique();
                            technique.setName(parts[1].trim());
                            technique.setType(parts[2].trim());
                            technique.setOwner(parts[3].trim());
                            try {
                                technique.setDamage(Long.parseLong(parts[4].trim()));
                            } catch (NumberFormatException e) {
                                technique.setDamage(0);
                            }
                            techniques.add(technique);
                        }
                        break;
                        
                    case "MISSION_RESULT":
                        if (parts.length >= 3) {
                            builder.setOutcome(parts[1].trim());
                            String damageStr = parts[2].trim();
                            if (damageStr.startsWith("damageCost=")) {
                                damageStr = damageStr.substring(11);
                            }
                            try {
                                builder.setDamageCost(Long.parseLong(damageStr));
                            } catch (NumberFormatException e) {
                                builder.setDamageCost(0);
                                builder.putExtra("damageCost_raw", damageStr);
                            }
                        }
                        break;
                        
                    case "TIMELINE_EVENT":
                        timelineEvents.add(line);
                        builder.putExtra("timeline_" + timelineEvents.size(), line);
                        break;
                        
                    case "ENEMY_ACTION":
                        enemyActions.add(line);
                        builder.putExtra("enemyAction_" + enemyActions.size(), line);
                        break;
                        
                    case "CIVILIAN_IMPACT":
                        for (int i = 1; i < parts.length; i++) {
                            String[] kv = parts[i].trim().split("=", 2);
                            if (kv.length == 2) {
                                builder.putExtra("civilian." + kv[0].trim(), kv[1].trim());
                            } else {
                                builder.putExtra("civilian_" + i, parts[i]);
                            }
                        }
                        break;
                        
                    default:
                        StringBuilder valueBuilder = new StringBuilder();
                        for (int i = 1; i < parts.length; i++) {
                            if (i > 1) valueBuilder.append("|");
                            valueBuilder.append(parts[i]);
                        }
                        builder.putExtra(unknownCounter + "_" + command, valueBuilder.toString());
                        unknownCounter++;
                        break;
                }
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

    @Override
    public boolean supports(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstLine = reader.readLine();
            if (firstLine == null) return false;
            String trimmed = firstLine.trim();
            if (trimmed.contains("|")) return true;
            if (!trimmed.startsWith("{") && !trimmed.startsWith("<") && !trimmed.startsWith("[") && !trimmed.contains(": ")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}