package ua.com.vovacoffee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.vovacoffee.dao.SalePositionDAO;
import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.repository.SalePositionRepository;

@Repository
public class SalePositionDAOImpl extends DataDAOImpl<SalePosition> implements SalePositionDAO {

    @Autowired
    public SalePositionDAOImpl(SalePositionRepository repository) {
        super(repository);
    }
}
