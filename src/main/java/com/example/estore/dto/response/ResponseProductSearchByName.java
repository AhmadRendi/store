package com.example.estore.dto.response;


import com.example.estore.Entity.Product;
import com.example.estore.Entity.Store;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NamedStoredProcedureQuery(
        name = "Product.search_name_product_two",
        procedureName = "search_name_product_two",
        resultClasses = {ResponseProductSearchByName.class},
        parameters =  {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "names"),
        }
)
public class ResponseProductSearchByName {

        private String name;

        private Long id;

        private Long price;

        private Store store;

//        public ResponseProductSearchByName(String name, Long id, Long price, Store store){
//                this.name = name;
//                this.id = id;
//                this.price = price;
//                this.store = store;
//        }

}
