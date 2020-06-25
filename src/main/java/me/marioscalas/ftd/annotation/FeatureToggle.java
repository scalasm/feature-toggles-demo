/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas 
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved. 
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;

import static java.lang.Boolean.parseBoolean;

/**
 * Tag Spring Beans (es., @Controller, @Service, ...) so that they can be activated / deactivated as part of a single logical feature
 * To streamline things we use a conventional namespace <code>ftd.feature.</code>.
 * <br/>
 * In example:
 * <code>
 * ftd:
 *   feature:
 *     hello: false
 * </code>
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(FeatureToggle.FeatureToggleCondition.class )
public @interface FeatureToggle {
    /**
     * Feature's name (without suffix).
     * @return Feature's name (without suffix).
     */
    String value();

    /**
     * Implementation for the activate / deactivation of this feature toggle. By design, a feature is enabled by default
     * unless explicitly disabled.
     */
    class FeatureToggleCondition implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata metadata) {
            MergedAnnotation<FeatureToggle> annotation = metadata.getAnnotations().get(FeatureToggle.class);
            String featureName = annotation.getString("value");

            String fqdnFeatureName = "ftd.feature." + featureName;
            String propertyValue = conditionContext.getEnvironment().getProperty(fqdnFeatureName);

            boolean missing = propertyValue == null;
            boolean definedWithoutValue = "".equals( propertyValue );
            boolean explicitlyEnabled = parseBoolean( propertyValue );
            boolean featureIsEnabled = (missing || definedWithoutValue) || explicitlyEnabled;

            return featureIsEnabled;
        }
    }
}