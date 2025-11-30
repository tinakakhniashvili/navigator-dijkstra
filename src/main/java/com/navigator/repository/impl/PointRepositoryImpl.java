package com.navigator.repository.impl;

import com.navigator.config.MyBatisConfig;
import com.navigator.domain.Point;
import com.navigator.repository.PointRepository;
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
