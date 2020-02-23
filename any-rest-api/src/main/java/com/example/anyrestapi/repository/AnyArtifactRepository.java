package com.example.anyrestapi.repository;

import com.example.anyrestapicore.bean.database.AnyArtifactRecordBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnyArtifactRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AnyArtifactRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AnyArtifactRecordBean> select(List<String> idList) {
        String sql = "" +
                "select id, name, type " +
                "from any_artifact " +
                "where id in (:idList)";

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("idList", idList),
                new BeanPropertyRowMapper<>(AnyArtifactRecordBean.class));
    }
}
