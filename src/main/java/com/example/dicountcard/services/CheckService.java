package com.example.dicountcard.services;

import com.example.dicountcard.dto.CheckDTO;
import com.example.dicountcard.dto.CheckPackDTO;
import com.example.dicountcard.dto.ClientDTO;
import com.example.dicountcard.model.Check;
import com.example.dicountcard.model.Client;
import com.example.dicountcard.model.PositionFromCheck;
import com.example.dicountcard.repository.CheckRepository;
import com.example.dicountcard.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(CheckPackDTO checkPackDTO) {

// идем по списку входящих чеков
        for (CheckDTO checkDTO : checkPackDTO.getCheckList()) {
            Client client = clientRepository.getClientByCardNumber(checkDTO.getCardNumber());

//проверяем есть ли клиент в базе, если нет, то добавляем и сохраняем чек - иначе просто сохраняем чек
            if (client == null) {
                Client newClient = saveAndGetClient(checkDTO);
                saveCheck(checkDTO, newClient);
            } else {
                saveCheck(checkDTO, client);
            }

// вычитаем баллы с баланса карты, если клиент использовал их при покупке
            writeOffPointsAccount(checkDTO, client);

//если в мапе клиентов нет номера карты, то добавляем номер карты и клиента со счётом (считаем сумму из всех чеков)
            if (!clientMap.containsKey(checkDTO.getCardNumber())) {
                clientMap.put(checkDTO.getCardNumber(), new ClientDTO(checkDTO.getCheckAmount(), checkDTO.getCardNumber()));
            } else {
                long bill = clientMap.get(checkDTO.getCardNumber()).getAccount();
                clientMap.get(checkDTO.getCardNumber()).setAccount(bill + checkDTO.getCheckAmount());
            }
        }

//считаем количества баллов на основании суммы всех чеков и сохраняем на балансе
        putPointsOnAccount();
        clientMap.clear();
    }

    private void writeOffPointsAccount(CheckDTO checkDTO, Client client) {

        long sumPositions = 0;

        for (PositionFromCheck position : checkDTO.getListPosition()) {
            sumPositions += position.getPositionAmount();
        }

        long points = sumPositions - checkDTO.getCheckAmount();
        if (points != 0) {
            long newBalanceValue = client.getCardBalance() - points;
            client.setCardBalance(newBalanceValue);
            clientRepository.save(client);
        }
    }

    private void putPointsOnAccount() {

        for (Map.Entry<Long, ClientDTO> entry : clientMap.entrySet()) {
            Client client = clientRepository.getClientByCardNumber(entry.getKey());

            long account = entry.getValue().getAccount();
            long points;

            if (account <= 50_000) {
                points = account / 50;
                client.setCardBalance(client.getCardBalance() + points);
            } else if (account <= 100_000) {
                points = account / 40;
                client.setCardBalance(client.getCardBalance() + points);
            } else {
                points = account / 30;
                client.setCardBalance(client.getCardBalance() + points);
            }

            clientRepository.save(client);
        }
    }

    private Client saveAndGetClient(CheckDTO checkDTO) {
        return clientRepository.save(new Client(checkDTO.getCardNumber(), 0));
    }

    private void saveCheck(CheckDTO checkDTO, Client client) {
        Check newCheck = new Check(checkDTO.getCheckNumber(), checkDTO.getCheckAmount(), client);
        Check check = checkRepository.save(newCheck);

        checkDTO.getListPosition().stream()
                .peek(e -> e.setCheck(check))
                .forEach(positionFromCheckService::save);
    }
}
