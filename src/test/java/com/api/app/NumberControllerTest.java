package com.api.app;

import com.api.app.models.RequestModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberControllerTest {
    private NumberController numberController = new NumberController();

    @Test
    public void isMiddleNumberEqual15() {
        RequestModel model = new RequestModel();
        model.setMiddleNumber(15.0);

        assertThrows(RuntimeException.class, () -> numberController.random(model));
    }

}