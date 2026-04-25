/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author alina
 */

public class EnemyActivity {
    private String behaviorType;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "targetPriority")
    private List<String> targetPriority = new ArrayList<>();
    
    @JacksonXmlElementWrapper(localName = "attackPatterns")
    @JacksonXmlProperty(localName = "pattern")
    private List<String> attackPatterns = new ArrayList<>();
    
    private String mobility;
    private String escalationRisk;

    public EnemyActivity() {}

    public String getBehaviorType() { return behaviorType; }
    public List<String> getTargetPriority() { return targetPriority; }
    public List<String> getAttackPatterns() { return attackPatterns; }
    public String getMobility() { return mobility; }
    public String getEscalationRisk() { return escalationRisk; }

    public void setBehaviorType(String behaviorType) { this.behaviorType = behaviorType; }
    public void setMobility(String mobility) { this.mobility = mobility; }
    public void setEscalationRisk(String escalationRisk) { this.escalationRisk = escalationRisk; }

    public void setTargetPriority(List<String> targetPriority) { 
        this.targetPriority = targetPriority; 
    }
    public void setAttackPatterns(List<String> attackPatterns) { 
        this.attackPatterns = attackPatterns; 
    }

    public void setTargetPriority(String value) {
        if (this.targetPriority == null) {
            this.targetPriority = new ArrayList<>();
        }
        this.targetPriority.add(value);
    }
    public void setAttackPatterns(String value) {
        if (this.attackPatterns == null) {
            this.attackPatterns = new ArrayList<>();
        }
        this.attackPatterns.add(value);
    }
}