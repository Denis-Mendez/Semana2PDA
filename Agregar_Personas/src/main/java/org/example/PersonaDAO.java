package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private Connection connection;

    public PersonaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPersona(Persona persona) throws SQLException {
        String sql = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, persona.getIdPersona());
            statement.setString(2, persona.getDocumento());
            statement.setString(3, persona.getApellido1());
            statement.setString(4, persona.getApellido2());
            statement.setString(5, persona.getNombre1());
            statement.setString(6, persona.getNombre2());
            statement.executeUpdate();
        }
    }

    public List<Persona> listarPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM PERSONA";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_PERSONA");
                String documento = resultSet.getString("DOCUMENTO");
                String apellido1 = resultSet.getString("APELLIDO1");
                String apellido2 = resultSet.getString("APELLIDO2");
                String nombre1 = resultSet.getString("NOMBRE1");
                String nombre2 = resultSet.getString("NOMBRE2");
                personas.add(new Persona(id, documento, apellido1, apellido2, nombre1, nombre2));
            }
        }
        return personas;
    }

    public List<String> listarNombres() throws SQLException {
        List<String> nombres = new ArrayList<>();
        String sql = "SELECT NOMBRE1, NOMBRE2, APELLIDO1, APELLIDO2, DOCUMENTO FROM PERSONA";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String nombre1 = resultSet.getString("NOMBRE1");
                String nombre2 = resultSet.getString("NOMBRE2");
                String apellido1 = resultSet.getString("APELLIDO1");
                String apellido2 = resultSet.getString("APELLIDO2");
                String documento = resultSet.getString("DOCUMENTO");
                if (nombre2 != null && !nombre2.isEmpty()) {
                    nombres.add(nombre1 + " " + nombre2);
                } else {
                    nombres.add(nombre1);
                }
            }
        }
        return nombres;
    }
    public List<Persona> listarPersonasPorApellido(String apellido) throws SQLException {
        List<Persona> personasConApellido = new ArrayList<>();
        String sql = "SELECT * FROM PERSONA WHERE APELLIDO1 = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, apellido);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID_PERSONA");
                    String documento = resultSet.getString("DOCUMENTO");
                    String apellido1 = resultSet.getString("APELLIDO1");
                    String apellido2 = resultSet.getString("APELLIDO2");
                    String nombre1 = resultSet.getString("NOMBRE1");
                    String nombre2 = resultSet.getString("NOMBRE2");
                    personasConApellido.add(new Persona(id, documento, apellido1, apellido2, nombre1, nombre2));
                }
            }
        }
        return personasConApellido;
    }
    public void agregarRegistro(String documento, String apellido1, String apellido2, String nombre1, String nombre2) throws SQLException {
        String sql = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2) VALUES (SEQ_PERSONA.NEXTVAL, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documento);
            statement.setString(2, apellido1);
            statement.setString(3, apellido2);
            statement.setString(4, nombre1);
            statement.setString(5, nombre2);
            statement.executeUpdate();
        }

    }


    }


