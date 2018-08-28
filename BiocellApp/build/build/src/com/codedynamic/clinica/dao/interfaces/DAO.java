package com.codedynamic.clinica.dao.interfaces;

import java.util.List;

public interface DAO<K, O> {
	void insertar(O o);
    void modificar(O o);
    void eliminar(O o);
    O obtenerPorID(K k);
    List<O> listar();
}
