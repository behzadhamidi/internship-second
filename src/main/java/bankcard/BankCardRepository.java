package bankcard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankCardRepository {
    private Connection connection;

    public BankCardRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCard(BankCard card) throws SQLException {
        String sql = "INSERT INTO bank_cards (cardnumber, cardholdername, expirationdate, cvv, userid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, card.getCardNumber());
            statement.setString(2, card.getCardHolderName());
            statement.setString(3, card.getExpirationDate());
            statement.setString(4, card.getCvv());
            statement.setInt(5, card.getUserId());
            statement.executeUpdate();
        }
    }

    public BankCard getCard(int cardId) throws SQLException {
        String sql = "SELECT * FROM bank_cards WHERE cardid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cardId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new BankCard(
                            resultSet.getInt("cardid"),
                            resultSet.getString("cardnumber"),
                            resultSet.getString("cardholdername"),
                            resultSet.getString("expirationdate"),
                            resultSet.getString("cvv"),
                            resultSet.getInt("userid")
                    );
                }
            }
        }
        return null;
    }

    public List<BankCard> getAllCards() throws SQLException {
        List<BankCard> cards = new ArrayList<>();
        String sql = "SELECT * FROM bank_cards";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                cards.add(new BankCard(
                        resultSet.getInt("cardid"),
                        resultSet.getString("cardnumber"),
                        resultSet.getString("cardholdername"),
                        resultSet.getString("expirationdate"),
                        resultSet.getString("cvv"),
                        resultSet.getInt("userid")
                ));
            }
        }
        return cards;
    }

    public void updateCard(BankCard card) throws SQLException {
        String sql = "UPDATE bank_cards SET cardnumber = ?, cardholdername = ?, expirationdate = ?, cvv = ?, userid = ? WHERE cardid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, card.getCardNumber());
            statement.setString(2, card.getCardHolderName());
            statement.setString(3, card.getExpirationDate());
            statement.setString(4, card.getCvv());
            statement.setInt(5, card.getUserId());
            statement.setInt(6, card.getCardId());
            statement.executeUpdate();
        }
    }

    public void deleteCard(int cardId) throws SQLException {
        String sql = "DELETE FROM bank_cards WHERE cardid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cardId);
            statement.executeUpdate();
        }
    }
}


