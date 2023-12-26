package day01.ex05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private TransactionsService transactionsService;

    private Boolean isDevelop_;

    public Controller(TransactionsService transactionsService, Boolean isDevelop) {
        this.transactionsService = transactionsService;
        this.isDevelop_ = isDevelop;
    }

    public Map<Boolean, String> addUserAction(String name, Double balance) {
        try {
            if (balance < 0) {
                return createErrorResponse("balance has negate value.");
            }
            transactionsService.addUser(new User(name, balance));
            return createSuccessResponse();
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public Map<Boolean, String> viewUserBalancesAction(Integer userID) {
        try {
            String result = transactionsService.getUserName(userID);
            result += " - " + transactionsService.getUserBalance(userID).toString();

            return createSuccessResponse(result);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public Map<Boolean, String> performTransferAction(Integer senderID, Integer recipientID, Double transferAmount) {
        try {
            transactionsService.performTransaction(senderID, recipientID, transferAmount);

            return createSuccessResponse();
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public Map<Boolean, String> removeTransferByIdAction(Integer userID, Integer transactionID) {
        try {
            transactionsService.removeTransaction(userID, transactionID);

            return createSuccessResponse();
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public ArrayList<String> viewTransactionsForUserAction(Integer userID) {
        ArrayList<String> responce = new ArrayList<>();

        try {

            Transaction[] res = transactionsService.getUserTransactions(userID).toArray();
            for (Transaction transaction : res) {
                responce.add(
                        "To " + transaction.getRecipient().getName() + " " + transaction.getTransferAmount().toString()
                                + " with id transaction = " + transaction.getId());
            }
            return responce;
        } catch (Exception e) {
            responce.clear();
            return responce;
        }
    }

    public ArrayList<String> checkTransferValidityAction() {
        ArrayList<String> responce = new ArrayList<>();

        try {

            ArrayList<Transaction> res = transactionsService.getUnpairedTransactions();
            for (Transaction transaction : res) {
                responce.add(
                        transaction.getSender().getName() + " ( id user = " + transaction.getSender().getIdentifier()
                                + " ) " + " has an unacknowledged transfer amount = "
                                + transaction.getTransferAmount().toString()
                                + " with id transaction = " + transaction.getId());
            }
            return responce;
        } catch (Exception e) {
            responce.clear();
            return responce;
        }
    }

    public boolean isDevelopAction() {
        return this.isDevelop_;
    }

    private Map<Boolean, String> createSuccessResponse() {
        Map<Boolean, String> response = new HashMap<>();
        response.put(true, "successfully");
        return response;
    }

    private Map<Boolean, String> createSuccessResponse(String result) {
        Map<Boolean, String> response = new HashMap<>();
        response.put(true, result);
        return response;
    }

    private Map<Boolean, String> createErrorResponse(String errorMessage) {
        Map<Boolean, String> response = new HashMap<>();
        response.put(false, errorMessage);
        return response;
    }
}