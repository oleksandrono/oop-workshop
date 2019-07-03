package checkout;

import java.time.LocalDate;

public abstract class Offer {

    public final LocalDate expiredDate;

    public abstract void apply(Check check);

    public Offer(LocalDate expiredDate){
        this.expiredDate = expiredDate;
    }


    public boolean checkExpiryDate(){
        if(expiredDate.isAfter(LocalDate.now())){
            return true;
        }
        return false;
    }


}
