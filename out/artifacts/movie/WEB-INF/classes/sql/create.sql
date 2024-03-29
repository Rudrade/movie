DROP TABLE IF EXISTS person_movie;
DROP TABLE IF EXISTS person_occupation;
DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS occupation;
DROP TABLE IF EXISTS person;

CREATE TABLE occupation (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	occupation VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE country (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	country VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE movies (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    release_date DATE,
    country_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);

CREATE TABLE person (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    birth_date DATE,
    country_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(country_id) REFERENCES country(id)
);

CREATE TABLE person_occupation(
	person_id INT NOT NULL,
    occupation_id INT NOT NULL,
    PRIMARY KEY (person_id, occupation_id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (occupation_id) REFERENCES occupation(id)
);

CREATE TABLE person_movie (
	movie_id INT NOT NULL,
    person_id INT NOT NULL,
    PRIMARY KEY (movie_id, person_id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
);