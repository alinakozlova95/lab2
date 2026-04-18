/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;
import model.Curse;
import model.Mission;
import model.Sorcerer;
import model.Technique;
/**
 *
 * @author alina
 */
public class ShortReport implements ReportGenerator{
    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        sb.append("\n");
        sb.append("         MISSION SHORT REPORT           \n");
        sb.append("\n");
        sb.append("\n");
        sb.append("MISSION ID: ").append(nullToEmpty(mission.getMissionId())).append("\n");
        sb.append("Date:       ").append(nullToEmpty(mission.getDate())).append("\n");
        sb.append("Location:   ").append(nullToEmpty(mission.getLocation())).append("\n");
        sb.append("Outcome:    ").append(nullToEmpty(mission.getOutcome())).append("\n");
        
        if (mission.getDamageCost() > 0) {
            sb.append("Damage:     ").append(mission.getDamageCost()).append("\n");
        }
        
        Curse curse = mission.getCurse();
        if (curse != null) {
            sb.append("\n CURSE \n");
            sb.append("  ").append(nullToEmpty(curse.getName()));
            sb.append(" (").append(nullToEmpty(curse.getThreatLevel())).append(")\n");
        }

        if (mission.getSorcerers() != null && !mission.getSorcerers().isEmpty()) {
            sb.append("\n SORCERERS (").append(mission.getSorcerers().size()).append(") \n");
            for (Sorcerer s : mission.getSorcerers()) {
                sb.append("  • ").append(nullToEmpty(s.getName()));
                sb.append(" [").append(nullToEmpty(s.getRank())).append("]\n");
            }
        }
        
        if (mission.getTechniques() != null && !mission.getTechniques().isEmpty()) {
            sb.append("\n TECHNIQUES (").append(mission.getTechniques().size()).append(") \n");
            for (Technique t : mission.getTechniques()) {
                sb.append("  • ").append(nullToEmpty(t.getName()));
                sb.append(" (").append(nullToEmpty(t.getType())).append(")\n");
            }
        }

        if (mission.getComment() != null && !mission.getComment().trim().isEmpty()) {
            sb.append("\n COMMENT \n");
            sb.append("  ").append(mission.getComment()).append("\n");
        }
        
        sb.append("\n");
        
        return sb.toString();
    }

    @Override
    public String getTypeName() {
        return "Short Report";
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
