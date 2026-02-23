package ${basePackage}.repository;

import ${basePackage}.entity.${entityName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ${className} extends JpaRepository<${entityName}, Long>, JpaSpecificationExecutor<${entityName}> {
}
