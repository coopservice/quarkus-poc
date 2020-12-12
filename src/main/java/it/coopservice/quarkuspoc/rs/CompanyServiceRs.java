package it.coopservice.quarkuspoc.rs;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import it.coopservice.api.service.RsRepositoryServiceV3;
import it.coopservice.quarkuspoc.model.Company;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static it.coopservice.quarkuspoc.management.AppConstants.COMPANIES_PATH;


@Path(COMPANIES_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class CompanyServiceRs extends RsRepositoryServiceV3<Company, String> {


    public CompanyServiceRs() {
        super(Company.class);
    }

    @Override
    protected String getDefaultOrderBy() {
        return "name asc";
    }

    @Override
    public PanacheQuery<Company> getSearch(String orderBy) throws Exception {
        PanacheQuery<Company> search;
        Sort sort = sort(orderBy);

        if (sort != null) {
            search = Company.find("select a from Company a", sort);
        } else {
            search = Company.find("select a from Company a");
        }
        if (nn("obj.code")) {
            search
                    .filter("obj.code", Parameters.with("code", get("obj.code")));
        }
        if (nn("like.name")) {
            search
                    .filter("like.name", Parameters.with("name", likeParamToLowerCase("like.name")));
        }
        if (nn("obj.ldap_group")) {
            search
                    .filter("obj.ldap_group", Parameters.with("ldap_group", get("obj.ldap_group")));
        }
        search.filter("obj.active", Parameters.with("active", true));
        return search;
    }
}
