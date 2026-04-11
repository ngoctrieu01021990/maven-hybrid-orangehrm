package phuongHoaAn;

public class TPBank {
    //Phạm vi ngoài class nhưng trong cùng package
    public void showTVName(){
        FPTCorporation fpt=new FPTCorporation();
        fpt.tvName="TPbank LCD";
        fpt.setTvName();

        fpt.tvColor="Black";
        fpt.setTvColor();

        fpt.tvChannel="";
        fpt.setTvChannel();

        //fpt.tvVolumn="";
        //fpt.settvVolumn();

    }
}
