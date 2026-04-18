/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;
import model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alina
 */
public class MissionBuilderImpl implements MissionBuilder {
    
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

    @Override
    public MissionBuilder reset() {
        missionId = null;
        date = null;
        location = null;
        outcome = null;
        damageCost = 0;
        comment = null;
        note = null;
        curse = null;
        sorcerers.clear();
        techniques.clear();
        economicAssessment = null;
        civilianImpact = null;
        enemyActivity = null;
        environmentConditions = null;
        operationTimeline.clear();
        operationTags.clear();
        supportUnits.clear();
        recommendations.clear();
        artifactsRecovered.clear();
        evacuationZones.clear();
        statusEffects.clear();
        extras.clear();
        return this;
    }

    @Override
    public MissionBuilder setMissionId(String id) {
        this.missionId = id;
        return this;
    }

    @Override
    public MissionBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public MissionBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    @Override
    public MissionBuilder setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    @Override
    public MissionBuilder setDamageCost(long cost) {
        this.damageCost = cost;
        return this;
    }

    @Override
    public MissionBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public MissionBuilder setNote(String note) {
        this.note = note;
        return this;
    }

    @Override
    public MissionBuilder setCurse(Curse curse) {
        this.curse = curse;
        return this;
    }

    @Override
    public MissionBuilder addSorcerer(Sorcerer sorcerer) {
        this.sorcerers.add(sorcerer);
        return this;
    }

    @Override
    public MissionBuilder addTechnique(Technique technique) {
        this.techniques.add(technique);
        return this;
    }

    @Override
    public MissionBuilder setEconomicAssessment(EconomicAssessment assessment) {
        this.economicAssessment = assessment;
        return this;
    }

    @Override
    public MissionBuilder setCivilianImpact(CivilianImpact impact) {
        this.civilianImpact = impact;
        return this;
    }

    @Override
    public MissionBuilder setEnemyActivity(EnemyActivity activity) {
        this.enemyActivity = activity;
        return this;
    }

    @Override
    public MissionBuilder setEnvironmentConditions(EnvironmentConditions conditions) {
        this.environmentConditions = conditions;
        return this;
    }

    @Override
    public MissionBuilder addTimelineEvent(TimelineEvent event) {
        this.operationTimeline.add(event);
        return this;
    }

    @Override
    public MissionBuilder addOperationTag(String tag) {
        this.operationTags.add(tag);
        return this;
    }

    @Override
    public MissionBuilder addSupportUnit(String unit) {
        this.supportUnits.add(unit);
        return this;
    }

    @Override
    public MissionBuilder addRecommendation(String recommendation) {
        this.recommendations.add(recommendation);
        return this;
    }

    @Override
    public MissionBuilder addArtifactRecovered(String artifact) {
        this.artifactsRecovered.add(artifact);
        return this;
    }

    @Override
    public MissionBuilder addEvacuationZone(String zone) {
        this.evacuationZones.add(zone);
        return this;
    }

    @Override
    public MissionBuilder addStatusEffect(String effect) {
        this.statusEffects.add(effect);
        return this;
    }

    @Override
    public MissionBuilder putExtra(String key, Object value) {
        this.extras.put(key, value);
        return this;
    }

    @Override
    public Mission build() {
        Mission mission = new Mission();

        mission.setMissionId(missionId);
        mission.setDate(date);
        mission.setLocation(location);
        mission.setOutcome(outcome);
        mission.setDamageCost(damageCost);
        mission.setComment(comment);
        mission.setNote(note);

        mission.setCurse(curse);

        mission.setSorcerers(new ArrayList<>(sorcerers));
        mission.setTechniques(new ArrayList<>(techniques));

        mission.setEconomicAssessment(economicAssessment);
        mission.setCivilianImpact(civilianImpact);
        mission.setEnemyActivity(enemyActivity);
        mission.setEnvironmentConditions(environmentConditions);
        mission.setOperationTimeline(new ArrayList<>(operationTimeline));

        mission.setOperationTags(new ArrayList<>(operationTags));
        mission.setSupportUnits(new ArrayList<>(supportUnits));
        mission.setRecommendations(new ArrayList<>(recommendations));
        mission.setArtifactsRecovered(new ArrayList<>(artifactsRecovered));
        mission.setEvacuationZones(new ArrayList<>(evacuationZones));
        mission.setStatusEffects(new ArrayList<>(statusEffects));

        for (Map.Entry<String, Object> entry : extras.entrySet()) {
            mission.putExtra(entry.getKey(), entry.getValue());
        }

        return mission;
    }

    public static MissionBuilderImpl fromMission(Mission mission) {
        MissionBuilderImpl builder = new MissionBuilderImpl();

        if (mission == null) {
            return builder;
        }

        builder.setMissionId(mission.getMissionId())
               .setDate(mission.getDate())
               .setLocation(mission.getLocation())
               .setOutcome(mission.getOutcome())
               .setDamageCost(mission.getDamageCost())
               .setComment(mission.getComment())
               .setNote(mission.getNote())
               .setCurse(mission.getCurse())
               .setEconomicAssessment(mission.getEconomicAssessment())
               .setCivilianImpact(mission.getCivilianImpact())
               .setEnemyActivity(mission.getEnemyActivity())
               .setEnvironmentConditions(mission.getEnvironmentConditions());

        if (mission.getSorcerers() != null) {
            for (Sorcerer s : mission.getSorcerers()) {
                builder.addSorcerer(s);
            }
        }

        if (mission.getTechniques() != null) {
            for (Technique t : mission.getTechniques()) {
                builder.addTechnique(t);
            }
        }

        if (mission.getOperationTimeline() != null) {
            for (TimelineEvent e : mission.getOperationTimeline()) {
                builder.addTimelineEvent(e);
            }
        }

        if (mission.getOperationTags() != null) {
            for (String tag : mission.getOperationTags()) {
                builder.addOperationTag(tag);
            }
        }

        if (mission.getSupportUnits() != null) {
            for (String unit : mission.getSupportUnits()) {
                builder.addSupportUnit(unit);
            }
        }

        if (mission.getRecommendations() != null) {
            for (String rec : mission.getRecommendations()) {
                builder.addRecommendation(rec);
            }
        }

        if (mission.getArtifactsRecovered() != null) {
            for (String art : mission.getArtifactsRecovered()) {
                builder.addArtifactRecovered(art);
            }
        }

        if (mission.getEvacuationZones() != null) {
            for (String zone : mission.getEvacuationZones()) {
                builder.addEvacuationZone(zone);
            }
        }

        if (mission.getStatusEffects() != null) {
            for (String effect : mission.getStatusEffects()) {
                builder.addStatusEffect(effect);
            }
        }

        if (mission.getExtras() != null) {
            for (Map.Entry<String, Object> entry : mission.getExtras().entrySet()) {
                builder.putExtra(entry.getKey(), entry.getValue());
            }
        }

        return builder;
    }
}
