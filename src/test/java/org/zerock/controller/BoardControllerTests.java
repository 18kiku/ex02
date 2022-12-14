package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:web/WEB-INF/applicationContext.xml")
@Log4j
public class BoardControllerTests {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    /*@Test
    public void testList() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }*/

    @Test
    public void testListWithPaging() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                                .param("pageNum", "2")
                                .param("amount", "50"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }

    @Test
    public void testRegister() throws Exception {

        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title", "test title")
                .param("content", "test content")
                .param("writer", "test writer")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders
                        .get("/board/get")
                        .param("bno", "2"))
                .andReturn()
                .getModelAndView().getModelMap()
        );
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders
                .post("/board/modify")
                .param("bno", "1")
                .param("title", "update title")
                .param("content", "update content")
                .param("writer", "update writer"))
                .andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }
}