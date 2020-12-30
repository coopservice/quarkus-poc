package it.coopservice.quarkuspoc.rs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import it.coopservice.api.service.RsRepositoryServiceV3;
import it.coopservice.quarkuspoc.model.Company;
import it.coopservice.quarkuspoc.model.Operation;
import org.hibernate.Session;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static it.coopservice.quarkuspoc.management.AppConstants.OPERATIONS_PATH;


@Path(OPERATIONS_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class OperationServiceRs extends RsRepositoryServiceV3<Operation, String> {


    public OperationServiceRs() {
        super(Operation.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "name asc";
    }

    @Override
    public PanacheQuery<Operation> getSearch(String orderBy) throws Exception {
        PanacheQuery<Operation> search;
        Sort sort = sort(orderBy);

        if (sort != null) {
            search = Operation.find(null, sort);
        } else {
            search = Operation.find(null);
        }
        if (nn("obj.customer_uuids")) {
            String[] values = get("obj.customer_uuids").split(",");
            getEntityManager().unwrap(Session.class)
                    .enableFilter("obj.customer_uuids")
                    .setParameterList("customer_uuids", values);
        }
        return search;
    }
}
