package ar.edu.frc.utn.bda3k4.northwind.services.implementations;

import ar.edu.frc.utn.bda3k4.northwind.entities.Shipper;
import ar.edu.frc.utn.bda3k4.northwind.repositories.ShipperRepository;
import ar.edu.frc.utn.bda3k4.northwind.services.interfaces.ShipperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;

    public ShipperServiceImpl(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    @Override
    public Shipper add(Shipper entity) {
        return this.shipperRepository.save(entity);
    }

    @Override
    public Shipper update(Integer id, Shipper entity) {
        Shipper shipper = this.shipperRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Shipper not found"));
        shipper.update(entity);
        return this.shipperRepository.save(shipper);
    }

    @Override
    public Shipper delete(Integer id) {
        Shipper shipper = this.shipperRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Shipper not found"));
        if(shipper != null){
            this.shipperRepository.delete(shipper);
            return shipper;
        }
        throw new IllegalArgumentException("Shipper not found");
    }

    @Override
    public Shipper findById(Integer id) {
        return this.shipperRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Shipper not found"));
    }

    @Override
    public List<Shipper> findAll() {
        List<Shipper> shippers = this.shipperRepository.findAll();
        if(shippers.isEmpty()){throw new IllegalArgumentException("No shippers found");}
        return shippers;
    }
}
