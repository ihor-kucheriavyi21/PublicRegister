package com.example.publicregister.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class DataService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DataService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public <T> void saveDataFromFile(MultipartFile file, Class<T> clazz) throws IOException {
        InputStream inputStream = file.getInputStream();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory() && zipEntry.getName().endsWith(".json")) {
                    List<Document> documents = readJsonData(zipInputStream);
                    mongoTemplate.insert(documents, clazz);
                }
                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    public <T> void removeAllDataFromCollection(Class<T> clazz) {
        mongoTemplate.dropCollection(clazz);
    }

    private List<Document> readJsonData(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Document> documents = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(inputStream);
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                Document document = Document.parse(node.toString());
                documents.add(document);
            }
        } else {
            Document document = Document.parse(rootNode.toString());
            documents.add(document);
        }
        return documents;
    }
}

