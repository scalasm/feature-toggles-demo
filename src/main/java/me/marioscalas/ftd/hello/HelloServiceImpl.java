/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas 
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved. 
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.hello;

import me.marioscalas.ftd.annotation.FeatureToggle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@FeatureToggle( "hello" )
@Service
@ConditionalOnProperty( value = "ftd.feature.hello", matchIfMissing = true, havingValue = "true" )
public class HelloServiceImpl implements HelloService {
    @Override
    public Hello getSalute() {
        return new Hello( "Hello World!" );
    }
}
