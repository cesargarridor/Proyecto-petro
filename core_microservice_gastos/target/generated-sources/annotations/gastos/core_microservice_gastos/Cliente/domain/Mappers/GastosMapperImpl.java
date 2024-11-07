package gastos.core_microservice_gastos.Cliente.domain.Mappers;

import gastos.core_microservice_gastos.Cliente.domain.Gasto;
import gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO.GastoModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T09:29:53+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class GastosMapperImpl implements GastosMapper {

    @Override
    public GastoModel gastoToGastoModel(Gasto gasto) {
        if ( gasto == null ) {
            return null;
        }

        GastoModel gastoModel = new GastoModel();

        gastoModel.setClientId( gasto.getClientId() );
        gastoModel.setGastoId( gasto.getGastoId() );
        gastoModel.setCantidad( gasto.getCantidad() );
        gastoModel.setEstado( gasto.isEstado() );

        return gastoModel;
    }

    @Override
    public Gasto modelToEntity(GastoModel gastoModel) {
        if ( gastoModel == null ) {
            return null;
        }

        Gasto.GastoBuilder<?, ?> gasto = Gasto.builder();

        gasto.pk( gastoModel.getClientId() );
        gasto.gIndexPk( gastoModel.getGastoId() );
        gasto.clientId( gastoModel.getClientId() );
        gasto.gastoId( gastoModel.getGastoId() );
        gasto.cantidad( gastoModel.getCantidad() );
        gasto.estado( gastoModel.isEstado() );

        gasto.sk( Gasto.PATTERN_SK + gastoModel.getGastoId() );

        return gasto.build();
    }
}
