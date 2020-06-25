/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas 
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved. 
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.otherfeature;

import lombok.RequiredArgsConstructor;
import me.marioscalas.ftd.annotation.FeatureToggle;
import me.marioscalas.ftd.hello.Hello;
import me.marioscalas.ftd.hello.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller only exists in order to show how feature toggling works.
 */
@FeatureToggle( "other" )
@RestController
@RequestMapping( "/other" )
@RequiredArgsConstructor
public class OtherFeatureController {

    @GetMapping SomethingCool getCoolFeature() {
        return new SomethingCool( "Yes, I'm cool!" );
    }
}
