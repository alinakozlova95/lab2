/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;
import model.*;
/**
 *
 * @author alina
 */
public interface MissionBuilder {
    MissionBuilder reset();

    MissionBuilder setMissionId(String id);
    MissionBuilder setDate(String date);
    MissionBuilder setLocation(String location);
    MissionBuilder setOutcome(String outcome);
    MissionBuilder setDamageCost(long cost);
    MissionBuilder setComment(String comment);
    MissionBuilder setNote(String note);

    MissionBuilder setCurse(Curse curse);

    MissionBuilder addSorcerer(Sorcerer sorcerer);
    MissionBuilder addTechnique(Technique technique);

    MissionBuilder setEconomicAssessment(EconomicAssessment assessment);
    MissionBuilder setCivilianImpact(CivilianImpact impact);
    MissionBuilder setEnemyActivity(EnemyActivity activity);
    MissionBuilder setEnvironmentConditions(EnvironmentConditions conditions);
    MissionBuilder addTimelineEvent(TimelineEvent event);

    MissionBuilder addOperationTag(String tag);
    MissionBuilder addSupportUnit(String unit);
    MissionBuilder addRecommendation(String recommendation);
    MissionBuilder addArtifactRecovered(String artifact);
    MissionBuilder addEvacuationZone(String zone);
    MissionBuilder addStatusEffect(String effect);

    MissionBuilder putExtra(String key, Object value);

    Mission build();
}