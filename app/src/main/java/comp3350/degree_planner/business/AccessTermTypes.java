package comp3350.degree_planner.business;

import comp3350.degree_planner.persistence.DataAccess;

/**
 * Created by Penny He on 7/16/2017.
 */

public class AccessTermTypes {
    private DataAccess dataAccess;

    public AccessTermTypes(DataAccess dataAccess){ this.dataAccess = dataAccess; }

    public int getTermTypeIdByName(String termType){
        int termTypeId = -1;

        try{
            termTypeId = dataAccess.getTermTypeIdByName(termType);
        }catch(Exception e){
            e.printStackTrace();
        }
        return termTypeId;
    }
}
