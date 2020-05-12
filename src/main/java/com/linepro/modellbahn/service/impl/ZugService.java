package com.linepro.modellbahn.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linepro.modellbahn.entity.Zug;
import com.linepro.modellbahn.entity.ZugConsist;
import com.linepro.modellbahn.model.ZugModel;
import com.linepro.modellbahn.repository.ZugRepository;
import com.linepro.modellbahn.service.ItemService;

/**
 * ZugService. CRUD service for Zug
 * @author $Author:$
 * @version $Id:$
 */
@Service
public class ZugService extends NamedItemServiceImpl<ZugModel,Zug> implements ItemService<ZugModel> {

    @Autowired
    public ZugService(ZugRepository repository) {
        super(repository, () -> new ZugModel(), () -> new Zug());
    }

    public Optional<ZugConsist> addConsist(String zugStr, String artikelId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Optional<ZugConsist> updateConsist(String zugStr, Integer position, String artikelId) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean deleteConsist(String zugStr, Integer position) {
        // TODO Auto-generated method stub
        return false;
    }
}
