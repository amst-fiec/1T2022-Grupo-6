import 'package:g3amstv2/utils/info.dart';
import 'package:g3amstv2/models/product.dart';
class Cart{
  final Product product;
  int numOfItems;
  Cart(this.product, this.numOfItems);
  int getTotal(){
    return product.price * numOfItems;
  }
}
class ShoppingCart{
  List<Cart> carItems;
  ShoppingCart(this.carItems);
  int getTotal(){
    int total = 0;
    for (Cart item in carItems){
      total += item.getTotal();
    }
    return total;
  }
  Cart getItem(index){
    return carItems[index];
  }
  void removeAt(index){
    carItems.removeAt(index);
  }
  int getLenght(){
    return carItems.length;
  }
  void addItem(Product product){
    for(Cart item in carItems) {
      if (item.product == product){
        item.numOfItems += 1;
        return;
      }
    };
    carItems.add(new Cart(product, 1));
  }
}
ShoppingCart demoCarts = new ShoppingCart([
  Cart(promos[0], 1),
  Cart(promos[2], 2)
]);