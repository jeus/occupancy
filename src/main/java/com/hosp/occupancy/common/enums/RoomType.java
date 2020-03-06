/*
 * @author jeus (suje@protonmail.com)
 * @since 3/2/20
 */
package com.hosp.occupancy.common.enums;

public enum RoomType implements Parameter {

    ECONOMY(Id.ECONOMY, Type.ECONOMY, Symbol.ECONOMY,MinPrice.ECONOMY),
    PREMIUM(Id.PREMIUM, Type.PREMIUM, Symbol.PREMIUM,MinPrice.PREMIUM);

   private Long id;
   private String type;
   private String symbol;
   private int minPrice;

     RoomType(Long id, String type, String symbol,int minPrice) {
        this.id = id;
        this.type = type;
        this.symbol = symbol;
        this.minPrice = minPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCode() {
        return type;
    }

    @Override
    public ParameterGroup getGroup() {
        return ParameterGroup.ROOMTYPE;
    }

    public int getMinPrice() {return minPrice;}

   public static class Id {
        public static final Long PREMIUM = 1L;
        public static final Long ECONOMY = 2L;
    }

    public static class Type {
        public static final String PREMIUM = "PREMIUM";
        public static final String ECONOMY = "ECONOMY";
    }

    public static class Symbol {
        public static final String PREMIUM = "PREMIUM";
        public static final String ECONOMY = "ECONOMY";
    }

    public static class MinPrice {
        public static final int PREMIUM = 100;
        public static final int ECONOMY = 1;
    }

}
