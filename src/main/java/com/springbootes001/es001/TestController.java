package com.springbootes001.es001;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private final ElasticsearchOperations elasticsearchOperations;

    final PersonRepository personRepository;

    // 생성자를 이용하여 ElasticsearchOperations 빈을 자동 주입한다.
    public TestController(ElasticsearchOperations elasticsearchOperations, PersonRepository personRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.personRepository = personRepository;
    }

    // ES 클러스터에 엔티티를 저장한다.
    @PostMapping("/person")
    public String save(@RequestBody Person person) {
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(person.getClass());
        assert person.getId() != null;
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        return elasticsearchOperations.index(indexQuery,indexCoordinates);
    }

    // ES 클러스터에 엔티티를 저장한다.
    @PostMapping("/user")
    public User save(@RequestBody User user) {
        System.out.println(user.toString());
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(user.getClass());
        assert user.getId() != null;
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(user.getId().toString())
                .withObject(user)
                .build();
        elasticsearchOperations.index(indexQuery,indexCoordinates);
        return user;
    }


    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id")  Long id) {
        return personRepository.findById(id);
    }

    @GetMapping("/person/lastName/{lastName}")
    public List<Person> findById(@PathVariable("lastName")  String lastName) {
        return personRepository.findByLastName(lastName);
    }
}
