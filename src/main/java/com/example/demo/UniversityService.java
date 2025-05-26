package com.example.demo;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    private final JdbcTemplate jdbc;  //?

    private final NamedParameterJdbcTemplate namedParameter; //Parameter
    String query = "";

    public UniversityService(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameter) {
        this.jdbc = jdbc;
        this.namedParameter = namedParameter;
    }

    //operations
    //get all universities
    public List<University> getAll() {
        query = "select * from university";
//        return jdbc.query(query,
//                new BeanPropertyRowMapper<>(University.class));
        return namedParameter.query(query ,
                new BeanPropertyRowMapper<>(University.class));
    }

    //get univ by id
    public University getUnivById(int id) {
        //query = "select * from university where id = ?"; // ? placeholder
        query = "select * from university where id = :id"; // :Parameter
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("id" , id);
        return namedParameter.queryForObject(query ,
                source ,
                new BeanPropertyRowMapper<>(University.class)
                );
//    return jdbc.queryForObject(query ,
//            new Object[]{id},
//            new BeanPropertyRowMapper<>(University.class));
    }

    //add university
    public void addUniversity(University university){
       // query = "insert into university values(? , ? , ?)";
        query = "insert into university values(:id , :name , :location)";
        MapSqlParameterSource source = new MapSqlParameterSource()
                .addValue("name" , university.getName())
                .addValue("location" , university.getLocation())
                .addValue("id" , university.getId());
        namedParameter.update(query , source);

        //jdbc.update(query , university.getId() , university.getName() , university.getLocation());
    }

    // update
    public void updateUniversity(University newUniversity, int id){
//        query = "update university set name = ? , location=? where id=?";
//        jdbc.update(query , newUniversity.getName(),newUniversity.getLocation(),id);
//
        query = "update university set name = :name , location=:location where id=:id";
        MapSqlParameterSource source= new MapSqlParameterSource()
                .addValue("name",newUniversity.getName())
                .addValue("id",id)
                .addValue("location", newUniversity.getLocation());
        namedParameter.update(query,source);
}

    //delete
    public void deleteUniversity(int id){
        //query = "delete from university where id =?";
        query = "delete from university where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id" , id);
        namedParameter.update(query , parameterSource);
        //jdbc.update(query , id);
    }
}
