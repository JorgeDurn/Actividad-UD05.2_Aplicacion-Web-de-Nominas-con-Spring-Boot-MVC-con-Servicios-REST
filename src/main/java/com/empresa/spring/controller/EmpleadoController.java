package com.empresa.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.empresa.spring.model.Empleado;
import com.empresa.spring.service.EmpleadoService;

import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con empleados.
 * Este controlador proporciona endpoints para listar, buscar, crear, 
 * actualizar y eliminar empleados.
 */
@RestController
@RequestMapping("/empresa/empleados")
public class EmpleadoController {

    /** Servicio para realizar operaciones relacionadas con empleados. */
    @Autowired
    private EmpleadoService empleadoService;

    /**
     * Obtiene una lista de todos los empleados.
     * @return una lista de empleados en formato JSON.
     */
    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.obtenerTodos();
    }

    /**
     * Obtiene un empleado por su DNI.
     * @param dni el DNI del empleado a buscar.
     * @return el empleado correspondiente al DNI, o {@code null} si no se encuentra.
     */
    @GetMapping("/{dni}")
    public Empleado obtenerEmpleadoPorDni(@PathVariable String dni) {
        return empleadoService.obtenerPorDni(dni);
    }

    /**
     * Crea un nuevo empleado.
     * @param empleado los datos del empleado a crear (en formato JSON).
     * @return el empleado creado.
     */
    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        empleadoService.guardar(empleado);
        return empleado;
    }

    /**
     * Actualiza un empleado existente.
     * @param dni el DNI del empleado a actualizar.
     * @param empleado los nuevos datos del empleado (en formato JSON).
     * @return el empleado actualizado.
     */
    @PutMapping("/{dni}")
    public Empleado actualizarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        empleado.setDni(dni);
        empleadoService.editar(empleado);
        return empleado;
    }

    /**
     * Elimina un empleado por su DNI.
     * @param dni el DNI del empleado a eliminar.
     * @return un mensaje indicando si el empleado fue eliminado o no encontrado.
     */
    @DeleteMapping("/{dni}")
    public String eliminarEmpleado(@PathVariable String dni) {
        boolean eliminado = empleadoService.eliminarSiExiste(dni);
        return eliminado ? "Empleado eliminado" : "Empleado no encontrado";
    }
}
