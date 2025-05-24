package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.entity;

import java.util.ArrayList;

import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.List;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisEntityAssembler implements EntityAssembler<PaisEntity, PaisDomain> {

	private static final PaisEntityAssembler INSTANCE = new PaisEntityAssembler();
	
	private PaisEntityAssembler() {
		super();
	}
	
	public static PaisEntityAssembler getInstance() {
		return INSTANCE;
	}
	
	
	@Override
	public PaisEntity toEntity(PaisDomain domain) {
		return UtilObjeto.getInstance().esNulo(null)
				? PaisEntity.obtenerValorDefecto()
				: new PaisEntity(domain.getId(), domain.getNombre());
	}

	@Override
	public PaisDomain toDomain(PaisEntity entity) {
		var PaisAEnsamblar = PaisEntity.obtenerValorDefecto(entity);
		return new PaisDomain(PaisAEnsamblar.getId(), PaisAEnsamblar.getNombre());
	}

	@Override
	public List<PaisDomain> toDomain(List<PaisEntity> entityList) {
		
		var listaResultados = new ArrayList<PaisDomain>();
		
		for(PaisEntity paisEntity : entityList) {
			listaResultados.add(toDomain(paisEntity));
		}
		
		return null;
	}
	
}
