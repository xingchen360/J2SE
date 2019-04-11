package com.noteshare.enums;

public class Test {
    
    @org.junit.Test
    public void colorEnum(){
        //枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值
        ColorEnum colorEnum = ColorEnum.BLUE;
        switch (colorEnum) {
            case RED:
                System.out.println("color is red");
                break;
            case GREEN:
                System.out.println("color is green");
                break;
            case YELLOW:
                System.out.println("color is yellow");
                break;
            case BLUE:
                System.out.println("color is blue");
                break;
        }
        
        //遍历枚举
        System.out.println("遍历ColorEnum枚举中的值");
        for(ColorEnum color : ColorEnum.values()){
            System.out.println(color);
        }
        
        //获取枚举的个数
        System.out.println("ColorEnum枚举中的值有:"+ColorEnum.values().length+"个");
        
        //获取枚举的索引位置，默认从0开始
        System.out.println("索引位置："+ColorEnum.RED.ordinal());//0
        System.out.println("索引位置："+ColorEnum.GREEN.ordinal());//1
        System.out.println("索引位置："+ColorEnum.YELLOW.ordinal());//2
        System.out.println("索引位置："+ColorEnum.BLUE.ordinal());//3
        
        //枚举默认实现了java.lang.Comparable接口
        System.out.println("类型比较："+ColorEnum.RED.compareTo(ColorEnum.GREEN));//-1
    }
    
    @org.junit.Test
    public void seasonEnum(){
        System.out.println("季节为：" + SeasonEnum.getSeason());
        for(SeasonEnum season : SeasonEnum.values()){
        	System.out.println(season.name() + ":" + season.ordinal());
        }
    }
    
    @org.junit.Test
    public void genderEnum(){
        for(GenderEnum gender : GenderEnum.values()){
        	System.out.println(gender);
            System.out.println("迭代得到的code值："+gender.code);
            System.out.println("迭代得到的name值："+gender.name());
        }
        
        if(GenderEnum.MAN.getCode().equals("0")){
            System.out.println("true");
            System.out.println(GenderEnum.getByCode("0").getDesc());
        }
        
        System.out.println(GenderEnum.nameOf("man"));
    }
    
    @org.junit.Test
    public void orderStateEnum(){
        for(OrderStateEnum order : OrderStateEnum.values()){
            System.out.println(order);
            System.out.println(order.getName());
        }
        System.out.println(OrderStateEnum.CANCEL.toString());
    }
    
}
