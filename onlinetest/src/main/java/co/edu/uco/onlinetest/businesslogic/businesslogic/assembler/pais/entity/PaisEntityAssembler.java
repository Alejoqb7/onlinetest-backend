package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisEntityAssembler implements EntityAssembler<PaisEntity, PaisDomain> {

    private static final PaisEntityAssembler INSTANCE = new PaisEntityAssembler();

    private PaisEntityAssembler() { }

    public static PaisEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public PaisEntity toEntity(PaisDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return PaisEntity.obtenerValorDefecto();
        }
        return new PaisEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public PaisDomain toDomain(PaisEntity entity) {
        var e = (entity == null ? PaisEntity.obtenerValorDefecto() : entity);
        return new PaisDomain(e.getId(), e.getNombre());
    }

    @Override
    public List<PaisDomain> toDomain(List<PaisEntity> entityList) {
        var lista = new ArrayList<PaisDomain>();
        if (UtilObjeto.getInstance().esNulo(entityList)) {
            return lista;
        }
        for (var e : entityList) {
            lista.add(toDomain(e));
        }
        return lista;
    }

}
