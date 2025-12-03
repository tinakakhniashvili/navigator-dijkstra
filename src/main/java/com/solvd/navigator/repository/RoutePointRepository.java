package com.solvd.navigator.repository;

import org.apache.ibatis.annotations.Param;

public interface RoutePointRepository {

    void saveRoutePoint(@Param("routeId") Long routeId,
                        @Param("pointId") Long pointId,
                        @Param("orderIndex") int orderIndex);

}