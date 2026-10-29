package controller;

import model.Cliente;
import model.ClienteDAO;
import model.CompraDAO;
import model.Membresia;
import model.MembresiaDAO;
import view.GestionClientesView;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.List;

public class ClienteDAOController { // ClienteDAOController es el controlador que usa el 
	                                //administrador para gestionar clientes. 
	                              //Implementa las operaciones CRUD:



    private GestionClientesView vista;
    private ClienteDAO clienteDAO;
    private MembresiaDAO membresiaDAO;
    private int idSeleccionado = -1;
    private CompraDAO compraDAO;
    public ClienteDAOController(GestionClientesView vista) {
        this.vista = vista;
        this.clienteDAO = new ClienteDAO();
        this.membresiaDAO = new MembresiaDAO();
        this.compraDAO = new CompraDAO();

        initListeners();
        cargarMembresias();
        cargarTabla();
    }

    private void initListeners() {
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnActualizar().addActionListener(e -> actualizar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnLimpiar().addActionListener(e -> limpiar());

        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarFila();
        });
    }

    private void cargarMembresias() {
        vista.getCmbMembresia().removeAllItems(); //limpia el combo
        List<Membresia> lista = membresiaDAO.listar(); // obtiene la membresias
        for (Membresia membresia : lista) {
            vista.getCmbMembresia().addItem(membresia.getIdMembresia() + " - " + membresia.getNombre());
        }
    }

    private void cargarTabla() {
        vista.getModeloTabla().setRowCount(0);
        List<Cliente> lista = clienteDAO.listar();
        for (Cliente cliente : lista) {
            vista.getModeloTabla().addRow(new Object[]{ // agrega filas con esos datos
                cliente.getIdCliente(),
                cliente.getIdUsuario(),
                cliente.getIdMembresia(),
                cliente.getTelefono(),
                cliente.getFechaInicioMembresia(),
                cliente.getFechaFinMembresia()
            });
        }
    }

    private void agregar() {
        String idUsuarioStr  = vista.getIdUsuario();
        String telefono      = vista.getTelefono();
        String fechaInicio   = vista.getFechaInicio();
        String fechaFin      = vista.getFechaFin();
        
        if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(
                vista,
                "El teléfono debe tener exactamente 8 dígitos.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        
        
        // Verificar que el usuario no tenga ya un cliente
        try {
            int idUsuario = Integer.parseInt(idUsuarioStr);
            Cliente existente = clienteDAO.buscarPorUsuario(idUsuario);
            if (existente != null) {
                JOptionPane.showMessageDialog(vista, "Ese usuario ya tiene un cliente registrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID Usuario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idUsuarioStr.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete los campos obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idUsuario  = Integer.parseInt(idUsuarioStr);
            int idMembresia = obtenerIdMembresia();
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin  = LocalDate.parse(fechaFin);
            

            if (fin.isBefore(inicio)) {
                JOptionPane.showMessageDialog(
                    vista,
                    "La fecha final no puede ser menor que la fecha inicial.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Cliente cliente = new Cliente(); // crea el cliente
            cliente.setIdUsuario(idUsuario);
            cliente.setIdMembresia(idMembresia);
            cliente.setTelefono(telefono);
            cliente.setFechaInicioMembresia(inicio);
            cliente.setFechaFinMembresia(fin);

            if (clienteDAO.insertar(cliente)) { // y lo guarda
                JOptionPane.showMessageDialog(vista, "Cliente agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "ID Usuario debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String telefono  = vista.getTelefono();
        String fechaInicio = vista.getFechaInicio();
        String fechaFin = vista.getFechaFin();

        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete los campos obligatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idMembresia = obtenerIdMembresia();
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin = LocalDate.parse(fechaFin);

            Cliente cliente = new Cliente();
            cliente.setIdCliente(idSeleccionado);
            cliente.setIdMembresia(idMembresia);
            cliente.setTelefono(telefono);
            cliente.setFechaInicioMembresia(inicio);
            cliente.setFechaFinMembresia(fin);

            if (clienteDAO.actualizar(cliente)) {
                JOptionPane.showMessageDialog(vista, "Cliente actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un cliente de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(vista, 
            "¿Está seguro? Esto también eliminará las compras del cliente.", 
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            // Primero eliminar compras del cliente
            compraDAO.eliminarPorCliente(idSeleccionado);
            
            // Luego eliminar el cliente
            if (clienteDAO.eliminar(idSeleccionado)) {
                JOptionPane.showMessageDialog(vista, "Cliente eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarFila() {
        int fila = vista.getTabla().getSelectedRow();
        if (fila == -1) return;

        idSeleccionado  = (int)    vista.getModeloTabla().getValueAt(fila, 0);
        String idUsuario = String.valueOf(vista.getModeloTabla().getValueAt(fila, 1));
        String telefono = (String) vista.getModeloTabla().getValueAt(fila, 3);
        String inicio  = String.valueOf(vista.getModeloTabla().getValueAt(fila, 4));
        String fin  = String.valueOf(vista.getModeloTabla().getValueAt(fila, 5));

        vista.llenarFormulario(idUsuario, telefono, inicio, fin);
    }

    private int obtenerIdMembresia() {
        String seleccion = (String) vista.getCmbMembresia().getSelectedItem();
        if (seleccion == null) return 0;
        return Integer.parseInt(seleccion.split(" - ")[0]);
    }

    private void limpiar() {
        vista.limpiarFormulario();
        idSeleccionado = -1;
    }
    // "Es el controlador MVC encargado de administrar los clientes del gimnasio.
    // Gestiona las operaciones CRUD utilizando ClienteDAO,
    // carga las membresías desde MembresiaDAO, muestra la información en la tabla 
    // de la vista y elimina primero las compras asociadas mediante CompraDAO 
    // antes de borrar un cliente para mantener la integridad referencial 
    // de la base de datos."
}