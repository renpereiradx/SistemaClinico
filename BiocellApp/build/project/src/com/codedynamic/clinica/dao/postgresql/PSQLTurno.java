package com.codedynamic.clinica.dao.postgresql;

import com.codedynamic.clinica.dao.interfaces.TurnoDAO;
import com.codedynamic.clinica.excepciones.ExcepcionGeneral;
import com.codedynamic.clinica.modelo.Paciente;
import com.codedynamic.clinica.modelo.Turno;
import com.codedynamic.clinica.utilidades.UtilidadFecha;
import com.codedynamic.clinica.utilidades.UtilidadHora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLTurno implements TurnoDAO {

    private final String INSERTAR = "INSERT INTO turnos(numero, fecha, id_paciente, hora, descripcion) values(?, to_date(?, 'DD-MM-YYYY'), ?, to_timestamp(?, 'HH24:MI:SS'), ?) RETURNING id_turno";
    private final String OBTENER = "SELECT id_turno, numero, to_char(fecha, 'DD-MM-YYYY') AS fecha, id_paciente, to_char(hora, 'HH24:MI:SS') AS hora, descripcion, estado" +
            " from turnos";
    private final String OBTNERPORID = "SELECT id_turno, numero, to_char(fecha, 'DD-MM-YYYY') AS fecha, id_paciente, to_char(hora, 'HH24:MI:SS') AS hora, descripcion, estado " +
            "from turnos where id_turno = ?";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

    @Override
    public void insertar(Turno o) {
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setInt(1, o.getNumero());
            sentencia.setString(2, UtilidadFecha.formato(o.getFecha()));
            sentencia.setShort(3, o.getPaciente().getId_paciente());
            sentencia.setString(4, UtilidadHora.formatoHora(o.getHora()));
            sentencia.setString(5, o.getDescripcion());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                o.setId_turno(resultado.getShort(1));
            } else {
                throw new ExcepcionGeneral("No se inserto ningun registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Turno o) {

    }

    @Override
    public void eliminar(Turno o) {

    }

    @Override
    public Turno obtenerPorID(Short id_turno) throws ExcepcionGeneral {
        Turno turno = new Turno();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTNERPORID);
            sentencia.setShort(1, id_turno);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                turno = convertir(resultado);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }

        return turno;
    }

    @Override
    public List<Turno> listar() throws ExcepcionGeneral {
        List<Turno> lista = new ArrayList<>();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                Turno turno = convertir(resultado);
                lista.add(turno);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    private Turno convertir(ResultSet resultado) throws SQLException {
        Turno turno = new Turno();
        PSQLPaciente psqlPaciente = new PSQLPaciente();
        Paciente paciente = null;
        turno.setId_turno(resultado.getShort("id_turno"));
        turno.setNumero(resultado.getShort("numero"));
        turno.setFecha(UtilidadFecha.analizar(resultado.getString("fecha")));
        paciente = psqlPaciente.obtenerPorID(resultado.getShort("id_paciente"));
        turno.setPaciente(paciente);
        turno.setHora(UtilidadHora.analizarHora(resultado.getString("hora")));
        turno.setDescripcion(resultado.getString("descripcion"));
        turno.setEstado(resultado.getString("estado"));
        return turno;
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
