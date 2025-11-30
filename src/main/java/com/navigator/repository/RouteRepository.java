package com.navigator.repository;

import com.navigator.domain.Route;
import org.apache.ibatis.annotations.Param;

public interface RouteRepository {
    void saveRoute(Route route);
    Route findById(@Param("id") Long id);
}