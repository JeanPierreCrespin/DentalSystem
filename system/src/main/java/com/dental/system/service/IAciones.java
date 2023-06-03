package com.dental.system.service;

import java.util.List;

public interface IAciones<T> {

    T agregar(T t);
    T modificar(T t);
    void eliminar(String id);
    List<T> listar();

}
