package com.sainik.tacocloudmay.repository;

import com.sainik.tacocloudmay.models.Taco;
import com.sainik.tacocloudmay.models.TacoOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoOrderRepository implements TacoOrderRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TacoRepository tacoRepository;

    public JdbcTacoOrderRepository(JdbcTemplate jdbcTemplate, TacoRepository tacoRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.tacoRepository = tacoRepository;
    }

    @Override
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            "insert into Taco_Order (delivery_name, delivery_street, delivery_city, " +
            "delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
            Arrays.asList(
                order.getDeliveryName(), order.getDeliveryStreet(), order.getDeliveryCity(),
                order.getDeliveryState(), order.getDeliveryZip(),
                order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(),
                new Timestamp(order.getPlacedAt().getTime())
            )
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        for (Taco taco : order.getTacos()) {
            Taco savedTaco = tacoRepository.save(taco);
            jdbcTemplate.update(
                "insert into Taco_Order_Tacos (taco_order_id, taco_id) values (?, ?)",
                orderId, savedTaco.getId()
            );
        }
        return order;
    }
}
