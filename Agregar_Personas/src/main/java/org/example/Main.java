package org.example;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException{
        System.out.println("-----DAO Personas-----");
        System.out.println();
        List<String> nombres = PersonaDAO.listarNombres();
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
        String apellido = JOptionPane.showInputDialog("Ingrese un apellido: ");
        List<Persona> personasConApellido = PersonaDAO.listarPersonasPorApellido(apellido);
        if (!personasConApellido.isEmpty()) {
            System.out.println("Personas con el apellido '" + apellido + "':");
            for (Persona persona : personasConApellido) {
                System.out.println(persona.getApellido1() + " " + persona.getNombre1());
            }
        } else {
            System.out.println("No se encontraron personas con el apellido '" + apellido + "'.");
        }
        /*String documento = JOptionPane.showInputDialog("Documento: ");
        String apellido1 = JOptionPane.showInputDialog("Apellido1: ");
        String apellido2 = JOptionPane.showInputDialog("Apellido2: ");
        String nombre1 = JOptionPane.showInputDialog("Nombre1: ");
        String nombre2 = JOptionPane.showInputDialog("Nombre2: ");

        personaDAO.agregarRegistro(documento, apellido1, apellido2, nombre1, nombre2);
        List<Persona> personas = personaDAO.listarPersonas();*/
    }
}