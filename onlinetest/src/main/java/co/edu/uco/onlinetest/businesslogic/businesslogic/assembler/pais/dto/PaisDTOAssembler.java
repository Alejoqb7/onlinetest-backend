package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler.pais.dto;

public interface PaisDTOAssembler implements DTOAssembler<PaisDTO, PaisDomain> {
	
	PaisDTO toDTO(PaisDomain domain);
	
	PaisDomain toDomain(PaisDTO dto);
	
	List<PaisDomain> toDomain(List<PaisDTO> dtoList);
	
	

}
