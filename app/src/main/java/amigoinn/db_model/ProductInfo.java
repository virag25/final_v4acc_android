package amigoinn.db_model;

import java.util.ArrayList;
import java.util.List;

import amigoinn.activerecordbase.ActiveRecordBase;
import amigoinn.activerecordbase.ActiveRecordException;
import amigoinn.activerecordbase.CamelNotationHelper;
import amigoinn.example.v4sales.AccountApplication;
import amigoinn.modelmapper.ModelMapper;

/**
 * Created by Virag kuvadia on 24-04-2016.
 */
public class ProductInfo extends ActiveRecordBase {



    @ModelMapper(JsonKey = "StockNo")
    public String StockNo = "";

    @ModelMapper(JsonKey = "category")
    public String category = "";

    @ModelMapper(JsonKey = "itemgroup")
    public String itemgroup = "";
    @ModelMapper(JsonKey = "product_name")
    public String product_name = "";
    @ModelMapper(JsonKey = "model")
    public String product_model = "";

    @ModelMapper(JsonKey = "size")
    public String product_size = "";
    //
    @ModelMapper(JsonKey = "LeastSalableQty")
    public String LeastSalableQty = "";
    //
    @ModelMapper(JsonKey = "Retail_Price")
    public String Retail_Price = "";
    //
    @ModelMapper(JsonKey = "ImagePresent")
    public String ImagePresent = "";

    public static ArrayList<ProductInfo> getAllProduct() {
        ArrayList<ProductInfo> m_list = new ArrayList<ProductInfo>();
        try {
            List<ProductInfo> lst = AccountApplication.Connection().findAll(
                    ProductInfo.class);
            if (lst != null && lst.size() > 0) {
                m_list = new ArrayList<ProductInfo>(lst);
            }
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
        return m_list;
    }


    public static ProductInfo getProductInfoById(int Code) {
        try {
            List<ProductInfo> lst = AccountApplication.Connection().find(
                    ProductInfo.class,
                    CamelNotationHelper.toSQLName("client_code") + "=?",
                    new String[]{String.valueOf(Code)});
            if (lst != null && lst.size() > 0) {
                return lst.get(0);
            }
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
        return null;
    }

}
