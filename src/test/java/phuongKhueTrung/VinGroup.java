package phuongKhueTrung;

import phuongHoaAn.FPTCorporation;

public class VinGroup {
    //Phạm vi ngoài class & khác package
    public void showTVName(){
        FPTCorporation fpt=new FPTCorporation();
        fpt.tvName="VinGroup LCD";
        fpt.setTvName();

        //k dùng được do k cùng package
        //tvChannel = "";
        //setTvChannel();
    }
}
