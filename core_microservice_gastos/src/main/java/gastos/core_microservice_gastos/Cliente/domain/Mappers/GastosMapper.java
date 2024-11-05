package gastos.core_microservice_gastos.Cliente.domain.Mappers;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GastosMapper {
    GastosMapper INSTANCE = Mappers.getMapper(GastosMapper.class);


    GastoModel gastoToGastoModel(Gasto gasto);
}
