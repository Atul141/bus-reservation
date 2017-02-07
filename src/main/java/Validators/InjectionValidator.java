package Validators;


public class InjectionValidator {

    public boolean validateInjection(String value){
        if(value.contains("<")||value.contains(">")||value.contains("(")||value.contains(")")||value.contains("/")||value.contains("{")||value.contains("}")
                ||value.contains("/")||value.contains("=")||value.contains(";"))
            return true;
        return false;
    }
}
