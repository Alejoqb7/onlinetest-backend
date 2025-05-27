package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler;

import java.util.ArrayList;
import java.util.List;

public interface EntityAssembler<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);

    default List<D> toDomain(List<E> entityList) {
        List<D> lista = new ArrayList<>();
        if (entityList == null) {
            return lista;
        }
        for (E e : entityList) {
            lista.add(toDomain(e));
        }
        return lista;
    }

    default List<E> toEntityList(List<D> domainList) {
        List<E> lista = new ArrayList<>();
        if (domainList == null) {
            return lista;
        }
        for (D d : domainList) {
            lista.add(toEntity(d));
        }
        return lista;
    }
}
