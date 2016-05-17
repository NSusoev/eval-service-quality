package esq.core;

import esq.application.model.ESQSettingsProfile;
import esq.application.model.ServiceQualitySurvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс расчёта интегральной оценки качества
 */


public class ESQCalculator {

    private ESQSettingsProfile settingsProfile;

    public ESQCalculator(ESQSettingsProfile settingsProfile) {
        this.settingsProfile = settingsProfile;
    }

}
