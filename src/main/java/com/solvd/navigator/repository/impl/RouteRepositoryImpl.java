package com.solvd.navigator.repository.impl;

import com.solvd.navigator.config.MyBatisConfig;
import com.solvd.navigator.domain.Route;
import com.solvd.navigator.repository.RouteRepository;
import org.apache.ibatis.session.SqlSession;

public class RouteRepositoryImpl implements RouteRepository {

    @Override
    public void saveRoute(Route route) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            RouteRepository mapper = session.getMapper(RouteRepository.class);
            mapper.saveRoute(route);
        }
    }

    @Override
    public Route findById(Long id) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            RouteRepository mapper = session.getMapper(RouteRepository.class);
            return mapper.findById(id);
        }
    }
}
