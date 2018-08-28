package com.codedynamic.clinica.dao.interfaces;

public interface DAOSimple<K, O> {
	void insertar(O o);
    void modificar(O o);
    void eliminar(O o);
    O obtenerPorID(K k);
}
