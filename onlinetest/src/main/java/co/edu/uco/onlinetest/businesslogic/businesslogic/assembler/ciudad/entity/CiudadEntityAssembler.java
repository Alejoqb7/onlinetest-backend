package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.ciudad.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.departamento.entity.DepartamentoEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.CiudadEntity;

public class CiudadEntityAssembler implements EntityAssembler<CiudadEntity, CiudadDomain> {

    private static final CiudadEntityAssembler INSTANCE = new CiudadEntityAssembler();

    private CiudadEntityAssembler() { }

    public static CiudadEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public CiudadEntity toEntity(CiudadDomain domain) {
        if (UtilObjeto.getInstance().esNulo(domain)) {
            return (CiudadEntity) CiudadEntity.obtenerValorDefecto();
        }
        var entity = new CiudadEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        if (!UtilObjeto.getInstance().esNulo(domain.getDepartamento())) {
            entity.setDepartamento(
                DepartamentoEntityAssembler.getInstance().toEntity(domain.getDepartamento())
            );
        }
        return entity;
    }

    @Override
    public CiudadDomain toDomain(CiudadEntity entity) {
        var e = (entity == null ? CiudadEntity.obtenerValorDefecto() : entity);
        return new CiudadDomain(
            ((CiudadDomain) e).getId(),
            ((CiudadDomain) e).getNombre(),
            DepartamentoEntityAssembler.getInstance().toDomain(((CiudadDomain) e).getDepartamento())
        );
    }



    @Override
    public List<CiudadDomain> toDomain(List<CiudadEntity> entityList) {
        var lista = new ArrayList<CiudadDomain>();
        if (UtilObjeto.getInstance().esNulo(entityList)) {
            return lista;
        }
        for (var e : entityList) {
            lista.add(toDomain(e));
        }
        return lista;
    }

    @Override
    public List<CiudadEntity> toEntityList(List<CiudadDomain> domainList) {
        var lista = new ArrayList<CiudadEntity>();
        if (UtilObjeto.getInstance().esNulo(domainList)) {
            return lista;
        }
        for (var d : domainList) {
            lista.add(toEntity(d));
        }
        return lista;
    }
}
