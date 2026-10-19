package ie.dbs.tennisclubsite.court;

import jakarta.persistence.*;

@Entity
@Table(name = "court")
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtID;

    @Column(nullable = false)
    private int courtNum;

    @Column(nullable = false)
    private String courtType;

    public Court() {
    }

    public Court(int courtNum, String courtType) {
        this.courtNum = courtNum;
        this.courtType = courtType;
    }

    public Court(
            Long courtID,
            int courtNum,
            String courtType) {

        this.courtID = courtID;
        this.courtNum = courtNum;
        this.courtType = courtType;
    }

    public Long getCourtID() { return courtID; }

    public void setCourtID(Long courtID) { this.courtID = courtID; }

    public int getCourtNum() { return courtNum; }

    public void setCourtNum(int courtNum) { this.courtNum = courtNum; }

    public String getCourtType() { return courtType; }

    public void setCourtType(String courtType) { this.courtType = courtType; }


}
