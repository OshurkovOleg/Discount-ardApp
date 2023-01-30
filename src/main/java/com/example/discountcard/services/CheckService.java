package com.example.discountcard.services;

import com.example.discountcard.dto.CheckDTO;
import com.example.discountcard.dto.CheckPackDTO;
import com.example.discountcard.dto.ClientDTO;
import com.example.discountcard.entities.CheckEntity;
import com.example.discountcard.entities.ClientEntity;
import com.example.discountcard.entities.PositionFromCheckEntity;
import com.example.discountcard.mappers.MapperObject;
import com.example.discountcard.repository.CheckRepository;
import com.example.discountcard.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheckService {

    private final CheckRepository checkRepository;
    private final ClientRepository clientRepository;
    private final PositionFromCheckService positionFromCheckService;
    private final Map<Long, ClientDTO> clientMap = new HashMap<>();

    @Autowired
    public CheckService(CheckRepository checkRepository, ClientRepository clientRepository, PositionFromCheckService positionFromCheckService) {
        this.checkRepository = checkRepository;
        this.clientRepository = clientRepository;
        this.positionFromCheckService = positionFromCheckService;
    }

    @Transactional
    public void save(CheckPackDTO checkPackDTO) {

// идем по списку входящих чеков
        for (CheckDTO checkDTO : checkPackDTO.getCheckList()) {
            ClientEntity clientEntity = clientRepository.getClientByCardNumber(checkDTO.getCardNumber());

//проверяем есть ли клиент в базе, если нет, то добавляем и сохраняем чек - иначе просто сохраняем чек
            if (clientEntity == null) {
                ClientEntity newClientEntity = saveAndGetClient(checkDTO);
                saveCheck(checkDTO, newClientEntity);
            } else {
                saveCheck(checkDTO, clientEntity);
            }

// вычитаем баллы с баланса карты, если клиент использовал их при покупке
            writeOffPointsAccount(checkDTO, clientEntity);

//если в мапе клиентов нет номера карты, то добавляем номер карты и клиента и прокидываем сумму чека на баланс для
//подсчёта баллов, если карта уже есть, то получаем баланс карты и прибавляем сумму чека к нему
            if (!clientMap.containsKey(checkDTO.getCardNumber())) {
                clientMap.put(checkDTO.getCardNumber(), ClientDTO.builder()
                        .balance(checkDTO.getPrice())
                        .cardNumber(checkDTO.getCardNumber())
                        .build());
            } else {
                long bill = clientMap.get(checkDTO.getCardNumber()).getBalance();
                long newBalance = bill + checkDTO.getPrice();
                clientMap.get(checkDTO.getCardNumber()).setBalance(newBalance);
            }
        }

//считаем количества баллов для начисления на основании мапы (на основании сумм всех чеков) и сохраняем баллы на карте
        putPointsOnAccount();
        clientMap.clear();
    }


    private void writeOffPointsAccount(CheckDTO checkDTO, ClientEntity clientEntity) {

        long sumPositions = 0;

        for (PositionFromCheckEntity position : checkDTO.getListPosition()) { //stream
            sumPositions += position.getPrice();
        }

        long points = sumPositions - checkDTO.getPrice();

        if (points != 0) {
            long newBalanceValue = clientEntity.getCardBalance() - points;
            clientEntity.setCardBalance(newBalanceValue);
            clientRepository.save(clientEntity);
        }
    }

    private void putPointsOnAccount() {

        for (Map.Entry<Long, ClientDTO> entry : clientMap.entrySet()) {
            ClientEntity clientEntity = clientRepository.getClientByCardNumber(entry.getKey());

            long account = entry.getValue().getBalance();
            long points;

            if (account <= 50_000) {
                points = account / 50;
                clientEntity.setCardBalance(clientEntity.getCardBalance() + points);
            } else if (account <= 100_000) {
                points = account / 40;
                clientEntity.setCardBalance(clientEntity.getCardBalance() + points);
            } else {
                points = account / 30;
                clientEntity.setCardBalance(clientEntity.getCardBalance() + points);
            }

            clientRepository.save(clientEntity);
        }
    }

    private ClientEntity saveAndGetClient(CheckDTO checkDTO) {
        ClientEntity clientEntity = MapperObject.getNewClientEntityUsingCardNumberFromCheckDTO(checkDTO);
        return clientRepository.save(clientEntity);
    }

    private void saveCheck(CheckDTO checkDTO, ClientEntity clientEntity) {

        CheckEntity checkEntity = MapperObject.getCheckEntityFromCheckDTO(checkDTO, clientEntity);

        checkDTO.getListPosition().stream()
                .peek(e -> e.setCheckEntity(checkEntity))
                .forEach(positionFromCheckService::save);

        checkRepository.save(checkEntity);

    }
}
