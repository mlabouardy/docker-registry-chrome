package com.labouardy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labouardy.model.API;
import com.labouardy.model.Protocol;
import com.labouardy.model.Registry;
import com.labouardy.model.RepositoryInfo;
import com.labouardy.model.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DockerRegistryChromeApplication.class)
@WebAppConfiguration
public class DockerRegistryV2ServiceTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	private Registry registry;
	
	private RepositoryInfo info;
	
	@Before
	public void setUp(){
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
		registry=new Registry();
		registry.setApi(API.V2);
		registry.setHostname("frpar-ccvrp-coreos-containers.corp.capgemini.com");
		registry.setPort(5010);
		registry.setProtocol(Protocol.HTTP);
		
		info=new RepositoryInfo();
		info.setRegistry(registry);
		info.setRepoName("ccvrp/drupal");
	}

	@Test
	public void getRepositories() throws JsonProcessingException, Exception {
		ObjectMapper mapper=new ObjectMapper();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v2/repositories")
				.contentType("application/json")
				.content(mapper.writeValueAsString(registry)))
		       .andExpect(MockMvcResultMatchers.status().isOk());
		       
	}
	
	@Test
	public void getTags() throws JsonProcessingException, Exception {
		ObjectMapper mapper=new ObjectMapper();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v2/repositories/tags")
				.contentType("application/json")
				.content(mapper.writeValueAsString(info)))
		       .andExpect(MockMvcResultMatchers.status().isOk());
		       
	}
}
