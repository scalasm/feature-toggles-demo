/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved.
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.otherfeature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( OtherFeatureController.class )
@TestPropertySource( properties = "ftd.feature.other" )
class OtherFeatureControllerTest {

    @Autowired
    MockMvc mvc;

    @Test void getCoolFeature() throws Exception {
        // given

        // when
        mvc.perform( get("/other")
                .accept( MediaType.APPLICATION_JSON))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", equalTo("Yes, I'm cool!")));
    }
}