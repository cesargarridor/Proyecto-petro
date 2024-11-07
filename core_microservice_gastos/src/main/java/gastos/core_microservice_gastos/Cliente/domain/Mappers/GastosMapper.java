package gastos.core_microservice_gastos.Cliente.domain.Mappers;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.input.GastoInput;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface GastosMapper {

    GastoModel gastoToGastoModel(Gasto gasto);

    @Mapping(target = "pk", source="clientId")
    @Mapping(target = "sk", expression = "java(Gasto.PATTERN_SK + gastoModel.getGastoId())")
    @Mapping(target = "gIndexPk", source = "gastoId")
    @Mapping(target="estado",constant="true")
    @Mapping(target="fechaCreacion" ,source="fechaCreacion",dateFormat = "yyyy-MM-dd")
    Gasto modelToEntity(GastoModel gastoModel);

    @Mapping(target="estado",constant="true")
    @Mapping(target="fechaCreacion", source="fechaCreacion")
    GastoModel InputToModel(GastoInput gasto);


/*@AfterMapping
default void setPkandSk(GastoModel gastoModel, @MappingTarget Gasto gasto){
       gasto.setPk(gastoModel.getClientId());
       gasto.setSk("GASTOS");
}*/
}