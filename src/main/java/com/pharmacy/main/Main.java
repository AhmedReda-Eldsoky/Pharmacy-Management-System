package com.pharmacy.main;

import com.pharmacy.dao.*;
import com.pharmacy.model.Product;
import com.pharmacy.model.Sale;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ProductDao productDao = new ProductDaoImpl();
    static SaleDao saleDao = new SaleDaoImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        while (true) {
            System.out.print("""
                    ========================================
                         نظام إدارة الصيدلية (Pharmacy System)
                    ========================================
                    1. إضافة منتج جديد
                    2. عرض جميع المنتجات
                    3. البحث عن منتج بالرقم (ID)
                    4. تعديل بيانات منتج
                    5. حذف منتج
                    6. تسجيل عملية بيع جديدة
                    7. عرض جميع الفواتير/المبيعات
                    8. خروج من النظام
                    ========================================
                    اختر رقم العملية:\s""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("إختيار غير صحيح");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    Product newProd = addProduct();
                    if (newProd != null && productDao.addNewProduct(newProd)) {
                        System.out.println("تم اضافة منتج جديد");
                    } else {
                        System.out.println("فشل اضافة منتج جديد");
                    }
                    break;
                case 2:
                    List<Product> products = productDao.findAll();
                    if (!products.isEmpty()) {
                        for (Product p : products) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("لا توجد منتجات لعرضها");
                    }
                    break;
                case 3:
                    Product product = productDao.findById(searchById());
                    if (product != null) {
                        System.out.println(product);
                    } else {
                        System.out.println("هذا المنتج غير موجود");
                    }
                    break;
                case 4:
                    Product updated = editProduct();
                    if (updated != null) {
                        if (productDao.updateProduct(updated)) {
                            System.out.println("تم تعديل المنتج بنجاح");
                        } else {
                            System.out.println("فشل تعديل المنتج");
                        }
                    }
                    break;
                case 5:
                    if (productDao.deleteProduct(removeProduct())) {
                        System.out.println("تم حذف المنتج بنجاح");
                    } else {
                        System.out.println("فشل حذف المنتج");
                    }
                    break;
                case 6:
                    Sale sale = newSale();
                    if (sale != null && saleDao.addNewSale(sale)) {
                        System.out.println("تم اضافة عملية بيع جديدة");
                    } else {
                        System.out.println("فشل اضافة عملية البيع");
                    }
                    break;
                case 7:
                    List<Sale> saleList = saleDao.findAllSales();
                    if (!saleList.isEmpty()) {
                        for (Sale s : saleList) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("لا توجد عمليات بيع لعرضها");
                    }
                    break;
                case 8:
                    System.out.println("تم الخروج من النظام");
                    return;
                default:
                    System.out.println("إختيار غير صحيح");
            }
        }
    }

    public static Product addProduct() {
        while (true) {
            try {
                System.out.println("أدخل أسم المنتج الجديد");
                String name = scanner.nextLine();

                System.out.println("أدخل سعر المنتج الجديد");
                double price = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("أدخل كمية المنتج الجديد");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                return new Product(name, price, quantity);
            } catch (Exception e) {
                System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                scanner.nextLine();
            }
        }
    }

    public static int searchById() {
        while (true) {
            try {
                System.out.println("أدخل ID المنتج الذي تريد البحث عنه");
                int id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } catch (Exception e) {
                System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                scanner.nextLine();
            }
        }
    }

    public static Product editProduct() {
        while (true) {
            try {
                System.out.println("أدخل ID المنتج الذي تريد تعديله");
                int id = scanner.nextInt();
                scanner.nextLine();

                Product updatedProduct = productDao.findById(id);
                if (updatedProduct == null) {
                    System.out.println("هذا المنتج غير موجود");
                    return null;
                }
                System.out.println("بيانات المنتج الذي تريد تعديله هو: " + updatedProduct);
                boolean hasEdited = false;
                int editChoice = 0;
                while (true) {
                    System.out.println("""
                            ماذا تريد تعديله؟
                            1. الاسم
                            2. السعر
                            3. الكمية
                            4. حفظ التعديلات والخروج
                            5. الخروج بدون تعديلات
                            """);
                    try {
                        editChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                        scanner.nextLine();
                        continue;
                    }
                    switch (editChoice) {
                        case 1:
                            System.out.println("أدخل الأسم الجديد للمنتج");
                            updatedProduct.setName(scanner.nextLine());
                            hasEdited = true;
                            break;
                        case 2:
                            System.out.println("أدخل السعر الجديد للمنتج");
                            updatedProduct.setPrice(scanner.nextDouble());
                            scanner.nextLine();
                            hasEdited = true;
                            break;
                        case 3:
                            System.out.println("أدخل الكمية الجديدة للمنتج");
                            updatedProduct.setQuantity(scanner.nextInt());
                            scanner.nextLine();
                            hasEdited = true;
                            break;
                        case 4:
                            if (!hasEdited) {
                                System.out.println("يجب تعديل عنصر واحد على الأقل");
                                break;
                            }
                            return updatedProduct;
                        case 5:
                            return null;
                        default:
                            System.out.println("إختيار غير صحيح");
                    }
                }
            } catch (Exception e) {
                System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                scanner.nextLine();
            }
        }
    }

    public static int removeProduct() {
        while (true) {
            try {
                System.out.println("أدخل ID المنتج الذي تريد حذفه");
                int id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } catch (Exception e) {
                System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                scanner.nextLine();
            }
        }
    }

    public static Sale newSale() {
        while (true) {
            try {
                System.out.println("أدخل أسم العميل(المشتري)");
                String customerName = scanner.nextLine();

                System.out.println("أدخل سعر المنتج المباع");
                double totalAmount = scanner.nextDouble();
                scanner.nextLine();
                return new Sale(customerName, totalAmount, new Date());
            } catch (Exception e) {
                System.out.println("حدث خطأ, يرجى ادخال قيم صحيحة");
                scanner.nextLine();
            }
        }
    }
}