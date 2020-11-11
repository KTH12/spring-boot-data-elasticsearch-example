package com.springbootes001.es001;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private  ElasticsearchOperations elasticsearchOperations;

    @Autowired PersonRepository personRepository;

    // 생성자를 이용하여 ElasticsearchOperations 빈을 자동 주입한다.
    public TestController(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    // ES 클러스터에 엔티티를 저장한다.
    @PostMapping("/person")
    public String save(@RequestBody Person person) {
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(person.getClass());
        System.out.println(person.toString());
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery,indexCoordinates);
        System.out.println(documentId);
        return documentId;
    }


    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  Long id) {
        Person person = elasticsearchOperations
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }

    @GetMapping("/person/lastName/{lastName}")
    public List<Person> findById(@PathVariable("lastName")  String lastName) {
        return personRepository.findByLastName(lastName);
    }
}
