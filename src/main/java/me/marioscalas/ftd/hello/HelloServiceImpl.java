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
import org.springframework.stereotype.Service;

@FeatureToggle( "hello" )
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public Hello getSalute() {
        return new Hello( "Hello World!" );
    }
}
