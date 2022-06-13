package pbo_m13_520411352;

public class SemesterHarusValid extends Exception{
    @Override
    public String getMessage(){
        return "Inputan semester di luar range masa studi";
    }  
}
