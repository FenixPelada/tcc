package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
	private static final String URL = "jdbc:mysql://localhost:3306/javabase";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}

/*
create database javabase;
use javabase;
/*drop database javabase;
SHOW VARIABLES LIKE 'datadir';

CREATE TABLE tb_curso(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL
);
	
CREATE TABLE tb_materia (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL
);

CREATE TABLE tb_professor (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (100) NOT NULL
);

CREATE TABLE tb_sala (
	id INT PRIMARY KEY AUTO_INCREMENT,
    numero INT NOT NULL
);

CREATE TABLE tb_curso_materia (
    id_curso INT,
    id_materia INT,
    PRIMARY KEY (id_curso, id_materia),
    FOREIGN KEY (id_curso) REFERENCES tb_curso(id) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES tb_materia(id) ON DELETE CASCADE
);

CREATE TABLE tb_professor_materia (
    id_professor INT,
    id_materia INT,
    PRIMARY KEY (id_professor, id_materia),
    FOREIGN KEY (id_professor) REFERENCES tb_professor(id) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES tb_materia(id) ON DELETE CASCADE
);

CREATE TABLE tb_professor_disponibilidade (
    id_professor INT,
    dia VARCHAR(20),
    PRIMARY KEY (id_professor, dia),
    FOREIGN KEY (id_professor) REFERENCES tb_professor(id) ON DELETE CASCADE
);

CREATE TABLE tb_horario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_curso INT NOT NULL,
    id_materia INT NOT NULL,
    id_professor INT NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,  -- 'SEGUNDA', 'TERCA', etc
    hora TIME NOT NULL,               -- '07:00', '08:00', etc
    FOREIGN KEY (id_curso) REFERENCES tb_curso(id) ON DELETE CASCADE,
    FOREIGN KEY (id_materia) REFERENCES tb_materia(id) ON DELETE CASCADE,
    FOREIGN KEY (id_professor) REFERENCES tb_professor(id) ON DELETE CASCADE
);

select * from tb_curso;
select * from tb_professor;
select * from tb_materia;
select * from tb_curso;

*/