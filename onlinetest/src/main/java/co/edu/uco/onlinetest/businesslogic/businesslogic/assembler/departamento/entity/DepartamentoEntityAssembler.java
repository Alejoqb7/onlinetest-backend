package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.departamento.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.entity.PaisEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

public class DepartamentoEntityAssembler implements EntityAssembler<DepartamentoEntity, DepartamentoDomain> {

	private static final DepartamentoEntityAssembler INSTANCE = new DepartamentoEntityAssembler();

	private DepartamentoEntityAssembler() {
	}

	public static DepartamentoEntityAssembler getInstance() {
		return INSTANCE;
	}

	@Override
	public DepartamentoEntity toEntity(DepartamentoDomain domain) {
		if (UtilObjeto.getInstance().esNulo(domain)) {
			return DepartamentoEntity.obtenerValorDefecto();
		}
		var entity = new DepartamentoEntity();
		entity.setId(domain.getId());
		entity.setNombre(domain.getNombre());
		if (!UtilObjeto.getInstance().esNulo(domain.getPais())) {
			entity.setPais(PaisEntityAssembler.getInstance().toEntity(domain.getPais()));
		}
		return entity;
	}

	@Override
	public DepartamentoDomain toDomain(DepartamentoEntity entity) {
		var e = (entity == null ? DepartamentoEntity.obtenerValorDefecto() : entity);
		return new DepartamentoDomain(e.getId(), e.getNombre(),
				PaisEntityAssembler.getInstance().toDomain(e.getPais()));
	}

	@Override
	public List<DepartamentoDomain> toDomain(List<DepartamentoEntity> entityList) {
		var lista = new ArrayList<DepartamentoDomain>();
		if (UtilObjeto.getInstance().esNulo(entityList)) {
			return lista;
		}
		for (var e : entityList) {
			lista.add(toDomain(e));
		}
		return lista;
	}

	@Override
	public List<DepartamentoEntity> toEntityList(List<DepartamentoDomain> domainList) {
		var lista = new ArrayList<DepartamentoEntity>();
		if (UtilObjeto.getInstance().esNulo(domainList)) {
			return lista;
		}
		for (var d : domainList) {
			lista.add(toEntity(d));
		}
		return lista;
	}

	public DepartamentoDomain toDomain(DepartamentoDomain departamento) {
		// TODO Auto-generated method stub
		return null;
	}
}
