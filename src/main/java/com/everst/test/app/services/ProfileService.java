package com.everst.test.app.services;

import com.everst.test.app.models.Profiles;
import com.everst.test.dto.BalanceRequest;
import com.everst.test.dto.BestProfessionResponse;

import java.util.List;
import java.util.Map;

/**
 * @author Cesar on 02/12/22.
 * @project Everst
 */
public interface ProfileService {

    Profiles getProfile(Long id);

    Profiles update(Profiles profiles);

    Map<String, String> depositMoneyClient(Profiles profiles, BalanceRequest balanceRequest) throws Exception;

    List<BestProfessionResponse> bestClients();

}
