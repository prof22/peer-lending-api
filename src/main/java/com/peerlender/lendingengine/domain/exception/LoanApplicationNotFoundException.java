package com.peerlender.lendingengine.domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException{

    public LoanApplicationNotFoundException (long loadApplicationId){
        super("Loan application with id: " + loadApplicationId + "was not found" );
    }
}
