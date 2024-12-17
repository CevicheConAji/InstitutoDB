package org.example.controllers;

import org.example.DB.ConnectDB;

import java.sql.*;

public class ControllerDB {
    private final ConnectDB connectDB = new ConnectDB();
    private Connection con = connectDB.getConnection();

    public Connection connect() {
        try {
            con = DriverManager.getConnection(connectDB.getJdbc(), connectDB.getUser(), connectDB.getPassword());
            System.out.println("Connected to the database successfully");
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }

    public void disconnect() {
        try{
            con.close();
            System.out.println("Disconnected from the database successfully");
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public int executeUpdate(String sql) {
        int rowsAffected = 0;
        try {
            rowsAffected = con.createStatement().executeUpdate(sql);
            return rowsAffected;
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;

    }
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        Statement stmt;
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return rs;
    }
    public void showTableProfesor(ResultSet rs) {
        try {
            while (rs.next()) {
                int id = rs.getInt("id_profesor");
                String prof = rs.getString("nombre");
                String apellido = rs.getString("apellidos");
                Date fechaNacimiento = rs.getDate("fecha_nac");
                System.out.printf("%-10s",id);

            }
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
