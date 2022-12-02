package com.everst.test.app.dao;

import com.everst.test.dto.BestProfessionResponse;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
@Repository
public class ProfileDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BestProfessionResponse> professionWithHigherPayments() {
        Query query = entityManager.createNativeQuery(
                        "SELECT p.id, p.firstName || ' ' || p.lastName as fullName, sum(price) as paid FROM Profiles p " +
                                "INNER JOIN Contracts c on c.ClientId = p.id " + " INNER JOIN Jobs j on c.id = j.ContractId " +
                                "WHERE j.paid = 1 GROUP BY j.ContractId ORDER BY paid DESC").unwrap(NativeQuery.class)
                .setResultTransformer(new ResultTransformer() {
                    private final Map<Long, BestProfessionResponse> mapBestProfession = new HashMap<>();

                    @Override
                    public Object transformTuple(Object[] tuple, String[] strings) {

                        Long profileId = ((Number) tuple[0]).longValue();
                        BestProfessionResponse bestProfession = mapBestProfession.get(profileId);

                        if (bestProfession == null) {
                            bestProfession = new BestProfessionResponse();
                            bestProfession.setId(tuple[0] != null ? ((Number) tuple[0]).longValue() : null);
                            bestProfession.setFullName(tuple[1] != null ? (String) tuple[1] : null);
                            bestProfession.setPaid(tuple[2] != null ? ((Number) tuple[2]).doubleValue() : null);
                        }

                        mapBestProfession.putIfAbsent(bestProfession.getId(), bestProfession);
                        return bestProfession;
                    }

                    @Override
                    public List transformList(List list) {
                        return new ArrayList(mapBestProfession.values());
                    }

                });

        return (List<BestProfessionResponse>) query.getResultList();
    }


}
