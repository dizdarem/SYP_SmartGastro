package at.htl_villach.feedbackapp_with_firebase.bll;

public class Feedback {
    private int id;
    private String opinion;
    private int rate;

    public Feedback(int id, String opinion, int rate) {
        this.id = id;
        this.opinion = opinion;
        this.rate = rate;
    }

    public  Feedback(){

    }

    @Override
    public String toString() {
        String result = "";
        if(rate == 1) {
            result = opinion + "\n" + rate + " Stern";
        }
        else{
            result = opinion + "\n" + rate + " Sterne";
        }
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
