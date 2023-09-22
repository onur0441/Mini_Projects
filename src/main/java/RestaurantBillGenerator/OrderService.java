package RestaurantBillGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//10-orderla ilgili işlemler
public class OrderService {

    Scanner inp=new Scanner(System.in);

    public List<Order> orderList=new ArrayList<>();


    //11-sipariş oluşturma
    public void createOrder(DishService dishService){

        int dishCode;

        do {
            System.out.println("Lütfen ürün kodunu giriniz(ÇIKIŞ için 0!) : ");
            dishCode=inp.nextInt();//100-->bu code ile dish objesini bulmamız gerekir:12
            Dish dish=dishService.findDishByCode(dishCode);//adana kebabı
            if (dish!=null){
                System.out.println("Adet giriniz: ");
                int number=inp.nextInt();//3
                //bu yemek daha önce sipariş edilmiş mi-->13
                Order order=findOrderByDish(dish);//null veya mevcut sipariş
                if (order!=null){
                    //bu yemek daha önce 2 adet sipariş edilmiş
                    order.setNumberOfDish(order.getNumberOfDish()+number);//5
                    order.setOrderPrice();
                }else {
                    //yeni sipariş oluşturalım
                    order=new Order(dish,number);
                    order.setOrderPrice();
                    this.orderList.add(order);

                }
            }
            //sipariş oluşunca altta sipariş listesini görüntüleyelim-->14
            listOrders();

        }while (dishCode!=0);


    }

    //13-yemek bilgisi ile orderı bulma
    public Order findOrderByDish(Dish dish){
        for (Order order:this.orderList){
            if (order.getDish().equals(dish)){
                return order;
            }
        }
        return null;
    }

    //14-siparişleri yazdırma
    public void listOrders(){
        this.orderList.forEach(order->
                System.out.printf("Sipariş kodu:%-5s  Lezzet kodu:%-4s Lezzet Adı:%-20s Adet:%-2s Tutar:%-5s\n",
                        order.getOrderCode(),order.getDish().getCode(),order.getDish().getName(),order.getNumberOfDish(),order.getOrderPrice())
        );
    }


    //15-hesabı oluşturma:herbir siparişin tutarını toplayalım
    public void printBill(){
        double total=0;
        System.out.println("    Lezzet Restaurant Lezzet Fişi     ");
        listOrders();
        for (Order order:this.orderList){
            //total = total+order.getOrderPrice()
            total+=order.getOrderPrice();
        }
        System.out.println("Toplam tutar : "+total);
        System.out.println("Afiyet olsun...Yine bekleriz:)");
        //adisyon kapatıldı, listeyi boşaltalım, yeni masaya geçilebilir.
        this.orderList.clear();
    }



}
