package com.solvd.navigator.repository.impl;

import com.solvd.navigator.config.MyBatisConfig;
import com.solvd.navigator.repository.RoutePointRepository;
import org.apache.ibatis.session.SqlSession;

public class RoutePointRepositoryImpl implements RoutePointRepository {

    @Override
    public void saveRoutePoint(Long routeId, Long pointId, int orderIndex) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession(true)) {
            RoutePointRepository mapper = session.getMapper(RoutePointRepository.class);
            mapper.saveRoutePoint(routeId, pointId, orderIndex);
        }
    }
}
