package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler;

public interface DTOAssembler <T, D> {
	
	T toDTO(D domain);
	
	D toDomain(T dto);
	
	List<D> toDomain(List<T> dtoList);

}
