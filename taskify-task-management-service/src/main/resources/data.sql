INSERT INTO country(alpha2_code, name) VALUES
(
    'AD',
    'Andorra'
),
(
    'AZ',
    'Azerbaijan'
);


INSERT INTO agency(name, alias, tag, timezone, status, created_on, updated_on, cdn_id, fk_country_id,fk_price_group_id) VALUES
(
    'Dev Agency',
    'devagency',
    'Devel',
    'UTC',
    'ACTIVE',
    '2020-09-18 11:05:09.341000',
    '2020-09-18 11:05:09.341000',
    1,
    1,
    1
),
(
    'Test OSM',
    'test-osm',
    'tag',
    'UTC+4',
    'ACTIVE',
    '2020-10-21 11:52:50.649000',
    '2020-10-21 11:52:50.650000',
    1,
    1,
    2
),
(
    'Wandafi',
    'wandafi',
    'tag',
    'UTC+4',
    'INACTIVE',
    '2020-09-21 11:52:50.649000',
    '2020-09-21 11:52:50.650000',
    1,
    1,
    3
),
(
    'Safron',
    'safron',
    'tag',
    'UTC+4',
    'INACTIVE',
    '2020-09-21 11:52:50.649000',
    '2020-09-21 11:52:50.650000',
    1,
    2,
    4
),
(
    'Coca cola',
    'cola',
    'tag',
    'UTC+4',
    'INACTIVE',
    '2020-09-21 11:52:50.649000',
    '2020-09-21 11:52:50.650000',
    1,
    2,
    5
),
(
    'Mcdonalds',
    'mcdonalds',
    'tag',
    'UTC+4',
    'INACTIVE',
    '2020-09-21 11:52:50.649000',
    '2020-09-21 11:52:50.650000',
    1,
    2,
    6
);

INSERT INTO agency_venue_collection(fk_agency_id,venue_id,assigned_on) VALUES
(
    1,
    1,
    '2020-09-18 11:05:09.341000'

),
(
    1,
    2,
    '2020-10-21 11:50:50.649000'
),
(
    2,
    3,
    '2020-10-21 11:52:50.649000'
);
