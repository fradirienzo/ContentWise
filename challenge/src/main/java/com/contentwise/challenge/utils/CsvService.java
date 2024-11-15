package com.contentwise.challenge.utils;

import com.contentwise.challenge.entity.User;
import com.contentwise.challenge.repository.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class CsvService {

    private final UserRepository userRepository;

    @Autowired
    public CsvService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Transactional
    public void loadCsvData(String filePath) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()))) {
            String[] nextLine;
            List<User> users = new ArrayList<>();
            reader.readNext(); // Skip the header row
            while ((nextLine = reader.readNext()) != null) {
                User user = new User();
                user.setId(Long.parseLong(nextLine[0])); // First column
                user.setUsername(nextLine[1]); // Second column
                users.add(user);
            }
            userRepository.saveAll(users);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the CSV file", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Error validating the CSV file", e);
        }
    }

}
