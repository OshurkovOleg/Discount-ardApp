package com.example.dicountcard.services;

import com.example.dicountcard.dto.CheckDTO;
import com.example.dicountcard.dto.CheckPackDTO;
import com.example.dicountcard.dto.ClientDTO;
import com.example.dicountcard.model.Check;
import com.example.dicountcard.model.Client;
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

    Map<Long, ClientDTO> clientMap = new HashMap<>();

    @Autowired
    public CheckService(CheckRepository checkRepository, ClientRepository clientRepository,
                        PositionFromCheckService positionFromCheckService) {
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

//если в мапе клиентов нет номера карты, то добавляем номер карты и клиента со счётом (считаем сумму из всех чеков)
            if (!clientMap.containsKey(checkDTO.getCardNumber())) {
                clientMap.put(checkDTO.getCardNumber(), new ClientDTO(checkDTO.getTotal(), checkDTO.getCardNumber()));
            } else {
                long bill = clientMap.get(checkDTO.getCardNumber()).getBill();
                clientMap.get(checkDTO.getCardNumber()).setBill(bill + checkDTO.getTotal());
            }
        }

//считаем количества баллов на основании суммы всех чеков
        accumulatePoints();

    }

    private void accumulatePoints() {
        for (Map.Entry<Long, ClientDTO> entry : clientMap.entrySet()) {
            Client client = clientRepository.getClientByCardNumber(entry.getKey());
            long bill = entry.getValue().getBill();

            if (bill <= 50_000) {
                client.setCardBalance(bill / 50);
            } else if (bill <= 100_000) {
                client.setCardBalance(bill / 40);
            } else {
                client.setCardBalance(bill / 30);
            }

            clientRepository.save(client);
        }
    }

    private Client saveAndGetClient(CheckDTO checkDTO) {
        return clientRepository.save(new Client(checkDTO.getCardNumber(), 0));
    }

    private void saveCheck(CheckDTO checkDTO, Client client) {
        Check check = checkRepository.save(new Check(checkDTO.getCheckNumber(), checkDTO.getTotal(),
                client));

        checkDTO.getListPosition().stream()
                .peek(e -> e.setCheck(check))
                .forEach(positionFromCheckService::save);
    }

}
