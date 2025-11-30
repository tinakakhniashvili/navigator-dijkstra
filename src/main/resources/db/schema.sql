CREATE TABLE point (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    x DOUBLE NOT NULL,
    y DOUBLE NOT NULL
);

CREATE TABLE route (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    total_time DOUBLE NOT NULL
);

CREATE TABLE route_point (
    route_id BIGINT NOT NULL,
    point_id BIGINT NOT NULL,
    order_index INT NOT NULL,

    PRIMARY KEY (route_id, order_index),

    FOREIGN KEY (route_id) REFERENCES route(id),
    FOREIGN KEY (point_id) REFERENCES point(id)
);
