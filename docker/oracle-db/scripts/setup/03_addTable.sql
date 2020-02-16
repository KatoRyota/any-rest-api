CONNECT test/test@//localhost:1521/testPdb;

CREATE TABLE any_artifact (
    id VARCHAR2(20),
    name VARCHAR2(20),
    type VARCHAR2(20)
);

INSERT INTO any_artifact (id, name, type)
    VALUES ('id-000-0000', 'name-000-0000', 'type-000-0000');
INSERT INTO any_artifact (id, name, type)
    VALUES ('id-111-1111', 'name-111-1111', 'type-111-1111');

COMMIT;
exit;
