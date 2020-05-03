package com.linepro.modellbahn.converter.base;

import com.linepro.modellbahn.model.base.ItemModel;

public interface ModelCreator<M extends ItemModel> {
    M create();
}
