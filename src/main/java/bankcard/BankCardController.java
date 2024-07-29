package bankcard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BankCardController {
    private BankCardService bankCardService;

    public BankCardController(Connection connection) {
        this.bankCardService = new BankCardService(connection);
    }

    public void addCard(BankCard card) throws SQLException {
        bankCardService.addCard(card);
    }

    public BankCard getCard(int cardId) throws SQLException {
        return bankCardService.getCard(cardId);
    }

    public List<BankCard> getAllCards() throws SQLException {
        return bankCardService.getAllCards();
    }

    public void updateCard(BankCard card) throws SQLException {
        bankCardService.updateCard(card);
    }

    public void deleteCard(int cardId) throws SQLException {
        bankCardService.deleteCard(cardId);
    }
}
