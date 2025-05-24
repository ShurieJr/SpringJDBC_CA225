package com.example.demo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    private final JdbcTemplate jdbc;
    String query = "";

    public UniversityService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //operations
    //get all universities
    public List<University> getAll() {
        query = "select * from university";
        return jdbc.query(query,
                new BeanPropertyRowMapper<>(University.class));
    }

    //get univ by id
    public University getUnivById(int id) {
        query = "select * from university where id = ?"; // ? placeholder
    return jdbc.queryForObject(query ,
            new Object[]{id},
            new BeanPropertyRowMapper<>(University.class));
    }

    //add university
    public void addUniversity(University university){
        query = "insert into university values(? , ? , ?)";
        jdbc.update(query , university.getId() , university.getName() , university.getLocation());
    }

    // update
    public void updateUniversity(University newUniversity, int id){
        query = "update university set name = ? , location=? where id=?";
        jdbc.update(query , newUniversity.getName(),newUniversity.getLocation(),id);
    }

    //delete
    public void deleteUniversity(int id){
        query = "delete from university where id =?";
        jdbc.update(query , id);
    }
}
