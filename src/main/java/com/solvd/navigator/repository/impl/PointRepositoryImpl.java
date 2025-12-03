package com.solvd.navigator.repository.impl;

import com.solvd.navigator.repository.config.MyBatisConfig;
import com.solvd.navigator.domain.Point;
import com.solvd.navigator.repository.PointRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PointRepositoryImpl implements PointRepository {

    @Override
    public void savePoints(List<Point> points) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            PointRepository mapper = session.getMapper(PointRepository.class);
            mapper.savePoints(points);
        }
    }

    @Override
    public List<Point> findAll() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            PointRepository mapper = session.getMapper(PointRepository.class);
            return mapper.findAll();
        }
    }
}
