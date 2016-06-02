package com.labouardy.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labouardy.api.*;
import com.labouardy.model.*;

@CrossOrigin
@Component
@RequestMapping("/v1/repositories")
@RestController
public class DockerRegistryV1Service implements RegistryAPI{
	
	private RestTemplate restTemplate;
	
	private DockerRemoteAPI dockerRemote;
	
	public DockerRegistryV1Service(){
		restTemplate=new RestTemplate();
		dockerRemote=new DockerRemoteAPI();
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	@Override
	public Repository repositories(Registry registry) {
		URL url=new URL()
				.setProtocol(registry.getProtocol())
				.setHostname(registry.getHostname())
				.setPort(registry.getPort())
				.build();
		String uri = dockerRemote.repositoriesResourceV1(url.getURI());
		if(registry.isProtectedRegistry()){
			ResponseEntity<RepositoryV1> entity = null;
			try {
				entity = restTemplate.exchange(new URI(uri), HttpMethod.POST, new HttpEntity<String>(RestConfig.basicAuth(registry.getUsername(), registry.getPassword())), RepositoryV1.class);
			} catch (RestClientException | URISyntaxException e1) {
				
			}
			return entity.getBody();
		}
		RepositoryV1 data =restTemplate.getForObject(uri, RepositoryV1.class);
		return data;
	}

	@RequestMapping(value="ping", method=RequestMethod.POST, produces="application/json")
	@Override
	public Message ping(Registry registry) {
		URL url=new URL()
					.setProtocol(registry.getProtocol())
					.setHostname(registry.getHostname())
					.setPort(registry.getPort())
					.build();
		String uri = dockerRemote.pingRegistryV1(url.getURI());
		HttpEntity<String> request=null;
		if(registry.isProtectedRegistry()){
			request = new HttpEntity<String>(RestConfig.basicAuth(registry.getUsername(), registry.getPassword()));
		}
		ResponseEntity<String> out = null;
		try {
			out = restTemplate.exchange(new URI(uri), HttpMethod.GET, request, String.class);
		} catch (RestClientException | URISyntaxException e1) {
			
		}
		if(out.getStatusCode() == HttpStatus.OK)
			return new Message(200, "Success");
		else
			return new Message(400, "Fail");
	}

	@RequestMapping(value="tags",method=RequestMethod.POST, produces="application/json")
	public Collection<String> tags(RepositoryInfo info) {
		Registry registry = info.getRegistry();
		URL url=new URL()
				.setProtocol(registry.getProtocol())
				.setHostname(registry.getHostname())
				.setPort(registry.getPort())
				.build();
		String uri = dockerRemote.tagsResourceV1(url.getURI(), info.getRepoName());
		if(registry.isProtectedRegistry()){
			ResponseEntity<String> entity = null;
			try {
				entity = restTemplate.exchange(new URI(uri), HttpMethod.GET, new HttpEntity<String>(RestConfig.basicAuth(registry.getUsername(), registry.getPassword())), String.class);
			} catch (RestClientException | URISyntaxException e1) {
				
			}
			String tags =entity.getBody();
			ObjectMapper mapper=new ObjectMapper();
			TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
			HashMap<String, String> t=null;
			try {
				t = mapper.readValue(tags, typeRef);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return t.keySet();
		}
		String tags =restTemplate.getForObject(uri, String.class);
		ObjectMapper mapper=new ObjectMapper();
		TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
		HashMap<String, String> t=null;
		try {
			t = mapper.readValue(tags, typeRef);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t.keySet();
	}
}
