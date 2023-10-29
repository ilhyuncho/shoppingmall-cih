package com.cih.shoppingmallcih.common.test.validator;


import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PasswordRuleValidator implements ConstraintValidator<Password, String> {
                                                         //<1,2> : 첫번째 인자는 커스텀 밸리데이터 로직을 적용하게 해주는 애너테이션
                                                         //        두번째 인자는 커스텀 애너테이션을 적용해야 하는 데이터 타입

    private static final int MIN_COMPLEX_RULES = 2;
    private static final int MAX_REPETITIVE_CHARS = 3;
    private static final int MIN_SPECIAL_CASE_CHARS = 1;
    private static final int MIN_UPPER_CASE_CHARS = 1;
    private static final int MIN_LOWER_CASE_CHARS = 1;
    private static final int MIN_DIGIT_CASE_CHARS = 1;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        List<Rule> passwordRules = new ArrayList<>();
        passwordRules.add(new LengthRule(8,30));

        CharacterCharacteristicsRule characterCharacteristicsRule =
                new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
                        new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
                        new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS)
                        );

        passwordRules.add(characterCharacteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS));  // 동일한 문자 반복

        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);

        return ruleResult.isValid();
    }

}
