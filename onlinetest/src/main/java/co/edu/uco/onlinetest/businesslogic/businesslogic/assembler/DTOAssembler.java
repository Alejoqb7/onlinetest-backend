package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler;

import java.util.List;

public interface DTOAssembler <T, D> {
	
	T toDTO(D domain);
	
	D toDomain(T dto);
	
	List<D> toDomain(List<T> dtoList);
}