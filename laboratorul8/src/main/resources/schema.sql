CREATE DATABASE IF NOT EXISTS lab8;
USE lab8;

CREATE TABLE persoane (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nume VARCHAR(255) NOT NULL,
    varsta INT NOT NULL
);

CREATE TABLE excursii (
    id_excursie INT PRIMARY KEY AUTO_INCREMENT,
    id_persoana INT,
    destinatia VARCHAR(255),
    anul INT,
    FOREIGN KEY (id_persoana) REFERENCES persoane(id)
);
