package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.dao.interfaces.PacienteDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Paciente;
import com.codedynamic.clinica.utilidades.UtilidadFecha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLPaciente implements PacienteDAO {
    private static final String INSERTAR = "INSERT INTO pacientes(nombres, apellidos, cedula, correo_electronico, fecha_nacimiento, departamento, " +
            "ciudad, barrio, profesion, sexo, hijos, id_usuario, nro_telefono, nombre_padre, nombre_madre, estado_civil, grado_instruccion, " +
            "persona_responsable, etnia) "
            + "VALUES(?, ?, ?, ?, to_date(?, 'DD-MM-YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_paciente";
    private static final String MODIFICAR = "UPDATE pacientes SET nombres = ?, apellidos = ?, cedula = ?, correo_electronico = ?, "
    		+ "fecha_nacimiento = to_date(?, 'DD-MM-YYYY'), " +
            "departamento = ?, ciudad = ?, barrio = ?, profesion = ?, sexo = ?, hijos = ?, nro_telefono = ?, nombre_padre = ?, " +
            "nombre_madre = ?, estado_civil = ?, grado_instruccion = ?, persona_responsable = ?, etnia = ? "
            + "WHERE id_paciente = ?";
    private static final String ELIMINAR = "DELETE FROM pacientes WHERE id_paciente = ?";
    private static final String OBTENERPORID = "SELECT id_paciente, nombres, apellidos, cedula, correo_electronico, " +
            "to_char(fecha_nacimiento, 'DD-MM-YYYY') AS fecha_nacimiento,  departamento, ciudad, barrio, profesion, sexo, " +
            "hijos, id_usuario, nro_telefono, nombre_padre, nombre_madre, estado_civil, grado_instruccion, " +
            "persona_responsable, etnia FROM pacientes WHERE id_paciente = ?";
    private static final String OBTENER = "SELECT id_paciente, nombres, apellidos, cedula, correo_electronico, " +
            "to_char(fecha_nacimiento, 'DD-MM-YYYY') AS fecha_nacimiento, departamento, ciudad, barrio, profesion, sexo, hijos, " +
            "id_usuario, nro_telefono, nombre_padre, nombre_madre, estado_civil, grado_instruccion, persona_responsable, etnia FROM pacientes";
    private static final String BUSCARNOMBRE = "SELECT id_paciente, nombres, apellidos, cedula, correo_electronico, " +
            "to_char(fecha_nacimiento, 'DD-MM-YYYY') AS fecha_nacimiento, departamento, ciudad, barrio, profesion, sexo, hijos, id_usuario, " +
            "nro_telefono, nombre_padre, nombre_madre, estado_civil, grado_instruccion, persona_responsable, etnia FROM pacientes WHERE nombres ILIKE ?";
    private static final String BUSCARAPELLIDO = "SELECT id_paciente, nombres, apellidos, cedula, correo_electronico, " +
            "to_char(fecha_nacimiento, 'DD-MM-YYYY') AS fecha_nacimiento, departamento, ciudad, barrio, profesion, sexo, hijos, id_usuario, " +
            "nro_telefono, nombre_padre, nombre_madre, estado_civil, grado_instruccion, persona_responsable, etnia FROM pacientes WHERE apellidos ILIKE ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Paciente o) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1, o.getNombres());
            sentencia.setString(2, o.getApellidos());
            sentencia.setString(3, o.getCedula());
            sentencia.setString(4, o.getCorreo());
            sentencia.setString(5, UtilidadFecha.formato(o.getFecha_nacimiento()));
            sentencia.setString(6, o.getDepartamento());
            sentencia.setString(7, o.getCiudad());
            sentencia.setString(8, o.getBarrio());
            sentencia.setString(9, o.getProfesion());
            sentencia.setString(10, o.getSexo());
            sentencia.setInt(11, o.getHijos());
            sentencia.setShort(12, o.getId_usuario());
            sentencia.setString(13, o.getNroTelefono());
            sentencia.setString(14, o.getNombrePadre());
            sentencia.setString(15, o.getNombreMadre());
            sentencia.setString(16, o.getEstadoCivil());
            sentencia.setString(17, o.getGradoInstruccion());
            sentencia.setString(18, o.getPersonaResponsable());
            sentencia.setString(19, o.getEtnia());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                o.setId_paciente(resultado.getShort(1));
            } else {
                throw  new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException sqle) {
            throw new ExcepcionGeneral(sqle.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Paciente o) throws ExcepcionGeneral{
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1, o.getNombres());
            sentencia.setString(2, o.getApellidos());
            sentencia.setString(3, o.getCedula());
            sentencia.setString(4, o.getCorreo());
            sentencia.setString(5, UtilidadFecha.formato(o.getFecha_nacimiento()));
            sentencia.setString(6, o.getDepartamento());
            sentencia.setString(7, o.getCiudad());
            sentencia.setString(8, o.getBarrio());
            sentencia.setString(9, o.getProfesion());
            sentencia.setString(10, o.getSexo());
            sentencia.setInt(11, o.getHijos());
            //sentencia.setShort(12, o.getId_usuario());
            sentencia.setString(12, o.getNroTelefono());
            sentencia.setString(13, o.getNombrePadre());
            sentencia.setString(14, o.getNombreMadre());
            sentencia.setString(15, o.getEstadoCivil());
            sentencia.setString(16, o.getGradoInstruccion());
            sentencia.setString(17, o.getPersonaResponsable());
            sentencia.setString(18, o.getEtnia());
            sentencia.setShort(19, o.getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se modifico ningun registro");
            }
        } catch (SQLException sqle) {
            throw new ExcepcionGeneral(sqle.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void eliminar(Paciente o) throws ExcepcionGeneral {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setShort(1, o.getId_paciente());
            if (sentencia.executeUpdate() == 0) {
                throw new ExcepcionGeneral("No se elimino ningun registro");
            }
        } catch (SQLException sqle) {
            throw new ExcepcionGeneral(sqle.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public Paciente obtenerPorID(Short id_paciente) {
        Paciente paciente = null;
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENERPORID);
            sentencia.setShort(1, id_paciente);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                paciente = convertir(resultado);
            }
        } catch (SQLException sqle) {
            throw new ExcepcionGeneral(sqle.getMessage());
        } finally {
        	cerrarConexiones();
        }
        return paciente;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> listado = new ArrayList<>();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Paciente paciente = convertir(resultado);
                listado.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	cerrarConexiones();
        }
        return listado;
    }

    public ObservableList<Paciente> listarBusqueda(String nombre) {
    	ObservableList<Paciente> lista = FXCollections.observableArrayList();
    	try {
			conexion = new PSQLConexion().conectar();
			sentencia = conexion.prepareStatement(BUSCARNOMBRE);
			sentencia.setString(1, "%"+nombre+"%");
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				Paciente paciente = convertir(resultado);
				lista.add(paciente);
			}
		} catch (SQLException sqle) {
			throw new ExcepcionGeneral(sqle.getMessage());
		} finally {
			cerrarConexiones();
		}
		return lista;
    }

    public List<Paciente> listarBusqApe(String apellido) throws ExcepcionGeneral{
        List<Paciente> lista = new ArrayList<>();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(BUSCARAPELLIDO);
            sentencia.setString(1, "%"+apellido+"%");
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Paciente paciente = convertir(resultado);
                lista.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Paciente convertir(ResultSet resultado) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId_paciente(resultado.getShort("id_paciente"));
        paciente.setNombres(resultado.getString("nombres"));
        paciente.setApellidos(resultado.getString("apellidos"));
        paciente.setCedula(resultado.getString("cedula"));
        paciente.setCorreo(resultado.getString("correo_electronico"));
        if (resultado.getString("fecha_nacimiento") != null) {
			paciente.setFecha_nacimiento(UtilidadFecha.analizar(resultado.getString("fecha_nacimiento")));
		}
		paciente.setDepartamento(resultado.getString("departamento"));
        paciente.setCiudad(resultado.getString("ciudad"));
        paciente.setBarrio(resultado.getString("barrio"));
        paciente.setProfesion(resultado.getString("profesion"));
        paciente.setSexo(resultado.getString("sexo"));
        paciente.setHijos(resultado.getShort("hijos"));
        paciente.setId_usuario(resultado.getShort("id_usuario"));
        paciente.setNroTelefono(resultado.getString("nro_telefono"));
        paciente.setNombrePadre(resultado.getString("nombre_padre"));
        paciente.setNombreMadre(resultado.getString("nombre_madre"));
        paciente.setEstadoCivil(resultado.getString("estado_civil"));
        paciente.setGradoInstruccion(resultado.getString("grado_instruccion"));
        paciente.setPersonaResponsable(resultado.getString("persona_responsable"));
        paciente.setEtnia(resultado.getString("etnia"));
        return paciente;
    }

    private void cerrarConexiones() {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException sqle) { }
    }
}

