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
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Hello {
    String message;
}
