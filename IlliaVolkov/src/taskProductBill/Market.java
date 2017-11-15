package taskProductBill;

import javax.swing.*;

public class Market {

    private Bill[] billList;

    Market(){

        billList = new Bill[100];
    }

    private static int questionCreateBill(){
        return JOptionPane.showConfirmDialog( null, "Создать новый чек?","Ожидание продолжения работы", JOptionPane.YES_NO_OPTION);
    }

    public static void main(String[] args) {

        Market myMarket = new Market();

        JOptionPane.showMessageDialog(null, "Добрый день!\nМагазин начинает работу");

        String stringCountProducts = JOptionPane.showInputDialog("Введите количество продуктов в ассортименте магазина", 0);
        int countProducts = Integer.parseInt((stringCountProducts == null ? "0": stringCountProducts));

        Product[] productsList = Product.initProductsList(countProducts);

        String nameSaler = JOptionPane.showInputDialog("Введите имя Продавца");

        int key = questionCreateBill();

        while (key == JOptionPane.YES_OPTION) {

            Bill currentBill = new Bill(1, 100, nameSaler, productsList);

            while (!currentBill.closed){

                currentBill.choseProduct();

                Bill.showInfo(currentBill);

                currentBill.allProductsSelected();

                currentBill.closeBill();

                myMarket.addBillInList(currentBill);

            }

            key = questionCreateBill();
         }

         showInfo(myMarket);

    }

    private void addBillInList(Bill currentBill) {

        if (currentBill.closed) {

            for (int i = 0; i < this.billList.length; i++) {
                if (this.billList[i] == null) {
                    this.billList[i] = currentBill;

                    break;
                }
            }
        }
    }

    public static void showInfo(Market myMarket){

        System.out.println("\n\n\n СТАТИСТИКА РАБОТЫ МАГАЗИНА");

        for (int i = 0; i < myMarket.billList.length; i++) {

            if (myMarket.billList[i] != null) {

                Bill.showInfo(myMarket.billList[i]);
            }
        }
    }
}
