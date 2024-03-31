package org.pantasoft.pantastore;

import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.pantasoft.pantastore.controller.dto.CategoryRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnityTestUtils {

    private final Faker faker = Faker.instance();

    private final EasyRandom easyRandom = new EasyRandom();

    public UUID givenUUID() {
        return UUID.randomUUID();
    }

    public <T> T givenOneObjectOf(Class<T> type) {
        return easyRandom.nextObject(type);
    }

    public <T> List<T> givenListOf(Class<T> type, int size) {
        return easyRandom.objects(type, size).collect(Collectors.toList());
    }

    public String asJsonString(final Object obj) {
        return new com.fasterxml.jackson.databind.ObjectMapper()
                .valueToTree(obj).toString();
    }
}
