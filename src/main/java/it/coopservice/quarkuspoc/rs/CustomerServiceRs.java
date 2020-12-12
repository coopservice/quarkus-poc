package it.coopservice.quarkuspoc.rs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import it.coopservice.api.service.RsRepositoryServiceV3;
import it.coopservice.quarkuspoc.model.Customer;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static it.coopservice.quarkuspoc.management.AppConstants.CUSTOMERS_PATH;


@Path(CUSTOMERS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class CustomerServiceRs extends RsRepositoryServiceV3<Customer, String> {


    public CustomerServiceRs() {
        super(Customer.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "name asc";
    }

    @Override
    public PanacheQuery<Customer> getSearch(String orderBy) throws Exception {
        PanacheQuery<Customer> search;
        Sort sort = sort(orderBy);

        if (sort != null) {
            search = Customer.find("select a from Customer a", sort);
        } else {
            search = Customer.find("select a from Customer a");
        }
        if (nn("obj.code")) {
            search
                    .filter("obj.code", Parameters.with("code", get("obj.code")));
        }
        if (nn("like.name")) {
            search
                    .filter("like.name", Parameters.with("name", likeParamToLowerCase("like.name")));
        }
        search.filter("obj.active", Parameters.with("active", true));
        return search;
    }

}
