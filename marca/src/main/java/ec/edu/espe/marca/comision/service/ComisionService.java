package ec.edu.espe.marca.comision.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import ec.edu.espe.marca.comision.model.Comision;
import ec.edu.espe.marca.comision.repository.ComisionRepository;
import ec.edu.espe.marca.transaccion.model.Transaccion;

@Service
public class ComisionService {

    private final ComisionRepository comisionRepository;
    private static final String CREDITO_LABEL_TARJETA = "CRE";
    private static final String CREDITO_LABEL_COMISION= "CREDITO";
    private static final String DEBITO_LABEL_COMISION= "DEBITO";
    private static final String COMISION_PORCENTAJE= "PORCENTAJE";
    private static final String COMISION_FIJO = "FIJO";

    public ComisionService(ComisionRepository comisionRepository) {
        this.comisionRepository = comisionRepository;
    }
    
    public Transaccion calcularComisionTransaccionTarjeta(Transaccion transaccion) {
        Comision comisionAAplicar = obtenerComisionTransaccionTarjeta(transaccion);
        BigDecimal comisionMultiplo;
        BigDecimal montoComision = BigDecimal.ZERO;

        // Valida de que pais procede la transacción y la tarjeta de credito
        if (transaccion.getPaisOrigen().equals(transaccion.getTarjeta().getPaisTarjeta())) {
            comisionMultiplo = comisionAAplicar.getComisionLocal();
        } else {
            comisionMultiplo = comisionAAplicar.getComisionInternacional();
        }
        if (comisionAAplicar.getTipoComision().equals(COMISION_PORCENTAJE)) {
            montoComision = transaccion.getMonto().multiply(comisionMultiplo);
        } else if (comisionAAplicar.getTipoComision().equals(COMISION_FIJO)) {
            montoComision = comisionMultiplo;
        }
        transaccion.setComisionMarca(montoComision.add(comisionAAplicar.getComisionFija()));
        transaccion.setComision(comisionAAplicar);
        transaccion.setCodComision(comisionAAplicar.getCodComision());

        return transaccion;
    }
    
    private Comision obtenerComisionTransaccionTarjeta(Transaccion transaccion) {
        String marcaTarjetaRecibida = obtenerMarca(transaccion.getTarjeta().getNumeroTarjeta());
        String tipoTarjeta = transaccion.getTarjeta().getTipoTarjeta().equals(CREDITO_LABEL_TARJETA) ? CREDITO_LABEL_COMISION : DEBITO_LABEL_COMISION;
        String pais = transaccion.getPaisOrigen();

        Comision comisionEcontradaAAplicar = this.comisionRepository.obtenerComision(marcaTarjetaRecibida, tipoTarjeta, pais);
        if (comisionEcontradaAAplicar == null) {
            throw new RuntimeException("No se encontró en la bd la comisión a aplicar!");
        }
        return comisionEcontradaAAplicar;
    }
        
    @Cacheable("comisiones")
    public List<Object[]> obtenerMarcasYPrefijos() {
        return comisionRepository.obtenerMarcasYPrefijos();
    }

    public String obtenerMarca(String numeroTarjeta) {
        List<Object[]> marcas = obtenerMarcasYPrefijos();
        String marcaTarjeta = "";

        for (Object[] marca : marcas) {
            String prefijoMarca = (String) marca[0]; 
            if (numeroTarjeta.startsWith(prefijoMarca))
            {
                marcaTarjeta = (String) marca[1];
                break;
            }
        }
        if (marcaTarjeta.isEmpty()) {
            throw new RuntimeException("No se encontró la marca de la tarjeta!");
        }

        return marcaTarjeta;
    }


}
