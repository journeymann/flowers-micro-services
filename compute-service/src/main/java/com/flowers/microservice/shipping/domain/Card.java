package com.flowers.microservice.shipping.domain;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class Card extends Model{

    private String longNum;
    private String expires;
    private String ccv;

    public Card() {
    }

    public Card(String id, String longNum, String expires, String ccv) {
        this.id = id;
        this.longNum = longNum;
        this.expires = expires;
        this.ccv = ccv;
    }

    public Card(String longNum, String expires, String ccv) {
        this(null, longNum, expires, ccv);
    }

    public String getLongNum() {
        return longNum;
    }

    public void setLongNum(String longNum) {
        this.longNum = longNum;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    private String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card obj = (Card) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("Card{id: %s,longNum: %s,expires: %s,ccv: %s}",id,longNum,expires,ccv);

    }
    
}