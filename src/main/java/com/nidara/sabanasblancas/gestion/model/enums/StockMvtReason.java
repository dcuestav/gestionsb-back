package com.nidara.sabanasblancas.gestion.model.enums;

public enum StockMvtReason {

    NOT_VALID(0, 1),
    INCREASE(1, 1),
    DECREASE(2, -1),
    CUSTOMER_ORDER(3, -1),
    DECREASE_DUE_TO_INVENTORY(4, -1),
    INCREASE_DUE_TO_INVENTORY(5, 1),
    TRANSFER_TO_WAREHOUSE(6, -1),
    TRANSFER_FROM_WAREHOUSE(7, -1),
    SUPPLY_ORDER(8, 1),
    CUSTOMER_ORDER_X(9, 1),
    PRODUCT_RETURN(10, 1),
    INCREASE_DUE_TO_EMPLOYEE_EDITION(11, 1),
    DECREASE_DUE_TO_EMPLOYEE_EDITION(12, -1);

    private final int value;
    private final int sign; // 1 o -1

    StockMvtReason(int value, int sign) {
        this.value = value;
        this.sign = sign;
    }

    public int getValue() {
        return value;
    }

    public int getSign() {
        return sign;
    }
}
