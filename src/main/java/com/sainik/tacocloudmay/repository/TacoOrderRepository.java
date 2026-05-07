package com.sainik.tacocloudmay.repository;

import com.sainik.tacocloudmay.models.TacoOrder;

public interface TacoOrderRepository {
    TacoOrder save(TacoOrder order);
}
