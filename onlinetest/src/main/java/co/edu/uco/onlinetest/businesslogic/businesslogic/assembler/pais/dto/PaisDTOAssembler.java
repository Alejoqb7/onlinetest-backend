package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.dto;

import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.DTOAssembler;
import co.edu.uco.onlinetest.businesslogic.businesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.dto.PaisDTO;

public final class PaisDTOAssembler implements DTOAssembler<PaisDTO, PaisDomain> {
	
	private static final PaisDTOAssembler INSTANCE = new PaisDTOAssembler();
	
	private PaisDTOAssembler() {
		super();
	}
	
	public static PaisDTOAssembler getInstance() {
		return INSTANCE;
	}
	
	@Override
	public PaisDTO toDTO(PaisDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaisDomain toDomain(PaisDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDomain> toDomain(List<PaisDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}


}
