package ua.artcode.market.Util;

import ua.artcode.market.controllers.AppDBImpl;

public class Generator{

    AppDBImpl currentAppDBImpl;

    public Generator(AppDBImpl currentAppDBImpl) {
        this.currentAppDBImpl = currentAppDBImpl;
    }

    public void initSalesMans(int countSalesMans){

        for (int i = 1; i <= countSalesMans; i++) {
           this.currentAppDBImpl.createSalesMan("SalesMan"+i, "SM"+i, ""+i);
        }
    }

    public void initProductsPrice(int countProducts){

        for (int i = 0; i < countProducts; i++) {
            this.currentAppDBImpl.createProduct(i+1,
                    "Goods"+(i+1),
                    Math.rint((Math.random()*10)*100)/100);
        }
    }
}
