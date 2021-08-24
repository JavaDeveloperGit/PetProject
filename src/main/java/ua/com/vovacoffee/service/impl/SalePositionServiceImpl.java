package ua.com.vovacoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.vovacoffee.dao.SalePositionDAO;
import ua.com.vovacoffee.model.SalePosition;
import ua.com.vovacoffee.service.SalePositionService;

@Service
public class SalePositionServiceImpl extends MainServiceImpl<SalePosition> implements SalePositionService {

    @Autowired
    public SalePositionServiceImpl(SalePositionDAO dao) {
        super(dao);
    }
}
