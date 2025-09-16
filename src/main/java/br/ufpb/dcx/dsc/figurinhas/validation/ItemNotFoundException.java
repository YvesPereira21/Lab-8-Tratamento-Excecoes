package br.ufpb.dcx.dsc.figurinhas.validation;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
