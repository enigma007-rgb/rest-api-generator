package ${basePackage}.mapper;

import ${basePackage}.dto.${dtoClass};
import ${basePackage}.entity.${entityName};
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ${className} {

    ${entityName} toEntity(${dtoClass} dto);

    ${dtoClass} toDto(${entityName} entity);

    void updateEntityFromDto(${dtoClass} dto, @MappingTarget ${entityName} entity);
}
