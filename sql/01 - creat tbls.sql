DROP TABLE pet;

DROP TABLE adopter;

CREATE TABLE pet (
        petId SERIAL NOT NULL,
        name CHARACTER VARYING(255), 
        type INTEGER,
        breed INTEGER, 
        PRIMARY KEY (petId)
);


CREATE TABLE adopter (
        id SERIAL NOT NULL, 
        name CHARACTER VARYING(255), 
        phoneNumber CHARACTER VARYING(20),
        adoptionDate DATE,
        petId INTEGER,
        PRIMARY KEY (id), 
        CONSTRAINT adopter_petid_fkey FOREIGN KEY (petId) REFERENCES "pet" (petId)
 );
