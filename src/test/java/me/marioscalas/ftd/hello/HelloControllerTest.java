/*******************************************************************************
 * Copyright (c) 2020 Mario Scalas
 * via F. De Pinedo, 1/a - http://www.marioscalas.me
 * All rights reserved.
 *
 * Contributors:
 *     Mario Scalas - initial API and implementation
 *******************************************************************************/
package me.marioscalas.ftd.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests per {@link HelloController}.
 */
@WebMvcTest( HelloController.class )
@TestPropertySource( properties = "ftd.feature.hello" )
class HelloControllerTest {

    @MockBean
    private HelloService helloService;

    @Autowired
    MockMvc mvc;

    @Test
    public void getSalute() throws Exception {
        // given
        final String saluteMessage = "Hello, world!";
        when(helloService.getSalute()).thenReturn( new Hello( saluteMessage ) );

        // when
        mvc.perform( get("/hello")
                .accept(MediaType.APPLICATION_JSON))
        // then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message", equalTo(saluteMessage)));
    }
}