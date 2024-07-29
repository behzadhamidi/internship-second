package bankcard;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankCardService {
    private BankCardRepository bankCardRepository;

    public BankCardService(Connection connection) {
        this.bankCardRepository = new BankCardRepository(connection);
    }

    public void addCard(BankCard card) throws SQLException {
        bankCardRepository.addCard(card);
    }

    public BankCard getCard(int cardId) throws SQLException {
        return bankCardRepository.getCard(cardId);
    }

    public List<BankCard> getAllCards() throws SQLException {
        return bankCardRepository.getAllCards();
    }

    public void updateCard(BankCard card) throws SQLException {
        bankCardRepository.updateCard(card);
    }

    public void deleteCard(int cardId) throws SQLException {
        bankCardRepository.deleteCard(cardId);
    }
}
