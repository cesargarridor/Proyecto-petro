package gastos.core_microservice_gastos.Cliente.domain.Mappers;


import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface GastosMapper {


    GastoModel gastoToGastoModel(Gasto gasto);

    @Mapping(target = "pk", source="clientId")
    @Mapping(target = "sk", constant="clientGasto")
    @Mapping(target = "gIndexPk", source = "gastoId")
    Gasto modelToEntity(GastoModel gastoModel);



    /*@AfterMapping
    default void setPkandSk(GastoModel gastoModel, @MappingTarget Gasto gasto){
           gasto.setPk(gastoModel.getClientId());
           gasto.setSk("GASTOS");
    }*/

}
