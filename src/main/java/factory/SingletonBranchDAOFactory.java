package factory;

import api.dao.BranchDAO;
import dao.BranchDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class SingletonBranchDAOFactory {
    static Map<String, BranchDAO>  storageTypeBranchDAOs = new HashMap<>();

    public static BranchDAO getBranchDAOInstance(String storageType) {
        switch (storageType) {
            // we can add multiple types based on the requirements
            case "in-memory":
            default:
                if(!storageTypeBranchDAOs.containsKey(storageType)) {
                    storageTypeBranchDAOs.put(storageType, new BranchDAOImpl());
                }
                return storageTypeBranchDAOs.get(storageType);

        }
    }

    public static void resetData(String storageType) {
        storageTypeBranchDAOs.remove(storageType);
    }
}
