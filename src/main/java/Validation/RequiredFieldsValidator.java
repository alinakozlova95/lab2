/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validation;
import model.Curse;
import model.Mission;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alina
 */
public class RequiredFieldsValidator implements Validator {

    @Override
    public List<String> validate(Mission mission) {
        List<String> errors = new ArrayList<>();

        if (isNullOrEmpty(mission.getMissionId())) {
            errors.add("missionId is required");
        }

        if (isNullOrEmpty(mission.getDate())) {
            errors.add("date is required");
        }

        if (isNullOrEmpty(mission.getLocation())) {
            errors.add("location is required");
        }

        if (isNullOrEmpty(mission.getOutcome())) {
            errors.add("outcome is required");
        }

        Curse curse = mission.getCurse();
        if (curse == null) {
            errors.add("curse block is required");
        } else {
            if (isNullOrEmpty(curse.getName())) {
                errors.add("curse.name is required");
            }
            if (isNullOrEmpty(curse.getThreatLevel())) {
                errors.add("curse.threatLevel is required");
            }
        }

        if (mission.getSorcerers() != null) {
            for (int i = 0; i < mission.getSorcerers().size(); i++) {
                var s = mission.getSorcerers().get(i);
                if (isNullOrEmpty(s.getName())) {
                    errors.add("sorcerer[" + i + "].name is required");
                }
                if (isNullOrEmpty(s.getRank())) {
                    errors.add("sorcerer[" + i + "].rank is required");
                }
            }
        }

        if (mission.getTechniques() != null) {
            for (int i = 0; i < mission.getTechniques().size(); i++) {
                var t = mission.getTechniques().get(i);
                if (isNullOrEmpty(t.getName())) {
                    errors.add("technique[" + i + "].name is required");
                }
                if (isNullOrEmpty(t.getType())) {
                    errors.add("technique[" + i + "].type is required");
                }
                if (isNullOrEmpty(t.getOwner())) {
                    errors.add("technique[" + i + "].owner is required");
                }
            }
        }

        if (mission.getOperationTimeline() != null) {
            for (int i = 0; i < mission.getOperationTimeline().size(); i++) {
                var event = mission.getOperationTimeline().get(i);
                if (isNullOrEmpty(event.getTimestamp())) {
                    errors.add("timeline[" + i + "].timestamp is required");
                }
                if (isNullOrEmpty(event.getType())) {
                    errors.add("timeline[" + i + "].type is required");
                }
                if (isNullOrEmpty(event.getDescription())) {
                    errors.add("timeline[" + i + "].description is required");
                }
            }
        }

        return errors;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
