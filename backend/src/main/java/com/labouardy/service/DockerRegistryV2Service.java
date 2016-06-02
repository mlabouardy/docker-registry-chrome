package com.labouardy.service;

import java.net.URI;
import java.net.URISyntaxException;
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
import com.labouardy.api.DockerRemoteAPI;
import com.labouardy.api.RegistryAPI;
import com.labouardy.api.RestConfig;
import com.labouardy.model.Message;
import com.labouardy.model.Registry;
import com.labouardy.model.RepositoryInfo;
import com.labouardy.model.RepositoryV2;
import com.labouardy.model.Tag;
import com.labouardy.model.URL;

@CrossOrigin
@Component
@RequestMapping("/v2/repositories")
@RestController
public class DockerRegistryV2Service implements RegistryAPI{

	private RestTemplate restTemplate;
	
	private DockerRemoteAPI dockerRemote;
	
	public DockerRegistryV2Service(){
		restTemplate=new RestTemplate();
		dockerRemote=new DockerRemoteAPI();
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	@Override
	public RepositoryV2 repositories(Registry registry) {
		URL url=new URL()
				.setProtocol(registry.getProtocol())
				.setHostname(registry.getHostname())
				.setPort(registry.getPort())
				.build();
		String uri = dockerRemote.repositoriesResourceV2(url.getURI());
		if(registry.isProtectedRegistry()){
			ResponseEntity<RepositoryV2> entity = null;
			try {
				entity = restTemplate.exchange(new URI(uri), HttpMethod.GET, new HttpEntity<String>(RestConfig.basicAuth(registry.getUsername(), registry.getPassword())), RepositoryV2.class);
			} catch (RestClientException | URISyntaxException e) {
				
			}
			return entity.getBody();
		}
		RepositoryV2 data =restTemplate.getForObject(uri, RepositoryV2.class);
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
		String uri = dockerRemote.pingRegistryV2(url.getURI());
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
	public Tag tags(RepositoryInfo info) {
		Registry registry = info.getRegistry();
		URL url=new URL()
				.setProtocol(registry.getProtocol())
				.setHostname(registry.getHostname())
				.setPort(registry.getPort())
				.build();
		String uri = dockerRemote.tagsResourceV2(url.getURI(), info.getRepoName());
		if(registry.isProtectedRegistry()){
			ResponseEntity<Tag> entity = null;
			try {
				entity = restTemplate.exchange(new URI(uri), HttpMethod.GET, new HttpEntity<String>(RestConfig.basicAuth(registry.getUsername(), registry.getPassword())), Tag.class);
			} catch (RestClientException | URISyntaxException e) {
				
			}
			return entity.getBody();
		}
		Tag result =restTemplate.getForObject(uri, Tag.class);
		return result;
	}
}
