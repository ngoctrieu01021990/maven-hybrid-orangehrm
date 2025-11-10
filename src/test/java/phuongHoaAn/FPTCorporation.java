package phuongHoaAn;

public class FPTCorporation {
    //Thuộc tính
    public String tvName;
    protected String tvColor;
    String tvChannel;
    private String tvVolumn;//chỉ dùng được trong chính class này

    //Phương thức
    public void setTvName(){
        System.out.println(tvName);
    }

    //trong cùng 1 package thì dùng được thông qua khởi tạo hoặc kế thừa
    //khác package thì phải thông qua quan hệ kế thừa mới dùng được
    protected void setTvColor(){

    }

    //chỉ dùng trong package
    void setTvChannel(){

    }
     private void setTvVolumn(){

     }
}
