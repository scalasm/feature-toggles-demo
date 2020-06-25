/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas 
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved. 
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.hello;

import lombok.RequiredArgsConstructor;
import me.marioscalas.ftd.annotation.FeatureToggle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FeatureToggle( "hello" )
@RestController
@RequestMapping( "/hello" )
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping
    Hello getSalute() {
        return helloService.getSalute();
    }
}
