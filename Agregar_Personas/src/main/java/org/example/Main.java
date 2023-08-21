package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1522:XE";
        String usuario = "System";
        String contraseña = "root";

        try {
            Connection connection = DriverManager.getConnection(url, usuario, contraseña);
            PersonaDAO personaDAO = new PersonaDAO(connection);


            List<String> nombres = personaDAO.listarNombres();
            for (String nombre : nombres) {
                System.out.println(nombre);
            }
            String apellido = JOptionPane.showInputDialog("Ingrese un apellido: ");
            List<Persona> personasConApellido = personaDAO.listarPersonasPorApellido(apellido);
            if (!personasConApellido.isEmpty()) {
                System.out.println("Personas con el apellido '" + apellido + "':");
                for (Persona persona : personasConApellido) {
                    System.out.println(persona.getApellido1() + " " + persona.getNombre1());
                }
            } else {
                System.out.println("No se encontraron personas con el apellido '" + apellido + "'.");
            }
//            String documento = JOptionPane.showInputDialog("Documento: ");
//            String apellido1 = JOptionPane.showInputDialog("Apellido1: ");
//            String apellido2 = JOptionPane.showInputDialog("Apellido2: ");
//            String nombre1 = JOptionPane.showInputDialog("Nombre1: ");
//            String nombre2 = JOptionPane.showInputDialog("Nombre2: ");
//
//            personaDAO.agregarRegistro(documento, apellido1, apellido2, nombre1, nombre2);
//            List<Persona> personas = personaDAO.listarPersonas();
//            for (Persona persona : personas) {
//                System.out.println(persona.getApellido1() + " " + persona.getNombre1());
//            }
            connection.close();
            System.out.println("Se cierra");        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}