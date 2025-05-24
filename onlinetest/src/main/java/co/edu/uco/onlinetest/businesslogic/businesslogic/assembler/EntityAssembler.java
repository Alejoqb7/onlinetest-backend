package co.edu.uco.onlinetest.businesslogic.businesslogic.assembler;

public interface EntityAssembler<E, D>{
	
	E toEntity(D domain);
	
	D toDomain(E entity);
	
	List<D> toDomain(List<E> entityList);
}
