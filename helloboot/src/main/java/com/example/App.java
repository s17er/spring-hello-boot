package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Item;
import com.example.repository.ItemRepository;

/**
 * Hello world!
 *
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
public class App extends SpringBootServletInitializer {
	
	@Autowired
	ItemRepository itemRepository;
	
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	return application.sources(App.class);
    }

    @RequestMapping("/")
    public String home() {
    	StringBuffer sb = new StringBuffer();
    	Item item = this.itemRepository.save(new Item(null, "test"));
    	sb.append("insert new item. <br/>");
    	List<Item> items = this.itemRepository.findAll();
    	if(items != null) {
    		for(Item i: items) {
    			sb.append("find item. [" + i.getContent() + "] <br>");
    		}
    	}
        return sb.toString();
    }
}
