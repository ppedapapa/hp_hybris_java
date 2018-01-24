package com.shaklee.rulesets.healthQuestionaire.components;

import org.springframework.stereotype.Component;

import com.shaklee.promo.Condition;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;
import com.shaklee.shared.validation.InputValidationException;

@Component
public class BMI extends AbstractComponent<Questions> implements Condition<PromoRequest<Questions>> {

    public Integer min, max;

    @Override
    public boolean evaluate(PromoRequest<Questions> q) throws InputValidationException {
        if (min == null && max == null)
            throw new InputValidationException("min or max cannot be null");

        if (q.request.weight_lbs == null || q.request.height_inches == null)
            return false;

        float bmi = bmi(q.request);

        if (min != null && bmi < min)
            return false;

        if (max != null && bmi >= max)
            return false;

        return true;
    }

    public static float bmi(Questions request) {
        float weight = request.weight_lbs * 0.453592f;
        float height = request.height_inches * 0.0254f;
        float bmi = weight / (height * height);
        return bmi;
    }
}
