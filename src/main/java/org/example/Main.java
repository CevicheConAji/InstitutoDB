package org.example;

import org.example.controllers.ControllerDB;

public class Main {
    public static void main(String[] args) {
        testing();
    }
    public static void testing(){
        ControllerDB controllerDB = new ControllerDB();
        connect(controllerDB);

        createTableProfesor(controllerDB);
        createTableAlumno(controllerDB);
        createTableAsignatura(controllerDB);
        createTableMatricula(controllerDB);

        insertProfesor(controllerDB);
        insertAlumno(controllerDB);
        insertAsignatura(controllerDB);
        insertMatricula(controllerDB);

        disconnect(controllerDB);
    }
    public static void connect(ControllerDB controllerDB){
        controllerDB.connect();
    }
    public static void disconnect(ControllerDB controllerDB){
        controllerDB.disconnect();
    }
    public static void createTableAlumno(ControllerDB controllerDB){
        String createTable = "CREATE TABLE IF NOT EXISTS alumnos(\n" +
                "\tid_alumno int PRIMARY KEY auto_increment,\n" +
                "    nombre VARCHAR(30) NOT NULL,\n" +
                "    apellidos VARCHAR(30) NOT NULL,\n" +
                "    fecha_nac DATE NOT NULL\n" +
                ")";
        controllerDB.executeUpdate(createTable);
    }
    public static void createTableProfesor(ControllerDB controllerDB){
        String createTable = "CREATE TABLE IF NOT EXISTS profesor(\n" +
                "\tid_profesor int PRIMARY KEY auto_increment,\n" +
                "    nombre VARCHAR(32) NOT NULL,\n" +
                "    apellidos VARCHAR(100) NOT NULL,\n" +
                "    fecha_nac DATE NOT NULL\n" +
                ");";
        controllerDB.executeUpdate(createTable);
    }
    public static void createTableAsignatura(ControllerDB controllerDB){
        String createTable = "CREATE TABLE IF NOT EXISTS asignatura(\n" +
                "\tid_asignatura int PRIMARY KEY auto_increment,\n" +
                "    nombre VARCHAR(30) NOT NULL,\n" +
                "    id_profesor int NOT NULL,\n" +
                "    FOREIGN KEY profesor_asignatura(id_profesor) REFERENCES profesor(id_profesor)\n" +
                ");";
        controllerDB.executeUpdate(createTable);
    }
    public static void createTableMatricula(ControllerDB controllerDB){
        String createTable = "CREATE TABLE IF NOT EXISTS matricula(\n" +
                "\tid_alumno int NOT NULL,\n" +
                "    id_asignatura int NOT NULL,\n" +
                "    fecha YEAR NOT NULL,\n" +
                "    nota int,\n" +
                "    PRIMARY KEY (id_alumno, id_asignatura, fecha),\n" +
                "    FOREIGN KEY alumno_matriculado(id_alumno) REFERENCES alumnos(id_alumno),\n" +
                "    FOREIGN KEY asignatura_matriculada(id_asignatura) REFERENCES asignatura(id_asignatura)\n" +
                ");";
        controllerDB.executeUpdate(createTable);
    }
    public static void insertProfesor(ControllerDB controllerDB){
        String insert = "INSERT INTO profesor (nombre, apellidos, fecha_nac) VALUES \n" +
                "('Juan', 'Pérez', '1980-05-10'),\n" +
                "('María', 'Gómez', '1985-03-20')";
        controllerDB.executeUpdate(insert);

    }
    public static void insertAlumno(ControllerDB controllerDB){
        String insert = "INSERT INTO alumnos (nombre, apellidos, fecha_nac) VALUES \n" +
                "('Carlos', 'López', '2001-07-15'),\n" +
                "('Ana', 'Martínez', '2002-11-25')";
        controllerDB.executeUpdate(insert);
    }
    public static void insertAsignatura(ControllerDB controllerDB){
        String insert = "INSERT INTO asignatura (nombre, id_profesor) VALUES \n" +
                "('Matemáticas', 1),\n" +
                "('Historia', 2)";
        controllerDB.executeUpdate(insert);
    }
    public static void insertMatricula(ControllerDB controllerDB){
        String insert = "INSERT INTO matricula (id_alumno, id_asignatura, fecha, nota) VALUES \n" +
                "(1, 1, 2024, 8),\n" +
                "(2, 2, 2024, 9)";
        controllerDB.executeUpdate(insert);
    }
}