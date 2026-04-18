/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
/**
 *
 * @author alina
 */
public class Mission {
    private String missionId;
    private String date;
    private String location;
    private String outcome;
    private long damageCost;
    private String comment;
    private String note;

    private Curse curse;

    private List<Sorcerer> sorcerers = new ArrayList<>();
    private List<Technique> techniques = new ArrayList<>();

    private EconomicAssessment economicAssessment;
    private CivilianImpact civilianImpact;
    private EnemyActivity enemyActivity;
    private EnvironmentConditions environmentConditions;
    private List<TimelineEvent> operationTimeline = new ArrayList<>();

    private List<String> operationTags = new ArrayList<>();
    private List<String> supportUnits = new ArrayList<>();
    private List<String> recommendations = new ArrayList<>();
    private List<String> artifactsRecovered = new ArrayList<>();
    private List<String> evacuationZones = new ArrayList<>();
    private List<String> statusEffects = new ArrayList<>();

    private Map<String, Object> extras = new HashMap<>();

    public Mission() {}


    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public long getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(long damageCost) {
        this.damageCost = damageCost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Curse getCurse() {
        return curse;
    }

    public void setCurse(Curse curse) {
        this.curse = curse;
    }

    public List<Sorcerer> getSorcerers() {
        return sorcerers;
    }

    public void setSorcerers(List<Sorcerer> sorcerers) {
        this.sorcerers = sorcerers;
    }

    public List<Technique> getTechniques() {
        return techniques;
    }

    public void setTechniques(List<Technique> techniques) {
        this.techniques = techniques;
    }

    public EconomicAssessment getEconomicAssessment() {
        return economicAssessment;
    }

    public void setEconomicAssessment(EconomicAssessment economicAssessment) {
        this.economicAssessment = economicAssessment;
    }

    public CivilianImpact getCivilianImpact() {
        return civilianImpact;
    }

    public void setCivilianImpact(CivilianImpact civilianImpact) {
        this.civilianImpact = civilianImpact;
    }

    public EnemyActivity getEnemyActivity() {
        return enemyActivity;
    }

    public void setEnemyActivity(EnemyActivity enemyActivity) {
        this.enemyActivity = enemyActivity;
    }

    public EnvironmentConditions getEnvironmentConditions() {
        return environmentConditions;
    }

    public void setEnvironmentConditions(EnvironmentConditions environmentConditions) {
        this.environmentConditions = environmentConditions;
    }

    public List<TimelineEvent> getOperationTimeline() {
        return operationTimeline;
    }

    public void setOperationTimeline(List<TimelineEvent> operationTimeline) {
        this.operationTimeline = operationTimeline;
    }

    public List<String> getOperationTags() {
        return operationTags;
    }

    public void setOperationTags(List<String> operationTags) {
        this.operationTags = operationTags;
    }

    public List<String> getSupportUnits() {
        return supportUnits;
    }

    public void setSupportUnits(List<String> supportUnits) {
        this.supportUnits = supportUnits;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }

    public List<String> getArtifactsRecovered() {
        return artifactsRecovered;
    }

    public void setArtifactsRecovered(List<String> artifactsRecovered) {
        this.artifactsRecovered = artifactsRecovered;
    }

    public List<String> getEvacuationZones() {
        return evacuationZones;
    }

    public void setEvacuationZones(List<String> evacuationZones) {
        this.evacuationZones = evacuationZones;
    }

    public List<String> getStatusEffects() {
        return statusEffects;
    }

    public void setStatusEffects(List<String> statusEffects) {
        this.statusEffects = statusEffects;
    }

    public void putExtra(String key, Object value) {
        this.extras.put(key, value);
    }

    public Object getExtra(String key) {
        return this.extras.get(key);
    }

    public Map<String, Object> getExtras() {
        return new HashMap<>(extras);
    }

    public boolean hasExtra(String key) {
        return this.extras.containsKey(key);
    }

    public void addSorcerer(Sorcerer s) {
        if (this.sorcerers == null) {
            this.sorcerers = new ArrayList<>();
        }
        this.sorcerers.add(s);
    }

    public void addTechnique(Technique t) {
        if (this.techniques == null) {
            this.techniques = new ArrayList<>();
        }
        this.techniques.add(t);
    }

    public void addTimelineEvent(TimelineEvent event) {
        if (this.operationTimeline == null) {
            this.operationTimeline = new ArrayList<>();
        }
        this.operationTimeline.add(event);
    }
}
