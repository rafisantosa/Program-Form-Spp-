package pbo_m13_520411352;

public class NimHarusPositifException extends Exception{
    @Override
    public String getMessage(){
        return "Inputan NIM harus bilangan positif";
    }
}
