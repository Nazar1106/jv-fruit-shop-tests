package core.basesyntax.service.impl;

import static core.basesyntax.db.Storage.storage;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.dao.impl.FruitDaoImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReturnOperationTest {
    private FruitDao fruitDao = new FruitDaoImpl();
    private OperationStrategy returnOperation = new ReturnOperation(fruitDao);

    @BeforeEach
    void setUp() {
        storage.clear();
    }

    @Test
    void return_operation_successful() {
        FruitTransaction fruitTransaction = new
                FruitTransaction(FruitTransaction.Operation.RETURN, "banana", 25);

        returnOperation.execute(fruitTransaction);
        int actual = storage.get("banana");
        int expected = 25;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void return_dataExist_successful() {
        storage.put("banana", 25);
        FruitTransaction fruitTransaction = new
                FruitTransaction(FruitTransaction.Operation.RETURN, "banana", 25);
        returnOperation.execute(fruitTransaction);
        int actual = storage.get("banana");
        int expected = 50;
        Assertions.assertEquals(expected, actual);
    }
}
