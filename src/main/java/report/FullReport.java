/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;
import model.*;
import java.util.List;
import java.util.Map;
/**
 *
 * @author alina
 */
public class FullReport implements ReportGenerator {
    @Override
    public String generate(Mission mission) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\n");
        sb.append("\n");
        sb.append("                    MISSION REPORT                      \n");
        sb.append("\n");
        sb.append("\n");
        sb.append("\n");
        sb.append(" BASIC INFORMATION                                       \n");
        sb.append("\n");
        sb.append("  Mission ID:    ").append(nullToEmpty(mission.getMissionId())).append("\n");
        sb.append("  Date:          ").append(nullToEmpty(mission.getDate())).append("\n");
        sb.append("  Location:      ").append(nullToEmpty(mission.getLocation())).append("\n");
        sb.append("  Outcome:       ").append(nullToEmpty(mission.getOutcome())).append("\n");
        sb.append("  Damage Cost:   ").append(mission.getDamageCost()).append("\n");
        sb.append("\n");
        
        Curse curse = mission.getCurse();
        if (curse != null) {
            sb.append("\n");
            sb.append(" CURSE                                                   │\n");
            sb.append("\n");
            sb.append("  Name:          ").append(nullToEmpty(curse.getName())).append("\n");
            sb.append("  Threat Level:  ").append(nullToEmpty(curse.getThreatLevel())).append("\n");
            sb.append("\n");
        }
        
        List<Sorcerer> sorcerers = mission.getSorcerers();
        if (sorcerers != null && !sorcerers.isEmpty()) {
            sb.append("\n");
            sb.append(" SORCERERS (").append(sorcerers.size()).append(")                                              \n");
            sb.append("\n");
            for (int i = 0; i < sorcerers.size(); i++) {
                Sorcerer s = sorcerers.get(i);
                sb.append("  ").append(i + 1).append(". ");
                sb.append(nullToEmpty(s.getName()));
                sb.append(" [").append(nullToEmpty(s.getRank())).append("]\n");
            }
            sb.append("\n");
        }
        
        List<Technique> techniques = mission.getTechniques();
        if (techniques != null && !techniques.isEmpty()) {
            sb.append("\n");
            sb.append(" TECHNIQUES (").append(techniques.size()).append(")                                             \n");
            sb.append("\n");
            for (int i = 0; i < techniques.size(); i++) {
                Technique t = techniques.get(i);
                sb.append("  ").append(i + 1).append(". ");
                sb.append(nullToEmpty(t.getName()));
                sb.append(" (").append(nullToEmpty(t.getType())).append(")\n");
                sb.append("     Owner:  ").append(nullToEmpty(t.getOwner())).append("\n");
                sb.append("     Damage: ").append(t.getDamage()).append("\n");
            }
            sb.append("\n");
        }
        
        EconomicAssessment econ = mission.getEconomicAssessment();
        if (econ != null) {
            sb.append("\n");
            sb.append(" ECONOMIC ASSESSMENT                                     \n");
            sb.append("\n");
            if (econ.getTotalDamageCost() != null) {
                sb.append("  Total Damage:        ").append(econ.getTotalDamageCost()).append("\n");
            }
            if (econ.getInfrastructureDamage() != null) {
                sb.append("  Infrastructure:      ").append(econ.getInfrastructureDamage()).append("\n");
            }
            if (econ.getCommercialDamage() != null) {
                sb.append("  Commercial:          ").append(econ.getCommercialDamage()).append("\n");
            }
            if (econ.getTransportDamage() != null) {
                sb.append("  Transport:           ").append(econ.getTransportDamage()).append("\n");
            }
            if (econ.getRecoveryEstimateDays() != null) {
                sb.append("  Recovery (days):     ").append(econ.getRecoveryEstimateDays()).append("\n");
            }
            if (econ.getInsuranceCovered() != null) {
                sb.append("  Insurance Covered:   ").append(econ.getInsuranceCovered() ? "Yes" : "No").append("\n");
            }
            sb.append("\n");
        }
        
        CivilianImpact civilian = mission.getCivilianImpact();
        if (civilian != null) {
            sb.append("\n");
            sb.append(" CIVILIAN IMPACT                                         \n");
            sb.append("\n");
            if (civilian.getEvacuated() != null) {
                sb.append("  Evacuated:          ").append(civilian.getEvacuated()).append("\n");
            }
            if (civilian.getInjured() != null) {
                sb.append("  Injured:            ").append(civilian.getInjured()).append("\n");
            }
            if (civilian.getMissing() != null) {
                sb.append("  Missing:            ").append(civilian.getMissing()).append("\n");
            }
            if (civilian.getPublicExposureRisk() != null) {
                sb.append("  Public Exposure:    ").append(civilian.getPublicExposureRisk()).append("\n");
            }
            sb.append("\n");
        }
         
        EnemyActivity enemy = mission.getEnemyActivity();
        if (enemy != null) {
            sb.append("\n");
            sb.append(" ENEMY ACTIVITY                                          \n");
            sb.append("\n");
            if (enemy.getBehaviorType() != null) {
                sb.append("  Behavior Type:      ").append(enemy.getBehaviorType()).append("\n");
            }
            if (enemy.getMobility() != null) {
                sb.append("  Mobility:           ").append(enemy.getMobility()).append("\n");
            }
            if (enemy.getEscalationRisk() != null) {
                sb.append("  Escalation Risk:    ").append(enemy.getEscalationRisk()).append("\n");
            }
            if (enemy.getTargetPriority() != null && !enemy.getTargetPriority().isEmpty()) {
                sb.append("  Target Priority:    ").append(String.join(", ", enemy.getTargetPriority())).append("\n");
            }
            if (enemy.getAttackPatterns() != null && !enemy.getAttackPatterns().isEmpty()) {
                sb.append("  Attack Patterns:    ").append(String.join(", ", enemy.getAttackPatterns())).append("\n");
            }
            sb.append("\n");
        }
        
        EnvironmentConditions env = mission.getEnvironmentConditions();
        if (env != null) {
            sb.append("\n");
            sb.append(" ENVIRONMENT CONDITIONS                                  \n");
            sb.append("\n");
            if (env.getWeather() != null) {
                sb.append("  Weather:            ").append(env.getWeather()).append("\n");
            }
            if (env.getTimeOfDay() != null) {
                sb.append("  Time of Day:        ").append(env.getTimeOfDay()).append("\n");
            }
            if (env.getVisibility() != null) {
                sb.append("  Visibility:         ").append(env.getVisibility()).append("\n");
            }
            if (env.getCursedEnergyDensity() != null) {
                sb.append("  Energy Density:     ").append(env.getCursedEnergyDensity()).append("\n");
            }
            sb.append("\n");
        }
        
        List<TimelineEvent> timeline = mission.getOperationTimeline();
        if (timeline != null && !timeline.isEmpty()) {
            sb.append("\n");
            sb.append(" OPERATION TIMELINE (").append(timeline.size()).append(")                                    \n");
            sb.append("\n");
            for (int i = 0; i < timeline.size(); i++) {
                TimelineEvent e = timeline.get(i);
                sb.append("  ").append(i + 1).append(". [").append(nullToEmpty(e.getTimestamp())).append("] ");
                sb.append(nullToEmpty(e.getType())).append("\n");
                sb.append("     ").append(nullToEmpty(e.getDescription())).append("\n");
            }
            sb.append("\n");
        }
        
       
        if (hasAnyData(mission)) {
            sb.append("\n");
            sb.append(" ADDITIONAL DATA                                         \n");
            sb.append("\n");
            
            printListIfNotEmpty(sb, "Operation Tags", mission.getOperationTags());
            printListIfNotEmpty(sb, "Support Units", mission.getSupportUnits());
            printListIfNotEmpty(sb, "Recommendations", mission.getRecommendations());
            printListIfNotEmpty(sb, "Artifacts Recovered", mission.getArtifactsRecovered());
            printListIfNotEmpty(sb, "Evacuation Zones", mission.getEvacuationZones());
            printListIfNotEmpty(sb, "Status Effects", mission.getStatusEffects());
        }
        
        if (mission.getNote() != null && !mission.getNote().trim().isEmpty()) {
            sb.append("\n");
            sb.append(" NOTE                                                    \n");
            sb.append("\n");
            sb.append("  ").append(mission.getNote()).append("\n");
            sb.append("\n");
        }
        
        if (mission.getComment() != null && !mission.getComment().trim().isEmpty()) {
            sb.append("\n");
            sb.append(" COMMENT                                                 \n");
            sb.append("\n");
            sb.append("  ").append(mission.getComment()).append("\n");
            sb.append("\n");
        }

        Map<String, Object> extras = mission.getExtras();
        if (extras != null && !extras.isEmpty()) {
            sb.append("\n");
            sb.append(" EXTRA FIELDS                                            \n");
            sb.append("\n");
            for (Map.Entry<String, Object> entry : extras.entrySet()) {
                sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            sb.append("\n");
        }
        
        sb.append("\n");
        
        return sb.toString();
    }

    @Override
    public String getTypeName() {
        return "Full Report";
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    private void printListIfNotEmpty(StringBuilder sb, String title, List<String> list) {
        if (list != null && !list.isEmpty()) {
            sb.append("  ").append(title).append(": ");
            sb.append(String.join(", ", list)).append("\n");
        }
    }

    private boolean hasAnyData(Mission mission) {
        return (mission.getOperationTags() != null && !mission.getOperationTags().isEmpty()) ||
               (mission.getSupportUnits() != null && !mission.getSupportUnits().isEmpty()) ||
               (mission.getRecommendations() != null && !mission.getRecommendations().isEmpty()) ||
               (mission.getArtifactsRecovered() != null && !mission.getArtifactsRecovered().isEmpty()) ||
               (mission.getEvacuationZones() != null && !mission.getEvacuationZones().isEmpty()) ||
               (mission.getStatusEffects() != null && !mission.getStatusEffects().isEmpty());
    }
}