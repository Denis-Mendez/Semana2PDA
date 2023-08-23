package org.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private static final String INSERT_PERSONA = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String LISTAR_PERSONAS = "SELECT * FROM PERSONA";
    private static final String LISTAR_NOMBRES = "SELECT NOMBRE1, NOMBRE2, APELLIDO1, APELLIDO2, DOCUMENTO FROM PERSONA";
    private static final String LISTAR_POR_APELLIDO = "SELECT * FROM PERSONA WHERE APELLIDO1 = ?";
//    private static final String AGREGAR_REGISTRO = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2) VALUES (SEQ_PERSONA.NEXTVAL, ?, ?, ?, ?, ?)";

    private static final String CUENTA_PERSONAS = "SELECT COUNT (ID_PERSONA) AS CUENTA FROM PERSONA";

    public static int cuentaPersonas() {
        int cuenta = 0;

        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(CUENTA_PERSONAS);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                cuenta = resultado.getInt("CUENTA");
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        return cuenta;
    }

    public static boolean agregarPersona(Persona persona) {
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PERSONA)) {
            statement.setString(1, persona.getIdPersona());
            statement.setString(2, persona.getDocumento());
            statement.setString(3, persona.getApellido1());
            statement.setString(4, persona.getApellido2());
            statement.setString(5, persona.getNombre1());
            statement.setString(6, persona.getNombre2());
            int retorno = statement.executeUpdate();

            return retorno > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Persona> listarPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        try (Statement statement = DatabaseManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(LISTAR_PERSONAS)) {
            while (resultSet.next()) {
                Persona persona = getResultFromPersonaRS(resultSet);
                personas.add(persona);
            }
        }
        return personas;
    }

    public static List<String> listarNombres() throws SQLException {
        List<String> nombres = new ArrayList<>();
        try (Statement statement = DatabaseManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(LISTAR_NOMBRES)) {
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

    public static List<Persona> listarPersonasPorApellido(String apellido) throws SQLException {
        List<Persona> personasConApellido = new ArrayList<>();
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(LISTAR_POR_APELLIDO)) {
            statement.setString(1, apellido);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Persona persona = getResultFromPersonaRS(resultSet);
                    personasConApellido.add(persona);
                }
            }
        }
        return personasConApellido;
    }
/*    public void agregarRegistro(String documento, String apellido1, String apellido2, String nombre1, String nombre2) throws SQLException {
        try (PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(AGREGAR_REGISTRO)) {
            statement.setString(1, documento);
            statement.setString(2, apellido1);
            statement.setString(3, apellido2);
            statement.setString(4, nombre1);
            statement.setString(5, nombre2);
            statement.executeUpdate();
        }

    }*/

    private static Persona getResultFromPersonaRS(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("ID_PERSONA");
        String documento = resultSet.getString("DOCUMENTO");
        String apellido1 = resultSet.getString("APELLIDO1");
        String apellido2 = resultSet.getString("APELLIDO2");
        String nombre1 = resultSet.getString("NOMBRE1");
        String nombre2 = resultSet.getString("NOMBRE2");
        Persona persona = new Persona(id, documento, apellido1, apellido2, nombre1, nombre2);
        return persona;
    }

}


