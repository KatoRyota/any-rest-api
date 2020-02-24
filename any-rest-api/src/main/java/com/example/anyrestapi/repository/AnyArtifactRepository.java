package com.example.anyrestapi.repository;

import com.example.anyrestapicore.bean.database.AnyArtifactRecordBean;
import com.example.anyrestapicore.bean.request.payload.AnyCalcRequestBean;
import com.example.anyrestapicore.model.common.AnyDataModel;
import com.example.anyrestapicore.model.common.partial.AnyPartialDataModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnyArtifactRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AnyArtifactRepository(
            NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AnyArtifactRecordBean> selectUsingIdList(
            List<String> idList) {

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

    public List<AnyArtifactRecordBean> selectUsingAnyArtifactRecordList(
            List<AnyCalcRequestBean> anyCalcRequestList) {

        String sql = "" +
                "select id, name, type " +
                "from any_artifact " +
                "where id in (:idList)";

        List<String> idList = new ArrayList<>();

        for (AnyCalcRequestBean anyCalcRequest : anyCalcRequestList) {
            idList.add(anyCalcRequest.getId());
        }

        return jdbcTemplate.query(
                sql,
                new MapSqlParameterSource()
                        .addValue("idList", idList),
                new BeanPropertyRowMapper<>(AnyArtifactRecordBean.class));
    }

    public int[] update(
            List<AnyDataModel> anyDataModelList) {

        String sql = "" +
                "update any_artifact " +
                "set name = :name, " +
                "    type = :type " +
                "where id = :id";

        List<AnyArtifactRecordBean> anyArtifactRecordList = new ArrayList<>();

        for (AnyDataModel anyDataModel : anyDataModelList) {
            for (AnyPartialDataModel anyPartialDataModel : anyDataModel.anyPartialDataModels) {
                AnyArtifactRecordBean anyArtifactRecord = new AnyArtifactRecordBean();
                anyArtifactRecord.setId(anyPartialDataModel.id);
                anyArtifactRecord.setName(anyPartialDataModel.name);
                anyArtifactRecord.setType(anyPartialDataModel.type);
                anyArtifactRecordList.add(anyArtifactRecord);
            }
        }

        return jdbcTemplate.batchUpdate(
                sql,
                SqlParameterSourceUtils.createBatch(anyArtifactRecordList.toArray()));
    }
}
